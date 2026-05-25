package io.github.mapvina.compose.sources

import kotlinx.serialization.json.JsonObject
import io.github.mapvina.android.style.sources.TileSet
import io.github.mapvina.android.style.sources.VectorSource as MLNVectorSource
import io.github.mapvina.compose.expressions.ast.Expression
import io.github.mapvina.compose.expressions.ast.ExpressionContext
import io.github.mapvina.compose.expressions.dsl.const
import io.github.mapvina.compose.expressions.value.BooleanValue
import io.github.mapvina.compose.util.correctedAndroidUri
import io.github.mapvina.compose.util.toLatLngBounds
import io.github.mapvina.compose.util.toMLNExpression
import io.github.mapvina.spatialk.geojson.Feature
import io.github.mapvina.spatialk.geojson.Geometry

public actual class VectorSource : Source {
  override val impl: MLNVectorSource

  internal constructor(source: MLNVectorSource) {
    impl = source
  }

  public actual constructor(id: String, uri: String) {
    impl = MLNVectorSource(id, uri.correctedAndroidUri())
  }

  public actual constructor(id: String, tiles: List<String>, options: TileSetOptions) {
    impl =
      MLNVectorSource(
        id,
        TileSet("{\"type\": \"vector\"}", *tiles.map { it.correctedAndroidUri() }.toTypedArray())
          .apply {
            minZoom = options.minZoom.toFloat()
            maxZoom = options.maxZoom.toFloat()
            scheme =
              when (options.tileCoordinateSystem) {
                TileCoordinateSystem.XYZ -> "xyz"
                TileCoordinateSystem.TMS -> "tms"
              }
            options.boundingBox?.let { setBounds(it.toLatLngBounds()) }
            attribution = options.attributionHtml
          },
      )
  }

  public actual fun querySourceFeatures(
    sourceLayerIds: Set<String>,
    predicate: Expression<BooleanValue>,
  ): List<Feature<Geometry, JsonObject?>> {
    return impl
      .querySourceFeatures(
        sourceLayerIds = sourceLayerIds.toTypedArray(),
        filter =
          predicate
            .takeUnless { it == const(true) }
            ?.compile(ExpressionContext.None)
            ?.toMLNExpression(),
      )
      .map { Feature.fromJson(it.toJson()) }
  }
}
