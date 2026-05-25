# Target creation and configuration

add_library(mapvina-jni SHARED
    src/main/cpp/mapvina_map.cpp
    src/main/cpp/conversions.cpp
    src/main/cpp/jni_on_load.cpp
    src/main/cpp/jni_map_observer.cpp
    src/main/cpp/canvas_frontend.cpp
    src/main/cpp/canvas_metal_backend.mm
    src/main/cpp/canvas_opengl_backend.cpp
    src/main/cpp/canvas_vulkan_backend.cpp
    src/main/cpp/mapvina_util_projection.cpp
)

target_compile_options(mapvina-jni PRIVATE
    $<$<CXX_COMPILER_ID:GNU>:-frtti -fexceptions>
    $<$<CXX_COMPILER_ID:Clang>:-frtti -fexceptions>
)

if(WIN32)
    target_compile_definitions(mapvina-jni PRIVATE
        NOMINMAX  # Prevent min/max macro conflicts
    )
endif()

set_target_properties(mapvina-jni PROPERTIES
    LIBRARY_OUTPUT_DIRECTORY ${OUTPUT_DIR}
    RUNTIME_OUTPUT_DIRECTORY ${OUTPUT_DIR}
)
