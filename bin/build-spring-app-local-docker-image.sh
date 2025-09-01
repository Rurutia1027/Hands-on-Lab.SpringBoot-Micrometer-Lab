#!/bin/sh 

cd springboot-micrometer-hol && mvn clean compile jib:build -Pdocker-local -DskipTests