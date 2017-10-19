package chasemh.java.coursera;

import edu.duke.*;

/**
 * Exercise solutions to Breaking the Caesar Cipher Part 1: Word Lengths
 * https://www.coursera.org/learn/java-programming-arrays-lists-data/supplement/727CD/programming-exercise-breaking-the-caesar-cipher
 *
 * @author Chase Hennion
 * @version 2017-10-19
 */
public class WordLengths {
	
	// Group all words of length 30+ in count
	// So need an array of length 31;
	// Stuff in the 31st place will be the counts of words with 30 or more characters
	
	public void countWordLengths( FileResource resource, int[] counts ) {
		// Read all of the words in the file resource 
		// Get the length of each word
		// Increment counts[ wordLength ]
		
		// For each word in resource.words() 
			// Get the length of the word 
				// Check the last two characters
					// If length >= 30
						// Increment counts[ 30 ];
					// Otherwise
						// Increment counts[ wordLength ]
		
		for( String word : resource.words() ) {
			int wordLength = word.length();
			
			// Check for bad first and last characters to obtain the true length
			int lengthModifier = 0;
			if(  !Character.isLetter( word.charAt( 0 ) ) ) {
				lengthModifier++;
			}
			if ( !Character.isLetter( word.charAt( wordLength - 1 ) ) ) {
				lengthModifier++;
			}
			
			int trueLength = wordLength - lengthModifier;
				
			if( trueLength >= 30 ) {
				counts[ 30 ]++;
			}
			else if( trueLength > 0 ) {
				counts[ trueLength ]++;
			}
				
		}
		
		printCounts( counts );
		
	}
	
	public int indexOfMax( int[] values ) {
		// Get the index position of the largest element in values
		
		int maxIndex = 0;
		int currentMax = -1;
		for( int i = 0; i < values.length; ++i ) {
			if( values[ i ] > currentMax ) {
				currentMax = values[ i ];
				maxIndex = i;
			}
		}
		
		return maxIndex;
	}
	
	/*
	 * Test Methods
	 */
	
	public void testCountWordLengths() {
		FileResource fr = new FileResource();
		int[] counts = new int[ 31 ];
		countWordLengths( fr, counts );
		int indexOfMax = indexOfMax( counts );
		System.out.println( "Most Common Word Length is " + indexOfMax + " with " + counts[ indexOfMax ] + " entries." );
	}
	
	public void printCounts( int[] counts ) {
		for( int i = 0; i < counts.length; ++i ){
			if( counts[ i ] != 0 ) {
				System.out.println( "Number of words with length " + i + ":\t" + counts[i] );
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WordLengths wl = new WordLengths();
		wl.testCountWordLengths();

	}

}
