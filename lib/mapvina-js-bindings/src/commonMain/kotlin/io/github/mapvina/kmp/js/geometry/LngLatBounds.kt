@file:JsModule("mapvina-gl")

package io.github.mapvina.kmp.js.geometry

/** [LngLatBounds](https://mapvina.io/github/mapvina-gl-js/docs/API/classes/LngLatBounds/) */
public external class LngLatBounds(sw: LngLat, ne: LngLat) {
  public fun adjustAntiMeridian(): LngLatBounds

  public fun contains(lngLat: LngLat): Boolean

  public fun extend(lngLat: LngLat): LngLatBounds

  public fun getCenter(): LngLat

  public fun getEast(): Double

  public fun getWest(): Double

  public fun getNorth(): Double

  public fun getSouth(): Double

  public fun getSouthWest(): LngLat

  public fun getNorthEast(): LngLat

  public fun getNorthWest(): LngLat

  public fun getSouthEast(): LngLat

  public fun isEmpty(): Boolean

  public fun setSouthWest(lngLat: LngLat): LngLatBounds

  public fun setNorthEast(lngLat: LngLat): LngLatBounds

  public fun toArray(): Array<DoubleArray>
}
