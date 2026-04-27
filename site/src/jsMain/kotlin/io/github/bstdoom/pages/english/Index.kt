package io.github.bstdoom.pages.english

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.core.Page
import io.github.bstdoom.components.SiteScaffold
import io.github.bstdoom.components.StaticPageLayout
import io.github.bstdoom.components.StaticTextSection

@Page
@Composable
fun EnglishPage() {
  SiteScaffold {
    StaticPageLayout(title = "English") {
      StaticTextSection(
        title = "Intro",
        paragraphs = listOf(
          "B.S.T, is a traditional four-piece Doom Metal band located in Hamburg, Germany. The band was originally founded by singer, guitarist and main song-writer Heiko Wenck and Drummer Jan Galinski in 1994 under the name \"Blut Schweiß Tränen\" which translates to \"Blood Sweat Tears\". Yes, we weren't aware of the Psychadelic-Rock Band of the same name these days, today we just use the abbreviation and leave interpretation of the true meaning to the observer.",
          "Over the years we made some major changes both in line-up and musical style. Looking back, we consider 2009 to be our \"real\" birth-year, when the the line-up was completed with Jan Rudßuck on Lead-guitar (and of course Lutz Reimer who had already played bass with us for several years back then). It was around the same time we dropped all remaing hardcore/doomcore roots and concentrated on what we've always done best: heavy, traditional doom metal combined with modern sound and heart-breaking melodies.",
          "One of our trademarks is the consequent use of german vocals since the early beginnings. We believe that the deep and intense emotions that characterize Heikos lyrics work best when they do not have to be translated.",
          "in 2009 we recorded our Debut EP \"Hamburg City Doom\" and earned some reputation, followed by our first full-length LP \"Die Illusion\" (\"The Illusion\") 2012 which got some attention and nice reviews in the german doom-underground. We released both CDs (\"Die Illusion\" is also available on vinyl) on our own and didn't work with a label so far, but that hopefully will change with the next album we are currently working on. All our releases are available on bandcamp. For the latest release \"Unter Deck\" (2017) we joined forces with the underground label voodoo chamber records.",
          "We publish our musc in german only, but for those of you who'd like to know what we sing about, here are a few translations:",
        )
      )
      StaticTextSection(
        title = "Lyrics",
        paragraphs = listOf(
          "VOICES\n\nVoices I don’t understand\nSeem to be so close\nBut I can’t find a beginning\nDrowning in the masses, all alone\nBecause something is missing\nThe feeling so intense",
          "GIVING UP\n\nYes, the silent view over the sea\nIs my solace, it keeps me here\nMore and more you seem to drift away\nMy face is turned to you",
          "BURN\n\nLook around\nThere’s no human being far and wide\nThe days are numbered\nDeep down here, below deck\nWhile the waves are rolling\nI hear you say\nThere’s no escape",
          "CHANCE\n\nSo many questions\nSo many questions\nI do not want to leave\nI do not want to go\nI do not want to complain anymore\nNor feel or see\nI don’t want tears\nAnd also no pain",
        )
      )
    }
  }
}
