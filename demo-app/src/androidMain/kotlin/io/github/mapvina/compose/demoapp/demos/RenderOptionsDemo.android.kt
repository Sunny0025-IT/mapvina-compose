package io.github.mapvina.compose.demoapp.demos

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kotlin.math.roundToInt
import io.github.mapvina.compose.demoapp.DemoState
import io.github.mapvina.compose.demoapp.design.CardColumn
import io.github.mapvina.compose.demoapp.design.SegmentedButtonListItem
import io.github.mapvina.compose.demoapp.design.SliderListItem
import io.github.mapvina.compose.demoapp.design.SwitchListItem
import io.github.mapvina.compose.map.RenderOptions

object RenderOptionsDemo : Demo {
  override val name = "Configure rendering"

  @Composable
  override fun SheetContent(state: DemoState, modifier: Modifier) {
    CardColumn {
      SegmentedButtonListItem(
        options = RenderOptions.RenderMode.entries,
        selectedOption = state.renderOptions.renderMode,
        onOptionSelected = { option ->
          state.renderOptions = state.renderOptions.copy(renderMode = option)
        },
      )

      SliderListItem(
        text = "Maximum FPS",
        value = state.renderOptions.maximumFps?.toFloat() ?: 120f,
        onValueChange = { value ->
          state.renderOptions = state.renderOptions.copy(maximumFps = value.roundToInt())
        },
        valueLabel = { it.roundToInt().toString() },
        valueRange = 15f..120f,
        steps = 20,
      )

      SwitchListItem(
        text = "Enable Debug Overlays",
        checked = state.renderOptions.isDebugEnabled,
        onCheckedChange = { isChecked ->
          state.renderOptions = state.renderOptions.copy(isDebugEnabled = isChecked)
        },
      )
    }
  }
}
