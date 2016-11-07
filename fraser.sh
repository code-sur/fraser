#!/usr/bin/env bash
BASE_DIR=`pwd`

JAVA_HOME="$BASE_DIR/jdk"
MAVEN_HOME="$BASE_DIR/maven"

CLASSPATH=`$MAVEN_HOME/bin/mvn dependency:build-classpath | grep -v '[INFO]'`
CLASSPATH="target/classes:$CLASSPATH"

$JAVA_HOME/bin/java -cp $CLASSPATH CodeRaguet.Main
