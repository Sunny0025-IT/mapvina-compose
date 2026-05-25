@file:JsModule("mapvina-gl")

package io.github.mapvina.kmp.js.geometry

/** [LngLat](https://mapvina.io/github/mapvina-gl-js/docs/API/classes/LngLat/) */
public external class LngLat(public val lng: Double, public val lat: Double) {
  public fun distanceTo(lngLat: LngLat): Double

  public fun toArray(): DoubleArray

  public fun wrap(): LngLat
}
