package chasemh.java.coursera;

/**
 * Exercise solutions for Assignment: Filtering Data
 * https://www.coursera.org/learn/java-programming-design-principles/supplement/yzFlm/programming-exercise-filtering-data
 *
 * @authorx Chase Hennion
 * @version 2017-10-25
 */
public class MinMagFilter implements Filter
{
    private double magMin; 
    String name;
    
    public MinMagFilter(double min, String name) { 
        magMin = min;
        this.name = name;
    } 

    public boolean satisfies(QuakeEntry qe) { 
        return qe.getMagnitude() >= magMin; 
    }

	@Override
	public String getName() {
		return null;
	} 

}
