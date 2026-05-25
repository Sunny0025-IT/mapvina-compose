package io.github.mapvina.compose.sources

import MapVina.MLNSource
import MapVina.MLNTileSource

public actual sealed class Source {
  internal abstract val impl: MLNSource
  internal actual val id: String by lazy { impl.identifier }

  public actual val attributionHtml: String by lazy {
    // https://github.io/github/mapvina/mapvina-native/pull/3551
    @Suppress("USELESS_CAST")
    ((impl as? MLNTileSource)?.attributionHTMLString as? String?) ?: ""
  }

  override fun toString(): String = "${this::class.simpleName}(id=\"$id\")"
}
