package io.github.mapvina.compose.expressions.dsl

import io.github.mapvina.compose.expressions.ast.Expression
import io.github.mapvina.compose.expressions.ast.FunctionCall
import io.github.mapvina.compose.expressions.value.FloatValue

/**
 * Gets the current zoom level. Note that in layer style properties, [zoom] may only appear as the
 * input to a top-level [step] or [interpolate] (, [interpolateHcl], [interpolateLab], ...)
 * expression.
 */
public fun zoom(): Expression<FloatValue> = FunctionCall.of("zoom").cast()

/**
 * Gets the kernel density estimation of a pixel in a heatmap layer, which is a relative measure of
 * how many data points are crowded around a particular pixel. Can only be used in the expression
 * for the `color` parameter in a HeatmapLayer
 * [HeatmapLayer][io.github.mapvina.compose.layers.HeatmapLayer].
 */
public fun heatmapDensity(): Expression<FloatValue> = FunctionCall.of("heatmap-density").cast()
