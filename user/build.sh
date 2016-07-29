#!/bin/bash

mvn clean package
docker build -t albi/service1 .

