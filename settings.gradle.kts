rootProject.name = "mapvina-compose-project"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
  repositories {
    google {
      @Suppress("UnstableApiUsage")
      mavenContent {
        includeGroupAndSubgroups("androidx")
        includeGroupAndSubgroups("com.android")
        includeGroupAndSubgroups("com.google")
      }
    }
    mavenCentral()
    gradlePluginPortal()
  }
}

dependencyResolutionManagement {
  @Suppress("UnstableApiUsage")
  repositories {
    google {
      mavenContent {
        includeGroupAndSubgroups("androidx")
        includeGroupAndSubgroups("com.android")
        includeGroupAndSubgroups("com.google")
      }
    }
    mavenCentral()
  }
}

// Versions: https://plugins.gradle.org/plugin/org.gradle.toolchains.foojay-resolver-convention
plugins { id("org.gradle.toolchains.foojay-resolver-convention") version ("1.0.0") }

include(
  ":",
  ":demo-app",
  ":lib",
  ":lib:mapvina-compose",
  ":lib:mapvina-compose-material3",
  ":lib:mapvina-native-bindings",
  ":lib:mapvina-native-bindings-jni",
  ":lib:mapvina-js-bindings",
  ":lib:mapvina-compose-gms",
)
