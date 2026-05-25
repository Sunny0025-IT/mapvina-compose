package io.github.mapvina.compose.location

import android.location.Location as AndroidLocation
import android.os.Build
import android.os.SystemClock
import kotlin.time.Duration.Companion.nanoseconds
import kotlin.time.TimeSource
import io.github.mapvina.spatialk.geojson.Position
import io.github.mapvina.spatialk.units.Bearing
import io.github.mapvina.spatialk.units.extensions.degrees
import io.github.mapvina.spatialk.units.extensions.meters

public fun AndroidLocation.asMapVinaLocation(): Location =
  Location(
    position =
      PositionWithAccuracy(
        value = Position(longitude = longitude, latitude = latitude, altitude = altitude),
        accuracy = if (hasAccuracy()) accuracy.toDouble().meters else null,
      ),
    speed =
      if (hasSpeed()) {
        SpeedWithAccuracy(
          distancePerSecond = speed.toDouble().meters,
          accuracy =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && hasSpeedAccuracy()) {
              speedAccuracyMetersPerSecond.toDouble().meters
            } else {
              null
            },
        )
      } else {
        null
      },
    course =
      if (hasBearing()) {
        BearingWithAccuracy(
          value = Bearing.North + bearing.toDouble().degrees,
          accuracy =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && hasBearingAccuracy()) {
              bearingAccuracyDegrees.toDouble().degrees
            } else {
              null
            },
        )
      } else {
        null
      },
    timestamp =
      (SystemClock.elapsedRealtimeNanos() - elapsedRealtimeNanos).nanoseconds.let { age ->
        TimeSource.Monotonic.markNow() - age
      },
  )
