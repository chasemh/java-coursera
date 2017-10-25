
/**
 * Find N-closest quakes
 * 
 * @author Duke Software/Learn to Program
 * @version 1.0, November 2015
 */

import java.util.*;

/**
 * Exercise solutions Assignment: Searching Earthquake Data
 * https://www.coursera.org/learn/java-programming-design-principles/supplement/3RkuE/programming-exercise-searching-earthquake-data
 *
 * Modified By Chase Hennion
 * @version 2017-10-24
 */
public class ClosestQuakes {
	
	public ArrayList<QuakeEntry> sortByDistance( ArrayList<QuakeEntry> quakeData, Location current ) {
		ArrayList<QuakeEntry> sorted = new ArrayList<QuakeEntry>( quakeData );
		// Use a selection sort
		for( int i = 0; i < sorted.size() - 1; ++i ) {
			int minIndex = i;
			for( int j = i + 1; j < sorted.size(); ++j ) {
				// Find the smallest distance
				double distAtJ = sorted.get( j ).getLocation().distanceTo( current );
				double currentMinDist = sorted.get( minIndex ).getLocation().distanceTo( current );
				if( distAtJ < currentMinDist ) {
					minIndex = j;
				}
				
			}
			// Swap the QuakeEntry with the smallest distance with the QuakeEntry in index i
			QuakeEntry temp = sorted.get( i );
			sorted.set( i, sorted.get( minIndex ) );
			sorted.set( minIndex, temp );
		}
		
		return sorted;
	}
	
    public ArrayList<QuakeEntry> getClosest(ArrayList<QuakeEntry> quakeData, Location current, int howMany) {
        ArrayList<QuakeEntry> ret = new ArrayList<QuakeEntry>();
        
        // Find the closest number of howMany earthquakes from the given location
        // Closest earthquake should be in index 0
        ArrayList<QuakeEntry> sorted = this.sortByDistance( quakeData, current );
        
        // Copy the desired number of values into ret
        for( int i = 0; i < howMany; ++i ) {
        		ret.add( sorted.get( i ) );
        }
        
        return ret;
    }

    public void findClosestQuakes( String source, double lat, double lon, int howMany ) {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size());

        Location jakarta  = new Location(-6.211,106.845);
        Location loc = new Location( lat, lon );
        
        //int howMany = 3;

        ArrayList<QuakeEntry> close = getClosest(list,loc,howMany);
        for(int k=0; k < close.size(); k++){
            QuakeEntry entry = close.get(k);
            double distanceInMeters = jakarta.distanceTo(entry.getLocation());
            System.out.printf("%4.2f\t %s\n", distanceInMeters/1000,entry);
        }
        System.out.println("number found: "+close.size());
    }
    
}
