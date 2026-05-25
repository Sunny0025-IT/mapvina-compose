@file:JsModule("mapvina-gl")

package io.github.mapvina.kmp.js.map

/** [JumpToOptions](https://mapvina.io/github/mapvina-gl-js/docs/API/type-aliases/JumpToOptions/) */
public sealed external interface JumpToOptions : CameraOptions {
  public var padding: PaddingOptions?
}
