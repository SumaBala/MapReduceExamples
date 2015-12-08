package com.mapReduceExamples.TopWordRecommendation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

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
		  String ignoreWords = "(they|such|could|much|as|had|can|for|since|do|these|it|be|where|whom|than|is|to|with|and|of|are|a|an|on|was|were|who|the|in|have|was|at|or|but|because|by|this)*";
		  String keyWord;
		  String valueWord;	  
		  
		  		  
		  for(int i= 1; i<words.length;i++)
		  {

			  keyWord = words[i-1].trim().replaceAll("\"", "");
			  valueWord = words[i].trim().replaceAll("\"", "");
			  keyWord = words[i-1].trim().replaceAll("\\.", "");
			  valueWord = words[i].trim().replaceAll("\\.", "");
			  
			  if (	
					( keyWord.toLowerCase().matches("[a-z]+[']?[a-z]*") || keyWord.toLowerCase().matches("[a-z]+") )
							&&
					(valueWord.toLowerCase().matches("[a-z]+[']?[a-z]*") || valueWord.toLowerCase().matches("[a-z]+") )
						&&
					!keyWord.toLowerCase().matches(ignoreWords) &&
					!valueWord.toLowerCase().matches(ignoreWords)
				 ) 
			  {
				  
				  
				  if (  (null != keyWord && !("").equals(keyWord)) &&
						  (null != valueWord && !("").equals(valueWord)) 
				     ) 
				  {
					
					  System.out.println(keyWord + " "+valueWord);
				  
					  context.write(new Text(keyWord), new Text(valueWord));
				  }
				 
			  } else {
				  //System.out.println("Ignored = " +keyWord + " "+valueWord);
			  }
		  }
	  
  }
}
