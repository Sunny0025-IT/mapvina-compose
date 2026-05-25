package io.github.mapvina.compose.layers

import androidx.compose.ui.graphics.Color
import io.github.mapvina.compose.expressions.ast.Expression
import io.github.mapvina.compose.expressions.dsl.const
import io.github.mapvina.compose.expressions.dsl.heatmapDensity
import io.github.mapvina.compose.expressions.dsl.interpolate
import io.github.mapvina.compose.expressions.dsl.linear
import io.github.mapvina.compose.expressions.value.ColorValue
import io.github.mapvina.compose.expressions.value.ListValue
import io.github.mapvina.compose.expressions.value.StringValue

public object LayerDefaults {
  public val HeatmapColors: Expression<ColorValue> =
    interpolate(
      linear(),
      heatmapDensity(),
      0 to const(Color.Companion.Transparent),
      0.1 to const(Color(0xFF4169E1)), // royal blue
      0.3 to const(Color(0xFF00FFFF)), // cyan
      0.5 to const(Color(0xFF00FF00)), // lime
      0.7 to const(Color(0xFFFFFF00)), // yellow
      1 to const(Color(0xFFFF0000)), // red
    )

  public val FontNames: Expression<ListValue<StringValue>> =
    const(listOf("Open Sans Regular", "Arial Unicode MS Regular"))
}
