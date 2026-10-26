# GH Token Handling

We discussed moving GitHub CLI auth to a repo-local, gitignored token file for more predictable `gh` usage in Codex and local shells.

Possible shape:
- store the token in a gitignored path such as `.agents/.gh-token` or `.local/gh-token`
- use `GH_TOKEN="$(cat ...)"` for `gh issue`, `gh run`, and PR-related commands
- keep the token out of the repo history

This is a future cleanup item. No implementation decision has been made yet.
