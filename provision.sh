#!/usr/bin/env bash

sudo add-apt-repository ppa:webupd8team/java --yes
sudo apt-get -qq update
echo "oracle-java8-installer shared/accepted-oracle-license-v1-1 select true" | sudo debconf-set-selections
sudo apt-get install -y oracle-java8-installer

wget http://mirrors.nxnethosting.com/apache/maven/maven-3/3.3.9/binaries/apache-maven-3.3.9-bin.tar.gz
tar zxf apache-maven-3.3.9-bin.tar.gz
export MAVEN_HOME="/home/vagrant/apache-maven-3.3.9"
echo "export PATH=$MAVEN_HOME/bin:$PATH" > maven.sh
sudo echo "export PATH=$MAVEN_HOME/bin:$PATH" > /etc/profile.d/maven.sh
sudo cp maven.sh /etc/profile.d