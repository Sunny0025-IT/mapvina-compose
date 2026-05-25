@file:Suppress("unused")

package io.github.mapvina.compose.docsnippets

import androidx.compose.runtime.Composable
import org.jetbrains.compose.resources.ExperimentalResourceApi
import io.github.mapvina.compose.camera.CameraPosition
import io.github.mapvina.compose.camera.rememberCameraState
import io.github.mapvina.compose.location.LocationPuck
import io.github.mapvina.compose.location.LocationTrackingEffect
import io.github.mapvina.compose.location.mostAccurateBearing
import io.github.mapvina.compose.location.rememberDefaultLocationProvider
import io.github.mapvina.compose.location.rememberDefaultOrientationProvider
import io.github.mapvina.compose.location.rememberUserLocationState
import io.github.mapvina.compose.map.MapvinaMap

@Composable
@OptIn(ExperimentalResourceApi::class)
fun Location() {
  // -8<- [start:puck]
  val cameraState = rememberCameraState()

  val locationProvider = rememberDefaultLocationProvider()
  val orientationProvider =
    rememberDefaultOrientationProvider() // optional: get device orientation from sensors
  val locationState = rememberUserLocationState(locationProvider, orientationProvider)

  MapvinaMap(cameraState = cameraState) {
    LocationPuck(
      idPrefix = "user",
      location = locationState.location,
      // optional: combine course and orientation bearing
      bearing = locationState.mostAccurateBearing(),
      cameraState = cameraState,
    )

    LocationTrackingEffect(locationState = locationState) {
      val position = currentLocation.location?.position?.value
      if (position != null) {
        cameraState.animateTo(CameraPosition(target = position, zoom = 15.0))
      }
    }
  }
  // -8<- [end:puck]
}
