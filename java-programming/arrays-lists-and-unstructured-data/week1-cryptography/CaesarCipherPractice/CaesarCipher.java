package chasemh.java.coursera;

/**
 * Exercise solutions to Object Oriented Caesar Cipher Part 1
 * https://www.coursera.org/learn/java-programming-arrays-lists-data/supplement/DBSYj/programming-exercise-object-oriented-caesar-cipher 
 *
 * @author Chase Hennion
 * @version 2017-10-19
 */
public class CaesarCipher {
	
	public static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private String shiftedAlphabet;
	private int key;
	
	// Constructor
	
	public CaesarCipher( int key ) {
		String shift = ALPHABET.substring( key );
		this.shiftedAlphabet = shift + ALPHABET.substring( 0, key );
		this.key = key;
		
	}
	
	// Public Methods
	
	public String encrypt( String input ) {
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
					encrypted.setCharAt( i , this.shiftedAlphabet.charAt( regularIndex ) );
				}
				else {
					encrypted.setCharAt( i, Character.toLowerCase( this.shiftedAlphabet.charAt( regularIndex ) ) );
				}
			}
		}
		
		return encrypted.toString();
	}
	
	public String decrypt( String input ) {
		CaesarCipher cc = new CaesarCipher( ALPHABET.length() - this.key );
		return cc.encrypt( input );
	}
	

}
