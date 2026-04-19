package io.github.bstdoom.pages.info.promotion

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.core.Page
import io.github.bstdoom.components.NavLink
import io.github.bstdoom.components.SiteScaffold
import io.github.bstdoom.components.StaticListSection
import io.github.bstdoom.components.StaticPageLayout
import io.github.bstdoom.components.StaticTextSection

@Page
@Composable
fun PromotionPage() {
    SiteScaffold(
        navLinks = listOf(
            NavLink("/", "Home"),
            NavLink("/musik/", "Musik"),
            NavLink("/info/", "Info"),
            NavLink("/english/", "en"),
        )
    ) {
        StaticPageLayout(title = "Promotion") {
            StaticTextSection(
                title = "Promo (deutsch)",
                paragraphs = listOf(
                    "Moin XXX, Ihr braucht noch einen Support für Euer Festival? Dann sucht nicht länger.",
                    "Wir sind B.S.T. aus Hamburg und spielen Hamburg City Doom: langsam, melancholisch, traditionell mit einer gehörigen Prise Sludge, viel Melodie und deutschsprachigen Lyrics.",
                    "Unser aktuelles Album \"Unter Deck\" ist 2017 auf Voodoo Chamber Records erschienen und bekam durch die Bank sehr gute Kritiken in Print und Online.",
                )
            )
            StaticListSection(
                title = "Bisherige Gigs",
                items = listOf(
                    "Pentagram, Procession, Apostle of Solitude, Naevus",
                    "Order of Israfel, Ophis, Doomocracy, Saturnus, Mayfair",
                    "Spirit Descent, Bone Man, Dust, Shakthyor und Body Farm",
                )
            )
            StaticListSection(
                title = "Links",
                items = listOf(
                    "Bandcamp: https://bstdoom.bandcamp.com/album/unter-deck",
                    "Spotify: https://open.spotify.com/album/41nUKzpKHci7aY7e0GULdw",
                    "YouTube: https://www.youtube.com/channel/UCZcM2xHbSAL9o1HiJu8ZO1g",
                )
            )
            StaticTextSection(
                title = "Promo (english)",
                paragraphs = listOf(
                    "Greetings XXX, if you are searching for a support band for your festival, look no further.",
                    "We are B.S.T. from Hamburg/Germany, and we play Hamburg City Doom: slow, traditional with a bit of sludge, lots of melody and German lyrics.",
                    "Our latest album \"Unter Deck\" was released via Voodoo Chamber Records in 2017 and received very good reviews in print and online magazines.",
                )
            )
            StaticListSection(
                title = "Contact",
                items = listOf(
                    "Phone: Jan +49 170 9163745",
                    "Mail: band@b-s-t.net",
                    "Homepage: http://www.b-s-t.net/",
                    "Facebook: http://facebook.com/hamburgCityDoom",
                )
            )
        }
    }
}
