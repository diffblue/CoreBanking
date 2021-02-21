#!/bin/bash

rm -f `find . -name '*DiffblueTest.java'`
rm -Rf .diffblue
rm -Rf ~/.diffblue/rec
mvn clean package -DskipTests
