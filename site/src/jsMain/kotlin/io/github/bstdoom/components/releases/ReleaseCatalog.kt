package io.github.bstdoom.components.releases

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import io.github.bstdoom.components.NavLink
import io.github.bstdoom.generated.DIE_ILLUSION_LYRICS_SECTIONS
import io.github.bstdoom.generated.DIE_ILLUSION_RELEASE_JSON
import io.github.bstdoom.generated.HAMBURG_CITY_DOOM_LYRICS_SECTIONS
import io.github.bstdoom.generated.HAMBURG_CITY_DOOM_RELEASE_JSON
import io.github.bstdoom.generated.HERBST_LYRICS_SECTIONS
import io.github.bstdoom.generated.HERBST_RELEASE_JSON
import io.github.bstdoom.generated.RELEASE_INDEX_JSON
import io.github.bstdoom.generated.UNTER_DECK_LYRICS_SECTIONS
import io.github.bstdoom.generated.UNTER_DECK_RELEASE_JSON
import io.github.bstdoom.generated.VIER_PLUS_ZWEI_LYRICS_SECTIONS
import io.github.bstdoom.generated.VIER_PLUS_ZWEI_RELEASE_JSON
import kotlin.js.JSON
import kotlin.js.jsTypeOf

@Composable
fun ReleasePageForSlug(slug: String) {
  val release = remember(slug) {
    val assets = releaseAssets(slug)
    parseReleasePage(assets.releaseJson, assets.lyricsSections)
  }
  ReleasePage(release)
}

data class ReleaseIndexEntry(
  val slug: String,
  val title: String,
  val coverImage: String,
  val year: Int,
  val releaseType: ReleaseKind,
  val showInNavigation: Boolean,
)

enum class ReleaseKind(
  val jsonValue: String,
  val sectionTitle: String,
  val displayLabel: String,
) {
  FULL_LENGTH("fullLength", "Full Length", "LP, CD"),
  EP("ep", "EP", "EP"),
  DEMO("demo", "Demo", "Demo");

  companion object {
    fun fromJson(value: String): ReleaseKind {
      return entries.firstOrNull { it.jsonValue == value }
        ?: error("Unknown release type: $value")
    }
  }
}

fun releaseIndexEntries(): List<ReleaseIndexEntry> {
  val rawEntries = JSON.parse<dynamic>(RELEASE_INDEX_JSON)
  return parseObjectArray(rawEntries).map { rawEntry ->
    ReleaseIndexEntry(
      slug = rawEntry.slug as String,
      title = rawEntry.title as String,
      coverImage = rawEntry.coverImage as String,
      year = rawEntry.year.toString().toInt(),
      releaseType = ReleaseKind.fromJson(rawEntry.releaseType as String),
      showInNavigation = rawBoolean(rawEntry.showInNavigation) ?: true,
    )
  }
}

fun releaseNavChildren(): List<NavLink> {
  return releaseIndexEntries()
    .asSequence()
    .filter { it.showInNavigation }
    .sortedBy { it.year }
    .map { entry ->
      NavLink(
        href = "/musik/${slugToPath(entry.slug)}/",
        label = "${entry.title} [${entry.releaseType.displayLabel}] (${entry.year})",
      )
    }
    .toList()
}

private data class ReleaseAssets(
  val releaseJson: String,
  val lyricsSections: List<Pair<String, List<String>>>,
)

private fun releaseAssets(slug: String): ReleaseAssets {
  return when (slug) {
    "herbst" -> ReleaseAssets(HERBST_RELEASE_JSON, HERBST_LYRICS_SECTIONS)
    "unter_deck" -> ReleaseAssets(UNTER_DECK_RELEASE_JSON, UNTER_DECK_LYRICS_SECTIONS)
    "die_illusion" -> ReleaseAssets(DIE_ILLUSION_RELEASE_JSON, DIE_ILLUSION_LYRICS_SECTIONS)
    "hamburg_city_doom" -> ReleaseAssets(HAMBURG_CITY_DOOM_RELEASE_JSON, HAMBURG_CITY_DOOM_LYRICS_SECTIONS)
    "vier_plus_zwei" -> ReleaseAssets(VIER_PLUS_ZWEI_RELEASE_JSON, VIER_PLUS_ZWEI_LYRICS_SECTIONS)
    else -> error("Unknown release slug: $slug")
  }
}

private fun parseObjectArray(rawValue: dynamic): Array<dynamic> {
  return if (rawValue == null || jsTypeOf(rawValue) == "undefined") {
    emptyArray()
  } else {
    rawValue as Array<dynamic>
  }
}

private fun rawBoolean(value: dynamic): Boolean? {
  return if (value == null || jsTypeOf(value) == "undefined") null else value as Boolean
}

private fun slugToPath(slug: String): String {
  return slug.replace('_', '-')
}
