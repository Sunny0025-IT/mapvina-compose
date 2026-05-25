package io.github.mapvina.compose.sources

import MapVina.MLNDEMEncodingMapbox
import MapVina.MLNDEMEncodingTerrarium
import MapVina.MLNRasterDEMSource
import MapVina.MLNTileCoordinateSystemTMS
import MapVina.MLNTileCoordinateSystemXYZ
import MapVina.MLNTileSourceOptionAttributionHTMLString
import MapVina.MLNTileSourceOptionCoordinateBounds
import MapVina.MLNTileSourceOptionDEMEncoding
import MapVina.MLNTileSourceOptionMaximumZoomLevel
import MapVina.MLNTileSourceOptionMinimumZoomLevel
import MapVina.MLNTileSourceOptionTileCoordinateSystem
import MapVina.MLNTileSourceOptionTileSize
import io.github.mapvina.compose.util.toMLNCoordinateBounds
import platform.Foundation.NSURL

public actual class RasterDemSource : Source {
  override val impl: MLNRasterDEMSource

  internal constructor(source: MLNRasterDEMSource) {
    this.impl = source
  }

  public actual constructor(id: String, uri: String, tileSize: Int) : super() {
    this.impl =
      MLNRasterDEMSource(
        identifier = id,
        configurationURL = NSURL(string = uri),
        tileSize = tileSize.toDouble(),
      )
  }

  public actual constructor(
    id: String,
    tiles: List<String>,
    options: TileSetOptions,
    tileSize: Int,
    demEncoding: RasterDemEncoding,
  ) : super() {
    this.impl =
      MLNRasterDEMSource(
        identifier = id,
        tileURLTemplates = tiles,
        options =
          buildMap {
            this[MLNTileSourceOptionDEMEncoding] =
              when (demEncoding) {
                RasterDemEncoding.Mapbox -> MLNDEMEncodingMapbox
                RasterDemEncoding.Terrarium -> MLNDEMEncodingTerrarium
                else -> demEncoding.value // not supported but let's not crash it
              }
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
