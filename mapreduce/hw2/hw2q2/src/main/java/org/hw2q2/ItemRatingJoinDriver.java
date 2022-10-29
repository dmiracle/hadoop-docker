package org.hw2q2;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class ItemRatingJoinDriver {

    public static void main(String[] args) throws Exception {
        if (args.length != 3) {
            System.out.printf("Usage: hw2q2 <input dir1> <input dir2> <output dir>\n");
            System.exit(-1);
        }

        Job job = Job.getInstance();
        job.setJarByClass(ItemRatingJoinDriver.class);
        job.setJobName("Item ID and Rating for F users");

        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileInputFormat.addInputPath(job, new Path(args[1]));
        FileOutputFormat.setOutputPath(job, new Path(args[2]));

        job.setMapperClass(ItemRatingJoinMapper.class);
        job.setMapOutputKeyClass(IntWritable.class);
        job.setMapOutputValueClass(CompositeKey.class);

        job.setReducerClass(ItemRatingJoinReducer.class);

        job.setNumReduceTasks(2);

        // setting the output data type classes
        job.setOutputKeyClass(IntWritable.class);
        job.setOutputValueClass(IntWritable.class);

        System.exit(job.waitForCompletion(true) ? 0 : 1);

    }
}
