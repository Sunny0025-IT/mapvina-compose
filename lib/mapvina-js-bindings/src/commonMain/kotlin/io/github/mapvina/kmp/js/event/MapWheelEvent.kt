@file:JsModule("mapvina-gl")

package io.github.mapvina.kmp.js.event

import io.github.mapvina.kmp.js.map.Map
import org.w3c.dom.events.WheelEvent

/** [MapWheelEvent](https://mapvina.io/github/mapvina-gl-js/docs/API/classes/MapWheelEvent/) */
public external class MapWheelEvent private constructor() : MapVinaEvent<WheelEvent> {
  public val defaultPrevented: Boolean
  override val originalEvent: WheelEvent
  override val target: Map
  override val type: String

  public fun preventDefault()
}
