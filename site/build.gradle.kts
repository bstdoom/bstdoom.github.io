import com.varabyte.kobweb.gradle.application.util.configAsKobwebApplication
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.targets.js.yarn.YarnPlugin
import org.jetbrains.kotlin.gradle.targets.js.yarn.YarnRootExtension
import kotlinx.html.link

plugins {
  alias(libs.plugins.kotlin.multiplatform)
  alias(libs.plugins.compose.compiler)
  alias(libs.plugins.kobweb.application)
  alias(libs.plugins.kobwebx.markdown)
  id("io.github.bstdoom.site-content")
}

group = "io.github.bstdoom"
version = "1.0-SNAPSHOT"

kobweb {
  app {
    index {
      description.set("B.S.T. - Hamburg City Doom")
      head.add {
        link {
          attributes["id"] = "bstdoom-font-awesome-css"
          rel = "stylesheet"
          href = "/css/font-awesome.min.css"
        }
        link {
          attributes["id"] = "bstdoom-theme-css"
          rel = "stylesheet"
          href = "/css/theme.css"
        }
      }
    }
  }
}


rootProject.plugins.withType<YarnPlugin> {
  rootProject.extensions.getByType<YarnRootExtension>().lockFileDirectory = rootProject.file("gradle/kotlin-js-store")
}


kotlin {
  configAsKobwebApplication("bstdoom", includeServer = true)

  sourceSets {
//        commonMain.dependencies {
//          // Add shared dependencies between JS and JVM here
//        }
    jsMain.dependencies {
      implementation(libs.compose.runtime)
      implementation(libs.compose.html.core)
      implementation(libs.kobweb.core)
      implementation(libs.kobweb.silk)
      implementation(libs.kobwebx.markdown)
      implementation(project(":worker"))
    }
    jvmMain.dependencies {
      compileOnly(libs.kobweb.api) // Provided by Kobweb backend at runtime
    }
  }
}

kotlin.sourceSets.named("jsMain") {
  kotlin.srcDir(layout.buildDirectory.dir("generated/site-content/src/jsMain/kotlin"))
}
