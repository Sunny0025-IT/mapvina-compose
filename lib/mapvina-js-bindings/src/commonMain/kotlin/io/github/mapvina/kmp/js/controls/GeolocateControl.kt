@file:JsModule("mapvina-gl")

package io.github.mapvina.kmp.js.controls

import io.github.mapvina.kmp.js.map.Map
import org.w3c.dom.HTMLElement

/** [GeolocateControl](https://mapvina.io/github/mapvina-gl-js/docs/API/classes/GeolocateControl/) */
public external class GeolocateControl
public constructor(options: GeolocateControlOptions = definedExternally) : IControl {
  override fun onAdd(map: Map): HTMLElement

  override fun onRemove(map: Map)
}
