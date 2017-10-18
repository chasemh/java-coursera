package chasemh.java.coursera;

import edu.duke.*;
import java.io.*;

/**
 * Exercise solutions to Part 1: Word Play
 * https://www.coursera.org/learn/java-programming-arrays-lists-data/supplement/DvNzQ/programming-exercise-implementing-the-caesar-cipher
 *
 * @author Chase Hennion
 * @version 2017-10-18
 */
public class WordPlay {
	
	public boolean isVowel( char ch ) {
		// Return true if ch is a, e, i, o, or u
		// Sanitize the case of the char to lowercase before testing
		
		char chReal = Character.toLowerCase( ch );
		if( chReal == 'a' || chReal == 'e' || chReal == 'i' || chReal == 'o' || chReal == 'u' ) {
			return true;
		}
		return false;
	}
	
	public String replaceVowels( String phrase, char ch ) {
		
		// Replace all vowel characters in phrase with the given character
		
		char[] chars = phrase.toCharArray();
		for( int i = 0; i < chars.length; ++i ) {
			if( isVowel( chars[ i ] ) ) {
				chars[ i ] = ch;
			}
		}
		
		return new String( chars );
	}
	
	public String emphasize( String phrase, char ch ) {
		// Return the string that is the string phrase but with ch replaced by * if at an odd number location or + if at an even number location
		// Note: In this method the "number location" would be first ( index = 0 ), second ( index = 1 ) and so on
		// So really looking at the parity of index + 1 and not the index
		
		// Sanitize input char
		char chLower = Character.toLowerCase( ch );
		char chUpper = Character.toUpperCase( ch );
		
		char[] chars = phrase.toCharArray();
		for( int i = 0; i < chars.length; ++i ) {
			if( chars[ i ] == chLower || chars[ i ] == chUpper ) {
				// Found the character. Now see if in an even or odd index.
				if( ( i + 1 ) % 2 == 0 ) {
					// Even index so replace with '+'
					chars[ i ] = '+';
				}
				else {
					// Odd index so replace with '*'
					chars[ i ] = '*';
				}
			}
		}
		
		return new String( chars );
	}
	
	/*
	 * Testing Methods
	 */
	
	public void testIsVowel() {
		System.out.println( "Is 'a' a vowel? " + isVowel( 'a' ) );
		System.out.println( "Is 'E' a vowel? " + isVowel( 'E' ) );
		System.out.println( "Is 'X' a vowel? " + isVowel( 'X' ) );
		System.out.println( "Is '#' a vowel? " + isVowel( '#' ) );
	}
	
	public void testReplaceVowels() {
		System.out.println( "Replace Vowels in Bananas with #: " + replaceVowels( "Bananas", '#') );
		System.out.println( "Replace Vowls in Hello World with *: " + replaceVowels( "Hello World", '*' ) );
	}
	
	public void testEmphasize() {
		String out1 = emphasize( "Hello World", 'e' );
		String out2 = emphasize( "dna ctgaaactga", 'a' );
		String out3 = emphasize( "Mary Bella Abracadabra", 'a' );
		
		String desired1 = "H+llo World";
		String desired2 = "dn* ctg+*+ctg+";
		String desired3 = "M+ry Bell+ +br*c*d*br+";
		
		if( !out1.equals( desired1 ) ) {
			System.out.println( "Failure: " + out1 + " != " + desired1 );
		}
		else if( !out2.equals( desired2 ) ) {
			System.out.println( "Failure: " + out2 + " != " + desired2 );
		}
		else if( !out3.equals( desired3 ) ) {
			System.out.println( "Failure: " + out3 + " != " + desired3 );
		}
		else {
			System.out.println( "Test cases pass for emphasize method!" );
		}
		
		//System.out.println( "Emphasizing e in Hello World. Should be H*llo World " + emphasize( "Hello World", 'e' ) );
		//System.out.println( "Emphasizing a in dna ctgaaactga. Should be dn* ctg+*+ctg+ " + emphasize( "dna ctgaaactga", 'a' ) );
		//System.out.println( "Emphasizing a in Mary Bella Abracadabra. Should be M+ry Bell+ +br*c*d*br+ " + emphasize( "Mary Bella Abracadabra", 'a' ) );
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WordPlay wp = new WordPlay();
		wp.testIsVowel();
		wp.testReplaceVowels();
		wp.testEmphasize();
	}

}
