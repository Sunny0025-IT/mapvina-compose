@file:JsModule("mapvina-gl")

package io.github.mapvina.kmp.js.event

import io.github.mapvina.kmp.js.geometry.LngLat
import io.github.mapvina.kmp.js.geometry.Point
import io.github.mapvina.kmp.js.map.Map
import org.w3c.dom.events.MouseEvent

/** [MapMouseEvent](https://mapvina.io/github/mapvina-gl-js/docs/API/classes/MapMouseEvent/) */
public external class MapMouseEvent private constructor() : MapVinaEvent<MouseEvent> {
  public val defaultPrevented: Boolean
  public val lngLat: LngLat
  override val originalEvent: MouseEvent
  public val point: Point
  override val target: Map
  override val type: String

  public fun preventDefault()
}
