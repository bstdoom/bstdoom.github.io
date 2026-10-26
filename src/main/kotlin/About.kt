package io.github.bstdoom.roq

import io.quarkiverse.roq.data.runtime.annotations.DataMapping

@DataMapping("about")
open class About(
  open var bioParagraphs: List<String> = mutableListOf(),
  open var facts: List<String> = mutableListOf(),
)
