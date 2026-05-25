@file:JsModule("mapvina-gl")

package io.github.mapvina.kmp.js.map

/** [FlyToOptions](https://mapvina.io/github/mapvina-gl-js/docs/API/type-aliases/FlyToOptions/) */
public sealed external interface FlyToOptions : CameraOptions {
  public var curve: Double?
  public var maxDuration: Double?
  public var minZoom: Double?
  public var padding: PaddingOptions?
  public var speed: Double?
  public var screenSpeed: Double?
}
