package com.mapReduceExamples.unusualDemocracyVote;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class StubReducer extends Reducer<Text, Text, Text,Text> {

  @Override
  public void reduce(Text key, Iterable<Text> values, Context context)
      throws IOException, InterruptedException {
	  
	  ArrayList voters = new ArrayList();
	  String value;  
	 
	  int sum = 0;
	  	  
	  for(Text iw:values)
	  {
		  value = (iw.toString());	
		  try {
			  sum += Integer.parseInt(value);
		  }
		  catch (Exception e) {
		  }
		  
	  }	  

	  System.out.println("Reducer  Output : "+key + " "+ sum);
	  context.write( new Text(key)  , new Text( Integer.toString(sum)));
  }
}