package io.github.bstdoom.buildlogic

import org.gradle.api.DefaultTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.CacheableTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputDirectory
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.PathSensitive
import org.gradle.api.tasks.PathSensitivity
import org.gradle.api.tasks.TaskAction
import org.gradle.kotlin.dsl.register
import groovy.json.JsonOutput
import groovy.json.JsonSlurper
import java.io.File

class SiteContentPlugin : Plugin<Project> {
  override fun apply(project: Project) {
    val generatedDir = project.layout.buildDirectory.dir("generated/site-content/src/jsMain/kotlin")

    project.tasks.register<GenerateReadmeContentSourceTask>("generateReadmeContentSource") {
      inputFile.set(project.rootProject.layout.projectDirectory.file("README.md"))
      outputFile.set(generatedDir.map { it.file("io/github/bstdoom/generated/ReadmeContentData.kt") })
    }

    project.tasks.register<GenerateJsonSourceTask>("generateHomeLinksSource") {
      inputFile.set(project.layout.projectDirectory.file("src/jsMain/resources/data/home-links.json"))
      outputFile.set(generatedDir.map { it.file("io/github/bstdoom/generated/HomeLinksData.kt") })
      constName.set("HOME_LINKS_JSON")
    }

    project.tasks.register<GenerateJsonSourceTask>("generateBandMembersSource") {
      inputFile.set(project.layout.projectDirectory.file("src/jsMain/resources/data/band-members.json"))
      outputFile.set(generatedDir.map { it.file("io/github/bstdoom/generated/BandMembersData.kt") })
      constName.set("BAND_MEMBERS_JSON")
    }

    project.tasks.register<GenerateJsonSourceTask>("generateAboutSource") {
      inputFile.set(project.layout.projectDirectory.file("src/jsMain/resources/data/about.json"))
      outputFile.set(generatedDir.map { it.file("io/github/bstdoom/generated/AboutData.kt") })
      constName.set("ABOUT_JSON")
    }

    project.tasks.register<GenerateReleaseIndexSourceTask>("generateReleaseIndexSource") {
      inputDirectory.set(project.layout.projectDirectory.dir("src/jsMain/resources/data/releases"))
      outputFile.set(generatedDir.map { it.file("io/github/bstdoom/generated/ReleaseIndexData.kt") })
      constName.set("RELEASE_INDEX_JSON")
    }

    val releasesRoot = File(project.projectDir, "src/jsMain/resources/data/releases")
    releasesRoot.listFiles()
      ?.filter { it.isDirectory }
      ?.sortedBy { it.name }
      ?.forEach { releaseDir ->
        val slug = releaseDir.name
        val constantBase = slug.toConstantBase()
        val camelBase = slug.toCamelBase()
        val releaseJson = File(releaseDir, "release.json")
        val lyricsDir = File(releaseDir, "lyrics")

        if (releaseJson.isFile) {
          project.tasks.register<GenerateJsonSourceTask>("generate${camelBase}ReleaseSource") {
            inputFile.set(project.layout.projectDirectory.file("src/jsMain/resources/data/releases/$slug/release.json"))
            outputFile.set(generatedDir.map { it.file("io/github/bstdoom/generated/${camelBase}ReleaseData.kt") })
            constName.set("${constantBase}_RELEASE_JSON")
          }
        }

        if (lyricsDir.isDirectory) {
          project.tasks.register<GenerateMarkdownSectionsTask>("generate${camelBase}LyricsSectionsSource") {
            inputDirectory.set(project.layout.projectDirectory.dir("src/jsMain/resources/data/releases/$slug/lyrics"))
            outputFile.set(generatedDir.map { it.file("io/github/bstdoom/generated/${camelBase}LyricsSectionsData.kt") })
            constName.set("${constantBase}_LYRICS_SECTIONS")
          }
        }
      }

    project.tasks.matching { it.name == "compileKotlinJs" }.configureEach {
      dependsOn(project.tasks.matching { task ->
        task.name.startsWith("generate") && task.name.endsWith("Source")
      })
    }

    project.tasks.matching { it.name == "kspKotlinJs" }.configureEach {
      dependsOn(project.tasks.matching { task ->
        task.name.startsWith("generate") && task.name.endsWith("Source")
      })
    }
  }
}

