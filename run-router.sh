#!/bin/bash

AGENT_JAR=diffblue-cover/cover-replay-agent.jar

# run the service router
java -ea -cp 'target/classes:target/dependency/*' -javaagent:$AGENT_JAR io.diffblue.corebanking.ui.service.MessageRouterService
