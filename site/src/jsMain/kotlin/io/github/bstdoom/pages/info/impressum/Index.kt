package io.github.bstdoom.pages.info.impressum

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.core.Page
import io.github.bstdoom.components.NavLink
import io.github.bstdoom.components.SiteScaffold
import io.github.bstdoom.components.StaticListSection
import io.github.bstdoom.components.StaticPageLayout
import io.github.bstdoom.components.StaticTextSection

@Page
@Composable
fun ImpressumPage() {
    SiteScaffold(
        navLinks = listOf(
            NavLink("/", "Home"),
            NavLink("/musik/", "Musik"),
            NavLink("/info/", "Info"),
            NavLink("/english/", "en"),
        )
    ) {
        StaticPageLayout(title = "Impressum") {
            StaticTextSection(
                title = "Impressum",
                paragraphs = listOf(
                    "Quelle: e-recht24.de",
                    "Angaben gemäß § 5 TMG: B.S.T., Jan Galinski, Klabautermannweg 35, 22457 Hamburg.",
                )
            )
            StaticListSection(
                title = "Vertreten durch",
                items = listOf(
                    "Jan Galinski",
                    "Heiko Wenck",
                    "Lutz Reimer",
                    "Jan Rudßuck",
                )
            )
            StaticListSection(
                title = "Kontakt",
                items = listOf(
                    "Telefon: +49 170 916 37 45",
                    "Telefax: +49 3222 336 8972",
                    "E-Mail: jan@b-s-t.net",
                )
            )
            StaticTextSection(
                title = "Streitschlichtung",
                paragraphs = listOf(
                    "Wir sind nicht bereit oder verpflichtet, an Streitbeilegungsverfahren vor einer Verbraucherschlichtungsstelle teilzunehmen.",
                )
            )
            StaticTextSection(
                title = "Haftung für Inhalte",
                paragraphs = listOf(
                    "Als Diensteanbieter sind wir gemäß § 7 Abs.1 TMG für eigene Inhalte auf diesen Seiten nach den allgemeinen Gesetzen verantwortlich.",
                    "Nach §§ 8 bis 10 TMG sind wir als Diensteanbieter jedoch nicht verpflichtet, übermittelte oder gespeicherte fremde Informationen zu überwachen.",
                    "Bei Bekanntwerden von entsprechenden Rechtsverletzungen werden wir diese Inhalte umgehend entfernen.",
                )
            )
            StaticTextSection(
                title = "Haftung für Links",
                paragraphs = listOf(
                    "Unser Angebot enthält Links zu externen Webseiten Dritter, auf deren Inhalte wir keinen Einfluss haben.",
                    "Bei Bekanntwerden von Rechtsverletzungen werden wir derartige Links umgehend entfernen.",
                )
            )
            StaticTextSection(
                title = "Urheberrecht",
                paragraphs = listOf(
                    "Die durch die Seitenbetreiber erstellten Inhalte und Werke auf dieser Seite unterliegen dem deutschen Urheberrecht.",
                    "Downloads und Kopien dieser Seite sind nur für den privaten, nicht kommerziellen Gebrauch gestattet.",
                )
            )
            StaticTextSection(
                title = "Datenschutzerklärung",
                paragraphs = listOf(
                    "Die Betreiber dieser Seiten nehmen den Schutz Ihrer persönlichen Daten sehr ernst und behandeln personenbezogene Daten vertraulich entsprechend der gesetzlichen Datenschutzvorschriften.",
                    "Cookies können eingesetzt werden, um das Angebot nutzerfreundlicher, effektiver und sicherer zu machen.",
                    "Auf der Seite eingebundene Spotify- und YouTube-Funktionen können technische Verbindungen zu den jeweiligen Diensten herstellen.",
                    "Wenn die SSL-Verschlüsselung aktiviert ist, können die Daten, die Sie an uns übermitteln, nicht von Dritten mitgelesen werden.",
                )
            )
        }
    }
}
