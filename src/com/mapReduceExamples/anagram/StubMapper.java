package com.mapReduceExamples.anagram;

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
	  for(String word:words)
	  {
		  char[] sortedArray = word.toCharArray();
		  
		  Arrays.sort(sortedArray);
		  
		  String sortedWord = Arrays.toString(sortedArray);
		  
		  System.out.println(sortedWord + word);
		  
		  context.write(new Text(sortedWord), new Text(word));
	  }
  }
}
