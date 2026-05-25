#include <mbgl/map/map.hpp>
#include <mbgl/map/map_options.hpp>
#include <mbgl/storage/file_source.hpp>
#include <mbgl/storage/file_source_manager.hpp>
#include <mbgl/storage/resource_options.hpp>
#include <mbgl/style/style.hpp>
#include <mbgl/util/client_options.hpp>

#include <jni.h>
#include <smjni/java_exception.h>

#include <MapVinaMap_class.h>

#include "conversions.hpp"
#include "java_classes.hpp"
#include "jni_map_observer.hpp"

#pragma mark - Helpers

struct MapWrapper {
  std::unique_ptr<mapvina_jni::JniMapObserver> observer;
  std::unique_ptr<mbgl::Map> map;

  MapWrapper(mbgl::Map* map, mapvina_jni::JniMapObserver* observer)
      : observer(observer), map(map) {}
};

template <typename Func>
auto withMapWrapper(JNIEnv* env, jMapVinaMap map, Func&& func)
  -> decltype(func(std::declval<MapWrapper*>())) {
  using ReturnType = decltype(func(std::declval<MapWrapper*>()));
  try {
    auto ptr =
      java_classes::get<MapVinaMap_class>().getNativePointer(env, map);
    // NOLINTNEXTLINE(cppcoreguidelines-pro-type-reinterpret-cast)
    auto* wrapper = reinterpret_cast<MapWrapper*>(ptr);
    return std::forward<Func>(func)(wrapper);
  } catch (const std::exception& e) {
    smjni::java_exception::translate(env, e);
    if constexpr (!std::is_void_v<ReturnType>) return ReturnType{};
  }
}

#pragma mark - Rendering

// TODO: wrap StillImageCallback
// using StillImageCallback = std::function<void(std::exception_ptr)>;
// void renderStill(StillImageCallback);
// void renderStill(const CameraOptions&, MapDebugOptions, StillImageCallback);

void JNICALL MapVinaMap_class::triggerRepaint(JNIEnv* env, jMapVinaMap map) {
  withMapWrapper(env, map, [](auto wrapper) {
    wrapper->map->triggerRepaint();
  });
}

#pragma mark - Style

// TODO: wrap style::Style
// style::Style& getStyle();
// const style::Style& getStyle() const;
// void setStyle(std::unique_ptr<style::Style>);

void JNICALL
MapVinaMap_class::loadStyleURL(JNIEnv* env, jMapVinaMap map, jstring url) {
  withMapWrapper(env, map, [env, url](auto wrapper) {
    wrapper->map->getStyle().loadURL(smjni::java_string_to_cpp(env, url));
  });
}

void JNICALL
MapVinaMap_class::loadStyleJSON(JNIEnv* env, jMapVinaMap map, jstring json) {
  withMapWrapper(env, map, [env, json](auto wrapper) {
    wrapper->map->getStyle().loadJSON(smjni::java_string_to_cpp(env, json));
  });
}

#pragma mark - Transitions

void JNICALL
MapVinaMap_class::cancelTransitions(JNIEnv* env, jMapVinaMap map) {
  withMapWrapper(env, map, [](auto wrapper) {
    wrapper->map->cancelTransitions();
  });
}

void JNICALL MapVinaMap_class::setGestureInProgressNative(
  JNIEnv* env, jMapVinaMap map, jboolean inProgress
) {
  withMapWrapper(env, map, [inProgress](auto wrapper) {
    wrapper->map->setGestureInProgress(inProgress != JNI_FALSE);
  });
}

auto JNICALL
MapVinaMap_class::isGestureInProgressNative(JNIEnv* env, jMapVinaMap map)
  -> jboolean {
  return withMapWrapper(env, map, [](auto wrapper) {
    return static_cast<jboolean>(wrapper->map->isGestureInProgress());
  });
}

