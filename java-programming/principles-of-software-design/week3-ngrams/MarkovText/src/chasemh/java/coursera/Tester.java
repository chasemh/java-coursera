package chasemh.java.coursera;

import java.util.ArrayList;
import edu.duke.*;

/**
 * Exercise solutions to Assignment: Generating Random Text
 * https://www.coursera.org/learn/java-programming-design-principles/supplement/xToQx/programming-exercise-generating-random-text 
 *
 * Modified By Chase Hennion
 * @version 2017-11-04
 */
public class Tester {
	
	private void printArrayList( ArrayList<String> al ) {
		
		System.out.println( "Contents of Array: " );
		for( String val : al ) {
			System.out.print( val + ", " );
		}
		System.out.println( "\n Size of Array: " + al.size() );
		
	}
	
	public void testGetFollows( String training, String key ) {
		
		MarkovOne mo = new MarkovOne();
		mo.setTraining( training );
		
		ArrayList< String > follows = mo.getFollows( key );
		this.printArrayList( follows );
		
	}
	
	public void testGetFollowsWithFile( String key ) {
	
		FileResource fr = new FileResource();
		MarkovOne mo = new MarkovOne();
		mo.setTraining( fr.asString() );
		
		ArrayList<String> follows = mo.getFollows( key );
		System.out.println( "Size of follows using key " + key + " with file: " + follows.size() );
		
	}
	
	public static void main( String args[] ) {
		Tester t = new Tester();
		String training = "this is a test yes this is a test.";
		/*
		t.testGetFollows( training, "t" );
		t.testGetFollows( training, "e" );
		t.testGetFollows( training, "es" );
		t.testGetFollows( training, "t." );
		t.testGetFollows( training, "." );
		*/
		//t.testGetFollowsWithFile( "t" );
		
		// Practice Quiz
		//t.testGetFollowsWithFile( "o" );
		t.testGetFollowsWithFile( "th" );
		
	}

}
