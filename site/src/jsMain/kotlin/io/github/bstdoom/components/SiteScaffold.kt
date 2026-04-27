package io.github.bstdoom.components

import androidx.compose.runtime.Composable
import io.github.bstdoom.components.releases.releaseNavChildren
import kotlinx.browser.window
import org.jetbrains.compose.web.dom.*

data class NavLink(
  val href: String,
  val label: String,
  val children: List<NavLink> = emptyList(),
  val iconClasses: List<String> = emptyList(),
)

private val infoNavChildren = listOf(
  NavLink("/info/impressum/", "Impressum"),
)

private val socialNavLinks = listOf(
  NavLink(
    "https://www.facebook.com/hamburgCityDoom",
    "Facebook",
    iconClasses = listOf("fa", "fa-facebook"),
  ),
  NavLink(
    "https://www.youtube.com/@bstdoom",
    "YouTube",
    iconClasses = listOf("fa", "fa-youtube"),
  ),
  NavLink(
    "https://bstdoom.bandcamp.com/",
    "Bandcamp",
    iconClasses = listOf("fa", "fa-bandcamp"),
  ),
)

private val mainNavLinks = listOf(
  NavLink("/", "Home"),
  NavLink("/musik/", "Musik"),
  NavLink("/info/", "Info"),
  NavLink("/english/", "en"),
)

@Composable
fun SiteScaffold(content: @Composable () -> Unit) {
  val currentPath = window.location.pathname
  val renderedLinks = mainNavLinks.map { link ->
    when (link.label) {
      "Musik" -> link.copy(children = releaseNavChildren())
      "Info" -> link.copy(children = infoNavChildren)
      else -> link
    }
  }

  Div(attrs = { classes("site-shell") }) {
    Div(attrs = { id("navigation") }) {
      Div(attrs = { classes("site-container", "site-header-row") }) {
        A(href = "/", attrs = { classes("navbar-brand") }) {
          Img(
            src = "/assets/theme/logo.png",
            attrs = {
              attr("alt", "B.S.T.")
              classes("site-logo")
            }
          )
        }
        Div(attrs = { classes("navbar-menu-group") }) {
          Ul(attrs = { classes("navbar-nav", "nav-primary") }) {
            renderedLinks.forEach { link ->
              NavItem(link = link, currentPath = currentPath)
            }
          }
          Ul(attrs = { classes("navbar-nav", "nav-social") }) {
            socialNavLinks.forEach { link ->
              Li(attrs = { classes("social-item") }) {
                A(
                  href = link.href,
                  attrs = {
                    classes("social-link")
                    attr("target", "_blank")
                    attr("rel", "noopener noreferrer")
                    attr("aria-label", link.label)
                  }
                ) {
                  I(attrs = { classes(*link.iconClasses.toTypedArray()) })
                  Span(attrs = { classes("sr-only") }) {
                    Text(link.label)
                  }
                }
              }
            }
          }
        }
      }
    }

    Div(attrs = { classes("site-container", "site-main") }) {
      content()
    }

    Footer(attrs = { classes("site-container", "site-footer") }) {
      Div(attrs = { classes("footer") }) {
        Div(attrs = { classes("footer-left") }) {
          A(
            href = "https://github.com/bstdoom/bstdoom.github.io",
            attrs = {
              classes("footer-social-link")
              attr("target", "_blank")
              attr("rel", "noopener noreferrer")
              attr("aria-label", "GitHub repository")
            }
          ) {
            I(attrs = { classes("fa", "fa-github") })
          }
          Span(attrs = { classes("footer-copy") }) {
            Text("powered by ")
            A(href = "https://kobweb.varabyte.com/") {
              Text("Kobweb")
            }
            Text(" and ")
            A(
              href = "https://github.com/bstdoom/bstdoom.github.io",
              attrs = {
                attr("target", "_blank")
                attr("rel", "noopener noreferrer")
              }
            ) {
              Text("GitHub")
            }
          }
        }
        Div(attrs = { classes("footer-right") }) {
          Span(attrs = { classes("footer-copy") }) {
            Text("vocal theme by ")
            A(href = "http://www.themeum.com/") {
              Text("Themeum")
            }
          }
        }
      }
    }
  }
}

@Composable
private fun NavItem(
  link: NavLink,
  currentPath: String,
) {
  val hasChildren = link.children.isNotEmpty()
  val normalizedLink = link.href.trimEnd('/')
  val isSelected = if (normalizedLink.isEmpty()) {
    currentPath == "/"
  } else {
    currentPath == normalizedLink || currentPath.startsWith("$normalizedLink/")
  }

  Li(attrs = {
    if (isSelected) {
      classes("selected")
    }
    if (hasChildren) {
      classes("dropdown")
    }
  }) {
    A(href = link.href) {
      Text(link.label)
      if (hasChildren) {
        I(attrs = { classes("fa", "fa-angle-down") })
      }
    }
    if (hasChildren) {
      Ul(attrs = { classes("sub-menu") }) {
        link.children.forEach { child ->
          val normalizedChild = child.href.trimEnd('/')
          val childSelected = currentPath == normalizedChild || currentPath.startsWith("$normalizedChild/")
          Li(attrs = {
            if (childSelected) {
              classes("selected")
            }
          }) {
            A(href = child.href) {
              Text(child.label)
            }
          }
        }
      }
    }
  }
}

@Composable
private fun FooterColumn(
  title: String,
  links: List<NavLink>,
) {
  Div(attrs = { classes("quick-links") }) {
    Div(attrs = { classes("footer-title") }) { Text(title) }
    Ul {
      links.forEach { link ->
        Li {
          A(
            href = link.href,
            attrs = {
              if (link.href.startsWith("http")) {
                attr("target", "_blank")
                attr("rel", "noopener noreferrer")
              }
            }
          ) {
            Text(link.label)
          }
        }
      }
    }
  }
}
