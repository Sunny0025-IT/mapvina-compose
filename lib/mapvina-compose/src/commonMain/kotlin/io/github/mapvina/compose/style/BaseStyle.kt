package io.github.mapvina.compose.style

import androidx.compose.runtime.Immutable
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonObjectBuilder
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import kotlinx.serialization.json.putJsonArray
import kotlinx.serialization.json.putJsonObject

@Immutable
public sealed interface BaseStyle {

  @Immutable public data class Uri(public val uri: String) : BaseStyle

  @Immutable
  public data class Json(public val json: String) : BaseStyle {

    public constructor(json: JsonObject) : this(json.toString())

    public constructor(
      builderAction: JsonObjectBuilder.() -> Unit
    ) : this(buildJsonObject(builderAction))
  }

  public companion object {
    public val Demo: Uri = Uri("https://maps.mapvina.com/styles/v2/streets.json?key=public_key")
    public val Empty: Json = Json {
      put("version", 8)
      put("name", "MapVina Compose")
      putJsonObject("metadata") {}
      putJsonObject("sources") {}
      putJsonArray("layers") {}
    }
  }
}
