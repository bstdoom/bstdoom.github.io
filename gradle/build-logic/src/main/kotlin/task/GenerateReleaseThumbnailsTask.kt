package io.github.bstdoom.buildlogic.task

import java.awt.RenderingHints
import java.awt.image.BufferedImage
import javax.imageio.ImageIO
import org.gradle.api.DefaultTask
import org.gradle.api.file.ConfigurableFileCollection
import org.gradle.api.tasks.CacheableTask
import org.gradle.api.tasks.InputFiles
import org.gradle.api.tasks.OutputFiles
import org.gradle.api.tasks.PathSensitive
import org.gradle.api.tasks.PathSensitivity
import org.gradle.api.tasks.TaskAction

@CacheableTask
abstract class GenerateReleaseThumbnailsTask : DefaultTask() {
  @get:InputFiles
  @get:PathSensitive(PathSensitivity.RELATIVE)
  abstract val coverFiles: ConfigurableFileCollection

  @get:OutputFiles
  abstract val thumbnailFiles: ConfigurableFileCollection

  @TaskAction
  fun generate() {
    releaseSlugs.forEach { slug ->
      val source = coverFiles.files.first { it.path.endsWith("${releaseRoutes.getValue(slug)}/cover.jpg") }
      val target = thumbnailFiles.files.first { it.path.endsWith("${releaseRoutes.getValue(slug)}/cover-thumb.jpg") }
      val sourceImage = ImageIO.read(source)
      val size = 360
      val thumbnail = BufferedImage(size, size, BufferedImage.TYPE_INT_RGB)
      val graphics = thumbnail.createGraphics()
      graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC)
      graphics.drawImage(sourceImage, 0, 0, size, size, null)
      graphics.dispose()
      target.parentFile.mkdirs()
      ImageIO.write(thumbnail, "jpg", target)
    }
  }
}
