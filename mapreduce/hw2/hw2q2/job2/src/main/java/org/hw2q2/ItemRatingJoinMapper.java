package org.hw2q2;

import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;

public class ItemRatingJoinMapper extends Mapper<LongWritable, Text, IntWritable, CompositeKey> {

    @Override
    public void map(LongWritable key, Text value, Context context)
            throws IOException, InterruptedException {
        int userId = -1;
        String line = value.toString();
        String[] user_fields = line.split("\\|");
        String[] data_fields = line.split("\t");
        CompositeKey ck = new CompositeKey();
        if (user_fields.length == 5) {
            ck = new CompositeKey(user_fields[0], user_fields[2].charAt(0));
            userId = Integer.parseInt(user_fields[0]);
        } else if (data_fields.length == 4) {
            ck = new CompositeKey(data_fields[0], data_fields[1], data_fields[2]);
            userId = Integer.parseInt(data_fields[0]);
        } else {
            System.out.println("Error: Invalid input");
            System.exit(1);
        }

        IntWritable outputKey = new IntWritable(userId);
        context.write(outputKey, ck);

    }

}
