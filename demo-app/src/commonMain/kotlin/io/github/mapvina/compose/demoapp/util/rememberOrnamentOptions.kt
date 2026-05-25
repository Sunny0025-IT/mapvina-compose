package io.github.mapvina.compose.demoapp.util

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import io.github.mapvina.compose.map.OrnamentOptions

@Composable expect fun rememberOrnamentOptions(padding: PaddingValues): OrnamentOptions
