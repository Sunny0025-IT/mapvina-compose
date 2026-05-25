@file:JsModule("mapvina-gl")

package io.github.mapvina.kmp.js.controls

import io.github.mapvina.kmp.js.map.Map
import org.w3c.dom.HTMLElement

/** [FullscreenControl](https://mapvina.io/github/mapvina-gl-js/docs/API/classes/FullscreenControl/) */
public external class FullscreenControl
public constructor(options: FullscreenControlOptions = definedExternally) : IControl {
  override fun onAdd(map: Map): HTMLElement

  override fun onRemove(map: Map)
}
