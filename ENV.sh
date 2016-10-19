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
JDK_VERSION=`java -version 2>&1 | sed -nr 's/.*"(.*)".*/\1/p'`
BOLD='\e[1m'
NO_BOLD='\e[21m'
PS1="jdk$BOLD$JDK_VERSION$NO_BOLD $PS1"
