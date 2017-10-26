package chasemh.java.coursera;
import java.util.*;
import edu.duke.*;

/**
 * Exercise solutions for Assignment: Filtering Data
 * https://www.coursera.org/learn/java-programming-design-principles/supplement/yzFlm/programming-exercise-filtering-data
 *
 * Modified by Chase Hennion
 * @version 2017-10-25
 */
public class EarthQuakeClient2 {
    public EarthQuakeClient2() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) { 
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) { 
            if (f.satisfies(qe)) { 
                answer.add(qe); 
            } 
        } 
        
        return answer;
    } 

    public void quakesWithFilter() { 
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "data/nov20quakedatasmall.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");

        /*
        Filter f = new MinMagFilter(4.0); 
        ArrayList<QuakeEntry> m7  = filter(list, f); 
        for (QuakeEntry qe: m7) { 
            System.out.println(qe);
        } 
        */
        
        
        // Filter on magnitude between 4.0 and 5.0 
        // Then filter on depth between -35000.0 and -12000.0
        // Then print the result
        /*
        Filter magFilter = new MagnitudeFilter( 4.0, 5.0, "Mag Filter" );
        Filter depthFilter = new DepthFilter( -35000.0, -12000.0, "Depth Filter" );
        ArrayList<QuakeEntry> validQuakes = filter( filter( list, magFilter ), depthFilter );
        System.out.println( "Filtered down to " + validQuakes.size() + " earthquakes." );
        for( QuakeEntry qe : validQuakes ) {
        		System.out.println( qe );
        }
        */
        
        
        // Filter on 10,000,000 meters from Tokyo
        // Filter on earthquakes that are in Japan ( Japan is last word in quake info )
        /*
        Location tokyo = new Location( 35.42, 139.43 );
        Filter distFilter = new DistanceFilter( tokyo, 10000000.0, "Disance Filter" );
        Filter phraseFilter = new PhraseFilter( "end", "Japan", "Phrase Filter" );
        ArrayList<QuakeEntry> validQuakes = this.filter( this.filter( list, distFilter ), phraseFilter );
        System.out.println( "Filtered down to " + validQuakes.size() + " earthquakes." );
        for( QuakeEntry qe : validQuakes ) {
        		System.out.println( qe );
        }
        */
        
        // Quiz
        // Filter on 1000000m from Denver, CO (39.7392, -104.9903)
        // Filter on end with 'a'
        Location denver = new Location( 39.7392, -104.9903 );
        Filter distFilter = new DistanceFilter( denver, 1000000, "Dist Filter" );
        Filter phraseFilter = new PhraseFilter( "end", "a", "Phrase Filter" );
        
        ArrayList<QuakeEntry> validQuakes = this.filter( this.filter( list, distFilter ), phraseFilter );
        System.out.println( "Filtered down to " + validQuakes.size() + " earthquakes." );
        if( validQuakes.size() < 20 ) {
		    	for( QuakeEntry qe : validQuakes ) {
		    		System.out.println( qe );
		    }
        }
        
        // Quiz
        // Filter on magnitude between 3.5 and 4.5 inclusive
        // Filter on depth between -55,000.0 and -20,000.0 inclusive
        // Filter on the letter “o” is in the title
        /*
        Filter magFilter = new MagnitudeFilter( 3.5, 4.5, "Mag Filter" );
        Filter depthFilter = new DepthFilter( -55000.0, -20000.0, "Depth Filter" );
        
        ArrayList<QuakeEntry> validQuakes = this.filter( this.filter( list, magFilter ), depthFilter );
        System.out.println( "Filtered down to " + validQuakes.size() + " earthquakes." );
        if( validQuakes.size() < 20 ) {
		    	for( QuakeEntry qe : validQuakes ) {
		    		System.out.println( qe );
		    }
        }
        */
    }
    
    public void testMatchAllFilter() {
    		EarthQuakeParser parser = new EarthQuakeParser(); 
    		//String source = "data/nov20quakedatasmall.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");
        
        
        MatchAllFilter maf = new MatchAllFilter();
        
        /*
        Filter magFilter = new MagnitudeFilter( 0.0, 2.0, "Mag Filter" );
        Filter depthFilter = new DepthFilter( -100000.0, -10000.0, "Depth Filter" );
        Filter phraseFilter = new PhraseFilter( "any", "a", "Phrase Filter" );
        maf.addFilter( magFilter );
        maf.addFilter( depthFilter );
        maf.addFilter( phraseFilter );
        */
        
        // Quiz
        Filter magFilter = new MagnitudeFilter( 1.0, 4.0, "Mag Filter" );
        Filter depthFilter = new DepthFilter( -180000.0, -30000.0, "Depth Filter" );
        Filter phraseFilter = new PhraseFilter( "any", "o", "Phrase Filter" );
        maf.addFilter( magFilter );
        maf.addFilter( depthFilter );
        maf.addFilter( phraseFilter );
        
        ArrayList<QuakeEntry> validQuakes = this.filter( list, maf );
        System.out.println( "Filtered down to " + validQuakes.size() + " earthquakes." );
        if( validQuakes.size() < 20 ) {
		    	for( QuakeEntry qe : validQuakes ) {
		    		System.out.println( qe );
		    }
        }
        System.out.println( "Filters used: " + maf.getName() );
    }
    
    public void testMatchAllFilter2() {
		EarthQuakeParser parser = new EarthQuakeParser(); 
		//String source = "data/nov20quakedatasmall.atom";
        String source = "data/nov20quakedata.atom";
	    ArrayList<QuakeEntry> list  = parser.read(source);         
	    System.out.println("read data for "+list.size()+" quakes");
	    
	    Location tulsa = new Location(36.1314, -95.9372);
	    Location billund = new Location( 55.7308, 9.1153 );
	    
	    MatchAllFilter maf = new MatchAllFilter();
	    /*
	    Filter magFilter = new MagnitudeFilter( 0.0, 3.0, "Mag Filter" );
	    Filter distFilter = new DistanceFilter( tulsa, 10000000, "Dist Filter" );
	    Filter phraseFilter = new PhraseFilter( "any", "Ca", "Phrase Filter" );
	    maf.addFilter( magFilter );
	    maf.addFilter( distFilter );
	    maf.addFilter( phraseFilter );
	    */
	    
	    // Quiz
	    Filter magFilter = new MagnitudeFilter( 0.0, 5.0, "Mag Filter" );
	    Filter distFilter = new DistanceFilter( billund, 3000000.0, "Dist Filter" );
	    Filter phraseFilter = new PhraseFilter( "any", "e", "Phrase Filter" );
	    maf.addFilter( magFilter );
	    maf.addFilter( distFilter );
	    maf.addFilter( phraseFilter );
	    
	    ArrayList<QuakeEntry> validQuakes = this.filter( list, maf );
	    System.out.println( "Filtered down to " + validQuakes.size() + " earthquakes." );
	    
	    if( validQuakes.size() < 20 ) {
		    	for( QuakeEntry qe : validQuakes ) {
		    		System.out.println( qe );
		    }
	    }
	    
	    System.out.println( "Filters used: " + maf.getName() );
	    
    }

    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
      //String source = "data/nov20quakedatasmall.atom";
        String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: "+list.size());
    }

    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }
    }

}
