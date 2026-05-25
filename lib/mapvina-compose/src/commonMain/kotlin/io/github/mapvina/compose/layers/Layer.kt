package io.github.mapvina.compose.layers

internal expect sealed class Layer {
  val id: String
  var minZoom: Float
  var maxZoom: Float
  var visible: Boolean
}