auto JNICALL MapVinaMap_class::isRotatingNative(JNIEnv* env, jMapVinaMap map)
  -> jboolean {
  return withMapWrapper(env, map, [](auto wrapper) {
    return static_cast<jboolean>(wrapper->map->isRotating());
  });
}

auto JNICALL MapVinaMap_class::isScalingNative(JNIEnv* env, jMapVinaMap map)
  -> jboolean {
  return withMapWrapper(env, map, [](auto wrapper) {
    return static_cast<jboolean>(wrapper->map->isScaling());
  });
}

auto JNICALL MapVinaMap_class::isPanningNative(JNIEnv* env, jMapVinaMap map)
  -> jboolean {
  return withMapWrapper(env, map, [](auto wrapper) {
    return static_cast<jboolean>(wrapper->map->isPanning());
  });
}

#pragma mark - Camera

auto JNICALL MapVinaMap_class::getCameraOptions(JNIEnv* env, jMapVinaMap map)
  -> jCameraOptions {
  return withMapWrapper(env, map, [env](auto wrapper) {
    auto opts = wrapper->map->getCameraOptions();
    return mapvina_jni::convertCameraOptions(env, opts);
  });
}

void JNICALL MapVinaMap_class::jumpTo(
  JNIEnv* env, jMapVinaMap map, jCameraOptions cameraOptions
) {
  withMapWrapper(env, map, [env, cameraOptions](auto wrapper) {
    auto opts = mapvina_jni::convertCameraOptions(env, cameraOptions);
    wrapper->map->jumpTo(opts);
  });
}

void JNICALL MapVinaMap_class::easeTo(
  JNIEnv* env, jMapVinaMap map, jCameraOptions cameraOptions, jint duration
) {
  withMapWrapper(env, map, [env, cameraOptions, duration](auto wrapper) {
    auto opts = mapvina_jni::convertCameraOptions(env, cameraOptions);
    wrapper->map->easeTo(
      opts, mbgl::AnimationOptions{
              static_cast<mbgl::Duration>(std::chrono::milliseconds(duration))
            }
    );
  });
}

void JNICALL MapVinaMap_class::flyTo(
  JNIEnv* env, jMapVinaMap map, jCameraOptions cameraOptions, jint duration
) {
  withMapWrapper(env, map, [env, cameraOptions, duration](auto wrapper) {
    auto opts = mapvina_jni::convertCameraOptions(env, cameraOptions);
    wrapper->map->flyTo(
      opts, mbgl::AnimationOptions{
              static_cast<mbgl::Duration>(std::chrono::milliseconds(duration))
            }
    );
  });
}

void JNICALL MapVinaMap_class::moveBy(
  JNIEnv* env, jMapVinaMap map, jScreenCoordinate screenCoordinate
) {
  withMapWrapper(env, map, [env, screenCoordinate](auto wrapper) {
    auto coord = mapvina_jni::convertScreenCoordinate(env, screenCoordinate);
    wrapper->map->moveBy(coord);
  });
}

void JNICALL MapVinaMap_class::scaleBy(
  JNIEnv* env, jMapVinaMap map, jdouble scale, jScreenCoordinate anchor
) {
  withMapWrapper(env, map, [env, scale, anchor](auto wrapper) {
    auto anchorCoord = mapvina_jni::convertScreenCoordinate(env, anchor);
    wrapper->map->scaleBy(scale, anchorCoord);
  });
}

void JNICALL
MapVinaMap_class::pitchBy(JNIEnv* env, jMapVinaMap map, jdouble pitch) {
  withMapWrapper(env, map, [pitch](auto wrapper) {
    wrapper->map->pitchBy(pitch);
  });
}

void JNICALL MapVinaMap_class::rotateBy(
  JNIEnv* env, jMapVinaMap map, jScreenCoordinate first,
  jScreenCoordinate second
) {
  withMapWrapper(env, map, [env, first, second](auto wrapper) {
    auto firstCoord = mapvina_jni::convertScreenCoordinate(env, first);
    auto secondCoord = mapvina_jni::convertScreenCoordinate(env, second);
    wrapper->map->rotateBy(firstCoord, secondCoord);
  });
}

