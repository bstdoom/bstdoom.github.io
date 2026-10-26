import java.awt.RenderingHints
import java.awt.image.BufferedImage
import javax.imageio.ImageIO

plugins {
  alias(libs.plugins.kotlin.jvm)
  alias(libs.plugins.quarkus)
}

group = "io.github.bstdoom"
version = "0.1.0-SNAPSHOT"

val javaVersion = rootProject.file(".java-version").readText().trim().substringBefore('.').toInt()

java {
  toolchain {
    languageVersion = JavaLanguageVersion.of(javaVersion)
  }
}

quarkus {
  if (gradle.startParameter.taskNames.any {
      it == "roqGenerate" || it.endsWith(":roqGenerate") ||
        it == "roqServe" || it.endsWith(":roqServe")
    }) {
    quarkusBuildProperties.put("quarkus.roq.generator.batch", "true")
  }
}

repositories {
  mavenCentral()
}

val hostOperatingSystem = when {
  System.getProperty("os.name").lowercase().contains("mac") -> OperatingSystemFamily.MACOS
  System.getProperty("os.name").lowercase().contains("win") -> OperatingSystemFamily.WINDOWS
  else -> OperatingSystemFamily.LINUX
}

val hostArchitecture = when (System.getProperty("os.arch").lowercase()) {
  "aarch64", "arm64" -> MachineArchitecture.ARM64
  else -> MachineArchitecture.X86_64
}

configurations.configureEach {
  if (isCanBeResolved) {
    attributes {
      attribute(
        OperatingSystemFamily.OPERATING_SYSTEM_ATTRIBUTE,
        objects.named(hostOperatingSystem),
      )
      attribute(
        MachineArchitecture.ARCHITECTURE_ATTRIBUTE,
        objects.named(hostArchitecture),
      )
    }
  }
}

dependencies {
  implementation(enforcedPlatform(libs.quarkus.bom))
  implementation(libs.quarkus.kotlin)
  implementation(libs.quarkus.roq)
  implementation(libs.quarkus.vertx)
}

tasks.named<Test>("test") {
  useJUnitPlatform()
}

// Quarkus's Gradle code-generation task checks this conventional Java output
// directory even though this project intentionally contains Kotlin sources only.
val prepareQuarkusJavaOutput = tasks.register("prepareQuarkusJavaOutput") {
  val javaOutputDirectory = layout.buildDirectory.dir("classes/java/main")
  outputs.dir(javaOutputDirectory)
  doLast {
    javaOutputDirectory.get().asFile.mkdirs()
  }
}

tasks.matching { it.name.startsWith("quarkusGenerateCode") }.configureEach {
  dependsOn(prepareQuarkusJavaOutput)
}

val releaseSlugs = listOf(
  "die_illusion",
  "hamburg_city_doom",
  "herbst",
  "unter_deck",
  "vier_plus_zwei",
)

val releaseRoutes = mapOf(
  "die_illusion" to "die-illusion",
  "hamburg_city_doom" to "hamburg-city-doom",
  "herbst" to "herbst",
  "unter_deck" to "unter-deck",
  "vier_plus_zwei" to "vier-plus-zwei",
)

val generateReleasePages = tasks.register("generateReleasePages") {
  group = "roq"
  description = "Assemble release pages from the individual lyric Markdown files."
  notCompatibleWithConfigurationCache("release page assembly uses the project file model")
  inputs.dir(layout.projectDirectory.dir("lyrics"))
  outputs.files(releaseSlugs.map { slug -> layout.projectDirectory.file("content/musik/${releaseRoutes.getValue(slug)}.md") })
  doLast {
    releaseSlugs.forEach { slug ->
      val lyricDirectory = layout.projectDirectory.dir("lyrics/$slug").asFile
      val lyricFiles = lyricDirectory.listFiles()
        .orEmpty()
        .filter { it.extension == "md" }
        .sortedBy { it.name }
      val route = releaseRoutes.getValue(slug)
      val target = layout.projectDirectory.file("content/musik/$route.md").asFile
      val title = lyricFiles.firstOrNull()
        ?.readLines()
        ?.firstOrNull { it.startsWith("## ") }
        ?.removePrefix("## ")
        ?: slug
      target.writeText(
        buildString {
          appendLine("---")
          appendLine("title: $title")
          appendLine("layout: release")
          appendLine("release: $slug")
          appendLine("---")
          appendLine()
          lyricFiles.forEachIndexed { index, file ->
            if (index > 0) appendLine()
            appendLine(file.readText().trim())
          }
        }
      )
    }
  }
}

val releaseImageSources = mapOf(
  "die_illusion" to "die_illusion.jpg",
  "hamburg_city_doom" to "hamburg_city_doom.jpg",
  "herbst" to "herbst.jpg",
  "unter_deck" to "unter_deck.jpg",
  "vier_plus_zwei" to "vier_plus_zwei.jpg",
)

val generateReleaseThumbnails = tasks.register("generateReleaseThumbnails") {
  group = "roq"
  description = "Generate release-card thumbnails from the single full-size cover sources."
  notCompatibleWithConfigurationCache("thumbnail generation uses ImageIO and project files")
  inputs.files(releaseImageSources.values.map { fileName -> layout.projectDirectory.file("public/assets/releases/$fileName") })
  val thumbnailDirectory = layout.projectDirectory.dir("public/assets/releases/generated")
  outputs.dir(thumbnailDirectory)
  doLast {
    val outputDirectory = thumbnailDirectory.asFile
    outputDirectory.deleteRecursively()
    outputDirectory.mkdirs()
    releaseImageSources.forEach { (slug, fileName) ->
      val source = ImageIO.read(layout.projectDirectory.file("public/assets/releases/$fileName").asFile)
      val size = 360
      val thumbnail = BufferedImage(size, size, BufferedImage.TYPE_INT_RGB)
      val graphics = thumbnail.createGraphics()
      graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC)
      graphics.drawImage(source, 0, 0, size, size, null)
      graphics.dispose()
      ImageIO.write(thumbnail, "jpg", outputDirectory.resolve("$slug-thumb.jpg"))
    }
  }
}

tasks.named("quarkusBuild").configure {
  dependsOn(generateReleasePages, generateReleaseThumbnails)
}

tasks.named("quarkusDev").configure {
  dependsOn(generateReleasePages, generateReleaseThumbnails)
}

val roqCli = providers.gradleProperty("roq.cli")
  .orElse("${System.getProperty("user.home")}/.jbang/bin/roq")
  .get()

tasks.register<Exec>("roqGenerate") {
  group = "roq"
  description = "Generate the Roq site as static files under build/roq."
  dependsOn(tasks.named("quarkusBuild"))

  workingDir(project.projectDir)
  val roqJar = layout.buildDirectory.file("quarkus-app/quarkus-run.jar").get().asFile.absolutePath
  commandLine("java", "-jar", roqJar)
  // Keep internal generation isolated from the long-running dev server on 8082.
  environment("QUARKUS_HTTP_PORT", "18083")
}

tasks.register<Exec>("roqServe") {
  group = "roq"
  description = "Generate the Roq site and serve the generated static files."
  dependsOn(tasks.named("roqGenerate"))

  workingDir(project.projectDir)
  commandLine(roqCli, "serve", layout.buildDirectory.dir("roq").get().asFile.absolutePath)
}
