#pragma once

#include <stdexcept>

namespace mapvina_jni {

inline void check(bool condition, const char* message) {
  if (!condition) throw std::runtime_error(message);
}

}  // namespace mapvina_jni
