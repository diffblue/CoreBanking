#!/bin/bash

DEBUG="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005"

AGENT_JAR=$(echo $HOME/x/devel/diffblue/cover/ris-agent/target/ris-agent-*-shaded.jar)
#AGENT_JAR=$HOME/Downloads/jacoco/lib/jacocoagent.jar

# run the service router
set -x
java $DEBUG -ea -esa -cp 'target/classes:target/dependency/*' -javaagent:$AGENT_JAR io.diffblue.corebanking.ui.service.MessageRouterService

