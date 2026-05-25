@file:JsModule("mapvina-gl")

package io.github.mapvina.kmp.js.controls

import io.github.mapvina.kmp.js.map.Map
import org.w3c.dom.HTMLElement

/** [ScaleControl](https://mapvina.io/github/mapvina-gl-js/docs/API/classes/ScaleControl/) */
public external class ScaleControl
public constructor(options: ScaleControlOptions = definedExternally) : IControl {
  override fun onAdd(map: Map): HTMLElement

  override fun onRemove(map: Map)
}
