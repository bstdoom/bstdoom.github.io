package io.github.bstdoom.roq

data class Release(
  var slug: String = "",
  var title: String = "",
  var coverImage: String = "",
  var thumbnailImage: String = "",
  var coverAlt: String = "",
  var releaseType: String = "",
  var year: Int = 0,
  var showInNavigation: Boolean = true,
  var facts: MutableList<String> = arrayListOf(),
  var tracks: MutableList<Track> = arrayListOf(),
  var bandcampPlayerUrl: String? = null,
  var spotifyEmbedUrl: String? = null,
  var itunesUrl: String? = null,
  var bandcampUrl: String? = null,
  var bandcampVinylUrl: String? = null,
  var actionLinks: MutableList<ReleaseAction> = arrayListOf(),
  var reviews: MutableList<Review> = arrayListOf(),
)
