package chasemh.java.coursera;

import java.lang.IllegalArgumentException;

/**
 * Exercise solutions for Assignment: Filtering Data
 * https://www.coursera.org/learn/java-programming-design-principles/supplement/yzFlm/programming-exercise-filtering-data
 *
 * @author Chase Hennion
 * @version 2017-10-25
 */
public class PhraseFilter implements Filter {
	
	private String searchLocation;
	private String phrase;
	private String name;
	
	public PhraseFilter( String searchLocation, String phrase, String name ) {
		
		if( !searchLocation.equals( "start") && !searchLocation.equals( "end" ) && !searchLocation.equals( "any" ) ) {
			throw new IllegalArgumentException( "Bad Value for where string: " + searchLocation + ". Valid values are: start, end, any"  );
		}
		
		this.searchLocation = searchLocation;
		this.phrase = phrase;
		this.name = name;
	}
	
	@Override
	public boolean satisfies( QuakeEntry qe ) {
		// Return true if phrase is found at the searchLocation in the qe's info
		// Otherwise Return false 
		
		String quakeInfo = qe.getInfo();
		if( this.searchLocation.equals( "start" ) && quakeInfo.startsWith( this.phrase ) ) {
			return true;
		}
		else if( this.searchLocation.equals( "end" ) && quakeInfo.endsWith( this.phrase ) ) {
			return true;
		}
		else if( this.searchLocation.equals( "any" ) && quakeInfo.contains( this.phrase ) ) {
			return true;
		}
		
		return false;
	}

	@Override
	public String getName() {
		return this.name;
	}

}
