package chasemh.java.coursera;

/**
 * Exercise solutions to Part 2: Caesar Cipher
 * https://www.coursera.org/learn/java-programming-arrays-lists-data/supplement/DvNzQ/programming-exercise-implementing-the-caesar-cipher
 *
 * @author Chase Hennion
 * @version 2017-10-18
 */
public class CaesarCipher {
	
	public static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
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
	
	public String decrypt( String input, int key ) {
		return encrypt( input, ALPHABET.length() - key );
	}
	
	public String decryptTwoKeys( String input, int key1, int key2 ) {
		return encryptTwoKeys( input, ALPHABET.length() - key1, ALPHABET.length() - key2 );
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
	
	public void quizTester() {
		String input = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD!";
		
		System.out.println( "5. Encrypt w/ key=15: " + input );
		System.out.println( encrypt( input, 15) );
		
		System.out.println();
		
		System.out.println( "6. Encrypt w/ key1=8 and key2=21: " + input );
		System.out.println( encryptTwoKeys( input, 8, 21 ) );
	}

	
	public static void main(String[] args) {
		CaesarCipher cc = new CaesarCipher();
		cc.testEncrypt();
		cc.testEncryptTwoKeys();
		cc.quizTester();
	}

}
