package io.github.bstdoom.components.layouts

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.core.PageContext
import com.varabyte.kobweb.core.layout.Layout
import com.varabyte.kobwebx.markdown.markdown
import io.github.bstdoom.components.SiteScaffold
import com.varabyte.kobwebx.frontmatter.FrontMatterElement
import org.jetbrains.compose.web.dom.*

@Layout
@Composable
fun ImpressumLayout(ctx: PageContext, content: @Composable () -> Unit) {
  val markdown = ctx.markdown ?: error("Impressum layout can only be used from a markdown page")

  SiteScaffold {
    Section(attrs = { classes("release-detail-page") }) {
      Div(attrs = { classes("release-detail-grid") }) {
        Div(attrs = { classes("release-main-column") }) {
          Div(attrs = { classes("site-content", "bg", "site-panel") }) {
            content()
          }
        }
        Div(attrs = { classes("sidebar-inner") }) {
          Div(attrs = { classes("widget", "bg", "site-panel") }) {
            ImpressumSidebar(markdown.frontMatter)
          }
        }
      }
    }
  }
}

@Composable
private fun ImpressumSidebar(frontMatter: FrontMatterElement.ValueMap) {
  Div(attrs = { classes("static-page-content") }) {
    StaticSidebarSection(
      title = "Adresse",
      lines = frontMatter.getValue("sidebar.address"),
      renderAsPre = true,
    )
    StaticSidebarSection(
      title = "Vertreten durch",
      lines = frontMatter.getValue("sidebar.representatives"),
    )
    StaticSidebarSection(
      title = "Kontakt",
      lines = frontMatter.getValue("sidebar.contact"),
    )
  }
}

@Composable
private fun StaticSidebarSection(
  title: String,
  lines: List<String>,
  renderAsPre: Boolean = false,
) {
  Div(attrs = { classes("static-section") }) {
    H3(attrs = { classes("static-section-title") }) {
      Text(title)
    }
    if (renderAsPre) {
      Pre {
        Text(lines.joinToString("\n"))
      }
    } else {
      Ul(attrs = { classes("static-list") }) {
        lines.forEach { line ->
          Li { Text(line) }
        }
      }
    }
  }
}
