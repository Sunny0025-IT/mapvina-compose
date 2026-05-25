package io.github.mapvina.compose.style

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.unit.Density
import io.github.mapvina.android.maps.ImageContent
import io.github.mapvina.android.maps.ImageStretches
import io.github.mapvina.android.style.sources.CustomGeometrySource
import io.github.mapvina.android.style.sources.GeoJsonSource
import io.github.mapvina.android.style.sources.ImageSource
import io.github.mapvina.android.style.sources.RasterDemSource
import io.github.mapvina.android.style.sources.RasterSource
import io.github.mapvina.android.style.sources.Source
import io.github.mapvina.android.style.sources.VectorSource
import io.github.mapvina.compose.layers.Layer
import io.github.mapvina.compose.layers.UnknownLayer
import io.github.mapvina.compose.sources.ComputedSource
import io.github.mapvina.compose.sources.UnknownSource
import io.github.mapvina.compose.util.ImageResizeOptions

internal class AndroidStyle(
  style: io.github.mapvina.android.maps.Style,
  private val getDensity: () -> Density,
) : Style {
  private var impl: io.github.mapvina.android.maps.Style = style

  override fun addImage(
    id: String,
    image: ImageBitmap,
    sdf: Boolean,
    resizeOptions: ImageResizeOptions?,
  ) {
    val androidBitmap = image.asAndroidBitmap()
    if (resizeOptions == null) impl.addImage(id, androidBitmap, sdf)
    else {
      with(getDensity()) {
        val left = resizeOptions.left.toPx()
        val top = resizeOptions.top.toPx()
        val right = androidBitmap.width - resizeOptions.right.toPx()
        val bottom = androidBitmap.height - resizeOptions.bottom.toPx()
        impl.addImage(
          id,
          androidBitmap,
          sdf,
          listOf(ImageStretches(left, right)),
          listOf(ImageStretches(top, bottom)),
          ImageContent(left, top, right, bottom),
        )
      }
    }
  }

  override fun removeImage(id: String) {
    impl.removeImage(id)
  }

  private fun Source.toSource() =
    when (this) {
      is VectorSource -> io.github.mapvina.compose.sources.VectorSource(this)
      is GeoJsonSource -> io.github.mapvina.compose.sources.GeoJsonSource(this)
      is RasterSource -> io.github.mapvina.compose.sources.RasterSource(this)
      is ImageSource -> io.github.mapvina.compose.sources.ImageSource(this)
      is RasterDemSource -> io.github.mapvina.compose.sources.RasterDemSource(this)
      is CustomGeometrySource -> ComputedSource(this)
      else -> UnknownSource(this)
    }

  override fun getSource(id: String): io.github.mapvina.compose.sources.Source? {
    return impl.getSource(id)?.toSource()
  }

  override fun getSources(): List<io.github.mapvina.compose.sources.Source> {
    return impl.sources.map { it.toSource() }
  }

  override fun addSource(source: io.github.mapvina.compose.sources.Source) {
    impl.addSource(source.impl)
  }

  override fun removeSource(source: io.github.mapvina.compose.sources.Source) {
    impl.removeSource(source.impl)
  }

  override fun getLayer(id: String): Layer? {
    return impl.getLayer(id)?.let { UnknownLayer(it) }
  }

  override fun getLayers(): List<Layer> {
    return impl.layers.map { UnknownLayer(it) }
  }

  override fun addLayer(layer: Layer) {
    impl.addLayer(layer.impl)
  }

  override fun addLayerAbove(id: String, layer: Layer) {
    impl.addLayerAbove(layer.impl, id)
  }

  override fun addLayerBelow(id: String, layer: Layer) {
    impl.addLayerBelow(layer.impl, id)
  }

  override fun addLayerAt(index: Int, layer: Layer) {
    impl.addLayerAt(layer.impl, index)
  }

  override fun removeLayer(layer: Layer) {
    impl.removeLayer(layer.impl)
  }
}
