package io.github.mapvina.compose.demoapp.util

import androidx.compose.foundation.layout.PaddingValues
import io.github.mapvina.compose.demoapp.demos.Demo
import io.github.mapvina.compose.map.OrnamentOptions

expect object Platform {
  val name: String

  val version: String

  val supportedFeatures: Set<PlatformFeature>

  val extraDemos: List<Demo>

  fun padOrnaments(options: OrnamentOptions, padding: PaddingValues): OrnamentOptions
}
