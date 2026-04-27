package io.github.bstdoom.components

import androidx.compose.runtime.Composable
import io.github.bstdoom.components.releases.SidebarWidget
import org.jetbrains.compose.web.dom.*

data class DownloadItem(
  val title: String,
  val href: String,
  val thumbnail: String,
  val alt: String,
)

@Composable
fun DownloadsWidget(items: List<DownloadItem>) {
  SidebarWidget(title = "Dateien") {
    Div(attrs = { classes("downloads-list") }) {
      items.forEach { item ->
        Div(attrs = { classes("download-item") }) {
          A(href = item.href, attrs = { classes("download-thumb-link") }) {
            Img(
              src = item.thumbnail,
              attrs = {
                attr("alt", item.alt)
                classes("download-thumb")
              }
            )
          }
          Div(attrs = { classes("download-copy") }) {
            P {
              A(href = item.href) {
                Text(item.title)
              }
            }
          }
        }
      }
    }
  }
}
