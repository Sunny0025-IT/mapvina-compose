@file:JsModule("mapvina-gl")

package io.github.mapvina.kmp.js.map

import org.w3c.dom.HTMLElement

/** [MapOptions](https://mapvina.io/github/mapvina-gl-js/docs/API/type-aliases/MapOptions/) */
public sealed external interface MapOptions {
  public var container: HTMLElement
  public var attributionControl: dynamic // false | AttributionControlOptions
}
