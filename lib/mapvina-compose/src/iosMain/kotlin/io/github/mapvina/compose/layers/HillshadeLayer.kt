package io.github.mapvina.compose.layers

import MapVina.MLNHillshadeStyleLayer
import io.github.mapvina.compose.expressions.ast.CompiledExpression
import io.github.mapvina.compose.expressions.value.ColorValue
import io.github.mapvina.compose.expressions.value.FloatValue
import io.github.mapvina.compose.expressions.value.IlluminationAnchor
import io.github.mapvina.compose.sources.Source
import io.github.mapvina.compose.util.toNSExpression

internal actual class HillshadeLayer actual constructor(id: String, actual val source: Source) :
  Layer() {

  override val impl = MLNHillshadeStyleLayer(id, source.impl)

  actual fun setHillshadeIlluminationDirection(direction: CompiledExpression<FloatValue>) {
    impl.hillshadeIlluminationDirection = direction.toNSExpression()
  }

  actual fun setHillshadeIlluminationAnchor(anchor: CompiledExpression<IlluminationAnchor>) {
    impl.hillshadeIlluminationAnchor = anchor.toNSExpression()
  }

  actual fun setHillshadeExaggeration(exaggeration: CompiledExpression<FloatValue>) {
    impl.hillshadeExaggeration = exaggeration.toNSExpression()
  }

  actual fun setHillshadeShadowColor(shadowColor: CompiledExpression<ColorValue>) {
    impl.hillshadeShadowColor = shadowColor.toNSExpression()
  }

  actual fun setHillshadeHighlightColor(highlightColor: CompiledExpression<ColorValue>) {
    impl.hillshadeHighlightColor = highlightColor.toNSExpression()
  }

  actual fun setHillshadeAccentColor(accentColor: CompiledExpression<ColorValue>) {
    impl.hillshadeAccentColor = accentColor.toNSExpression()
  }
}
