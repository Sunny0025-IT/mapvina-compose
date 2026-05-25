@file:JsModule("mapvina-gl")

package io.github.mapvina.kmp.js.controls

import io.github.mapvina.kmp.js.map.Map
import org.w3c.dom.HTMLElement

/** [LogoControl](https://mapvina.io/github/mapvina-gl-js/docs/API/classes/LogoControl/) */
public external class LogoControl
public constructor(options: LogoControlOptions = definedExternally) : IControl {
  override fun onAdd(map: Map): HTMLElement

  override fun onRemove(map: Map)
}
