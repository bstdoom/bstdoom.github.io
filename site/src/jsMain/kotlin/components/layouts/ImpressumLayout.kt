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
    AddressSidebarSection(frontMatter.getValue("sidebar.address"))
    StaticSidebarSection(
      title = "Vertreten durch",
      lines = frontMatter.getValue("sidebar.representatives"),
    )
    ContactSidebarSection(frontMatter)
  }
}

@Composable
private fun StaticSidebarSection(
  title: String,
  lines: List<String>,
) {
  Div(attrs = { classes("static-section") }) {
    H3(attrs = { classes("static-section-title") }) {
      Text(title)
    }
    Ul(attrs = { classes("static-list") }) {
      lines.forEach { line ->
        Li { Text(line) }
      }
    }
  }
}

@Composable
private fun AddressSidebarSection(lines: List<String>) {
  Div(attrs = { classes("static-section") }) {
    H3(attrs = { classes("static-section-title") }) {
      Text("Adresse")
    }
    Div(attrs = { classes("impressum-address") }) {
      lines.forEachIndexed { index, line ->
        if (index > 0) {
          Br()
        }
        Text(line)
      }
    }
  }
}

@Composable
private fun ContactSidebarSection(frontMatter: FrontMatterElement.ValueMap) {
  val phone = frontMatter.query("sidebar.contact.phone")?.scalarOrNull()
  val email = frontMatter.query("sidebar.contact.email")?.scalarOrNull()

  Div(attrs = { classes("static-section") }) {
    H3(attrs = { classes("static-section-title") }) {
      Text("Kontakt")
    }
    Ul(attrs = { classes("static-list", "impressum-contact") }) {
      phone?.let {
        Li {
          Text("Telefon: ")
          A(href = "tel:" + it.replace(" ", "")) {
            Text(it)
          }
        }
      }
      email?.let {
        Li {
          Text("E-Mail: ")
          A(href = "mailto:$it") {
            Text(it)
          }
        }
      }
    }
  }
}
