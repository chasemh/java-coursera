package chasemh.java.coursera;

import edu.duke.*; 

/**
 * Exercise solutions to Assignment: Interfaces and Abstract Classes
 * https://www.coursera.org/learn/java-programming-design-principles/supplement/n1DIm/programming-exercise-interface-and-abstract-class 
 *
 * Modified By Chase Hennion
 * @version 2017-11-05
 */
public class MarkovRunnerWithInterface {
    public void runModel( IMarkovModel markov, String text, int size, int seed ) {
        markov.setTraining(text);
        markov.setRandom( seed );
        System.out.println("running with " + markov);
        for(int k=0; k < 3; k++){
			String st= markov.getRandomText(size);
			printOut(st);
		}
    }
    
    public void runMarkov( int seed ) {
        FileResource fr = new FileResource();
		String st = fr.asString();
		st = st.replace('\n', ' ');
		int size = 200;
		
        MarkovZero mz = new MarkovZero();
        
        runModel(mz, st, size, seed);
    
        MarkovOne mOne = new MarkovOne();
        runModel(mOne, st, size, seed);
        
        MarkovModel mThree = new MarkovModel(3);
        runModel(mThree, st, size, seed);
        
        MarkovFour mFour = new MarkovFour();
        runModel(mFour, st, size, seed);

    }
    
    public void testHashMap( int seed, int order ) {
    	
    		FileResource fr = new FileResource();
		String st = fr.asString();
		st = st.replace('\n', ' ');
    		//String st = "yes-this-is-a-thin-pretty-pink-thistle";
		int size = 50;
		
		EfficientMarkovModel emm = new EfficientMarkovModel( order );
		runModel( emm, st, size, seed );
    	
    }
    
    public void compareMethods( int seed, int order, int size ) {
    	
    		FileResource fr = new FileResource();
    		String st = fr.asString();
    		st = st.replace('\n', ' ');
    	
    		MarkovModel mm = new MarkovModel( order );
    		EfficientMarkovModel emm = new EfficientMarkovModel( order );
    		
    		long mmStart = System.nanoTime();
    		this.runModel( mm, st, size, seed );
    		long mmStop = System.nanoTime();
    		
    		long emmStart = System.nanoTime();
    		this.runModel( emm, st, size, seed );
    		long emmStop = System.nanoTime();
    		
    		long mmDuration = mmStop - mmStart;
    		long emmDuration = emmStop - emmStart;
    		long runtimeDiff = mmDuration - emmDuration;
    		
    		System.out.println( "Runtime for MarkovModel w/ order " + order + " and seed " + seed + " is " + mmDuration + "ns" );
    		System.out.println( "Runtime for EfficientMarkovModel w/ order " + order + " and seed " + seed + " is " + emmDuration + "ns" );
    		System.out.println( "MarkovModel took " + runtimeDiff + "ns longer than EfficientMarkovModel" );
    		
    	
    }

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
	
	public static void main( String[] args ) {
		MarkovRunnerWithInterface mr = new MarkovRunnerWithInterface();
		//mr.runMarkov( 10 );
		//mr.testHashMap( 42, 2 );
		//mr.compareMethods( 42, 2, 1000 );
		
		// Practice Quiz
		//mr.testHashMap( 615, 5 );
		
		// Section Quiz
		mr.testHashMap( 792, 6 );
		//mr.testHashMap(531, 5);
	}
	
}
