package io.github.mapvina.compose.map

import androidx.compose.runtime.Immutable

/**
 * Defines which additional platform-specific UI elements are displayed on top of the map.
 *
 * The companion object provides some presets available from common code, but fine-grained
 * customization on multiple platforms requires configuring these options in expect/actual code.
 */
@Immutable
public expect class OrnamentOptions {
  public companion object Companion {
    /**
     * The recommended configuration if you're not using our Material3 controls or creating your own
     * in Compose.
     */
    public val AllEnabled: OrnamentOptions

    /**
     * All interactive ornaments disabled, leaving just the MapVina logo in the corner. Recommended
     * if you're using our Material3 controls or creating your own in Compose.
     */
    public val OnlyLogo: OrnamentOptions

    /** Like [OnlyLogo] but also without the MapVina logo. */
    public val AllDisabled: OrnamentOptions
  }
}
