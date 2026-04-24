package io.github.bstdoom.pages.musik.unter_deck

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.core.Page
import io.github.bstdoom.components.NavLink
import io.github.bstdoom.components.ReleaseDetailLayout
import io.github.bstdoom.components.ReleaseLyricsSection
import io.github.bstdoom.components.ReviewQuote
import io.github.bstdoom.components.ReviewsWidget
import io.github.bstdoom.components.SiteScaffold
import io.github.bstdoom.components.SpotifyWidget
import io.github.bstdoom.components.Track
import io.github.bstdoom.components.TrackActionLink
import io.github.bstdoom.components.TracksWidget

@Page
@Composable
fun UnterDeckPage() {
    SiteScaffold(
        navLinks = listOf(
            NavLink("/", "Home"),
            NavLink("/musik/", "Musik"),
            NavLink("/info/", "Info"),
        )
    ) {
        ReleaseDetailLayout(
            title = "Unter Deck",
            coverImage = "/assets/releases/unter_deck_detail.jpg",
            coverAlt = "Unter Deck Cover",
            body = {
                ReleaseLyricsSection(
                    sections = listOf(
                        "Stimmen" to listOf(
                            "Stimmen, die ich nicht versteh, hören sich so nah an,\ndoch ich find keinen Anfang\nAllein in der Menge geh ich unter,\nweil mir irgendwas fehlt,\nich fühl es so sehr",
                            "Stimmen, die ich nicht mehr hör,\nerfordern einen Plan,\nwill die Zukunft noch ändern\nBerühre das, was unberührbar scheint,\nohne jegliche Scham,\nich fühl es so sehr",
                            "Es fehlt der Zweck,\ndoch ich mach immer weiter,\ndas Ziel ist klar\nAuf der ewige Suche des Lebens,\nich stagniere,\ndoch es tut nicht mehr weh",
                            "Stimmen, die es nicht mehr gibt,\nbegrenzen meinen Mut,\nich will jetzt keinen Abgang\nAllein Ehre soll Geschenk mir sein,\nich erheb nun mein Glas,\nes ist nicht mehr leer",
                            "Es fehlt der Zweck,\ndoch ich mach immer weiter,\ndas Ziel ist klar\nAuf der ewigen Suche des Lebens,\nich stagniere,\ndoch es tut nicht mehr weh",
                        ),
                        "Aufgabe" to listOf(
                            "Doch, der stille Blick aufs Meer,\nist mein Trost und hält mich hier\nScheint, dass Du Dich löst, immer mehr\nMein Gesicht, es schaut zu Dir\nIch geb Dich niemals auf,\nhab Dich darum nicht verloren\nSteh zu Dir nach all der Zeit,\nWunden sind mein Lohn",
                            "Die Welle nimmt mich mit,\ntaucht mich ein und spült mich fort\nSand, der mich begräbt wiegt so schwer\nUnd ich frag, vertraust Du mir\nIch geb Dich niemals auf,\nhab Dich darum nicht verloren\nSteh zu Dir nach all der Zeit,\nWunden sind mein Lohn",
                            "Hör zu für immer, Dein Leid viel tiefer,\nder Schmerz verschwindet schon bald\nIch möchte nur nach Haus",
                            "Stell Dir vor, wenn am Ende nichts mehr bleibt\nZwischen Dir und mir, bleibt ein Band\nKeine Angst, vor der stillen Einsamkeit\nKeiner nimmt Sie mir, diese Zeit",
                        ),
                        "Brenne" to listOf(
                            "Sieh Dich um,\nist kein Mensch hier weit und breit,\ndie Tage sind gezählt\nTief hier unter Deck,\nwenn die Wellen schlagen,\nhör ich Dich sagen\nHier kommt keiner weg",
                            "Brenne, Seele brenne,\nich sehe zu bevor ich renne\nDenn ich will weg von diesem Ort,\nwill nicht sehen wie es zuende geht\nWill mein Gewissen nicht mehr trüben,\nwill mich selbst nicht mehr in Frage stellen\nWill nicht klagen, will nicht lügen,\nMut zur Wahrheit, mit all dem Hohn",
                            "Meeresgrund, oder doch der Horizont,\ntrennt nur ein Wimpernschlag\nGeist verliert an Kraft,\neingefasst in Stille,\nder letzte Wille\nDer für immer bleibt",
                            "Du wolltest den Beweis,\nmeiner Schwäche\nDu hast ihn bekommen",
                            "Brenne, Seele brenne,\nich sehe zu bevor ich renne\nDenn ich will weg von diesem Ort,\nwill nicht sehen wie es zuende geht\nWill mein Gewissen nicht mehr trüben,\nwill mich selbst nicht mehr in Frage stellen\nWill nicht klagen, will nicht lügen,\nMut zur Wahrheit, mit all dem Hohn",
                        ),
                        "Chance" to listOf(
                            "So viele Fragen, so viele Fragen\nWill nicht fort, will nicht gehen,\nwill nicht mehr klagen, nicht fühlen, nicht sehen\nWill keine Tränen und auch keinen Schmerz",
                            "So viele Fragen, so viel ist geschehen,\ntreibe weit aufs Meer hinaus\nSo viele Fragen, sie stellen sich Dir nicht,\nweiß  keine Antwort darauf",
                            "Sag mir warum, bleibt die Rettung verwehrt,\nein letztes Mal, setz das falsche Signal\nSag mir warum, bleibt die Chance mir verwehrt,\nein letztes Mal, setz das falsche Signal",
                            "So viele Fragen, so viele Fragen\nWill nicht fort, will nicht gehen,\n will nicht mehr klagen, nicht fühlen, nicht sehen\nWill keine Tränen und auch keinen Schmerz",
                        ),
                        "Ride On (Cover)" to listOf(
                            "See original lyrics: https://www.lyricsmode.com/lyrics/c/christy_moore/ride_on.html",
                        ),
                    )
                )
            },
            sidebar = {
                TracksWidget(
                    tracks = listOf(
                        Track("Stimmen", "10:23"),
                        Track("Aufgabe", "10:44"),
                        Track("Brenne", "08:57"),
                        Track("Chance", "06:46"),
                        Track("Ride On", "05:27"),
                    )
                )
                TrackActionLink(
                    label = "iTunes",
                    href = "https://itunes.apple.com/de/album/unter-deck/id1215878221",
                )
                SpotifyWidget(embedUrl = "https://open.spotify.com/embed/album/41nUKzpKHci7aY7e0GULdw")
                ReviewsWidget(
                    reviews = listOf(
                        ReviewQuote(
                            source = "Deaf Forever",
                            url = "https://www.deaf-forever.de",
                            quote = "Vier bockstarke Songs, die an die emotionalsten Momente von Crowbar erinnern. Wunderschön!",
                        ),
                        ReviewQuote(
                            source = "Rock Hard",
                            url = "https://www.rockhard.de",
                            quote = "B.S.T. zocken lupenreinen Doom mit melodischer New-Orleans-Schlagseite. Starkes Ding!",
                        ),
                        ReviewQuote(
                            source = "Obliveon (8,5/10)",
                            url = "http://www.obliveon.de/pn-om/modules.php?op=modload&name=tplcdeigenp&file=index&req=showcontent&id=22452",
                            quote = "\"Unter Deck\" entfaltet musikalischen Tiefgang und die Prosa der Texte ist auch alles andere als flach.",
                        ),
                        ReviewQuote(
                            source = "Legacy (13/15)",
                            url = "http://www.legacy.de",
                            quote = "Die deutschen Texte heben B.S.T. aus der Masse der Genrekollegen ab.",
                        ),
                        ReviewQuote(
                            source = "powermetal.de (10/10)",
                            url = "http://powermetal.de/review/review-B_S_T_/Unter_Deck,31265,31233.html",
                            quote = "Origineller und emotionaler wird Doom nicht mehr.",
                        ),
                        ReviewQuote(
                            source = "streetclip.tv (8/10)",
                            url = "http://www.streetclip.tv/magazine/details/reviews/article/b-s-t-unter-deck/",
                            quote = "B.S.T. sind die deutsche Stimme des Doom. Hört sie.",
                        ),
                    )
                )
            }
        )
    }
}
