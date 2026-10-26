package io.github.bstdoom.roq

import io.quarkiverse.roq.data.runtime.annotations.DataMapping

@DataMapping(value = "releaseCatalog", type = DataMapping.Type.OBJECT_DIR)
open class ReleaseCatalog(
    open var map: MutableMap<String, Release> = linkedMapOf(),
)
