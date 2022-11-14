import sys
from pyspark import SparkContext

sc = SparkContext()
sc.setLogLevel("ERROR")

#file is on HDFS
counts = sc.textFile("/spark_examples/shakespeare/comedies")
words = counts.flatMap(lambda line:line.split())
word1pair = words.map(lambda word: (word,1))
wordcounts = word1pair.reduceByKey(lambda v1,v2: v1+v2)
for pair in wordcounts.take(5): print(pair)

sc.stop()