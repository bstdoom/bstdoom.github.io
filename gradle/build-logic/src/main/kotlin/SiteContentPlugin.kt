package io.github.bstdoom.buildlogic

import io.github.bstdoom.buildlogic.task.GenerateReleasePagesTask
import io.github.bstdoom.buildlogic.task.GenerateReleaseThumbnailsTask
import io.github.bstdoom.buildlogic.task.releaseRoutes
import io.github.bstdoom.buildlogic.task.releaseSlugs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.register

class SiteContentPlugin : Plugin<Project> {
  override fun apply(project: Project) {
    val contentDirectory = project.layout.projectDirectory.dir("content/musik")
    val projectLyricsDirectory = project.layout.projectDirectory.dir("data/lyrics")

    val releasePages = project.tasks.register<GenerateReleasePagesTask>("generateReleasePages") {
      lyricsDirectory.set(projectLyricsDirectory)
      generatedFiles.from(releaseSlugs.map { slug ->
        contentDirectory.file("${releaseRoutes.getValue(slug)}/index.md")
      })
    }

    val releaseThumbnails = project.tasks.register<GenerateReleaseThumbnailsTask>("generateReleaseThumbnails") {
      coverFiles.from(releaseSlugs.map { slug ->
        contentDirectory.file("${releaseRoutes.getValue(slug)}/cover.jpg")
      })
      thumbnailFiles.from(releaseSlugs.map { slug ->
        contentDirectory.file("${releaseRoutes.getValue(slug)}/cover-thumb.jpg")
      })
    }

    project.tasks.matching { it.name == "quarkusBuild" || it.name == "quarkusDev" }.configureEach {
      dependsOn(releasePages, releaseThumbnails)
    }
  }
}
