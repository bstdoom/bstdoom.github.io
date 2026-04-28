package io.github.bstdoom.components.releases

import androidx.compose.runtime.Composable
import kotlin.js.JSON
import kotlin.js.jsTypeOf

data class ReleaseActionLink(
  val label: String,
  val href: String,
  val kind: ReleaseActionKind = ReleaseActionKind.Generic,
)

enum class ReleaseActionKind {
  Spotify,
  AppleMusic,
  Bandcamp,
  YouTube,
  Generic,
}

data class ReleasePageData(
  val title: String,
  val coverImage: String,
  val coverAlt: String,
  val facts: List<String>,
  val tracks: List<Track>,
  val bandcampPlayerUrl: String? = null,
  val spotifyEmbedUrl: String? = null,
  val itunesUrl: String? = null,
  val bandcampUrl: String? = null,
  val bandcampVinylUrl: String? = null,
  val actionLinks: List<ReleaseActionLink> = emptyList(),
  val reviews: List<ReviewQuote> = emptyList(),
  val lyricsSections: List<Pair<String, List<String>>> = emptyList(),
)

@Composable
fun ReleasePage(release: ReleasePageData) {
  ReleaseDetailLayout(
    title = release.title,
    coverImage = release.coverImage,
    coverAlt = release.coverAlt,
    body = {
      ReleaseFactsList(facts = release.facts)
      ReleaseLyricsSection(sections = release.lyricsSections)
    },
    sidebar = {
      TracksWidget(tracks = release.tracks)
      BuyListenWidget(
        playerUrl = release.bandcampPlayerUrl,
        spotifyUrl = spotifyPageUrl(release.spotifyEmbedUrl),
        links = buildList {
          release.itunesUrl?.let {
            add(ReleaseActionLink(label = "iTunes", href = it, kind = ReleaseActionKind.AppleMusic))
          }
          release.bandcampVinylUrl?.let {
            add(ReleaseActionLink(label = "Bandcamp Vinyl", href = it, kind = ReleaseActionKind.Bandcamp))
          }
          addAll(release.actionLinks)
        }
      )
      if (release.reviews.isNotEmpty()) {
        ReviewsWidget(reviews = release.reviews)
      }
    }
  )
}

internal fun parseReleasePage(jsonText: String, lyricsSections: List<Pair<String, List<String>>>): ReleasePageData {
  val raw = JSON.parse<dynamic>(jsonText)
  return ReleasePageData(
    title = raw.title as String,
    coverImage = raw.coverImage as String,
    coverAlt = raw.coverAlt as String,
    facts = parseStringArray(raw.facts),
    tracks = parseTracks(raw.tracks),
    bandcampPlayerUrl = rawString(raw.bandcampPlayerUrl),
    spotifyEmbedUrl = rawString(raw.spotifyEmbedUrl),
    itunesUrl = rawString(raw.itunesUrl),
    bandcampUrl = rawString(raw.bandcampUrl),
    bandcampVinylUrl = rawString(raw.bandcampVinylUrl),
    actionLinks = parseReleaseActionLinks(raw.actionLinks),
    reviews = parseReviewQuotes(raw.reviews),
    lyricsSections = lyricsSections,
  )
}

private fun parseReleaseActionLinks(rawValue: dynamic): List<ReleaseActionLink> {
  if (rawValue == null || jsTypeOf(rawValue) == "undefined") {
    return emptyList()
  }

  return parseObjectArray(rawValue).map { rawLink ->
    ReleaseActionLink(
      label = rawLink.label as String,
      href = rawLink.href as String,
      kind = parseActionKind(rawString(rawLink.kind), rawLink.label as String, rawString(rawLink.href)),
    )
  }
}

private fun parseTracks(rawValue: dynamic): List<Track> {
  return parseObjectArray(rawValue).map { rawTrack ->
    Track(
      name = rawTrack.name as String,
      time = rawTrack.time as String,
    )
  }
}

private fun parseReviewQuotes(rawValue: dynamic): List<ReviewQuote> {
  return parseObjectArray(rawValue).map { rawReview ->
    ReviewQuote(
      source = rawReview.source as String,
      url = rawString(rawReview.url),
      quote = rawReview.quote as String,
    )
  }
}

private fun parseStringArray(rawValue: dynamic): List<String> {
  return parseObjectArray(rawValue).map { it as String }
}

private fun parseObjectArray(rawValue: dynamic): Array<dynamic> {
  return if (rawValue == null || jsTypeOf(rawValue) == "undefined") {
    emptyArray()
  } else {
    rawValue as Array<dynamic>
  }
}

private fun rawString(value: dynamic): String? {
  return if (value == null || jsTypeOf(value) == "undefined") null else value as String
}

private fun parseActionKind(rawValue: String?, label: String, href: String?): ReleaseActionKind {
  return when (rawValue?.lowercase() ?: inferActionKind(label, href)) {
    "applemusic", "itunes" -> ReleaseActionKind.AppleMusic
    "bandcamp" -> ReleaseActionKind.Bandcamp
    "spotify" -> ReleaseActionKind.Spotify
    "youtube" -> ReleaseActionKind.YouTube
    else -> ReleaseActionKind.Generic
  }
}

private fun inferActionKind(label: String, href: String?): String {
  val normalizedLabel = label.lowercase()
  val normalizedHref = href?.lowercase().orEmpty()
  return when {
    "spotify.com" in normalizedHref -> "spotify"
    "youtube" in normalizedLabel || "youtube.com" in normalizedHref || "youtu.be" in normalizedHref -> "youtube"
    "bandcamp" in normalizedLabel || "bandcamp.com" in normalizedHref -> "bandcamp"
    normalizedLabel == "itunes" || "music.apple.com" in normalizedHref || "itunes.apple.com" in normalizedHref -> "itunes"
    else -> "generic"
  }
}

private fun spotifyPageUrl(embedUrl: String?): String? {
  if (embedUrl == null) {
    return null
  }

  val normalized = embedUrl
    .replace("/embed/album/", "/album/")
    .replace("/embed/track/", "/track/")
    .substringBefore('?')

  return normalized
}
