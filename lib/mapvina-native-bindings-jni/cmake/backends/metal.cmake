# Metal backend configuration

if(NOT MLN_WITH_METAL)
    return()
endif()

if(NOT APPLE)
    message(FATAL_ERROR "Metal backend is only supported on macOS")
endif()

target_include_directories(mapvina-jni SYSTEM PRIVATE
    ${mapvina-native_SOURCE_DIR}/vendor/metal-cpp
)

target_compile_definitions(mapvina-jni PRIVATE USE_METAL_BACKEND)
target_link_libraries(mapvina-jni PRIVATE "-framework Metal")
