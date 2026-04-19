package io.github.bstdoom.components

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.dom.A
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Footer
import org.jetbrains.compose.web.dom.Img
import org.jetbrains.compose.web.dom.Li
import org.jetbrains.compose.web.dom.Text
import org.jetbrains.compose.web.dom.Ul

data class NavLink(
    val href: String,
    val label: String,
)

@Composable
fun SiteScaffold(
    navLinks: List<NavLink>,
    content: @Composable () -> Unit,
) {
    Div(attrs = { classes("site-shell") }) {
        Div(attrs = { id("navigation") }) {
            Div(attrs = { classes("site-container", "site-header-row") }) {
                A(href = "/", attrs = { classes("navbar-brand") }) {
                    Img(
                        src = "/assets/theme/logo.png",
                        attrs = {
                            attr("alt", "B.S.T.")
                            classes("site-logo")
                        }
                    )
                }
                Ul(attrs = { classes("navbar-nav") }) {
                    navLinks.forEach { link ->
                        Li {
                            A(href = link.href) {
                                Text(link.label)
                            }
                        }
                    }
                }
            }
        }

        Div(attrs = { classes("site-container", "site-main") }) {
            content()
        }

        Footer(attrs = { classes("site-container", "site-footer") }) {
            Div(attrs = { classes("updates-links", "bg") }) {
                Div(attrs = { classes("footer-grid") }) {
                    FooterColumn(
                        title = "Quick Links",
                        links = navLinks + listOf(
                            NavLink("https://bstdoom.bandcamp.com/", "Bandcamp"),
                            NavLink("https://facebook.com/hamburgcitydoom", "Facebook"),
                        )
                    )
                    FooterColumn(
                        title = "Kontakt",
                        links = listOf(
                            NavLink("mailto:band@b-s-t.net", "band@b-s-t.net"),
                            NavLink("https://www.youtube.com/channel/UCZcM2xHbSAL9o1HiJu8ZO1g", "YouTube"),
                            NavLink("https://open.spotify.com/artist/6GYU7k1jVtTH7XquD79T5V", "Spotify"),
                        )
                    )
                }
            }
            Div(attrs = { classes("footer", "text-center") }) {
                Text("Copyright © B.S.T. Rebuilt with Kobweb.")
            }
        }
    }
}

@Composable
private fun FooterColumn(
    title: String,
    links: List<NavLink>,
) {
    Div(attrs = { classes("quick-links") }) {
        Div(attrs = { classes("footer-title") }) { Text(title) }
        Ul {
            links.forEach { link ->
                Li {
                    A(href = link.href) {
                        Text(link.label)
                    }
                }
            }
        } 
    }
}
