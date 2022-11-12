#!/bin/bash

hdfs dfs -mkdir /user
hdfs dfs -mkdir /user/root
hdfs dfs -mkdir data
hdfs dfs -put /mapreduce/wc/input data
