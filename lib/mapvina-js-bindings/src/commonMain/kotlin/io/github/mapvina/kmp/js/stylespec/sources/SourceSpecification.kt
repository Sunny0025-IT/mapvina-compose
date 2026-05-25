@file:JsModule("mapvina-gl")

package io.github.mapvina.kmp.js.stylespec.sources

/**
 * Represents any source specification type.
 *
 * See [MapVina Style Spec - Sources](https://mapvina.io/github/mapvina-style-spec/sources/)
 */
public sealed external interface SourceSpecification {
  /** The source type: "geojson", "vector", "raster", "raster-dem", "image", or "video". */
  public var type: String
}
