package chasemh.java.coursera;

import edu.duke.*;
import java.util.*;

/**
 * Exercise solutions to Assignment: Fruits and Verbs
 * https://www.coursera.org/learn/java-programming-arrays-lists-data/supplement/54Iig/programming-exercise-using-gladlibs 
 *
 * Modified By Chase Hennion
 * @version 2017-10-20
 */
public class GladLib {
	private ArrayList<String> adjectiveList;
	private ArrayList<String> nounList;
	private ArrayList<String> colorList;
	private ArrayList<String> countryList;
	private ArrayList<String> nameList;
	private ArrayList<String> animalList;
	private ArrayList<String> timeList;
	private ArrayList<String> verbList;
	private ArrayList<String> fruitList;
	private ArrayList<String> usedWords;
	
	private Random myRandom;
	
	private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
	private static String dataSourceDirectory = "data";
	
	public GladLib(){
		this.usedWords = new ArrayList<String>();
		initializeFromSource(dataSourceDirectory);
		myRandom = new Random();
	}
	
	public GladLib(String source){
		this.usedWords = new ArrayList<String>();
		initializeFromSource(source);
		myRandom = new Random();
	}
	
	private void initializeFromSource(String source) {
		adjectiveList= readIt(source+"/adjective.txt");	
		nounList = readIt(source+"/noun.txt");
		colorList = readIt(source+"/color.txt");
		countryList = readIt(source+"/country.txt");
		nameList = readIt(source+"/name.txt");		
		animalList = readIt(source+"/animal.txt");
		timeList = readIt(source+"/timeframe.txt");	
		verbList = readIt(source+"/verb.txt");
		fruitList = readIt(source+"/fruit.txt");
	}
	
	private String randomFrom(ArrayList<String> source){
		int index = myRandom.nextInt(source.size());
		return source.get(index);
	}
	
	private String getSubstitute(String label) {
		if (label.equals("country")) {
			return randomFrom(countryList);
		}
		if (label.equals("color")){
			return randomFrom(colorList);
		}
		if (label.equals("noun")){
			return randomFrom(nounList);
		}
		if (label.equals("name")){
			return randomFrom(nameList);
		}
		if (label.equals("adjective")){
			return randomFrom(adjectiveList);
		}
		if (label.equals("animal")){
			return randomFrom(animalList);
		}
		if (label.equals("timeframe")){
			return randomFrom(timeList);
		}
		if (label.equals("verb")){
			return randomFrom(verbList);
		}
		if (label.equals("fruit")){
			return randomFrom(fruitList);
		}
		if (label.equals("number")){
			return ""+myRandom.nextInt(50)+5;
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
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//GladLib gl = new GladLib( "../data/short" );
		GladLib gl = new GladLib( "data/long" );
		gl.makeStory();

	}
	


}
