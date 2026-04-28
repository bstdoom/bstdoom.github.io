package io.github.bstdoom.components.layouts

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.core.PageContext
import com.varabyte.kobweb.core.layout.Layout
import com.varabyte.kobwebx.markdown.markdown
import io.github.bstdoom.components.SiteScaffold
import io.github.bstdoom.components.StaticPageLayout
import org.jetbrains.compose.web.dom.*

@Layout
@Composable
fun EnglishLayout(ctx: PageContext, content: @Composable () -> Unit) {
  val markdown = ctx.markdown ?: error("English layout can only be used from a markdown page")
  val title = markdown.frontMatter["title"]?.single() ?: "English"

  SiteScaffold {
    StaticPageLayout(title = title) {
      Div(attrs = { classes("entry-content") }) {
        content()
      }
    }
  }
}
