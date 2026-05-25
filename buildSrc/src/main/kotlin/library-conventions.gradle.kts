plugins {
  id("module-conventions")
  id("org.jetbrains.kotlin.multiplatform")
  id("org.jetbrains.kotlin.plugin.serialization")
  id("org.jetbrains.dokka")
  id("maven-publish")
}

kotlin {
  explicitApi()

  jvmToolchain(properties["jvmToolchain"]!!.toString().toInt())

  compilerOptions {
    // KLIB resolver: The same 'unique_name=annotation_commonMain' found in more than one library
    allWarningsAsErrors = false
    freeCompilerArgs.addAll("-Xexpect-actual-classes", "-Xconsistent-data-class-copy-visibility")
  }
}

dokka {
  dokkaSourceSets {
    configureEach {
      includes.from("MODULE.md")
      sourceLink {
        remoteUrl("https://github.io/github/mapvina/mapvina-compose/tree/${project.ext["base_tag"]}/")
        localDirectory.set(rootDir)
      }
      externalDocumentationLinks {
        create("spatial-k") { url("https://mapvina.com/spatial-k/api/") }
        create("mapvina-native") {
          url("https://mapvina.io/github/mapvina-native/android/api/")
          packageListUrl(
            "https://mapvina.io/github/mapvina-native/android/api/-map-libre%20-native%20-android/package-list"
          )
        }
      }
    }
  }
}

publishing {
  repositories {
    maven {
      name = "GitHubPackages"
      setUrl("https://maven.pkg.github.io/github/mapvina/mapvina-compose")
      credentials {
        username = project.properties["githubUser"]?.toString()
        password = project.properties["githubToken"]?.toString()
      }
    }
  }
}
