package io.github.bstdoom.pages.musik.hamburg_city_doom

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.core.Page
import io.github.bstdoom.components.NavLink
import io.github.bstdoom.components.ReleaseDetailLayout
import io.github.bstdoom.components.ReleaseLyricsSection
import io.github.bstdoom.components.ReviewQuote
import io.github.bstdoom.components.ReviewsWidget
import io.github.bstdoom.components.SiteScaffold
import io.github.bstdoom.components.Track
import io.github.bstdoom.components.TracksWidget

@Page
@Composable
fun HamburgCityDoomPage() {
    SiteScaffold(
        navLinks = listOf(
            NavLink("/", "Home"),
            NavLink("/musik/", "Musik"),
            NavLink("/info/", "Info"),
        )
    ) {
        ReleaseDetailLayout(
            title = "Hamburg City Doom",
            coverImage = "/assets/releases/hamburg_city_doom_detail.jpg",
            coverAlt = "Hamburg City Doom Cover",
            body = {
                ReleaseLyricsSection(
                    sections = listOf(
                        "Ziel" to listOf(
                            "Ich wird es nie mehr tun,\nwünscht ich wäre nicht der Grund dafür\nJedes Mal wenn ich sie nur berühr,\nmerke ich es stirbt ein Teil von ihr\nMache, dass ich sie nicht mehr verletz,\ngib ihr Kraft zu leben, gib ihr Macht\nEs gibt soviel zu klären,\nich will nicht der Mörder sein",
                            "Die Zeit steht nie mehr still,\nkeine Macht der Welt hält sie noch auf\nHabe in den Kampf viel investiert,\nhat mich in die Einsamkeit geführt\nUnsere gute Zeit kehrt nie zurück,\nfolgt dem Lauf des Lebens hält nicht an\nKann ich es jemals ändern,\nich bin selbst mein größter Feind",
                            "In alle Ewigkeit,\nbin ich jetzt verdammt allein zu sein\nZiehe mich ganz in mich selbst zurück,\nfinde doch auch hier kein besseres Los\nAlle meine Freunde sind längst fort,\nkonnten mich auf Dauer nicht verstehen\nIch gebe auf zu kämpfen,\ntrete in den Schatten ein",
                            "Wut belanglos,\nsie ist da,\nmühsam ist ihr zu begegnen\nDieses ist, so glaube ich,\nmein angestrebtes Ziel",
                        ),
                        "Traum" to listOf(
                            "Es ist das Böse in Dir,\nDu wirst es sehen\nDer Kampf ist sinnlos denn,\nDu bist zu schwach",
                            "Es läuft nicht wie geplant,\nder Mut vergeht\nDer Widerstand zerbricht,\nmir fehlt die Kraft",
                            "Wie im Traum,\nTrauer und Schmerz so extrem\nIch hoff, dass Du mich befreist\nEs gibt so viel zu verstehen\nEs ist vorbei",
                        ),
                        "Bastard" to listOf(
                            "Bastard,\nschau mich an,\nwie ich leide\nSo hart,\nauf dem Boden aufgeschlagen",
                            "Ich hab, stets versucht,\nMensch zu werden\nIhr habt, mich benutzt,\nzurückgewiesen",
                            "Ich kann Euch schreien hören,\nwenn Ihr bei mir steht\nEgal was Ihr versuchtet,\ndies ist mein Weg,\nich wird ihn gehen",
                            "Ich bin müde\nBefreit\nIch schwebe",
                        ),
                    )
                )
            },
            sidebar = {
                TracksWidget(
                    tracks = listOf(
                        Track("Das Ziel", "08:09"),
                        Track("Bastard", "04:28"),
                        Track("Der Traum", "06:08"),
                    )
                )
                ReviewsWidget(
                    reviews = listOf(
                        ReviewQuote(
                            source = "Dark Heart (6/7)",
                            url = "http://www.darkheartmagazin.de/rezi/bsta6001.htm",
                            quote = "Deutschsprachige düstere Texte mischen sich mit harten Gitarrenriffs und skizzieren das Leben und seine Unwägbarkeiten.",
                        ),
                        ReviewQuote(
                            source = "amboss-mag.de",
                            url = "http://www.amboss-mag.de/reviews/Archiv2/09/r10-09/bst.html",
                            quote = "Doom Metal mit deutschen Texten? Jawoll, kann es. Eine geile Mischung von Crowbar und St. Vitus.",
                        ),
                        ReviewQuote(
                            source = "REVOLT",
                            quote = "Schön fetter Sound, interessante Riffs, gut rübergebrachte Atmosphäre. Für ein Demo sehr professionell.",
                        ),
                        ReviewQuote(
                            source = "igelmetal.de (4/5)",
                            quote = "Klagende deutschsprachige Texte prallen auf tonnenschwere, unglaublich mächtige Riffs.",
                        ),
                        ReviewQuote(
                            source = "squealer rocks",
                            url = "http://www.squealer-rocks.de/neu_cdreview.php?var=2092",
                            quote = "Doom Metal mit starker Core-Schlagseite und deutschen Texten eindrucksvoll in Szene gesetzt.",
                        ),
                    )
                )
            }
        )
    }
}
