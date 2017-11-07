package chasemh.java.coursera;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Exercise solutions to Assignment: Interfaces and Abstract Classes
 * https://www.coursera.org/learn/java-programming-design-principles/supplement/n1DIm/programming-exercise-interface-and-abstract-class 
 *
 * Modified By Chase Hennion
 * @version 2017-11-05
 */
public class EfficientMarkovModel extends AbstractMarkovModel {
	
	private int order;
	private HashMap<String,ArrayList<String>> followsMap;
	
	public EfficientMarkovModel( int order ) {
		this.myRandom = new Random();
		this.order = order;
	}
	
	private HashMap<String,ArrayList<String>> buildMap() {
		// Construct a map of all substrings and their following sets
		
		HashMap<String,ArrayList<String>> followsMap = new HashMap<String,ArrayList<String>>();

		/* Inefficient
		for( int i = 0; i <= this.myText.length() - this.order; ++i ) {
			String key = this.myText.substring( i, i + this.order );
			ArrayList<String> followsSet = new ArrayList<String>();
			
			if( !followsMap.containsKey( key ) ) {
				
				// Find the key repeatedly in myText this.order
				for( int keyIndex = this.myText.indexOf( key ); keyIndex != -1; keyIndex = this.myText.indexOf( key, keyIndex + 1 ) ) {
					// Key found, grab the next character after the key and put it in followsSet
					if( keyIndex == this.myText.length() - this.order ) {
						// At the end of the string, break
						break;
					}
					
					followsSet.add( this.myText.substring( keyIndex + this.order, keyIndex + this.order + 1 ) );
				}
				
				followsMap.put( key, followsSet );
				
			}		
			
		}
		*/
		
		for( int i = 0; i <= this.myText.length() - this.order; ++i ) {
			String key = this.myText.substring( i, i + this.order );
			if( i == this.myText.length() - this.order ) {
				// Special case: Right at the end of the string, no following chars for this string
				followsMap.put( key, new ArrayList<String>() );
			}
			else if( followsMap.containsKey( key ) ) {
				followsMap.get( key ).add( this.myText.substring( i + this.order, i + this.order + 1 ) );
			}
			else {
				ArrayList<String> follows = new ArrayList<String>();
				follows.add( this.myText.substring( i + this.order, i + this.order + 1) );
				followsMap.put( key, follows );
			}
		}

        return followsMap;
		
	}
	
	public void setRandom( int seed ){
		this.myRandom = new Random( seed );
	}
	
	public void setTraining( String s ) {
		this.myText = s.trim();
		this.followsMap = this.buildMap();
		this.printHashMapInfo();
	}
	
	public String getRandomText( int numChars ) {
		
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
		return this.followsMap.get( key );
	}
	
	public String toString() {
		return "EfficientMarkovModel of Order " + this.order;
	}
	
	public void printHashMapInfo() {
		
		boolean printMap = this.followsMap.size() < 50;
		
		String largestKey = "";
		int largestSetSize = -1;
		
		for( String key : this.followsMap.keySet() ) {
			if( printMap ) {
				System.out.print( "Key " + key + ": " + this.followsMap.get( key ) + "\n" );
			}
			int setSize =  this.followsMap.get( key ).size();
			if( setSize > largestSetSize ) {
				largestSetSize = setSize;
				largestKey = key;
			}
			
		}
		
		System.out.println( "Number of keys in map: " + this.followsMap.size() );
		System.out.println( "Largest Follows Set has key " + largestKey + " and is of size " + this.followsMap.get( largestKey ).size() );
		System.out.println( "Strings in the largest follow set: " + this.followsMap.get( largestKey ) );
		
		
	}

}
