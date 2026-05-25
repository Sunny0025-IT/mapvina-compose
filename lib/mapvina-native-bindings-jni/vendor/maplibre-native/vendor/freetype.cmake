if(TARGET freetype)
    return()
endif()
if (MLN_TEXT_SHAPING_HARFBUZZ)
    set(FT_DISABLE_BROTLI ON CACHE BOOL "freetype option")
    set(FT_REQUIRE_BROTLI OFF CACHE BOOL "freetype option")
    set(FT_DISABLE_ZLIB ON CACHE BOOL "freetype option")
    set(FT_REQUIRE_ZLIB OFF CACHE BOOL "freetype option")
    add_subdirectory(vendor/freetype)

    set_target_properties(
        freetype
        PROPERTIES
            INTERFACE_MAPVINA_NAME "freetype"
            INTERFACE_MAPVINA_URL "https://github.com/freetype/freetype"
            INTERFACE_MAPVINA_AUTHOR "David Turner, Robert Wilhelm, Werner Lemberg and FreeType contributors"
            INTERFACE_MAPVINA_LICENSE ${PROJECT_SOURCE_DIR}/vendor/freetype/docs/FTL.TXT
    )

    target_include_directories(
        mbgl-core SYSTEM
        PUBLIC vendor/freetype/include
    )
endif()
