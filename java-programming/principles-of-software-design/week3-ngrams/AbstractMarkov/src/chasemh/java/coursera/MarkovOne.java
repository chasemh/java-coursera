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
public class MarkovOne extends AbstractMarkovModel {
	
	public MarkovOne() {
		myRandom = new Random();
	}
	
	public void setRandom( int seed ){
		myRandom = new Random( seed );
	}
	
	public void setTraining( String s ){
		myText = s.trim();
	}
	
	public String getRandomText( int numChars ) {
		
		if (myText == null){
			return "";
		}
		
		StringBuilder sb = new StringBuilder();
		int index = this.myRandom.nextInt( this.myText.length() - 1);
        String key = this.myText.substring( index, index + 1 );
        sb.append(key);
		
		for(int k = 0; k < numChars - 1; k++){
			
			ArrayList<String> follows = this.getFollows( key );
			if( follows.size() == 0 ) {
				break;
			}
			index = this.myRandom.nextInt( follows.size() );
			String next = follows.get( index );
			sb.append( next );
			key = next;
			
		}
		
		return sb.toString();
	}
	
	public ArrayList<String> getFollows( String key ) {
		// Find all characters in myText that follow the given key
		ArrayList<String> followsSet = new ArrayList<String>();
		
		// Find the key repeatedly in myText
		for( int keyIndex = this.myText.indexOf( key ); keyIndex != -1; keyIndex = this.myText.indexOf( key, keyIndex + key.length() ) ) {
			// Key found, grab the next character after the key and put it in followsSet
			if( keyIndex == this.myText.length() - key.length() ) {
				// At the end of the string, break
				break;
			}
			
			followsSet.add( this.myText.substring( keyIndex + key.length(), keyIndex + key.length() + 1 ) );
		}
		
		return followsSet;
	}
	
	public String toString() {
		return "MarkovModel of Order 1";
	}

}
