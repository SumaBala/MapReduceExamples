package com.mapReduceExamples.anagram;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class StubReducer extends Reducer<Text, Text, Text, Text> {

  @Override
  public void reduce(Text key, Iterable<Text> values, Context context)
      throws IOException, InterruptedException {
	  
	  ArrayList anagram = new ArrayList();
	  String value;
	  String outputAnagram = new String();
	  
	  for(Text iw:values)
	  {
		  value = (iw.toString());
		  if (anagram.size() > 0 && anagram.contains(value)) 
		  {
			 System.out.println("already contains"+value);
		  }
		  else {
			  
			  anagram.add(value);
			  outputAnagram = outputAnagram + new String(" ") + value;
			  
		  }
	  }
	  System.out.println(key+outputAnagram);
	  context.write(key, new Text(outputAnagram));
  }
}
