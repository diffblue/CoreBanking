#! /usr/bin/env bash

set -euvo pipefail

BRANCH="$1"
SHA="$2"

if [ "$BRANCH" != "dcover-dogfooding-noTrivial" ]
then
	if [[ $BRANCH != *"-Diffblue-tests"* ]]; then
		git checkout -b "$BRANCH""-Diffblue-tests"
		mvn clean compile -B 
		timeout 360 ./dcover create -d src/dcover/java --skip-test-verification --batch
		python3 .codebuild/removeTrivialTests.py
		git add src/dcover
		git commit -m "Add diffblue tests" || true
		git push origin "$BRANCH""-Diffblue-tests"
		NEW_SHA=$(git rev-parse HEAD)
		echo "$SHA"
		echo "$NEW_SHA"
		.codebuild/github-post-pr.sh "$SHA" "Diffblue has found some code changes please check these are intended https://github.com/diffblue/CoreBanking/compare/$BRANCH...$BRANCH-Diffblue-tests?expand=1"
        fi
fi
