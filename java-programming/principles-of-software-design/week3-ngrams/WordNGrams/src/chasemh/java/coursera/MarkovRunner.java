package chasemh.java.coursera;

import edu.duke.*;

/**
 * Exercise solutions to Assignment: Word N-Grams
 * https://www.coursera.org/learn/java-programming-design-principles/supplement/wkafw/programming-exercise-word-n-grams 
 *
 * Modified By Chase Hennion
 * @version 2017-11-06
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

    public void runMarkov( int seed, int numWords ) { 
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        MarkovWordOne markovWord = new MarkovWordOne(); 
        markovWord.setRandom( seed );
        runModel( markovWord, st, numWords ); 
    } 

    public void testGetFollows() {
    		String st = "this is just a test yes this is a simple test";
    		st = st.replace('\n', ' '); 
        MarkovWordOne markovWord = new MarkovWordOne(); 
        runModel( markovWord, st, 50 );
    }
    
    public void runMarkovTwo( int seed, int numWords ) {
    	FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        MarkovWordTwo markovWord = new MarkovWordTwo(); 
        markovWord.setRandom( seed );
        runModel( markovWord, st, numWords ); 
    }

}
