#!/bin/bash

AGENT=$HOME/cover/cover-replay-agent.jar # MODIFY HERE IF NECESSARY!

# proactively check for common mistakes when running the demo
if [ ! -f "$AGENT" ]; then
  echo "File '$AGENT' not found: please edit line 3 of the script '$0'"
  exit 1
fi
if [ ! -d target/classes ]; then
  echo "Please run 'mvn compile' first"
  exit 1
fi
if [ ! -d target/dependency ]; then
  echo "Please run 'mvn dependency:copy-dependencies' first"
  exit 1
fi

# figure out the classpath separator, ":" in unix and ";" in windows
SEP=$(java -XshowSettings 2>&1 | grep path.separator | sed 's/.*path.separator = //')

# record the application under test
set -x
export COVER_TARGET=io.diffblue.
java -javaagent:$AGENT -cp "target/classes${SEP}target/dependency/*" io.diffblue.corebanking.ui.batch.BatchProcessor src/test/resources/batch/*.txt
