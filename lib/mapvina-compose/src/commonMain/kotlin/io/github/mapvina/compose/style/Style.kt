package io.github.mapvina.compose.style

import androidx.compose.ui.graphics.ImageBitmap
import io.github.mapvina.compose.layers.Layer
import io.github.mapvina.compose.sources.Source
import io.github.mapvina.compose.util.ImageResizeOptions

internal interface Style {
  fun addImage(id: String, image: ImageBitmap, sdf: Boolean, resizeOptions: ImageResizeOptions?)

  fun removeImage(id: String)

  fun getSource(id: String): Source?

  fun getSources(): List<Source>

  fun addSource(source: Source)

  fun removeSource(source: Source)

  fun getLayer(id: String): Layer?

  fun getLayers(): List<Layer>

  fun addLayer(layer: Layer)

  fun addLayerAbove(id: String, layer: Layer)

  fun addLayerBelow(id: String, layer: Layer)

  fun addLayerAt(index: Int, layer: Layer)

  fun removeLayer(layer: Layer)
}
