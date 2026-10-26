package io.github.bstdoom.roq

data class BandMember(
    var name: String = "",
    var role: String = "",
    var info: String = "",
    var image: String = "",
    var socialLinks: List<SocialLink> = mutableListOf(),
)
