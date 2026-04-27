# ADR 0002: Self-host Dosis fonts and document the choice in `.agents/`

## Status

Accepted

## Context

The site uses the Dosis typeface as part of the original visual style. We replaced the Google Fonts dependency with local font files under `site/src/jsMain/resources/public/fonts/dosis/` so the site no longer relies on an external font CDN at runtime or export time.

The Dosis family is open source and can be self-hosted under its font license. The legal text is stored with the font files in `OFL.txt`.

## Decision

Keep the Dosis font binaries in `site/src/jsMain/resources/public/fonts/dosis/` and document the self-hosting decision in `.agents/adr/`.

## Consequences

- The website is self-contained for typography.
- The font files remain in the natural asset location for fonts.
- The rationale for bundling the fonts is recorded in the agent documentation instead of a separate repo note inside the asset tree.

## Rationale

The asset directory should contain the actual font binaries and their license text. The project-level reasoning belongs in `.agents/`, where other architectural notes live. That keeps the repository tidy while still preserving the licensing and implementation decision for future maintenance.
