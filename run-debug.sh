#!/bin/sh

# Do not send ctrl+c interruption to the background jobs
set -m

# Skips all tests for local development builds
mvn clean package -DskipTests &&

# Runs docker-compose
docker-compose -f docker/docker-compose-debug.yml down -v &&
docker-compose -f docker/docker-compose-debug.yml up --build
#2>&1 >> "docker.log" &
