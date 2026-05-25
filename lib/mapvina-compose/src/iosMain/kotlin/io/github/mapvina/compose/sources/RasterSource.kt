package io.github.mapvina.compose.sources

import MapVina.MLNRasterTileSource
import MapVina.MLNTileCoordinateSystemTMS
import MapVina.MLNTileCoordinateSystemXYZ
import MapVina.MLNTileSourceOptionAttributionHTMLString
import MapVina.MLNTileSourceOptionCoordinateBounds
import MapVina.MLNTileSourceOptionMaximumZoomLevel
import MapVina.MLNTileSourceOptionMinimumZoomLevel
import MapVina.MLNTileSourceOptionTileCoordinateSystem
import MapVina.MLNTileSourceOptionTileSize
import io.github.mapvina.compose.util.toMLNCoordinateBounds
import platform.Foundation.NSURL

public actual class RasterSource : Source {
  override val impl: MLNRasterTileSource

  internal constructor(source: MLNRasterTileSource) {
    this.impl = source
  }

  public actual constructor(id: String, uri: String, tileSize: Int) : super() {
    this.impl = MLNRasterTileSource(id, NSURL(string = uri), tileSize.toDouble())
  }

  public actual constructor(
    id: String,
    tiles: List<String>,
    options: TileSetOptions,
    tileSize: Int,
  ) : super() {
    this.impl =
      MLNRasterTileSource(
        identifier = id,
        tileURLTemplates = tiles,
        options =
          buildMap {
            this[MLNTileSourceOptionMinimumZoomLevel] = options.minZoom.toDouble()
            this[MLNTileSourceOptionMaximumZoomLevel] = options.maxZoom.toDouble()
            this[MLNTileSourceOptionTileSize] = tileSize.toDouble()
            this[MLNTileSourceOptionTileCoordinateSystem] =
              when (options.tileCoordinateSystem) {
                TileCoordinateSystem.XYZ -> MLNTileCoordinateSystemXYZ
                TileCoordinateSystem.TMS -> MLNTileCoordinateSystemTMS
              }
            if (options.boundingBox != null)
              this[MLNTileSourceOptionCoordinateBounds] =
                options.boundingBox.toMLNCoordinateBounds()
            if (options.attributionHtml != null)
              this[MLNTileSourceOptionAttributionHTMLString] = options.attributionHtml
          },
      )
  }
}
