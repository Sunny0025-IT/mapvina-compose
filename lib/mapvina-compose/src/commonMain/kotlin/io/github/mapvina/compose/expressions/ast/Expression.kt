package io.github.mapvina.compose.expressions.ast

import io.github.mapvina.compose.expressions.value.ExpressionValue

/**
 * An [Expression] that evaluates to a value of type [T].
 *
 * The functions to create expressions are defined in the
 * [`io.github.mapvina.compose.expressions.dsl`](https://mapvina.io/github/mapvina-compose/api/lib/mapvina-compose/io.github.mapvina.compose.expressions.dsl/index.html)
 * package.
 *
 * Most functions are named the same as in the
 * [MapVina style specification](https://mapvina.io/github/mapvina-style-spec/expressions/), a few have
 * been renamed to be Kotlin-idiomatic or made into extension functions (to an [Expression]).
 *
 * # Function overview
 *
 * ### Literals
 * - [const][io.github.mapvina.compose.expressions.dsl.const] - literal expression
 * - [nil][io.github.mapvina.compose.expressions.dsl.nil] - literal `null` expression
 *
 * ### Decision
 * - [switch][io.github.mapvina.compose.expressions.dsl.switch] - if-else / switch-case
 * - [coalesce][io.github.mapvina.compose.expressions.dsl.coalesce] - get first non-null value
 * - [eq][io.github.mapvina.compose.expressions.dsl.eq], [neq][io.github.mapvina.compose.expressions.dsl.neq],
 *   [gt][io.github.mapvina.compose.expressions.dsl.gt], [gte][io.github.mapvina.compose.expressions.dsl.gte],
 *   [lt][io.github.mapvina.compose.expressions.dsl.lt],
 *   [lte][io.github.mapvina.compose.expressions.dsl.lte] - infix comparison (`=`,`≠`,`>`,`≥`,`<`, `≤`)
 * - [not][io.github.mapvina.compose.expressions.dsl.not], [all][io.github.mapvina.compose.expressions.dsl.all],
 *   [any][io.github.mapvina.compose.expressions.dsl.any] - boolean operators, also available as infix
 *   [and][io.github.mapvina.compose.expressions.dsl.and], [or][io.github.mapvina.compose.expressions.dsl.or]
 *
 * ### Ramps, scales, curves
 * - [step][io.github.mapvina.compose.expressions.dsl.step] - produce stepped results
 * - [interpolate][io.github.mapvina.compose.expressions.dsl.interpolate] - produce interpolation
 * - [interpolateHcl][io.github.mapvina.compose.expressions.dsl.interpolateHcl] - produce interpolation
 *   in HCL color space
 * - [interpolateLab][io.github.mapvina.compose.expressions.dsl.interpolateLab] - produce interpolation
 *   in CIELAB color space
 *
 * ### Math
 * - [+][io.github.mapvina.compose.expressions.dsl.plus], [-][io.github.mapvina.compose.expressions.dsl.minus],
 *   [*][io.github.mapvina.compose.expressions.dsl.times], [/][io.github.mapvina.compose.expressions.dsl.div],
 *   [%][io.github.mapvina.compose.expressions.dsl.rem], [pow][io.github.mapvina.compose.expressions.dsl.pow],
 *   [sqrt][io.github.mapvina.compose.expressions.dsl.sqrt] - algebraic operations
 * - [log10][io.github.mapvina.compose.expressions.dsl.log10], [log2][io.github.mapvina.compose.expressions.dsl.log2],
 *   [ln][io.github.mapvina.compose.expressions.dsl.ln] - logarithmic functions
 * - [sin][io.github.mapvina.compose.expressions.dsl.sin], [cos][io.github.mapvina.compose.expressions.dsl.cos],
 *   [tan][io.github.mapvina.compose.expressions.dsl.tan],
 *   [asin][io.github.mapvina.compose.expressions.dsl.asin],
 *   [acos][io.github.mapvina.compose.expressions.dsl.acos],
 *   [atan][io.github.mapvina.compose.expressions.dsl.atan] - trigonometric functions
 * - [floor][io.github.mapvina.compose.expressions.dsl.floor], [ceil][io.github.mapvina.compose.expressions.dsl.ceil],
 *   [round][io.github.mapvina.compose.expressions.dsl.round],
 *   [abs][io.github.mapvina.compose.expressions.dsl.round] - coercing numbers
 * - [min][io.github.mapvina.compose.expressions.dsl.min], [max][io.github.mapvina.compose.expressions.dsl.max],
 *   [round][io.github.mapvina.compose.expressions.dsl.round] - rounding integers
 * - [LN_2][io.github.mapvina.compose.expressions.dsl.LN_2], [PI][io.github.mapvina.compose.expressions.dsl.PI],
 *   [E][io.github.mapvina.compose.expressions.dsl.E] - constants
 *
 * ### Inputs, feature data
 * - [zoom][io.github.mapvina.compose.expressions.dsl.zoom] - get current zoom level
 * - [heatmapDensity][io.github.mapvina.compose.expressions.dsl.heatmapDensity] - get heatmap density
 * - `feature.`[get][io.github.mapvina.compose.expressions.dsl.Feature.get] - get feature attribute
 * - `feature.`[has][io.github.mapvina.compose.expressions.dsl.Feature.has] - check presence of feature
 *   attribute
 * - `feature.`[properties][io.github.mapvina.compose.expressions.dsl.Feature.properties] - get all
 *   feature attributes
 * - `feature.`[state][io.github.mapvina.compose.expressions.dsl.Feature.state] - get property from
 *   feature state
 * - `feature.`[geometryType][io.github.mapvina.compose.expressions.dsl.Feature.geometryType] - get
 *   feature's geometry type
 * - `feature.`[id][io.github.mapvina.compose.expressions.dsl.Feature.id] - get feature id
 * - `feature.`[lineProgress][io.github.mapvina.compose.expressions.dsl.Feature.lineProgress] - progress
 *   along a gradient line
 * - `feature.`[accumulated][io.github.mapvina.compose.expressions.dsl.Feature.accumulated] - value of
 *   accumulated cluster property so far
 * - `feature.`[within][io.github.mapvina.compose.expressions.dsl.Feature.within] - check whether feature
 *   is within geometry
 * - `feature.`[distance][io.github.mapvina.compose.expressions.dsl.Feature.distance] - distance of
 *   feature to geometry
 *
 * ### Collections
 * - `Expression<ListValue<T>>.`[get][io.github.mapvina.compose.expressions.dsl.get] - get value at index
 * - `Expression<ListValue<T>>.`[contains][io.github.mapvina.compose.expressions.dsl.contains] - check
 *   whether list contains value
 * - `Expression<ListValue<T>>.`[indexOf][io.github.mapvina.compose.expressions.dsl.indexOf] - check
 *   where the list contains value
 * - `Expression<ListValue<T>>.`[slice][io.github.mapvina.compose.expressions.dsl.slice] - return a
 *   sub-list
 * - `Expression<ListValue<T>>.`[length][io.github.mapvina.compose.expressions.dsl.length] - list length
 * - `Expression<MapValue<T>>.`[get][io.github.mapvina.compose.expressions.dsl.get] - get value
 * - `Expression<MapValue<T>>.`[has][io.github.mapvina.compose.expressions.dsl.has] - check presence of
 *   key
 *
 * ### Strings
 * - `Expression<StringValue>.`[contains][io.github.mapvina.compose.expressions.dsl.contains] - check if
 *   string contains another
 * - `Expression<StringValue>.`[indexOf][io.github.mapvina.compose.expressions.dsl.indexOf] - check where
 *   string contains another
 * - `Expression<StringValue>.`[substring][io.github.mapvina.compose.expressions.dsl.substring] - return
 *   a sub-string
 * - `Expression<StringValue>.`[length][io.github.mapvina.compose.expressions.dsl.length] - string length
 * - `Expression<StringValue>.` [isScriptSupported][io.github.mapvina.compose.expressions.dsl.isScriptSupported] -
 *   whether string is expected to render correctly
 * - `Expression<StringValue>.`[uppercase][io.github.mapvina.compose.expressions.dsl.uppercase] -
 *   uppercase the string
 * - `Expression<StringValue>.`[lowercase][io.github.mapvina.compose.expressions.dsl.lowercase] -
 *   lowercase the string
 * - `Expression<StringValue>.`[+][io.github.mapvina.compose.expressions.dsl.plus] - concatenate the
 *   string
 * - [resolvedLocale][io.github.mapvina.compose.expressions.dsl.resolvedLocale] - return locale
 *
 * ### Format
 * - [format][io.github.mapvina.compose.expressions.dsl.format] - format text with
 *   [span][io.github.mapvina.compose.expressions.dsl.span]s of different text styling
 *
 * ### Color
 * - [rgbColor][io.github.mapvina.compose.expressions.dsl.rgbColor] - create color from components
 * - `Expression<ColorValue>.` [toRgbaComponents][io.github.mapvina.compose.expressions.dsl.toRgbaComponents] -
 *   deconstruct color into components
 *
 * ### Image
 * - [image][io.github.mapvina.compose.expressions.dsl.image] - image for use in `iconImage`
 *
 * ### Variable binding
 * - [withVariable][io.github.mapvina.compose.expressions.dsl.withVariable] - define variable within
 *   expression
 */
public sealed interface Expression<out T : ExpressionValue> {
  /** Transform this expression into the equivalent [CompiledExpression]. */
  public fun compile(context: ExpressionContext): CompiledExpression<T>

  public fun visit(block: (Expression<*>) -> Unit)

  @Suppress("UNCHECKED_CAST")
  public fun <X : ExpressionValue> cast(): Expression<X> = this as Expression<X>
}
