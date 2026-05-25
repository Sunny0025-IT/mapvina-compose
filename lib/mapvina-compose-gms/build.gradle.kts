plugins {
  id("library-conventions")
  id("android-library-conventions")
  id(libs.plugins.kotlin.multiplatform.get().pluginId)
  id(libs.plugins.kotlin.composeCompiler.get().pluginId)
  id(libs.plugins.android.library.get().pluginId)
  id(libs.plugins.compose.get().pluginId)
  id(libs.plugins.mavenPublish.get().pluginId)
}

mavenPublishing {
  pom {
    name = "MapVina Compose Google Play Services"
    description = "Google Play Services extensions for MapVina Compose."
    url = "https://github.io/github/mapvina/mapvina-compose"
  }
}

kotlin {
  androidLibrary { namespace = "io.github.mapvina.compose.gms" }

  applyDefaultHierarchyTemplate()

  sourceSets {
    commonMain.dependencies {
      api(libs.alchemist)
      implementation(libs.jetbrains.compose.material3)
      implementation(libs.jetbrains.compose.components.resources)
      implementation(libs.bytesize)
      implementation(libs.htmlConverterCompose)
      api(project(":lib:mapvina-compose"))
    }

    androidMain.dependencies {
      implementation(libs.playServices.location)
      implementation(libs.kotlinx.coroutines.playServices)
    }

    commonTest.dependencies {
      implementation(kotlin("test"))
      implementation(kotlin("test-common"))
      implementation(kotlin("test-annotations-common"))
      implementation(libs.jetbrains.compose.ui.test)
    }

    androidHostTest.dependencies { implementation(compose.desktop.currentOs) }

    androidDeviceTest.dependencies {
      implementation(libs.jetbrains.compose.ui.testJunit4)
      implementation(libs.androidx.composeUi.testManifest)
    }
  }
}

compose.resources { packageOfResClass = "io.github.mapvina.compose.gms.generated" }