auto JNICALL MapVinaMap_class::cameraForLatLngBounds(
  JNIEnv* env, jMapVinaMap map, jLatLngBounds bounds, jEdgeInsets padding,
  jDouble bearing, jDouble pitch
) -> jCameraOptions {
  return withMapWrapper(
    env, map, [env, bounds, padding, bearing, pitch](auto wrapper) {
      auto cppBounds = mapvina_jni::convertLatLngBounds(env, bounds);
      auto cppPadding = mapvina_jni::convertEdgeInsets(env, padding);

      std::optional<double> cppBearing = std::nullopt;
      if (bearing != nullptr) {
        cppBearing =
          java_classes::get<Double_class>().doubleValue(env, bearing);
      }

      std::optional<double> cppPitch = std::nullopt;
      if (pitch != nullptr) {
        cppPitch = java_classes::get<Double_class>().doubleValue(env, pitch);
      }

      auto opts = wrapper->map->cameraForLatLngBounds(
        cppBounds, cppPadding, cppBearing, cppPitch
      );
      return mapvina_jni::convertCameraOptions(env, opts);
    }
  );
}

auto JNICALL MapVinaMap_class::latLngBoundsForCamera(
  JNIEnv* env, jMapVinaMap map, jCameraOptions camera
) -> jLatLngBounds {
  return withMapWrapper(env, map, [env, camera](auto wrapper) {
    auto cppCamera = mapvina_jni::convertCameraOptions(env, camera);
    auto bounds = wrapper->map->latLngBoundsForCamera(cppCamera);
    return mapvina_jni::convertLatLngBounds(env, bounds);
  });
}

auto JNICALL MapVinaMap_class::latLngBoundsForCameraUnwrapped(
  JNIEnv* env, jMapVinaMap map, jCameraOptions camera
) -> jLatLngBounds {
  return withMapWrapper(env, map, [env, camera](auto wrapper) {
    auto cppCamera = mapvina_jni::convertCameraOptions(env, camera);
    auto bounds = wrapper->map->latLngBoundsForCameraUnwrapped(cppCamera);
    return mapvina_jni::convertLatLngBounds(env, bounds);
  });
}

// TODO: wrap std::vector<LatLng>
// CameraOptions cameraForLatLngs(const std::vector<LatLng>&,
//   const EdgeInsets&,
//   const std::optional<double>& bearing = std::nullopt,
//   const std::optional<double>& pitch = std::nullopt) const;

// TODO: wrap Geometry<>
// CameraOptions cameraForGeometry(const Geometry<double>&,
//   const EdgeInsets&,
//   const std::optional<double>& bearing = std::nullopt,
//   const std::optional<double>& pitch = std::nullopt) const;

#pragma mark - Bounds

void JNICALL MapVinaMap_class::setBoundsNative(
  JNIEnv* env, jMapVinaMap map, jBoundOptions boundOptions
) {
  withMapWrapper(env, map, [env, boundOptions](auto wrapper) {
    auto opts = mapvina_jni::convertBoundOptions(env, boundOptions);
    wrapper->map->setBounds(opts);
  });
}

auto JNICALL MapVinaMap_class::getBoundsNative(JNIEnv* env, jMapVinaMap map)
  -> jBoundOptions {
  return withMapWrapper(env, map, [env](auto wrapper) {
    auto opts = wrapper->map->getBounds();
    return mapvina_jni::convertBoundOptions(env, opts);
  });
}

#pragma mark - Map Options

