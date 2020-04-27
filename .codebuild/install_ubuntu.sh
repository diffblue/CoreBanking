#!/bin/bash
set -euo pipefail

apt-get update
apt-get --no-install-recommends -y install build-essential git maven
