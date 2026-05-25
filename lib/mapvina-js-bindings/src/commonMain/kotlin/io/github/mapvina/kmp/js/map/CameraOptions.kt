@file:JsModule("mapvina-gl")

package io.github.mapvina.kmp.js.map

import io.github.mapvina.kmp.js.geometry.LngLat

/** [CameraOptions](https://mapvina.io/github/mapvina-gl-js/docs/API/type-aliases/CameraOptions/) */
public sealed external interface CameraOptions : CenterZoomBearing {
  public var around: LngLat?
  public var pitch: Double?
}
