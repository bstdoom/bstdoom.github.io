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

data class BandSocialLink(
    val label: String,
    val href: String,
    val iconClasses: List<String>,
)

data class BandMember(
    val name: String,
    val role: String,
    val info: String,
    val image: String,
    val socialLinks: List<BandSocialLink> = emptyList(),
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
                Text("In Neuformierung - Mund abputzen und weiter")
            }
            H1 {
                Text("B.S.T. - Hamburg City Doom")
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
                    Text("Gegründet 1994 in Hamburg, steht B.S.T. für \"Blut, Schweiß, Tränen\". Die aktuelle Formation besteht seit 2009.")
                }
                P {
                    Text("Ihre Doom-Metal-Musik ist so dunkel und schwer wie ein Nordatlantiksturm im November. Mit ihren deutschen Texten zeichnen sie ein unerbittliches Bild von der rauen See und dem unausweichlichen Tod.")
                }
                P {
                    Text("B.S.T. ist definitiv keine Band für Gelegenheitssegler oder Freizeitkapitäne!")
                }
                H3 {
                    Text("Steckbrief")
                }
                Ul {
                    Li { Text("Herkunft: Hamburg, 1994") }
                    Li { Text("Stilrichtung: Doom Einflüsse: alles was langsam und hart ist") }
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
                        if (member.socialLinks.isNotEmpty()) {
                            Div(attrs = { classes("social-icons") }) {
                                member.socialLinks.forEach { social ->
                                    A(
                                        href = social.href,
                                        attrs = {
                                            attr("target", "_blank")
                                            attr("rel", "noopener noreferrer")
                                            attr("aria-label", social.label)
                                        }
                                    ) {
                                        org.jetbrains.compose.web.dom.I(attrs = { classes(*social.iconClasses.toTypedArray()) })
                                    }
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
