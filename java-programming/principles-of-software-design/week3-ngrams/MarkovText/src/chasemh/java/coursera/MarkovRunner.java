package chasemh.java.coursera;

import edu.duke.*;

/**
 * Exercise solutions to Assignment: Generating Random Text
 * https://www.coursera.org/learn/java-programming-design-principles/supplement/xToQx/programming-exercise-generating-random-text 
 *
 * Modified By Chase Hennion
 * @version 2017-11-04
 */
public class MarkovRunner {
	
	private void printOut(String s){
		String[] words = s.split("\\s+");
		int psize = 0;
		System.out.println("----------------------------------");
		for(int k=0; k < words.length; k++){
			System.out.print(words[k]+ " ");
			psize += words[k].length() + 1;
			if (psize > 60) {
				System.out.println();
				psize = 0;
			}
		}
		System.out.println("\n----------------------------------");
	}
	
    public void runMarkovZero( int randomSeed ) {
		FileResource fr = new FileResource();
		String st = fr.asString();
		st = st.replace('\n', ' ');
		MarkovZero markov = new MarkovZero();
		
		markov.setRandom( randomSeed );
		
		markov.setTraining(st);
		for(int k=0; k < 3; k++){
			String text = markov.getRandomText(500);
			printOut(text);
		}
	}
    
    public void runMarkovOne( int randomSeed ) {
		FileResource fr = new FileResource();
		String st = fr.asString();
		st = st.replace('\n', ' ');
		MarkovOne markov = new MarkovOne();
		
		markov.setRandom( randomSeed );
		
		//String st = "this is a test yes a test.";
		markov.setTraining(st);
		for(int k=0; k < 3; k++){
			String text = markov.getRandomText(500);
			printOut(text);
		}
	}
    
    public void runMarkovFour( int randomSeed ) {
		FileResource fr = new FileResource();
		String st = fr.asString();
		st = st.replace('\n', ' ');
		MarkovFour markov = new MarkovFour();
		
		markov.setRandom( randomSeed );
		
		markov.setTraining(st);
		for(int k=0; k < 3; k++){
			String text = markov.getRandomText(500);
			printOut(text);
		}
	}
    
    public void runMarkovModel( int randomSeed, int order ) {
		FileResource fr = new FileResource();
		String st = fr.asString();
		st = st.replace('\n', ' ');
		MarkovModel markov = new MarkovModel( order );
		
		markov.setRandom( randomSeed );
		
		markov.setTraining(st);
		for(int k=0; k < 3; k++){
			String text = markov.getRandomText(500);
			printOut(text);
		}
	}
	
	public static void main( String[] args ) {
		MarkovRunner mr = new MarkovRunner();
		//mr.runMarkovZero( 42 );
		//mr.runMarkovZero( 42 );
	    //mr.runMarkovOne( 42 );
		//mr.runMarkovFour( 25 );
		//mr.runMarkovModel( 38, 6 );
		
		// Practice Quiz
		//mr.runMarkovZero( 88 );
		//mr.runMarkovOne( 273 );
		//mr.runMarkovFour( 371 );
		//mr.runMarkovModel( 365, 8 );
		
		// Section Quiz
		//mr.runMarkovZero( 1024 );
		//mr.runMarkovOne( 365 );
		//mr.runMarkovFour( 715 );
		//mr.runMarkovModel( 953, 7 );
	}
	
}
