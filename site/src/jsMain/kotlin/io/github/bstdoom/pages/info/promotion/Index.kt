package io.github.bstdoom.pages.info.promotion

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.core.Page
import io.github.bstdoom.components.SiteScaffold
import io.github.bstdoom.components.StaticListSection
import io.github.bstdoom.components.StaticPageLayout
import io.github.bstdoom.components.StaticTextSection

@Page
@Composable
fun PromotionPage() {
    SiteScaffold {
        StaticPageLayout(title = "Promotion") {
            StaticTextSection(
                title = "Promo (deutsch)",
                paragraphs = listOf(
                    "Moin XXX,",
                    "Ihr braucht noch einen Support für Euer Festival ? Dann sucht nicht länger!",
                    "Wir sind B.S.T. aus Hamburg und spielen Hamburg City Doom - langsam, melancholisch, traditionell mit einer gehörigen Prise Sludge, viel Melodie und deutschsprachigen Lyrics.",
                    "Zu Buche stehen mittlerweile über 40 Gigs, unter anderem mit Pentagram, Procession, Apostle of Solitude, Naevus, Order of Israfel, Ophis, Doomocracy, Saturnus, Mayfair, Spirit Descent, Bone Man, Dust, Shakthyor und Body Farm.",
                    "Unser aktuelles Album \"Unter Deck\" ist 2017 auf Voodoo Chamber Records erschienen, und bekommt durch die Bank sehr gute Kritiken in Print und Online.",
                    "Reinhören könnt Ihr hier: https://bstdoom.bandcamp.com/album/unter-deck | https://open.spotify.com/album/41nUKzpKHci7aY7e0GULdw",
                    "Reviews findet Ihr hier: https://www.b-s-t.net/musik/unter_deck#Reviews",
                    "Es wäre uns eine Ehre, auch für euch spielen zu dürfen!",
                    "Einen Eindruck von unseren Live Qualitäten könnt ihr euch bei Youtube machen: https://www.youtube.com/channel/UCZcM2xHbSAL9o1HiJu8ZO1g",
                    "Freut uns, von euch zu hören!",
                    "B.S.T.",
                )
            )
            StaticListSection(
                title = "Kontakt",
                items = listOf(
                    "telefon: Jan +49 170 9163745",
                    "Mail: band@b-s-t.net",
                    "Homepage: http://www.b-s-t.net/",
                    "Facebook: http://facebook.com/hamburgCityDoom",
                )
            )
            StaticTextSection(
                title = "Promo (english)",
                paragraphs = listOf(
                    "Greetings XXX,",
                    "you are searching for a support band for your festival? Then look no further!",
                    "We are B.S.T. from Hamburg/Germany, and we play Hamburg City Doom - slow, traditional with a bit of sludge, lots of melody and german lyrics.",
                    "We played more than 40 Gigs, including such great acts as Pentagram, Procession, Apostle of Solitude, Naevus, Order of Israfel, Ophis, Doomocracy, Saturnus, Mayfair, Spirit Descent, Bone Man, Dust, Shakthyor und Body Farm.",
                    "Our latest album \"Unter Deck\" was released via Voodoo Chamber Records in 2017 and received very good reviews in print and online magazines.",
                    "Listen to it here: https://bstdoom.bandcamp.com/album/unter-deck | https://open.spotify.com/album/41nUKzpKHci7aY7e0GULdw",
                    "Reviews (in german): https://www.b-s-t.net/musik/unter_deck#Reviews",
                    "It would be an honor for us to play for you!",
                    "To get an impression of what we look like on stage, check our youtube-channel: https://www.youtube.com/channel/UCZcM2xHbSAL9o1HiJu8ZO1g",
                    "Looking forward to hearing from you",
                    "B.S.T",
                )
            )
        }
    }
}
