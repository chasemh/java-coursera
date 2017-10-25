package chasemh.java.coursera;
import java.util.ArrayList;

/**
 * Exercise solutions Assignment: Searching Earthquake Data
 * https://www.coursera.org/learn/java-programming-design-principles/supplement/3RkuE/programming-exercise-searching-earthquake-data
 *
 * @author Chase Hennion
 * @version 2017-10-24
 */
public class Tester {
	
	public void testBigQuakes() {
		EarthQuakeClient eqc = new EarthQuakeClient();
		eqc.bigQuakes( "data/nov20quakedatasmall.atom", 5.0 );
	}
	
	public void testCloseToMe() {
		EarthQuakeClient eqc = new EarthQuakeClient();
		eqc.closeToMe( "data/nov20quakedatasmall.atom", 35.988, -78.907 );
	}
	
	public void testQuakesOfDepth() {
		EarthQuakeClient eqc = new EarthQuakeClient();
		eqc.quakesOfDepth( "data/nov20quakedatasmall.atom", -10000.0, -5000.0 );
	}
	
	public void testQuakesByPhrase() {
		EarthQuakeClient eqc = new EarthQuakeClient();
		eqc.quakesByPhrase( "data/nov20quakedatasmall.atom", "start", "Explosion" );
	}
	
	public void testFindClosestQuakes() {
		ClosestQuakes cq = new ClosestQuakes();
		cq.findClosestQuakes( "data/nov20quakedatasmall.atom", -6.211, 106.845, 3);
	}
	
	public void testFindLargestQuakes() {
		LargestQuakes lq = new LargestQuakes();
		lq.findLargestQuakes( "data/nov20quakedatasmall.atom", 5);
	}
	
	public void testIndexOfLargest() {
		LargestQuakes lq = new LargestQuakes();
		EarthQuakeParser p = new EarthQuakeParser();
		String source = "data/nov20quakedatasmall.atom";
		ArrayList<QuakeEntry> quakeData = p.read( source );
		int maxIndex = lq.indexOfLargest( quakeData );
		System.out.println( "Max magnitude earthquake at index " + maxIndex );
		System.out.println( quakeData.get( maxIndex ) );
	}
	
	public void quiz1() {
		// https://www.coursera.org/learn/java-programming-design-principles/quiz/GkcpD/searching-earthquake-data
		String source = "data/nov20quakedata.atom";
		EarthQuakeClient eqc = new EarthQuakeClient();
		LargestQuakes lq = new LargestQuakes();
		
		eqc.quakesOfDepth( source, -10000.0, -8000.0);
		
		System.out.println();
		
		eqc.quakesByPhrase(source, "start", "Explosion" );
		
		System.out.println();
		
		eqc.quakesByPhrase(source, "end", "California" );
		
		System.out.println();
		
		eqc.quakesByPhrase( source, "any", "Creek" );
		
		System.out.println();
		
		lq.findLargestQuakes( source, 3);
		
		System.out.println();
		
		lq.findLargestQuakes( source, 5);
		
		System.out.println();
	}

	public static void main(String[] args) {
		Tester t = new Tester();
		//t.testBigQuakes();
		//t.testCloseToMe();
		//t.testQuakesOfDepth();
		//t.testQuakesByPhrase();
		//t.testFindClosestQuakes();
		//t.testFindLargestQuakes();
		//t.testIndexOfLargest();
		t.quiz1();

	}

}
