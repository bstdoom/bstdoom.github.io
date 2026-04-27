package io.github.bstdoom.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import io.github.bstdoom.generated.BAND_MEMBERS_JSON
import kotlin.js.JSON
import kotlin.js.jsTypeOf

@Composable
fun BandSection() {
  val members = remember { parseBandMembers(BAND_MEMBERS_JSON) }
  BandSection(members)
}

internal fun parseBandMembers(jsonText: String): List<BandMember> {
  val rawMembers = JSON.parse<Array<dynamic>>(jsonText)
  return rawMembers.map { rawMember ->
    BandMember(
      name = rawMember.name as String,
      role = rawMember.role as String,
      info = rawMember.info as String,
      image = rawMember.image as String,
      socialLinks = parseBandSocialLinks(rawMember.socialLinks),
    )
  }
}

private fun parseBandSocialLinks(rawValue: dynamic): List<BandSocialLink> {
  if (rawValue == null || jsTypeOf(rawValue) == "undefined") {
    return emptyList()
  }

  val rawLinks = rawValue as Array<dynamic>
  return rawLinks.map { rawLink ->
    BandSocialLink(
      href = rawLink.href as String,
      type = parseBandSocialType(rawLink.type as String),
    )
  }
}

private fun parseBandSocialType(value: String): BandSocialType = when (value.lowercase()) {
  "facebook" -> BandSocialType.Facebook
  "twitter" -> BandSocialType.Twitter
  "youtube" -> BandSocialType.YouTube
  else -> error("Unsupported band social link type: $value")
}
