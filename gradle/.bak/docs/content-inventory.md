# Content Inventory

## Purpose

This document maps the historical Grav content in `_tmp/www/user/pages` to the target static Kobweb routes.

Working rules from `AGENTS.md`:

- Wayback snapshot is the canonical content source.
- Local Grav content and theme files are structural and stylistic references.

Status of this inventory:

- route and source mapping is based on the local archive
- Wayback verification is still pending for per-page text parity because the snapshot could not be reliably inspected from the current environment during this pass

## Target Navigation

Top-level navigation inferred from the archive:

- `/` -> `Home`
- `/musik/` -> `Musik`
- `/info/` -> `Info`
- `/english/` -> `en`

Likely secondary routes:

- `/musik/vier_plus_zwei/`
- `/musik/hamburg_city_doom/`
- `/musik/die_illusion/`
- `/musik/unter_deck/`
- `/info/impressum/`
- `/info/promotion/`

## Page Inventory

## `/`

- Menu label: `Home`
- Grav source: `_tmp/www/user/pages/01.home/modular.md`
- Layout type: modular landing page
- Modules in display order:
  - `_tmp/www/user/pages/01.home/_teaser/teaser.de.md`
  - `_tmp/www/user/pages/01.home/_about/about.de.md`
  - `_tmp/www/user/pages/01.home/_band/band.de.md`
  - `_tmp/www/user/pages/01.home/_links/links.de.md`
- Assets:
  - `_tmp/www/user/pages/01.home/_about/bandfoto.jpg`
  - `_tmp/www/user/pages/01.home/_band/band-heiko.png`
  - `_tmp/www/user/pages/01.home/_band/band-jan.png`
  - `_tmp/www/user/pages/01.home/_band/band-jan2.png`
  - `_tmp/www/user/pages/01.home/_band/band-lutz.png`
- Notes:
  - this is the most important route to rebuild first
  - the page is modular and maps well to Kotlin section components

## `/musik/`

- Menu label: `Musik`
- Grav source: `_tmp/www/user/pages/02.musik/index.md`
- Layout type: modular overview page
- Supporting intro modules:
  - `_tmp/www/user/pages/02.musik/_01.LP/texte.de.md`
  - `_tmp/www/user/pages/02.musik/_02.EP/texte.de.md`
  - `_tmp/www/user/pages/02.musik/_03.Demo/texte.de.md`
- Child release pages:
  - `vier_plus_zwei`
  - `hamburg_city_doom`
  - `die_illusion`
  - `unter_deck`
- Notes:
  - this route likely acts as both an overview and gateway to release detail pages
  - Wayback should be checked to confirm ordering and whether all releases were shown in nav/content

## `/musik/vier_plus_zwei/`

- Menu label: `Vier+2 [Demo] (2000)`
- Grav source: `_tmp/www/user/pages/02.musik/00.vier_plus_zwei/modular-sidebar.md`
- Layout type: content-with-sidebar
- Main content modules:
  - `_tmp/www/user/pages/02.musik/00.vier_plus_zwei/_texte/content.de.md`
- Sidebar modules:
  - `_tmp/www/user/pages/02.musik/00.vier_plus_zwei/__01.sidebar_tracks/sidebar.de.md`
- Assets:
  - `_tmp/www/user/pages/02.musik/00.vier_plus_zwei/cover.jpg`
- Notes:
  - `visible: false` in Grav, but it may still be linked from the music page or Wayback
  - verify whether this page should remain publicly routable in the rebuild

## `/musik/hamburg_city_doom/`

- Menu label: `Hamburg City Doom [EP] (2009)`
- Grav source: `_tmp/www/user/pages/02.musik/01.hamburg_city_doom/modular-sidebar.md`
- Layout type: content-with-sidebar
- Main content modules:
  - `_tmp/www/user/pages/02.musik/01.hamburg_city_doom/_texte/content.md`
- Sidebar modules:
  - `_tmp/www/user/pages/02.musik/01.hamburg_city_doom/__01.sidebar_tracks/sidebar.de.md`
  - `_tmp/www/user/pages/02.musik/01.hamburg_city_doom/__03.sidebar_reviews/sidebar.de.md`
- Assets:
  - `_tmp/www/user/pages/02.musik/01.hamburg_city_doom/cover.jpg`
- Notes:
  - no Spotify widget in the local archive for this release
  - review block exists and should become a sidebar widget

## `/musik/die_illusion/`

- Menu label: `Die Illusion [LP,CD] (2013)`
- Grav source: `_tmp/www/user/pages/02.musik/02.die_illusion/modular-sidebar.de.md`
- Layout type: content-with-sidebar
- Main content modules:
  - `_tmp/www/user/pages/02.musik/02.die_illusion/_01.texte/content.de.md`
- Sidebar modules:
  - `_tmp/www/user/pages/02.musik/02.die_illusion/__01.sidebar_tracks/sidebar.de.md`
  - `_tmp/www/user/pages/02.musik/02.die_illusion/__02.sidebar_spotify/sidebar.de.md`
  - `_tmp/www/user/pages/02.musik/02.die_illusion/__03.sidebar_reviews/sidebar.de.md`
- Assets:
  - `_tmp/www/user/pages/02.musik/02.die_illusion/cover.jpg`
- Notes:
  - this is a good candidate for the first full release-page migration
  - it exercises all major content patterns: cover, lyrics/body copy, track list, embed, review list

## `/musik/unter_deck/`

- Menu label: `Unter Deck [LP, CD] (2017)`
- Grav source: `_tmp/www/user/pages/02.musik/03.unter_deck/modular-sidebar.md`
- Layout type: content-with-sidebar
- Main content modules:
  - `_tmp/www/user/pages/02.musik/03.unter_deck/_01.texte/content.de.md`
