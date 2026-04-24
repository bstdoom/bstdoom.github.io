package io.github.bstdoom.pages.musik.die_illusion

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.core.Page
import io.github.bstdoom.components.ReleaseDetailLayout
import io.github.bstdoom.components.ReleaseLyricsSection
import io.github.bstdoom.components.ReviewQuote
import io.github.bstdoom.components.ReviewsWidget
import io.github.bstdoom.components.SiteScaffold
import io.github.bstdoom.components.SpotifyWidget
import io.github.bstdoom.components.Track
import io.github.bstdoom.components.TracksWidget

@Page
@Composable
fun DieIllusionPage() {
    SiteScaffold {
        ReleaseDetailLayout(
            title = "Die Illusion",
            coverImage = "/assets/releases/die_illusion_detail.jpg",
            coverAlt = "Die Illusion Cover",
            body = {
                ReleaseLyricsSection(
                    sections = listOf(
                        "Die Illusion" to listOf(
                            "Komm ein bisschen näher, schau ihm tief in die Augen\nhalt' ihm stand dem starren Blick\nDu wirst seh'n\nes ist nur noch Schmerz",
                            "Es ist furchtbar schwer, immer wieder mitzuleiden\nwürd' es enden, wenn ich bete\nDu sähest mich\nsofort auf den Knien",
                            "Es wird immer mehr, was kann dieser Mann ertragen\nhoffe auf den Neuanfang\nund eine Chance\nin der anderen Welt",
                            "Es ist die Illusion\nin der Nacht\ndass ich die Emotionen spüre\ngibt mir Macht",
                        ),
                        "Die Moral" to listOf(
                            "Vergesst jetzt Euer Zaudern, Anstand und Moral\nIch hab nur dieses Leben und dafür steh' ich ein\ndas Leiden unbeschreiblich, Schmerz ist nur gedämpft\ndoch ich weiß jetzt wofür man kämpft",
                            "Der innere Krieg trübt, die Erinnerung total\ndie Wahl vor der ich stehe, entscheid ich niemals fair\nder Geist ist fehlgeleitet, Wahrnehmung gestört\nich ruf' Deinen Namen, hast Du's gehört",
                            "Lass uns leben, komm und nimm meine Hand\nauf dem Weg, ich kann Dir nichts besseres geben\nNenn es Freiheit, nenn es nichts zu verlier'n\nall mein Stolz, wird gebraucht zum Überleben",
                        ),
                        "Die Lüge" to listOf(
                            "Glück ist Illusion, es schwebt davon\ndie Lüge des Lebens, zieht Ihren Kreis",
                            "Nicht weit von hier, den Weg vor Dir\nsiehst nicht weit von hier\nder Tunnel unendlich, das ist der Beweis",
                            "Das Ziel in Sicht, Du siehst es nicht\nDu mühst Dich vergebens, Du zahlst den Preis",
                            "Was die Nacht verbirgt, das zeigt der Morgen\ndie Unnachgiebigkeit, die Du nicht verstehst",
                            "Wenn man den Hass verliert\ndann ist es bald soweit\nmein Schutzschild liegt in Trümmern\njenseits und in Deiner Macht",
                            "Und es ist mein Wille\nund es bleibt der Glaube daran\nund es bleibt mein Wille",
                        ),
                        "Die Bilder" to listOf(
                            "Werd im Kopf nicht leer so sehr ich's versuch\nich hab es geahnt\nall die Bilder sind fest eingebrannt\nfür die Ewigkeit",
                            "Unaufhörlich steigt der Druck ich muss fliehen\nich hab Euch gewarnt\nist es wirklich das, was ich verdien'\nobwohl Ihr mich doch kennt",
                            "Habe stets beschützt, egal wer mich rief\nmit all meiner Macht\nwenn Ihr mich benutzt Ihr werdet's bereuen\nden ich kenne mich",
                            "Stets das gleiche Spiel\nWut zu schüren gelingt nur mir\ndoch ich werd' mich erneut erklären\ndie Versuche, sind ohne Chance",
                            "Doch dann kommt zuviel\nund es kocht die Wut in mir\ndann lass ich sie bewusst gewähren\nohne Zügel, mit voller Kraft",
                        ),
                        "Die Nacht" to listOf(
                            "Die Nacht, ist am schönsten allein\negal, wie verlockend es klingt\nich warte darauf, laufe nicht hinterher\ndie Nacht, ist am schönsten allein",
                            "Der Mond, ist mein einziger Freund\ner hält keine Fragen bereit\ner schweigt und bleibt still\nund er kennt meinen Schmerz\nder Mond, ist mein einziger Freund",
                            "Ich glaub' ich brauche noch Zeit\nwann werd' ich endlich befreit\nich glaub' ich brauche noch Zeit",
                        ),
                        "Die Hoffnung" to listOf(
                            "Hoffnung enttäuscht, der Blick ist leer\nGesicht entstellt, ist jemand ruhig\ndas Glück genommen, der Schatten viel\nes ist kein Anfang, total verwirrt",
                            "Hoffnung enttäuscht, der Weg ist weit\nich kenn Dich nicht, es tut mir leid\ndas Pech wird kommen, gehört zu mir\nes ist kein Ankommen, ich bin nicht ich",
                            "Komm schau mich an, Komm und schau mich an\nund lass mich sehen, dass Du mich trotzdem berührst\nKomm schau mich an, komm und schau mich an\nich lass Dich gehen, auch wenn ich alles verlier'",
                        ),
                    )
                )
            },
            sidebar = {
                TracksWidget(
                    tracks = listOf(
                        Track("Die Illusion", "07:29"),
                        Track("Die Moral", "08:57"),
                        Track("Die Lüge", "07:05"),
                        Track("Die Bilder", "07:46"),
                        Track("Die Nacht", "05:37"),
                        Track("Die Hoffnung", "09:41"),
                    )
                )
                SpotifyWidget(embedUrl = "https://open.spotify.com/embed/album/3bgPe0umYuNjeH4WCdokgb")
                ReviewsWidget(
                    reviews = listOf(
                        ReviewQuote(
                            source = "Deaf Forever, Götz Kühnemund",
                            url = "http://www.deaf-forever.de/",
                            quote = "'Die Illusion' darf trotz seines Alters nicht übergangen werden. Crowbar-artiger Doom mit guten deutschen Texten ist schließlich nichts Alltägliches.",
                        ),
                        ReviewQuote(
                            source = "Legacy, Juli 2013 (11/15)",
                            url = "http://www.legacy.de",
                            quote = "Die Hamburger haben sich zu einer ernstzunehmenden Formation mit vielen Alleinstellungsmerkmalen entwickelt.",
                        ),
                        ReviewQuote(
                            source = "Sacred Metal, Michael Kohsiek",
                            url = "http://sacredsacredmetal.blogspot.de/2014/02/bst-die-illusion.html",
                            quote = "Exzellente Gitarrenmelodien, greifbare Melancholie und erfreulich kitschfreie Texte machen 'Die Illusion' zu einem richtig guten Doomalbum aus Deutschland.",
                        ),
                        ReviewQuote(
                            source = "allover-ffm-rock.de, Michael Toscher (8/10)",
                            url = "http://allover-ffm-rock.de/reviews-interviews/cd-reviews/20477-b-s-t-die-illusion",
                            quote = "Ein anspruchsvolles Werk mit tiefgründigem Hintergrund, das unverblümt aus dem Leben erzählt.",
                        ),
                        ReviewQuote(
                            source = "streetclip.tv, Michael Haifl (9/10)",
                            url = "http://www.streetclip.tv/magazine/details/reviews/article/b-s-t-die-illusion/",
                            quote = "Hart, düster und zugleich himmelhochjauchzend. B.S.T. liefern deutschen Doom mit erstaunlicher Wirkung.",
                        ),
                        ReviewQuote(
                            source = "laut.de (4/5)",
                            url = "http://www.laut.de/B.S.T./Die-Illusion-(Album)",
                            quote = "So unkonventionell die Idee, so im besten Sinne routiniert klingt ihr Longplay-Debüt. Genre-Fans sollten ein Ohr riskieren.",
                        ),
                    )
                )
            }
        )
    }
}
