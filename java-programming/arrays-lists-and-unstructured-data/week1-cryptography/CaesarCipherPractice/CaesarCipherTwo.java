package chasemh.java.coursera;

/**
 * Exercise solutions to Object Oriented Caesar Cipher Part 2
 * https://www.coursera.org/learn/java-programming-arrays-lists-data/supplement/DBSYj/programming-exercise-object-oriented-caesar-cipher 
 *
 * @author Chase Hennion
 * @version 2017-10-19
 */
public class CaesarCipherTwo {
	
	public static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private String shiftedAlphabet1;
	private String shiftedAlphabet2;
	private int key1;
	private int key2;
	
	// Constructor
	public CaesarCipherTwo( int key1, int key2 ) {
		this.key1 = key1;
		this.key2 = key2;
		
		String shift1 = ALPHABET.substring( key1 );
		this.shiftedAlphabet1 = shift1 + ALPHABET.substring( 0, key1 );
		
		String shift2 = ALPHABET.substring( key2 );
		this.shiftedAlphabet2 = shift2 + ALPHABET.substring( 0, key2 );
	}
	
	// Public Methods
	
	public String encrypt( String input ) {
		// key1 used to encrypt every other character starting at the first character ( index = 0 )
		// key2 used to encrypt every other character starting at the second character ( index = 1 )
		// key1 used for even indices
		// key2 used for odd indices
		
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
						encrypted.setCharAt( i , this.shiftedAlphabet1.charAt( regularIndex ) );
					}
					else {
						encrypted.setCharAt( i , this.shiftedAlphabet2.charAt( regularIndex ) );
					}
					
				}
				else {
					
					if( usingKey1 ) {
						encrypted.setCharAt( i, Character.toLowerCase( this.shiftedAlphabet1.charAt( regularIndex ) ) );
					}
					else {
						encrypted.setCharAt( i, Character.toLowerCase( this.shiftedAlphabet2.charAt( regularIndex ) ) );
					}
				}
			}
		}
		
		return encrypted.toString();
		
	}
	
	public String decrypt( String input ) {
		CaesarCipherTwo cct = new CaesarCipherTwo( ALPHABET.length() - this.key1, ALPHABET.length() - this.key2 );
		return cct.encrypt( input );
	}

}
