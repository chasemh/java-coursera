package chasemh.java.coursera;
import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
	
	public static final String[] AVAILABLE_LANGUAGES = { "Danish", "Dutch", "English", "French", "German", "Italian", "Portuguese", "Spanish" };
	
	public char mostCommonCharIn( HashSet<String> dictionary ) {
		
		HashMap<Character,Integer> letterCounts = new HashMap<Character,Integer>();
		
		// Count up all the letter occurrences in the dictionary
		for( String word : dictionary ) {
			char[] chars = word.toCharArray();
			for( char c : chars ) {
				if( letterCounts.containsKey( Character.toLowerCase( c ) ) ) {
					letterCounts.put( Character.toLowerCase( c ) , letterCounts.get( Character.toLowerCase( c ) ) + 1 );
				}
				else {
					letterCounts.put( Character.toLowerCase( c ), 1 );
				}
			}
		}
		
		// Find the highest value in the letter count map
		int currentMax = -1;
		char maxCharacter = ' ';
		for( char c : letterCounts.keySet() ) {
			int count = letterCounts.get( c );
			if( count > currentMax ) {
				currentMax = count;
				maxCharacter = c;
			}
		}
		
		return maxCharacter;
	}
	
    public String sliceString( String message, int whichSlice, int totalSlices ) {
    		// whichSlice: Where to start slicing, index of start
    		// totalSlices: How many slices to get, slice increment
    		StringBuilder slice = new StringBuilder();
    		for( int currentIndex = whichSlice; currentIndex < message.length() ; currentIndex += totalSlices ) {
    			char toAppend = message.charAt( currentIndex );
    			slice.append( toAppend );
    		}
    		
        return slice.toString();
    }

    public int[] tryKeyLength( String encrypted, int klength, char mostCommon ) {
        int[] key = new int[klength];
        
        CaesarCracker cc = new CaesarCracker( mostCommon );
        
        // For each slice
			// Get the key for the slice from the CeasarCracker
			// Put the key into the key array
		// Return the key array
        
        for( int i = 0; i < klength; ++i ) {
        		String currentSlice = this.sliceString( encrypted, i, klength );
        		key[ i ] = cc.getKey( currentSlice );
        }
        
        return key;
    }
    
    public HashSet<String> readDictionary( FileResource fr ) {
    		HashSet<String> dictionary = new HashSet<String>();
    		
    		for( String entry : fr.lines() ) {
    			dictionary.add( entry.toLowerCase() );
    		}
    		
    		return dictionary;
    }
    
    public int countWords( String message, HashSet<String> dictionary ) {
    		int validWordCount = 0;
    		
    		String[] words = message.split( "\\W" );
    		for( String word : words ) {
    			if( dictionary.contains( word.toLowerCase() ) ) {
    				validWordCount++;
    			}
    		}
    		
    		return validWordCount;
    }
    
    public String breakForLanguage( String encrypted, HashSet<String> dictionary ) {
    	
    		char mostCommon = this.mostCommonCharIn( dictionary );
    		String validResult = null;
    		int[] trueKey = null;
    		int maxValidWords = 0;
    		
    		for( int currentKeyLength = 1; currentKeyLength <= 100; ++currentKeyLength ) {
    			int[] key = this.tryKeyLength( encrypted, currentKeyLength, mostCommon );
    			VigenereCipher vc = new VigenereCipher( key );
    			String possibleDecrypted = vc.decrypt( encrypted );
    			int numValidWords = this.countWords( possibleDecrypted, dictionary );
    			
    			if( numValidWords > maxValidWords ) {
    				maxValidWords = numValidWords;
    				validResult = possibleDecrypted;
    				trueKey = key;
    			}
    		}
    	
    		System.out.println( "Guessed Key: " + Arrays.toString( trueKey ) );
    		System.out.println( "Key has length " + trueKey.length );
    		System.out.println( "Key yields " + maxValidWords + " valid words when decrypted." );
    		return validResult;
    }
    
    public String breakForAllLanguages( String encrypted, HashMap<String,HashSet<String>> languages ) {
    	
    		// For each language, call breakForLangauge
    	
    		int maxValidWords = 0;
    		String trueDecrypted = null;
    		String trueLanguage = null;
    	
    		for( String language: languages.keySet() ) {
    			System.out.println( "Testing language " + language );
    			HashSet<String> dictionary = languages.get( language );
    			String possibleDecrypt = this.breakForLanguage( encrypted, dictionary );
    			int validWordCount = this.countWords( possibleDecrypt, dictionary );
    			if( validWordCount > maxValidWords ) {
    				maxValidWords = validWordCount;
    				trueDecrypted = possibleDecrypt;
    				trueLanguage = language;
    			}
    		}
    		
    		System.out.println( "Encrypted Message written in " + trueLanguage + "." );
    		System.out.println( "Encrypted Message contains " + maxValidWords + " in " + trueLanguage + "." );
    		
    		return trueDecrypted;
    }

    public void breakVigenere () {
    	
    		FileResource secret = new FileResource();
    		String encrypted = secret.asString();

    		HashMap<String,HashSet<String>> languages = new HashMap<String,HashSet<String>>();
    		
    		// Read in all of the languages
    		for( String language : AVAILABLE_LANGUAGES ) {
    			FileResource fr = new FileResource( "dictionaries/" + language );
    			languages.put( language, this.readDictionary( fr ) );
    			System.out.println( "Language " + language + " read in successfully." );
    		}
        
        String decrypted = this.breakForAllLanguages( encrypted, languages );
        
        String[] encryptedLines = encrypted.split( "\n" );
        String[] decryptedLines = decrypted.split( "\n" );
        
        System.out.println( "Encrypted Message: \n===========================" );
        for( int i = 0; i < 5; ++i ) {
        		System.out.println( encryptedLines[ i ] );
        }
        
        System.out.println( "Decrypted Message: \n===========================" );
        for( int i = 0; i < 5; ++i ) {
    			System.out.println( decryptedLines[ i ] );
        }
        
    }
    
}
