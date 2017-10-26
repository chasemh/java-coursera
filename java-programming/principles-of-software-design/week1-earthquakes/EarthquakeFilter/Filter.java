package chasemh.java.coursera;

/**
 * Exercise solutions for Assignment: Filtering Data
 * https://www.coursera.org/learn/java-programming-design-principles/supplement/yzFlm/programming-exercise-filtering-data
 *
 * @author by Chase Hennion
 * @version 2017-10-25
 */
public interface Filter
{
    public boolean satisfies(QuakeEntry qe); 
    public String getName();
}
