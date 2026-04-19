package io.github.bstdoom.pages.english

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.core.Page
import io.github.bstdoom.components.NavLink
import io.github.bstdoom.components.SiteScaffold
import io.github.bstdoom.components.StaticPageLayout
import io.github.bstdoom.components.StaticTextSection

@Page
@Composable
fun EnglishPage() {
    SiteScaffold(
        navLinks = listOf(
            NavLink("/", "Home"),
            NavLink("/musik/", "Musik"),
            NavLink("/info/", "Info"),
            NavLink("/english/", "en"),
        )
    ) {
        StaticPageLayout(title = "English") {
            StaticTextSection(
                title = "Intro",
                paragraphs = listOf(
                    "B.S.T. is a traditional four-piece doom metal band from Hamburg, Germany. The band was founded in 1994 under the name \"Blut Schweiß Tränen\".",
                    "Looking back, 2009 became the band's real birth year, when the line-up was completed and the remaining hardcore/doomcore roots were left behind in favor of heavy, traditional doom metal with modern sound and heart-breaking melodies.",
                    "One trademark of B.S.T. is the consistent use of German vocals. The band believes the deep emotions in the lyrics work best when they do not have to be translated.",
                    "After the 2009 EP \"Hamburg City Doom\" and the full-length \"Die Illusion\", the band joined forces with Voodoo Chamber Records for \"Unter Deck\" in 2017.",
                )
            )
            StaticTextSection(
                title = "Lyrics",
                paragraphs = listOf(
                    "VOICES: Voices I don't understand seem to be so close, but I can't find a beginning. Drowning in the masses, all alone, because something is missing.",
                    "GIVING UP: The silent view over the sea is my solace, it keeps me here. Never giving you up, that's why I haven't lost you.",
                    "BURN: Look around, there's no human being far and wide. Burn, soul burn, I watch before I run.",
                    "CHANCE: So many questions, so much happened. Tell me why the rescue keeps being refused, one last time.",
                )
            )
        }
    }
}
