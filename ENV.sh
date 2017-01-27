#!/usr/bin/env bash

BASEDIR=`dirname ${BASH_SOURCE[0]} | xargs readlink -f`
JDK="$BASEDIR/jdk"
MAVEN="$BASEDIR/maven"
if [[ ! -e $JDK ]]; then
  (>&2 echo "ERROR: jdk is not set")
  return 1
fi
if [[ ! -e $MAVEN ]]; then
  (>&2 echo "ERROR: maven is not set")
  return 1
fi


# JAVA
JAVA_HOME=$JDK
PATH=$JAVA_HOME/bin:$PATH

# Maven
MAVEN_HOME=$MAVEN
PATH=$MAVEN_HOME/bin:$PATH

# PS1
YELLOW='\e[33m'
DEFAULT='\e[39m'
PS1="$YELLOW(ShellENV4J)$DEFAULT $PS1"

# Source additional script
ADDITIONAL_SCRIPT='.envrc'
if [[ -f $ADDITIONAL_SCRIPT ]]; then
    . $ADDITIONAL_SCRIPT
fi
