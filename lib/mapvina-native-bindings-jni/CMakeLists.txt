cmake_minimum_required(VERSION 3.21)

include(cmake/vars.cmake)

project(mapvina-jni)

include(cmake/target.cmake)

include(cmake/dependencies/platform.cmake)
include(cmake/dependencies/jni.cmake)
include(cmake/dependencies/simplejni.cmake)
include(cmake/dependencies/mapvina-native.cmake)

include(cmake/backends/metal.cmake)
include(cmake/backends/vulkan.cmake)
include(cmake/backends/opengl.cmake)
