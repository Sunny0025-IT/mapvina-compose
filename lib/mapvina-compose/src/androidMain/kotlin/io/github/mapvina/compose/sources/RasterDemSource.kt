package io.github.mapvina.compose.sources

import io.github.mapvina.android.style.sources.RasterDemSource as MLNRasterDemSource
import io.github.mapvina.android.style.sources.TileSet
import io.github.mapvina.compose.util.correctedAndroidUri
import io.github.mapvina.compose.util.toLatLngBounds

public actual class RasterDemSource : Source {
  override val impl: MLNRasterDemSource

  internal constructor(source: MLNRasterDemSource) {
    impl = source
  }

  public actual constructor(id: String, uri: String, tileSize: Int) {
    impl = MLNRasterDemSource(id, uri.correctedAndroidUri(), tileSize)
  }

  public actual constructor(
    id: String,
    tiles: List<String>,
    options: TileSetOptions,
    tileSize: Int,
    demEncoding: RasterDemEncoding,
  ) {
    impl =
      MLNRasterDemSource(
        id,
        TileSet(
            "{\"type\": \"raster-dem\"}",
            *tiles.map { it.correctedAndroidUri() }.toTypedArray(),
          )
          .apply {
            minZoom = options.minZoom.toFloat()
            maxZoom = options.maxZoom.toFloat()
            encoding = demEncoding.value
            options.boundingBox?.let { setBounds(it.toLatLngBounds()) }
            attribution = options.attributionHtml
          },
        tileSize,
      )
  }
}
