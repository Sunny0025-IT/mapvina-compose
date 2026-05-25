@file:JsModule("mapvina-gl")

package io.github.mapvina.kmp.js.event

import io.github.mapvina.kmp.js.map.Map

/** [MapVinaEvent](https://mapvina.io/github/mapvina-gl-js/docs/API/type-aliases/MapVinaEvent/) */
public external interface MapVinaEvent<T> : Event {
  public val originalEvent: T
  public val target: Map
  public val type: String
}
