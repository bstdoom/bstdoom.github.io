import com.varabyte.kobweb.gradle.application.util.configAsKobwebApplication
import org.gradle.api.DefaultTask
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.CacheableTask
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.PathSensitive
import org.gradle.api.tasks.PathSensitivity
import org.gradle.api.tasks.TaskAction
plugins {
  alias(libs.plugins.kotlin.multiplatform)
  alias(libs.plugins.compose.compiler)
  alias(libs.plugins.kobweb.application)
  alias(libs.plugins.kobwebx.markdown)
}

group = "io.github.bstdoom"
version = "1.0-SNAPSHOT"

kobweb {
  app {
    index {
      description.set("Powered by Kobweb")
    }
  }
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
      implementation(libs.silk.icons.fa)
      implementation(libs.kobwebx.markdown)
      implementation(project(":worker"))
    }
    jvmMain.dependencies {
      compileOnly(libs.kobweb.api) // Provided by Kobweb backend at runtime
    }
  }
}

@CacheableTask
abstract class GenerateJsonSourceTask : DefaultTask() {
  @get:InputFile
  @get:PathSensitive(PathSensitivity.RELATIVE)
  abstract val inputFile: RegularFileProperty

  @get:OutputFile
  abstract val outputFile: RegularFileProperty

  @get:Input
  abstract val constName: Property<String>

  @TaskAction
  fun generate() {
    val json = inputFile.get().asFile.readText()
    val escapedJson = json.replace("\"\"\"", "\\\"\\\"\\\"")
    val output = outputFile.get().asFile
    output.parentFile.mkdirs()
    output.writeText(
      buildString {
        appendLine("package io.github.bstdoom.generated")
        appendLine()
        appendLine("internal const val ${constName.get()} = \"\"\"")
        appendLine(escapedJson)
        appendLine("\"\"\"")
      }
    )
  }
}

val homeLinksSource = layout.projectDirectory.file("src/jsMain/resources/data/home-links.json")
val bandMembersSource = layout.projectDirectory.file("src/jsMain/resources/data/band-members.json")
val generatedHomeLinksDir = layout.buildDirectory.dir("generated/home-links/src/jsMain/kotlin")
val generatedHomeLinksFile = generatedHomeLinksDir.map {
  it.file("io/github/bstdoom/generated/HomeLinksData.kt")
}
val generatedBandMembersFile = generatedHomeLinksDir.map {
  it.file("io/github/bstdoom/generated/BandMembersData.kt")
}

val generateHomeLinksSource = tasks.register<GenerateJsonSourceTask>("generateHomeLinksSource") {
  inputFile.set(homeLinksSource)
  outputFile.set(generatedHomeLinksFile)
  constName.set("HOME_LINKS_JSON")
}

val generateBandMembersSource = tasks.register<GenerateJsonSourceTask>("generateBandMembersSource") {
  inputFile.set(bandMembersSource)
  outputFile.set(generatedBandMembersFile)
  constName.set("BAND_MEMBERS_JSON")
}

kotlin.sourceSets.named("jsMain") {
  kotlin.srcDir(generatedHomeLinksDir)
}

tasks.named("compileKotlinJs") {
  dependsOn(generateHomeLinksSource)
  dependsOn(generateBandMembersSource)
}

tasks.matching { it.name == "kspKotlinJs" }.configureEach {
  dependsOn(generateHomeLinksSource)
  dependsOn(generateBandMembersSource)
}
