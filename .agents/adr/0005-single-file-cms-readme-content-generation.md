# ADR 0005: Single-file CMS via README Content and Gradle Build-Time Generation

## Status

Accepted

## Context

The website needs lightweight, frequently updated content snippets (such as homepage kickers, news announcements, and tour notices) to be easily editable without touching Kotlin Compose frontend code or introducing a heavyweight dynamic CMS backend.

In addition, parsing markdown into HTML in the browser via custom regexes is brittle and doesn't scale to rich markdown features (lists, links, formatting, images).

## Decision

1. **Root README as Single-File CMS:** Treat the `## Content` section of the repository root `README.md` as a centralized single-file CMS. Each `### <Section Title>` subsection is treated as a dynamic content snippet accessible by key.
2. **Build-Time Generation:** The Gradle plugin (`SiteContentPlugin` via `GenerateReadmeContentSourceTask`) extracts these sections at build time and generates a Kotlin accessor object (`ReadmeContent`) containing `ReadmeContentEntry` models with both `html` (pre-rendered HTML) and `markdown` (raw text).
3. **Markdown Engine Selection (CommonMark):** Use `org.commonmark:commonmark` in the Gradle build-logic for Markdown-to-HTML conversion.

## Markdown Library Comparison & Choice

During design, two library options were considered:
- **`org.jetbrains:markdown` (Kotlin Multiplatform):** Offers multiplatform support across JVM and JS targets.
- **`org.commonmark:commonmark` (CommonMark Java):** Battle-tested CommonMark reference implementation for JVM.

**Winner:** `org.commonmark:commonmark` was chosen because Kobweb already uses `commonmark-java` internally in `kobwebx-markdown`. Standardizing on CommonMark ensures 100% consistent markdown processing behavior across static pages and snippet generation while keeping markdown parsing strictly at build time with 0 KB client-side runtime JS overhead.

## Consequences

- Content editors can update live notices directly by editing `README.md` on GitHub.
- Frontend components access snippets with clean syntax: `ReadmeContent["News"]`.
- Components can render the pre-rendered HTML directly (or inspect the raw Markdown if needed).
- Gradle input tracking (`@InputFile`) ensures reliable incremental builds and caching whenever `README.md` changes.
- Eliminates custom, fragile client-side markdown regex parsers.
