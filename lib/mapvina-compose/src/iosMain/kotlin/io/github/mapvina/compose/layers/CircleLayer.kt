package io.github.mapvina.compose.layers

import MapVina.MLNCircleStyleLayer
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
import io.github.mapvina.compose.util.toNSExpression
import io.github.mapvina.compose.util.toNSPredicate

internal actual class CircleLayer actual constructor(id: String, source: Source) :
  FeatureLayer(source) {

  override val impl = MLNCircleStyleLayer(id, source.impl)

  actual override var sourceLayer: String
    get() = impl.sourceLayerIdentifier!!
    set(value) {
      impl.sourceLayerIdentifier = value
    }

  actual override fun setFilter(filter: CompiledExpression<BooleanValue>) {
    impl.predicate = filter.toNSPredicate()
  }

  actual fun setCircleSortKey(sortKey: CompiledExpression<FloatValue>) {
    impl.circleSortKey = sortKey.toNSExpression()
  }

  actual fun setCircleRadius(radius: CompiledExpression<DpValue>) {
    impl.circleRadius = radius.toNSExpression()
  }

  actual fun setCircleColor(color: CompiledExpression<ColorValue>) {
    impl.circleColor = color.toNSExpression()
  }

  actual fun setCircleBlur(blur: CompiledExpression<FloatValue>) {
    impl.circleBlur = blur.toNSExpression()
  }

  actual fun setCircleOpacity(opacity: CompiledExpression<FloatValue>) {
    impl.circleOpacity = opacity.toNSExpression()
  }

  actual fun setCircleTranslate(translate: CompiledExpression<DpOffsetValue>) {
    impl.circleTranslation = translate.toNSExpression()
  }

  actual fun setCircleTranslateAnchor(translateAnchor: CompiledExpression<TranslateAnchor>) {
    impl.circleTranslationAnchor = translateAnchor.toNSExpression()
  }

  actual fun setCirclePitchScale(pitchScale: CompiledExpression<CirclePitchScale>) {
    impl.circleScaleAlignment = pitchScale.toNSExpression()
  }

  actual fun setCirclePitchAlignment(pitchAlignment: CompiledExpression<CirclePitchAlignment>) {
    impl.circlePitchAlignment = pitchAlignment.toNSExpression()
  }

  actual fun setCircleStrokeWidth(strokeWidth: CompiledExpression<DpValue>) {
    impl.circleStrokeWidth = strokeWidth.toNSExpression()
  }

  actual fun setCircleStrokeColor(strokeColor: CompiledExpression<ColorValue>) {
    impl.circleStrokeColor = strokeColor.toNSExpression()
  }

  actual fun setCircleStrokeOpacity(strokeOpacity: CompiledExpression<FloatValue>) {
    impl.circleStrokeOpacity = strokeOpacity.toNSExpression()
  }
}
