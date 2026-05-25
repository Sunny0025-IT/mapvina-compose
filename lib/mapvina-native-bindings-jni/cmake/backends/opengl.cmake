# OpenGL backend configuration

if(NOT MLN_WITH_OPENGL)
    return()
endif()

target_compile_definitions(mapvina-jni PRIVATE USE_OPENGL_BACKEND)

find_package(OpenGL REQUIRED)
target_link_libraries(mapvina-jni PRIVATE OpenGL::GL)
target_include_directories(mapvina-jni PRIVATE ${OpenGL_INCLUDE_DIR})

if(UNIX AND NOT APPLE)
    find_package(OpenGL REQUIRED COMPONENTS GLX)
    target_link_libraries(mapvina-jni PRIVATE OpenGL::GLX)
endif()