void JNICALL MapVinaMap_class::setNorthOrientationNative(
  JNIEnv* env, jMapVinaMap map, jNorthOrientation value
) {
  withMapWrapper(env, map, [env, value](auto wrapper) {
    jint nativeValue =
      java_classes::get<NorthOrientation_class>().getNativeValue(env, value);
    wrapper->map->setNorthOrientation(
      static_cast<mbgl::NorthOrientation>(nativeValue)
    );
  });
}

void JNICALL MapVinaMap_class::setConstrainModeNative(
  JNIEnv* env, jMapVinaMap map, jConstrainMode value
) {
  withMapWrapper(env, map, [env, value](auto wrapper) {
    jint nativeValue =
      java_classes::get<ConstrainMode_class>().getNativeValue(env, value);
    wrapper->map->setConstrainMode(
      static_cast<mbgl::ConstrainMode>(nativeValue)
    );
  });
}

void JNICALL MapVinaMap_class::setViewportModeNative(
  JNIEnv* env, jMapVinaMap map, jViewportMode value
) {
  withMapWrapper(env, map, [env, value](auto wrapper) {
    jint nativeValue =
      java_classes::get<ViewportMode_class>().getNativeValue(env, value);
    wrapper->map->setViewportMode(static_cast<mbgl::ViewportMode>(nativeValue));
  });
}

void JNICALL
MapVinaMap_class::setSize(JNIEnv* env, jMapVinaMap map, jSize size) {
  withMapWrapper(env, map, [env, size](auto wrapper) {
    auto cSize = mapvina_jni::convertSize(env, size);
    if (cSize.width > 0 && cSize.height > 0) wrapper->map->setSize(cSize);
  });
}

auto JNICALL
MapVinaMap_class::getMapOptionsNative(JNIEnv* env, jMapVinaMap map)
  -> jMapOptions {
  return withMapWrapper(env, map, [env](auto wrapper) {
    const mbgl::MapOptions opts = wrapper->map->getMapOptions();
    return mapvina_jni::convertMapOptions(env, opts);
  });
}

#pragma mark - Projection Mode

// TODO: wrap ProjectionMode
// void setProjectionMode(const ProjectionMode&);
// ProjectionMode getProjectionMode() const;

#pragma mark - Projection

auto JNICALL
MapVinaMap_class::pixelForLatLng(JNIEnv* env, jMapVinaMap map, jLatLng latLng)
  -> jScreenCoordinate {
  return withMapWrapper(env, map, [env, latLng](auto wrapper) {
    auto cLatLng = mapvina_jni::convertLatLng(env, latLng);
    return mapvina_jni::convertScreenCoordinate(
      env, wrapper->map->pixelForLatLng(cLatLng)
    );
  });
}

auto JNICALL MapVinaMap_class::latLngForPixel(
  JNIEnv* env, jMapVinaMap map, jScreenCoordinate pixel
) -> jLatLng {
  return withMapWrapper(env, map, [env, pixel](auto wrapper) {
    auto cPixel = mapvina_jni::convertScreenCoordinate(env, pixel);
    return mapvina_jni::convertLatLng(
      env, wrapper->map->latLngForPixel(cPixel)
    );
  });
}

// TODO: wrap std::vector<LatLng>
// std::vector<ScreenCoordinate> pixelsForLatLngs(const std::vector<LatLng>&)
// const; std::vector<LatLng> latLngsForPixels(const
//   std::vector<ScreenCoordinate>&) const;

#pragma mark - Transform

// TODO: wrap TransformState
// TransformState getTransfromState() const;

#pragma mark - Annotations

// TODO: wrap style::Image, Annotation, AnnotationID
// void addAnnotationImage(std::unique_ptr<style::Image>);
// void removeAnnotationImage(const std::string&);
// double getTopOffsetPixelsForAnnotationImage(const std::string&);
// AnnotationID addAnnotation(const Annotation&);
// void updateAnnotation(AnnotationID, const Annotation&);
// void removeAnnotation(AnnotationID);

#pragma mark - Tile prefetching

