package io.github.bstdoom.pages.info

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.core.Page
import io.github.bstdoom.components.DownloadItem
import io.github.bstdoom.components.DownloadsWidget
import io.github.bstdoom.components.NavLink
import io.github.bstdoom.components.SiteScaffold
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H2
import org.jetbrains.compose.web.dom.Img
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Section
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text

@Page
@Composable
fun InfoPage() {
    SiteScaffold(
        navLinks = listOf(
            NavLink("/", "Home"),
            NavLink("/musik/", "Musik"),
            NavLink("/info/", "Info"),
            NavLink("/english/", "en"),
        )
    ) {
        Section(attrs = { classes("info-page", "release-detail-page") }) {
            Div(attrs = { classes("release-detail-grid") }) {
                Div(attrs = { classes("release-main-column") }) {
                    Div(attrs = { classes("site-content", "bg", "site-panel") }) {
                        H2(attrs = { classes("heading") }) {
                            Text("Band ")
                            Span(attrs = { style { property("font-weight", "700") } }) {
                                Text("Info")
                            }
                        }
                        Img(
                            src = "/assets/info/live.jpg",
                            attrs = {
                                attr("alt", "B.S.T. live")
                                classes("info-lead-image")
                            }
                        )
                        Div(attrs = { classes("entry-content") }) {
                            P {
                                Text("Wer macht schon Doom Metal mit deutschen Texten? Im Grunde niemand. Insofern sind B.S.T. aus Hamburg echte Genre-Pioniere. Ihr Metal fließt dahin wie Lava und kein englisches Wort kommt über ihre Lippen.")
                            }
                            P {
                                Text("Die Combo besteht aus den langjährigen Freunden Heiko Wenck, Jan Rudßuck, Lutz Reimer und Jan Galinski. Zu Anfang war ihre Musik noch deutlich Core-lastiger, das Bandgefüge noch auf Feierabend-Niveau.")
                            }
                            P {
                                Text("Unter Blut, Schweiß und Tränen schält sich nach und nach ein zähflüssiger Marmeladenmetal heraus. Das Ergebnis taufen sie \"Hamburg City Doom\". Ein Tröpfchen Black Sabbath, mehr Saint Vitus plus eine gehörige Portion Candlemass kann man als Einfluss deutlich heraushören.")
                            }
                            P {
                                Text("Genau aus diesem Stahl ist ihr 2013er Debüt \"Die Illusion\" geschnitzt. Trotz der nebenher notwendigen Berufstätigkeit macht das Haus B.S.T. alles in Eigenregie: Band, Produktion und Label in Personalunion.")
                            }
                            P {
                                Text("Der Clou ihrer Mucke sind gleichwohl ihre deutschen Zeilen. Alles Leid und allen Schmerz dieser Welt kleiden sie in anschauliche und sprachlich anspruchsvolle, mitunter gar poetische Worte.")
                            }
                            P {
                                Text("Kein Wunder, denn hier schlägt kein öder Hirnfurz zu, sondern das pralle Leben: Hauptsongwriter und Texter Wenck verarbeitet in den Songs Erfahrungen, die weit über bloße Pose hinausgehen.")
                            }
                            P(attrs = { classes("info-attribution") }) {
                                Text("Ulf Kubanke, laut.de")
                            }
                        }
                    }
                }
                Div(attrs = { classes("sidebar-inner") }) {
                    DownloadsWidget(
                        items = listOf(
                            DownloadItem(
                                title = "Unser Logo zur Gestaltung von Flyern etc.",
                                href = "/assets/info/bst-logo.tiff",
                                thumbnail = "/assets/info/bst-logo-thumb.png",
                                alt = "B.S.T. Logo",
                            ),
                            DownloadItem(
                                title = "Bandfoto",
                                href = "/assets/info/bst-bandfoto.jpg",
                                thumbnail = "/assets/info/bst-bandfoto-thumb.png",
                                alt = "Bandfoto",
                            ),
                            DownloadItem(
                                title = "Equipment und Bühnenaufbau für Mischer/Bühnentechniker",
                                href = "/assets/info/bst-technical-rider.pdf",
                                thumbnail = "/assets/info/bst-technical-rider-thumb.png",
                                alt = "Technical Rider",
                            ),
                            DownloadItem(
                                title = "Promo Flyer Unter Deck",
                                href = "/assets/info/bst-unterdeck-promo.pdf",
                                thumbnail = "/assets/info/bst-unterdeck-promo-thumb.png",
                                alt = "Promo Flyer Unter Deck",
                            ),
                        )
                    )
                }
            }
        }
    }
}
