package chasemh.java.coursera;

import java.util.ArrayList;
import java.util.Random;

/**
 * Exercise solutions to Assignment: WordGram Class
 * https://www.coursera.org/learn/java-programming-design-principles/supplement/cajch/programming-exercise-wordgram-class 
 *
 * Modified By Chase Hennion
 * @version 2017-11-06
 */
public class MarkovWord implements IMarkovModel {
	
	private String[] myText;
	private Random myRandom;
	private int myOrder;
	
	public MarkovWord( int order ) {
		this.myOrder = order;
		this.myRandom = new Random();
	}
	
	public void setRandom( int seed ) {
		this.myRandom = new Random( seed );
	}
	
	public void setTraining( String text ) {
		this.myText = text.split( "\\s+" );
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
		ArrayList<String> follows = new ArrayList<String>();
		
		for( int i = this.indexOf( this.myText, kGram, 0 ); i != -1; i = this.indexOf( this.myText, kGram, i + 1 ) ) {
			if( i == this.myText.length - this.myOrder ) {
				break;
			}
			
			follows.add( this.myText[ i + this.myOrder ] );
		}
		
		return follows;
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

}
