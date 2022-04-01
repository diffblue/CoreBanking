#!/bin/bash

# Agent that will record the execution
AGENT=$HOME/cover/cover-replay-agent.jar # MODIFY HERE IF NECESSARY!

# Proactively check for common mistakes when running the demo
if [ ! -f "$AGENT" ]; then
  echo "File '$AGENT' not found: please edit line 4 of the script '$0'"
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

# Figure out the classpath separator, ":" in unix and ";" in windows
SEP=$(java -XshowSettings 2>&1 | grep path.separator | sed 's/.*path.separator = //')

set -x

# The $COVER_TARGET environment variable instructs the agent to record just enough to extract tests for classes that live in the package `io.diffblue`.
export COVER_TARGET=io.diffblue.

# Record the application under test

# The -javagent argument must be put before the class, otherwise it would be
# passed to the application instead of the JVM and the application would not
# be recorded.
java -javaagent:$AGENT -cp "target/classes${SEP}target/dependency/*" io.diffblue.corebanking.ui.batch.BatchProcessor src/test/resources/batch/*.txt
