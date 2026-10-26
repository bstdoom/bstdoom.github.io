package io.github.bstdoom.roq

import io.quarkiverse.roq.data.runtime.annotations.DataMapping

@DataMapping(value = "bandMembers", type = DataMapping.Type.ARRAY_FILE)
open class BandMembers(
    open var list: List<BandMember> = mutableListOf(),
)
