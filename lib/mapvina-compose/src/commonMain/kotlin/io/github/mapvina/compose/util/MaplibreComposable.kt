package io.github.mapvina.compose.util

import androidx.compose.runtime.ComposableTargetMarker

/**
 * This annotation marks a composable for use within the context of a
 * [io.github.mapvina.compose.map.MapvinaMap] `content`.
 */
@Retention(AnnotationRetention.BINARY)
@ComposableTargetMarker(description = "MapVina Composable")
@Target(
  AnnotationTarget.FILE,
  AnnotationTarget.FUNCTION,
  AnnotationTarget.PROPERTY_GETTER,
  AnnotationTarget.TYPE,
  AnnotationTarget.TYPE_PARAMETER,
)
public annotation class MapvinaComposable
