@file:JsModule("mapvina-gl")

package io.github.mapvina.kmp.js.controls

import io.github.mapvina.kmp.js.map.Map
import org.w3c.dom.HTMLElement

/** [NavigationControl](https://mapvina.io/github/mapvina-gl-js/docs/API/classes/NavigationControl/) */
public external class NavigationControl
public constructor(options: NavigationControlOptions = definedExternally) : IControl {
  override fun onAdd(map: Map): HTMLElement

  override fun onRemove(map: Map)
}
