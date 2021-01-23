
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
   + ./apache-pulsar-2.6.2/bin/pulsar standalone
   [AppClassLoader@18b4aac2] info AspectJ Weaver Version 1.9.2 built on Wednesday Oct 24, 2018 at 15:43:33 GMT
   [AppClassLoader@18b4aac2] info register classloader sun.misc.Launcher$AppClassLoader@18b4aac2
   ...
   ```

3. On the second terminal, run the code under test, a 'message router' service
   which consumes messages from, and pushes messages to, the broker:
   ```sh
   $ ./run-router.sh 
   + java -cp 'target/classes:target/dependency/*' -javaagent:/path/to/ris-agent.jar io.diffblue.corebanking.ui.service.MessageRouterService
   Listening for transport dt_socket at address: 5005
   MessageRouterService: starting... (type Ctrl+C to quit)
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
   WorkerThread: topic 'all-incoming': received '10:1:-1:0'
   WorkerThread: forwarding message '10:1:-1:0' to topic 'batch'...
   WorkerThread: sent, id: '14:0:-1:0'
   WorkerThread: message acknowledged
   WorkerThread: topic 'all-incoming': receiving...
   WorkerThread: topic 'all-incoming': received '10:2:-1:0'
   WorkerThread: forwarding message '10:2:-1:0' to topic 'manual'...
   WorkerThread: sent, id: '15:0:-1:0'
   WorkerThread: message acknowledged
   ...
   ```

4. In the third terminal, run some unit tests which interact with the message
   router (via the broker), thus exercising the code of the message router:
   ```sh
   $ mvn failsafe:integration-test -Dit.test=MessageRouterServiceIT
   [INFO] Scanning for projects...
   ...
   [INFO] Running io.diffblue.corebanking.ui.service.MessageRouterServiceIT
   urgent: topic 'all-incoming': sending message 'BANK|150000|2020-01-10|9876|1234|2021-01-23T00:35:46.230Z'...
   urgent: topic 'all-incoming': sent '10:3:-1:0'
   urgent: topic 'urgent': receiving...
   urgent: topic 'urgent': received '11:1:-1:0'
   urgent: topic 'urgent': acknowledging...
   urgent: topic 'urgent': done
   batch: topic 'all-incoming': sending message 'BANK|150000|2020-01-10|9876|1234|2021-01-23T00:35:46.891Z'...
   batch: topic 'all-incoming': sent '10:4:-1:0'
   batch: receiving message...
   batch: topic 'batch': receiving...
   batch: topic 'batch': received '14:1:-1:0'
   batch: topic 'batch': acknowledging...
   batch: topic 'batch': done
   manual: topic 'all-incoming': sending message 'blablabla 2021-01-23T00:35:47.001Z'...
   manual: topic 'all-incoming': sent '10:5:-1:0'
   manual: topic 'manual': receiving...
   manual: topic 'manual': received '15:1:-1:0'
   manual: topic 'manual': acknowledging...
   manual: topic 'manual': done
   [INFO] Tests run: 3, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 1.282 s - in io.diffblue.corebanking.ui.service.MessageRouterServiceIT
   [INFO] 
   [INFO] Results:
   [INFO] 
   [INFO] Tests run: 3, Failures: 0, Errors: 0, Skipped: 0
   [INFO] 
   [INFO] ------------------------------------------------------------------------
   [INFO] BUILD SUCCESS
   ```

5. Now go to the second terminal and Ctrl+C the service. This should save the
   execution trace.

6. Extract tests using the execution trace of the message router:

   ```sh
   dcover create --trace io.diffblue.corebanking.communication \
      --mock org.apache.pulsar.client.api.Message \
      --mock org.apache.pulsar.client.api.Consumer \
      --mock org.apache.pulsar.client.api.Producer \
      --mock org.apache.pulsar.client.api.TypedMessageBuilder
   ```
FIXME
- file paths to the cover tarball
- more context
