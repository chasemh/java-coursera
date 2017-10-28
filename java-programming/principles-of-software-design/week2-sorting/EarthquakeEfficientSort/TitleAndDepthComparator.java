package chasemh.java.coursera;

import java.util.*;

/**
 * Exercise solutions for Assignment: Sorting at Scale
 * https://www.coursera.org/learn/java-programming-design-principles/supplement/OnNGh/programming-exercise-sorting-at-scale
 *
 * @author Chase Hennion
 * @version 2017-10-28
 */
public class TitleAndDepthComparator implements Comparator<QuakeEntry> {

	@Override
	public int compare( QuakeEntry q1, QuakeEntry q2 ) {
		// q1 title before q2 title: Return negative
		// q1 title after q2 title : Return positive
		// q1 title same as q2 title: Compare depths.
		int titleCompare = q1.getInfo().compareTo( q2.getInfo() );
		if( titleCompare == 0 ) {
			return Double.compare( q1.getDepth(), q2.getDepth() );
		}
		return titleCompare;
	}

}
