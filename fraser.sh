#!/usr/bin/env bash

CLASSPATH=`mvn dependency:build-classpath | grep -v '[INFO]'`
CLASSPATH="target/classes:$CLASSPATH"

java -cp $CLASSPATH CodeRaguet.fraser.Main
