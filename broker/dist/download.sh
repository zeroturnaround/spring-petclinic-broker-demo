#!/bin/env sh
set -e

#### CHANGE THESE VALUES ####
# IDEA_PLUGIN_URL: URL to the JRebel for IntelliJ IDEA plugin
export IDEA_PLUGIN_URL="https://<>/jr-ide-intellij.zip"
# JREBEL_AGENT_STANDALONE_URL: URL to the JRebel Agent Standalone distribution
export JREBEL_AGENT_STANDALONE_URL="https://<>/jrebel-dist.zip"
# BROKER_JAR_URL: URL to the JRebel Broker Server JAR
export BROKER_JAR_URL="https://<>/jr-broker-server-0.0.1-SNAPSHOT.jar"
#############################

[[ x"${IDEA_PLUGIN_URL}" == "x" ]] && echo "IDEA_PLUGIN_URL is not specified" && exit 1
[[ x"${JREBEL_AGENT_STANDALONE_URL}" == "x" ]] && echo "JREBEL_AGENT_STANDALONE_URL is not specified" && exit 1
[[ x"${BROKER_JAR_URL}" == "x" ]] && echo "BROKER_JAR_URL is not specified" && exit 1

BASE_PATH=$(dirname "$(realpath $0)")
printf "Downloading artifacts to $BASE_PATH\n"

download() {
  printf "Downloading... $1\n"
  curl "$1" -o "$BASE_PATH/$(basename "$1")"
  curl "$1.sha1" -o "$BASE_PATH/$(basename "$1.sha1")"
}

download  $IDEA_PLUGIN_URL
download  $JREBEL_AGENT_STANDALONE_URL
download  $BROKER_JAR_URL

# Checksums
printf "\nChecking checksums...\n"
pushd $BASE_PATH
sha1sum -c *.sha1
popd

JR_DIST=$(basename $JREBEL_AGENT_STANDALONE_URL)
printf "\nUnzipping... $JR_DIST\n"
unzip -o "$BASE_PATH/$JR_DIST" -d "$BASE_PATH"

printf "\nAll done!\n"
