package io.github.bstdoom.components

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.dom.A
import org.jetbrains.compose.web.dom.Br
import org.jetbrains.compose.web.dom.B
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H2
import org.jetbrains.compose.web.dom.H3
import org.jetbrains.compose.web.dom.Iframe
import org.jetbrains.compose.web.dom.Img
import org.jetbrains.compose.web.dom.Li
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Section
import org.jetbrains.compose.web.dom.Text
import org.jetbrains.compose.web.dom.Ol
import org.jetbrains.compose.web.dom.Ul

data class Track(
    val name: String,
    val time: String,
)

data class ReviewQuote(
    val source: String,
    val url: String? = null,
    val quote: String,
)

@Composable
fun ReleaseDetailLayout(
    title: String,
    coverImage: String,
    coverAlt: String,
    body: @Composable () -> Unit,
    sidebar: @Composable () -> Unit,
) {
    Section(attrs = { classes("release-detail-page") }) {
        Div(attrs = { classes("release-detail-grid") }) {
            Div(attrs = { classes("release-main-column") }) {
                Div(attrs = { classes("site-content", "bg", "site-panel") }) {
                    H2(attrs = { classes("heading") }) {
                        Text(title)
                    }
                    Img(
                        src = coverImage,
                        attrs = {
                            attr("alt", coverAlt)
                            classes("release-detail-cover")
                        }
                    )
                    body()
                }
            }
            Div(attrs = { classes("sidebar-inner") }) {
                sidebar()
            }
        }
    }
}

@Composable
fun ReleaseLyricsSection(
    sections: List<Pair<String, List<String>>>,
) {
    Div(attrs = { classes("release-lyrics") }) {
        sections.forEach { (heading, paragraphs) ->
            H3(attrs = { classes("release-subheading") }) {
                Text(heading)
            }
            paragraphs.forEach { paragraph ->
                P(attrs = { classes("release-paragraph") }) {
                    paragraph.split('\n').forEachIndexed { index, line ->
                        if (index > 0) {
                            Br()
                        }
                        Text(line)
                    }
                }
            }
        }
    }
}

@Composable
fun ReleaseFactsList(
    facts: List<String>,
) {
    Ul(attrs = { classes("release-facts") }) {
        facts.forEach { fact ->
            Li {
                Text(fact)
            }
        }
    }
}

@Composable
fun SidebarWidget(
    title: String,
    content: @Composable () -> Unit,
) {
    Div(attrs = { classes("widget", "bg", "site-panel") }) {
        H3(attrs = { classes("widget-title") }) {
            Text(title)
        }
        content()
    }
}

@Composable
fun TracksWidget(tracks: List<Track>) {
    SidebarWidget(title = "Tracks") {
        Ol(attrs = { classes("play-list") }) {
            tracks.forEach { track ->
                Li {
                    B { Text(track.name) }
                    Text(" (${track.time})")
                }
            }
        }
    }
}

@Composable
fun TrackActionLink(
    label: String,
    href: String,
) {
    Div(attrs = { classes("track-action-link") }) {
        A(href = href) {
            Text(label)
        }
    }
}

@Composable
fun SpotifyWidget(embedUrl: String) {
    SidebarWidget(title = "Player") {
        Iframe(
            attrs = {
                attr("src", embedUrl)
                attr("width", "100%")
                attr("height", "380")
                attr("allowtransparency", "true")
                classes("spotify-embed")
            }
        )
    }
}

@Composable
fun ReviewsWidget(reviews: List<ReviewQuote>) {
    SidebarWidget(title = "Reviews") {
        Div(attrs = { classes("reviews-list") }) {
            reviews.forEach { review ->
                Div(attrs = { classes("review-item") }) {
                    if (review.url != null) {
                        A(href = review.url, attrs = { classes("review-source") }) {
                            Text(review.source)
                        }
                    } else {
                        Div(attrs = { classes("review-source") }) {
                            Text(review.source)
                        }
                    }
                    Div(attrs = { classes("review-quote") }) {
                        Text(review.quote)
                    }
                }
            }
        }
    }
}
