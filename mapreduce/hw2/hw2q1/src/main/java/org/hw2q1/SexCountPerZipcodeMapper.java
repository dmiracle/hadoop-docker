package org.hw2q1;

import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;

public class SexCountPerZipcodeMapper extends Mapper<LongWritable, Text, CompositeKey, IntWritable> {
    static enum CountersEnum {
        Teen, Adult, Senior
    };

    @Override
    public void map(LongWritable key, Text value, Context context)
            throws IOException, InterruptedException {

        String line = value.toString();
        String[] fields = line.split("\\|");

        String zipcode = fields[4];
        char sex = fields[2].charAt(0);
        int age = Integer.parseInt(fields[1]);

        if (age < 20) {
            context.getCounter(CountersEnum.Teen).increment(1);
        } else if (age < 60) {
            context.getCounter(CountersEnum.Adult).increment(1);
        } else {
            context.getCounter(CountersEnum.Senior).increment(1);
        }

        CompositeKey ck = new CompositeKey(zipcode, sex);
        IntWritable outputValue = new IntWritable(1);
        context.write(ck, outputValue);

    }

}
