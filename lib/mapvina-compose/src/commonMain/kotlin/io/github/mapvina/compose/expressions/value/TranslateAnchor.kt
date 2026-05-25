package io.github.mapvina.compose.expressions.value

import io.github.mapvina.compose.expressions.ast.StringLiteral

/** Frame of reference for offsetting geometry. */
public enum class TranslateAnchor(override val literal: StringLiteral) :
  EnumValue<TranslateAnchor> {
  /** Offset is relative to the map */
  Map(StringLiteral.of("map")),

  /** Offset is relative to the viewport */
  Viewport(StringLiteral.of("viewport")),
}
