package io.github.mapvina.compose.demoapp.demos

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt
import org.jetbrains.compose.resources.painterResource
import io.github.mapvina.compose.demoapp.DemoState
import io.github.mapvina.compose.demoapp.design.CardColumn
import io.github.mapvina.compose.demoapp.generated.Res
import io.github.mapvina.compose.demoapp.generated.marker
import io.github.mapvina.compose.expressions.dsl.image
import io.github.mapvina.compose.layers.SymbolLayer
import io.github.mapvina.compose.sources.GeoJsonData
import io.github.mapvina.compose.sources.rememberGeoJsonSource
import io.github.mapvina.spatialk.geojson.Feature
import io.github.mapvina.spatialk.geojson.FeatureCollection
import io.github.mapvina.spatialk.geojson.Point

object MapClickDemo : Demo {
  override val name = "Map Click Events"

  @Composable
  override fun MapContent(state: DemoState, isOpen: Boolean) {
    if (!isOpen) return

    val clickFeatures by derivedStateOf {
      FeatureCollection(
        state.mapClickEvents.map { Feature(geometry = Point(it.position), properties = null) }
      )
    }

    SymbolLayer(
      id = "click-markers",
      source = rememberGeoJsonSource(data = GeoJsonData.Features(clickFeatures)),
      iconImage = image(painterResource(Res.drawable.marker)),
    )
  }

  private fun Double.rounded(): Double = (this * 10000).roundToInt().toDouble() / 10000

  @Composable
  override fun SheetContent(state: DemoState, modifier: Modifier) {
    val clickPositions by derivedStateOf { state.mapClickEvents.map { it.position } }

    // TODO: clear all button?

    CardColumn {
      if (clickPositions.isEmpty()) {
        Text(
          text = "Click on the map to add markers",
          modifier = Modifier.padding(16.dp),
          style = MaterialTheme.typography.bodyMedium,
        )
      } else {
        clickPositions.forEach { pos ->
          key(pos.hashCode()) {
            ListItem(
              headlineContent = {
                Text("Lat: ${pos.latitude.rounded()}, Lng: ${pos.longitude.rounded()}")
              }
            )
          }
        }
      }
    }
  }
}
