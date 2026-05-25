package io.github.mapvina.compose.demoapp.demos

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.UiComposable
import io.github.mapvina.compose.demoapp.DemoState
import io.github.mapvina.compose.util.MapvinaComposable
import io.github.mapvina.spatialk.geojson.BoundingBox

interface Demo {
  val name: String

  val region: BoundingBox?
    get() = null

  val mapContentVisibilityState: MutableState<Boolean>?
    get() = null

  @MapvinaComposable @Composable fun MapContent(state: DemoState, isOpen: Boolean) {}

  @UiComposable @Composable fun MapOverlayContent(state: DemoState, isOpen: Boolean) {}

  @UiComposable @Composable fun SheetContent(state: DemoState, modifier: Modifier) {}
}
