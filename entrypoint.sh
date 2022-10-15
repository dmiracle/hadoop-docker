#!/bin/bash

service ssh restart
hdfs namenode -format
start-dfs.sh
