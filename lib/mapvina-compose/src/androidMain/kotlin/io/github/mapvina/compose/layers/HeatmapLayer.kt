package io.github.mapvina.compose.layers

import io.github.mapvina.android.style.expressions.Expression as MLNExpression
import io.github.mapvina.android.style.layers.HeatmapLayer as MLNHeatmapLayer
import io.github.mapvina.android.style.layers.PropertyFactory
import io.github.mapvina.compose.expressions.ast.CompiledExpression
import io.github.mapvina.compose.expressions.value.BooleanValue
import io.github.mapvina.compose.expressions.value.ColorValue
import io.github.mapvina.compose.expressions.value.DpValue
import io.github.mapvina.compose.expressions.value.FloatValue
import io.github.mapvina.compose.sources.Source
import io.github.mapvina.compose.util.toMLNExpression

internal actual class HeatmapLayer actual constructor(id: String, source: Source) :
  FeatureLayer(source) {
  override val impl = MLNHeatmapLayer(id, source.id)

  actual override var sourceLayer: String by impl::sourceLayer

  actual override fun setFilter(filter: CompiledExpression<BooleanValue>) {
    impl.setFilter(filter.toMLNExpression() ?: MLNExpression.literal(true))
  }

  actual fun setHeatmapRadius(radius: CompiledExpression<DpValue>) {
    impl.setProperties(PropertyFactory.heatmapRadius(radius.toMLNExpression()))
  }

  actual fun setHeatmapWeight(weight: CompiledExpression<FloatValue>) {
    impl.setProperties(PropertyFactory.heatmapWeight(weight.toMLNExpression()))
  }

  actual fun setHeatmapIntensity(intensity: CompiledExpression<FloatValue>) {
    impl.setProperties(PropertyFactory.heatmapIntensity(intensity.toMLNExpression()))
  }

  actual fun setHeatmapColor(color: CompiledExpression<ColorValue>) {
    impl.setProperties(PropertyFactory.heatmapColor(color.toMLNExpression()))
  }

  actual fun setHeatmapOpacity(opacity: CompiledExpression<FloatValue>) {
    impl.setProperties(PropertyFactory.heatmapOpacity(opacity.toMLNExpression()))
  }
}
