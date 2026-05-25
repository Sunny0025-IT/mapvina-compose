import ru.vyarus.gradle.plugin.mkdocs.task.MkdocsTask

plugins {
  id(libs.plugins.dokka.get().pluginId)
  id(libs.plugins.mkdocs.get().pluginId)
  id("module-conventions")
}

mkdocs {
  sourcesDir = "docs"
  strict = true
  publish {
    docPath = null // single version site
  }
}

tasks.withType<MkdocsTask>().configureEach {
  val releaseVersion = ext["base_tag"].toString().replace("v", "")
  val snapshotVersion = "${ext["next_patch_version"]}-SNAPSHOT"
  extras.set(
    mapOf(
      "release_version" to releaseVersion,
      "snapshot_version" to snapshotVersion,
      "mapvina_android_version" to libs.versions.mapvina.android.sdk.get(),
      "mapvina_ios_version" to project.properties["mapvinaIosVersion"]!!.toString(),
      "mapvina_js_version" to libs.versions.mapvina.js.get(),
    )
  )
}

dokka { moduleName = "MapVina Compose API Reference" }

tasks.register("generateDocs") {
  dependsOn("dokkaGenerate", "mkdocsBuild")
  doLast {
    copy {
      from(layout.buildDirectory.dir("mkdocs"))
      into(layout.buildDirectory.dir("docs"))
    }
    copy {
      from(layout.buildDirectory.dir("dokka/html"))
      into(layout.buildDirectory.dir("docs/api"))
    }
  }
}

dependencies {
  dokka(project(":lib:mapvina-native-bindings"))
  dokka(project(":lib:mapvina-js-bindings"))
  dokka(project(":lib:mapvina-compose"))
  dokka(project(":lib:mapvina-compose-material3"))
  dokka(project(":lib:mapvina-compose-gms"))
}
