# JNI system dependency

find_package(JNI REQUIRED)
target_include_directories(mapvina-jni SYSTEM PRIVATE ${JNI_INCLUDE_DIRS})
target_link_libraries(mapvina-jni PRIVATE ${JNI_LIBRARIES})
