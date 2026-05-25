package io.github.mapvina.compose.layers

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeNode
import androidx.compose.runtime.Updater
import androidx.compose.runtime.key
import io.github.mapvina.compose.style.LayerNode
import io.github.mapvina.compose.style.LocalStyleNode
import io.github.mapvina.compose.style.MapNodeApplier
import io.github.mapvina.compose.util.FeaturesClickHandler
import io.github.mapvina.compose.util.MapvinaComposable

@Composable
@MapvinaComposable
internal fun <T : Layer> LayerNode(
  factory: () -> T,
  update: Updater<LayerNode<T>>.() -> Unit,
  onClick: FeaturesClickHandler?,
  onLongClick: FeaturesClickHandler?,
) {
  val anchor = LocalAnchor.current
  val node = LocalStyleNode.current

  key(factory, anchor) {
    ComposeNode<LayerNode<T>, MapNodeApplier>(
      factory = { LayerNode(layer = factory(), anchor = anchor) },
      update = {
        if (!node.style.isUnloaded) {
          update()
          set(onClick) { this.onClick = it }
          set(onLongClick) { this.onLongClick = it }
        }
      },
    )
  }
}
