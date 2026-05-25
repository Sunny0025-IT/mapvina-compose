plugins {
  id("library-conventions")
  id("android-library-conventions")
  id(libs.plugins.kotlin.multiplatform.get().pluginId)
  id(libs.plugins.kotlin.composeCompiler.get().pluginId)
  id(libs.plugins.android.library.get().pluginId)
  id(libs.plugins.compose.get().pluginId)
  id(libs.plugins.mavenPublish.get().pluginId)
  id(libs.plugins.spmForKmp.get().pluginId)
}

mavenPublishing {
  pom {
    name = "MapVina Compose"
    description = "Add interactive vector tile maps to your Compose app"
    url = "https://github.io/github/mapvina/mapvina-compose"
  }
}

kotlin {
  androidLibrary { namespace = "io.github.mapvina.compose" }

  listOf(iosArm64(), iosSimulatorArm64()).forEach {
    it.compilations.getByName("main") {
      cinterops {
        create("observer") {
          defFile(project.file("src/nativeInterop/cinterop/observer.def"))
          packageName("io.github.mapvina.compose.util")
        }
      }
    }
    it.configureSpmMapvina(project)
  }

  jvm("desktop") { compilerOptions { jvmTarget = project.getJvmTarget() } }

  js(IR) { browser() }

  applyDefaultHierarchyTemplate()

  sourceSets {
    val desktopMain by getting

    listOf(iosMain, iosArm64Main, iosSimulatorArm64Main).forEach {
      it { languageSettings { optIn("kotlinx.cinterop.ExperimentalForeignApi") } }
    }

    commonMain.dependencies {
      implementation(libs.jetbrains.compose.foundation)
      implementation(libs.jetbrains.compose.components.resources)
      implementation(libs.lifecycle.runtime.compose)
      api(libs.kermit)
      api(libs.spatialk.geojson)
      api(libs.spatialk.units)
    }

    // used to share some implementation on targets where Compose UI is backed by Skia directly
    // (e.g. all but Android, which is backed by the Android Canvas API)
    create("skiaMain") {
      dependsOn(commonMain.get())
      desktopMain.dependsOn(this)
      iosMain.get().dependsOn(this)
      jsMain.get().dependsOn(this)
    }

    // used to expose APIs only available on targets backed by MapVina Native
    // (e.g. all but browser targets, which use MapVina JS)
    create("mapvinaNativeMain") {
      dependsOn(commonMain.get())
      androidMain.get().dependsOn(this)
      iosMain.get().dependsOn(this)
      // TODO: when we're ready to support the offline manager on desktop
      // desktopMain.dependsOn(this)
    }

    iosMain {}

    androidMain {
      dependencies {
        api(libs.mapvina.android)
        implementation(libs.mapvina.android.scalebar)
      }
    }

    desktopMain.apply {
      dependencies {
        implementation(compose.desktop.currentOs)
        implementation(libs.kotlinx.coroutines.swing)
        implementation(project(":lib:mapvina-native-bindings"))
      }
    }

    jsMain { dependencies { implementation(project(":lib:mapvina-js-bindings")) } }

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

compose.resources { packageOfResClass = "io.github.mapvina.compose.generated" }
