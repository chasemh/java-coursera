package chasemh.java.coursera;

import java.util.*;

/**
 * Exercise solutions for Assignment: Sorting at Scale
 * https://www.coursera.org/learn/java-programming-design-principles/supplement/OnNGh/programming-exercise-sorting-at-scale
 *
 * @author Chase Hennion
 * @version 2017-10-28
 */
public class MagnitudeComparator implements Comparator<QuakeEntry> {
    public int compare(QuakeEntry qe1, QuakeEntry qe2) {
        return Double.compare(qe1.getMagnitude(), qe2.getMagnitude());
    }
    
}
