package com.mapReduceExamples.MirrorImageDNA;

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
	  
	  String mirrorImageWord = new StringBuilder(words[1]).reverse().toString();
	  
	  String keyDNA = ( words[1].compareTo(mirrorImageWord) < 0  ? words[1] : mirrorImageWord ); 
			  
	  System.out.println("Mapper output- key : value >> " + keyDNA+" : "+ words[0]);
		  
	  context.write(new Text(keyDNA), new Text(words[0]));
	  
  }
}
