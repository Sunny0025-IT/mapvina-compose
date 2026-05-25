package io.github.mapvina.compose.expressions.dsl

import io.github.mapvina.compose.expressions.ast.Expression
import io.github.mapvina.compose.expressions.ast.FunctionCall
import io.github.mapvina.compose.expressions.value.ColorValue
import io.github.mapvina.compose.expressions.value.FloatValue
import io.github.mapvina.compose.expressions.value.IntValue
import io.github.mapvina.compose.expressions.value.VectorValue

/**
 * Returns a four-element list, containing the color's red, green, blue, and alpha components, in
 * that order.
 */
public fun Expression<ColorValue>.toRgbaComponents(): Expression<VectorValue<Number>> =
  FunctionCall.of("to-rgba", this).cast()

/**
 * Creates a color value from [red], [green], and [blue] components, which must range between 0 and
 * 255, and optionally an [alpha] component which must range between 0 and 1.
 *
 * If any component is out of range, the expression is an error.
 */
public fun rgbColor(
  red: Expression<IntValue>,
  green: Expression<IntValue>,
  blue: Expression<IntValue>,
  alpha: Expression<FloatValue>? = null,
): Expression<ColorValue> =
  if (alpha != null) {
      FunctionCall.of("rgba", red, green, blue, alpha)
    } else {
      FunctionCall.of("rgb", red, green, blue)
    }
    .cast()
