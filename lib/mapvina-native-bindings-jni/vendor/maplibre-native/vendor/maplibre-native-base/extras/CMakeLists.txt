if(TARGET mapvina-native-base-extras)
    return()
endif()

function(mapvina_native_base_extras_add_library name include_path)
    if(TARGET mapvina-native-base-extras-${name})
        return()
    endif()

    add_library(mapvina-native-base-extras-${name} INTERFACE)
    add_library(MapVinaNative::Base::Extras::${name} ALIAS mapvina-native-base-extras-${name})

    target_include_directories(mapvina-native-base-extras-${name} SYSTEM INTERFACE
        ${include_path}
    )

    target_link_libraries(mapvina-native-base-extras INTERFACE mapvina-native-base-extras-${name})
endfunction()

add_library(mapvina-native-base-extras INTERFACE)
add_library(MapVinaNative::Base::Extras ALIAS mapvina-native-base-extras)

mapvina_native_base_extras_add_library(args ${CMAKE_CURRENT_LIST_DIR}/args)
mapvina_native_base_extras_add_library(filesystem ${CMAKE_CURRENT_LIST_DIR}/filesystem/include)
mapvina_native_base_extras_add_library(expected-lite ${CMAKE_CURRENT_LIST_DIR}/expected-lite/include)
mapvina_native_base_extras_add_library(kdbush.hpp ${CMAKE_CURRENT_LIST_DIR}/kdbush.hpp/include)

include(${PROJECT_SOURCE_DIR}/extras/rapidjson.cmake)
