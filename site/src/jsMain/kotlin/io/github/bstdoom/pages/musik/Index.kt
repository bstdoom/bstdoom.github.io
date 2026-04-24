package io.github.bstdoom.pages.musik

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.core.Page
import io.github.bstdoom.components.SiteScaffold
import org.jetbrains.compose.web.dom.A
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H2
import org.jetbrains.compose.web.dom.Img
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Section
import org.jetbrains.compose.web.dom.Text

private data class ReleaseCard(
    val title: String,
    val href: String,
    val image: String,
    val type: String,
    val year: String,
)

@Page
@Composable
fun MusikPage() {
    SiteScaffold {
        Section(attrs = { classes("bg", "site-panel", "music-page-intro") }) {
            H2(attrs = { classes("heading") }) {
                Text("Musik")
            }
            P(attrs = { classes("music-intro-copy") }) {
                Text("Hier findest Du unsere Veröffentlichungen im Überblick.")
            }
        }

        ReleaseGroup(
            title = "Full Length",
            releases = listOf(
                ReleaseCard(
                    title = "Herbst",
                    href = "/musik/herbst/",
                    image = "/assets/releases/herbst.jpg",
                    type = "LP, CD",
                    year = "2022",
                ),
                ReleaseCard(
                    title = "Unter Deck",
                    href = "/musik/unter-deck/",
                    image = "/assets/releases/unter_deck.jpg",
                    type = "LP, CD",
                    year = "2017",
                ),
                ReleaseCard(
                    title = "Die Illusion",
                    href = "/musik/die-illusion/",
                    image = "/assets/releases/die_illusion.jpg",
                    type = "LP, CD",
                    year = "2013",
                ),
            )
        )

        ReleaseGroup(
            title = "EP",
            releases = listOf(
                ReleaseCard(
                    title = "Hamburg City Doom",
                    href = "/musik/hamburg-city-doom/",
                    image = "/assets/releases/hamburg_city_doom.jpg",
                    type = "EP",
                    year = "2009",
                ),
            )
        )

        ReleaseGroup(
            title = "Demo",
            releases = listOf(
                ReleaseCard(
                    title = "Vier + 2",
                    href = "/musik/vier-plus-zwei/",
                    image = "/assets/releases/vier_plus_zwei.jpg",
                    type = "Demo",
                    year = "2000",
                ),
            )
        )
    }
}

@Composable
private fun ReleaseGroup(
    title: String,
    releases: List<ReleaseCard>,
) {
    Section(attrs = { classes("bg", "site-panel") }) {
        H2(attrs = { classes("heading") }) {
            Text(title)
        }
        Div(attrs = { classes("release-grid") }) {
            releases.forEach { release ->
                A(href = release.href, attrs = { classes("release-card") }) {
                    Img(
                        src = release.image,
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
                            Text("${release.type} (${release.year})")
                        }
                    }
                }
            }
        }
    }
}
