package chasemh.java.coursera;

import java.util.*;

/**
 * Exercise solutions for Assignment: Sorting at Scale
 * https://www.coursera.org/learn/java-programming-design-principles/supplement/OnNGh/programming-exercise-sorting-at-scale
 *
 * @author Chase Hennion
 * @version 2017-10-28
 */
public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry> {
	
	@Override
	public int compare( QuakeEntry q1, QuakeEntry q2 ) {
		// Compare the last word of info
		// If the same, compare by magnitude
		String[] q1Words = q1.getInfo().split( " " );
		String[] q2Words = q2.getInfo().split( " " );
		
		int lastWordCompare = q1Words[ q1Words.length - 1].compareTo( q2Words[ q2Words.length - 1 ] );
		if( lastWordCompare == 0 ) {
			return Double.compare( q1.getMagnitude(), q2.getMagnitude() );
		}
		
		return lastWordCompare;
		
	}

}
