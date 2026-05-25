package io.github.mapvina.kmp.js.map

import io.github.mapvina.kmp.js.geometry.Point

/**
 * [CameraForBoundsOptions](https://mapvina.io/github/mapvina-gl-js/docs/API/type-aliases/CameraForBoundsOptions/)
 */
public sealed external interface CameraForBoundsOptions : CameraOptions {
  public var maxZoom: Double?
  public var offset: Point?
  public var padding: PaddingOptions?
}
