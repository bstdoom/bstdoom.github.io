package io.github.bstdoom.roq

import io.quarkiverse.roq.data.runtime.annotations.DataMapping

@DataMapping("home")
open class Home(
  open var news: String = "",
)
