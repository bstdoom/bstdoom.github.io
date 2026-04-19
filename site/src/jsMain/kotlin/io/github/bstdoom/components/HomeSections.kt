package io.github.bstdoom.components

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.dom.A
import org.jetbrains.compose.web.dom.Br
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.H2
import org.jetbrains.compose.web.dom.H3
import org.jetbrains.compose.web.dom.Img
import org.jetbrains.compose.web.dom.Li
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Section
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text
import org.jetbrains.compose.web.dom.Ul

data class BandMember(
    val name: String,
    val role: String,
    val info: String,
    val image: String,
)

data class LinkGroup(
    val title: String,
    val items: List<LinkItem>,
)

data class LinkItem(
    val label: String,
    val href: String,
    val location: String? = null,
)

@Composable
fun HomeHeroSection() {
    Section(attrs = { id("home") }) {
        Div(attrs = { classes("hero-copy") }) {
            Div(attrs = { classes("home-kicker") }) {
                Text("19.10. - Paderborn")
            }
            H1 {
                Text("B.S.T. - Metal Inferno")
            }
            P(attrs = { classes("hero-copy-text") }) {
                Text("Hamburg City Doom since 1994. German lyrics, heavy riffs, and the slow kind of pressure the old site promised immediately.")
            }
        }
    }
}

@Composable
fun HomePanelSection(
    title: @Composable () -> Unit,
    content: @Composable () -> Unit,
) {
    Section(attrs = { classes("bg", "site-panel") }) {
        H2(attrs = { classes("heading") }) {
            title()
        }
        content()
    }
}

@Composable
private fun Emphasis(text: String) {
    Span(
        attrs = {
            style {
                property("font-weight", "700")
            }
        }
    ) {
        Text(text)
    }
}

@Composable
fun AboutSection() {
    HomePanelSection(
        title = {
            Text("Über ")
            Emphasis("uns")
        }
    ) {
        Div(attrs = { classes("about-grid") }) {
            Img(
                src = "/assets/home/bandfoto.jpg",
                attrs = {
                    attr("alt", "B.S.T. Bandfoto")
                    classes("about-photo")
                }
            )
            Div(attrs = { classes("about-content") }) {
                H3 {
                    Text("Bio")
                }
                P {
                    Text("Gegründet 1994 unter dem Namen \"Blut Schweiß Tränen\", besteht die aktuelle Formation seit 2009. Die Core-Schlagseite der frühen Jahre wich nach und nach der Orientierung an Genre-Klassikern.")
                }
                P {
                    Text("Das 2012 aufgenommene Debut-Album \"Die Illusion\" markiert dabei den vorläufigen kreativen Höhepunkt, der alle Markenzeichen spielerisch vereint.")
                }
                P {
                    Text("B.S.T. sind keine Band zum Selbstzweck sondern eine eingespielte und eingeschworene Gemeinschaft, das macht uns stark.")
                }
                H3 {
                    Text("Steckbrief")
                }
                Ul {
                    Li { Text("Herkunft: Hamburg, 1994") }
                    Li { Text("Stilrichtung: Doom mit Einflüssen aus allem was langsam und hart ist") }
                    Li { Text("Besonderheiten: Deutsche Texte") }
                }
            }
        }
    }
}

@Composable
fun BandSection(members: List<BandMember>) {
    HomePanelSection(
        title = {
            Text("Die ")
            Emphasis("Band")
        }
    ) {
        Div(attrs = { classes("performers-grid") }) {
            members.forEach { member ->
                Div(attrs = { classes("perfomer-wrapper") }) {
                    Img(
                        src = member.image,
                        attrs = {
                            attr("alt", member.name)
                            classes("single-performer-image")
                        }
                    )
                    Div(attrs = { classes("single-performer-copy") }) {
                        H3 {
                            Text(member.name)
                        }
                        Span(attrs = { classes("performer-role") }) {
                            Text(member.role)
                        }
                        P {
                            Text(member.info)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun LinksSection(groups: List<LinkGroup>) {
    HomePanelSection(
        title = {
            Text("Freunde & ")
            Emphasis("Links")
        }
    ) {
        Div(attrs = { classes("links-groups") }) {
            groups.forEach { group ->
                Div {
                    H3(attrs = { classes("links-group-title") }) {
                        Text(group.title)
                    }
                    Div(attrs = { classes("links-grid") }) {
                        group.items.forEach { item ->
                            A(
                                href = item.href,
                                attrs = { classes("links-item") }
                            ) {
                                Emphasis(item.label)
                                item.location?.let {
                                    Text(" ($it)")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun IntroNotice() {
    Div(attrs = { classes("intro-notice") }) {
        Text("This is the first rebuild pass of the old home page: structure, sections, and original assets are now in place. Next steps are route-level pages, styling refinement, and Wayback parity checks.")
        Br()
        Text("See ")
        A(href = "/musik/") {
            Text("Musik")
        }
        Text(", ")
        A(href = "/info/") {
            Text("Info")
        }
        Text(", and ")
        A(href = "/english/") {
            Text("English")
        }
        Text(" for the next routes to implement.")
    }
}
