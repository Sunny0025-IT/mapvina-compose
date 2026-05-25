package io.github.mapvina.compose.layers

import io.github.mapvina.compose.expressions.ast.CompiledExpression
import io.github.mapvina.compose.expressions.value.BooleanValue
import io.github.mapvina.compose.expressions.value.CirclePitchAlignment
import io.github.mapvina.compose.expressions.value.CirclePitchScale
import io.github.mapvina.compose.expressions.value.ColorValue
import io.github.mapvina.compose.expressions.value.DpOffsetValue
import io.github.mapvina.compose.expressions.value.DpValue
import io.github.mapvina.compose.expressions.value.FloatValue
import io.github.mapvina.compose.expressions.value.TranslateAnchor
import io.github.mapvina.compose.sources.Source

internal actual class CircleLayer actual constructor(id: String, source: Source) :
  FeatureLayer(source) {
  override val impl = TODO()

  actual override var sourceLayer: String = TODO()

  actual override fun setFilter(filter: CompiledExpression<BooleanValue>) {
    TODO()
  }

  actual fun setCircleSortKey(sortKey: CompiledExpression<FloatValue>) {
    TODO()
  }

  actual fun setCircleRadius(radius: CompiledExpression<DpValue>) {
    TODO()
  }

  actual fun setCircleColor(color: CompiledExpression<ColorValue>) {
    TODO()
  }

  actual fun setCircleBlur(blur: CompiledExpression<FloatValue>) {
    TODO()
  }

  actual fun setCircleOpacity(opacity: CompiledExpression<FloatValue>) {
    TODO()
  }

  actual fun setCircleTranslate(translate: CompiledExpression<DpOffsetValue>) {
    TODO()
  }

  actual fun setCircleTranslateAnchor(translateAnchor: CompiledExpression<TranslateAnchor>) {
    TODO()
  }

  actual fun setCirclePitchScale(pitchScale: CompiledExpression<CirclePitchScale>) {
    TODO()
  }

  actual fun setCirclePitchAlignment(pitchAlignment: CompiledExpression<CirclePitchAlignment>) {
    TODO()
  }

  actual fun setCircleStrokeWidth(strokeWidth: CompiledExpression<DpValue>) {
    TODO()
  }

  actual fun setCircleStrokeColor(strokeColor: CompiledExpression<ColorValue>) {
    TODO()
  }

  actual fun setCircleStrokeOpacity(strokeOpacity: CompiledExpression<FloatValue>) {
    TODO()
  }
}
