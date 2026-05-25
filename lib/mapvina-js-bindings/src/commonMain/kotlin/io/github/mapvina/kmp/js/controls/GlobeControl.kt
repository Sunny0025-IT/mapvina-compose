@file:JsModule("mapvina-gl")

package io.github.mapvina.kmp.js.controls

import io.github.mapvina.kmp.js.map.Map
import org.w3c.dom.HTMLElement

/** [GlobeControl](https://mapvina.io/github/mapvina-gl-js/docs/API/classes/GlobeControl/) */
public external class GlobeControl public constructor() : IControl {
  override fun onAdd(map: Map): HTMLElement

  override fun onRemove(map: Map)
}
