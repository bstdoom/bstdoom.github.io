package io.github.bstdoom.pages.info.impressum

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.core.Page
import io.github.bstdoom.components.SiteScaffold
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Em
import org.jetbrains.compose.web.dom.H2
import org.jetbrains.compose.web.dom.H3
import org.jetbrains.compose.web.dom.Li
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Pre
import org.jetbrains.compose.web.dom.Section
import org.jetbrains.compose.web.dom.Text
import org.jetbrains.compose.web.dom.Br
import org.jetbrains.compose.web.dom.Ul

@Page
@Composable
fun ImpressumPage() {
    SiteScaffold {
        Section(attrs = { classes("release-detail-page") }) {
            Div(attrs = { classes("release-detail-grid") }) {
                Div(attrs = { classes("release-main-column") }) {
                    Div(attrs = { classes("site-content", "bg", "site-panel") }) {
                        H2(attrs = { classes("heading") }) {
                            Text("Impressum")
                        }
                        Div(attrs = { classes("static-page-content") }) {
                            Div(attrs = { classes("static-section") }) {
                                P(attrs = { classes("static-paragraph") }) {
                                    Em { Text("Quelle: https://www.e-recht24.de/impressum-generator.html") }
                                }
                                P(attrs = { classes("static-paragraph") }) {
                                    Text("Angaben gemäß § 5 TMG:")
                                }
                                H3(attrs = { classes("static-section-title") }) {
                                    Text("Streitschlichtung")
                                }
                                P(attrs = { classes("static-paragraph") }) {
                                    Text("Wir sind nicht bereit oder verpflichtet, an Streitbeilegungsverfahren vor einer Verbraucherschlichtungsstelle teilzunehmen.")
                                }
                                H3(attrs = { classes("static-section-title") }) {
                                    Text("Haftung für Inhalte")
                                }
                                P(attrs = { classes("static-paragraph") }) {
                                    Text("Als Diensteanbieter sind wir gemäß § 7 Abs.1 TMG für eigene Inhalte auf diesen Seiten nach den allgemeinen Gesetzen verantwortlich.")
                                }
                                P(attrs = { classes("static-paragraph") }) {
                                    Text("Nach §§ 8 bis 10 TMG sind wir als Diensteanbieter jedoch nicht verpflichtet, übermittelte oder gespeicherte fremde Informationen zu überwachen oder nach Umständen zu forschen, die auf eine rechtswidrige Tätigkeit hinweisen.")
                                }
                                P(attrs = { classes("static-paragraph") }) {
                                    Text("Verpflichtungen zur Entfernung oder Sperrung der Nutzung von Informationen nach den allgemeinen Gesetzen bleiben hiervon unberührt. Eine diesbezügliche Haftung ist jedoch erst ab dem Zeitpunkt der Kenntnis einer konkreten Rechtsverletzung möglich. Bei Bekanntwerden von entsprechenden Rechtsverletzungen werden wir diese Inhalte umgehend entfernen.")
                                }
                                H3(attrs = { classes("static-section-title") }) {
                                    Text("Haftung für Links")
                                }
                                P(attrs = { classes("static-paragraph") }) {
                                    Text("Unser Angebot enthält Links zu externen Webseiten Dritter, auf deren Inhalte wir keinen Einfluss haben. Deshalb können wir für diese fremden Inhalte auch keine Gewähr übernehmen. Für die Inhalte der verlinkten Seiten ist stets der jeweilige Anbieter oder Betreiber der Seiten verantwortlich. Die verlinkten Seiten wurden zum Zeitpunkt der Verlinkung auf mögliche Rechtsverstöße überprüft. Rechtswidrige Inhalte waren zum Zeitpunkt der Verlinkung nicht erkennbar.")
                                }
                                P(attrs = { classes("static-paragraph") }) {
                                    Text("Eine permanente inhaltliche Kontrolle der verlinkten Seiten ist jedoch ohne konkrete Anhaltspunkte einer Rechtsverletzung nicht zumutbar. Bei Bekanntwerden von Rechtsverletzungen werden wir derartige Links umgehend entfernen.")
                                }
                                H3(attrs = { classes("static-section-title") }) {
                                    Text("Urheberrecht")
                                }
                                P(attrs = { classes("static-paragraph") }) {
                                    Text("Die durch die Seitenbetreiber erstellten Inhalte und Werke auf diesen Seiten unterliegen dem deutschen Urheberrecht. Die Vervielfältigung, Bearbeitung, Verbreitung und jede Art der Verwertung außerhalb der Grenzen des Urheberrechtes bedürfen der schriftlichen Zustimmung des jeweiligen Autors bzw. Erstellers.")
                                }
                                P(attrs = { classes("static-paragraph") }) {
                                    Text("Downloads und Kopien dieser Seite sind nur für den privaten, nicht kommerziellen Gebrauch gestattet.")
                                }
                                P(attrs = { classes("static-paragraph") }) {
                                    Text("Soweit die Inhalte auf dieser Seite nicht vom Betreiber erstellt wurden, werden die Urheberrechte Dritter beachtet. Insbesondere werden Inhalte Dritter als solche gekennzeichnet. Sollten Sie trotzdem auf eine Urheberrechtsverletzung aufmerksam werden, bitten wir um einen entsprechenden Hinweis. Bei Bekanntwerden von Rechtsverletzungen werden wir derartige Inhalte umgehend entfernen.")
                                }
                            }
                            Div(attrs = { classes("static-section") }) {
                                H3(attrs = { classes("static-section-title") }) {
                                    Text("Datenschutzerklärung")
                                }
                                P(attrs = { classes("static-paragraph") }) {
                                    Em { Text("Quelle: https://www.e-recht24.de/muster-datenschutzerklaerung.html") }
                                }
                                H3(attrs = { classes("static-section-title") }) {
                                    Text("Datenschutz")
                                }
                                P(attrs = { classes("static-paragraph") }) {
                                    Text("Die Betreiber dieser Seiten nehmen den Schutz Ihrer persönlichen Daten sehr ernst. Wir behandeln Ihre personenbezogenen Daten vertraulich und entsprechend der gesetzlichen Datenschutzvorschriften sowie dieser Datenschutzerklärung. Die Nutzung unserer Webseite ist in der Regel ohne Angabe personenbezogener Daten möglich. Soweit auf unseren Seiten personenbezogene Daten (beispielsweise Name, Anschrift oder E-Mail-Adressen) erhoben werden, erfolgt dies, soweit möglich, stets auf freiwilliger Basis. Diese Daten werden ohne Ihre ausdrückliche Zustimmung nicht an Dritte weitergegeben. Wir weisen darauf hin, dass die Datenübertragung im Internet (z.B. bei der Kommunikation per E-Mail) Sicherheitslücken aufweisen kann. Ein lückenloser Schutz der Daten vor dem Zugriff durch Dritte ist nicht möglich.")
                                }
                                H3(attrs = { classes("static-section-title") }) {
                                    Text("Cookies")
                                }
                                P(attrs = { classes("static-paragraph") }) {
                                    Text("Die Internetseiten verwenden teilweise so genannte Cookies. Cookies richten auf Ihrem Rechner keinen Schaden an und enthalten keine Viren. Cookies dienen dazu, unser Angebot nutzerfreundlicher, effektiver und sicherer zu machen. Cookies sind kleine Textdateien, die auf Ihrem Rechner abgelegt werden und die Ihr Browser speichert. Die meisten der von uns verwendeten Cookies sind so genannte „Session-Cookies“. Sie werden nach Ende Ihres Besuchs automatisch gelöscht. Andere Cookies bleiben auf Ihrem Endgerät gespeichert, bis Sie diese löschen. Diese Cookies ermöglichen es uns, Ihren Browser beim nächsten Besuch wiederzuerkennen. Sie können Ihren Browser so einstellen, dass Sie über das Setzen von Cookies informiert werden und Cookies nur im Einzelfall erlauben, die Annahme von Cookies für bestimmte Fälle oder generell ausschließen sowie das automatische Löschen der Cookies beim Schließen des Browser aktivieren. Bei der Deaktivierung von Cookies kann die Funktionalität dieser Website eingeschränkt sein.")
                                }
                                H3(attrs = { classes("static-section-title") }) {
                                    Text("Server-Log-Files")
                                }
                                P(attrs = { classes("static-paragraph") }) {
                                    Text("Der Provider der Seiten erhebt und speichert automatisch Informationen in so genannten Server-Log Files, die Ihr Browser automatisch an uns übermittelt. Dies sind: Browsertyp und Browserversion verwendetes Betriebssystem.")
                                }
                                H3(attrs = { classes("static-section-title") }) {
                                    Text("Referrer URL")
                                }
                                P(attrs = { classes("static-paragraph") }) {
                                    Text("Hostname des zugreifenden Rechners Uhrzeit der Serveranfrage. Diese Daten sind nicht bestimmten Personen zuordenbar. Eine Zusammenführung dieser Daten mit anderen Datenquellen wird nicht vorgenommen. Wir behalten uns vor, diese Daten nachträglich zu prüfen, wenn uns konkrete Anhaltspunkte für eine rechtswidrige Nutzung bekannt werden.")
                                }
                                H3(attrs = { classes("static-section-title") }) {
                                    Text("Kontaktformular")
                                }
                                P(attrs = { classes("static-paragraph") }) {
                                    Text("Wenn Sie uns per Kontaktformular Anfragen zukommen lassen, werden Ihre Angaben aus dem Anfrageformular inklusive der von Ihnen dort angegebenen Kontaktdaten zwecks Bearbeitung der Anfrage und für den Fall von Anschlussfragen bei uns gespeichert. Diese Daten geben wir nicht ohne Ihre Einwilligung weiter.")
                                }
                                H3(attrs = { classes("static-section-title") }) {
                                    Text("Newsletterdaten")
                                }
                                P(attrs = { classes("static-paragraph") }) {
                                    Text("Wenn Sie den auf der Webseite angebotenen Newsletter beziehen möchten, benötigen wir von Ihnen eine E-Mail-Adresse sowie Informationen, welche uns die Überprüfung gestatten, dass Sie der Inhaber der angegebenen E-Mail-Adresse sind und mit dem Empfang des Newsletters einverstanden sind. Weitere Daten werden nicht erhoben. Diese Daten verwenden wir ausschließlich für den Versand der angeforderten Informationen und geben sie nicht an Dritte weiter. Die erteilte Einwilligung zur Speicherung der Daten, der E-Mail-Adresse sowie deren Nutzung zum Versand des Newsletters können Sie jederzeit widerrufen, etwa über den \"Austragen\"-Link im Newsletter.")
                                }
                                H3(attrs = { classes("static-section-title") }) {
                                    Text("Spotify")
                                }
                                P(attrs = { classes("static-paragraph") }) {
                                    Text("Auf unseren Seiten sind Funktionen des Musik-Dienstes Spotify eingebunden. Anbieter ist die Spotify AB, Birger Jarlsgatan 61, 113 56 Stockholm in Schweden. Die Spotify PlugIns erkennen Sie an dem grünen Logo auf unserer Seite. Eine Übersicht über die Spotify-PlugIns finden Sie unter https://developer.spotify.com Dadurch kann beim Besuch unserer Seiten über das Plugin eine direkte Verbindung zwischen Ihrem Browser und dem Spotify-Server hergestellt werden. Spotify erhält dadurch die Information, dass Sie mit Ihrer IP-Adresse unsere Seite besucht haben. Wenn Sie den Spotify Button anklicken während Sie in Ihrem Spotify-Account eingeloggt sind, können Sie die Inhalte unserer Seiten auf Ihrem Spotify Profil verlinken. Dadurch kann Spotify den Besuch unserer Seiten Ihrem Benutzerkonto zuordnen. Weitere Informationen hierzu finden Sie in der Datenschutzerklärung von Spotify: https://www.spotify.com/de/legal/privacy-policy/ Wenn Sie nicht wünschen, dass Spotify den Besuch unserer Seiten Ihrem Spotify-Nutzerkonto zuordnen kann, loggen Sie sich bitte aus Ihrem Spotify-Benutzerkonto aus.")
                                }
                                H3(attrs = { classes("static-section-title") }) {
                                    Text("YouTube")
                                }
                                P(attrs = { classes("static-paragraph") }) {
                                    Text("Unsere Webseite nutzt Plugins der von Google betriebenen Seite YouTube. Betreiber der Seiten ist die YouTube, LLC, 901 Cherry Ave., San Bruno, CA 94066, USA. Wenn Sie eine unserer mit einem YouTube- Plugin ausgestatteten Seiten besuchen, wird eine Verbindung zu den Servern von YouTube hergestellt. Dabei wird dem Youtube-Server mitgeteilt, welche unserer Seiten Sie besucht haben. Wenn Sie in Ihrem YouTube-Account eingeloggt sind ermöglichen Sie YouTube, Ihr Surfverhalten direkt Ihrem persönlichen Profil zuzuordnen. Dies können Sie verhindern, indem Sie sich aus Ihrem YouTube- Account ausloggen. Weitere Informationen zum Umgang von Nutzerdaten finden Sie in der Datenschutzerklärung von YouTube unter: https://www.google.de/intl/de/policies/privacy")
                                }
                                H3(attrs = { classes("static-section-title") }) {
                                    Text("SSL-Verschlüsselung")
                                }
                                P(attrs = { classes("static-paragraph") }) {
                                    Text("Diese Seite nutzt aus Gründen der Sicherheit und zum Schutz der Übertragung vertraulicher Inhalte, wie zum Beispiel der Anfragen, die Sie an uns als Seitenbetreiber senden, eine SSL-Verschlüsselung. Eine verschlüsselte Verbindung erkennen Sie daran, dass die Adresszeile des Browsers von \"http://\" auf \"https://\" wechselt und an dem Schloss-Symbol in Ihrer Browserzeile. Wenn die SSL Verschlüsselung aktiviert ist, können die Daten, die Sie an uns übermitteln, nicht von Dritten mitgelesen werden.")
                                }
                                H3(attrs = { classes("static-section-title") }) {
                                    Text("Widerspruch Werbe-Mails")
                                }
                                P(attrs = { classes("static-paragraph") }) {
                                    Text("Der Nutzung von im Rahmen der Impressumspflicht veröffentlichten Kontaktdaten zur Übersendung von nicht ausdrücklich angeforderter Werbung und Informationsmaterialien wird hiermit widersprochen. Die Betreiber der Seiten behalten sich ausdrücklich rechtliche Schritte im Falle der unverlangten Zusendung von Werbeinformationen, etwa durch Spam-E-Mails, vor.")
                                }
                            }
                        }
                    }
                }
                Div(attrs = { classes("sidebar-inner") }) {
                    Div(attrs = { classes("widget", "bg", "site-panel") }) {
                        Div(attrs = { classes("static-page-content") }) {
                            Div(attrs = { classes("static-section") }) {
                                H3(attrs = { classes("static-section-title") }) { Text("Adresse") }
                                Pre {
                                    Text(
                                        """
                                        B.S.T.
                                        Jan Galinski
                                        Klabautermannweg 35
                                        22457 Hamburg
                                        """.trimIndent()
                                    )
                                }
                            }
                            Div(attrs = { classes("static-section") }) {
                                H3(attrs = { classes("static-section-title") }) { Text("Vertreten durch") }
                                Ul(attrs = { classes("static-list") }) {
                                    listOf("Jan Galinski", "Heiko Wenck", "Lutz Reimer").forEach { name ->
                                        Li { Text(name) }
                                    }
                                }
                            }
                            Div(attrs = { classes("static-section") }) {
                                H3(attrs = { classes("static-section-title") }) { Text("Kontakt") }
                                Ul(attrs = { classes("static-list") }) {
                                    listOf(
                                        "Telefon: +49 170 916 37 45",
                                        "Telefax: +49 3222 336 8972",
                                        "E-Mail: jan@b-s-t.net",
                                    ).forEach { item ->
                                        Li { Text(item) }
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
