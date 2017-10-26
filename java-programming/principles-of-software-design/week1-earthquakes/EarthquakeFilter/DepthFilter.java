package chasemh.java.coursera;

/**
 * Exercise solutions for Assignment: Filtering Data
 * https://www.coursera.org/learn/java-programming-design-principles/supplement/yzFlm/programming-exercise-filtering-data
 *
 * @author Chase Hennion
 * @version 2017-10-25
 */
public class DepthFilter implements Filter {
	
	private double minDepth;
	private double maxDepth;
	private String name;
	
	public DepthFilter( double minDepth, double maxDepth, String name ) {
		this.minDepth = minDepth;
		this.maxDepth = maxDepth;
		this.name = name;
	}

	@Override
	public boolean satisfies( QuakeEntry qe ) {
		// Return true if depth is between minDepth and maxDepth, inclusive
		// Return false otherwise
		
		return qe.getDepth() >= this.minDepth && qe.getDepth() <= this.maxDepth;
	}

	@Override
	public String getName() {
		return this.name;
	}
	
	
}
