package io.github.bstdoom.components

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.dom.*

@Composable
fun StaticPageLayout(
  title: String,
  sections: @Composable () -> Unit,
) {
  Section(attrs = { classes("bg", "site-panel", "static-page") }) {
    H2(attrs = { classes("heading") }) {
      Text(title)
    }
    Div(attrs = { classes("static-page-content") }) {
      sections()
    }
  }
}

@Composable
fun StaticTextSection(
  title: String,
  paragraphs: List<String>,
) {
  Div(attrs = { classes("static-section") }) {
    H3(attrs = { classes("static-section-title") }) {
      Text(title)
    }
    paragraphs.forEach { paragraph ->
      P(attrs = { classes("static-paragraph") }) {
        Text(paragraph)
      }
    }
  }
}

@Composable
fun StaticListSection(
  title: String,
  intro: String? = null,
  items: List<String>,
) {
  Div(attrs = { classes("static-section") }) {
    H3(attrs = { classes("static-section-title") }) {
      Text(title)
    }
    intro?.let {
      P(attrs = { classes("static-paragraph") }) {
        Text(it)
      }
    }
    Ul(attrs = { classes("static-list") }) {
      items.forEach { item ->
        org.jetbrains.compose.web.dom.Li {
          Text(item)
        }
      }
    }
  }
}
