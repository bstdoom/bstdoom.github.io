package io.github.bstdoom.pages

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.core.Page
import io.github.bstdoom.components.*

@Page
@Composable
fun HomePage() {
  SiteScaffold {
    HomeHeroSection()
    AboutSection()
    BandSection()
    LinksSection()
  }
}
