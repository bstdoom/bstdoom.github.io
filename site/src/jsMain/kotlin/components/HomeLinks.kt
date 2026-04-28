package io.github.bstdoom.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import io.github.bstdoom.generated.HOME_LINKS_JSON
import kotlin.js.JSON
import kotlin.js.jsTypeOf

@Composable
fun LinksSection() {
  val groups = remember { parseLinkGroups(HOME_LINKS_JSON) }
  LinksSection(groups)
}

internal fun parseLinkGroups(jsonText: String): List<LinkGroup> {
  val rawGroups = JSON.parse<Array<dynamic>>(jsonText)
  return rawGroups.map { rawGroup ->
    val rawItems = rawGroup.items as Array<dynamic>
    LinkGroup(
      title = rawGroup.title as String,
      items = rawItems.map { rawItem ->
        LinkItem(
          label = rawItem.label as String,
          href = rawItem.href as String,
          location = rawString(rawItem.location),
        )
      },
    )
  }
}

private fun rawString(value: dynamic): String? {
  return if (value == null || jsTypeOf(value) == "undefined") null else value as String
}
