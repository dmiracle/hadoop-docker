package org.hw2q2;

import java.io.IOException;
import java.util.List;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;

public class TitleAveRatingReducer extends Reducer<IntWritable, CompositeKey, Text, FloatWritable> {
    @Override
    public void reduce(IntWritable key, Iterable<CompositeKey> values,
            Context context)
            throws IOException, InterruptedException {
        Integer sum = 0;
        Integer count = 0;
        String title = "";
        for (CompositeKey value : values) {
            if (value.getRating().equals("") == false) {
                sum += Integer.parseInt(value.getRating());
                count++;
            } else {
                title = value.getTitle();
            }
        }
        Float avg = (float) sum / count;
        context.write(new Text(title.toString()), new FloatWritable(avg));
    }
}
