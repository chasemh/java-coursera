package chasemh.java.coursera;

import edu.duke.*;

/**
 * Exercise solutions to Object Oriented Caesar Cipher Part 1
 * https://www.coursera.org/learn/java-programming-arrays-lists-data/supplement/DBSYj/programming-exercise-object-oriented-caesar-cipher 
 *
 * @author Chase Hennion
 * @version 2017-10-19
 */
public class TestCaesarCipher {
	
	// Private Methods
	
	private int[] countLetterOccurrences( String input ) {
		// Count the number of times each letter appears in the input string
		
		// Create an array of counts with the same length as the Alphabet
		// For each letter in the input string
			// Uppercase the letter 
			// Find the index of the letter in the alphabet
			// If the index is not -1 ( the letter was found in the alphabet )
				// Increment counts[ index ]

		int[] counts = new int[ CaesarCipher.ALPHABET.length() ];
		
		for( int i = 0; i < input.length(); ++i ) {
			char ch = Character.toUpperCase( input.charAt( i ) );
			int indexInAlphabet = CaesarCipher.ALPHABET.indexOf( ch );
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
	
	private int guessDecryptionKey( String input ) {
		
		// Use statistical analysis to decrypt the string
		// Count the number of occurrences of each character
		// Assume the highest occurring character will be e when unencrypted
		// Determine the shift key based upon the shift from the highest occurring character to e in the regular alphabet
		// Return the guessed decryption key
				
		int[] letterOccurrences = countLetterOccurrences( input );
		int indexOfMax = getIndexOfMax( letterOccurrences );
		int eIndex = CaesarCipher.ALPHABET.indexOf( 'E' );
		int decryptionKey = indexOfMax - eIndex;
		if( indexOfMax < eIndex ) {
			decryptionKey = CaesarCipher.ALPHABET.length() - ( eIndex - indexOfMax );
		}
				
		return decryptionKey;
		
	}
	
	// Public Methods
	public void simpleTests() {
		FileResource fr = new FileResource();
		String frStr = fr.asString();
		CaesarCipher cc = new CaesarCipher( 18 );
		String encrypted = cc.encrypt( frStr );
		String decrypted = cc.decrypt( encrypted );
		System.out.println( "Encrypting w/ key 18: " + frStr );
		System.out.println( "Encrypted String: " + encrypted );
		System.out.println( "Decrypted String: " + decrypted );
		
		breakCaesarCipher();
		
	}
	
	public void breakCaesarCipher() {
		// Determine the key used to encrypt a message
		FileResource fr = new FileResource();
		String frStr = fr.asString();
		int key = guessDecryptionKey( frStr );
		CaesarCipher cc = new CaesarCipher( key );
		String decrypted = cc.decrypt( frStr );
		
		System.out.println( "Guessing decryption key for " + frStr);
		System.out.println( "Guessing key is " + key );
		System.out.println( "Decrypted message with key " + key + ": " + decrypted );

	}
	
	public void breakCaesarCipher( String input ) {
		// Determine the key used to encrypt a message
		int key = guessDecryptionKey( input );
		CaesarCipher cc = new CaesarCipher( key );
		String decrypted = cc.decrypt( input );
		
		System.out.println( "Guessing decryption key for " + input );
		System.out.println( "Guessing key is " + key );
		System.out.println( "Decrypted message with key " + key + ": " + decrypted );

	}

	public static void main(String[] args) {
		TestCaesarCipher tcc = new TestCaesarCipher();
		tcc.simpleTests();
	}

}
