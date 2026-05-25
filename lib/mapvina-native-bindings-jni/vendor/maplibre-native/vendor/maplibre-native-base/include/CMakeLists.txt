add_library(mapvina-native-base INTERFACE)
add_library(MapVinaNative::Base ALIAS mapvina-native-base)

target_include_directories(mapvina-native-base SYSTEM INTERFACE
    "${PROJECT_SOURCE_DIR}/include"
)

target_link_libraries(mapvina-native-base INTERFACE
    MapVinaNative::Base::Extras::expected-lite
)
