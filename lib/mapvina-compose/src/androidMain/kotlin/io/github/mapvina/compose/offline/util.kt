package io.github.mapvina.compose.offline

import io.github.mapvina.android.offline.OfflineGeometryRegionDefinition
import io.github.mapvina.android.offline.OfflineRegion
import io.github.mapvina.android.offline.OfflineRegionDefinition
import io.github.mapvina.android.offline.OfflineRegionStatus
import io.github.mapvina.android.offline.OfflineTilePyramidRegionDefinition
import io.github.mapvina.compose.util.toBoundingBox
import io.github.mapvina.compose.util.toLatLngBounds
import io.github.mapvina.compose.util.toMlnGeometry
import io.github.mapvina.spatialk.geojson.Geometry

internal fun OfflineRegionDefinition.toOfflinePackDefinition() =
  when (this) {
    is OfflineTilePyramidRegionDefinition ->
      OfflinePackDefinition.TilePyramid(
        // can this ever be null? assuming no until proven otherwise
        styleUrl = styleURL!!,
        bounds = bounds!!.toBoundingBox(),
        minZoom = minZoom.toInt(),
        maxZoom = if (maxZoom.isInfinite()) null else maxZoom.toInt(),
      )
    is OfflineGeometryRegionDefinition ->
      OfflinePackDefinition.Shape(
        styleUrl = styleURL!!,
        shape = Geometry.fromJson(geometry!!.toJson()),
        minZoom = minZoom.toInt(),
        maxZoom = if (maxZoom.isInfinite()) null else maxZoom.toInt(),
      )
    else -> throw IllegalArgumentException("Unknown OfflineRegionDefinition type: $this")
  }

internal fun OfflinePackDefinition.toMLNOfflineRegionDefinition(pixelRatio: Float) =
  when (this) {
    is OfflinePackDefinition.TilePyramid ->
      OfflineTilePyramidRegionDefinition(
        styleURL = styleUrl,
        bounds = bounds.toLatLngBounds(),
        minZoom = minZoom.toDouble(),
        maxZoom = maxZoom?.toDouble() ?: Double.POSITIVE_INFINITY,
        pixelRatio = pixelRatio,
      )
    is OfflinePackDefinition.Shape ->
      OfflineGeometryRegionDefinition(
        styleURL = styleUrl,
        geometry = shape.toMlnGeometry(),
        minZoom = minZoom.toDouble(),
        maxZoom = maxZoom?.toDouble() ?: Double.POSITIVE_INFINITY,
        pixelRatio = pixelRatio,
      )
  }

internal fun OfflineRegionStatus.toDownloadProgress() =
  DownloadProgress.Healthy(
    completedResourceCount = completedResourceCount,
    completedResourceBytes = completedResourceSize,
    completedTileCount = completedTileCount,
    completedTileBytes = completedTileSize,
    status =
      if (isComplete) DownloadStatus.Complete
      else
        when (downloadState) {
          OfflineRegion.STATE_ACTIVE -> DownloadStatus.Downloading
          OfflineRegion.STATE_INACTIVE -> DownloadStatus.Paused
          else -> error("Unknown OfflineRegion state: $downloadState")
        },
    isRequiredResourceCountPrecise = isRequiredResourceCountPrecise,
    requiredResourceCount = requiredResourceCount,
  )
