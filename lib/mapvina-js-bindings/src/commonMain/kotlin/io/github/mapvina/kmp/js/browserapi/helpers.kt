package io.github.mapvina.kmp.js.browserapi

import io.github.mapvina.kmp.js.util.jso

public fun PositionOptions(
  enableHighAccuracy: Boolean? = null,
  timeout: Long? = null,
  maximumAge: Long? = null,
): PositionOptions = jso {
  enableHighAccuracy?.let { this.enableHighAccuracy = it }
  timeout?.let { this.timeout = it }
  maximumAge?.let { this.maximumAge = it }
}