void JNICALL MapVinaMap_class::setPrefetchZoomDeltaNative(
  JNIEnv* env, jMapVinaMap map, jbyte delta
) {
  withMapWrapper(env, map, [delta](auto wrapper) {
    wrapper->map->setPrefetchZoomDelta(static_cast<uint8_t>(delta));
  });
}

auto JNICALL
MapVinaMap_class::getPrefetchZoomDeltaNative(JNIEnv* env, jMapVinaMap map)
  -> jbyte {
  return withMapWrapper(env, map, [](auto wrapper) {
    return static_cast<jbyte>(wrapper->map->getPrefetchZoomDelta());
  });
}

#pragma mark - Debug

void JNICALL MapVinaMap_class::setDebugNative(
  JNIEnv* env, jMapVinaMap map, jint debugOptions
) {
  withMapWrapper(env, map, [debugOptions](auto wrapper) {
    wrapper->map->setDebug(static_cast<mbgl::MapDebugOptions>(debugOptions));
  });
}

auto JNICALL MapVinaMap_class::getDebugNative(JNIEnv* env, jMapVinaMap map)
  -> jint {
  return withMapWrapper(env, map, [](auto wrapper) {
    return static_cast<jint>(wrapper->map->getDebug());
  });
}

auto JNICALL MapVinaMap_class::isRenderingStatsViewEnabledNative(
  JNIEnv* env, jMapVinaMap map
) -> jboolean {
  return withMapWrapper(env, map, [](auto wrapper) {
    return static_cast<jboolean>(wrapper->map->isRenderingStatsViewEnabled());
  });
}

void JNICALL MapVinaMap_class::enableRenderingStatsViewNative(
  JNIEnv* env, jMapVinaMap map, jboolean enabled
) {
  withMapWrapper(env, map, [enabled](auto wrapper) {
    wrapper->map->enableRenderingStatsView(enabled != JNI_FALSE);
  });
}

auto JNICALL
MapVinaMap_class::isFullyLoadedNative(JNIEnv* env, jMapVinaMap map)
  -> jboolean {
  return withMapWrapper(env, map, [](auto wrapper) {
    return static_cast<jboolean>(wrapper->map->isFullyLoaded());
  });
}

void JNICALL MapVinaMap_class::dumpDebugLogs(JNIEnv* env, jMapVinaMap map) {
  withMapWrapper(env, map, [](auto wrapper) { wrapper->map->dumpDebugLogs(); });
}

#pragma mark - Free Camera

// TODO: wrap FreeCameraOptions
// void setFreeCameraOptions(const FreeCameraOptions& camera);
// FreeCameraOptions getFreeCameraOptions() const;

#pragma mark - Tile LOD controls

void JNICALL MapVinaMap_class::setTileLodMinRadiusNative(
  JNIEnv* env, jMapVinaMap map, jdouble value
) {
  withMapWrapper(env, map, [value](auto wrapper) {
    wrapper->map->setTileLodMinRadius(value);
  });
}

auto JNICALL
MapVinaMap_class::getTileLodMinRadiusNative(JNIEnv* env, jMapVinaMap map)
  -> jdouble {
  return withMapWrapper(env, map, [](auto wrapper) {
    return wrapper->map->getTileLodMinRadius();
  });
}

void JNICALL MapVinaMap_class::setTileLodScaleNative(
  JNIEnv* env, jMapVinaMap map, jdouble value
) {
  withMapWrapper(env, map, [value](auto wrapper) {
    wrapper->map->setTileLodScale(value);
  });
}

auto JNICALL
MapVinaMap_class::getTileLodScaleNative(JNIEnv* env, jMapVinaMap map)
  -> jdouble {
  return withMapWrapper(env, map, [](auto wrapper) {
    return wrapper->map->getTileLodScale();
  });
}

