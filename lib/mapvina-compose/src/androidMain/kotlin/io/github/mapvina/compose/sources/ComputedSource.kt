package io.github.mapvina.compose.sources

import io.github.mapvina.android.geometry.LatLngBounds
import io.github.mapvina.android.style.sources.CustomGeometrySource
import io.github.mapvina.android.style.sources.CustomGeometrySourceOptions
import io.github.mapvina.android.style.sources.GeometryTileProvider
import io.github.mapvina.compose.util.toBoundingBox
import io.github.mapvina.compose.util.toLatLngBounds
import io.github.mapvina.geojson.FeatureCollection as MLNFeatureCollection
import io.github.mapvina.spatialk.geojson.BoundingBox
import io.github.mapvina.spatialk.geojson.FeatureCollection
import io.github.mapvina.spatialk.geojson.GeoJsonObject
import io.github.mapvina.spatialk.geojson.toJson

public actual class ComputedSource : Source {
  override val impl: CustomGeometrySource

  internal constructor(impl: CustomGeometrySource) {
    this.impl = impl
  }

  public actual constructor(
    id: String,
    options: ComputedSourceOptions,
    getFeatures: (bounds: BoundingBox, zoomLevel: Int) -> FeatureCollection<*, *>,
  ) : this(
    CustomGeometrySource(
      id = id,
      options = buildOptionMap(options),
      provider =
        object : GeometryTileProvider {
          override fun getFeaturesForBounds(
            bounds: LatLngBounds,
            zoomLevel: Int,
          ): MLNFeatureCollection {
            // HACK: we intentionally drop the FeatureCollection<*, *> type info in order to use the
            // runtime serializer detection of GeoJsonObject.
            val features: GeoJsonObject = getFeatures(bounds.toBoundingBox(), zoomLevel)
            return MLNFeatureCollection.fromJson(features.toJson())
          }
        },
    )
  )

  public actual fun invalidateBounds(bounds: BoundingBox) {
    impl.invalidateRegion(bounds.toLatLngBounds())
  }

  public actual fun invalidateTile(zoomLevel: Int, x: Int, y: Int) {
    impl.invalidateTile(zoomLevel = zoomLevel, x = x, y = y)
  }

  public actual fun setData(zoomLevel: Int, x: Int, y: Int, data: FeatureCollection<*, *>) {
    impl.setTileData(
      zoomLevel = zoomLevel,
      x = x,
      y = y,
      data = MLNFeatureCollection.fromJson(data.toJson()),
    )
  }

  private companion object {
    private fun buildOptionMap(options: ComputedSourceOptions) =
      CustomGeometrySourceOptions().apply {
        withMinZoom(options.minZoom)
        withMaxZoom(options.maxZoom)
        withBuffer(options.buffer)
        withTolerance(options.tolerance)
        withClip(options.clip)
        withWrap(options.wrap)
      }
  }
}
