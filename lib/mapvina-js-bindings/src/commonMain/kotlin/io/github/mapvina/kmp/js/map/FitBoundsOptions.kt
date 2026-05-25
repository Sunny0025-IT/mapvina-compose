@file:JsModule("mapvina-gl")

package io.github.mapvina.kmp.js.map

import io.github.mapvina.kmp.js.geometry.Point

/**
 * [FitBoundsOptions](https://mapvina.io/github/mapvina-gl-js/docs/API/type-aliases/FitBoundsOptions/)
 */
public sealed external interface FitBoundsOptions : FlyToOptions {
  public var linear: Boolean?
  public var maxZoom: Double?
  public var offset: Point?
}
