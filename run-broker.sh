#!/bin/bash

# download and unpackage pulsar if not done before
V=2.6.2
set -x
if ! test -d apache-pulsar-$V; then
   wget -O pulsar.tar.gz "https://archive.apache.org/dist/pulsar/pulsar-$V/apache-pulsar-${V}-bin.tar.gz";
   tar xzf pulsar.tar.gz
fi

# clean state of the server
rm -Rf apache-pulsar-$V/data/

# run the broker
exec ./apache-pulsar-$V/bin/pulsar standalone
