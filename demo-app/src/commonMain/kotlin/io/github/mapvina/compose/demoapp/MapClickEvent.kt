package io.github.mapvina.compose.demoapp

import androidx.compose.ui.unit.DpOffset
import io.github.mapvina.spatialk.geojson.Position

data class MapClickEvent(val position: Position, val offset: DpOffset)
