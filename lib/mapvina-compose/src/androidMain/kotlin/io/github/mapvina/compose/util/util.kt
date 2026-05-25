package io.github.mapvina.compose.util

import android.graphics.PointF
import android.graphics.RectF
import android.view.Gravity
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.DpRect
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.LayoutDirection
import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonNull
import com.google.gson.JsonObject
import com.google.gson.JsonPrimitive
import java.net.URI
import java.net.URISyntaxException
import io.github.mapvina.android.geometry.LatLng
import io.github.mapvina.android.geometry.LatLngBounds
import io.github.mapvina.android.geometry.LatLngQuad
import io.github.mapvina.android.style.expressions.Expression as MLNExpression
import io.github.mapvina.compose.expressions.ast.BooleanLiteral
import io.github.mapvina.compose.expressions.ast.ColorLiteral
import io.github.mapvina.compose.expressions.ast.CompiledExpression
import io.github.mapvina.compose.expressions.ast.CompiledFunctionCall
import io.github.mapvina.compose.expressions.ast.CompiledListLiteral
import io.github.mapvina.compose.expressions.ast.CompiledMapLiteral
import io.github.mapvina.compose.expressions.ast.CompiledOptions
import io.github.mapvina.compose.expressions.ast.DpPaddingLiteral
import io.github.mapvina.compose.expressions.ast.FloatLiteral
import io.github.mapvina.compose.expressions.ast.NullLiteral
import io.github.mapvina.compose.expressions.ast.OffsetLiteral
import io.github.mapvina.compose.expressions.ast.StringLiteral
import io.github.mapvina.spatialk.geojson.BoundingBox
import io.github.mapvina.spatialk.geojson.Feature
import io.github.mapvina.spatialk.geojson.FeatureCollection
import io.github.mapvina.spatialk.geojson.Geometry
import io.github.mapvina.spatialk.geojson.GeometryCollection
import io.github.mapvina.spatialk.geojson.LineString
import io.github.mapvina.spatialk.geojson.MultiLineString
import io.github.mapvina.spatialk.geojson.MultiPoint
import io.github.mapvina.spatialk.geojson.MultiPolygon
import io.github.mapvina.spatialk.geojson.Point
import io.github.mapvina.spatialk.geojson.Polygon
import io.github.mapvina.spatialk.geojson.Position
import io.github.mapvina.spatialk.geojson.toJson

internal fun String.correctedAndroidUri(): String {
  return try {
    // tile URLs contain template params like {z}, {x}, {y}. These are illegal in a URI, so we need
    // to parse only the constant part of the URI and then append the template part after correction
    val partition = this.indexOf('{')
    val constPart = if (partition == -1) this else this.substring(0, partition)
    val templatePart = if (partition == -1) "" else this.substring(partition)
    val uri = URI(constPart)
    if (uri.scheme == "file" && uri.path.startsWith("/android_asset/"))
      URI("asset://${uri.path.removePrefix("/android_asset/")}").toString() + templatePart
    else this
  } catch (e: URISyntaxException) {
    e.printStackTrace()
    this
  }
}

internal fun DpOffset.toPointF(density: Density): PointF =
  with(density) { PointF(x.toPx(), y.toPx()) }

internal fun PointF.toOffset(density: Density): DpOffset =
  with(density) { DpOffset(x = x.toDp(), y = y.toDp()) }

internal fun DpRect.toRectF(density: Density): RectF =
  with(density) { RectF(left.toPx(), top.toPx(), right.toPx(), bottom.toPx()) }

internal fun LatLng.toPosition(): Position = Position(longitude = longitude, latitude = latitude)

internal fun Position.toLatLng(): LatLng = LatLng(latitude = latitude, longitude = longitude)

internal fun LatLngBounds.toBoundingBox(): BoundingBox =
  BoundingBox(northeast = northEast.toPosition(), southwest = southWest.toPosition())

internal fun BoundingBox.toLatLngBounds(): LatLngBounds =
  LatLngBounds.from(
    latNorth = northeast.latitude,
    lonEast = northeast.longitude,
    latSouth = southwest.latitude,
    lonWest = southwest.longitude,
  )

internal fun CompiledExpression<*>.toMLNExpression(): MLNExpression? =
  if (this == NullLiteral) null else MLNExpression.Converter.convert(normalizeJsonLike(false))

