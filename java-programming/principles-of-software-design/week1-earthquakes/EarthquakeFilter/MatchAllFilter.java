package chasemh.java.coursera;

import java.util.ArrayList;

/**
 * Exercise solutions for Assignment: Filtering Data
 * https://www.coursera.org/learn/java-programming-design-principles/supplement/yzFlm/programming-exercise-filtering-data
 *
 * @author Chase Hennion
 * @version 2017-10-25
 */
public class MatchAllFilter implements Filter {
	
	private ArrayList<Filter> filters;
	
	public MatchAllFilter() {
		this.filters = new ArrayList<Filter>();
	}
	
	public MatchAllFilter( ArrayList<Filter> filters ) {
		this.filters = filters;
	}
	
	public void addFilter( Filter f ) {
		this.filters.add( f );
	}
	
	@Override
	public boolean satisfies( QuakeEntry qe ) {
		// Return true if every filter in the filters list is satisfied by this QuakeEntry.
		// Otherwise return false
		
		for( Filter f : this.filters ) {
			if( !f.satisfies( qe ) ) {
				return false;
			}
		}
		
		return true;
	}

	@Override
	public String getName() {
		StringBuilder sb = new StringBuilder();
		for( Filter f : this.filters ) {
			sb.append( f.getName() + "  " );
		}
		return sb.toString();
	}

}
