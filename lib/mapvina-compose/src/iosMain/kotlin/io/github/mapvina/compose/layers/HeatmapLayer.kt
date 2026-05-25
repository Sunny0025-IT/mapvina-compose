package io.github.mapvina.compose.layers

import MapVina.MLNHeatmapStyleLayer
import io.github.mapvina.compose.expressions.ast.CompiledExpression
import io.github.mapvina.compose.expressions.value.BooleanValue
import io.github.mapvina.compose.expressions.value.ColorValue
import io.github.mapvina.compose.expressions.value.DpValue
import io.github.mapvina.compose.expressions.value.FloatValue
import io.github.mapvina.compose.sources.Source
import io.github.mapvina.compose.util.toNSExpression
import io.github.mapvina.compose.util.toNSPredicate

internal actual class HeatmapLayer actual constructor(id: String, source: Source) :
  FeatureLayer(source) {

  override val impl = MLNHeatmapStyleLayer(id, source.impl)

  actual override var sourceLayer: String
    get() = impl.sourceLayerIdentifier!!
    set(value) {
      impl.sourceLayerIdentifier = value
    }

  actual override fun setFilter(filter: CompiledExpression<BooleanValue>) {
    impl.predicate = filter.toNSPredicate()
  }

  actual fun setHeatmapRadius(radius: CompiledExpression<DpValue>) {
    impl.heatmapRadius = radius.toNSExpression()
  }

  actual fun setHeatmapWeight(weight: CompiledExpression<FloatValue>) {
    impl.heatmapWeight = weight.toNSExpression()
  }

  actual fun setHeatmapIntensity(intensity: CompiledExpression<FloatValue>) {
    impl.heatmapIntensity = intensity.toNSExpression()
  }

  actual fun setHeatmapColor(color: CompiledExpression<ColorValue>) {
    impl.heatmapColor = color.toNSExpression()
  }

  actual fun setHeatmapOpacity(opacity: CompiledExpression<FloatValue>) {
    impl.heatmapOpacity = opacity.toNSExpression()
  }
}
