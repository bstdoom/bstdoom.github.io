package io.github.bstdoom.buildlogic.task

import io.github.bstdoom.buildlogic.task.releaseRoutes
import io.github.bstdoom.buildlogic.task.releaseSlugs
import org.gradle.api.DefaultTask
import org.gradle.api.file.ConfigurableFileCollection
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.tasks.CacheableTask
import org.gradle.api.tasks.InputDirectory
import org.gradle.api.tasks.OutputFiles
import org.gradle.api.tasks.PathSensitive
import org.gradle.api.tasks.PathSensitivity
import org.gradle.api.tasks.TaskAction

@CacheableTask
abstract class GenerateReleasePagesTask : DefaultTask() {
  @get:InputDirectory
  @get:PathSensitive(PathSensitivity.RELATIVE)
  abstract val lyricsDirectory: DirectoryProperty

  @get:OutputFiles
  abstract val generatedFiles: ConfigurableFileCollection

  @TaskAction
  fun generate() {
    releaseSlugs.forEach { slug ->
      val lyricDirectory = lyricsDirectory.get().dir(slug).asFile
      val lyricFiles = lyricDirectory.listFiles()
        .orEmpty()
        .filter { it.extension == "md" }
        .sortedBy { it.name }
      val route = releaseRoutes.getValue(slug)
      val target = generatedFiles.files.first { it.path.endsWith("$route/index.md") }
      target.parentFile.mkdirs()
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
        },
      )
    }
  }
}
