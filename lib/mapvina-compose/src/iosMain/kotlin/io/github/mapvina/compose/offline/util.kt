package io.github.mapvina.compose.offline

import MapVina.MLNOfflinePackProgress
import MapVina.MLNOfflinePackStateActive
import MapVina.MLNOfflinePackStateComplete
import MapVina.MLNOfflinePackStateInactive
import MapVina.MLNOfflinePackStateInvalid
import MapVina.MLNOfflinePackStateUnknown
import MapVina.MLNOfflineRegionProtocol
import MapVina.MLNShape
import MapVina.MLNShapeOfflineRegion
import MapVina.MLNTilePyramidOfflineRegion
import io.github.mapvina.compose.util.toBoundingBox
import io.github.mapvina.compose.util.toByteArray
import io.github.mapvina.compose.util.toMLNCoordinateBounds
import io.github.mapvina.compose.util.toNSData
import io.github.mapvina.spatialk.geojson.Geometry
import io.github.mapvina.spatialk.geojson.toJson
import platform.Foundation.NSError
import platform.Foundation.NSURL
import platform.Foundation.NSUTF8StringEncoding
import platform.posix.UINT64_MAX

internal fun NSError.toOfflineManagerException() =
  OfflineManagerException(message = localizedDescription)

internal fun MLNOfflineRegionProtocol.toOfflinePackDefinition() =
  when (this) {
    is MLNTilePyramidOfflineRegion ->
      OfflinePackDefinition.TilePyramid(
        styleUrl = styleURL.toString(),
        bounds = bounds.toBoundingBox(),
        minZoom = minimumZoomLevel.toInt(),
        maxZoom = if (maximumZoomLevel.isInfinite()) null else maximumZoomLevel.toInt(),
      )
    is MLNShapeOfflineRegion ->
      OfflinePackDefinition.Shape(
        styleUrl = styleURL.toString(),
        shape =
          Geometry.fromJson(
            shape.geoJSONDataUsingEncoding(NSUTF8StringEncoding).toByteArray().decodeToString()
          ),
        minZoom = minimumZoomLevel.toInt(),
        maxZoom = if (maximumZoomLevel.isInfinite()) null else maximumZoomLevel.toInt(),
      )
    else -> error("Unknown MLNOfflineRegion type: $this")
  }

internal fun OfflinePackDefinition.toMLNOfflineRegion(): MLNOfflineRegionProtocol =
  when (this) {
    is OfflinePackDefinition.TilePyramid ->
      MLNTilePyramidOfflineRegion(
        styleURL = NSURL(string = styleUrl),
        bounds = bounds.toMLNCoordinateBounds(),
        fromZoomLevel = minZoom.toDouble(),
        toZoomLevel = maxZoom?.toDouble() ?: Double.POSITIVE_INFINITY,
      )
    is OfflinePackDefinition.Shape ->
      MLNShapeOfflineRegion(
        styleURL = NSURL(string = styleUrl),
        shape =
          MLNShape.shapeWithData(
            data = shape.toJson().encodeToByteArray().toNSData(),
            encoding = NSUTF8StringEncoding,
            error = null,
          )!!,
        fromZoomLevel = minZoom.toDouble(),
        toZoomLevel = maxZoom?.toDouble() ?: Double.POSITIVE_INFINITY,
      )
  }

internal fun MLNOfflinePackProgress.toDownloadProgress(state: Long) =
  when (state) {
    MLNOfflinePackStateUnknown -> DownloadProgress.Unknown
    MLNOfflinePackStateInvalid ->
      DownloadProgress.Error("Invalid", "The pack has already been removed!")
    MLNOfflinePackStateInactive,
    MLNOfflinePackStateActive,
    MLNOfflinePackStateComplete ->
      DownloadProgress.Healthy(
        completedResourceCount = countOfResourcesCompleted.toLong(),
        completedResourceBytes = countOfBytesCompleted.toLong(),
        completedTileCount = countOfTilesCompleted.toLong(),
        completedTileBytes = countOfTileBytesCompleted.toLong(),
        status =
          when (state) {
            MLNOfflinePackStateInactive -> DownloadStatus.Paused
            MLNOfflinePackStateActive -> DownloadStatus.Downloading
            MLNOfflinePackStateComplete -> DownloadStatus.Complete
            else -> error("impossible")
          },
        // UINT64_MAX when unknown
        isRequiredResourceCountPrecise = maximumResourcesExpected < UINT64_MAX,
        requiredResourceCount = countOfResourcesExpected.toLong(),
      )
    else -> error("Unknown OfflinePack state: $state")
  }
