FROM openjdk:8-jdk
RUN apt-get update && apt-get install -y sudo openssh-server ssh && apt-get clean \
    && rm -rf /var/lib/apt/lists/*
ADD hadoop-3.3.4.tar.gz /opt
RUN mv /opt/hadoop-3.3.4 /opt/hadoop
COPY hadoop_configs/core-site.xml /opt/hadoop/etc/hadoop/core-site.xml
COPY hadoop_configs/hdfs-site.xml /opt/hadoop/etc/hadoop/hdfs-site.xml
COPY hadoop_configs/hadoop-env.sh /opt/hadoop/etc/hadoop/hadoop-env.sh
ENV HADOOP_HOME=/opt/hadoop
ENV PATH=$PATH:$HADOOP_HOME/bin:$HADOOP_HOME/sbin
RUN ssh-keygen -t rsa -P '' -f ~/.ssh/id_rsa && \
    cat ~/.ssh/id_rsa.pub >> ~/.ssh/authorized_keys && \
    chmod 0600 ~/.ssh/authorized_keys
COPY entrypoint.sh /entrypoint.sh
COPY setup.sh /setup.sh
RUN chmod +x /entrypoint.sh && chmod +x /setup.sh
ENTRYPOINT /entrypoint.sh && bash
