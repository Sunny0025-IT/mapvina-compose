@file:JsModule("mapvina-gl")

package io.github.mapvina.kmp.js.controls

import io.github.mapvina.kmp.js.map.Map
import org.w3c.dom.HTMLElement

/**
 * [AttributionControl](https://mapvina.io/github/mapvina-gl-js/docs/API/classes/AttributionControl/)
 */
public external class AttributionControl
public constructor(options: AttributionControlOptions = definedExternally) : IControl {
  override fun onAdd(map: Map): HTMLElement

  override fun onRemove(map: Map)
}
