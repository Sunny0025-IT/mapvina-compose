@file:JsModule("mapvina-gl")

package io.github.mapvina.kmp.js.map

public sealed external interface EaseToOptions : CameraOptions {
  public var padding: PaddingOptions?
  public var duration: Double?
  public var easing: (t: Double) -> Double?
}