private fun buildLiteralArray(inLiteral: Boolean, block: JsonArray.() -> Unit): JsonArray {
  return if (inLiteral) {
    JsonArray().apply(block)
  } else {
    JsonArray(2).apply {
      add("literal")
      add(JsonArray().apply(block))
    }
  }
}

private fun buildLiteralObject(inLiteral: Boolean, block: JsonObject.() -> Unit): JsonObject {
  return if (inLiteral) {
    JsonObject().apply(block)
  } else {
    JsonObject().apply { add("literal", JsonObject().apply(block)) }
  }
}

private fun CompiledExpression<*>.normalizeJsonLike(inLiteral: Boolean): JsonElement =
  when (this) {
    NullLiteral -> JsonNull.INSTANCE
    is BooleanLiteral -> JsonPrimitive(value)
    is FloatLiteral -> JsonPrimitive(value)
    is StringLiteral -> JsonPrimitive(value)
    is OffsetLiteral ->
      buildLiteralArray(inLiteral) {
        add(value.x)
        add(value.y)
      }

    is ColorLiteral ->
      JsonPrimitive(
        value.toArgb().let {
          "rgba(${(it shr 16) and 0xFF}, ${(it shr 8) and 0xFF}, ${it and 0xFF}, ${value.alpha})"
        }
      )

    is DpPaddingLiteral ->
      buildLiteralArray(inLiteral) {
        add(value.calculateTopPadding().value)
        add(value.calculateRightPadding(LayoutDirection.Ltr).value)
        add(value.calculateBottomPadding().value)
        add(value.calculateLeftPadding(LayoutDirection.Ltr).value)
      }

    is CompiledFunctionCall ->
      JsonArray(args.size + 1).apply {
        add(name)
        args.forEachIndexed { i, v -> add(v.normalizeJsonLike(inLiteral || isLiteralArg(i))) }
      }

    is CompiledListLiteral<*> ->
      buildLiteralArray(inLiteral) { value.forEach { add(it.normalizeJsonLike(true)) } }

    is CompiledMapLiteral<*> ->
      buildLiteralObject(inLiteral) {
        value.forEach { (k, v) -> add(k, v.normalizeJsonLike(true)) }
      }

    is CompiledOptions<*> ->
      JsonObject().apply { value.forEach { (k, v) -> add(k, v.normalizeJsonLike(inLiteral)) } }
  }

internal fun Alignment.toGravity(layoutDir: LayoutDirection): Int {
  val (x, y) = align(IntSize(1, 1), IntSize(3, 3), layoutDir)
  val h =
    when (x) {
      0 -> Gravity.LEFT
      1 -> Gravity.CENTER_HORIZONTAL
      2 -> Gravity.RIGHT
      else -> error("Invalid alignment")
    }
  val v =
    when (y) {
      0 -> Gravity.TOP
      1 -> Gravity.CENTER_VERTICAL
      2 -> Gravity.BOTTOM
      else -> error("Invalid alignment")
    }
  return h or v
}

internal fun Geometry.toMlnGeometry(): io.github.mapvina.geojson.Geometry {
  return when (this) {
    is Point -> io.github.mapvina.geojson.Point.fromJson(toJson())
    is GeometryCollection<*> -> io.github.mapvina.geojson.GeometryCollection.fromJson(toJson())
    is LineString -> io.github.mapvina.geojson.LineString.fromJson(toJson())
    is MultiLineString -> io.github.mapvina.geojson.MultiLineString.fromJson(toJson())
    is MultiPoint -> io.github.mapvina.geojson.MultiPoint.fromJson(toJson())
    is MultiPolygon -> io.github.mapvina.geojson.MultiPolygon.fromJson(toJson())
    is Polygon -> io.github.mapvina.geojson.Polygon.fromJson(toJson())
  }
}

internal fun Feature<*, kotlinx.serialization.json.JsonObject?>.toMLNFeature():
  io.github.mapvina.geojson.Feature {
  return io.github.mapvina.geojson.Feature.fromJson(toJson())
}

internal fun io.github.mapvina.geojson.FeatureCollection.toSpatialKFeatureCollection():
  FeatureCollection<Geometry, kotlinx.serialization.json.JsonObject?> {
  return FeatureCollection.fromJson(toJson())
}

internal fun PositionQuad.toLatLngQuad() =
  LatLngQuad(
    topRight = this.topRight.toLatLng(),
    topLeft = this.topLeft.toLatLng(),
    bottomLeft = this.bottomLeft.toLatLng(),
    bottomRight = this.bottomRight.toLatLng(),
  )
