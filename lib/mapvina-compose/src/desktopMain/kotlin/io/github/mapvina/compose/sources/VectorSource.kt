package io.github.mapvina.compose.sources

import kotlinx.serialization.json.JsonObject
import io.github.mapvina.compose.expressions.ast.Expression
import io.github.mapvina.compose.expressions.value.BooleanValue
import io.github.mapvina.spatialk.geojson.Feature
import io.github.mapvina.spatialk.geojson.Geometry

public actual class VectorSource : Source {
  public actual constructor(id: String, uri: String) : super() {
    this.impl = TODO()
  }

  public actual constructor(id: String, tiles: List<String>, options: TileSetOptions) : super() {
    this.impl = TODO()
  }

  override val impl: Nothing

  public actual fun querySourceFeatures(
    sourceLayerIds: Set<String>,
    predicate: Expression<BooleanValue>,
  ): List<Feature<Geometry, JsonObject?>> {
    TODO()
  }
}
