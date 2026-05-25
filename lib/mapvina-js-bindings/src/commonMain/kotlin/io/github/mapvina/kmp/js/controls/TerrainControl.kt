@file:JsModule("mapvina-gl")

package io.github.mapvina.kmp.js.controls

import io.github.mapvina.kmp.js.map.Map
import io.github.mapvina.kmp.js.stylespec.TerrainSpecification
import org.w3c.dom.HTMLElement

/** [TerrainControl](https://mapvina.io/github/mapvina-gl-js/docs/API/classes/TerrainControl/) */
public external class TerrainControl
public constructor(options: TerrainSpecification = definedExternally) : IControl {
  override fun onAdd(map: Map): HTMLElement

  override fun onRemove(map: Map)
}
