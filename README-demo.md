
1. Open three terminals and make the current working directory on all of them to
   be the root folder of the project. Compile the project, using one of the
   terminals:

   ```sh
   $ mvn package -DskipTests
   ```

2. On the first terminal, run the Apache Pulsar broker (the script will download
   the necessary binaries):
   ```sh
   $ ./run-broker.sh 
   + test -d apache-pulsar-2.6.2
   + rm -Rf apache-pulsar-2.6.2/data/
   + exec ./apache-pulsar-2.6.2/bin/pulsar standalone
   [AppClassLoader@18b4aac2] info AspectJ Weaver Version 1.9.2 built on Wednesday Oct 24, 2018 at 15:43:33 GMT
   [AppClassLoader@18b4aac2] info register classloader sun.misc.Launcher$AppClassLoader@18b4aac2
   ...
   ```

3. On the second terminal, run the code under test, a 'message router' service
   which consumes messages from, and pushes messages to, the broker:
   ```sh
   $ ./run-router.sh 
   + exec java -cp 'target/classes:target/dependency/*' -javaagent:/path/to/cover-cli/cover-replay-agent.jar io.diffblue.corebanking.ui.service.MessageRouterService
   MessageRouterService: starting... (type Ctrl+C to quit)
   MessageRouter: constructing the PulsarClient...
   MessageRouter: subscribing to topic 'all-incoming'...
   MessageRouter: opening producer on topic 'urgent'...
   MessageRouter: opening producer on topic 'batch'...
   MessageRouter: opening producer on topic 'manual'...
   MessageRouterService: main thread exits now
   WorkerThread: topic 'all-incoming': receiving...
   WorkerThread: topic 'all-incoming': received '10:0:-1:0'
   WorkerThread: forwarding message '10:0:-1:0' to topic 'urgent'...
   WorkerThread: sent, id: '11:0:-1:0'
   WorkerThread: message acknowledged
   WorkerThread: topic 'all-incoming': receiving...
   ```

4. On the third terminal, run some unit tests which interact with the message
   router (via the broker), thereby exercising the code of the message router:
   ```sh
   $ mvn failsafe:integration-test -Dit.test=MessageRouterServiceIT
   [INFO] Scanning for projects...
   ...
   [INFO] Running io.diffblue.corebanking.ui.service.MessageRouterServiceIT
   urgent: topic 'all-incoming': sending message 'BANK|150000|2020-01-10|9876|1234|2021-02-10T16:40:11.487Z'...
   urgent: topic 'all-incoming': sent '10:0:-1:0'
   urgent: topic 'urgent': receiving...
   urgent: topic 'urgent': received '11:0:-1:0'
   urgent: topic 'urgent': acknowledging...
   urgent: topic 'urgent': done
   [WARNING] Tests run: 3, Failures: 0, Errors: 0, Skipped: 2, Time elapsed: 17.282 s - in io.diffblue.corebanking.ui.service.MessageRouterServiceIT
   [INFO] 
   [INFO] Results:
   [INFO] 
   [WARNING] Tests run: 3, Failures: 0, Errors: 0, Skipped: 2
   [INFO] 
   [INFO] ------------------------------------------------------------------------
   [INFO] BUILD SUCCESS
   ```

5. Now go to the second terminal and Ctrl+C the service. This should produce the
   following output in the terminal:

   ```
   ^CWorkerThread: interrupted, terminating now
   ```

6. Extract tests using the execution trace of the message router:

   ```sh
   dcover create --trace --fuzzing-iterations=0 io.diffblue.corebanking.communication --mock org.apache.pulsar
   ```
