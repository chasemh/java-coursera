package chasemh.java.coursera;

import java.util.Random;

/**
 * Exercise solutions to Assignment: Generating Random Text
 * https://www.coursera.org/learn/java-programming-design-principles/supplement/xToQx/programming-exercise-generating-random-text 
 *
 * Modified By Chase Hennion
 * @version 2017-11-04
 */
public class MarkovZero {
	
    private String myText;
	private Random myRandom;
	
	public MarkovZero() {
		myRandom = new Random();
	}
	
	public void setRandom(int seed){
		myRandom = new Random(seed);
	}
	
	public void setTraining(String s){
		myText = s.trim();
	}
	
	public String getRandomText(int numChars){
		if (myText == null){
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for(int k=0; k < numChars; k++){
			int index = myRandom.nextInt(myText.length());
			sb.append(myText.charAt(index));
		}
		
		return sb.toString();
	}
}
