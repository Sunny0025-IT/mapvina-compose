@file:JsModule("mapvina-gl")

package io.github.mapvina.kmp.js.map

import io.github.mapvina.kmp.js.geometry.LngLat

/**
 * [CenterZoomBearing](https://mapvina.io/github/mapvina-gl-js/docs/API/type-aliases/CenterZoomBearing/)
 */
public sealed external interface CenterZoomBearing {
  public var bearing: Double?
  public var center: LngLat?
  public var zoom: Double?
}
