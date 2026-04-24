package io.github.bstdoom.pages

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.core.Page
import io.github.bstdoom.components.AboutSection
import io.github.bstdoom.components.BandMember
import io.github.bstdoom.components.BandSection
import io.github.bstdoom.components.BandSocialLink
import io.github.bstdoom.components.HomeHeroSection
import io.github.bstdoom.components.LinkGroup
import io.github.bstdoom.components.LinkItem
import io.github.bstdoom.components.LinksSection
import io.github.bstdoom.components.SiteScaffold

@Page
@Composable
fun HomePage() {
    SiteScaffold {
        HomeHeroSection()
        AboutSection()
        BandSection(
            members = listOf(
                BandMember(
                    name = "Heiko W.",
                    role = "Gitarre, Gesang",
                    info = "Gründungsmitglied, Freizeit-Folker",
                    image = "/assets/home/band-heiko.png",
                    socialLinks = listOf(
                        BandSocialLink("Facebook", "https://www.facebook.com/bst.band", listOf("fa", "fa-facebook")),
                    ),
                ),
                BandMember(
                    name = "Jan G.",
                    role = "Schlagzeug",
                    info = "Gründungsmitglied, Slayer-Maniac",
                    image = "/assets/home/band-jan.png",
                    socialLinks = listOf(
                        BandSocialLink("Facebook", "https://www.facebook.com/jan.galinski.74", listOf("fa", "fa-facebook")),
                        BandSocialLink("Twitter", "https://twitter.com/jangalinski", listOf("fa", "fa-twitter")),
                        BandSocialLink("YouTube", "https://www.youtube.com/channel/UCPyIu3_l2-VIlWdXzmEJFpQ", listOf("fa", "fa-youtube")),
                    ),
                ),
                BandMember(
                    name = "Lutz R.",
                    role = "Bass",
                    info = "seit 1998, Silberrücken",
                    image = "/assets/home/band-lutz.png",
                ),
            )
        )
        LinksSection(
            groups = listOf(
                LinkGroup(
                    title = "Bands",
                    items = listOf(
                        LinkItem("Rage of Samedi", "https://www.facebook.com/RageOfSamedi/?fref=ts"),
                        LinkItem("Apostle of Solitude", "http://apostleofsolitude.com/", "Indiana/US"),
                        LinkItem("Swamp Corpse", "http://swamp-corpse.com/", "Hamburg"),
                        LinkItem("Mayfair", "http://www.mayfairbrigade.com/"),
                        LinkItem("Crimson Swan", "http://www.crimsonswan.de/", "Hamburg"),
                        LinkItem("Opium Divan", "https://www.facebook.com/opiumdivan", "Paderborn"),
                        LinkItem("Dust", "http://www.dust-doom.de", "Bielefeld"),
                        LinkItem("The Giant's Vault", "https://www.facebook.com/The-Giants-Vault-1033417690033062/?fref=ts"),
                        LinkItem("The Hidden Spirit", "http://thehiddenspirit.de", "Hamburg"),
                        LinkItem("Shakhtyor", "https://www.facebook.com/shakhtyorband", "Hamburg"),
                    )
                ),
                LinkGroup(
                    title = "Produktion",
                    items = listOf(
                        LinkItem("RosenQuarz Tonstudio", "http://www.rosenquarz-tonstudio.de"),
                        LinkItem("Pink Tank Records", "https://www.pink-tank-records.de"),
                        LinkItem("Musikbunker Hamburg", "http://musikbunker-hamburg.de/"),
                        LinkItem("KS Musik (Presswerk)", "http://ks-musik.de/"),
                    )
                ),
                LinkGroup(
                    title = "Kontakt",
                    items = listOf(
                        LinkItem("band@b-s-t.net", "mailto:band@b-s-t.net"),
                        LinkItem("Bandcamp", "http://bstdoom.bandcamp.com/"),
                        LinkItem("Facebook (official)", "http://facebook.com/hamburgcitydoom"),
                        LinkItem("last.fm", "http://www.lastfm.de/music/B.S.T."),
                        LinkItem("metal-archives", "http://www.metal-archives.com/bands/B.S.T./3540310143"),
                    )
                ),
                LinkGroup(
                    title = "Magazine",
                    items = listOf(
                        LinkItem("Deaf Forever", "http://www.deaf-forever.de/"),
                        LinkItem("Doom Metal Front", "http://doommetalfront.blogspot.de/"),
                        LinkItem("Legacy", "http://www.legacy.de"),
                        LinkItem("Neckbreaker", "http://neckbreaker.de/"),
                        LinkItem("Obliveon", "http://www.obliveon.de/"),
                        LinkItem("Powermetal", "http://powermetal.de/"),
                        LinkItem("Rock Hard", "https://www.rockhard.de"),
                        LinkItem("Sacredmetal", "http://sacredsacredmetal.blogspot.de"),
                        LinkItem("Undergrounded", "http://www.undergrounded.de/"),
                        LinkItem("Streetclip.tv", "http://www.streetclip.tv/"),
                    )
                ),
            )
        )
    }
}
