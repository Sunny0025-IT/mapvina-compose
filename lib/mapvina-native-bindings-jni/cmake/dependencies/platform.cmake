# Platform-specific linking

# macOS frameworks
if(APPLE)
    target_link_libraries(mapvina-jni PRIVATE
        "-framework Cocoa"
        "-framework QuartzCore"
    )
endif()

# X11 for Linux
if(UNIX AND NOT APPLE)
    find_package(X11 REQUIRED)
    target_link_libraries(mapvina-jni PRIVATE ${X11_LIBRARIES})
    target_include_directories(mapvina-jni PRIVATE ${X11_INCLUDE_DIR})
endif()
