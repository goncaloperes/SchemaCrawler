#!/usr/bin/env bash
rm -f *.class
javac -classpath ../../_schemacrawler/config:$(echo ../../_schemacrawler/lib/*.jar | tr ' ' ':'):.  com/example/ApiExample.java
java -classpath ../../_schemacrawler/config:$(echo ../../_schemacrawler/lib/*.jar | tr ' ' ':'):. com.example.ApiExample
