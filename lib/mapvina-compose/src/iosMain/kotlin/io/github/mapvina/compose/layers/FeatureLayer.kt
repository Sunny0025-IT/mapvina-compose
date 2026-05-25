package io.github.mapvina.compose.layers

import io.github.mapvina.compose.expressions.ast.CompiledExpression
import io.github.mapvina.compose.expressions.value.BooleanValue
import io.github.mapvina.compose.sources.Source

internal actual sealed class FeatureLayer actual constructor(actual val source: Source) : Layer() {
  actual abstract var sourceLayer: String

  actual abstract fun setFilter(filter: CompiledExpression<BooleanValue>)
}
