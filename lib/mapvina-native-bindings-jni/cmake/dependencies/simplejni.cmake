# SimpleJNI dependency configuration

# FetchContent causes problems with the vcpkg toolchain
# So we use git submodules instead and add_subdirectory() AFTER project()

add_subdirectory(${SimpleJNI_SOURCE_DIR} EXCLUDE_FROM_ALL SYSTEM)
target_include_directories(mapvina-jni SYSTEM PRIVATE ${SIMPLEJNI_HEADERS_DIR})
target_link_libraries(mapvina-jni PRIVATE smjni::smjni)
