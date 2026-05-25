package io.github.mapvina.compose.util

import platform.UIKit.UIView

internal fun getSystemRefreshRate(view: UIView): Long {
  return view.window?.screen?.maximumFramesPerSecond ?: 0L
}
