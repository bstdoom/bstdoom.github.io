# ADR 0003: Keep `site/.kobweb/conf.yaml` checked in

## Status

Accepted

## Context

Kobweb requires `site/.kobweb/conf.yaml` to exist before the `com.varabyte.kobweb.application` plugin can be applied. If the file is missing, the project is no longer recognized as a Kobweb project and the Gradle build fails during configuration.

We briefly tried to generate this file from Gradle, but that breaks Kobweb plugin startup because the marker file must already exist on disk before the plugin is loaded.

## Decision

Keep `site/.kobweb/conf.yaml` in the repository and use `site/.kobweb/.gitignore` to ignore the rest of the generated Kobweb workspace.

## Consequences

- The Kobweb project marker always exists for local development and CI.
- The build can still regenerate or overwrite the file content if needed, but the file itself remains tracked.
- `.kobweb` remains the natural Kobweb workspace, rather than being moved under `build/`.

## Rationale

This is the least surprising setup for Kobweb. The project marker is part of the tool’s expected structure, so keeping it checked in avoids brittle bootstrapping logic and prevents plugin application failures.
