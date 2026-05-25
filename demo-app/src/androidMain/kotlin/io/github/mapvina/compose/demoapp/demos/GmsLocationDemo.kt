package io.github.mapvina.compose.demoapp.demos

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt
import kotlinx.coroutines.launch
import io.github.mapvina.compose.camera.CameraPosition
import io.github.mapvina.compose.demoapp.DemoState
import io.github.mapvina.compose.demoapp.design.CardColumn
import io.github.mapvina.compose.gms.rememberFusedLocationProvider
import io.github.mapvina.compose.gms.rememberFusedOrientationProvider
import io.github.mapvina.compose.location.LocationPuck
import io.github.mapvina.compose.location.UserLocationState
import io.github.mapvina.compose.location.mostAccurateBearing
import io.github.mapvina.compose.location.rememberUserLocationState
import io.github.mapvina.compose.material3.LocationPuckDefaults
import io.github.mapvina.spatialk.units.Bearing
import io.github.mapvina.spatialk.units.extensions.inDegrees
import io.github.mapvina.spatialk.units.extensions.inMeters

object GmsLocationDemo : Demo {
  override val name = "Gms Location"

  private var locationClickedCount by mutableIntStateOf(0)

  private var locationState by mutableStateOf<UserLocationState?>(null)

  @Composable
  override fun MapContent(state: DemoState, isOpen: Boolean) {
    if (!isOpen) return

    val coroutineScope = rememberCoroutineScope()

    // this if _is_ a permission check Lint just doesn't know that
    @SuppressLint("MissingPermission")
    if (state.locationPermissionState.hasPermission) {
      val locationProvider = rememberFusedLocationProvider()
      val orientationProvider = rememberFusedOrientationProvider()
      val locationState = rememberUserLocationState(locationProvider, orientationProvider)

      LaunchedEffect(locationState) { this@GmsLocationDemo.locationState = locationState }

      LocationPuck(
        idPrefix = "gms-location",
        location = locationState.location,
        bearing = locationState.mostAccurateBearing(),
        cameraState = state.cameraState,
        accuracyThreshold = 0f,
        colors = LocationPuckDefaults.colors(),
        onClick = { location ->
          locationClickedCount++
          coroutineScope.launch {
            state.cameraState.animateTo(
              CameraPosition(target = location.position.value, zoom = 16.0)
            )
          }
        },
      )
    }
  }

  @Composable
  override fun SheetContent(state: DemoState, modifier: Modifier) {
    if (!state.locationPermissionState.hasPermission) {
      Button(onClick = state.locationPermissionState::requestPermission) {
        Text("Request permission")
      }
    } else {
      CardColumn { Text("User Location clicked $locationClickedCount times") }
    }

    if (locationState != null) {
      Card {
        Column(
          modifier = Modifier.padding(8.dp).fillMaxWidth(),
          verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
          Text(
            "Position: ${locationState?.location?.position?.value} +- ${locationState?.location?.position?.accuracy?.inMeters?.roundToInt()}m"
          )
          Text(
            "Course: ${locationState?.location?.course?.value?.smallestRotationTo(Bearing.North)?.inDegrees?.roundToInt()} +- ${locationState?.location?.course?.accuracy?.inDegrees?.roundToInt()}"
          )
          Text(
            "Orientation: ${
              locationState?.orientation?.orientation?.value?.smallestRotationTo(
                Bearing.North
              )?.inDegrees?.roundToInt()
            } +- ${locationState?.orientation?.orientation?.accuracy?.inDegrees?.roundToInt()}"
          )
        }
      }
    }
  }
}
