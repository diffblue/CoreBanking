#!/bin/bash

DCOVER=$HOME/cover/dcover # MODIFY HERE IF NECESSARY!

# proactively check for common mistakes when running the demo
if [ ! -f "$DCOVER" ]; then
  echo "File '$DCOVER' not found: please edit line 3 of the script '$0'"
  exit 1
fi

set -x
./record.sh
$DCOVER create --trace --verbose "$@"
