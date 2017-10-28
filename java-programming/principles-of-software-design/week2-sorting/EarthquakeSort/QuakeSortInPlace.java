package chasemh.java.coursera;

/**
 * Exercise solutions for Assignment: Implementing Selection Sort
 * https://www.coursera.org/learn/java-programming-design-principles/supplement/fA6FH/programming-exercise-implementing-selection-sort
 *
 * @author Chase Hennion
 * @version 2017-10-28
 */
import java.util.*;
import edu.duke.*;

public class QuakeSortInPlace {
    public QuakeSortInPlace() {
        // TODO Auto-generated constructor stub
    }
    
    private boolean checkInSortedOrder( ArrayList<QuakeEntry> quakes ) {
    	
    		for( int i = 0; i < quakes.size() - 1; ++i ) {
			QuakeEntry q1 = quakes.get( i );
			QuakeEntry q2 = quakes.get( i + 1 );
			if( q1.getMagnitude() > q2.getMagnitude() ) {
				// Out of order. The list isn't sorted.
				return false;
			}
		}
    		return true;
    }
    
    private int getLargestDepth( ArrayList<QuakeEntry> quakeData, int from ) {
		// Iterate through quakeData starting from index from and locate the largest depth in the subarray from index from to the end of the array
		int maxDepthIndex = from;
		double maxDepth = quakeData.get( from ).getDepth();
		for( int i = from + 1; i < quakeData.size(); ++i ) {
			double currentDepth = quakeData.get( i ).getDepth();
			if( currentDepth > maxDepth ) {
				maxDepth = currentDepth;
				maxDepthIndex = i;
			}
		}
		
		return maxDepthIndex;
	
}
    
    private void onePassBubbleSort( ArrayList<QuakeEntry> quakeData, int numSorted ) {
	    	// After a pass, the last element in the array is guaranteed to be in order
	    	// So you only need to swap up to size - numSorted
    	
    		for( int i = 0; i < quakeData.size() - numSorted - 1; ++i ) {
    			QuakeEntry q1 = quakeData.get( i );
    			QuakeEntry q2 = quakeData.get( i + 1 );
    			if( q1.getMagnitude() > q2.getMagnitude() ) {
    				// Swap q1 and q2
    				quakeData.set( i, q2 );
    				quakeData.set( i + 1, q1 );
    			}
    		}
    }
    
    public void sortByMagnitudeWithBubbleSortWithCheck( ArrayList<QuakeEntry> quakeData ) {
    		int numSorted = 0;
		while( numSorted < quakeData.size() - 1 ) {
			if( this.checkInSortedOrder( quakeData ) ) {
				// Everything has been sorted.
				// Stop iterating.
				break;
			}
			
			this.onePassBubbleSort( quakeData, numSorted );
			numSorted++;
			/*
			System.out.println( "Result after pass " + numSorted );
			for( QuakeEntry qe : quakeData ) {
				System.out.println( qe );
			}
			*/
		}
		
		System.out.println( "Passes needed: " + numSorted );
    }
    
    public void sortByMagnitudeWithBubbleSort( ArrayList<QuakeEntry> quakeData ) {
    		int numSorted = 0;
    		while( numSorted < quakeData.size() - 1 ) {
    			this.onePassBubbleSort( quakeData, numSorted );
    			numSorted++;
    			/*
    			System.out.println( "Result after pass " + ( numSorted + 1 ) );
    			for( QuakeEntry qe : quakeData ) {
    				System.out.println( qe );
    			}
    			*/
    		}
    }
    
    public void quizSortByLargestDepth( ArrayList<QuakeEntry> in, int numPasses ) {
		// Sort by depth using selection sort
		for( int i = 0; i < numPasses; ++i ) {
			// Find the index of the largest depth
			int maxDepthIndex = this.getLargestDepth( in, i );
			// Swap the largest depth with the value at index i
			QuakeEntry temp = in.get( i );
			in.set( i, in.get( maxDepthIndex) );
			in.set( maxDepthIndex, temp );
		}
}
    
    public void sortByLargestDepth( ArrayList<QuakeEntry> in ) {
    		// Sort by depth using selection sort
    		for( int i = 0; i < in.size() - 1; ++i ) {
    			// Find the index of the largest depth
    			int maxDepthIndex = this.getLargestDepth( in, i );
    			// Swap the largest depth with the value at index i
    			QuakeEntry temp = in.get( i );
    			in.set( i, in.get( maxDepthIndex) );
    			in.set( maxDepthIndex, temp );
    		}
    }
   
    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }
    
    public void sortByMagnitudeWithCheck( ArrayList<QuakeEntry> in ) {
    	
    		int numPasses = 0;
    		for (int i=0; i< in.size(); i++) {
    			if( this.checkInSortedOrder( in ) ) {
    				break;
    			}
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
            numPasses++;
        }
    		System.out.println( "Selection Sort with check required " + numPasses + " passes." );
    }
    
    public void sortByMagnitude(ArrayList<QuakeEntry> in) {
       
    	   int numPasses = 0;
       for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
            numPasses++;
        }
       System.out.println( "Selection Sort witout check required " + numPasses + " passes." );
        
    }

    public void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "data/nov20quakedatasmall.atom";
        //String source = "data/nov20quakedata.atom";
        //String source = "data/earthquakeDataSampleSix2.atom";
        //String source = "data/earthquakeDataSampleSix1.atom";
        String source = "data/earthQuakeDataDec6sample1.atom";
        //String source = "data/earthQuakeDataDec6sample2.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);  
       
        System.out.println("read data for "+list.size()+" quakes");    
        //sortByMagnitude(list);
        //this.sortByLargestDepth( list );
        //this.sortByMagnitudeWithBubbleSort( list );
        //this.sortByMagnitudeWithBubbleSortWithCheck( list );
        
        // Quiz
        //this.quizSortByLargestDepth( list, 50 );
        //this.sortByMagnitudeWithCheck( list );
        this.sortByMagnitudeWithBubbleSortWithCheck( list );
        
        for (QuakeEntry qe: list) { 
            System.out.println(qe);
        } 
        
    }
    
    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "data/earthquakeDataSampleSix2.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
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
