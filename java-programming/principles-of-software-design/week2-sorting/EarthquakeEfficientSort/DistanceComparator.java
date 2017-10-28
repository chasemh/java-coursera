package chasemh.java.coursera;

/**
 * Exercise solutions for Assignment: Sorting at Scale
 * https://www.coursera.org/learn/java-programming-design-principles/supplement/OnNGh/programming-exercise-sorting-at-scale
 *
 * @author Chase Hennion
 * @version 2017-10-28
 */
import java.util.*;

public class DistanceComparator implements Comparator<QuakeEntry> {
    Location fromWhere;
    
    public DistanceComparator(Location where) {
        fromWhere = where;
    }
    
    public int compare(QuakeEntry q1, QuakeEntry q2) {
        double dist1 = q1.getLocation().distanceTo(fromWhere);
        double dist2 = q2. getLocation().distanceTo(fromWhere);
        return Double.compare(dist1, dist2);
    }
    
}
 