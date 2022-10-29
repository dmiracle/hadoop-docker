package org.hw2q2;

import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;

public class TitleAveRatingMapper extends Mapper<LongWritable, Text, IntWritable, CompositeKey> {

    @Override
    public void map(LongWritable key, Text value, Context context)
            throws IOException, InterruptedException {
        Integer itemId = -1;
        String line = value.toString();
        String[] item_fields = line.split("\\|");
        String[] join_fields = line.split("\t");
        CompositeKey ck = new CompositeKey();
        if (item_fields.length > 5) {
            ck = new CompositeKey(item_fields[0], item_fields[1], true);
            itemId = Integer.parseInt(item_fields[0]);
        } else if (join_fields.length == 2) {
            ck = new CompositeKey(join_fields[0], join_fields[1]);
            itemId = Integer.parseInt(join_fields[0]);
        } else {
            System.out.println("Error: Invalid input");
            System.exit(1);
        }

        IntWritable outputKey = new IntWritable(itemId);
        if (itemId != -1) {
            context.write(outputKey, ck);
        }
    }
}
