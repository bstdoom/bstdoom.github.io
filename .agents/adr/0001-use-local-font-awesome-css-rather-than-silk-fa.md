# ADR 0001: Use local Font Awesome CSS instead of `silk-fa`

## Status

Accepted

## Context

The site uses a small set of Font Awesome icons for navigation, social links, and release action buttons. These icons are rendered as plain HTML `<i>` elements with CSS classes such as `fa`, `fa-facebook`, `fa-bandcamp`, and `fa-youtube`.

During the Kobweb migration, the `silk.icons.fa` dependency was added, which caused Kobweb to inject a Font Awesome CDN stylesheet into the generated `<head>`. We already keep a local copy of `font-awesome.min.css` in the repo, so the dependency introduced an extra external link without giving us any practical benefit.

## Decision

Use the local Font Awesome CSS asset under `site/src/jsMain/resources/public/css/font-awesome.min.css` and do not depend on `silk.icons.fa`.

## Consequences

- The site stays self-hosted for icon styling.
- No Kobweb head interception is needed for Font Awesome.
- The code remains simple because it already uses raw CSS class names.
- We do not gain typed Silk icon composables, but the site does not need them for the current icon usage.

## Rationale

For this site, `silk-fa` adds more machinery than value. The icon set is small and stable, and direct CSS class usage is sufficient. Keeping the dependency would require either intercepting or self-hosting the injected CDN link, which is unnecessary when the local CSS file already works.
