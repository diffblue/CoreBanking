# The command for using dcover.

# Location of the dcover script
SCRIPT_LOCATION=$1
# The directory where the tests should be stored, e.g. src/diffbluetests/java
TEST_LOCATION=$2
# Module
MODULE=$3
# Patch file
PATCH_FILE=$4
# jacoco.exec
COVERAGE_REPORT=$5
# branch
BRANCH=$6

echo "Git set up"
git config --global user.email "db-ci-platform@diffblue.com"
git config --global user.name "db-ci-platform"
git fetch origin "$BRANCH" -q
git checkout -b "$BRANCH" origin/$BRANCH
echo "We've fetched checked out and origin/$BRANCH as $BRANCH"
git branch


# The complexity here is in error handling, but effectively it does this for the happy path
# Note that all mvn commands here are run with a profile that only compiles/run Diffblue tests which are stored separately
# 1) Run dcover clean to remove non-compiling tests
# 2) Run mvn test-compile run dcover clean to remove failing tests
# 3) Run mvn test to generate Jacoco report
# 4) Call dcover with patch and jacoco report

echo "Generating tests for module $MODULE"
# Note that all mvn command here are run with a profile that only compiles/run Diffblue tests which are stored separately
if [ ! -d "$MODULE/$TEST_LOCATION" ]
then
  echo "Running dcover without patch or coverage"
  mkdir -p $MODULE/$TEST_LOCATION
  "$SCRIPT_LOCATION" create --batch --merge -x "mvn test -P DiffblueTests" -d "$TEST_LOCATION" --working-directory "$MODULE"
  exit 0
fi
echo "Run dcover clean to remove non-compiling tests"
"$SCRIPT_LOCATION" clean -d "$TEST_LOCATION" --working-directory "$MODULE"
echo "Run mvn test-compile to compile the tests"
mvn test-compile -P DiffblueTests -pl "$MODULE"
echo "Run dcover clean to remove failing tests"
# TODO: bug, this seems to always remove tests
"$SCRIPT_LOCATION" clean -d "$TEST_LOCATION" --failing --working-directory "$MODULE"
echo "Running mvn test to get the jacoco report"
mvn test -P DiffblueTests -pl "$MODULE"
if [ -f "$COVERAGE_REPORT" ] && [ -f "$PATCH_FILE" ]
then
  echo "Running dcover with patch and coverage report"
  "$SCRIPT_LOCATION" create --batch --merge -x "mvn test -P DiffblueTests" -d "$TEST_LOCATION" --working-directory "$MODULE" --patch-only "$PATCH_FILE" --existing-coverage "$COVERAGE_REPORT"
else
  echo "Running dcover without patch or coverage because one of them doesn't exist"
  "$SCRIPT_LOCATION" create --batch --merge -x "mvn test -P DiffblueTests" -d "$TEST_LOCATION" --working-directory "$MODULE"
fi

echo "Git add and commit"
git add "$MODULE/$TEST_LOCATION"
if ! git diff --quiet --cached "$MODULE/$TEST_LOCATION"
then
  echo "Commit to branch"
  git branch
  git commit -m "Update tests from DCover for $MODULE"
  git push origin "$BRANCH"
else
  echo "Nothing to commit"
fi