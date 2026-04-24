package io.github.bstdoom.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import kotlinx.coroutines.delay
import org.jetbrains.compose.web.dom.A
import org.jetbrains.compose.web.dom.Br
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.H2
import org.jetbrains.compose.web.dom.H3
import org.jetbrains.compose.web.dom.H4
import org.jetbrains.compose.web.dom.Img
import org.jetbrains.compose.web.dom.Li
import org.jetbrains.compose.web.dom.Button
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
                Div(attrs = { classes("perfomer-wrapper", "single-performer") }) {
                    Img(
                        src = member.image,
                        attrs = {
                            attr("alt", member.name)
                            classes("single-performer-image")
                        }
                    )
                    Div(attrs = { classes("overlay") }) {
                        Div(attrs = { classes("overley-content") }) {
                            H3 {
                                Text(member.name)
                            }
                            H4(attrs = { classes("performer-role") }) {
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
}

@Composable
fun LinksSection(groups: List<LinkGroup>) {
    var activeIndex by remember(groups) { mutableIntStateOf(0) }

    LaunchedEffect(groups) {
        if (groups.size > 1) {
            while (true) {
                delay(12000)
                activeIndex = (activeIndex + 1) % groups.size
            }
        }
    }

    HomePanelSection(
        title = {
            Text("Freunde & ")
            Emphasis("Links")
        }
    ) {
        Div(attrs = { classes("links-carousel") }) {
            Div(attrs = { classes("links-carousel-viewport") }) {
                Div(
                    attrs = {
                        classes("links-carousel-track")
                        style {
                            property("transform", "translateX(-${activeIndex * 100}%)")
                        }
                    }
                ) {
                    groups.forEach { group ->
                        Div(attrs = { classes("links-carousel-slide") }) {
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
            if (groups.size > 1) {
                Div(attrs = { classes("links-carousel-controls") }) {
                    Button(
                        attrs = {
                            classes("links-carousel-control")
                            attr("type", "button")
                            attr("aria-label", "Previous section")
                            onClick { activeIndex = (activeIndex - 1 + groups.size) % groups.size }
                        }
                    ) {
                        Text("‹")
                    }
                    Button(
                        attrs = {
                            classes("links-carousel-control")
                            attr("type", "button")
                            attr("aria-label", "Next section")
                            onClick { activeIndex = (activeIndex + 1) % groups.size }
                        }
                    ) {
                        Text("›")
                    }
                }
                Div(attrs = { classes("links-carousel-dots") }) {
                    groups.forEachIndexed { index, group ->
                        Button(
                            attrs = {
                                classes("links-carousel-dot")
                                if (index == activeIndex) {
                                    classes("selected")
                                }
                                attr("type", "button")
                                attr("aria-label", "Show ${group.title}")
                                onClick { activeIndex = index }
                            }
                        ) {
                            Text(group.title)
                        }
                    }
                }
            }
        }
    }
}
