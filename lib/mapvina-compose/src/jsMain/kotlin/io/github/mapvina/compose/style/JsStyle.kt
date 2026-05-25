package io.github.mapvina.compose.style

import androidx.compose.ui.graphics.ImageBitmap
import io.github.mapvina.compose.layers.Layer
import io.github.mapvina.compose.sources.Source
import io.github.mapvina.compose.util.ImageResizeOptions
import io.github.mapvina.kmp.js.map.Map

internal class JsStyle(internal val impl: Map) : Style {

  override fun addImage(
    id: String,
    image: ImageBitmap,
    sdf: Boolean,
    resizeOptions: ImageResizeOptions?,
  ) {}

  override fun removeImage(id: String) {}

  override fun getSource(id: String): Source? {
    return null
  }

  override fun getSources(): List<Source> {
    return emptyList()
  }

  override fun addSource(source: Source) {}

  override fun removeSource(source: Source) {}

  override fun getLayer(id: String): Layer? {
    return null
  }

  override fun getLayers(): List<Layer> {
    return emptyList()
  }

  override fun addLayer(layer: Layer) {}

  override fun addLayerAbove(id: String, layer: Layer) {}

  override fun addLayerBelow(id: String, layer: Layer) {}

  override fun addLayerAt(index: Int, layer: Layer) {}

  override fun removeLayer(layer: Layer) {}
}
