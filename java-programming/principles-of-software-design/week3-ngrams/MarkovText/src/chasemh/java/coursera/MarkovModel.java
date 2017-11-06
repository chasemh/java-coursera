package chasemh.java.coursera;

import java.util.ArrayList;
import java.util.Random;

/**
 * Exercise solutions to Assignment: Generating Random Text
 * https://www.coursera.org/learn/java-programming-design-principles/supplement/xToQx/programming-exercise-generating-random-text 
 *
 * Modified By Chase Hennion
 * @version 2017-11-04
 */
public class MarkovModel {
	
	private String myText;
	private Random myRandom;
	private int order;
	
	public MarkovModel( int order ) {
		this.myRandom = new Random();
		this.order = order;
	}
	
	public void setRandom(int seed){
		this.myRandom = new Random(seed);
	}
	
	public void setTraining(String s){
		this.myText = s.trim();
	}
	
	public String getRandomText(int numChars){
		if ( this.myText == null ){
			return "";
		}

		StringBuilder sb = new StringBuilder();
		int index = this.myRandom.nextInt( this.myText.length() - this.order );
        String key = this.myText.substring( index, index + this.order );
        sb.append(key);
		
		for(int k = 0; k < numChars - this.order; k++){
			
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

}
