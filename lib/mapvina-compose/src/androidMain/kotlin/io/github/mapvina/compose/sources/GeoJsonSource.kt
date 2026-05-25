package io.github.mapvina.compose.sources

import java.net.URI
import kotlinx.serialization.json.JsonObject
import io.github.mapvina.android.style.sources.GeoJsonOptions as MLNGeoJsonOptions
import io.github.mapvina.android.style.sources.GeoJsonSource as MLNGeoJsonSource
import io.github.mapvina.compose.expressions.ast.ExpressionContext
import io.github.mapvina.compose.util.correctedAndroidUri
import io.github.mapvina.compose.util.toMLNExpression
import io.github.mapvina.compose.util.toMLNFeature
import io.github.mapvina.compose.util.toSpatialKFeatureCollection
import io.github.mapvina.spatialk.geojson.Feature
import io.github.mapvina.spatialk.geojson.FeatureCollection
import io.github.mapvina.spatialk.geojson.toJson

public actual class GeoJsonSource : Source {
  override val impl: MLNGeoJsonSource

  internal constructor(source: MLNGeoJsonSource) {
    impl = source
  }

  public actual constructor(id: String, data: GeoJsonData, options: GeoJsonOptions) {
    impl =
      when (data) {
        is GeoJsonData.Features ->
          MLNGeoJsonSource(id, data.geoJson.toJson(), buildOptionMap(options))

        is GeoJsonData.JsonString -> MLNGeoJsonSource(id, data.json, buildOptionMap(options))
        is GeoJsonData.Uri -> MLNGeoJsonSource(id, URI(data.uri), buildOptionMap(options))
      }
  }

  private fun buildOptionMap(options: GeoJsonOptions) =
    MLNGeoJsonOptions().apply {
      withMinZoom(options.minZoom)
      withMaxZoom(options.maxZoom)
      withBuffer(options.buffer)
      withTolerance(options.tolerance)
      withLineMetrics(options.lineMetrics)
      withCluster(options.cluster)
      withClusterMaxZoom(options.clusterMaxZoom)
      withClusterRadius(options.clusterRadius)
      withClusterMinPoints(options.clusterMinPoints)
      withSynchronousUpdate(options.synchronousUpdate)
      options.clusterProperties.forEach { (name, aggregator) ->
        withClusterProperty(
          name,
          aggregator.reducer.compile(ExpressionContext.None).toMLNExpression()!!,
          aggregator.mapper.compile(ExpressionContext.None).toMLNExpression()!!,
        )
      }
    }

  public actual fun setData(data: GeoJsonData) {
    when (data) {
      is GeoJsonData.Features -> impl.setGeoJson(data.geoJson.toJson())
      is GeoJsonData.JsonString -> impl.setGeoJson(data.json)
      is GeoJsonData.Uri -> impl.setUri(data.uri.correctedAndroidUri())
    }
  }

  public actual fun isCluster(feature: Feature<*, JsonObject?>): Boolean {
    return "cluster_id" in feature.properties.orEmpty()
  }

  public actual fun getClusterExpansionZoom(feature: Feature<*, JsonObject?>): Double {
    return impl.getClusterExpansionZoom(feature.toMLNFeature()).toDouble()
  }

  public actual fun getClusterChildren(
    feature: Feature<*, JsonObject?>
  ): FeatureCollection<*, JsonObject?> {
    return impl.getClusterChildren(feature.toMLNFeature()).toSpatialKFeatureCollection()
  }

  public actual fun getClusterLeaves(
    feature: Feature<*, JsonObject?>,
    limit: Long,
    offset: Long,
  ): FeatureCollection<*, JsonObject?> {
    return impl
      .getClusterLeaves(feature.toMLNFeature(), limit, offset)
      .toSpatialKFeatureCollection()
  }
}
