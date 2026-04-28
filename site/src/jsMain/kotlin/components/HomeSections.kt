package io.github.bstdoom.components

import androidx.compose.runtime.*
import io.github.bstdoom.generated.ABOUT_JSON
import kotlinx.coroutines.delay
import kotlin.js.JSON
import kotlin.js.jsTypeOf
import org.jetbrains.compose.web.dom.*

data class BandSocialLink(
  val href: String,
  val type: BandSocialType,
)

enum class BandSocialType {
  Facebook,
  Twitter,
  YouTube,
}

data class BandMember(
  val name: String,
  val role: String,
  val info: String,
  val image: String,
  val socialLinks: List<BandSocialLink> = emptyList(),
)

data class LinkGroup(
  val title: String,
  val items: List<LinkItem>,
)

data class LinkItem(
  val label: String,
  val href: String,
  val location: String? = null,
)

@Composable
fun HomeHeroSection() {
  Section(attrs = { id("home") }) {
    Div(attrs = { classes("hero-copy") }) {
      Div(attrs = { classes("home-kicker") }) {
        Text("In Neuformierung - Mund abputzen und weiter")
      }
      H1 {
        Text("B.S.T. - Hamburg City Doom")
      }
    }
  }
}

@Composable
fun HomePanelSection(
  title: @Composable () -> Unit,
  content: @Composable () -> Unit,
) {
  Section(attrs = { classes("bg", "site-panel") }) {
    H2(attrs = { classes("heading") }) {
      title()
    }
    content()
  }
}

@Composable
private fun Emphasis(text: String) {
  Span(
    attrs = {
      style {
        property("font-weight", "700")
      }
    }
  ) {
    Text(text)
  }
}

@Composable
fun AboutSection() {
  val about = rememberAboutSectionData()
  HomePanelSection(
    title = {
      Text("Über ")
      Emphasis("uns")
    }
  ) {
    Div(attrs = { classes("about-grid") }) {
      Img(
        src = "/assets/home/bandfoto.jpg",
        attrs = {
          attr("alt", "B.S.T. Bandfoto")
          classes("about-photo")
        }
      )
      Div(attrs = { classes("about-content") }) {
        H3 {
          Text("Bio")
        }
        about.bioParagraphs.forEach { paragraph ->
          P {
            Text(paragraph)
          }
        }
        H3 {
          Text("Steckbrief")
        }
        Ul {
          about.facts.forEach { fact ->
            Li { Text(fact) }
          }
        }
      }
    }
  }
}

private data class AboutSectionData(
  val bioParagraphs: List<String>,
  val facts: List<String>,
)

@Composable
private fun rememberAboutSectionData(): AboutSectionData {
  return remember(ABOUT_JSON) { parseAboutSectionData(ABOUT_JSON) }
}

private fun parseAboutSectionData(jsonText: String): AboutSectionData {
  val raw = JSON.parse<dynamic>(jsonText)
  return AboutSectionData(
    bioParagraphs = parseStringArray(raw.bioParagraphs),
    facts = parseStringArray(raw.facts),
  )
}

private fun parseStringArray(rawValue: dynamic): List<String> {
  return if (rawValue == null || jsTypeOf(rawValue) == "undefined") {
    emptyList()
  } else {
    (rawValue as Array<dynamic>).map { it as String }
  }
}

@Composable
fun BandSection(members: List<BandMember>) {
  HomePanelSection(
    title = {
      Text("Die ")
      Emphasis("Band")
    }
  ) {
    Div(attrs = { classes("performers-grid") }) {
      members.forEach { member ->
        Div(attrs = { classes("perfomer-wrapper", "single-performer") }) {
          Img(
            src = member.image,
            attrs = {
              attr("alt", member.name)
              classes("single-performer-image")
            }
          )
          Div(attrs = { classes("overlay") }) {
            Div(attrs = { classes("overley-content") }) {
              H3 {
                Text(member.name)
              }
              H4(attrs = { classes("performer-role") }) {
                Text(member.role)
              }
              P {
                Text(member.info)
              }
              if (member.socialLinks.isNotEmpty()) {
                Div(attrs = { classes("social-icons") }) {
                  member.socialLinks.forEach { social ->
                    A(
                      href = social.href,
                      attrs = {
                        attr("target", "_blank")
                        attr("rel", "noopener noreferrer")
                        attr("aria-label", social.type.displayName())
                      }
                    ) {
                      org.jetbrains.compose.web.dom.I(attrs = { classes(*social.type.iconClasses().toTypedArray()) })
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
}

private fun BandSocialType.iconClasses(): List<String> = when (this) {
  BandSocialType.Facebook -> listOf("fa", "fa-facebook")
  BandSocialType.Twitter -> listOf("fa", "fa-twitter")
  BandSocialType.YouTube -> listOf("fa", "fa-youtube")
}

private fun BandSocialType.displayName(): String = when (this) {
  BandSocialType.Facebook -> "Facebook"
  BandSocialType.Twitter -> "Twitter"
  BandSocialType.YouTube -> "YouTube"
}

@Composable
fun LinksSection(groups: List<LinkGroup>) {
  var activeIndex by remember(groups) { mutableIntStateOf(0) }

  LaunchedEffect(groups) {
    if (groups.size > 1) {
      while (true) {
        delay(12000)
        activeIndex = (activeIndex + 1) % groups.size
      }
    }
  }

  HomePanelSection(
    title = {
      Text("Freunde & ")
      Emphasis("Links")
    }
  ) {
    Div(attrs = { classes("links-carousel") }) {
      Div(attrs = { classes("links-carousel-viewport") }) {
        Div(
          attrs = {
            classes("links-carousel-track")
            style {
              property("transform", "translateX(-${activeIndex * 100}%)")
            }
          }
        ) {
          groups.forEach { group ->
            Div(attrs = { classes("links-carousel-slide") }) {
              H3(attrs = { classes("links-group-title") }) {
                Text(group.title)
              }
              Div(attrs = { classes("links-grid") }) {
                group.items.forEach { item ->
                  A(
                    href = item.href,
                    attrs = {
                      classes("links-item")
                      attr("target", "_blank")
                      attr("rel", "noopener noreferrer")
                    }
                  ) {
                    Emphasis(item.label)
                    item.location?.let {
                      Text(" ($it)")
                    }
                  }
                }
              }
            }
          }
        }
      }
      if (groups.size > 1) {
        Div(attrs = { classes("links-carousel-controls") }) {
          Button(
            attrs = {
              classes("links-carousel-control")
              attr("type", "button")
              attr("aria-label", "Previous section")
              onClick { activeIndex = (activeIndex - 1 + groups.size) % groups.size }
            }
          ) {
            Text("‹")
          }
          Button(
            attrs = {
              classes("links-carousel-control")
              attr("type", "button")
              attr("aria-label", "Next section")
              onClick { activeIndex = (activeIndex + 1) % groups.size }
            }
          ) {
            Text("›")
          }
        }
        Div(attrs = { classes("links-carousel-dots") }) {
          groups.forEachIndexed { index, group ->
            Button(
              attrs = {
                classes("links-carousel-dot")
                if (index == activeIndex) {
                  classes("selected")
                }
                attr("type", "button")
                attr("aria-label", "Show ${group.title}")
                onClick { activeIndex = index }
              }
            ) {
              Text(group.title)
            }
          }
        }
      }
    }
  }
}