- Sidebar modules:
  - `_tmp/www/user/pages/02.musik/03.unter_deck/__01.sidebar_tracks/sidebar.de.md`
  - `_tmp/www/user/pages/02.musik/03.unter_deck/__02.sidebar_spotify/sidebar.de.md`
  - `_tmp/www/user/pages/02.musik/03.unter_deck/__03.sidebar_reviews/sidebar.de.md`
- Assets:
  - `_tmp/www/user/pages/02.musik/03.unter_deck/cover.jpg`
  - `_tmp/www/user/pages/02.musik/03.unter_deck/voodoo-chamber-logo.jpg`
- Notes:
  - this is the newest release present locally
  - `content.de.md` includes long-form lyrics and is likely a high-value parity check against Wayback

## `/info/`

- Menu label: `Info`
- Grav source: `_tmp/www/user/pages/05.Info/info.de.md`
- Layout type: main content plus downloads sidebar
- Assets embedded or referenced in body/sidebar:
  - `_tmp/www/user/pages/05.Info/live.jpg`
  - `_tmp/www/user/pages/05.Info/bst-bandfoto.jpg`
  - `_tmp/www/user/pages/05.Info/bst-bandfoto.jpg-thumb.png`
  - `_tmp/www/user/pages/05.Info/bst-logo.tiff`
  - `_tmp/www/user/pages/05.Info/bst-logo.tiff-thumb.png`
  - `_tmp/www/user/pages/05.Info/bst-technical-rider.pdf`
  - `_tmp/www/user/pages/05.Info/bst-technical-rider.pdf-thumb.png`
  - `_tmp/www/user/pages/05.Info/bst-unterdeck-promo.pdf`
  - `_tmp/www/user/pages/05.Info/bst-unterdeck-promo.pdf-thumb.png`
- Structured downloadable items defined in front matter:
  - logo
  - band photo
  - technical rider
  - promo flyer
- Notes:
  - this page should get its own specialized sidebar widget component

## `/info/impressum/`

- Menu label: not explicitly visible in top nav
- Grav source: `_tmp/www/user/pages/05.Info/10.Impressum/modular.md`
- Layout type: modular static text page
- Modules:
  - `_tmp/www/user/pages/05.Info/10.Impressum/_01.impressum/texte.md`
  - `_tmp/www/user/pages/05.Info/10.Impressum/_02.datenschutz/texte.md`
- Assets:
  - `_tmp/www/user/pages/05.Info/10.Impressum/_01.impressum/impressum.pdf`
  - `_tmp/www/user/pages/05.Info/10.Impressum/_02.datenschutz/datenschutzerklaerung.pdf`
- Notes:
  - likely better represented in Kobweb as either:
    - one combined legal page, matching current Grav structure
    - or two dedicated pages if Wayback shows them separately
  - verify the final public URL structure on Wayback before implementation

## `/info/promotion/`

- Menu label: not visible in top nav, likely linked from `Info`
- Grav source: `_tmp/www/user/pages/05.Info/20.Promotion/modular.md`
- Layout type: modular static text page
- Modules:
  - `_tmp/www/user/pages/05.Info/20.Promotion/_01.promo_deutsch/texte.de.md`
  - `_tmp/www/user/pages/05.Info/20.Promotion/_02.promo_english/texte.de.md`
- Notes:
  - `visible: false` in Grav
  - likely still useful as a hidden public route or downloadable/promoter reference page

## `/english/`

- Menu label: `en`
- Grav source: `_tmp/www/user/pages/99.english/modular.md`
- Layout type: modular static page
- Modules in display order:
  - `_tmp/www/user/pages/99.english/_01.intro/texte.de.md`
  - `_tmp/www/user/pages/99.english/_05.lyrics/columns.md`
- Notes:
  - this page is a good fit for simple stacked sections
  - title is `English`, but menu label is only `en`

## Archive-Only / Non-Route Content

These files appear to be supporting or test content and should not automatically become public routes:

- `_tmp/www/user/pages/66.modular-sidebar/**`
- `_tmp/www/user/pages/images/background/default.md`
- `_tmp/www/user/pages/texte/default.de.md`
- `_tmp/www/user/pages/texte/Stimmen.md`

Potential use:

- `66.modular-sidebar` is useful as a reference implementation of the old sidebar layout pattern
- `images/background/*` are needed as design assets
- `texte/*` may be leftovers or helper lyric pages and should be checked against Wayback before inclusion

## Shared Assets To Port

Theme assets likely needed early:

- `_tmp/theme/images/logo.png`
- `_tmp/theme/css/main.css`
- `_tmp/theme/css/responsive.css`
- `_tmp/theme/css/preset5.css`
- `_tmp/theme/css/bootstrap.min.css`

Background assets:

- `_tmp/www/user/pages/images/background/bg1.png`
- `_tmp/www/user/pages/images/background/bg2.png`
- `_tmp/www/user/pages/images/background/bg3.png`
- `_tmp/www/user/pages/images/background/bg4.png`

## Implementation Priorities

Suggested migration order based on visibility and component reuse:

1. `/`
2. shared header / footer / background styling
3. `/musik/`
4. `/musik/die_illusion/` or `/musik/unter_deck/`
5. remaining release pages
6. `/info/`
7. `/info/impressum/`
8. `/info/promotion/`
9. `/english/`

## Verification Gaps

These items still need Wayback confirmation:

- exact public route structure and URL casing
- whether hidden Grav pages were publicly linked in the archived site
- whether the local markdown matches the final archived text for:
  - home teaser and about copy
  - release reviews and lyrics
  - info page downloads and descriptions
  - legal and promotion pages
- whether any pages or assets exist in Wayback that are newer than the local archive
