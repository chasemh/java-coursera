package chasemh.java.coursera;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Exercise solutions to Assignment: WordGram Class
 * https://www.coursera.org/learn/java-programming-design-principles/supplement/cajch/programming-exercise-wordgram-class 
 *
 * Modified By Chase Hennion
 * @version 2017-11-07
 */
public class EfficientMarkovWord implements IMarkovModel {
	
	private String[] myText;
	private Random myRandom;
	private int myOrder;
	private HashMap<WordGram, ArrayList<String>> myFollowsMap;
	
	public EfficientMarkovWord( int order ) {
		this.myOrder = order;
		this.myRandom = new Random();
	}
	
	public void setRandom( int seed ) {
		this.myRandom = new Random( seed );
	}
	
	public void setTraining( String text ) {
		this.myText = text.split( "\\s+" );
		this.myFollowsMap = this.buildMap();
		this.printHashMapInfo();
	}
	
	public int indexOf( String[] words, WordGram target, int start ) {
		for( int i = start; i < words.length - this.myOrder; ++i ) {
			WordGram test = new WordGram( words, i, this.myOrder );
			if( test.equals( target ) ) {
				return i;
			}
		}
		return -1;
	}
	
	public ArrayList<String> getFollows( WordGram kGram ) {
		return this.myFollowsMap.get( kGram );
	}
	
	public String getRandomText( int numWords ) {
		StringBuilder sb = new StringBuilder();
		
		int index = myRandom.nextInt( this.myText.length - this.myOrder );
		WordGram key = new WordGram( this.myText, index, this.myOrder );
		sb.append( key + " " );
		for( int k = 0; k < numWords - this.myOrder; k++ ) {
		    ArrayList<String> follows = getFollows( key );
		    if (follows.size() == 0) {
		        break;
		    }
			index = myRandom.nextInt( follows.size() );
			String next = follows.get( index );
			sb.append( next + " " );
			
			key = key.shiftAdd( next );
		}
		
		return sb.toString().trim();
	}
	
	private HashMap<WordGram, ArrayList<String>> buildMap() {
		HashMap<WordGram, ArrayList<String>> map = new HashMap<WordGram, ArrayList<String>>();
		
		/* Noticeably inefficient
		for( int i = 0; i <= this.myText.length - this.myOrder; ++i ) {
			WordGram key = new WordGram( this.myText, i, this.myOrder );
			if( !map.containsKey( key ) ) {
				ArrayList<String> followList = new ArrayList<String>();
				for( int keyIndex = this.indexOf( this.myText, key, i ); keyIndex != -1; keyIndex = this.indexOf( this.myText, key, keyIndex + 1 ) ) {
					if( keyIndex == this.myText.length - this.myOrder ) {
						// At the end of the array, break
						break;
					}
					followList.add( this.myText[ keyIndex + this.myOrder ] );
				}
				map.put( key, followList );
			}
		}
		*/
		
		for( int i = 0; i <= this.myText.length - this.myOrder; ++i ) {
			
			WordGram key = new WordGram( this.myText, i, this.myOrder );
			if( i == this.myText.length - this.myOrder ) {
				// Special handling for word gram that is right at the end of the text
				map.put( key, new ArrayList<String>() );
			}
			else if( map.containsKey( key ) ) {
				map.get( key ).add( this.myText[ i + this.myOrder ] );
			}
			else {
				ArrayList<String> follows = new ArrayList<String>();
				follows.add( this.myText[ i + this.myOrder ] );
				map.put( key, follows );
			}
		}
		
		return map;
	}
	
	public void printHashMapInfo() {
		
		boolean printMap = this.myFollowsMap.size() < 50;
		
		int largestSetSize = -1;
		
		for( WordGram key : this.myFollowsMap.keySet() ) {
			if( printMap ) {
				System.out.print( "Key " + key + ": " + this.myFollowsMap.get( key ) + "\n" );
			}
			int setSize =  this.myFollowsMap.get( key ).size();
			if( setSize > largestSetSize ) {
				largestSetSize = setSize;
			}
			
		}
		
		// Print out the largest key sets
		System.out.println( "Follow Sets of largest size (" + largestSetSize + ")\n======================================" );
		for( WordGram key: this.myFollowsMap.keySet() ) {
			if( this.myFollowsMap.get( key ).size() == largestSetSize ) {
				System.out.println( "Key " + key.toString() + ": " + this.myFollowsMap.get( key ) );
			}
		}
		
		System.out.println( "Number of keys in map: " + this.myFollowsMap.size() );
		System.out.println( "Largest Follows Set(s) has key size " + largestSetSize );
		
		
	}

}
