package io.github.mapvina.compose.demoapp.util

enum class PlatformFeature {
  InteropBlending,
  LayerStyling;

  companion object {
    val Everything = entries.toSet()
  }
}
