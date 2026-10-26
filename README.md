# B.S.T. - Hamburg City Doom 

A [Quarkus Roq](https://iamroq.dev/) based band website for Hamburg's finest doom metal band.

## Content

### News

Wir sind wieder da!

## Archiv

Lars Live Premiere: [Cause of Deaf Fest](https://www.kph-hamburg.de/veranstaltungen/detail/cause-of-deaf-fest-2026/10-10-2026), 10.10. - Bambi

## Project Architecture

The static site lives in the `roq/` Gradle submodule. It contains the Kotlin
support classes, Qute templates, content, JSON data, assets, and generated
release pages.

## Getting Started

Start the Roq development server:

```bash
just roq-serve-dev
```

Then open [http://localhost:8082](http://localhost:8082).

You can use any editor you want for the project, but we recommend using **IntelliJ IDEA Community Edition** downloaded
using the [Toolbox App](https://www.jetbrains.com/toolbox-app/).

## Generating the Static Site

Generate the static HTML, CSS, JavaScript, and assets with:

```bash
just roq-generate
```

The output is written to `roq/build/roq/`. To serve an existing generated
output directory, run:

```bash
just roq-serve
```
