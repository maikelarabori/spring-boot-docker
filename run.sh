#!/bin/sh

# Skips all tests for local development builds
mvn clean package -DskipTests

# Runs docker-compose
docker-compose -f docker/docker-compose.yml down -v
docker-compose -f docker/docker-compose.yml up --build
#2>&1 >> "docker.log"
