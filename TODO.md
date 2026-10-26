# Roq migration plan

This is a step-by-step migration plan for rebuilding the current Kobweb site in
the root Roq project. The current Kobweb export is the rendered HTML/assets
snapshot to compare against.

The migration is incremental. Each phase should leave the Roq site runnable and
should be verified before the next phase starts.

## Working rules

- Treat the rendered homepage in `_kobweb-export/pages/index.html` as the
  initial visual/markup baseline, not as application code to copy wholesale.
- Do not copy Kobweb runtime output from `_kobweb-export/system/` or the
  `_kobweb` worker files. Roq owns the document shell and static generation.
- Preserve the existing public URLs, asset paths, navigation labels, German
  homepage content, and external links unless a deliberate migration decision
  says otherwise.
- Prefer Roq content, Qute templates, data files, CSS, and small browser-side
  scripts over introducing Kotlin application code for presentation.
- Keep the first implementation deliberately boring: establish the shared
  shell and one static page before migrating interactive behavior or all pages.

## Phase 0: freeze the reference and record the route map

- [x] Confirm the current baseline with the Kobweb export:
  `_kobweb-export/pages/index.html`.
- [x] Record the existing route map:
  - `/`
  - `/musik/`
  - `/musik/hamburg-city-doom/`
  - `/musik/die-illusion/`
  - `/musik/unter-deck/`
  - `/musik/herbst/`
  - `/musik/vier-plus-zwei/`
  - `/info/`
  - `/info/impressum/`
  - `/english.html`
- [x] Record the homepage sections and their order: hero/news, About, Band,
  Friends & Links, footer.
- [x] Use the current Kotlin components as the migration map:
  `SiteScaffold.kt`, `HomeSections.kt`, `HomeLinks.kt`,
  `components/releases/`, and `pages/Index.kt`.
- [x] Keep the exported HTML available for visual comparison while the Roq
  version is being built.

## Phase 1: establish the reusable Roq theme/template shell

This is the first implementation phase and should be completed before adding
the full homepage.

- [x] Define the Roq document shell in
  `templates/layouts/base.html`:
  - `<!doctype html>` and `lang="de"`
  - charset and responsive viewport
  - page title and description from frontmatter/site data
  - favicon
  - Font Awesome stylesheet
  - the vocal-theme stylesheet
  - `{#insert /}` for page content
- [x] Create reusable partials under `templates/partials/`:
  - `head.html` for metadata and stylesheet links
  - `navigation.html` for the logo, primary navigation, dropdown placeholders,
    and social links
  - `footer.html` for the Roq and GitHub attribution
- [x] Decide the Roq theme boundary before copying page content:
  the base layout, navigation, footer, typography, panels, and responsive rules
  belong to the reusable theme; homepage-specific sections stay in
  `content/index.html` or homepage partials.
- [x] Keep the first navigation implementation static and explicit. Add
  generated release navigation only after the release data model is migrated.
- [x] Preserve the existing asset URLs with the migrated files under
  `public/`:
  - `assets/theme/logo.png`
  - `assets/background/bg1.png` through `bg4.png`
  - `css/font-awesome.min.css`
  - `fonts/fontawesome-*`
  - `fonts/dosis/*` including `OFL.txt`
  - `favicon.ico`
- [x] Bring over `theme.css` as the initial visual baseline. Do not rewrite all
  1,178 lines during the first pass; first make the existing selectors apply to
  the equivalent semantic HTML.
- [x] Initially use `bg1.png` as a fixed body background. Defer rotating
  backgrounds until the static shell has been visually verified.
- [x] Add a minimal template-only page that proves the shell renders a title,
  navigation, content slot, footer, fonts, logo, and CSS.

## Phase 2: create the simple Roq homepage

Start with a deliberately small page so the template and styling can be
verified independently of data parsing and browser interaction.

- [x] Create `content/index.html` with frontmatter for:
  - title: `B.S.T.`
  - description: `B.S.T. - Hamburg City Doom`
  - German site language
- [x] Render the hero section:
  - news/kicker: `Wir sind wieder da!`
  - heading: `B.S.T. - Hamburg City Doom`
- [x] Render the About panel using the exact text visible in the current
  exported homepage:
  - `data/about.json`
  - `/assets/home/bandfoto.jpg`
