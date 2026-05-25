package io.github.mapvina.compose.demoapp.demos

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import io.github.mapvina.compose.demoapp.DemoState
import io.github.mapvina.compose.expressions.dsl.const
import io.github.mapvina.compose.expressions.dsl.exponential
import io.github.mapvina.compose.expressions.dsl.interpolate
import io.github.mapvina.compose.expressions.dsl.zoom
import io.github.mapvina.compose.expressions.value.LineCap
import io.github.mapvina.compose.expressions.value.LineJoin
import io.github.mapvina.compose.layers.Anchor
import io.github.mapvina.compose.layers.LineLayer
import io.github.mapvina.compose.sources.GeoJsonData
import io.github.mapvina.compose.sources.GeoJsonOptions
import io.github.mapvina.compose.sources.rememberGeoJsonSource
import io.github.mapvina.spatialk.geojson.BoundingBox
import io.github.mapvina.spatialk.geojson.Position

object AnimatedLayerDemo : Demo {
  override val name = "Animated layers"

  override val region =
    BoundingBox(southwest = Position(-125.0, 24.0), northeast = Position(-66.0, 49.0))

  override val mapContentVisibilityState = mutableStateOf(true)

  @Composable
  override fun MapContent(state: DemoState, isOpen: Boolean) {
    val routeSource =
      rememberGeoJsonSource(
        data =
          GeoJsonData.Uri(
            "https://raw.githubusercontent.com/datanews/amtrak-geojson/refs/heads/master/amtrak-combined.geojson"
          ),
        options = GeoJsonOptions(tolerance = 0.1f),
      )

    val infiniteTransition = rememberInfiniteTransition()
    val animatedColor by
      infiniteTransition.animateColor(
        Color.hsl(0f, 1f, 0.5f),
        Color.hsl(0f, 1f, 0.5f),
        animationSpec =
          infiniteRepeatable(
            animation =
              keyframes {
                durationMillis = 10000
                for (i in 1..9) Color.hsl(i * 36f, 1f, 0.5f) at (i * 1000)
              }
          ),
      )

    Anchor.At(state.selectedStyle.anchorBelowSymbols) {
      LineLayer(
        id = "amtrak-routes",
        source = routeSource,
        color = const(animatedColor),
        cap = const(LineCap.Round),
        join = const(LineJoin.Round),
        width =
          interpolate(
            type = exponential(1.2f),
            input = zoom(),
            7 to const(1.75.dp),
            20 to const(22.dp),
          ),
      )
    }
  }
}
