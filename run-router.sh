#!/bin/bash

COVER_CLI_ROOT="path/to/uncompressed/cover-cli/zip"

AGENT="$COVER_CLI_ROOT/cover-replay-agent.jar"

export COVER_TARGET=io.diffblue.corebanking.

set -x
exec java -cp 'target/classes:target/dependency/*' -javaagent:$AGENT io.diffblue.corebanking.ui.service.MessageRouterService