@CacheableTask
abstract class GenerateReadmeContentSourceTask : DefaultTask() {
  @get:InputFile
  @get:PathSensitive(PathSensitivity.RELATIVE)
  abstract val inputFile: RegularFileProperty

  @get:OutputFile
  abstract val outputFile: RegularFileProperty

  @TaskAction
  fun generate() {
    val markdown = inputFile.get().asFile.readText()
    val sections = parseContentSections(markdown)

    val renderedEntries = sections.entries.joinToString(",\n") { (key, value) ->
      "    ${toKotlinRawString(key)} to ${toKotlinRawString(value)}"
    }

    val output = outputFile.get().asFile
    output.parentFile.mkdirs()
    output.writeText(
      buildString {
        appendLine("package io.github.bstdoom.generated")
        appendLine()
        appendLine("object ReadmeContent {")
        appendLine("  private val sections: Map<String, String> = mapOf(")
        if (renderedEntries.isNotBlank()) {
          appendLine(renderedEntries)
        }
        appendLine("  )")
        appendLine()
        appendLine("  operator fun get(key: String): String =")
        appendLine("    sections[key]")
        appendLine("      ?: sections.entries.firstOrNull { it.key.equals(key, ignoreCase = true) }?.value")
        appendLine("      ?: \"\"")
        appendLine("}")
      }
    )
  }

  private fun parseContentSections(markdown: String): Map<String, String> {
    val lines = markdown.lines()
    val contentHeaderIndex = lines.indexOfFirst {
      it.trim().equals("## Content", ignoreCase = true)
    }
    if (contentHeaderIndex == -1) return emptyMap()

    val result = linkedMapOf<String, String>()
    var currentSubheading: String? = null
    val currentLines = mutableListOf<String>()

    fun flush() {
      val heading = currentSubheading ?: return
      result[heading] = currentLines.joinToString("\n").trim()
      currentLines.clear()
    }

    for (i in (contentHeaderIndex + 1) until lines.size) {
      val line = lines[i]
      val trimmed = line.trim()
      if (trimmed.startsWith("## ") && !trimmed.startsWith("### ")) {
        // Hit next h2 section, stop parsing content
        break
      }

      if (trimmed.startsWith("### ")) {
        flush()
        currentSubheading = trimmed.removePrefix("### ").trim()
      } else if (currentSubheading != null) {
        currentLines.add(line)
      }
    }
    flush()

    return result
  }

