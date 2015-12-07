package com.mapReduceExamples.DNA;

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
	  
	  ArrayList DNASequence = new ArrayList();
	  String value;
	  String outputDNASequence = new String();
	  
	  for(Text iw:values)
	  {
		  value = (iw.toString());
		  	  
		  DNASequence.add(value);
		  outputDNASequence = outputDNASequence + new String(" ") + value;
			  
		  
	  }
	  System.out.println("Reducer Output : "+ key+outputDNASequence);
	  context.write(key, new Text(outputDNASequence));
  }
}
