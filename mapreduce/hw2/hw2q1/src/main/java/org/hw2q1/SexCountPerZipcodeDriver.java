package org.hw2q1;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapred.Counters.Counter;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class SexCountPerZipcodeDriver {

    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            System.out.printf("Usage: hw2q1 <input dir> <output dir>\n");
            System.exit(-1);
        }

        Job job = Job.getInstance();
        job.setJarByClass(SexCountPerZipcodeDriver.class);
        job.setJobName("Sex Count Per Zipcode");

        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        job.setMapperClass(SexCountPerZipcodeMapper.class);
        job.setMapOutputKeyClass(CompositeKey.class);
        job.setMapOutputValueClass(IntWritable.class);

        job.setReducerClass(SexCountPerZipcodeReducer.class);
        job.setCombinerClass(SexCountPerZipcodeReducer.class);
        job.setPartitionerClass(SexPartitioner.class);

        job.setNumReduceTasks(2);

        // setting the output data type classes
        job.setOutputKeyClass(CompositeKey.class);
        job.setOutputValueClass(IntWritable.class);

        System.exit(job.waitForCompletion(true) ? 0 : 1);

    }
}
