#!/bin/bash

ENV=$1
VERSION=$2

if [[ ${VERSION} = "" ]]; then
    VERSION="1.0.0"
fi

if [[ ${ENV} = "" ]]; then
    echo "Building the project pointing to local environment"
    ./gradlew clean build
else
    echo "Building the project pointing to ${ENV} environment"
    ./gradlew clean build -Penv=${ENV}
fi

echo `pwd`

echo "Starting the service"
java -jar build/libs/hs-moments-${VERSION}.jar
