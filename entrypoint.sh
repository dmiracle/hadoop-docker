#!/bin/bash

service ssh restart
hdfs namenode -format -nonInteractive
# start-dfs.sh
start-all.sh
# yarn app -launch sleeper-service /spark_examples/sleeper.json