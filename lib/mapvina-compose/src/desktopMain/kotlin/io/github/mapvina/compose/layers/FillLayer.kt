package io.github.mapvina.compose.layers

import io.github.mapvina.compose.expressions.ast.CompiledExpression
import io.github.mapvina.compose.expressions.value.BooleanValue
import io.github.mapvina.compose.expressions.value.ColorValue
import io.github.mapvina.compose.expressions.value.DpOffsetValue
import io.github.mapvina.compose.expressions.value.FloatValue
import io.github.mapvina.compose.expressions.value.ImageValue
import io.github.mapvina.compose.expressions.value.TranslateAnchor
import io.github.mapvina.compose.sources.Source

internal actual class FillLayer actual constructor(id: String, source: Source) :
  FeatureLayer(source) {

  override val impl = TODO()

  actual override var sourceLayer: String = TODO()

  actual override fun setFilter(filter: CompiledExpression<BooleanValue>) {
    TODO()
  }

  actual fun setFillSortKey(sortKey: CompiledExpression<FloatValue>) {
    TODO()
  }

  actual fun setFillAntialias(antialias: CompiledExpression<BooleanValue>) {
    TODO()
  }

  actual fun setFillOpacity(opacity: CompiledExpression<FloatValue>) {
    TODO()
  }

  actual fun setFillColor(color: CompiledExpression<ColorValue>) {
    TODO()
  }

  actual fun setFillOutlineColor(outlineColor: CompiledExpression<ColorValue>) {
    TODO()
  }

  actual fun setFillTranslate(translate: CompiledExpression<DpOffsetValue>) {
    TODO()
  }

  actual fun setFillTranslateAnchor(translateAnchor: CompiledExpression<TranslateAnchor>) {
    TODO()
  }

  actual fun setFillPattern(pattern: CompiledExpression<ImageValue>) {
    TODO()
  }
}
