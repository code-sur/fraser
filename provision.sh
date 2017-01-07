#!/usr/bin/env bash

sudo apt-get -qq update
sudo apt-get install -y postgresql

sudo su - postgres -c 'createuser -D -R -S fraser'
sudo su - postgres -c "psql -c \"ALTER USER fraser WITH PASSWORD 'fraser';\""
sudo su - postgres -c 'createdb -O fraser fraser'
