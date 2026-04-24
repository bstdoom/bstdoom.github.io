package io.github.bstdoom.components

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.dom.A
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Footer
import org.jetbrains.compose.web.dom.I
import org.jetbrains.compose.web.dom.Img
import org.jetbrains.compose.web.dom.Li
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text
import org.jetbrains.compose.web.dom.Ul

data class NavLink(
    val href: String,
    val label: String,
    val children: List<NavLink> = emptyList(),
    val iconClasses: List<String> = emptyList(),
)

private val musicNavChildren = listOf(
    NavLink("/musik/hamburg-city-doom/", "Hamburg City Doom [EP] (2009)"),
    NavLink("/musik/die-illusion/", "Die Illusion [LP,CD] (2013)"),
    NavLink("/musik/unter-deck/", "Unter Deck [LP, CD] (2017)"),
    NavLink("/musik/herbst/", "Herbst [LP,CD] (2022)"),
)

private val infoNavChildren = listOf(
    NavLink("/info/impressum/", "Impressum"),
)

private val socialNavLinks = listOf(
    NavLink(
        "https://facebook.com/hamburgcitydoom",
        "Facebook",
        iconClasses = listOf("fa", "fa-facebook"),
    ),
    NavLink(
        "https://www.youtube.com/channel/UCZcM2xHbSAL9o1HiJu8ZO1g",
        "YouTube",
        iconClasses = listOf("fa", "fa-youtube"),
    ),
    NavLink(
        "https://bstdoom.bandcamp.com/",
        "Bandcamp",
        iconClasses = listOf("fa", "fa-bandcamp"),
    ),
)

@Composable
fun SiteScaffold(
    navLinks: List<NavLink>,
    content: @Composable () -> Unit,
) {
    val renderedLinks = navLinks.map { link ->
        when (link.label) {
            "Musik" -> link.copy(children = musicNavChildren)
            "Info" -> link.copy(children = infoNavChildren)
            else -> link
        }
    }

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
                Div(attrs = { classes("navbar-menu-group") }) {
                    Ul(attrs = { classes("navbar-nav", "nav-primary") }) {
                        renderedLinks.forEach { link ->
                            NavItem(link = link)
                        }
                    }
                    Ul(attrs = { classes("navbar-nav", "nav-social") }) {
                        socialNavLinks.forEach { link ->
                            Li(attrs = { classes("social-item") }) {
                                A(
                                    href = link.href,
                                    attrs = {
                                        classes("social-link")
                                        attr("target", "_blank")
                                        attr("rel", "noopener noreferrer")
                                        attr("aria-label", link.label)
                                    }
                                ) {
                                    I(attrs = { classes(*link.iconClasses.toTypedArray()) })
                                    Span(attrs = { classes("sr-only") }) {
                                        Text(link.label)
                                    }
                                }
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
                        links = navLinks + socialNavLinks.map { it.copy(iconClasses = emptyList()) }
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
private fun NavItem(
    link: NavLink,
) {
    val hasChildren = link.children.isNotEmpty()

    Li(attrs = {
        if (hasChildren) {
            classes("dropdown")
        }
    }) {
        A(href = link.href) {
            Text(link.label)
            if (hasChildren) {
                I(attrs = { classes("fa-solid", "fa-angle-down") })
            }
        }
        if (hasChildren) {
            Ul(attrs = { classes("sub-menu") }) {
                link.children.forEach { child ->
                    Li {
                        A(href = child.href) {
                            Text(child.label)
                        }
                    }
                }
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
                    A(
                        href = link.href,
                        attrs = {
                            if (link.href.startsWith("http")) {
                                attr("target", "_blank")
                                attr("rel", "noopener noreferrer")
                            }
                        }
                    ) {
                        Text(link.label)
                    }
                }
            }
        }
    }
}
