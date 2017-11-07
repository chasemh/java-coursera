package chasemh.java.coursera;

/**
 * Exercise solutions to Assignment: Word N-Grams
 * https://www.coursera.org/learn/java-programming-design-principles/supplement/wkafw/programming-exercise-word-n-grams 
 *
 * Modified By Chase Hennion
 * @version 2017-11-06
 */
public class Tester {

	public static void main(String[] args) {
		//MarkovWordOne mwo = new MarkovWordOne();
		//mwo.testIndexOf();
		
		MarkovRunner mr = new MarkovRunner();
		//mr.testGetFollows();
		//mr.runMarkov( 175, 120 );
		//mr.runMarkovTwo( 549, 50 );
		
		// Practice Quiz
		//mr.runMarkov( 139, 50 );
		mr.runMarkovTwo( 832, 50 );

	}

}
