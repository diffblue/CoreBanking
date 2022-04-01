@echo off

:: MODIFY HERE IF NECESSARY!
set AGENT=C:\cover\cover-replay-agent.jar

:: Check for common mistakes when running the demo
if exist "%AGENT%" goto OkAgent
  echo "File '%AGENT%' not found: please edit line 4 of the script"
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

:: This environment variable instructs the agent to record just enough
:: information to extract tests for classes that live in the package
:: 'io.diffblue'
set COVER_TARGET=io.diffblue.

:: Finally, we record the application under test. Please remember to always put
:: the '-javagent' argument before the main class (BatchProcessor here), as
:: otherwise the JVM will think that it is an argument of the main program
@echo on
java -javaagent:%AGENT% -cp "target\classes;target\dependency\*" io.diffblue.corebanking.ui.batch.BatchProcessor src\test\resources\batch\*.txt
