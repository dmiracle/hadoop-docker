package org.hw2q1;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Partitioner;

public class SexPartitioner extends Partitioner<CompositeKey, IntWritable> {

    @Override
    public int getPartition(CompositeKey key, IntWritable value, int numPartitions) {
        return (key.getSex() == 'M') ? 0 : 1;
    }

}
