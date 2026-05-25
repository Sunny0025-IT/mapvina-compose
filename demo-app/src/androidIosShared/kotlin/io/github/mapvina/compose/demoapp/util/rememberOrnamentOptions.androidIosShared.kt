package io.github.mapvina.compose.demoapp.util

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import io.github.mapvina.compose.map.OrnamentOptions

@Composable
actual fun rememberOrnamentOptions(padding: PaddingValues): OrnamentOptions {
  return remember(padding) { OrnamentOptions.OnlyLogo.copy(padding = padding) }
}
