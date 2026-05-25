package io.github.mapvina.kmp.native.util

import smjni.jnigen.CalledByNative
import smjni.jnigen.ExposeToNative

@ExposeToNative
public data class ScreenCoordinate
@CalledByNative
public constructor(@get:CalledByNative val x: Double, @get:CalledByNative val y: Double)
