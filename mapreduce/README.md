
```
mvn archetype:generate -DgroupId=org.hw2q1 -DartifactId=hw2q1
```

pom from assignment
```xml
    <repositories>
        <repository>
            <id>cloudera</id>
            <url>https://repository.cloudera.com/artifactory/cloudera-repos</url>
            <releases>          <enabled>true</enabled>     </releases>
            <snapshots>          <enabled>true</enabled> </snapshots>
        </repository>
    </repositories>
    <dependencies>
        <dependency>
            <groupId>org.apache.hadoop</groupId>
            <artifactId>hadoop-core</artifactId>
            <version>2.6.0-mr1-cdh5.13.0</version>
        </dependency>

        <dependency>
            <groupId>org.apache.hadoop</groupId>
            <artifactId>hadoop-hdfs</artifactId>
            <version>2.6.0-cdh5.13.0</version>
        </dependency>

        <dependency>
            <groupId>org.apache.hadoop</groupId>
            <artifactId>hadoop-common</artifactId>
            <version>2.6.0-cdh5.13.0</version>
        </dependency>

        <dependency>
            <groupId>org.apache.hadoop</groupId>
            <artifactId>hadoop-mapreduce-client-common</artifactId>
            <version>3.1.3</version>
            <scope>compile</scope>
        </dependency>

    </dependencies>

```

Wordcount tests text:
```
Hi there. I am testing the word count algorithm.
The word count algorithm is a very simple algorithm. The algorithm takes a text file as input and outputs the number of times each word appears in the text file.
The word count algoritm is implemented in the Mapper and Reducer classes. The Mapper class takes a line of text as input and outputs a key-value pair where the key is the word and the value is 1. The Reducer class takes the key-value pairs from the Mapper class and outputs a key-value pair where the key is the word and the value is the number of times the word appears in the text file.
```


https://hadoop.apache.org/docs/stable/hadoop-project-dist/hadoop-hdfs/HDFSCommands.html

https://mvnrepository.com/artifact/edu.uchicago.mpcs53013/hadoop-archetype
