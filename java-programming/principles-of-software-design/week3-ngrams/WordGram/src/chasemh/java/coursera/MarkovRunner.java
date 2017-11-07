package chasemh.java.coursera;

import edu.duke.*;

/**
 * Exercise solutions to Assignment: WordGram Class
 * https://www.coursera.org/learn/java-programming-design-principles/supplement/cajch/programming-exercise-wordgram-class 
 *
 * Modified By Chase Hennion
 * @version 2017-11-06
 */
public class MarkovRunner {
    public void runModel(IMarkovModel markov, String text, int size){ 
        markov.setTraining(text); 
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runModel(IMarkovModel markov, String text, int size, int seed){ 
        markov.setTraining(text); 
        markov.setRandom(seed);
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runMarkov( int seed, int order ) { 
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        MarkovWord mw = new MarkovWord( order );
        mw.setRandom( seed );
        this.runModel( mw, st, 50 );
    }
    
    public void runMarkovWord( int seed, int order ) {
    		FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' ');
        MarkovWord mw = new MarkovWord( order );
        mw.setRandom( seed );
        this.runModel( mw, st, 50 );
    }
    
    public void testHashMap( int seed, int order, String st ) {
    		EfficientMarkovWord emw = new EfficientMarkovWord( order );
    		emw.setRandom( seed );
    		this.runModel( emw, st, 50 );
    }
    
    public void compareMethods( int seed, int order ) {
    	FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' ');
        MarkovWord mw = new MarkovWord( order );
        EfficientMarkovWord emw = new EfficientMarkovWord( order );
        mw.setRandom( seed );
        emw.setRandom( seed );
        
        long mwStart = System.nanoTime();
        this.runModel( mw, st, 100 );
        long mwEnd = System.nanoTime();
        
        long emwStart = System.nanoTime();
        this.runModel( emw, st, 100 );
        long emwEnd = System.nanoTime();
        		
        	long mwDuration = mwEnd - mwStart;
        long emwDuration = emwEnd - emwStart;
        long diff = mwDuration - emwDuration;
        
        System.out.println( "MarkovWord took " + mwDuration + "ns to complete." );
        System.out.println( "EfficientMarkovWord took " + emwDuration + "ns to complete." );
        System.out.println( "MarkovWord took " + diff + "ns longer to complete." );
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
    		MarkovRunner mr = new MarkovRunner();
    		//mr.runMarkovWord( 643, 3 );
    		//mr.testHashMap( 42, 2, "this is a test yes this is really a test" );
    		//mr.testHashMap( 42, 2, "this is a test yes this is really a test yes a test this is wow" );
    		//mr.compareMethods( 42, 2 );
    		
    		// Practice Quiz
    		//mr.runMarkov( 621, 3 );
    		//mr.runMarkov( 844, 5 );
    		//FileResource fr = new FileResource();
    		//String st = fr.asString();
    		//st = st.replace('\n', ' ');
    		//mr.testHashMap( 371, 3, st );
    		//mr.testHashMap( 65, 2, st );
    }

}
