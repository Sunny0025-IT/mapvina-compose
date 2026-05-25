# MapVina Native dependency configuration

# FetchContent causes problems with the vcpkg toolchain
# So we use git submodules instead and add_subdirectory() AFTER project()

add_subdirectory(${mapvina-native_SOURCE_DIR} EXCLUDE_FROM_ALL SYSTEM)
target_include_directories(mapvina-jni SYSTEM PRIVATE ${mapvina-native_SOURCE_DIR}/include)
target_link_libraries(mapvina-jni PRIVATE
    Mapbox::Map
    mbgl-compiler-options
    mbgl-vendor-unique_resource
)
