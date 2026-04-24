package io.github.bstdoom.pages.musik.vier_plus_zwei

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.core.Page
import io.github.bstdoom.components.ReleaseDetailLayout
import io.github.bstdoom.components.ReleaseLyricsSection
import io.github.bstdoom.components.SiteScaffold
import io.github.bstdoom.components.Track
import io.github.bstdoom.components.TracksWidget

@Page
@Composable
fun VierPlusZweiPage() {
    SiteScaffold {
        ReleaseDetailLayout(
            title = "Vier + 2",
            coverImage = "/assets/releases/vier_plus_zwei_detail.jpg",
            coverAlt = "Vier + 2 Cover",
            body = {
                ReleaseLyricsSection(
                    sections = listOf(
                        "Leuchtturm" to listOf(
                            "Ein starker Orkan\nAuf meiner Haut\nLäßt mich nicht los\nGibt mich nicht frei",
                            "LEUCHTTURM",
                            "Die pure Gewalt\nPrallt an mir ab\nMit viel Geduld\nZeigt sich die Tür",
                            "Meine Mauer zerbricht\nJetzt bin ich endlich frei",
                            "LEUCHTTURM",
                        ),
                        "Faust" to listOf(
                            "Wieder mal\nUnnötiger Schmerz\nHilflos\nKomm näher",
                            "Nichts ist mehr\nWie es einmal war\nSchuldig\nGlaub mir",
                            "Die Faust in der Wand\nDie Finger im Dreck\nDie Angst verjagt\nUnd das Vertrauen verschenkt",
                            "Dieses Mal hab ich es satt\nStets das zu tun, was ihr für’n Scheiß erzählt\nWenn ihr mich fragt, ich hab es satt\nHier ständig nur der Buhmann zu sein",
                        ),
                    )
                )
            },
            sidebar = {
                TracksWidget(
                    tracks = listOf(
                        Track("Wolf", "06:51"),
                        Track("Ich", "04:36"),
                        Track("Leuchtturm", "03:44"),
                        Track("Faust", "06:43"),
                        Track("B.S.T.", "04:23"),
                        Track("Bikini Fieber", "02:36"),
                    )
                )
            }
        )
    }
}
