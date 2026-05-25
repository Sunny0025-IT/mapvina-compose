package io.github.mapvina.compose.layers

import MapVina.MLNFillStyleLayer
import io.github.mapvina.compose.expressions.ast.CompiledExpression
import io.github.mapvina.compose.expressions.ast.NullLiteral
import io.github.mapvina.compose.expressions.value.BooleanValue
import io.github.mapvina.compose.expressions.value.ColorValue
import io.github.mapvina.compose.expressions.value.DpOffsetValue
import io.github.mapvina.compose.expressions.value.FloatValue
import io.github.mapvina.compose.expressions.value.ImageValue
import io.github.mapvina.compose.expressions.value.TranslateAnchor
import io.github.mapvina.compose.sources.Source
import io.github.mapvina.compose.util.toNSExpression
import io.github.mapvina.compose.util.toNSPredicate

internal actual class FillLayer actual constructor(id: String, source: Source) :
  FeatureLayer(source) {

  override val impl = MLNFillStyleLayer(id, source.impl)

  actual override var sourceLayer: String
    get() = impl.sourceLayerIdentifier!!
    set(value) {
      impl.sourceLayerIdentifier = value
    }

  actual override fun setFilter(filter: CompiledExpression<BooleanValue>) {
    impl.predicate = filter.toNSPredicate()
  }

  actual fun setFillSortKey(sortKey: CompiledExpression<FloatValue>) {
    impl.fillSortKey = sortKey.toNSExpression()
  }

  actual fun setFillAntialias(antialias: CompiledExpression<BooleanValue>) {
    impl.fillAntialiased = antialias.toNSExpression()
  }

  actual fun setFillOpacity(opacity: CompiledExpression<FloatValue>) {
    impl.fillOpacity = opacity.toNSExpression()
  }

  actual fun setFillColor(color: CompiledExpression<ColorValue>) {
    impl.fillColor = color.toNSExpression()
  }

  actual fun setFillOutlineColor(outlineColor: CompiledExpression<ColorValue>) {
    impl.fillOutlineColor = outlineColor.toNSExpression()
  }

  actual fun setFillTranslate(translate: CompiledExpression<DpOffsetValue>) {
    impl.fillTranslation = translate.toNSExpression()
  }

  actual fun setFillTranslateAnchor(translateAnchor: CompiledExpression<TranslateAnchor>) {
    impl.fillTranslationAnchor = translateAnchor.toNSExpression()
  }

  actual fun setFillPattern(pattern: CompiledExpression<ImageValue>) {
    // TODO: figure out how to unset a pattern in iOS
    if (pattern != NullLiteral) {
      impl.fillPattern = pattern.toNSExpression()
    }
  }
}
