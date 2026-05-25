package io.github.mapvina.compose.layers

import io.github.mapvina.android.style.layers.BackgroundLayer
import io.github.mapvina.android.style.layers.PropertyFactory
import io.github.mapvina.compose.expressions.ast.CompiledExpression
import io.github.mapvina.compose.expressions.value.ColorValue
import io.github.mapvina.compose.expressions.value.FloatValue
import io.github.mapvina.compose.expressions.value.ImageValue
import io.github.mapvina.compose.util.toMLNExpression

internal actual class BackgroundLayer actual constructor(id: String) : Layer() {

  override val impl: BackgroundLayer = BackgroundLayer(id)

  actual fun setBackgroundColor(color: CompiledExpression<ColorValue>) {
    impl.setProperties(PropertyFactory.backgroundColor(color.toMLNExpression()))
  }

  actual fun setBackgroundPattern(pattern: CompiledExpression<ImageValue>) {
    impl.setProperties(PropertyFactory.backgroundPattern(pattern.toMLNExpression()))
  }

  actual fun setBackgroundOpacity(opacity: CompiledExpression<FloatValue>) {
    impl.setProperties(PropertyFactory.backgroundOpacity(opacity.toMLNExpression()))
  }
}
