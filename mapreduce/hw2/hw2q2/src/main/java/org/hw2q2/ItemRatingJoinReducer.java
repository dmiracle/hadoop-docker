package org.hw2q2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;

public class ItemRatingJoinReducer extends Reducer<IntWritable, CompositeKey, Text, Text> {
    @Override
    public void reduce(IntWritable key, Iterable<CompositeKey> values, Context context)
            throws IOException, InterruptedException {
        char sex = ' ';
        Map<String, String> lines = new HashMap<String, String>();
        for (CompositeKey value : values) {
            if (value.getSex() != ' ') {
                sex = value.getSex();
            } else {
                lines.put(value.getItemId(), value.getRating());
            }
        }

        if (sex == 'F') {
            for (String outkey : lines.keySet()) {
                context.write(new Text(outkey), new Text(lines.get(outkey)));
            }
        }
    }
}
