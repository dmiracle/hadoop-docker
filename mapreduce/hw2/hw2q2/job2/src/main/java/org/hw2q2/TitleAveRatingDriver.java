package org.hw2q2;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class TitleAveRatingDriver {

    public static void main(String[] args) throws Exception {
        if (args.length != 3) {
            System.out.printf("Usage: hw2q2 <input from ItemRating MR> <items> <output dir>\n");
            System.exit(-1);
        }

        Job job = Job.getInstance();
        job.setJarByClass(TitleAveRatingDriver.class);
        job.setJobName("Item ID and Rating for F users");

        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileInputFormat.addInputPath(job, new Path(args[1]));
        FileOutputFormat.setOutputPath(job, new Path(args[2]));

        job.setMapperClass(TitleAveRatingMapper.class);
        job.setMapOutputKeyClass(IntWritable.class);
        job.setMapOutputValueClass(CompositeKey.class);

        job.setReducerClass(TitleAveRatingReducer.class);

        job.setNumReduceTasks(2);

        // setting the output data type classes
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(FloatWritable.class);

        System.exit(job.waitForCompletion(true) ? 0 : 1);

    }
}
