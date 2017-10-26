package chasemh.java.coursera;

/**
 * Exercise solutions for Assignment: Filtering Data
 * https://www.coursera.org/learn/java-programming-design-principles/supplement/yzFlm/programming-exercise-filtering-data
 *
 * @author Chase Hennion
 * @version 2017-10-25
 */
public class MagnitudeFilter implements Filter {

	private double minMag;
	private double maxMag;
	private String name;
	
	public MagnitudeFilter( double minMag, double maxMag, String name ) {
		this.minMag = minMag;
		this.maxMag = maxMag;
		this.name = name;
	}

	@Override
	public boolean satisfies( QuakeEntry qe ) {
		
		// Return true if qe magnitude is between minMag and maxMag, inclusive
		// Otherwise return false
		return qe.getMagnitude() >= this.minMag && qe.getMagnitude() <= this.maxMag;

	}

	@Override
	public String getName() {
		return this.name;
	}

}
