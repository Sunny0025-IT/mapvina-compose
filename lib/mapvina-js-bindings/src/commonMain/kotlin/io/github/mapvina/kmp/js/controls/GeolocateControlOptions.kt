@file:JsModule("mapvina-gl")

package io.github.mapvina.kmp.js.controls

import io.github.mapvina.kmp.js.browserapi.PositionOptions
import io.github.mapvina.kmp.js.map.FitBoundsOptions

/**
 * [GeolocateControlOptions](https://mapvina.io/github/mapvina-gl-js/docs/API/type-aliases/GeolocateControlOptions/)
 */
public external interface GeolocateControlOptions {
  public var fitBoundsOptions: FitBoundsOptions?
  public var positionOptions: PositionOptions?
  public var showAccuracyCircle: Boolean?
  public var showUserLocation: Boolean?
  public var trackUserLocation: Boolean?
}
