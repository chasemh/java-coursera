package chasemh.java.coursera;

import edu.duke.*;
import java.util.*;
import java.io.*;

/**
 * Exercise solutions to Assignment 2: Words in Files
 * https://www.coursera.org/learn/java-programming-arrays-lists-data/supplement/RnGtN/programming-exercise-improving-gladlibs 
 *
 * @author Chase Hennion
 * @version 2017-10-20
 */

public class WordsInFiles {
	
	// Map of words to an ArrayList containing the file names of the files the word appears in
	private HashMap<String,ArrayList<String>> wordFileMap;
	
	public WordsInFiles() {
		this.wordFileMap = new HashMap<String,ArrayList<String>>();
	}
	
	// Private Methods
	private void addWordsFromFile( File f ) {
		
		// Add all of the words in f to the map
		String fileName = f.getName();
		FileResource fr = new FileResource( f );
		
		for( String word : fr.words() ) {
			//word = word.toLowerCase();
			if( this.wordFileMap.containsKey( word ) ) {
				// Already has the word in the map
				// Just append the file name to the arraylist
				if( !this.wordFileMap.get( word ).contains( fileName ) ) {
					this.wordFileMap.get( word ).add( fileName );
				}	
			}
			else {
				// Word isn't in the map 
				// Need to make a new ArrayList, add the word and then set the ArrayList in the map
				ArrayList<String> al = new ArrayList<String>();
				al.add( fileName );
				this.wordFileMap.put( word , al );
			}
		}
		
	}
	
	public void buildWordFileMap() {
		// Select many files from a directory
		// Iterate through the files to build up the word-file map
		
		this.wordFileMap.clear();
		DirectoryResource dr = new DirectoryResource();
		
		for( File f : dr.selectedFiles() ) {
			addWordsFromFile( f );
		}
	}
	
	public int maxNumber() {
		// Returns the maximum number of files any word appears in
		
		int maxFiles = -1;

		for( String word : this.wordFileMap.keySet() ) {
			int numFiles = this.wordFileMap.get( word ).size();
			if( numFiles > maxFiles ) {
				maxFiles = numFiles;
			}
		}
		return maxFiles;
	}
	
	public ArrayList<String> wordsInNumFiles( int number ) {
		// Returns an ArrayList of words that appear in exactly number files 
		
		ArrayList<String> validWords = new ArrayList<String>();
		
		// Iterate through the key set
			// If the size of the ArrayList value for the given key == number 
				// Add the key to the new ArrayList
		
		for( String word : this.wordFileMap.keySet() ) {
			int numFilesForWord = this.wordFileMap.get( word ).size();
			if ( numFilesForWord == number ) {
				validWords.add( word );
			}
		}
		
		return validWords;
	}
	
	public void printFilesIn( String word ) {
		// Print the name of the files that the given word is in.
		//word = word.toLowerCase();
		if( this.wordFileMap.containsKey( word ) ) {
			System.out.println( "The word " + word + " appears in the following files:\n=====================================" );
			ArrayList<String> fileNames = this.wordFileMap.get( word );
			for( int i = 0; i < fileNames.size(); ++i ) {
				System.out.println( fileNames.get( i ) );
			}
		}
		else {
			System.out.println( "The word " + word + " does not appear in any files." );
		}
		
	}
	
	public void printMap() {
		for( String word : this.wordFileMap.keySet() ) {
			System.out.print( word + ": " );
			int size = this.wordFileMap.get( word ).size();
			for( int i = 0; i < size ; ++i ) {
				System.out.print( this.wordFileMap.get( word ).get( i ) + "  " );
			}
			System.out.print( "\n" );
		}
	}
	
	public void tester() {
		this.buildWordFileMap();
		int maxNumFiles = this.maxNumber();
		System.out.println( "Max Number of Files any word is in: " +  maxNumFiles );
		// Determine all of the words that are in the max number of files 
		ArrayList<String> maxFileWords = this.wordsInNumFiles( maxNumFiles );
		// For each word, print the filenames the word is in.
		for( int i = 0; i < maxFileWords.size(); ++i ) {
			printFilesIn( maxFileWords.get( i ) );
		}	
		
		if( this.wordFileMap.size() < 20 ) {
			System.out.println( "\nEntire Map:\n===========");
			this.printMap();
		}
	}

	public static void main(String[] args) {
		WordsInFiles wif = new WordsInFiles();
		wif.tester();

	}

}
