package com.mapReduceExamples.unusualDemocracyVote;

import java.io.IOException;
import java.util.Arrays;

import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class StubMapper extends Mapper<Object, Text, Text, Text> {

  @Override
  public void map(Object key, Text value, Context context)
      throws IOException, InterruptedException {
    /*
     * TODO implement
     */
	  String[] words = value.toString().split("[ \t]+");
	  
	  System.out.println("Mapper output- key : value >> " + words[0]+" : "+ words[1]);
		  
	  context.write(new Text(words[0]), new Text(words[1]));	  
  }
}
