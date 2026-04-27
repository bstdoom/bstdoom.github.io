package io.github.bstdoom.pages.musik.herbst

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.core.Page
import io.github.bstdoom.components.SiteScaffold
import io.github.bstdoom.components.releases.ReleasePageForSlug

@Page
@Composable
fun HerbstPage() = SiteScaffold {
  ReleasePageForSlug("herbst")
}
