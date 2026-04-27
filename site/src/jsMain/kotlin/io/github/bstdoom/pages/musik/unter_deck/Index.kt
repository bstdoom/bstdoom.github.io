package io.github.bstdoom.pages.musik.unter_deck

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.core.Page
import io.github.bstdoom.components.SiteScaffold
import io.github.bstdoom.components.releases.ReleasePageForSlug

@Page
@Composable
fun UnterDeckPage() = SiteScaffold {
  ReleasePageForSlug("unter_deck")
}
