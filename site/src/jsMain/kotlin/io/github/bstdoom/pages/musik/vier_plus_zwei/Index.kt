package io.github.bstdoom.pages.musik.vier_plus_zwei

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.core.Page
import io.github.bstdoom.components.SiteScaffold
import io.github.bstdoom.components.releases.ReleasePageForSlug

@Page
@Composable
fun VierPlusZweiPage() = SiteScaffold {
  ReleasePageForSlug("vier_plus_zwei")
}
