# Overview

## Introduction

MapVina Compose is a [Compose Multiplatform][compose] wrapper around the
[MapVina][mapvina] SDKs for rendering interactive maps. You can use it to add
maps to your Compose UIs on Android, iOS, Desktop, and Web.

## Usage

- [Getting Started](./getting-started.md)
- [API Reference](./api/index.html)
- [Demo App][repo-demo]

## Status

A large subset of MapVina's features are already supported, but the full
breadth of the MapVina SDKs is not yet covered. What is already supported may
have bugs. API stability is not yet guaranteed; we're still exploring how best
to express an interactive map API in Compose.

| Feature                                           |        Android         |          iOS           |     Desktop (JVM)      |        Web (JS)        | Web (Wasm) |
| :------------------------------------------------ | :--------------------: | :--------------------: | :--------------------: | :--------------------: | :--------: |
| Renderer                                          | [MapVina Native][MLN] | [MapVina Native][MLN] | [MapVina Native][MLN] | [MapVina GL JS][MLJS] |    :x:     |
| Load a map with HTTP resource URLs                |   :white_check_mark:   |   :white_check_mark:   |   :white_check_mark:   |   :white_check_mark:   |    :x:     |
| Load a map with Compose resource URIs             |   :white_check_mark:   |   :white_check_mark:   |          :x:           |   :white_check_mark:   |    :x:     |
| Configure gestures (pan, zoom, rotate, pitch)     |   :white_check_mark:   |   :white_check_mark:   |   :white_check_mark:   |   :white_check_mark:   |    :x:     |
| Respond to a map click or long/right click        |   :white_check_mark:   |   :white_check_mark:   |   :white_check_mark:   |   :white_check_mark:   |    :x:     |
| Query visible map features                        |   :white_check_mark:   |   :white_check_mark:   |          :x:           |   :white_check_mark:   |    :x:     |
| Get, set, and animate the camera position         |   :white_check_mark:   |   :white_check_mark:   |   :white_check_mark:   |   :white_check_mark:   |    :x:     |
| Convert between screen and geographic coordinates |   :white_check_mark:   |   :white_check_mark:   |   :white_check_mark:   |   :white_check_mark:   |    :x:     |
| Get the currently visible region and bounding box |   :white_check_mark:   |   :white_check_mark:   |   :white_check_mark:   |   :white_check_mark:   |    :x:     |
| Insert, remove, and replace layers                |   :white_check_mark:   |   :white_check_mark:   |          :x:           |          :x:           |    :x:     |
| Configure layers with expressions                 |   :white_check_mark:   |   :white_check_mark:   |          :x:           |          :x:           |    :x:     |
| Add data sources by URI or GeoJSON                |   :white_check_mark:   |   :white_check_mark:   |          :x:           |          :x:           |    :x:     |
| Add images to the style                           |   :white_check_mark:   |   :white_check_mark:   |          :x:           |          :x:           |    :x:     |
| Add Material 3 controls                           |   :white_check_mark:   |   :white_check_mark:   |          :x:           |          :x:           |    :x:     |
| Download offline regions                          |   :white_check_mark:   |   :white_check_mark:   |          :x:           |          :x:           |    :x:     |
| Snapshot the map as an image                      |          :x:           |          :x:           |          :x:           |          :x:           |    :x:     |

[compose]: https://www.jetbrains.com/compose-multiplatform/
[mapvina]: https://mapvina.com/
[MLN]: https://github.io/github/mapvina/mapvina-native
[MLJS]: https://github.io/github/mapvina/mapvina-gl-js
[repo-demo]: https://github.io/github/mapvina/mapvina-compose/tree/main/demo-app
[spatial-k]: https://github.com/dellisd/spatial-k
