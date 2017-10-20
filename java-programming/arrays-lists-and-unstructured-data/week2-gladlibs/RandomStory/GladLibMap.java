package chasemh.java.coursera;

import edu.duke.*;
import java.util.*;

/**
 * Exercise solutions to Assignment 3: Maps Version of GladLibs
 * https://www.coursera.org/learn/java-programming-arrays-lists-data/supplement/RnGtN/programming-exercise-improving-gladlibs 
 *
 * Modified By Chase Hennion
 * @version 2017-10-20
 */
public class GladLibMap {
	
	HashMap<String,ArrayList<String>> myMap;
	private ArrayList<String> usedWords;
	private ArrayList<String> categoriesUsed;
	private Random myRandom;
	
	private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
	private static String dataSourceDirectory = "data";
	
	public GladLibMap(){
		this.usedWords = new ArrayList<String>();
		this.categoriesUsed = new ArrayList<String>();
		this.myMap = new HashMap<String,ArrayList<String>>();
		initializeFromSource(dataSourceDirectory);
		myRandom = new Random();
	}
	
	public GladLibMap(String source){
		this.usedWords = new ArrayList<String>();
		this.myMap = new HashMap<String,ArrayList<String>>();
		this.categoriesUsed = new ArrayList<String>();
		initializeFromSource(source);
		myRandom = new Random();
	}
	
	private void initializeFromSource(String source) {
		String[] categories = { "adjective", "noun", "color", "country", "name", "animal", "timeframe", "verb", "fruit" };
		
		for( int i = 0; i < categories.length; ++i ) {
			String category = categories[ i ];
			this.myMap.put( categories[ i ], readIt( source + "/" + category + ".txt" ) );
		}
	}
	
	private String randomFrom(ArrayList<String> source){
		int index = myRandom.nextInt(source.size());
		return source.get(index);
	}
	
	private String getSubstitute(String label) {	
		
		if (label.equals("number")){
			return ""+myRandom.nextInt(50)+5;
		}
		else if( this.myMap.containsKey( label ) ) {
			// Mark the category as used
			if( !this.categoriesUsed.contains( label ) ) {
				this.categoriesUsed.add( label );
			}
			return randomFrom( this.myMap.get( label ) );
		}
		
		return "**UNKNOWN**";
	}
	
	private String processWord(String w){
		int first = w.indexOf("<");
		int last = w.indexOf(">",first);
		if (first == -1 || last == -1){
			return w;
		}
		String prefix = w.substring(0,first);
		String suffix = w.substring(last+1);
		String sub = getSubstitute(w.substring(first+1,last));
		
		boolean validSubFound = false;
		while( !validSubFound ) {
			if( this.usedWords.contains( sub ) ) {
				// Find an unused word
				sub = getSubstitute(w.substring(first+1,last));
			}
			else {
				validSubFound = true;
			}
		}
		
		this.usedWords.add( sub );
		return prefix+sub+suffix;
	}
	
	private void printOut(String s, int lineWidth){
		int charsWritten = 0;
		for(String w : s.split("\\s+")){
			if (charsWritten + w.length() > lineWidth){
				System.out.println();
				charsWritten = 0;
			}
			System.out.print(w+" ");
			charsWritten += w.length() + 1;
		}
	}
	
	private String fromTemplate(String source){
		String story = "";
		if (source.startsWith("http")) {
			URLResource resource = new URLResource(source);
			for(String word : resource.words()){
				story = story + processWord(word) + " ";
			}
		}
		else {
			FileResource resource = new FileResource(source);
			for(String word : resource.words()){
				story = story + processWord(word) + " ";
			}
		}
		return story;
	}
	
	private ArrayList<String> readIt(String source){
		ArrayList<String> list = new ArrayList<String>();
		if (source.startsWith("http")) {
			URLResource resource = new URLResource(source);
			for(String line : resource.lines()){
				list.add(line);
			}
		}
		else {
			FileResource resource = new FileResource(source);
			for(String line : resource.lines()){
				list.add(line);
			}
		}
		return list;
	}
	
	public void makeStory(){
		this.usedWords.clear();
	    System.out.println("\n");
		String story = fromTemplate("data/long/madtemplate2.txt");
		printOut(story, 60);
		
		System.out.println("\n\nNumber of Words Replaced in Story: " + this.usedWords.size() );
		System.out.println( "Total Number of Words Considered: " + totalWordsConsidered() );
		System.out.println( "Total Number of Words in the entire map: " + totalWordsInMap() );
	}
	
	public int totalWordsInMap() {
		// Get the total number of words in all of the ArrayLists in the map
		int count = 0;
		for( String word : this.myMap.keySet() ) {
			count += this.myMap.get( word ).size();
		}
		
		return count;
	}
	
	public int totalWordsConsidered() {
		// Returns the total number of words in ArrayLists for categories that were used in creating the story
		int count = 0;
		for( int i = 0; i < this.categoriesUsed.size(); ++i ) {
			String category = this.categoriesUsed.get( i );
			count += this.myMap.get( category ).size();
		}
		return count;
	}

	public static void main(String[] args) {
		GladLibMap glm = new GladLibMap( "data/long" );
		glm.makeStory();
	}

}
