# Mapreduce wordcount example

Get started with a maven archetype to create a simple mapreduce job.

```
mvn archetype:generate -DgroupId=org.wc -DartifactId=wc
```

## Sample text

Wordcount tests text:
```
Hi there. I am testing the word count algorithm.
The word count algorithm is a very simple algorithm. The algorithm takes a text file as input and outputs the number of times each word appears in the text file.
The word count algoritm is implemented in the Mapper and Reducer classes. The Mapper class takes a line of text as input and outputs a key-value pair where the key is the word and the value is 1. The Reducer class takes the key-value pairs from the Mapper class and outputs a key-value pair where the key is the word and the value is the number of times the word appears in the text file.
```

## References

https://hadoop.apache.org/docs/stable/hadoop-project-dist/hadoop-hdfs/HDFSCommands.html

https://mvnrepository.com/artifact/edu.uchicago.mpcs53013/hadoop-archetype
