package io.github.mapvina.compose.demoapp.util

import androidx.compose.foundation.layout.PaddingValues
import io.github.mapvina.compose.demoapp.demos.Demo
import io.github.mapvina.compose.demoapp.demos.GestureOptionsDemo
import io.github.mapvina.compose.demoapp.demos.OfflineManagerDemo
import io.github.mapvina.compose.demoapp.demos.OrnamentOptionsDemo
import io.github.mapvina.compose.demoapp.demos.RenderOptionsDemo
import io.github.mapvina.compose.map.OrnamentOptions
import platform.UIKit.UIDevice

actual object Platform {
  actual val name = "iOS"

  actual val version = UIDevice.currentDevice.systemVersion

  actual val supportedFeatures = PlatformFeature.Everything

  actual val extraDemos: List<Demo> =
    listOf(GestureOptionsDemo, OrnamentOptionsDemo, OfflineManagerDemo, RenderOptionsDemo)

  actual fun padOrnaments(options: OrnamentOptions, padding: PaddingValues) =
    options.copy(padding = padding)
}