- [x] Initially render the About data directly from a Roq data file or page
  frontmatter. Choose one representation and keep it readable; do not recreate
  the Kotlin/JS JSON parsing layer.
- [x] Render the homepage through the shared base layout and verify that the
  generated `index.html` contains ordinary static HTML with no Kobweb runtime
  dependency.
- [x] Run the full local loop:
  - `./gradlew roqGenerate`
  - `./gradlew roqServe`
  - inspect the generated homepage and CSS in `build/roq/`
  - verify `/`, `/musik/`, `/info/`, `/info/impressum/`, and `/english.html`
    through the static server (HTTP 200 on port 8181).
- [ ] Compare the simple page with the exported homepage at desktop and mobile
  widths before adding Band and Friends & Links.

## Phase 3: migrate the remaining homepage sections

- [x] Migrate the Band panel from the Kobweb band data:
  - preserve member order, names, roles, descriptions, images, and social URLs
  - preserve the grayscale image treatment and purple hover overlay
  - make the rendered information accessible without relying only on hover
- [x] Migrate Friends & Links from the Kobweb link data:
  - preserve all four groups and their links
  - preserve labels, locations, external-link behavior, and ordering
  - render all groups as static sections while carousel behavior is deferred
- [x] Add the links carousel as progressive enhancement around static HTML:
  one group is visible at a time, with previous/next controls, labeled tabs,
  keyboard focus state, and automatic 12-second rotation.
- [x] Add the rotating background as progressive enhancement using the four
  existing background assets and the original 12-second interval.
- [x] Add the mobile navigation toggle as progressive enhancement. The page
  must remain navigable if JavaScript is unavailable.

## Phase 4: move homepage data into stable Roq content/data files

- [x] Choose JSON data files plus Kotlin `@DataMapping` classes for typed Qute
  access.
- [x] Migrate the homepage data files:
  - `about.json`
  - `bandMembers.json`
  - `homeLinks.json`
- [x] Replace the current build-generated `ReadmeContent["News"]` dependency
  with the Roq-owned `data/home.json` source. The root README remains outside
  the Roq content pipeline for now.
- [x] Keep content editing separate from layout/template changes.

## Phase 5: migrate the remaining pages and navigation

- [x] Create the `/musik/` page from a typed Roq release catalog and the
  release JSON files.
- [x] Create one Roq page per release using the existing public slugs and cover
  images.
- [x] Migrate lyrics from the current release resources into readable Roq
  content files, preserving section order and German text.
- [x] Keep one full-size cover source per release and generate ignored
  thumbnails during the Gradle build for the `/musik/` cards.
- [x] Create `/info/`, `/info/impressum/`, and `/english.html` from the current
  page sources and Markdown resources.
- [x] Rebuild the `Musik` dropdown from migrated release metadata after the
  release pages work independently.
- [x] Rebuild the `Info` dropdown and verify every internal link with the static
  server and generated file tree.

## Phase 6: visual and behavioral parity review

- [ ] Compare every route against its corresponding file under
  `_kobweb-export/pages/`.
- [ ] Check desktop, tablet, and mobile layouts:
  - header height and sticky behavior
  - dropdown positioning
  - panel spacing and opacity
  - typography and Dosis font weights
  - band-card overlays
  - links layout/carousel
  - footer alignment
- [x] Check static asset references for case, URL, and base-path correctness.
- [ ] Check keyboard navigation, focus states, alt text, external-link behavior,
  and mobile menu accessibility.
- [x] Verify generated output contains only intended HTML/CSS/assets and does
  not contain Kobweb runtime files.

## Phase 7: retire the legacy implementation

- [x] Roq static export is accepted and is the deployed site.
- [x] Remove the obsolete `site/` and `worker/` modules after the Roq parity
  review.
- [x] Remove Kobweb, Compose, and Kotlin Multiplatform build dependencies.
- [x] Keep `gradle/build-logic` for shared build logic.

## First implementation checklist

The first Roq migration change should stop after these items are complete:

- [x] Shared `base.html` layout exists.
- [x] Navigation and footer partials exist.
- [x] Dosis, Font Awesome, logo, favicon, background, and vocal CSS are wired.
- [x] `content/index.html` renders the hero and About panel.
- [x] `./gradlew roqGenerate` succeeds.
- [x] `./gradlew roqServe` serves the generated page.
- [ ] The generated page is visually comparable to the corresponding Kobweb
  export at `/`.
