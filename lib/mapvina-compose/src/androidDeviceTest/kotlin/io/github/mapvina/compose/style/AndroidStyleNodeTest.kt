package io.github.mapvina.compose.style

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.runAndroidComposeUiTest
import io.github.mapvina.android.MapVina

@OptIn(ExperimentalTestApi::class)
class AndroidStyleNodeTest : StyleNodeTest() {
  override fun platformSetup() =
    runAndroidComposeUiTest<ComponentActivity> {
      activity!!.runOnUiThread { MapVina.getInstance(activity!!) }
    }
}
