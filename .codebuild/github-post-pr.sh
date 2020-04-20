#! /usr/bin/env bash

# Posts a comment on the github page of the cover PR that triggered the current build.
# arg1 Cover commit SHA
# arg2 URL to catfood branch

set -euvo pipefail
COVER_FULL_SHA=4d5ce0ace7a22400b693d8bbf5547c1266c839b7
CATFOOD_URL=$2

# get PR number from commit
PR=$(curl -s GET \
   -H "Authorization: token ${GITHUB_CI_STATUS_TOKEN}" \
   -H "Accept: application/vnd.github.groot-preview+json" \
   https://api.github.com/repos/diffblue/CoreBanking/commits/$COVER_FULL_SHA/pulls
)

echo "$PR"
# post catfood branch link to PR
COVER_BRANCH_URL=https://api.github.com/repos/diffblue/CoreBanking/issues/$PR/comments
curl --location --request POST "${COVER_BRANCH_URL}" \
  
	
  #--header "Content-Type: application/json" \
  #--header "Authorization: Bearer ${GITHUB_CI_STATUS_TOKEN}" \
  #--header "Content-Type: application/json" \
  #--data-raw "{\"body\": \"test\"}"
