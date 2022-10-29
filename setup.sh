#!/bin/bash

hdfs dfs -mkdir /user
hdfs dfs -mkdir /user/root
hdfs dfs -mkdir data
hdfs dfs -mkdir user
hdfs dfs -mkdir item
hdfs dfs -put /mapreduce/movielens-homework-02-input/u.data data
hdfs dfs -put /mapreduce/movielens-homework-02-input/u.user user
hdfs dfs -put /mapreduce/movielens-homework-02-input/u.item item
