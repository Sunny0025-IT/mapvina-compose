if(NOT TARGET mapvina-native)
    add_subdirectory(${CMAKE_CURRENT_LIST_DIR}/mapvina-native-base)
endif()

set_target_properties(
    mapvina-native-base-extras-kdbush.hpp
    PROPERTIES
        INTERFACE_MAPVINA_NAME "kdbush.hpp"
        INTERFACE_MAPVINA_URL "https://github.com/mourner/kdbush.hpp"
        INTERFACE_MAPVINA_AUTHOR "Vladimir Agafonkin"
        INTERFACE_MAPVINA_LICENSE ${CMAKE_CURRENT_LIST_DIR}/mapvina-native-base/extras/kdbush.hpp/LICENSE
)

set_target_properties(
    mapvina-native-base-extras-expected-lite
    PROPERTIES
        INTERFACE_MAPVINA_NAME "expected-lite"
        INTERFACE_MAPVINA_URL "https://github.com/martinmoene/expected-lite"
        INTERFACE_MAPVINA_AUTHOR "Martin Moene"
        INTERFACE_MAPVINA_LICENSE ${CMAKE_CURRENT_LIST_DIR}/mapvina-native-base/extras/expected-lite/LICENSE.txt
)

set_target_properties(
    mapvina-native-base-extras-filesystem
    PROPERTIES
        INTERFACE_MAPVINA_NAME "filesystem"
        INTERFACE_MAPVINA_URL "https://github.com/gulrak/filesystem"
        INTERFACE_MAPVINA_AUTHOR "Steffen Schümann"
        INTERFACE_MAPVINA_LICENSE ${CMAKE_CURRENT_LIST_DIR}/mapvina-native-base/extras/filesystem/LICENSE.txt
)

set_target_properties(
    mapvina-native-base-supercluster.hpp
    PROPERTIES
        INTERFACE_MAPVINA_NAME "supercluster.hpp"
        INTERFACE_MAPVINA_URL "https://github.com/mapbox/supercluster.hpp"
        INTERFACE_MAPVINA_AUTHOR "Mapbox"
        INTERFACE_MAPVINA_LICENSE ${CMAKE_CURRENT_LIST_DIR}/mapvina-native-base/deps/supercluster.hpp/LICENSE
)

set_target_properties(
    mapvina-native-base-shelf-pack-cpp
    PROPERTIES
        INTERFACE_MAPVINA_NAME "shelf-pack-cpp"
        INTERFACE_MAPVINA_URL "https://github.com/mapbox/shelf-pack-cpp"
        INTERFACE_MAPVINA_AUTHOR "Mapbox"
        INTERFACE_MAPVINA_LICENSE ${CMAKE_CURRENT_LIST_DIR}/mapvina-native-base/deps/shelf-pack-cpp/LICENSE.md
)

set_target_properties(
    mapvina-native-base-geojson-vt-cpp
    PROPERTIES
        INTERFACE_MAPVINA_NAME "geojson-vt-cpp"
        INTERFACE_MAPVINA_URL "https://github.com/mapbox/geojson-vt-cpp"
        INTERFACE_MAPVINA_AUTHOR "Mapbox"
        INTERFACE_MAPVINA_LICENSE ${CMAKE_CURRENT_LIST_DIR}/mapvina-native-base/deps/geojson-vt-cpp/LICENSE
)

set_target_properties(
    mapvina-native-base-extras-rapidjson
    PROPERTIES
        INTERFACE_MAPVINA_NAME "RapidJSON"
        INTERFACE_MAPVINA_URL "https://rapidjson.org"
        INTERFACE_MAPVINA_AUTHOR "THL A29 Limited, a Tencent company, and Milo Yip"
        INTERFACE_MAPVINA_LICENSE ${CMAKE_CURRENT_LIST_DIR}/mapvina-native-base/extras/rapidjson/license.txt
)

set_target_properties(
    mapvina-native-base-geojson.hpp
    PROPERTIES
        INTERFACE_MAPVINA_NAME "geojson.hpp"
        INTERFACE_MAPVINA_URL "https://github.com/mapbox/geojson-cpp"
        INTERFACE_MAPVINA_AUTHOR "Mapbox"
        INTERFACE_MAPVINA_LICENSE ${CMAKE_CURRENT_LIST_DIR}/mapvina-native-base/deps/geojson.hpp/LICENSE
)

set_target_properties(
    mapvina-native-base-geometry.hpp
    PROPERTIES
        INTERFACE_MAPVINA_NAME "geometry.hpp"
        INTERFACE_MAPVINA_URL "https://github.com/mapbox/geometry.hpp"
        INTERFACE_MAPVINA_AUTHOR "Mapbox"
        INTERFACE_MAPVINA_LICENSE ${CMAKE_CURRENT_LIST_DIR}/mapvina-native-base/deps/geometry.hpp/LICENSE
)

set_target_properties(
    mapvina-native-base
    PROPERTIES
        INTERFACE_MAPVINA_NAME "mapbox-base"
        INTERFACE_MAPVINA_URL "https://github.com/mapbox/mapbox-base"
        INTERFACE_MAPVINA_AUTHOR "Mapbox"
        INTERFACE_MAPVINA_LICENSE ${CMAKE_CURRENT_LIST_DIR}/mapvina-native-base/LICENSE
)

set_target_properties(
    mapvina-native-base-variant
    PROPERTIES
        INTERFACE_MAPVINA_NAME "variant"
        INTERFACE_MAPVINA_URL "https://github.com/mapbox/variant"
        INTERFACE_MAPVINA_AUTHOR "Mapbox"
        INTERFACE_MAPVINA_LICENSE ${CMAKE_CURRENT_LIST_DIR}/mapvina-native-base/deps/variant/LICENSE
)

set_target_properties(
    mapvina-native-base-cheap-ruler-cpp
    PROPERTIES
        INTERFACE_MAPVINA_NAME "cheap-ruler-cpp"
        INTERFACE_MAPVINA_URL "https://github.com/mapbox/cheap-ruler-cpp"
        INTERFACE_MAPVINA_AUTHOR "Mapbox"
        INTERFACE_MAPVINA_LICENSE ${CMAKE_CURRENT_LIST_DIR}/mapvina-native-base/deps/cheap-ruler-cpp/LICENSE
)
