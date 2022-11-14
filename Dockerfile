FROM openjdk:8-jdk
RUN apt-get update && apt-get install -y sudo openssh-server ssh vim && apt-get clean \
    && rm -rf /var/lib/apt/lists/*

ADD apache-maven-3.8.6-bin.tar.gz /opt
RUN mv /opt/apache-maven-3.8.6 /opt/maven
ENV MAVEN_HOME=/opt/maven

ADD hadoop-3.3.4.tar.gz /opt
RUN mv /opt/hadoop-3.3.4 /opt/hadoop
COPY hadoop_configs/core-site.xml /opt/hadoop/etc/hadoop/core-site.xml
COPY hadoop_configs/hdfs-site.xml /opt/hadoop/etc/hadoop/hdfs-site.xml
COPY hadoop_configs/hadoop-env.sh /opt/hadoop/etc/hadoop/hadoop-env.sh
ENV HADOOP_HOME=/opt/hadoop

ADD spark-3.3.1-bin-hadoop3.tgz /opt
RUN mv /opt/spark-3.3.1-bin-hadoop3 /opt/spark
ENV SPARK_HOME=/opt/spark

ENV PATH=$PATH:$HADOOP_HOME/bin:$HADOOP_HOME/sbin:$MAVEN_HOME/bin:$SPARK_HOME/bin
RUN ssh-keygen -t rsa -P '' -f ~/.ssh/id_rsa && \
    cat ~/.ssh/id_rsa.pub >> ~/.ssh/authorized_keys && \
    chmod 0600 ~/.ssh/authorized_keys
COPY entrypoint.sh /entrypoint.sh
COPY setup_generic.sh /setup_generic.sh
RUN chmod +x /entrypoint.sh && chmod +x /setup_generic.sh
ENTRYPOINT /entrypoint.sh && bash
