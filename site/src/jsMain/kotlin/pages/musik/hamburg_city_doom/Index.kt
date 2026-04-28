package io.github.bstdoom.pages.musik.hamburg_city_doom

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.core.Page
import io.github.bstdoom.components.SiteScaffold
import io.github.bstdoom.components.releases.ReleasePageForSlug

@Page
@Composable
fun HamburgCityDoomPage() = SiteScaffold {
  ReleasePageForSlug("hamburg_city_doom")
}
