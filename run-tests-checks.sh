#!/bin/bash

# Runs all tests, bugs checking and generates all SpringDocs
mvn clean compile checkstyle:check spotbugs:check test

# After this script finishes to run:
#
# - For SpringDocs see: 'target/generated-docs'
# - For Checkstyle errors: see the console
# - For SpotBugs errors see: 'target/spotbugsXml.xml'
