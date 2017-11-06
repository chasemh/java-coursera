package chasemh.java.coursera;

import java.util.Random;

/**
 * Exercise solutions to Assignment: Interfaces and Abstract Classes
 * https://www.coursera.org/learn/java-programming-design-principles/supplement/n1DIm/programming-exercise-interface-and-abstract-class 
 *
 * Modified By Chase Hennion
 * @version 2017-11-05
 */
public class MarkovZero extends AbstractMarkovModel {
	
	public MarkovZero() {
		myRandom = new Random();
	}
	
	public void setRandom( int seed ) {
		myRandom = new Random( seed );
	}
	
	public void setTraining( String s ) {
		myText = s.trim();
	}
	
	public String getRandomText( int numChars ) {
		
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
	
	public String toString() {
		return "MarkovModel of Order 0";
	}
	
}
