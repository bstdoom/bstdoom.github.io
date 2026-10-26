# Project: b.s.t. Website (Quarkus Roq)

## Context

This repository contains the static website of the doom metal band **b.s.t.** The active implementation is the Kotlin-only Quarkus Roq site at the repository root.

The previous website is broken and cannot be repaired. The goal is to **reproduce it as closely as possible** and then modernize the implementation.

### Historical Sources

- Archived version of the site (primary reference for content and structure):
  https://web.archive.org/web/20250812070114/http://www.b-s-t.net/

- Local project archive (inside this repo):
  - `_tmp/`: contains the original **Grav CMS project** that generated the site
  - `_tmp/theme/`: contains the purchased theme ("vocal") used for layout and styling

### Important Notes

- The Grav CMS project is **outdated**
- The archived website contains **newer content than the Grav project**
- The archived website should be treated as the **source of truth for content**
- The Grav project and theme should be used as **reference for structure and styling**

---

## Goal

The website is a **static site generated with Quarkus Roq**, preserving:

- Content (1:1 copy where possible)
- Structure
- Visual style (based on the "vocal" theme)

---

## Development Strategy

### Phase 1: Build and preview

- Use the Kotlin-only root Gradle project
- Generate static output with `just roq-generate`
- Preview development output with `just roq-serve-dev`

### Phase 2: Layout Structure

Establish a consistent page layout:

- Header / Navbar
- Main content area
  - Support for multi-column layouts if needed
- Footer

This structure should be reusable across all pages.

### Phase 3: Styling

- Recreate the visual style using:
  - The `vocal` theme (from `_tmp/theme`)
  - The archived website as visual reference
- Extract colors, typography, spacing, and layout patterns

### Phase 4: Content Migration

- Recreate pages as **static content**
- Aim for a **1:1 reproduction** of:
  - Text
  - Images
  - Page hierarchy
- Do not introduce dynamic CMS features

### Phase 5: Deployment

- Use the GitHub Actions workflow to:
  - Build and generate the Roq project
  - Deploy to **GitHub Pages**

---

## Constraints

- No backend required
- No CMS integration
- Focus on static generation
- Prefer simplicity over abstraction
- Do not add Java sources
- Do not reintroduce Kobweb, Compose, or the removed `site/` and `worker/` modules

---

## Agent Guidelines

- Work incrementally and keep changes small and reviewable
- Prioritize visible progress (rendered pages early)
- When unsure, follow the archived website
- Reuse existing assets where possible
- Avoid overengineering
- consider all md files under `.agents/`
- Keep `gradle/build-logic` available for shared build logic
- The development server uses port 8082; internal previews should use another port

---

## Open Questions

The agent should clarify unclear aspects before proceeding with major decisions.
