package io.github.mapvina.compose.demoapp.demos

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.github.mapvina.compose.demoapp.DemoState
import io.github.mapvina.compose.demoapp.OpenFreeMap
import io.github.mapvina.compose.demoapp.OtherStyles
import io.github.mapvina.compose.demoapp.Protomaps
import io.github.mapvina.compose.demoapp.Versatiles
import io.github.mapvina.compose.demoapp.design.CardColumn
import io.github.mapvina.compose.demoapp.design.SelectableListItem
import io.github.mapvina.compose.demoapp.design.Subheading

object StyleSelectorDemo : Demo {
  override val name = "Select a style"

  @Composable
  override fun SheetContent(state: DemoState, modifier: Modifier) {

    val stylesByProvider =
      mapOf(
        "Protomaps" to Protomaps.entries,
        "OpenFreeMap" to OpenFreeMap.entries,
        "Versatiles" to Versatiles.entries,
        "Other Styles" to OtherStyles.entries,
      )

    stylesByProvider.forEach { (provider, styles) ->
      Subheading(text = provider)
      CardColumn {
        styles.forEach { style ->
          SelectableListItem(
            text = style.displayName,
            onClick = { state.selectedStyle = style },
            isSelected = style == state.selectedStyle,
          )
        }
      }
    }
  }
}
