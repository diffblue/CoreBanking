#! /usr/bin/env bash

set -euvo pipefail

BRANCH="$1"
SHA="$2"
mvn clean compile -B 
timeout 360 ./dcover create -d src/dcover/java --skip-test-verification --batch
python3 .codebuild/removeTrivialTests.py

if [ "$BRANCH" != "dcover-dogfooding-master" ]
then
	git add src/dcover
	git commit -m "Add diffblue tests" || true
	git push origin "$BRANCH"
	NEW_SHA=$(git rev-parse HEAD)
	echo "$SHA"
	echo "$NEW_SHA"
	.codebuild/github-post-pr.sh "$SHA" "Diffblue has found some code changes please check these are intended $NEW_SHA"
fi
