package com.mapReduceExamples.TopWordRecommendation;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class StubReducer extends Reducer<Text, Text, Text, Text> {

  @Override
  public void reduce(Text key, Iterable<Text> values, Context context)
      throws IOException, InterruptedException {
	  
	  ArrayList recommendationArray = new ArrayList();
	  StringBuffer outputRecommendation = new StringBuffer();
	  
	  int i = 0;
	  String value;
	  Iterator iw = values.iterator();

	  while ( iw.hasNext() && i <=3)
	  {
		  value = (iw.next()).toString();
		  if (recommendationArray.size() > 0 && recommendationArray.contains(value)) 
		  {
			 System.out.println("already contains"+value);
		  }
		  else {
			  
			  recommendationArray.add(value);
			  outputRecommendation.append( (outputRecommendation.length()>0?" , ":" ")+value ) ;
			  
		  }
		  i++;
	  }
	  
	  System.out.println(key+" "+outputRecommendation.toString());
	  context.write(key, new Text(outputRecommendation.toString()));
  }
}
