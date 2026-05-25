@file:JsModule("mapvina-gl")

package io.github.mapvina.kmp.js.map

import io.github.mapvina.kmp.js.stylespec.Expression

/**
 * [QueryRenderedFeaturesOptions](https://mapvina.io/github/mapvina-gl-js/docs/API/type-aliases/QueryRenderedFeaturesOptions/)
 */
public sealed external interface QueryRenderedFeaturesOptions {
  public var availableImages: Array<String>?
  public var layers: Array<String>?
  public var filter: Expression?
  public var validate: Boolean?
}
