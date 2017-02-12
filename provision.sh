#!/usr/bin/env bash

sudo apt-get -qq update
sudo apt-get install -y postgresql

sudo su - postgres -c 'createuser -D -R -S fraser'
sudo su - postgres -c "psql -c \"ALTER USER fraser WITH PASSWORD 'fraser';\""
sudo su - postgres -c 'createdb -O fraser fraser'

POSTGRES_CONFIG_DIR='/etc/postgresql/9.1/main'
REPO_POSTGRES_CONFIG_DIR='/vagrant/postgres_config'
sudo service postgresql stop
sudo rm $POSTGRES_CONFIG_DIR/pg_hba.conf
sudo cp $REPO_POSTGRES_CONFIG_DIR/pg_hba.conf $POSTGRES_CONFIG_DIR/pg_hba.conf
sudo rm $POSTGRES_CONFIG_DIR/postgresql.conf
sudo cp $REPO_POSTGRES_CONFIG_DIR/postgresql.conf $POSTGRES_CONFIG_DIR/postgresql.conf
sudo service postgresql start
