import java.util.*;
import edu.duke.*;

/**
 * Exercise solutions to Assignment: Reading Web Logs
 * https://www.coursera.org/learn/java-programming-arrays-lists-data/supplement/cAl9o/programming-exercise-reading-log-files 
 *
 * Modified By Chase Hennion
 * @version 2017-10-20
 */
public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         // complete constructor
    	 	this.records = new ArrayList<LogEntry>();
     }
     
     // Private Methods
     
	     private int maxNumIPVisitsInDay( HashMap<String,Integer> ipCountInDay ) {
	 	 	// Find the maximum number of visits from a single IP in the given map
	 	 	int currentMax = -1;
	 	 	for( String ipAddr : ipCountInDay.keySet() ) {
	 	 		int value = ipCountInDay.get( ipAddr );
	 	 		if( value > currentMax ) {
	 	 			currentMax = value;
	 	 		}
	 	 	}
	 	 	
	 	 	return currentMax;
	  }
  
	  private HashMap<String,Integer> ipVisitsPerDay( HashMap<String,ArrayList<String>> dayIPMap, String day ) {
	 	 
	 	 	HashMap<String,Integer> ipCount = new HashMap<String,Integer>();
	
	 	 	for( String ipAddr : dayIPMap.get( day ) ) {
	 	 		if( ipCount.containsKey( ipAddr ) ) {
	 	 			ipCount.put( ipAddr, ipCount.get( ipAddr ) + 1 );
	 	 		}
	 	 		else {
	 	 			ipCount.put( ipAddr, 1 );
	 	 		}
	 	 	}
	 	 	
	 	 	return ipCount;
	 	 	
	  }
  
	 // Public Methods
        
     public void readFile(String filename) {
         // complete method
    	 	// Read logs line by line from a file
    	 	// For each line,
    	 		// Parse it into a LogEntry
    	 		// Put the LogEntry into records
    	 
    	 	FileResource fr = new FileResource( filename );
    	 	for( String logLine : fr.lines() ) {
    	 		this.records.add( WebLogParser.parseEntry( logLine ) );
    	 	}
    	 	
     }
     
     public int countUniqueIPs() {
    	 	// Count the number of unique IPs in each record 
    	 	// Return the count
    	 
    	 	ArrayList<String> uniqueIps = new ArrayList<String>();
    	 	for( LogEntry le : this.records ) {
    	 		String ipAddr = le.getIpAddress();
    	 		if( !uniqueIps.contains( ipAddr ) ) {
    	 			uniqueIps.add( ipAddr );
    	 		}
    	 	}
    	 	return uniqueIps.size();
     }
     
     public ArrayList<String> uniqueIPVisitsOnDay( String someDay ) {
    	 	// someDay Format: MMM DD
    	 	// Example: Dec 02
    	 	// Look through records for the unique IP addresses that appear for the given day
    	 	// Return an ArrayList containing the unique IPs for the given day
    	 
    	 	ArrayList<String> uniqueIps = new ArrayList<String>();
    	 	for( LogEntry le : records ) {
    	 		// Format Wed Sep 30 06:47:11 CDT 2015
    	 		String logDateStr = le.getAccessTime().toString();
    	 		if( logDateStr.indexOf( someDay ) != -1 ) {
    	 			// The day appears in the date, look at the IP
    	 			String ipAddr = le.getIpAddress();
    	 			if( !uniqueIps.contains( ipAddr ) ) {
    	 				uniqueIps.add( ipAddr );
    	 			}
    	 		}
    	 	}
    	 
    	 	return uniqueIps;
     }
     
     public int countUniqueIPsInRange( int low, int high ) {
    	 	// This method returns the number of unique IP addresses in records that have a status code in the range from low to high, inclusive.
    	 
    	 	ArrayList<String> uniqueIPs = new ArrayList<String>();
    	 	
    	 	if( low <= high ) {
    	 		for( LogEntry le : records ) {
        	 		int statusCode = le.getStatusCode();
        	 		if( statusCode >= low && statusCode <= high ) {
        	 			// Status code is in range. Look at the IP
        	 			String ipAddr = le.getIpAddress();
        	 			if( !uniqueIPs.contains( ipAddr ) ) {
        	 				uniqueIPs.add( ipAddr );
        	 			}
        	 			
        	 		}
        	 	}
    	 	}
    	 	else {
    	 		System.out.println( "Bad Values: The lower status code bound must be less than or equal to the higher bound." );
    	 		return -1;
    	 	}
    	 	
    	 	return uniqueIPs.size();
     }
     
     public HashMap<String,Integer> countVisitsPerIP() {
    	 	// Build a map that maps an IP to the number of times the IP has visited the site
    	 
    	 	HashMap<String,Integer> ipCounts = new HashMap<String,Integer>();
    	 	for( LogEntry le : records ) {
    	 		String ipAddr = le.getIpAddress();
    	 		if( ipCounts.containsKey( ipAddr ) ) {
    	 			// IP already in the map
    	 			// Increment the count for the IP
    	 			ipCounts.put( ipAddr, ipCounts.get( ipAddr ) + 1 );
    	 		}
    	 		else {
    	 			// Haven't seen the IP yet
    	 			// Initialize the count to 1 for the IP
    	 			ipCounts.put( ipAddr, 1 );
    	 		}
    	 	}
    	 	
    	 	
    	 	return ipCounts;
     }
     
     public int mostNumberVisitsByIP( HashMap<String,Integer> ipCounts ) {
    	 	// Returns the max number of visits from one IP in the given HashMap
    	 	int currentMax = -1;
    	 	for( String ipAddr : ipCounts.keySet() ) {
    	 		int ipCount = ipCounts.get( ipAddr );
    	 		if( ipCount > currentMax ) {
    	 			currentMax = ipCount;
    	 		}
    	 	}
    	 	return currentMax;
     }
     
     public ArrayList<String> iPsMostVisits( HashMap<String,Integer> ipCounts ) {
    	 	// Get an ArrayList containing all of the IPs that visited the site the max number of times
    	 	
    	 	int maxVisits = this.mostNumberVisitsByIP( ipCounts );
    	 	ArrayList<String> maxVisitIPs = new ArrayList<String>();
    	 	for( String ipAddr : ipCounts.keySet() ) {
    	 		if( ipCounts.get( ipAddr ) == maxVisits ) {
    	 			maxVisitIPs.add( ipAddr );
    	 		}
    	 	}
    	 	
    	 	return maxVisitIPs;
     }
     
     public HashMap<String,ArrayList<String>> iPsForDays() {
    	 	// Builds a HashMap that maps Days to the list of IPs that visited on that day
    	 	HashMap<String,ArrayList<String>> dayIPMap = new HashMap<String,ArrayList<String>>();
    	 	
    	 	for( LogEntry le : records ) {
    	 		String ipAddr = le.getIpAddress();
    	 		
    	 		// Format Wed Sep 30 06:47:11 CDT 2015
    	 		// Get the correct date string to use
    	 		String dateStr = le.getAccessTime().toString();
    	 		String[] dateComponents = dateStr.split( " " );
    	 		String trueDateStr = dateComponents[ 1 ] + " " + dateComponents[ 2 ];
    	 		
    	 		if( dayIPMap.containsKey( trueDateStr ) ) {
    	 			// Update the array list
    	 			ArrayList<String> al = dayIPMap.get( trueDateStr );
    	 			al.add( ipAddr );
    	 			dayIPMap.put( trueDateStr, al );
    	 		}
    	 		else {
    	 			// Initialize the ArrayList and add the ipAddr to it
    	 			ArrayList<String> al = new ArrayList<String>();
    	 			al.add( ipAddr );
    	 			dayIPMap.put( trueDateStr, al );
    	 		}
    	 	}
    	 	
    	 	return dayIPMap;
     }
     
     public String dayWithMostIPVisits( HashMap<String,ArrayList<String>> dayIPMap ) {
    	 	// Return the day that had the most IP visits
    	 	int currentMax = -1;
    	 	String maxDay = "";
    	 	
    	 	for( String dayStr : dayIPMap.keySet() ) {
    	 		int numIPVisits = dayIPMap.get( dayStr ).size();
    	 		if( numIPVisits > currentMax ) {
    	 			currentMax = numIPVisits;
    	 			maxDay = dayStr;
    	 		}
    	 	}
    	 	
    	 	return maxDay;
     }
     
     public ArrayList<String> iPsWithMostVisitsOnDay( HashMap<String,ArrayList<String>> dayIPMap, String day ) {
    	 	// Return an ArrayList containing the IP addresses with the most accesses on the given day
    	 
    	 	ArrayList<String> ipVisitsOnDay = dayIPMap.get( day );
    	 	ArrayList<String> maxVisitIPs = new ArrayList<String>();
    	 	
    	 	HashMap<String,Integer> ipCountInDay = ipVisitsPerDay( dayIPMap, day );
    	 	int maxNumVisitsInDay = this.maxNumIPVisitsInDay( ipCountInDay );

    	 	for( String ipAddr: ipCountInDay.keySet() ) {
    	 		int ipVisits = ipCountInDay.get( ipAddr );
    	 		if( ipVisits == maxNumVisitsInDay ) {
    	 			maxVisitIPs.add( ipAddr );
    	 		}
    	 	}
    	 
    	 	return maxVisitIPs;
     }
     
     // Printing Methods
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     public void printAllHigherThanNum( int num ) {
    	 	// Look at all the LogEntry record and print those with a status code greater than num
    	 	System.out.println( "Log Entries with Status Codes greater than " + num +":\n==============================================");
    	 	for( LogEntry le : records ) {
    	 		int statusCode = le.getStatusCode();
    	 		if( statusCode > num ) {
    	 			System.out.println( le );
    	 		}
    	 	}
     }
     
     
}
