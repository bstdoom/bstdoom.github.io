package io.github.bstdoom.buildlogic.task

import org.gradle.api.DefaultTask
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.CacheableTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.PathSensitive
import org.gradle.api.tasks.PathSensitivity
import org.gradle.api.tasks.TaskAction

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
