plugins {
  id("library-conventions")
  id(libs.plugins.kotlin.multiplatform.get().pluginId)
  id(libs.plugins.mavenPublish.get().pluginId)
}

mavenPublishing {
  pom {
    name = "MapVina GL JS Bindings"
    description = "Kotlin bindings for MapVina GL JS."
    url = "https://github.io/github/mapvina/mapvina-compose"
  }
}

kotlin {
  js(IR) { browser() }

  sourceSets {
    commonMain.dependencies {
      implementation(kotlin("stdlib-js"))
      implementation(npm("mapvina-gl", libs.versions.mapvina.js.get()))
    }

    commonTest.dependencies {
      implementation(kotlin("test"))
      implementation(kotlin("test-common"))
      implementation(kotlin("test-annotations-common"))
    }
  }
}
