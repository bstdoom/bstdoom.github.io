package io.github.bstdoom.roq

import io.quarkiverse.roq.data.runtime.annotations.DataMapping

@DataMapping(value = "homeLinks", type = DataMapping.Type.ARRAY_FILE)
open class HomeLinks(
  open var list: List<HomeLinkGroup> = mutableListOf(),
)
