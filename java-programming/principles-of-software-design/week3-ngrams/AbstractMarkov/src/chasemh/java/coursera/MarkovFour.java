package chasemh.java.coursera;

import java.util.ArrayList;
import java.util.Random;

/**
 * Exercise solutions to Assignment: Interfaces and Abstract Classes
 * https://www.coursera.org/learn/java-programming-design-principles/supplement/n1DIm/programming-exercise-interface-and-abstract-class 
 *
 * Modified By Chase Hennion
 * @version 2017-11-05
 */
public class MarkovFour extends AbstractMarkovModel {
	
	public MarkovFour() {
		myRandom = new Random();
	}
	
	public void setRandom( int seed ) {
		myRandom = new Random( seed );
	}
	
	public void setTraining(String s) {
		myText = s.trim();
	}
	
	public String getRandomText(int numChars) {
		
		if (myText == null){
			return "";
		}
		
		StringBuilder sb = new StringBuilder();
		int index = this.myRandom.nextInt( this.myText.length() - 4 );
        String key = this.myText.substring( index, index + 4 );
        sb.append( key );
		
		for(int k = 0; k < numChars - 4; k++){
			
			ArrayList<String> follows = this.getFollows( key );
			if( follows.size() == 0 ) {
				break;
			}

			index = this.myRandom.nextInt( follows.size() );
			String next = follows.get( index );
			sb.append( next );
			key = key.substring( 1 ) + next;
			
		}
		
		return sb.toString();
	}
	
	public String toString() {
		return "MarkovModel of Order 4";
	}

}
