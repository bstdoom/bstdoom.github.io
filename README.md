This is a [Kobweb](https://github.com/varabyte/kobweb) project bootstrapped with the `app/empty` template.

This template is useful if you already know what you're doing and just want a clean slate. By default, it
just creates a blank home page (which prints to the console so you can confirm it's working)

If you are still learning, consider instantiating the `app` template (or one of the examples) to see actual,
working projects.

## Project Architecture

Kobweb projects can look unusual at first because they combine:

- a Gradle multi-module build
- Kotlin Multiplatform source sets
- a frontend app and optional supporting browser worker code

In this repository, the important modules are:

- `site/`
  - this is the actual website
  - it contains the pages, components, styling, public assets, and the Kobweb app configuration
  - when you think "the website", this is the module you usually mean
- `worker/`
  - this is a separate browser worker bundle
  - Kobweb scaffolds this so the app can offload work from the main browser thread when needed
  - in this project it is mostly infrastructure right now, not where the page UI lives

### Why `site` and `worker`?

The split exists because the browser can run code in different contexts:

- the main page thread
  - renders UI
  - handles routing
  - runs normal frontend logic
- a worker thread
  - runs background code without touching the DOM directly
  - useful for heavy processing or tasks that should not block rendering

So:

- `site` builds the main web app
- `worker` builds the optional worker script that the app can depend on

You can see that in `site/build.gradle.kts`, where `jsMain` depends on `project(":worker")`.

### Why `jsMain`, `jvmMain`, and sometimes `commonMain`?

This comes from Kotlin Multiplatform.

A single module can have different source sets for different targets:

- `jsMain`
  - code compiled to JavaScript
  - this is where the Compose for Web / Kobweb frontend code lives
  - in this repo, almost all visible website code is here
- `jvmMain`
  - code compiled for the JVM
  - in Kobweb this is used for backend/server-side pieces if you have them
  - even for a mostly static site, Kobweb still supports a JVM side during development and export
- `commonMain`
  - optional shared Kotlin code used by both JS and JVM
  - put code here only if it is truly platform-agnostic
  - this repo currently does not use `commonMain`, which is normal

In this project specifically:

- `site/src/jsMain/`
  - pages
  - components
  - frontend app entry
  - public assets under `resources/public`
- `site/src/jvmMain/`
  - JVM-side app support for Kobweb
  - currently minimal because this site does not need a custom backend
- `worker/src/jsMain/`
  - the worker's JavaScript-side code

### Practical Rule Of Thumb

If you are changing the website UI, routes, content, or CSS, you almost always want:

- `site/src/jsMain/kotlin/...`
- `site/src/jsMain/resources/public/...`

You only need to care about:

- `site/src/jvmMain/...`
  - if you add backend/server-side behavior
- `worker/...`
  - if you intentionally add browser worker logic

So for day-to-day site work, you can think of `site/src/jsMain` as the main home of the project.

## Getting Started

First, run the development server by typing the following command in a terminal under the `site` folder:

```bash
$ cd site
$ kobweb run
```

Open [http://localhost:8080](http://localhost:8080) with your browser to see the result.

You can use any editor you want for the project, but we recommend using **IntelliJ IDEA Community Edition** downloaded
using the [Toolbox App](https://www.jetbrains.com/toolbox-app/).

Press `Q` in the terminal to gracefully stop the server.

### Live Reload

Feel free to edit / add / delete new components, pages, and API endpoints! When you make any changes, the site will
indicate the status of the build and automatically reload when ready.

## Exporting the Project

When you are ready to ship, you should shutdown the development server and then export the project using:

```bash
kobweb export
```

When finished, you can run a Kobweb server in production mode:

```bash
kobweb run --env prod
```

If you want to run this command in the Cloud provider of your choice, consider disabling interactive mode since nobody
is sitting around watching the console in that case anyway. To do that, use:

```bash
kobweb run --env prod --notty
```

Kobweb also supports exporting to a static layout which is compatible with static hosting providers, such as GitHub
Pages, Netlify, Firebase, any presumably all the others. You can read more about that approach here:
https://bitspittle.dev/blog/2022/staticdeploy
