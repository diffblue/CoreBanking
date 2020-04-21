#! /usr/bin/env bash

set -euvo pipefail
COVER_FULL_SHA=$1
MESSAGE=$2

# get PR number from commit
PR=$(curl -s GET \
   -H "Authorization: token ${GITHUB_CI_STATUS_TOKEN}" \
   -H "Accept: application/vnd.github.groot-preview+json" \
   https://api.github.com/repos/diffblue/CoreBanking/commits/$COVER_FULL_SHA/pulls | jq --arg COVER_FULL_SHA "$COVER_FULL_SHA" '.[] | select(.head.sha == $COVER_FULL_SHA) | .url' | sed 's~^.*\/\([0-9]*\)"$~\1~' || true
)

COVER_BRANCH_URL=https://api.github.com/repos/diffblue/CoreBanking/issues/"$PR"/comments
curl --location --request POST "${COVER_BRANCH_URL}" \
  --header "Content-Type: application/json" \
  --header "Authorization: Bearer ${GITHUB_CI_STATUS_TOKEN}" \
  --header "Content-Type: application/json" \
  --data-raw "{\"body\": \"$MESSAGE\"}"
