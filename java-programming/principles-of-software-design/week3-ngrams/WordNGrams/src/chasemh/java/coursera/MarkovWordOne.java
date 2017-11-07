package chasemh.java.coursera;

import java.util.*;

/**
 * Exercise solutions to Assignment: Word N-Grams
 * https://www.coursera.org/learn/java-programming-design-principles/supplement/wkafw/programming-exercise-word-n-grams 
 *
 * Modified By Chase Hennion
 * @version 2017-11-06
 */
public class MarkovWordOne implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    
    public MarkovWordOne() {
        myRandom = new Random();
    }
    
    private int indexOf( String[] words, String target, int start ) {
 
    		for( int i = start; i < words.length; ++i ) {
    			if( words[ i ].equals( target ) ) {
    				// Found the target string
    				// Return the index it was found in
    				return i;
    			}
    		}
    		return -1;
    }
    
    private ArrayList<String> getFollows(String key) {
	    ArrayList<String> follows = new ArrayList<String>();
	    
	    // Find each occurrence of key in myText
	    // Each time it is found
	       // Append the word following the occurrence of key to the follows list
	    // Return follows
	    
	    for( int i = this.indexOf( this.myText, key, 0 ); i != -1; i = this.indexOf( this.myText, key, i + 1 ) ) {
	    		if( i == this.myText.length - 1 ) {
	    			// At the end of the text array. Cannot get anymore following words.
	    			break;
	    		}
	    		follows.add( this.myText[ i + 1 ] );
	    }
	    
	    
	    return follows;
    }
    
    private boolean testIndexOf( String[] words, String target, int start, int expected ) {
    		System.out.println( "Looking for " + target + " starting at index " + start + ". Expecting " + target + " to be found at index " + expected + "." );
    		int ret = this.indexOf( words, target, start );
    		System.out.println( "Found " + target + " at index " + ret + ".");
    		if( ret == expected ) {
    			System.out.println( "Test passes." );
    			return true;
    		}
    		System.out.println( "Test fails." );
    		return false;
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
		myText = text.split("\\s+");
	}
	
	public String getRandomText(int numWords){
		StringBuilder sb = new StringBuilder();
		int index = myRandom.nextInt(myText.length-1);  // random word to start with
		String key = myText[index];
		sb.append(key);
		sb.append(" ");
		for(int k=0; k < numWords-1; k++){
		    ArrayList<String> follows = getFollows(key);
		    if (follows.size() == 0) {
		        break;
		    }
			index = myRandom.nextInt(follows.size());
			String next = follows.get(index);
			sb.append(next);
			sb.append(" ");
			
			// Testing
			//System.out.println( "Looking for key " + key );
			//System.out.println( "Key has follow set " + follows );
			
			key = next;
		}
		
		return sb.toString().trim();
	}
	
	public void testIndexOf() {
		String testStr = "this is just a test yes this is a simple test";
		//                0    1  2    3 4    5   6    7  8 9      10
		String[] words = testStr.split( "\\s+" );
		
		this.testIndexOf( words, "this", 0, 0 );
		this.testIndexOf( words, "this", 3, 6 );
		this.testIndexOf( words, "frog", 0, -1 );
		this.testIndexOf( words, "frog", 5, -1 );
		this.testIndexOf( words, "simple", 2, 9);
		this.testIndexOf( words, "test", 5, 10 );
		
	}

}
