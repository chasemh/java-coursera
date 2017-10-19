package chasemh.java.coursera;

import edu.duke.*;

/**
 * Exercise solutions to Part 2: Caesar Cipher and Breaking the Caesar Cipher Part 2: Caesar Cipher Two Keys Decrypt
 * https://www.coursera.org/learn/java-programming-arrays-lists-data/supplement/DvNzQ/programming-exercise-implementing-the-caesar-cipher
 * https://www.coursera.org/learn/java-programming-arrays-lists-data/supplement/727CD/programming-exercise-breaking-the-caesar-cipher
 *
 * @author Chase Hennion
 * @version 2017-10-18
 */
public class CaesarCipherPractice {
	
	public static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	private int[] countLetterOccurrences( String input ) {
		// Count the number of times each letter appears in the input string
		
		// Create an array of counts with the same length as the Alphabet
		// For each letter in the input string
			// Uppercase the letter 
			// Find the index of the letter in the alphabet
			// If the index is not -1 ( the letter was found in the alphabet )
				// Increment counts[ index ]
		
		int[] counts = new int[ ALPHABET.length() ];
		
		for( int i = 0; i < input.length(); ++i ) {
			char ch = Character.toUpperCase( input.charAt( i ) );
			int indexInAlphabet = ALPHABET.indexOf( ch );
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
	
	public String encrypt( String input, int key ) {
		// Encrypt the string given the alphabet shift amount, key
		
		// Create the shifted alphabet based upon the key
		// Create a StringBuilder, encrypted, to hold the encrypted string
		// For each index in encrypted
			// Get the current character at the index, currentChar
			// If the current character is capitalized
				// Find the index of currentChar in the regular alphabet, regularIndex
			// Otherwise
				// Find the index of Character.toUppercase( currentChar ) in the regular alphabet, regularIndex
			// If regularIndex != -1 (that is, the character is not in the alphabet)
				// If the currentChar is capitalized
					// Replace the unencrypted character at regularIndex with the corresponding character in the shifted alphabet
				// Otherwise
					// Replace the unencrypted character at regularIndex with the lower cased corresponding character in the shifted alphabet
			// Otherwise keep the character unencrypted
		
		
		String shiftedAlphabet = ALPHABET.substring( key );
		shiftedAlphabet = shiftedAlphabet + ALPHABET.substring( 0, key );
		
		StringBuilder encrypted = new StringBuilder( input );
		
		for( int i = 0; i < encrypted.length(); ++i ) {
			// Get the current character in the input and determine it's case
			char currentChar = encrypted.charAt( i );
			boolean isUpperCase = Character.isUpperCase( currentChar );
			
			// Find the index of the character in the regular alphabet
			int regularIndex = -1;
			if( isUpperCase ) {
				regularIndex = ALPHABET.indexOf( currentChar );
			}
			else {
				regularIndex = ALPHABET.indexOf( Character.toUpperCase( currentChar ) );
			}
			
			// If the character is found in the alphabet
			if( regularIndex != -1 ) {
				
				// Replace the unencrypted character at regularIndex with the corresponding character in the shifted alphabet
				// Make sure to check the case of the character first to preserve capitalization
				if( isUpperCase ) {
					encrypted.setCharAt( i , shiftedAlphabet.charAt( regularIndex ) );
				}
				else {
					encrypted.setCharAt( i, Character.toLowerCase( shiftedAlphabet.charAt( regularIndex ) ) );
				}
			}
		}
		
		return encrypted.toString();
	}
	
	public String encryptTwoKeys( String input, int key1, int key2 ) {
		// key1 used to encrypt every other character starting at the first character ( index = 0 )
		// key2 used to encrypt every other character starting at the second character ( index = 1 )
		// key1 used for even indices
		// key2 used for odd indices
		
		String key1Alphabet = ALPHABET.substring( key1 );
		key1Alphabet = key1Alphabet + ALPHABET.substring( 0, key1 );
		
		String key2Alphabet = ALPHABET.substring( key2 );
		key2Alphabet = key2Alphabet + ALPHABET.substring( 0, key2 );
		
		StringBuilder encrypted = new StringBuilder( input );
		
		for( int i = 0; i < encrypted.length(); ++i ) {
			// Get the current character in the input and determine it's case
			char currentChar = encrypted.charAt( i );
			boolean isUpperCase = Character.isUpperCase( currentChar );
			boolean usingKey1 = false;
			
			// Decide upon key to use
			if( i % 2 == 0 ) {
				// At an even index so we need to use key1
				// Flip the boolean
				usingKey1 = true;
			}
			
			// Find the index of the character in the regular alphabet
			int regularIndex = -1;
			if( isUpperCase ) {
				regularIndex = ALPHABET.indexOf( currentChar );
			}
			else {
				regularIndex = ALPHABET.indexOf( Character.toUpperCase( currentChar ) );
			}
			
			// If the character is found in the alphabet
			if( regularIndex != -1 ) {
				
				if( isUpperCase ) {
					
					if( usingKey1 ) {
						encrypted.setCharAt( i , key1Alphabet.charAt( regularIndex ) );
					}
					else {
						encrypted.setCharAt( i , key2Alphabet.charAt( regularIndex ) );
					}
					
				}
				else {
					
					if( usingKey1 ) {
						encrypted.setCharAt( i, Character.toLowerCase( key1Alphabet.charAt( regularIndex ) ) );
					}
					else {
						encrypted.setCharAt( i, Character.toLowerCase( key2Alphabet.charAt( regularIndex ) ) );
					}
				}
			}
		}
		
		return encrypted.toString();
		
	}
	
	public int guessDecryptionKey( String input ) {
		
		// Use statistical analysis to decrypt the string
		// Count the number of occurrences of each character
		// Assume the highest occurring character will be e when unencrypted
		// Determine the shift key based upon the shift from the highest occurring character to e in the regular alphabet
		// Return the guessed decryption key
				
		int[] letterOccurrences = countLetterOccurrences( input );
		int indexOfMax = getIndexOfMax( letterOccurrences );
		int eIndex = ALPHABET.indexOf( 'E' );
		int decryptionKey = indexOfMax - eIndex;
		if( indexOfMax < eIndex ) {
			decryptionKey = ALPHABET.length() - ( eIndex - indexOfMax );
		}
				
		return decryptionKey;
		
	}
	
	public String decrypt( String input, int key ) {
		return encrypt( input, ALPHABET.length() - key );
	}
	
	public String decryptTwoKeys( String input, int key1, int key2 ) {
		return encryptTwoKeys( input, ALPHABET.length() - key1, ALPHABET.length() - key2 );
	}
	
	public String decrypt( String input ) {
		return decrypt( input, guessDecryptionKey( input ) );
	}
	
	public String decryptTwoKeys( String input ) {
		// Split the input into two strings
		// The first string contains the even index characters
		// The second string contains the odd index characters
		// Guess the decryption keys for each string 
		// Return decryptTwoKeys( input, key1, key2 )
		
		String[] splitStrings = splitEvenOdd( input );
		
		int key1 = guessDecryptionKey( splitStrings[ 0 ] );
		int key2 = guessDecryptionKey( splitStrings[ 1 ] );
		
		System.out.println( "Guessed Key 1:\t" + key1 );
		System.out.println( "Guessed Key 2:\t" + key2 );
		
		return decryptTwoKeys( input, key1, key2 );
	}
	
	/*
	 * Test Methods
	 */
	
	public void testEncrypt() {
		String out1 = encrypt( "FIRST LEGION ATTACK EAST FLANK!", 23);
		String desired1 = "CFOPQ IBDFLK XQQXZH BXPQ CIXKH!";
		
		String out2 = encrypt( "First Legion", 17 );
		String desired2 = "Wzijk Cvxzfe";
		
		if( !out1.equals( desired1 ) ) {
			System.out.println( "Error: " + out1 + " != " + desired1 );
		}
		else if( !out2.equals( desired2 ) ) {
			System.out.println( "Error: " + out2 + " != " + desired2 );
		}
		else {
			System.out.println( "Test cases pass for the encrypt method!" );
			
			System.out.println( "Encrypted: " + out1 );
			System.out.println( "Decrpyted: " + decrypt( out1, 23 ) );
		}
	}
	
	public void testEncryptTwoKeys() {
		String out1 = encryptTwoKeys( "First Legion", 23, 17 );
		String desired1 = "Czojq Ivdzle";
		
		if( !out1.equals( desired1 ) ) {
			System.out.println( "Error: " + out1 + " != " + desired1 );
		}
		else {
			System.out.println( "Test cases pass for the encryptTwoKeys method!" );
		}
		
		System.out.println( "Encrypted: " + out1 );
		System.out.println( "Decrpyted: " + decryptTwoKeys( out1, 23, 17 ) );
	}
	
	public void testNoKeyTwoKeyDecrypt() {
		FileResource fr = new FileResource();
		String frString = fr.asString();
		System.out.println( "Decrypted Message: " + decryptTwoKeys( frString ) );
	}
	
	public void quizTester() {
		String input = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD!";
		
		System.out.println( "5. Encrypt w/ key=15: " + input );
		System.out.println( encrypt( input, 15) );
		
		System.out.println();
		
		System.out.println( "6. Encrypt w/ key1=8 and key2=21: " + input );
		System.out.println( encryptTwoKeys( input, 8, 21 ) );
	}
	
	public void quiz2Tester() {
		// Question 8
		String in1 = "Top ncmy qkff vi vguv vbg ycpx";
		int key1 = 2;
		int key2 = 20;
		System.out.println( "8. Decrypt " + in1 + " with key1 = " + key1 + " and key2 = " + key2 +": " + decryptTwoKeys( in1, key1, key2 ) );
		
		// Question 9
		String in2 = "Akag tjw Xibhr awoa aoee xakex znxag xwko";
		String out2 = decryptTwoKeys( in2 );
		System.out.println( "9. Decrypting with two keys " + in2 + ": " + out2 );
		
		// Question 10 + 11
		System.out.println( "Decrypting with two keys data in mysteryTwoKeysPractice.txt:" );
		FileResource fr1 = new FileResource( "data/mysteryTwoKeysPractice.txt" );
		String in3 = fr1.asString();
		String out3 = decryptTwoKeys( in3 );
		System.out.println( "Decrypted mysteryTwoKeysPractice.txt: " + out3 );
	}
	
	public void testDecrypt() {
		FileResource fr = new FileResource();
		String frStr = fr.asString();
		System.out.println( "Decrypting without a key: " + frStr );
		System.out.println( "Decrypted String: " + decrypt( frStr ) );
	}

	
	public static void main(String[] args) {
		CaesarCipherPractice cc = new CaesarCipherPractice();
		//cc.testEncrypt();
		//cc.testEncryptTwoKeys();
		//cc.quizTester();
		cc.testNoKeyTwoKeyDecrypt();
		//cc.quiz2Tester();
		//cc.testDecrypt();
	}

}
