package io.github.mapvina.compose.sources

import kotlinx.serialization.json.JsonObject
import io.github.mapvina.spatialk.geojson.Feature
import io.github.mapvina.spatialk.geojson.FeatureCollection

public actual class GeoJsonSource : Source {

  @Suppress("UNREACHABLE_CODE") override val impl: Nothing = TODO()

  public actual constructor(id: String, data: GeoJsonData, options: GeoJsonOptions)

  public actual fun setData(data: GeoJsonData) {
    TODO()
  }

  public actual fun isCluster(feature: Feature<*, JsonObject?>): Boolean {
    TODO()
  }

  public actual fun getClusterExpansionZoom(feature: Feature<*, JsonObject?>): Double {
    TODO()
  }

  public actual fun getClusterChildren(
    feature: Feature<*, JsonObject?>
  ): FeatureCollection<*, JsonObject?> {
    TODO()
  }

  public actual fun getClusterLeaves(
    feature: Feature<*, JsonObject?>,
    limit: Long,
    offset: Long,
  ): FeatureCollection<*, JsonObject?> {
    TODO()
  }
}
