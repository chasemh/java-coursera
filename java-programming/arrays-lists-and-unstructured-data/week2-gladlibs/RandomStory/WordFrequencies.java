package chasemh.java.coursera;

import edu.duke.*;
import java.util.*;

/**
 * Exercise solutions to Assignment 1: Most Frequent Word
 * https://www.coursera.org/learn/java-programming-arrays-lists-data/supplement/AbpYj/programming-exercise-telling-a-random-story 
 *
 * @author Chase Hennion
 * @version 2017-10-20
 */
public class WordFrequencies {
	
	private ArrayList<String> myWords;
	private ArrayList<Integer> myFreqs;
	
	// Constructors
	
	public WordFrequencies() {
		this.myWords = new ArrayList<String>();
		this.myFreqs = new ArrayList<Integer>();
	}
	
	// Private Methods
	
	private int findIndexOfMax() {
		// Iterate through myFreqs to find the largest value and the index of that value
		// Return the index
		
		int currentMax = -1;
		int maxIndex = -1;
		for( int i = 0; i < this.myFreqs.size(); ++i ) {
			int currentValue = this.myFreqs.get( i );
			if( currentValue > currentMax ) {
				currentMax = currentValue;
				maxIndex = i;
			}
		}
		
		return maxIndex;
	}
	
	// Public Methods
	
	public void findUnique() {
		// Clear both arraylists
		// Select a file and iterate over each word in it 
		// Place the unique words in myWords and increment the word count in myFreqs in the same index
		
		this.myWords.clear();
		this.myFreqs.clear();
		
		FileResource fr = new FileResource();
		for( String word : fr.words() ) {
			// Sanitize the word
			String trueWord = word.toLowerCase();
			if( this.myWords.contains( trueWord ) ) {
				// String already in the array
				// Find it's index and then update the occurrence count in the same index in myFreqs
				int wordIndex = this.myWords.indexOf( trueWord );
				int currentValue = this.myFreqs.get( wordIndex );
				this.myFreqs.set( wordIndex, currentValue + 1 );
			}
			else {
				// Encountered a new word
				// Add a new element to the end of both arrays
				this.myWords.add( trueWord );
				this.myFreqs.add( 1 );
			}
		}
	}
	
	
	// Test Methods
	
	public void tester() {
		findUnique();
		System.out.println( "Word Occurrences: \n===================" );
		for( int i = 0; i < this.myWords.size(); ++i ) {
			System.out.println( this.myWords.get( i ) + "\t" + this.myFreqs.get( i ) );
		}
		System.out.println( "Number of Unique Words: " + this.myWords.size() );
		int indexOfMax = findIndexOfMax();
		System.out.println( "The word that occurs most often and its count is: " + this.myWords.get( indexOfMax ) + "  " + this.myFreqs.get( indexOfMax ) );
	}

	public static void main(String[] args) {
		WordFrequencies wf = new WordFrequencies();
		wf.tester();
	}

}
