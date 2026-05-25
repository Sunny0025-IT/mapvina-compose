package io.github.mapvina.compose.expressions.value

import io.github.mapvina.compose.expressions.ast.Expression
import io.github.mapvina.compose.expressions.dsl.eq
import io.github.mapvina.compose.expressions.dsl.format
import io.github.mapvina.compose.expressions.dsl.gt
import io.github.mapvina.compose.expressions.dsl.gte
import io.github.mapvina.compose.expressions.dsl.interpolate
import io.github.mapvina.compose.expressions.dsl.lt
import io.github.mapvina.compose.expressions.dsl.lte
import io.github.mapvina.compose.expressions.dsl.neq
import io.github.mapvina.compose.expressions.dsl.switch

/** Represents and [Expression] that resolves to a value that can be an input to [format]. */
public sealed interface FormattableValue : ExpressionValue

/**
 * Represents an [Expression] that resolves to a value that can be compared for equality. See [eq]
 * and [neq].
 */
public sealed interface EquatableValue : ExpressionValue

/** Union type for an [Expression] that resolves to a value that can be matched. See [switch]. */
public sealed interface MatchableValue : ExpressionValue

/**
 * Union type for an [Expression] that resolves to a value that can be ordered with other values of
 * its type. See [gt], [lt], [gte], and [lte].
 *
 * @param T the type of the value that can be compared against for ordering.
 */
public sealed interface ComparableValue<T> : ExpressionValue

/**
 * Union type for an [Expression] that resolves to a value that can be interpolated. See
 * [interpolate].
 *
 * @param T the type of values that can be interpolated between.
 */
public sealed interface InterpolatableValue<T> : ExpressionValue
