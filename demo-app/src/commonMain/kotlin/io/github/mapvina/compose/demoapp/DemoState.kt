package io.github.mapvina.compose.demoapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import io.github.mapvina.compose.camera.CameraState
import io.github.mapvina.compose.camera.rememberCameraState
import io.github.mapvina.compose.demoapp.demos.AnimatedLayerDemo
import io.github.mapvina.compose.demoapp.demos.CameraStateDemo
import io.github.mapvina.compose.demoapp.demos.ClusteredPointsDemo
import io.github.mapvina.compose.demoapp.demos.Demo
import io.github.mapvina.compose.demoapp.demos.MapClickDemo
import io.github.mapvina.compose.demoapp.demos.MapManipulationDemo
import io.github.mapvina.compose.demoapp.demos.MarkersDemo
import io.github.mapvina.compose.demoapp.demos.StyleSelectorDemo
import io.github.mapvina.compose.demoapp.demos.UserLocationDemo
import io.github.mapvina.compose.demoapp.util.Platform
import io.github.mapvina.compose.demoapp.util.PlatformFeature
import io.github.mapvina.compose.location.UserLocationState
import io.github.mapvina.compose.location.rememberDefaultLocationProvider
import io.github.mapvina.compose.location.rememberDefaultOrientationProvider
import io.github.mapvina.compose.location.rememberNullLocationProvider
import io.github.mapvina.compose.location.rememberNullOrientationProvider
import io.github.mapvina.compose.location.rememberUserLocationState
import io.github.mapvina.compose.map.GestureOptions
import io.github.mapvina.compose.map.OrnamentOptions
import io.github.mapvina.compose.map.RenderOptions
import io.github.mapvina.compose.style.StyleState
import io.github.mapvina.compose.style.rememberStyleState

enum class MapSize {
  Full,
  Half,
  Fixed,
}

enum class MapPosition {
  TopLeft,
  TopCenter,
  TopRight,
  CenterLeft,
  Center,
  CenterRight,
  BottomLeft,
  BottomCenter,
  BottomRight,
}

class MapManipulationState {
  var isVisible by mutableStateOf(true)
  var size by mutableStateOf(MapSize.Full)
  var position by mutableStateOf(MapPosition.Center)
}

class OrnamentOptionsState {
  var isMaterial3ControlsEnabled by
    mutableStateOf(PlatformFeature.InteropBlending in Platform.supportedFeatures)
}

class DemoState(
  val nav: NavHostController,
  val cameraState: CameraState,
  val styleState: StyleState,
  val locationState: UserLocationState,
  val locationPermissionState: LocationPermissionState,
  val mapManipulationState: MapManipulationState = MapManipulationState(),
  val ornamentOptionsState: OrnamentOptionsState = OrnamentOptionsState(),
) {

  val mapClickEvents = mutableStateListOf<MapClickEvent>()

  val demos =
    (listOf(
      StyleSelectorDemo,
      CameraStateDemo,
      AnimatedLayerDemo,
      MarkersDemo,
      MapClickDemo,
      ClusteredPointsDemo,
      UserLocationDemo,
      MapManipulationDemo,
    ) + Platform.extraDemos)

  var selectedStyle by mutableStateOf<DemoStyle>(Protomaps.Light)
  var renderOptions by mutableStateOf(RenderOptions.Standard)
  var gestureOptions by mutableStateOf(GestureOptions.Standard)
  var ornamentOptions by mutableStateOf(OrnamentOptions.AllEnabled)

  private val navDestinationState = mutableStateOf<NavDestination?>(null)

  val navDestination: NavDestination?
    get() = navDestinationState.value

  init {
    nav.addOnDestinationChangedListener { _, destination, _ ->
      navDestinationState.value = destination
    }
  }

  fun isDemoOpen(demo: Demo): Boolean {
    return navDestination?.route == demo.name
  }

  fun shouldRenderMapContent(demo: Demo): Boolean {
    return isDemoOpen(demo) || demo.mapContentVisibilityState?.value ?: false
  }
}

@Composable
fun rememberDemoState(): DemoState {
  val nav = rememberNavController()
  val cameraState = rememberCameraState()
  val styleState = rememberStyleState()

  val locationPermissionState = rememberLocationPermissionState()
  // this keying and swapping of LocationProviders is necessary because of the way the demo is set
  // up
  //
  // In a normal app, it would be best to avoid creating a LocationProvider and everything dependent
  // on it altogether, if no permission has been granted. The at look at GmsLocationDemo on Android
  // for an example of this.
  val locationProvider =
    key(locationPermissionState.hasPermission) {
      if (locationPermissionState.hasPermission) {
        rememberDefaultLocationProvider()
      } else {
        rememberNullLocationProvider()
      }
    }
  val orientationProvider =
    key(locationPermissionState.hasPermission) {
      if (locationPermissionState.hasPermission) {
        rememberDefaultOrientationProvider()
      } else {
        rememberNullOrientationProvider()
      }
    }
  val locationState = rememberUserLocationState(locationProvider, orientationProvider)

  return remember(nav, cameraState, styleState, locationState, locationPermissionState) {
    DemoState(nav, cameraState, styleState, locationState, locationPermissionState)
  }
}

interface LocationPermissionState {
  val hasPermission: Boolean

  fun requestPermission()
}

@Composable expect fun rememberLocationPermissionState(): LocationPermissionState
