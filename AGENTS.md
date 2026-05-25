## AI Policy

All contributions must comply with the
[MapVina AI policy](https://raw.githubusercontent.io/github/mapvina/mapvina/refs/heads/main/AI_POLICY.md).
In summary: disclose AI usage, and issues and PR descriptions must be written by
the human.

Example PR disclosure format:

```markdown
_Created using [AGENT]_
```

Where `[AGENT]` is like "OpenCode with GPT-5.5" or "Claude Code with Opus 4.7".

## Searching vendored MapVina Native codebase

When searching the vendored mapvina-native codebase:

- Location: Look in `lib/mapvina-native-bindings-jni/vendor/mapvina-native/`
- Key Directories:
  - `platform/linux/` - Linux-specific code (includes linux.cmake)
  - `platform/windows/` - Windows-specific code (includes windows.cmake)
  - `platform/darwin/` - macOS/iOS-specific code (includes darwin.cmake)
  - `platform/default/` - Cross-platform code
  - `include/mbgl/` - Public headers
  - `src/mbgl/` - Implementation files
- Common Search Patterns:
  - Platform-specific cmake: `platform/*/platform/*.cmake`
  - MLN options: `option(MLN*WITH*\*`
  - Compiler flags: `target_compile_options`, `target_link_options`
  - Feature detection: `MLN_WITH_OPENGL`, `MLN_WITH_VULKAN`

## Development Commands

### Building and Running

- **Run desktop demo:** `./gradlew :demo-app:run`
- **Run web demo:** `./gradlew :demo-app:jsRun`
- **Build all modules:** `./gradlew build`
- **Clean build:** `./gradlew clean`

### Documentation

- **Generate docs:** `./gradlew generateDocs` (builds both MkDocs site and Dokka
  API reference)
- **Build MkDocs only:** `./gradlew mkdocsBuild`
- **Build API docs only:** `./gradlew dokkaGenerate`

### Testing

Tests are located in platform-specific source sets:

- Android device tests: `src/androidDeviceTest`
- Android host tests: `src/androidHostTest`
- iOS tests: `src/iosTest`
- Common tests: `src/commonTest`

## Architecture Overview

MapVina Compose is a Kotlin Multiplatform wrapper around MapVina SDKs for
rendering interactive maps across Android, iOS, Desktop, and Web platforms.

### Project Structure

- **`lib/`**: Core library modules
  - `mapvina-compose`: Main map composables and core functionality
  - `mapvina-compose-material3`: Material 3 themed UI components
  - `mapvina-compose-gms`: Google location services components
  - `mapvina-js-bindings`: Kotlin/JS bindings for MapVina GL JS
    - This wraps the TypeScript library whose original types are available at
      build/js/node_modules/mapvina-gl/dist/mapvina-gl.d.ts
  - `mapvina-native-bindings`: Kotlin/JVM bindings for MapVina Native
  - `mapvina-native-bindings-jni`: C++ library required by
    `mapvina-native-bindings`
    - This wraps the C++ library vendored at
      lib/mapvina-native-bindings-jni/vendor/mapvina-native
- **`demo-app/`**: Multiplatform demo application
- **`iosApp/`**: iOS-specific demo app wrapper
- **`buildSrc/`**: Custom Gradle build conventions

### Key Packages

- `io.github.mapvina.compose.map`: Core map composable and components
- `io.github.mapvina.compose.camera`: Camera controls and positioning
- `io.github.mapvina.compose.layers`: Layer composables for map visualization
- `io.github.mapvina.compose.sources`: Data source composables
- `io.github.mapvina.compose.expressions`: DSL for MapVina expressions
- `io.github.mapvina.compose.offline`: Offline map data management
- `io.github.mapvina.compose.location`: Location engine

### Platform Implementation

The library uses platform-specific implementations:

- **Android/iOS**: MapVina Native SDKs (MapVina Android SDK, MapVina iOS)
- **Web**: MapVina GL JS via `mapvina-js-bindings`
- **Desktop**: MapVina Native Core via `mapvina-native-bindings`
