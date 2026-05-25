package io.github.mapvina.compose.layers

import io.github.mapvina.compose.expressions.ast.CompiledExpression
import io.github.mapvina.compose.expressions.value.BooleanValue
import io.github.mapvina.compose.expressions.value.ColorValue
import io.github.mapvina.compose.expressions.value.DpValue
import io.github.mapvina.compose.expressions.value.FloatValue
import io.github.mapvina.compose.sources.Source

internal actual class HeatmapLayer actual constructor(id: String, source: Source) :
  FeatureLayer(source) {
  override val impl = TODO()

  actual override var sourceLayer: String = TODO()

  actual override fun setFilter(filter: CompiledExpression<BooleanValue>) {
    TODO()
  }

  actual fun setHeatmapRadius(radius: CompiledExpression<DpValue>) {
    TODO()
  }

  actual fun setHeatmapWeight(weight: CompiledExpression<FloatValue>) {
    TODO()
  }

  actual fun setHeatmapIntensity(intensity: CompiledExpression<FloatValue>) {
    TODO()
  }

  actual fun setHeatmapColor(color: CompiledExpression<ColorValue>) {
    TODO()
  }

  actual fun setHeatmapOpacity(opacity: CompiledExpression<FloatValue>) {
    TODO()
  }
}