  private fun toKotlinRawString(value: String): String {
    return "\"\"\"" + value.replace("\"\"\"", "\\\"\\\"\\\"") + "\"\"\""
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

@CacheableTask
abstract class GenerateMarkdownSectionsTask : DefaultTask() {
  @get:InputDirectory
  @get:PathSensitive(PathSensitivity.RELATIVE)
  abstract val inputDirectory: DirectoryProperty

  @get:OutputFile
  abstract val outputFile: RegularFileProperty

  @get:Input
  abstract val constName: Property<String>

  @TaskAction
  fun generate() {
    val directory = inputDirectory.get().asFile
    val markdownFiles = directory.walkTopDown()
      .filter { it.isFile && it.extension.lowercase() == "md" }
      .sortedBy { it.relativeTo(directory).path }
      .toList()

    val renderedSections = markdownFiles.joinToString(",\n") { file ->
      val (title, paragraphs) = parseMarkdownSong(file.readText(), file.nameWithoutExtension)
      val renderedParagraphs = paragraphs.joinToString(",\n") { paragraph ->
        "        ${toKotlinRawString(paragraph)}"
      }

      """
        ${toKotlinRawString(title)} to listOf(
${renderedParagraphs}
        )
      """.trimIndent()
    }

    val output = outputFile.get().asFile
    output.parentFile.mkdirs()
    output.writeText(
      buildString {
        appendLine("package io.github.bstdoom.generated")
        appendLine()
        appendLine("internal val ${constName.get()} = listOf(")
        appendLine(renderedSections)
        appendLine(")")
      }
    )
  }

  private fun parseMarkdownSong(markdownText: String, fallbackTitle: String): Pair<String, List<String>> {
    var title: String? = null
    var currentParagraphLines = mutableListOf<String>()
    val paragraphs = mutableListOf<String>()

    fun flushParagraph() {
      if (currentParagraphLines.isNotEmpty()) {
        paragraphs += currentParagraphLines.joinToString("\n")
        currentParagraphLines = mutableListOf()
      }
    }

    markdownText.lineSequence().forEach { line ->
      when {
        line.startsWith("# ") || line.startsWith("## ") -> {
          if (title == null) {
            title = line.removePrefix("# ").removePrefix("## ").trim()
          }
        }
        line.isBlank() -> flushParagraph()
        else -> currentParagraphLines.add(line)
      }
    }
    flushParagraph()

    return (title ?: prettifyTitle(fallbackTitle)) to paragraphs
  }

  private fun prettifyTitle(fallbackTitle: String): String {
    return fallbackTitle
      .replace('-', ' ')
      .replace('_', ' ')
      .replace(Regex("\\b\\d+\\b"), "")
      .trim()
      .split(Regex("\\s+"))
      .joinToString(" ") { word ->
        word.lowercase().replaceFirstChar { char ->
          if (char.isLowerCase()) char.titlecase() else char.toString()
        }
      }
  }

  private fun toKotlinRawString(value: String): String {
    return "\"\"\"" + value.replace("\"\"\"", "\\\"\\\"\\\"") + "\"\"\""
  }
}

@CacheableTask
abstract class GenerateReleaseIndexSourceTask : DefaultTask() {
  @get:InputDirectory
  @get:PathSensitive(PathSensitivity.RELATIVE)
  abstract val inputDirectory: DirectoryProperty

  @get:OutputFile
  abstract val outputFile: RegularFileProperty

  @get:Input
  abstract val constName: Property<String>

  @TaskAction
  fun generate() {
    val directory = inputDirectory.get().asFile
    val releaseDirs = directory.listFiles()
      ?.filter { it.isDirectory }
      ?.sortedBy { it.name }
      .orEmpty()

    val slurper = JsonSlurper()
    val entries = releaseDirs.mapNotNull { releaseDir ->
      val releaseFile = File(releaseDir, "release.json")
      if (!releaseFile.isFile) {
        return@mapNotNull null
      }

      val raw = slurper.parse(releaseFile) as Map<*, *>
      linkedMapOf(
        "slug" to releaseDir.name,
        "title" to requireString(raw, "title", releaseFile),
        "coverImage" to requireString(raw, "coverImage", releaseFile),
        "year" to requireInt(raw, "year", releaseFile),
        "releaseType" to requireString(raw, "releaseType", releaseFile),
        "showInNavigation" to requireBoolean(raw, "showInNavigation", defaultValue = true),
      )
    }

    val output = outputFile.get().asFile
    output.parentFile.mkdirs()
    output.writeText(
      buildString {
        appendLine("package io.github.bstdoom.generated")
        appendLine()
        appendLine("internal const val ${constName.get()} = \"\"\"")
        appendLine(JsonOutput.toJson(entries))
        appendLine("\"\"\"")
      }
    )
  }

  private fun requireString(raw: Map<*, *>, key: String, file: File): String {
    return raw[key]?.toString() ?: error("Missing '$key' in ${file.path}")
  }

  private fun requireInt(raw: Map<*, *>, key: String, file: File): Int {
    return when (val value = raw[key]) {
      is Number -> value.toInt()
      is String -> value.toIntOrNull() ?: error("Invalid '$key' in ${file.path}: $value")
      else -> error("Missing '$key' in ${file.path}")
    }
  }

  private fun requireBoolean(raw: Map<*, *>, key: String, defaultValue: Boolean): Boolean {
    return when (val value = raw[key]) {
      is Boolean -> value
      null -> defaultValue
      else -> value.toString().toBooleanStrictOrNull() ?: defaultValue
    }
  }
}


private fun String.toConstantBase(): String {
  return replace(Regex("[^A-Za-z0-9]+"), "_")
    .replace(Regex("_+"), "_")
    .trim('_')
    .uppercase()
}

private fun String.toCamelBase(): String {
  return split(Regex("[^A-Za-z0-9]+"))
    .filter { it.isNotBlank() }
    .joinToString("") { part ->
      part.lowercase().replaceFirstChar { char ->
        if (char.isLowerCase()) char.titlecase() else char.toString()
      }
    }
}
