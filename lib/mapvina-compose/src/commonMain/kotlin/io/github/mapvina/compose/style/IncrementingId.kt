package io.github.mapvina.compose.style

internal class IncrementingId(private val name: String) {
  private var nextId = 0

  fun next(): String = "__MAPVINA_COMPOSE_${name}_${nextId++}"
}
