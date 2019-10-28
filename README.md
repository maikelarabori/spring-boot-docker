# About this project

## Overview

This is a simple project using a few Spring modules and Docker Compose to run the application.
It will also demonstrates the usage of Spring Doc and the integration with PostgreSQL through Spring JPA.
In addition, a few useful plugins to ensure the code quality are added to the project: Checkstyle, SpotBugs and FindBugsSec.

## Prerequisites
In order to run and compile the project correctly you will need:

1. OpenJDK 13+
2. Maven 3.5+
3. Docker Desktop + Docker Compose
4. Linux or MacOS

## Getting started

The project brings three files that are executable in Linux or Mac systems.
They will help you to get started, debug and test your code. The following commands
must be run in the root project directory.

### Running the project in development mode
```code
$ sh run.sh
```
> Once your embedded Jetty server is started you can fire requests
against the endpoints through **http://localhost:8080**.

### Debugging your project
```code
$ sh run-debug.sh
```
> Now, connect in the port 5005 using the remote debug of your IDE.
> You're able to requests to **http://localhost:8080** and debug as usual.

### Test, validate your code and prevent bugs
```code
$ sh run-debug.sh
```
> The build will fail if some test fails, or you have some code styling issue or possible bugs are found.

## Example of implemented requests
*__You need to have cURL installed__*.

> Registers a new user
```code
curl -X POST \
  http://localhost:8080/api/users \
  -H 'Accept: application/json' \
  -H 'Accept-Encoding: gzip, deflate' \
  -H 'Cache-Control: no-cache' \
  -H 'Content-Type: application/json' \
  -H 'cache-control: no-cache' \
  -d '{
    "email": "maikel@somemail.io",
    "password": "Password1",
    "username": "maikel",
    "yearOfBirth": 1994
}'
```

> Lists all users stored
```code
curl -X GET \
  http://localhost:8080/api/users \
  -H 'Accept: application/json' \
  -H 'Content-Type: application/json' \
  -H 'cache-control: no-cache'
```
## Contribution
Feel free to make suggestions for fixes, improvements or contributing through pull requests.