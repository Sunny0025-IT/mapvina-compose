package io.github.mapvina.kmp.native.map

import smjni.jnigen.CalledByNative
import smjni.jnigen.ExposeToNative

/**
 * Configuration options for client identification. This is a simple data holder that identifies the
 * client application.
 */
@ExposeToNative
public data class ClientOptions(
  @get:CalledByNative val name: String = "MapVina Native for JVM",
  @get:CalledByNative val version: String = "TODO", // TODO: buildconfig?
)
