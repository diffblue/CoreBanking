@echo off

@REM MODIFY HERE IF NECESSARY!
set AGENT=%userprofile%\bin\cover\cover-replay-agent.jar

@REM proactively check for common mistakes when running the demo
if exist "%agent%" goto OkAgent
  echo "File '%agent%' not found: please edit line 3 of the batch script"
  cmd exit /B 1
:OkAgent
if exist target/classes goto OkClasses
  echo "Please run 'mvn compile' first"
  cmd exit /B 1
:OkClasses
if exist target/dependency goto OkDeps
  echo "Please run 'mvn dependency:copy-dependencies' first"
  cmd exit /B 1
:OkDeps


@REM The $COVER_TARGET environment variable instructs the agent to record
@REM just enough to extract tests for classes that live in the package `io.diffblue`.
set COVER_TARGET=io.diffblue.
@REM record the application under test

@REM The -javagent argument must be put before the class, otherwise it would be
@REM passed to the application instead of the JVM and the application would not
@REM be recorded.
java -javaagent:%AGENT% -cp "target\classes;target\dependency\*" io.diffblue.corebanking.ui.batch.BatchProcessor src\test\resources\batch\*.txt
