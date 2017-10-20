package chasemh.java.coursera;

import edu.duke.*;
import java.util.*;

/**
 * Exercise solutions to Assignment 2: Character Names
 * https://www.coursera.org/learn/java-programming-arrays-lists-data/supplement/AbpYj/programming-exercise-telling-a-random-story 
 *
 * @author Chase Hennion
 * @version 2017-10-20
 */
public class CharactersInPlay {
	
	private static final int MAX_NAME_LENGTH = 15;
	private static final int MAX_NUM_SPACES = 2;
	private ArrayList<String> names;
	private ArrayList<Integer> nameCounts;
	
	// Constructors
	
	public CharactersInPlay() {
		this.names = new ArrayList<String>();
		this.nameCounts = new ArrayList<Integer>();
	}
	
	// Private Methods
	private void update( String person ) {
		// Update the two arraylists with the characters name
		// Add it if it is not already in the lists
		// Otherwise, increment the count for the name
		
		// Sanitize the string
		String truePerson = person.toUpperCase();
		
		if( this.names.contains( truePerson ) ) {
			// Already in the names list
			// Get the index of the name in the list
			// Update the name count in the same index in nameCounts
			int nameIndex = this.names.indexOf( truePerson );
			int currentValue = this.nameCounts.get( nameIndex );
			this.nameCounts.set( nameIndex , currentValue + 1) ;
		}
		else {
			// Name isn't currently in the list
			// Add it the end of names
			// Add 1 to the end of nameCounts, to signify that the name has been encountered one time so far
			this.names.add( truePerson );
			this.nameCounts.add( 1 );
		}
		
	}
	
	private int countSpaces( String s ) {
		// Count the number of spaces in a string
		char[] chars = s.toCharArray();
		int spaceCount = 0;
		for( int i = 0; i < chars.length; ++i ) {
			if( chars[ i ] == ' ' ) {
				spaceCount++;
			}
		}
		return spaceCount;
	}
	
	private boolean isCharacterName( String person ) {
		// Returns true if person is indeed a character name
		// Returns false otherwise
		
		// Person is a character name if (Best Guess):
			// It doesn't contain more than MAX_NUM_SPACES
			// It is shorter than MAX_NAME_LENGTH characters in length
		
		if( person.length() < MAX_NAME_LENGTH ) {
			int spaceCount = countSpaces( person );
			if( spaceCount <= MAX_NUM_SPACES ) {
				return true;
			}
		}
		return false;
	}
	
	private void characterWithNumParts( int num1, int num2 ) {
		// Assume num1 is less than num2
		// Print out all of the characters with the number of speaking parts greater than or equal to num1 
		// and less than or equal to  num2
		
		if( num1 >= num2 ) {
			// Bad values
			System.out.println( "Bad call: num1 must be less than num2." );
			return;
		}
		
		// Iterate through the names
			// Get the number of times the name speaks (get nameCounts at the same index as in names)
			// If count >= num1 and count <= num2
				// Print it out
		
		System.out.println( "Characters with Number of Speaking Parts between " + num1 + " and " + num2 + ":\n=========================================================" );
		for( int i = 0; i < this.names.size(); ++ i ) {
			int count = this.nameCounts.get( i );
			if( count >= num1 && count <= num2 ) {
				System.out.println( this.names.get( i ) + "  " + count );
			}
		}
		
	}
	
	private int findIndexOfMax() {
		int currentMax = -1;
		int maxIndex = -1;
		for( int i = 0; i < this.names.size(); ++i ) {
			int currentValue = this.nameCounts.get( i );
			if( currentValue > currentMax ) {
				currentMax = currentValue;
				maxIndex = i;
			}
		}
		
		return maxIndex;
	}
	
	// Public Methods
	
	public void findAllCharacters() {
		// Open a file and count all of the characters names in it.
		// Read file line by line
		// Guess that the character name is the substring up to the first occurrence of a period
		
		this.names.clear();
		this.nameCounts.clear();
		
		FileResource fr = new FileResource();
		for( String line : fr.lines() ) {
			int periodIndex = line.indexOf( '.' );
			if( periodIndex != -1 ) {
				// A period was found on the line. It might contain a character name.
				// Trim off the leading and ending whitespaces if they exist
				//String possibleName = line.substring( 0, periodIndex );
				
				String possibleName = line.substring( 0, periodIndex ).trim();
				//if( isCharacterName( possibleName ) ) {
				//	update( possibleName );
				//}
				update( possibleName );
			}
		}
	}
	
	public void tester() {
		findAllCharacters();
		System.out.println( "Character Names and Occurrences:\n====================" );
		for( int i = 0; i < this.names.size(); ++i ) {
			if( this.nameCounts.get( i ) > 2 ) {
				System.out.println( this.names.get( i ) + "  " + this.nameCounts.get( i ) );
			}
		}
		int maxIndex = findIndexOfMax();
		System.out.println( "Character with most speaking parts and number of speaking parts: " + this.names.get( maxIndex ) + "  " + this.nameCounts.get( maxIndex ));
		characterWithNumParts( 10, 15 );
	}
	

	public static void main(String[] args) {
		CharactersInPlay cip = new CharactersInPlay();
		cip.tester();
	}

}