void JNICALL MapVinaMap_class::setTileLodPitchThresholdNative(
  JNIEnv* env, jMapVinaMap map, jdouble value
) {
  withMapWrapper(env, map, [value](auto wrapper) {
    wrapper->map->setTileLodPitchThreshold(value);
  });
}

auto JNICALL
MapVinaMap_class::getTileLodPitchThresholdNative(JNIEnv* env, jMapVinaMap map)
  -> jdouble {
  return withMapWrapper(env, map, [](auto wrapper) {
    return wrapper->map->getTileLodPitchThreshold();
  });
}

void JNICALL MapVinaMap_class::setTileLodZoomShiftNative(
  JNIEnv* env, jMapVinaMap map, jdouble value
) {
  withMapWrapper(env, map, [value](auto wrapper) {
    wrapper->map->setTileLodZoomShift(value);
  });
}

auto JNICALL
MapVinaMap_class::getTileLodZoomShiftNative(JNIEnv* env, jMapVinaMap map)
  -> jdouble {
  return withMapWrapper(env, map, [](auto wrapper) {
    return wrapper->map->getTileLodZoomShift();
  });
}

#pragma mark - Other

// TODO: wrap ClientOptions
// ClientOptions getClientOptions() const;

// TODO: wrap ActionJournal
// const std::unique_ptr<util::ActionJournal>& getActionJournal();

#pragma mark - Allocation

auto JNICALL MapVinaMap_class::nativeInit(
  JNIEnv* env, jclass /*unused*/, jlong frontendPointer,
  jMapObserver observerObj, jMapOptions optionsObj,
  jResourceOptions resourceOptionsObj, jClientOptions clientOptionsObj
) -> jlong {
  try {
    // NOLINTNEXTLINE(cppcoreguidelines-pro-type-reinterpret-cast)
    auto* renderer = reinterpret_cast<mbgl::RendererFrontend*>(frontendPointer);
    auto observer = std::make_unique<mapvina_jni::JniMapObserver>(observerObj);
    mbgl::MapOptions mapOptions =
      mapvina_jni::convertMapOptions(env, optionsObj);
    mbgl::ResourceOptions resourceOptions =
      mapvina_jni::convertResourceOptions(env, resourceOptionsObj);
    mbgl::ClientOptions clientOptions =
      mapvina_jni::convertClientOptions(env, clientOptionsObj);
    auto map = std::make_unique<mbgl::Map>(
      *renderer, *observer, mapOptions, resourceOptions, clientOptions
    );

    // Get network file source for HTTP downloads
    std::shared_ptr<mbgl::FileSource> networkFileSource =
      mbgl::FileSourceManager::get()->getFileSource(
        mbgl::FileSourceType::Network, resourceOptions, clientOptions
      );

    // Get resource loader for request management
    std::shared_ptr<mbgl::FileSource> resourceLoader =
      mbgl::FileSourceManager::get()->getFileSource(
        mbgl::FileSourceType::ResourceLoader, resourceOptions, clientOptions
      );

    // Get database file source for caching
    std::shared_ptr<mbgl::FileSource> databaseFileSource =
      mbgl::FileSourceManager::get()->getFileSource(
        mbgl::FileSourceType::Database, resourceOptions, clientOptions
      );

    // NOLINTNEXTLINE(cppcoreguidelines-pro-type-reinterpret-cast)
    return reinterpret_cast<jlong>(
      new MapWrapper(map.release(), observer.release())
    );
  } catch (const std::exception& e) {
    smjni::java_exception::translate(env, e);
    return 0;
  }
}

void JNICALL
MapVinaMap_class::nativeDestroy(JNIEnv* env, jclass /*unused*/, jlong ptr) {
  try {
    // NOLINTNEXTLINE(cppcoreguidelines-pro-type-reinterpret-cast,cppcoreguidelines-owning-memory)
    delete reinterpret_cast<MapWrapper*>(ptr);
  } catch (const std::exception& e) {
    smjni::java_exception::translate(env, e);
  }
}
