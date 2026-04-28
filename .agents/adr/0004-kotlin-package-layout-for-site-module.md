# ADR 0004: Keep Kotlin package paths aligned with the site module namespace

## Status

Accepted

## Context

The Kotlin sources under `site/src/jsMain/kotlin` had drifted away from the project’s package layout conventions. Kotlin’s coding conventions recommend that the directory structure follow the package structure, with the source root itself left package-less:

- https://kotlinlang.org/docs/coding-conventions.html#directory-structure

For the site module, that means the source root stays as the package-less `src/jsMain/kotlin`, while files in the common root package `io.github.bstdoom` live directly under that source root. Subpackages such as `components` and `pages` then map to subdirectories below it.

## Decision

Place all active site Kotlin sources under the `io.github.bstdoom` package hierarchy, with `AppEntry.kt` at the source root and the rest of the package tree directly underneath `src/jsMain/kotlin`.

This layout is mandatory for the site module.

## Consequences

- The directory tree under `site/src/jsMain/kotlin` mirrors the Kotlin package tree again, with the common root package omitted from the directory layout.
- Imports become more consistent and easier to scan.
- The site module is clearly separated from repo-level Kotlin or build-logic code.
- The package-less source root remains available for top-level module files that do not belong to a package tree.

## Rationale

This keeps the site codebase aligned with Kotlin’s documented conventions and makes the source tree easier to navigate as the project grows.
