package chasemh.java.coursera;

import edu.duke.*;

/**
 * Exercise solutions to Object Oriented Caesar Cipher Part 2
 * https://www.coursera.org/learn/java-programming-arrays-lists-data/supplement/DBSYj/programming-exercise-object-oriented-caesar-cipher 
 *
 * @author Chase Hennion
 * @version 2017-10-19
 */
public class TestCaesarCipherTwo {
	
	// Private Methods
	
	private int[] countLetterOccurrences( String input ) {
		// Count the number of times each letter appears in the input string
		
		// Create an array of counts with the same length as the Alphabet
		// For each letter in the input string
			// Uppercase the letter 
			// Find the index of the letter in the alphabet
			// If the index is not -1 ( the letter was found in the alphabet )
				// Increment counts[ index ]

		int[] counts = new int[ CaesarCipherTwo.ALPHABET.length() ];
		
		for( int i = 0; i < input.length(); ++i ) {
			char ch = Character.toUpperCase( input.charAt( i ) );
			int indexInAlphabet = CaesarCipherTwo.ALPHABET.indexOf( ch );
			if( indexInAlphabet != -1 ) {
				counts[ indexInAlphabet ]++;
			}
		}
		
		return counts;
	}
	
	private int getIndexOfMax( int[] counts ) {
		//Get the index where counts[ index ] is the max value
		
		int currentMax = -1;
		int maxIndex = 0;
		for( int i = 0; i < counts.length; ++i ) {
			if( counts[i] > currentMax ) {
				currentMax = counts[i];
				maxIndex = i;
			}
		}
		
		return maxIndex;
	}
	
	private String[] splitEvenOdd( String input ) {
		// Split the string into two strings 
		// First string contains all the chars at even indices
		// Second string contains all the chars at odd indices
		
		StringBuilder s1 = new StringBuilder();
		StringBuilder s2 = new StringBuilder();
		
		for( int i = 0; i < input.length(); ++i ) {
			char currentChar = input.charAt( i );
			if( i % 2 == 0 ) {
				s1.append( currentChar );
			}
			else {
				s2.append( currentChar );
			}
		}
		
		String[] ret = { s1.toString(), s2.toString() };
		return ret;
	}
	
	private int guessDecryptionKey( String input ) {
		
		// Use statistical analysis to decrypt the string
		// Count the number of occurrences of each character
		// Assume the highest occurring character will be e when unencrypted
		// Determine the shift key based upon the shift from the highest occurring character to e in the regular alphabet
		// Return the guessed decryption key
				
		int[] letterOccurrences = countLetterOccurrences( input );
		int indexOfMax = getIndexOfMax( letterOccurrences );
		int eIndex = CaesarCipherTwo.ALPHABET.indexOf( 'E' );
		int decryptionKey = indexOfMax - eIndex;
		if( indexOfMax < eIndex ) {
			decryptionKey = CaesarCipherTwo.ALPHABET.length() - ( eIndex - indexOfMax );
		}
				
		return decryptionKey;
		
	}
	
	// Public Methods
	
	public void simpleTests() {
		FileResource fr = new FileResource();
		String frStr = fr.asString();
		CaesarCipherTwo cct = new CaesarCipherTwo( 17, 3 );
		String encrypted = cct.encrypt( frStr );
		String decrypted = cct.decrypt( encrypted );
		System.out.println( "Encrypting w/ keys 17 and 3: " + frStr );
		System.out.println( "Encrypted String: " + encrypted );
		System.out.println( "Decrypted String: " + decrypted );
		breakCaesarCipher();
	}
	
	public void breakCaesarCipher() {
		FileResource fr = new FileResource();
		String frStr = fr.asString();
		String[] splitStrings = splitEvenOdd( frStr );
		
		int key1 = guessDecryptionKey( splitStrings[ 0 ] );
		int key2 = guessDecryptionKey( splitStrings[ 1 ] );
		
		CaesarCipherTwo cct = new CaesarCipherTwo( key1, key2 );
		
		System.out.println( "Decrypting String: " + frStr );
		System.out.println( "Guessed Key 1:\t" + key1 );
		System.out.println( "Guessed Key 2:\t" + key2 );
		System.out.println( "Decrypted String: " + cct.decrypt( frStr ) );
	}
	
	public void breakCaesarCipher( String input ) {
		
		String[] splitStrings = splitEvenOdd( input );
		
		int key1 = guessDecryptionKey( splitStrings[ 0 ] );
		int key2 = guessDecryptionKey( splitStrings[ 1 ] );
		
		CaesarCipherTwo cct = new CaesarCipherTwo( key1, key2 );
		
		System.out.println( "Decrypting String: " + input );
		System.out.println( "Guessed Key 1:\t" + key1 );
		System.out.println( "Guessed Key 2:\t" + key2 );
		System.out.println( "Decrypted String: " + cct.decrypt( input ) );
	}

	public static void main(String[] args) {
		TestCaesarCipherTwo tcct = new TestCaesarCipherTwo();
		tcct.simpleTests();
	}

}
