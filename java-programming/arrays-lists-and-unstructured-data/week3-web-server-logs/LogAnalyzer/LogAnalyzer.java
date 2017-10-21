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
