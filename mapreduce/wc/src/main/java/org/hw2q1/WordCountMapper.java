package org.hw2q1;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;

public class WordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

    @Override
    public void map(LongWritable key, Text value, Context context)
            throws IOException, InterruptedException {

        /*
         * convert the line which is received as a Text object to a String Object
         */
        String line = value.toString();
        /*
         * The line.split("\\W+") call uses regular expressions to split the line up
         * by non-word characters.
         */
        for (String word : line.split("\\W+")) {
            if (word.length() > 0) {
                Text outputKey = new Text(word);
                IntWritable outputValue = new IntWritable(1);
                context.write(outputKey, outputValue);
            }
        }
    }
}
