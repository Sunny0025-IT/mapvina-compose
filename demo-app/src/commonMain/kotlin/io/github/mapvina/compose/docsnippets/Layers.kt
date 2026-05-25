@file:Suppress("unused")

package io.github.mapvina.compose.docsnippets

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.ExperimentalResourceApi
import io.github.mapvina.compose.demoapp.generated.Res
import io.github.mapvina.compose.expressions.dsl.const
import io.github.mapvina.compose.expressions.dsl.exponential
import io.github.mapvina.compose.expressions.dsl.interpolate
import io.github.mapvina.compose.expressions.dsl.zoom
import io.github.mapvina.compose.expressions.value.LineCap
import io.github.mapvina.compose.expressions.value.LineJoin
import io.github.mapvina.compose.layers.Anchor
import io.github.mapvina.compose.layers.CircleLayer
import io.github.mapvina.compose.layers.LineLayer
import io.github.mapvina.compose.map.MapvinaMap
import io.github.mapvina.compose.sources.GeoJsonData
import io.github.mapvina.compose.sources.GeoJsonOptions
import io.github.mapvina.compose.sources.getBaseSource
import io.github.mapvina.compose.sources.rememberGeoJsonSource
import io.github.mapvina.compose.style.BaseStyle
import io.github.mapvina.compose.util.ClickResult
import io.github.mapvina.spatialk.geojson.toJson

@Composable
@OptIn(ExperimentalResourceApi::class)
fun Layers() {
  // -8<- [start:simple]
  MapvinaMap(baseStyle = BaseStyle.Uri("https://tiles.openfreemap.org/styles/liberty")) {
    getBaseSource(id = "openmaptiles")?.let { tiles ->
      CircleLayer(id = "example", source = tiles, sourceLayer = "poi")
    }
  }
  // -8<- [end:simple]

  MapvinaMap {
    val amtrakStations =
      rememberGeoJsonSource(GeoJsonData.Uri(Res.getUri("files/data/amtrak_stations.geojson")))

    // -8<- [start:amtrak-1]
    val amtrakRoutes =
      rememberGeoJsonSource(GeoJsonData.Uri(Res.getUri("files/data/amtrak_routes.geojson")))
    LineLayer(
      id = "amtrak-routes-casing",
      source = amtrakRoutes,
      color = const(Color.White),
      width = const(6.dp),
    )
    LineLayer(
      id = "amtrak-routes",
      source = amtrakRoutes,
      color = const(Color.Blue),
      width = const(4.dp),
    )
    // -8<- [end:amtrak-1]

    // -8<- [start:amtrak-2]
    LineLayer(
      id = "amtrak-routes",
      source = amtrakRoutes,
      cap = const(LineCap.Round),
      join = const(LineJoin.Round),
      color = const(Color.Blue),
      width =
        interpolate(
          type = exponential(1.2f),
          input = zoom(),
          5 to const(0.4.dp),
          6 to const(0.7.dp),
          7 to const(1.75.dp),
          20 to const(22.dp),
        ),
    )
    // -8<- [end:amtrak-2]

    // -8<- [start:anchors]
    Anchor.Above("road_motorway") { LineLayer(id = "amtrak-routes", source = amtrakRoutes) }
    // -8<- [end:anchors]

    // -8<- [start:interaction]
    CircleLayer(
      id = "amtrak-stations",
      source = amtrakStations,
      onClick = { features ->
        println("Clicked on ${features[0].toJson()}")
        ClickResult.Consume
      },
    )
    // -8<- [end:interaction]
  }

  // -8<- [start:synchronous-geojson-updates]
  val livePositions =
    rememberGeoJsonSource(
      data = GeoJsonData.JsonString("""{"type":"FeatureCollection","features":[]}"""),
      options = GeoJsonOptions(synchronousUpdate = true),
    )
  // Android only for now: other platforms currently ignore this option.
  // Use this only for small, frequently updated in-memory GeoJSON sources.
  // Synchronous updates can reduce update latency, but may reduce frame rate.
  CircleLayer(id = "live-positions", source = livePositions)
  // -8<- [end:synchronous-geojson-updates]
}
