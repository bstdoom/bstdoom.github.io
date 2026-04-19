# Handoff Note

## Current State

- Home page scaffold is implemented and theme-driven.
- Theme CSS is active via `site/src/jsMain/resources/public/css/theme.css`.
- `Musik` section is implemented with working Kobweb-generated routes:
  - `/musik/`
  - `/musik/die-illusion/`
  - `/musik/unter-deck/`
  - `/musik/hamburg-city-doom/`
  - `/musik/vier-plus-zwei/`
- Release detail pages use the shared component in:
  - `site/src/jsMain/kotlin/io/github/bstdoom/components/ReleaseDetail.kt`

## Important Dev Notes

- For normal content and style edits, browser refresh should usually be enough.
- For newly added or moved routes, Kobweb may need a restart if the browser does not pick them up.
- In this Codex shell environment, `localhost:8081` was not reachable via `curl` even when Kobweb was running, so route verification was done through generated Kobweb metadata instead.

## Next Pickup Point

Implement the `Info` section next.

Suggested order:

1. Add `/info/` route from `_tmp/www/user/pages/05.Info/info.de.md`
2. Recreate the info layout with:
   - main content on the left
   - downloads/files widget on the right
3. Port these assets first:
   - `live.jpg`
   - `bst-bandfoto.jpg`
   - `bst-logo.tiff` or a usable export if browser support is awkward
   - `bst-technical-rider.pdf`
   - `bst-unterdeck-promo.pdf`
   - existing thumb PNGs
4. After `/info/`, implement:
   - `/info/impressum/`
   - `/info/promotion/`
   - `/english/`

## Source References

- Plan: `MIGRATION_PLAN.md`
- Content inventory: `docs/content-inventory.md`
- Archive source of interest:
  - `_tmp/www/user/pages/05.Info`
  - `_tmp/www/user/pages/99.english`
  - `_tmp/theme/templates/info.html.twig`
  - `_tmp/theme/templates/modular-sidebar.html.twig`
