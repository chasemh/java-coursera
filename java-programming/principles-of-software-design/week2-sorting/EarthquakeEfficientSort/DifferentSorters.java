package chasemh.java.coursera;

/**
 * Exercise solutions for Assignment: Sorting at Scale
 * https://www.coursera.org/learn/java-programming-design-principles/supplement/OnNGh/programming-exercise-sorting-at-scale
 *
 * @author Chase Hennion
 * @version 2017-10-28
 */
import java.util.*;

public class DifferentSorters {
	
	public void quizSortWithCompareTo( String source, int printIndex ) {
		EarthQuakeParser parser = new EarthQuakeParser();
        ArrayList<QuakeEntry> list  = parser.read(source);
        Collections.sort(list);
        for(QuakeEntry qe: list) {
            System.out.println(qe);
        }
        
        System.out.println( printIndex + " entry in the list:" );
        System.out.println( list.get( printIndex ) );
	}
	
	public void quizSortByTitleAndDepth( String source, int printIndex ) {
		EarthQuakeParser parser = new EarthQuakeParser();
        ArrayList<QuakeEntry> list  = parser.read(source);
        Collections.sort(list, new TitleAndDepthComparator());
        for(QuakeEntry qe: list) {
            System.out.println(qe);
        }
        
        System.out.println( printIndex + " entry in the list:" );
        System.out.println( list.get( printIndex ) );
	}
	
	public void quizSortByLastWordInTitleThenByMagnitude( String source, int printIndex ) {
		EarthQuakeParser parser = new EarthQuakeParser();
        ArrayList<QuakeEntry> list  = parser.read(source);
        Collections.sort(list, new TitleLastAndMagnitudeComparator() );
        for(QuakeEntry qe: list) {
            System.out.println(qe);
        }
        
        System.out.println( printIndex + " entry in the list:" );
        System.out.println( list.get( printIndex ) );
	}
	
	public void sortByLastWordInTitleThenByMagnitude() {
		EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        Collections.sort(list, new TitleLastAndMagnitudeComparator() );
        for(QuakeEntry qe: list) {
            System.out.println(qe);
        }
        
        System.out.println( "Tenth entry in the list:" );
        System.out.println( list.get( 10 ) );
	}
	
	public void sortWithTitleAndDepth() {
		EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        Collections.sort(list, new TitleAndDepthComparator() );
        for(QuakeEntry qe: list) {
            System.out.println(qe);
        }
        
        System.out.println( "Tenth entry in the list:" );
        System.out.println( list.get( 10 ) );
		
	}
	
	
    public void sortWithCompareTo() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        Collections.sort(list);
        for(QuakeEntry qe: list) {
            System.out.println(qe);
        }
        
        System.out.println( "Tenth entry in the list:" );
        System.out.println( list.get( 10 ) );

    }    

    public void sortByMagnitude() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        Collections.sort(list, new MagnitudeComparator());
        for(QuakeEntry qe: list) {
            System.out.println(qe);
        }

    }

    public void sortByDistance() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        // Location is Durham, NC
        Location where = new Location(35.9886, -78.9072);
        Collections.sort(list, new DistanceComparator(where));
        for(QuakeEntry qe: list) {
            System.out.println(qe);
        }

    }
}
