package chasemh.java.coursera;
import java.util.*;

/**
 * Exercise solutions Assignment: Searching Earthquake Data
 * https://www.coursera.org/learn/java-programming-design-principles/supplement/3RkuE/programming-exercise-searching-earthquake-data
 *
 * Modified By Chase Hennion
 * @version 2017-10-24
 */

public class LargestQuakes {
	
	public int indexOfLargest( ArrayList<QuakeEntry> quakeData ) {
		// Find the index of the largest magnitide quake in quakeData
		int maxIndex = -1;
		double maxMag = 0.0;
		for( int i = 0; i < quakeData.size(); ++i ) {
			double mag = quakeData.get( i ).getMagnitude();
			if( mag > maxMag ) {
				maxMag = mag;
				maxIndex = i;
			}
		}
			
		return maxIndex;
	}
	
	public ArrayList<QuakeEntry> getLargest( ArrayList<QuakeEntry> quakeData, int howMany ) {
		// Get howMany number of earthquakes with the largest magnitude
		// Order so largest magnitude is at index 0
		
		ArrayList<QuakeEntry> ret = new ArrayList<QuakeEntry>();
		ArrayList<QuakeEntry> workingCopy = new ArrayList<QuakeEntry>( quakeData );
		
		for( int i = 0; i < howMany; ++i ) {
			int indexOfLargest =  this.indexOfLargest( workingCopy );
			ret.add( workingCopy.get( indexOfLargest ) );
			workingCopy.remove( indexOfLargest );
		}
		
		return ret;
	}
	
	public void findLargestQuakes( String source, int howMany) {
		// Read in earthquake data from a source 
		// Put it into an ArrayList
		// Print out how many earthquakes were read 
		// Print out each of the quake entries read in
		
		EarthQuakeParser p = new EarthQuakeParser();
		//String source = "data/nov20quakedatasmall.atom";
		ArrayList<QuakeEntry> quakeData = p.read( source );
		
		System.out.println( "Read in " + quakeData.size() + " earthquake entries from " + source + "." );
		
		//int howMany = 5;
		ArrayList<QuakeEntry> largestQuakes = this.getLargest( quakeData, howMany );
		System.out.println( howMany + " Largest Earthquakes:" );
		for( QuakeEntry qe : largestQuakes ) {
			System.out.println( qe );
		}
		
		/*
		for( QuakeEntry qe : quakeData ) {
			System.out.println( qe );
		}
		*/
	}

}
