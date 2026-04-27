package io.github.bstdoom.pages.musik.die_illusion

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.core.Page
import io.github.bstdoom.components.SiteScaffold
import io.github.bstdoom.components.releases.ReleasePageForSlug

@Page
@Composable
fun DieIllusionPage() = SiteScaffold {
  ReleasePageForSlug("die_illusion")
}
