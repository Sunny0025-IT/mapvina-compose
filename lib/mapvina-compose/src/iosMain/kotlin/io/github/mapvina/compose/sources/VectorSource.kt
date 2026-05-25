package io.github.mapvina.compose.sources

import MapVina.MLNFeatureProtocol
import MapVina.MLNTileCoordinateSystemTMS
import MapVina.MLNTileCoordinateSystemXYZ
import MapVina.MLNTileSourceOptionAttributionHTMLString
import MapVina.MLNTileSourceOptionCoordinateBounds
import MapVina.MLNTileSourceOptionMaximumZoomLevel
import MapVina.MLNTileSourceOptionMinimumZoomLevel
import MapVina.MLNTileSourceOptionTileCoordinateSystem
import MapVina.MLNVectorTileSource
import kotlinx.serialization.json.JsonObject
import io.github.mapvina.compose.expressions.ast.Expression
import io.github.mapvina.compose.expressions.ast.ExpressionContext
import io.github.mapvina.compose.expressions.dsl.const
import io.github.mapvina.compose.expressions.value.BooleanValue
import io.github.mapvina.compose.util.toFeature
import io.github.mapvina.compose.util.toMLNCoordinateBounds
import io.github.mapvina.compose.util.toNSPredicate
import io.github.mapvina.spatialk.geojson.Feature
import io.github.mapvina.spatialk.geojson.Geometry
import platform.Foundation.NSURL

public actual class VectorSource : Source {
  override val impl: MLNVectorTileSource

  internal constructor(source: MLNVectorTileSource) {
    impl = source
  }

  public actual constructor(id: String, uri: String) : super() {
    this.impl = MLNVectorTileSource(id, NSURL(string = uri))
  }

  public actual constructor(id: String, tiles: List<String>, options: TileSetOptions) : super() {
    this.impl =
      MLNVectorTileSource(
        identifier = id,
        tileURLTemplates = tiles,
        options =
          buildMap {
            put(MLNTileSourceOptionMinimumZoomLevel, options.minZoom.toDouble())
            put(MLNTileSourceOptionMaximumZoomLevel, options.maxZoom.toDouble())
            put(
              MLNTileSourceOptionTileCoordinateSystem,
              when (options.tileCoordinateSystem) {
                TileCoordinateSystem.XYZ -> MLNTileCoordinateSystemXYZ
                TileCoordinateSystem.TMS -> MLNTileCoordinateSystemTMS
              },
            )
            if (options.boundingBox != null)
              put(MLNTileSourceOptionCoordinateBounds, options.boundingBox.toMLNCoordinateBounds())
            if (options.attributionHtml != null)
              put(MLNTileSourceOptionAttributionHTMLString, options.attributionHtml)
          },
      )
  }

  public actual fun querySourceFeatures(
    sourceLayerIds: Set<String>,
    predicate: Expression<BooleanValue>,
  ): List<Feature<Geometry, JsonObject?>> {
    return impl
      .featuresInSourceLayersWithIdentifiers(
        sourceLayerIdentifiers = sourceLayerIds,
        predicate =
          predicate
            .takeUnless { it == const(true) }
            ?.compile(ExpressionContext.None)
            ?.toNSPredicate(),
      )
      .map { (it as MLNFeatureProtocol).toFeature() }
  }
}
