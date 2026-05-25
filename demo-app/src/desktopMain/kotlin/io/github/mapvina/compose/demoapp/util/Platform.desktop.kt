package io.github.mapvina.compose.demoapp.util

import androidx.compose.foundation.layout.PaddingValues
import io.github.mapvina.compose.demoapp.demos.Demo
import io.github.mapvina.compose.demoapp.demos.GestureOptionsDemo
import io.github.mapvina.compose.map.OrnamentOptions

actual object Platform {
  actual val name = System.getProperty("os.name")!!

  actual val version = System.getProperty("os.version")!!

  actual val supportedFeatures = emptySet<PlatformFeature>()

  actual val extraDemos: List<Demo> = listOf(GestureOptionsDemo)

  // Ornaments not supported on desktop
  actual fun padOrnaments(options: OrnamentOptions, padding: PaddingValues) = options
}
