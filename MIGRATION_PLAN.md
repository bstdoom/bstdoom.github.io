# b.s.t. Website Rebuild Plan

## Current State

- The repository is still close to the default Kobweb starter.
- The active site code is minimal:
  - `site/src/jsMain/kotlin/io/github/bstdoom/AppEntry.kt`
  - `site/src/jsMain/kotlin/io/github/bstdoom/pages/Index.kt`
- Historical source material is available locally in `_tmp/`.
- `AGENTS.md` sets the working rule clearly:
  - Wayback snapshot is the source of truth for content.
  - Grav content and the `vocal` theme are reference material for structure and styling.

## What Is In `_tmp`

### Grav content

The useful content lives mainly in `_tmp/www/user/pages`.

- Main sections found there:
  - `01.home`
  - `02.musik`
  - `05.Info`
  - `99.english`
- There is also a `66.modular-sidebar` test page and helper content under `texte` / `images`.
- The page content surface is manageable:
  - about `44` markdown files
  - about `25` page-level media / document assets

### Theme structure

The useful theme material lives in `_tmp/theme`.

- Important Twig templates:
  - `templates/partials/base.html.twig`
  - `templates/partials/header.html.twig`
  - `templates/partials/footer.html.twig`
  - `templates/modular-sidebar.html.twig`
  - `templates/info.html.twig`
  - `templates/modular/about.html.twig`
  - `templates/modular/band.html.twig`
  - `templates/modular/sidebar.html.twig`
  - `templates/modular/links.html.twig`
  - `templates/modular/teaser.html.twig`
- Important CSS / assets:
  - `css/main.css`
  - `css/responsive.css`
  - `css/preset5.css`
  - `css/bootstrap.min.css`
  - `images/logo.png`
  - background images under `_tmp/www/user/pages/images/background`

### Real page patterns we need to reproduce

The old site is not a CMS problem anymore. It reduces cleanly to a few static page types:

- Home page made of modular sections:
  - teaser
  - about
  - band
  - links
- Music landing page
- Release detail pages with two-column layout:
  - main content on the left
  - sidebar widgets on the right
  - releases found: `Vier + 2`, `Hamburg City Doom`, `Die Illusion`, `Unter Deck`
- Info page with downloadable files in a sidebar
- Legal / promo modular pages
- English page with intro and lyrics

## Recommended Approach

Do not migrate Grav mechanically. Rebuild the site as a small static Kobweb app with:

- a fixed route tree
- reusable layout components
- static Kotlin data objects for page content
- reused archive assets copied into Kobweb public resources

This keeps the implementation simple and matches the project constraints.

## Execution Plan

## Phase 1: Establish Source Inventory

Goal: make migration deterministic before writing much UI.

Steps:

1. Create a content inventory document from the Wayback snapshot and `_tmp/www/user/pages`.
2. For each route, record:
   - final URL
   - page title
   - nav label
   - body layout type
   - source markdown files
   - required images / PDFs / thumbnails
3. Mark content mismatches where Wayback is newer than Grav.
4. Treat Wayback as canonical whenever content differs.

Deliverable:

- `docs/content-inventory.md` or similar, listing every target page and its sources.

## Phase 2: Build The Site Shell

Goal: replace the starter page with the real reusable frame.

Steps:

1. Create a shared page scaffold for:
   - header / navbar
   - main content container
   - footer
2. Recreate the old top navigation from the Grav hierarchy:
   - `Home`
   - `Musik`
   - `Info`
   - `en`
3. Port the base visual language from the theme:
   - black background
   - large Dosis typography
   - translucent dark navbar
   - section containers with `bg` panels
4. Decide how Bootstrap will be integrated:
   - simplest option is to import Bootstrap CSS only and reproduce missing behavior in Compose / custom CSS
   - avoid depending on old jQuery theme scripts unless a specific effect is necessary

Deliverable:

- a real homepage frame rendered by Kobweb instead of the starter placeholder.

## Phase 3: Port Shared Styling And Assets

Goal: make the rebuilt pages visually resemble the original early.

