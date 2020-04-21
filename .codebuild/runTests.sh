set -euo pipefail
mvn clean test -B 2>&1 | tee mavenOutput.txt
