package chasemh.java.coursera;
import java.util.*;
import edu.duke.*;

/**
 * Exercise solutions Assignment: Searching Earthquake Data
 * https://www.coursera.org/learn/java-programming-design-principles/supplement/3RkuE/programming-exercise-searching-earthquake-data
 *
 * Modified By Chase Hennion
 * @version 2017-10-24
 */
public class EarthQuakeClient {
    public EarthQuakeClient() {
        // TODO Auto-generated constructor stub
    }
    
    public ArrayList<QuakeEntry> filterByPhrase( ArrayList<QuakeEntry> quakeData, String where, String phrase ) {
    		// Get the quakes whose phrase matches the given phrase at the location dictated by where.
    		// Three possible values for where: start, end, any
    		// Start => Phrase must be at beginning of quake info => Needs to be in the start index
    		// End => Phrase must be at the end of quake info => Needs to be in the end index
    		// Any => Phrase must appear somewhere in quake info => Any index in the string is fine
    	
    		if( !where.equals( "start") && !where.equals( "end" ) && !where.equals( "any" ) ) {
    			System.out.println( "Bad Value for where string: " + where );
    			System.out.println( "Valid values are: start, end, any" );
    			return null;
    		}
    		
    		ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
    		for( QuakeEntry qe : quakeData ) {
    			String quakeInfo = qe.getInfo();
    			if( where.equals( "start" ) && quakeInfo.startsWith( phrase ) ) {
    				answer.add( qe );
    			}
    			else if( where.equals( "end" ) && quakeInfo.endsWith( phrase ) ) {
    				answer.add( qe );
    			}
    			else if( where.equals( "any" ) && quakeInfo.contains( phrase ) ) {
    				answer.add( qe );
    			}
    		}
    		
    		return answer;
    }
    
    public ArrayList<QuakeEntry> filterByDepth( ArrayList<QuakeEntry> quakeData, double minDepth, double maxDepth ) {
    		//Get all of the quakes at a depth that is between minDepth and maxDepth, exclusive
    		ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
    		
    		for( QuakeEntry qe : quakeData ) {
    			double quakeDepth = qe.getDepth();
    			if( quakeDepth > minDepth && quakeDepth < maxDepth ) {
    				// Depth is within the desired range 
    				// Save this entry
    				answer.add( qe );
    			}
    		}
    		
    		return answer;
    }

    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData, double magMin ) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        
        // Get all earthquakes with a magnitude larger than magMin
        for( QuakeEntry currentEntry : quakeData ) {
        		double currentMag = currentEntry.getMagnitude();
        		if( currentMag > magMin ) {
        			// Earthquake has a magnitude greater than magMin
        			// Add this earthquake to the answers ArrayList
        			answer.add( currentEntry );
        		}
        }

        return answer;
    }

    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData, double distMax, Location from ) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // Return a list of all the earthquakes that are less than distMax from a given location
        for( QuakeEntry qe : quakeData ) {
        		double distance = from.distanceTo( qe.getLocation() );
        		if( distance < distMax ) {
        			answer.add( qe );
        		}
        }

        return answer;
    }

    public void dumpCSV( ArrayList<QuakeEntry> list ) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }

    }

    public void bigQuakes( String source, double minMag ) {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "data/nov20quakedata.atom";
        //String source = "data/nov20quakedatasmall.atom";
        //String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println( "Read Data for " + list.size() + " earthquakes." );
         
        // Update to use filter by magnitude and print earthquakes above a certain magnitude and the number of such earthquakes
        //double minMag = 5.0;
        ArrayList<QuakeEntry> highMagQuakes = this.filterByMagnitude( list, minMag );
        System.out.println( "Found " + highMagQuakes.size() + " with magnitude higher than " + minMag + "." );
        for( QuakeEntry qe : highMagQuakes ) {
        		System.out.println( qe );
        }

    }

    public void closeToMe( String source, double lat, double lon ){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "data/nov20quakedatasmall.atom";
        //String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        // This location is Durham, NC
        //Location city = new Location(35.988, -78.907);

        // This location is Bridgeport, CA
        //Location city =  new Location(38.17, -118.82);
        
        Location loc = new Location( lat, lon );

        // Use filterByDistance to get earthquakes within 1000 km of a given city
        // For each quake found, print the distance to the city followed by the city info
        double maxDist = 1000.0 * 1000.0; //1000.0;
        ArrayList<QuakeEntry> closeQuakes = this.filterByDistanceFrom( list, maxDist, loc );
        System.out.println( "Found " + closeQuakes.size() + " earthquakes within " + ( maxDist / 1000.0 ) + " km of " + loc );
        for( QuakeEntry qe : closeQuakes ) {
        		double distInKm = loc.distanceTo( qe.getLocation() ) / 1000.0;
        		System.out.println( "Disance in km: " + distInKm + "    " + qe );
        }
    }
    
    public void quakesOfDepth( String source, double minDepth, double maxDepth ) {
    		EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "data/nov20quakedatasmall.atom";
    		//String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        
        //double minDepth = -10000.0;
        //double maxDepth = -5000.0;
        
        ArrayList<QuakeEntry> correctDepthQuakes = this.filterByDepth( list, minDepth, maxDepth );
        System.out.println( "Found " + correctDepthQuakes.size() + " with depth between " + minDepth + " and " + maxDepth );
        for( QuakeEntry qe : correctDepthQuakes ) {
        		System.out.println( qe );
        }
    	
    }
    
    public void quakesByPhrase(String source, String where, String phrase ) {	
    		EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "data/nov20quakedatasmall.atom";
    		//String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        
        //String phrase = "Explosion";
        //String where = "start";
        ArrayList<QuakeEntry> correctPhraseQuakes = this.filterByPhrase( list, where, phrase );
        System.out.println( "Found " + correctPhraseQuakes.size() + " results with " + phrase + " in position " + where );
        for( QuakeEntry qe: correctPhraseQuakes ) {
        		System.out.println( qe );
        }
    	
    }

    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
       // String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }
    
}