Steps:

1. Copy only the assets that are still needed into `site/src/jsMain/resources/public/`.
2. Port the relevant CSS from the theme into maintained project styles:
   - start with `main.css`, `responsive.css`, `preset5.css`
   - prune unused plugin styles like prettyPhoto / SoundCloud player styling unless still needed
3. Recreate the original background treatment using the archived background images.
4. Reuse `logo.png` and the band / cover images directly where possible.

Notes:

- The old theme includes several legacy JS plugins. Most should not be carried over.
- Prefer static rendering and CSS over importing outdated interactive dependencies.

Deliverable:

- header, section cards, typography, spacing, and backgrounds visually aligned with the old site.

## Phase 4: Implement Core Content Components

Goal: model the small number of recurring content blocks directly in Kotlin.

Components to build:

- `SiteScaffold`
- `HeroTeaserSection`
- `AboutSection`
- `BandSection`
- `LinksSection`
- `ContentWithSidebarPage`
- `SidebarWidget`
- `InfoDownloadsWidget`
- `SimpleMarkdownSection`

Data model to introduce:

- `NavItem`
- `Release`
- `DownloadItem`
- `SidebarBlock`
- `StaticPageSection`

Recommendation:

- keep content in Kotlin data files first
- only introduce markdown loading if it clearly reduces duplication without adding fragility

## Phase 5: Migrate Pages In Visible Order

Goal: maximize visible progress while preserving correctness.

Recommended sequence:

1. Home
2. Musik landing page
3. One release detail page end-to-end, preferably `Die Illusion` or `Unter Deck`
4. Remaining release pages
5. Info page with downloads
6. Impressum / Datenschutz
7. Promotion
8. English page

Reasoning:

- This gets the primary public-facing experience online early.
- The release pages will validate the two-column layout and sidebar pattern for most of the site.

## Phase 6: Content Reconciliation Against Wayback

Goal: ensure the rebuilt site follows the real source of truth.

Steps:

1. Compare each rebuilt page against the archived site.
2. Update text, ordering, file references, and media where the Grav source is older.
3. Record any pages or assets missing from local archive so they can be fetched or recreated later.

Deliverable:

- a short discrepancy log for unresolved source gaps.

## Phase 7: Static Export And Deployment

Goal: finish the project as a static GitHub Pages site.

Steps:

1. Verify Kobweb static export works for all routes.
2. Add GitHub Actions workflow to:
   - build
   - export static site
   - deploy to GitHub Pages
3. Confirm asset paths and internal links work under the GitHub Pages base path.

Deliverable:

- automated deploy pipeline to GitHub Pages.

## Suggested Near-Term Tasks

This is the order I would actually execute next in the repo:

1. Create a content inventory from Wayback plus `_tmp/www/user/pages`.
2. Replace the starter `Index.kt` with a real `SiteScaffold`.
3. Port the header, footer, typography, and background styling.
4. Build the modular home page sections.
5. Build the reusable two-column content-with-sidebar template.
6. Migrate the first release page completely.

## Things To Avoid

- Do not attempt to port Grav templates one-to-one.
- Do not carry over outdated jQuery plugin behavior unless it is clearly needed.
- Do not over-abstract content management; the site is small enough for static data structures.
- Do not trust `_tmp/www` content over Wayback when there is a conflict.

## Open Questions Before Major Implementation

- Which exact Wayback pages are complete and readable enough to serve as canonical copies for every route?
- Is Bootstrap still desired if Kobweb + custom CSS already covers the layout cleanly?
- Should lyrics remain embedded in release pages / English page, or also be given dedicated routes?
- Which legacy interactive elements are actually worth preserving:
  - dropdown navigation
  - sliders / carousels
  - embedded players

## Practical Conclusion

The repo is in a good place for an incremental rebuild. The archive is rich enough to reconstruct the structure, styling direction, and most assets locally, while the current Kobweb code is still small enough to reshape cleanly. The safest path is:

- inventory first
- shell and styling second
- migrate one real page pattern at a time
- reconcile against Wayback continuously
