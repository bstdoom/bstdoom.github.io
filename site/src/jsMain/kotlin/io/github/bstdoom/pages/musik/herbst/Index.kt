package io.github.bstdoom.pages.musik.herbst

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.core.Page
import io.github.bstdoom.components.ReleaseDetailLayout
import io.github.bstdoom.components.ReleaseFactsList
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
fun HerbstPage() {
    SiteScaffold {
        ReleaseDetailLayout(
            title = "Herbst",
            coverImage = "/assets/releases/herbst.jpg",
            coverAlt = "Herbst Cover",
            body = {
                ReleaseFactsList(
                    facts = listOf(
                        "Recording: Rosenquarz Studio",
                        "Release: 2022",
                        "LP: Fucking Kill Records / Cause of Deaf Records",
                    )
                )
                ReleaseLyricsSection(
                    sections = listOf(
                        "Nur ein Tag im Leben" to listOf(
                            "So lang, auf Sicht gesegelt\nOhne schützendes Geleit\nWenn ich den Halt verlier, das Eis wird dünner\nWirkt er bodenlos, der Fall\nIch lass Dich langsam los, wirst Du mir begegnen",
                            "Hörst Du Ihren Namen rufen, ist es wirklich wahr\nKann keiner spüren die Gefahr\nNur ein Tag im Leben, nicht verhandelbar",
                            "Schließ die Augen, als wäre es das letzte Mal\nWas sich Dir hier noch zeigt, bleibt für immer bei Dir\nAtme ein, als wäre es das letzte Mal\nMach die Pause bewusst, dann mach den nächsten Zug",
                            "Hörst Du Ihren Namen rufen, ist es wirklich wahr\nKann keiner spüren die Gefahr\nNur ein Tag im Leben, nicht verhandelbar",
                            "Hörst Du Ihren Namen rufen, es ist wirklich wahr\nDenke zurück an jedes Jahr\nNur ein Tag im Leben, nicht verhandelbar",
                        ),
                        "Kaltstart" to listOf(
                            "Kaltstart, Körper stets im Wind, Kampf so wie gewohnt\nHau raus, alles was Du hast, teste Grenzen aus\nEinmal, Flanke ungeschützt, Undank Welten Lohn\nEs bleibt nur der eine Schluss, ich bin jetzt bereit\nIrgendwo, irgendwo\nIrgendwo, irgendwo",
                            "Ich bin verwundet, Narbe schließt nur halb\nDoch ich blute, nicht für mich\nHeilt nur langsam, Stille hilft, gibt Halt\nWorte sinnlos, steh zu mir",
                            "Mein Blick ist leer\nDu teilst ihn mit mir\nBestimmst meinen Weg\nGibst ihn mir vor",
                            "Kaltstart, hatten so viel Zeit, was heißt Liebe schon\nAll das, was Du für mich warst, für die Ewigkeit\nWirkt nun, da Du von mir gehst, fast wie blanker Hohn\nMir bleibt nur der eine Trost, auf ein Wiedersehen\nIrgendwann, irgendwann\nIrgendwann, irgendwann",
                            "Mein Blick ist leer\nDu teilst ihn mit mir\nbestimmst meinen Weg\nGibst ihn mir vor\nMein Blick ist leer\nDu teilst ihn mit mir\nBestimmst meinen Weg\nGibst ihn mir vor",
                            "Was ist bloß geschehen\nalles schien so klar\nNur ein Spiel auf Zeit\nhalte mich bereit",
                        ),
                        "Der Tod kommt näher" to listOf(
                            "Der Tod kommt näher, Der Tod kommt näher\nKeiner weiß mehr woher\nDer Tod kommt näher, das Ende näher\nKeiner spürt mehr woher",
                            "Der Tod kommt näher, Der Tod kommt näher\nKeiner weiß mehr woher\nDer Tod kommt näher, das Ende näher\nKeiner spürt mehr woher",
                            "Sprich die Sprache der Nacht\nSei nicht laut, sei aufmerksam, zögere nicht\nDu wirst Dich entlohnen",
                        ),
                        "Was jetzt noch bleibt" to listOf(
                            "Irgendwann, Irgendwer in der Welt\nMir die Daumen hält, wenn auch mit geballter Faust\nDie blinde Seite wahrt\nDoch für mich, in der Abgeschiedenheit\nIn den engen Straßen, wo ich meine kleinen Kreise zieh\nUnermüdlich\nAlles schweigt durcheinander\nStändig umgibt mich Verfall\nUnausweichlich\nSteht die Entscheidung bevor\nNiemals geahnt",
                            "Was jetzt noch bleibt für Dich\nVerblasst jetzt mit der Zeit\nDurch all das Leid, für Dich\nLässt mich den Hass verstehen",
                            "Was jetzt noch bleibt für Dich\nVerblasst jetzt mit der Zeit\nDurch all das Leid, für Dich\nLässt mich den Hass verstehen",
                            "Was jetzt noch bleibt für Dich\nVerblasst jetzt mit der Zeit\nDurch all das Leid, für Dich\nLässt mich den Hass verstehen",
                        ),
                        "Der Mut" to listOf(
                            "Kann es mir nicht erklären, wage mich vor\nEs kam mir noch niemals so nah\nIch will mich erinnern, nehme mich beim Wort\nWerde ihn überstehen diesen Tag\nÜberstehe",
                            "Die Wege zu weit, habe es trotzdem versucht\nAm Ende der Zeit, war es selten genug\nAuch wenn es ausschleicht, bleibt doch der Versuch\nGefällt ist das Urteil, eindrucksvoll war der Mut\nHabe nicht darum gebeten, trage die Last\nEs trifft mich die Wucht ungebremst\nKanns nicht verhindern, richte mich auf\nEin weiterer Schritt, der mir so fremd\nRichtung Ende",
                            "Die Wege zu weit, hab es trotzdem versucht\nAm Ende der Zeit, wars selten genug\nAuch wenn es ausschleicht, bleibt doch der Versuch\nGefällt ist das Urteil, eindrucksvoll war der Mut",
                        ),
                    )
                )
            },
            sidebar = {
                TracksWidget(
                    tracks = listOf(
                        Track("Nur ein Tag im Leben", "11:32"),
                        Track("Kaltstart", "11:48"),
                        Track("Der Tod kommt näher", "04:34"),
                        Track("Was jetzt noch bleibt", "08:01"),
                        Track("Der Mut", "10:52"),
                    )
                )
                SpotifyWidget(embedUrl = "https://open.spotify.com/embed/album/1MkkXCYQAamIyeG2EmTAkV?utm_source=generator")
                TrackActionLink(
                    label = "YouTube",
                    href = "https://www.youtube.com/playlist?list=OLAK5uy_mzPVrCfNZqB5J2mvxyIgpYAK--q7m0fvQ",
                )
                ReviewsWidget(
                    reviews = listOf(
                        ReviewQuote(
                            source = "Deaf Forever 51 - (9,5/10)",
                            url = "https://deaf-forever.de/",
                            quote = "\"Herbst\" ist eine bittersüße Tour de Force der intensivsten Art. Für mich schlicht DAS Doom-Album des Jahres 2022. Punkt.",
                        ),
                        ReviewQuote(
                            source = "powermetal.de (10/10)",
                            url = "https://www.powermetal.de/review/review-B_S_T_/Herbst,39497,39338.html",
                            quote = "Es sind tatsächlich schon wieder fünf Jahre ins Land gestrichen seit die Hamburger Super-Doomer mit \"Unter Deck\" eines der innovativsten und emotionalsten Doom-Alben der Neuzeit auf die Menschheit abgefeuert hat. Mir bleibt nichts anderes übrig als erneut mit der Höchstnote zu wedeln und freue mich auf weitere Livedarbietungen dieser Ausnahmeband.",
                        ),
                        ReviewQuote(
                            source = "Chuck Brown, Apostle of Solitude",
                            url = "https://www.facebook.com/apostleofsolitude",
                            quote = "The new record is fucking great guys!",
                        ),
                        ReviewQuote(
                            source = "reflections of darkness (9/10)",
                            url = "https://www.reflectionsofdarkness.com/artists-a-e-cdreviews-131/22174-cd-review-b-s-t-herbst",
                            quote = "B.S.T make music that is true to what Doom used to be: deep, dark and authentic sadness that is transported to the listener in such a raw and fragile despair that it actually makes your heart bleed.",
                        ),
                        ReviewQuote(
                            source = "zephyrs-odem.de (7,5/10)",
                            url = "https://www.zephyrs-odem.de/reviews/detail/bst-herbst-2022-12240/",
                            quote = "Das Album ist nix zum Feiern und Party machen. Die gute Laune kann man sich auch abschminken. Jedoch ist der Sound bestens geeignet, im Halbdunkel in einen Ohrensessel versenkt, das Glas Rotwein in der Hand, seinen düsteren Gedanken nachzuhängen.",
                        ),
                        ReviewQuote(
                            source = "Explosiv: Bandshirt der Woche",
                            url = "https://youtu.be/8cBXrReZbEc?t=354",
                            quote = "\"Nur ein Tag im Leben\", wenn Du da keine Gänsehaut kriegst, dann würde ich mir aber Sorgen machen, ob mein Gefühls-System noch irgendwie funktioniert.",
                        ),
                        ReviewQuote(
                            source = "saitenkult.de (9/10)",
                            url = "https://www.saitenkult.de/2022/10/14/b-s-t-herbst/",
                            quote = "Wenn dieser ´Herbst´ beginnt, dann hat sich die fünfjährige Wartezeit seit ´Unter Deck´ gelohnt.",
                        ),
                        ReviewQuote(
                            source = "vampster.com",
                            url = "https://vampster.com/cd-reviews/b-s-t-herbst/",
                            quote = "Für Doomer und Freunde trauriger Klänge ist „Herbst“ Pflichtprogramm",
                        ),
                        ReviewQuote(
                            source = "cause of deaf - podcast",
                            url = "https://linktr.ee/Causeofdeaf",
                            quote = "Für jeden Doom-Fan: Reinhören, gut finden, mehr Doom kriegt ihr nicht!",
                        ),
                        ReviewQuote(
                            source = "metal.de 8.0/10",
                            url = "https://www.metal.de/reviews/b-s-t-herbst-474444/",
                            quote = "Mit „Herbst“ liefert die Hamburger Doom-Institution B.S.T. ihr drittes Album ab. Wer die Band kennt, weiß, dass es erneut wieder Wehmut und Trostlosigkeit in Schleife gibt.",
                        ),
                    )
                )
            }
        )
    }
}
