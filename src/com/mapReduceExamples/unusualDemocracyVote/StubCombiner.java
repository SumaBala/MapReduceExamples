package com.mapReduceExamples.unusualDemocracyVote;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class StubCombiner extends Reducer<Text, Text, Text, LongWritable> {

  @Override
  public void reduce(Text key, Iterable<Text> values, Context context)
      throws IOException, InterruptedException {
	  
	  ArrayList voters = new ArrayList();
	  String value;
	  int worth = 0;
	  
	  for(Text iw:values)
	  {
		  value = (iw.toString());		  
		  
		  try {
			  worth = Integer.parseInt(value);
		  }
		  catch (Exception e) {
			  voters.add(value);
		  }		  
	  }	  	  
	  System.out.println("combiner Output : "+key + " "+0);
	  context.write( new Text(key)  , new LongWritable(0));
	  	  
	  for(Object voter:(voters.toArray())) 
	  {
		  System.out.println("combiner Output : "+voter.toString() + " "+ worth);
		  context.write( new Text(voter.toString())  ,  new LongWritable(worth));
	  }
  }
}