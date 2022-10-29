# Hadoop docker psuedo-distributed cluster

## Introduction
This is a pseudo-distributed cluster for Hadoop docker image based on the official OpenJDK-8 image. The image is only for educational purposes. Seriously, it runs hadoop as root. It's not secure. It's not meant to be secure. It could be so much better. But it's not. It's just a quick and dirty way to get a hadoop cluster up and running. Run in interactive mode to use the command line.

The build follows the official docs found here:
https://hadoop.apache.org/docs/stable/hadoop-project-dist/hadoop-common/SingleCluster.html

Download the hadoop and maven tarballs to this directory. This could be added to the Dockerfile if you want to automate it. I just didn't want to have to download them every time I built the image.
```
wget https://dlcdn.apache.org/hadoop/common/stable/hadoop-3.3.4.tar.gz
wget https://dlcdn.apache.org/maven/maven-3/3.8.6/binaries/apache-maven-3.8.6-bin.tar.gz
```

Configs are copied into the image from the `hadoop_configs` directory. The configs are modified to run in pseudo-distributed mode. You can find these configs in the hadoop installation at `hadoop/etc/hadoop`.

The container mounts the directory `hadoop-root` as a bind volume to `/tmp/hadoop-root`. This is where hadoop state is stored so keeping it intact will allow you to continue using the cluster after restarting the container. Delete it if you want to start over.

`entrypoint.sh` starts sshd, formats the namenode, and starts the hadoop daemons. `setup.sh` runs an example mapreduce job.

## Build
```
docker build -t hadoop:latest .
```
## Docker run

```bash
docker run --env-file ./hadoop_env -p 9870:9870 -p 9864:9864 -p 9866:9866 \
--mount type=bind,source=$(pwd)/hadoop-root,destination=/tmp/hadoop-root \
-it hadoop:latest bash
```

Run with another mounted directory called `mapreduce` where all the java work happens
```
docker run --env-file ./hadoop_env -p 9870:9870 -p 9864:9864 -p 9866:9866 \
--mount type=bind,source=$(pwd)/hadoop-root,destination=/tmp/hadoop-root \
--mount type=bind,source=$(pwd)/mapreduce,destination=/mapreduce \
-it hadoop:team bash
```

## Example usage

```bash
hdfs namenode -format
start-dfs.sh
hdfs dfs -mkdir /user
hdfs dfs -mkdir /user/root
hdfs dfs -mkdir input
hdfs dfs -put $HADOOP_HOME/etc/hadoop/*.xml input
hadoop jar $HADOOP_HOME/share/hadoop/mapreduce/hadoop-mapreduce-examples-3.3.4.jar grep input output 'dfs[a-z.]+'
```

## Wordcount example

Add import data to hdfs:

```bash
hdfs dfs -mkdir /user
hdfs dfs -mkdir /user/root
hdfs dfs -mkdir data
hdfs dfs -put /mapreduce/wc/input data
```

Build the jar:

```bash
cd /mapreduce/wc
mvn clean install
```

Run the job:

```bash
hadoop jar /mapreduce/wc/target/wc-1.0-SNAPSHOT.jar com.example.wc.WordCount input output
```

## Web interface
The namenode web interface is available at http://localhost:9870 and the datanode web interface is available at http://localhost:9864.
