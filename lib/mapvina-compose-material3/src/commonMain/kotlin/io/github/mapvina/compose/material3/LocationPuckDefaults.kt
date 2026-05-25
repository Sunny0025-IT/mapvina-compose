package io.github.mapvina.compose.material3

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import io.github.mapvina.compose.location.LocationPuck
import io.github.mapvina.compose.location.LocationPuckColors

/** Material 3 themed defaults for [LocationPuck] parameters */
public object LocationPuckDefaults {
  @Composable
  public fun colors(
    dotFillColorCurrentLocation: Color = MaterialTheme.colorScheme.primary,
    dotFillColorOldLocation: Color = MaterialTheme.colorScheme.surfaceDim,
    dotStrokeColor: Color = contentColorFor(dotFillColorCurrentLocation),
    accuracyStrokeColor: Color = dotFillColorCurrentLocation,
    bearingColor: Color = MaterialTheme.colorScheme.secondary,
  ): LocationPuckColors {
    return LocationPuckColors(
      dotFillColorCurrentLocation = dotFillColorCurrentLocation,
      dotFillColorOldLocation = dotFillColorOldLocation,
      dotStrokeColor = dotStrokeColor,
      accuracyStrokeColor = accuracyStrokeColor,
      bearingColor = bearingColor,
    )
  }
}
