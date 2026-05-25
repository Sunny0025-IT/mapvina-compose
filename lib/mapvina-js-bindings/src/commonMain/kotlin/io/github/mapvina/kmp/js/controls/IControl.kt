@file:JsModule("mapvina-gl")

package io.github.mapvina.kmp.js.controls

import io.github.mapvina.kmp.js.map.Map
import org.w3c.dom.HTMLElement

/** [IControl](https://mapvina.io/github/mapvina-gl-js/docs/API/interfaces/IControl/) */
public external interface IControl {
  public fun onAdd(map: Map): HTMLElement

  public fun onRemove(map: Map)
}
