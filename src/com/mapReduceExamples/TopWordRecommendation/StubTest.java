package com.mapReduceExamples.TopWordRecommendation;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.apache.hadoop.mrunit.mapreduce.MapReduceDriver;
import org.apache.hadoop.mrunit.mapreduce.ReduceDriver;
import org.apache.hadoop.mrunit.types.Pair;
import org.junit.Before;
import org.junit.Test;

public class StubTest {

	/*
	 * Declare harnesses that let you test a mapper, a reducer, and a mapper and
	 * a reducer working together.
	 */
	MapDriver<Object, Text, Text, Text> mapDriver;
	ReduceDriver<Text, Text, Text, Text> reduceDriver;
	MapReduceDriver<Object, Text, Text, Text, Text, Text> mapReduceDriver;

	/*
	 * Set up the test. This method will be called before every test.
	 */
	@Before
	public void setUp() {

		/*
		 * Set up the mapper test harness.
		 */
		StubMapper mapper = new StubMapper();
		mapDriver = new MapDriver<Object, Text, Text, Text>();
		mapDriver.setMapper(mapper);
		/*
		 * Set up the reducer test harness.
		 */
		StubReducer reducer = new StubReducer();
		reduceDriver = new ReduceDriver<Text, Text, Text, Text>();
		reduceDriver.setReducer(reducer);

		/*
		 * Set up the mapper/reducer test harness.
		 */
		mapReduceDriver = new MapReduceDriver<Object, Text, Text, Text, Text, Text>();
		mapReduceDriver.setMapper(mapper);
		mapReduceDriver.setReducer(reducer);
	}

	/*
	 * Test the mapper.
	 */
	@Test
	public void testMapper() {

		/*
		 * For this test, the mapper's input will be "1 cat cat dog" TODO:
		 * implement
		 */
		mapDriver.setInputKey("1");
		mapDriver.setInputValue(new Text("'Happy birthda'y to your. 9435s and your twin"));
		mapDriver.setInputKey("2");
		mapDriver.setInputValue(new Text("Happy New year to you and your family"));
		mapDriver.setInputKey("3");
		mapDriver.setInputValue(new Text("wish you a happy aniversary to both of you"));
		mapDriver.setInputKey("4");
		mapDriver.setInputValue( new Text("when are you celebrating  "));
		mapDriver.setInputKey("5");
		mapDriver.setInputValue(new Text("what are you eating"));
		mapDriver.setInputKey("6");
		mapDriver.setInputValue(new Text("give you a gift"));
		List<Pair<Text, Text>> x;
		try {
			x = mapDriver.run();
			assertEquals(x.size(), 3);
			assertEquals(x.get(0).getFirst().toString(), "this");
			assertEquals(x.get(1).getFirst().toString(), "is");
			assertEquals(x.get(2).getFirst().toString(), "test");
			
			for(Pair<Text, Text> p:x)
			{
				System.out.println("MAPPER: " + p.getFirst() + ": " + p.getSecond());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/*
	 * Test the reducer.
	 */
	@Test
	public void testReducer() {

		/*
		 * For this test, the reducer's input will be "cat 1 1". The expected
		 * output is "cat 2". TODO: implement
		 */
		fail("Please implement test.");

	}

	/*
	 * Test the mapper and reducer working together.
	 */
	@Test
	public void testMapReduce() throws IOException {

		mapReduceDriver.addInput(new Pair<Object, Text>("1", new Text(
				"Abolitionism as a principle was far more than just a 'concerted set of messages aimed at influencing the opinions or behavior of large numbers of people. Instead of impartially providing information, propaganda in its most basic sense presents information in order to influence its audience. The most effective propaganda is often completely truthful, but some propaganda presents facts selectively to encourage a particular synthesis, or gives loaded messages in order to produce an emotional rather than rational response to the information presented. The desired result is a change of the cognitive narrative of the subject in the target audience.")));
		mapReduceDriver.addInput(new Pair<Object, Text>("2", new Text(
				"These word These originates from the Latin name Congregatio de Propaganda Fide (\"Congregation for the Spreading of the Faith\") of a congregation founded by Pope Gregory XV in 1622, shortly after the start of the Thirty Years' War. This department of the pontifical administration was charged with the spread of Catholicism and with the regulation of ecclesiastical affairs in mission territory.")));
		mapReduceDriver.addInput(new Pair<Object, Text>("3", new Text(
				"wish you a happy aniversary for both of you")));
		mapReduceDriver.addInput(new Pair<Object, Text>("4", new Text(
				"when are you celebrating  ")));
		mapReduceDriver.addInput(new Pair<Object, Text>("5", new Text(
				"what are you eating")));
		mapReduceDriver.addInput(new Pair<Object, Text>("6", new Text(
				"give you a gift")));
		

		//mapReduceDriver.addInput(new Pair<Object, Text>("2", new Text(
		//		"teach the map and reduce class is fun.")));
		List<Pair<Text, Text>> output = mapReduceDriver.run();
		assertEquals(45, output.size());
		for (Pair<Text, Text> p : output) {
			System.out.println(p.getFirst() + " - " + p.getSecond());
		}
	}
}
