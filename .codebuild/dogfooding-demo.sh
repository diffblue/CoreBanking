#! /usr/bin/env bash

set -euvo pipefail

BRANCH="$1"
SHA="$2"

git add src/dcover
git commit -m "Add diffblue tests" || true
git push origin "$BRANCH"
NEW_SHA=$(git rev-parse HEAD)
echo "$SHA"
echo "$NEW_SHA"
.codebuild/github-post-pr.sh "$SHA" "Diffblue has found some code changes please check these are intended $NEW_SHA"
