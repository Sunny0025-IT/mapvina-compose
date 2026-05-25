package io.github.mapvina.compose.demoapp.util

import android.os.Build
import androidx.compose.foundation.layout.PaddingValues
import io.github.mapvina.compose.demoapp.demos.Demo
import io.github.mapvina.compose.demoapp.demos.GestureOptionsDemo
import io.github.mapvina.compose.demoapp.demos.GmsLocationDemo
import io.github.mapvina.compose.demoapp.demos.OfflineManagerDemo
import io.github.mapvina.compose.demoapp.demos.OrnamentOptionsDemo
import io.github.mapvina.compose.demoapp.demos.RenderOptionsDemo
import io.github.mapvina.compose.demoapp.demos.SynchronousGeoJsonUpdatesDemo
import io.github.mapvina.compose.map.OrnamentOptions

actual object Platform {
  actual val name = "Android"

  actual val version = "${Build.VERSION.RELEASE} ${Build.VERSION.CODENAME}"

  actual val supportedFeatures = PlatformFeature.Everything

  actual val extraDemos: List<Demo> =
    listOf(
      GestureOptionsDemo,
      OrnamentOptionsDemo,
      OfflineManagerDemo,
      RenderOptionsDemo,
      GmsLocationDemo,
      SynchronousGeoJsonUpdatesDemo,
    )

  actual fun padOrnaments(options: OrnamentOptions, padding: PaddingValues) =
    options.copy(padding = padding)
}
