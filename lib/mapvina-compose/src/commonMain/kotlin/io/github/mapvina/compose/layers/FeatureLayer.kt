package io.github.mapvina.compose.layers

import io.github.mapvina.compose.expressions.ast.CompiledExpression
import io.github.mapvina.compose.expressions.value.BooleanValue
import io.github.mapvina.compose.sources.Source

internal expect sealed class FeatureLayer(source: Source) : Layer {
  val source: Source
  abstract var sourceLayer: String

  abstract fun setFilter(filter: CompiledExpression<BooleanValue>)
}
