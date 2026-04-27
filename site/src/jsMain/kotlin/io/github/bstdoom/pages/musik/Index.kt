package io.github.bstdoom.pages.musik

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.varabyte.kobweb.core.Page
import io.github.bstdoom.components.SiteScaffold
import io.github.bstdoom.components.releases.ReleaseIndexEntry
import io.github.bstdoom.components.releases.ReleaseKind
import io.github.bstdoom.components.releases.releaseIndexEntries
import org.jetbrains.compose.web.dom.*

@Page
@Composable
fun MusikPage() {
  val releases = remember { releaseIndexEntries() }

  SiteScaffold {
    Section(attrs = { classes("bg", "site-panel", "music-page-intro") }) {
      H2(attrs = { classes("heading") }) {
        Text("Musik")
      }
    P(attrs = { classes("music-intro-copy") }) {
        Text("Hier findest Du unsere Veröffentlichungen im Überblick.")
      }
    }

    ReleaseKind.entries.forEach { kind ->
      val releasesForKind = releases
        .filter { it.releaseType == kind }
        .sortedByDescending { it.year }

      if (releasesForKind.isNotEmpty()) {
        ReleaseGroup(
          title = kind.sectionTitle,
          releases = releasesForKind,
        )
      }
    }
  }
}

@Composable
private fun ReleaseGroup(
  title: String,
  releases: List<ReleaseIndexEntry>,
) {
  Section(attrs = { classes("bg", "site-panel") }) {
    H2(attrs = { classes("heading") }) {
      Text(title)
    }
    Div(attrs = { classes("release-grid") }) {
      releases.forEach { release ->
        A(href = "/musik/${release.slug.replace('_', '-')}/", attrs = { classes("release-card") }) {
          Img(
            src = release.coverImage,
            attrs = {
              attr("alt", release.title)
              classes("release-cover")
            }
          )
          Div(attrs = { classes("release-copy") }) {
            Div(attrs = { classes("release-title") }) {
              Text(release.title)
            }
            Div(attrs = { classes("release-meta") }) {
              Text("${release.releaseType.displayLabel} (${release.year})")
            }
          }
        }
      }
    }
  }
}
