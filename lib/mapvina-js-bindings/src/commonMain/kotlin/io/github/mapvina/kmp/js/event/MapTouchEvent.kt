@file:JsModule("mapvina-gl")

package io.github.mapvina.kmp.js.event

import io.github.mapvina.kmp.js.geometry.LngLat
import io.github.mapvina.kmp.js.geometry.Point
import io.github.mapvina.kmp.js.map.Map
import org.w3c.dom.TouchEvent

/** [MapTouchEvent](https://mapvina.io/github/mapvina-gl-js/docs/API/classes/MapTouchEvent/) */
public external class MapTouchEvent private constructor() : MapVinaEvent<TouchEvent> {
  public val defaultPrevented: Boolean
  public val lngLat: LngLat
  public val lngLats: Array<LngLat>
  override val originalEvent: TouchEvent
  public val point: Point
  public val points: Array<Point>
  override val target: Map
  override val type: String

  public fun preventDefault()
}
