package chasemh.java.coursera;

/**
 * Exercise solutions for Assignment: Filtering Data
 * https://www.coursera.org/learn/java-programming-design-principles/supplement/yzFlm/programming-exercise-filtering-data
 *
 * @author Chase Hennion
 * @version 2017-10-25
 */
public class DistanceFilter implements Filter {
	
	private Location location;
	private double maxDist;
	private String name;
	
	public DistanceFilter( Location location, double maxDist, String name ) {
		this.location = location;
		this.maxDist = maxDist;
		this.name = name;
	}

	@Override
	public boolean satisfies( QuakeEntry qe ) {
		// Return true if the distance between the location of the given quake and the given location is less than maxDist
		// Return false otherwise
		return location.distanceTo( qe.getLocation() ) < maxDist;
	}

	@Override
	public String getName() {
		return this.name;
	}
	
}
