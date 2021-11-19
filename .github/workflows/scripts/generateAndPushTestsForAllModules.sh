# Generates tests for all modules

# Location of the dcover script
SCRIPT_LOCATION=$1
# The directory where the tests should be stored, e.g. src/diffbluetests/java
TEST_LOCATION=$2
# Branch to push to
BRANCH=$3
# Patch file
PATCH_FILE=$4
# jacoco.exec
COVERAGE_REPORT=$5

for MODULE in "."
do
  echo "Generating tests for $MODULE on branch $BRANCH with test location $TEST_LOCATION, patch file $PATCH_FILE and coverage report $COVERAGE_REPORT"
	./.github/workflows/scripts/generateTests.sh $SCRIPT_LOCATION $TEST_LOCATION $MODULE $PATCH_FILE $COVERAGE_REPORT $BRANCH
done