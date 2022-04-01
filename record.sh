#!/bin/bash

# The Java agent that will record the execution
AGENT=$HOME/cover/cover-replay-agent.jar # MODIFY HERE IF NECESSARY!

# Check for common mistakes when running the demo
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

# This environment variable instructs the agent to record just enough
# information to extract tests for classes that live in the package
# 'io.diffblue'
export COVER_TARGET=io.diffblue.

# Finally, we record the application under test. Please remember to always put
# the '-javagent' argument before the main class (BatchProcessor here), as
# otherwise the JVM will think that it is an argument of the main program
java -javaagent:$AGENT -cp "target/classes${SEP}target/dependency/*" io.diffblue.corebanking.ui.batch.BatchProcessor src/test/resources/batch/*.txt
