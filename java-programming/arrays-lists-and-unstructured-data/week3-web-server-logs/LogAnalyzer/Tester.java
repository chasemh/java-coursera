import java.util.*;

/**
 * Exercise solutions to Assignment: Reading Web Logs
 * https://www.coursera.org/learn/java-programming-arrays-lists-data/supplement/cAl9o/programming-exercise-reading-log-files 
 *
 * Modified By Chase Hennion
 * @version 2017-10-20
 */
public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer( String fileName ) {
        // complete method
    		// Create a new LogAnalyzer
    		// Call readFile() on short-test_log 
    	 	// Call printAll
    		LogAnalyzer la = new LogAnalyzer();
    		la.readFile( fileName );
    		la.printAll();
    }
    
    public void testUniqueIP( String fileName ) {
    		// This method should create a LogAnalyzer, read from the file short-test_log, and then test the method countUniqueIPs
    		LogAnalyzer la = new LogAnalyzer();
		la.readFile( fileName );
		System.out.println( "Number of Unique IPs in " + fileName +": " + la.countUniqueIPs() );
    }
    
    public void testPrintAllHigherThanNum( String fileName, int num ) {
    		LogAnalyzer la = new LogAnalyzer();
    		la.readFile( fileName );
    		System.out.println( "Testing Log Entries in " + fileName + "with Status Code greater than " + num );
    		la.printAllHigherThanNum( num );
    }
    
    public void testUniqueIPVisitsOnDay( String fileName, String someDay ) {
    		
    		LogAnalyzer la = new LogAnalyzer();
		la.readFile( fileName );
		ArrayList<String> uniqueIPs = la.uniqueIPVisitsOnDay( someDay );
		System.out.println( "Unique IP Addresses for " + someDay + " in file " + fileName + ":\n===============================================" );
		if( uniqueIPs.size() < 50 ) {
			for( String IP : uniqueIPs ) {
				System.out.println( IP );
			}
		}
		System.out.println( "There are " + uniqueIPs.size() + " unique IP addresses for " + someDay );
    }
    
    public void testCountUniqueIPsInRange( String fileName, int low, int high ) {
    		LogAnalyzer la = new LogAnalyzer();
		la.readFile( fileName );
		System.out.println( "The number of unique IPs with status codes between " + low + " and " +  high + ", inclusive, in " + fileName + " is " + la.countUniqueIPsInRange( low, high ) + "." );
    }
    
    
    public static void main( String[] args ) {
    		Tester t = new Tester();
    		//t.testLogAnalyzer( "data/short-test_log" );
    		//t.testUniqueIP( "data/short-test_log" );
    		//t.testPrintAllHigherThanNum( "data/short-test_log", 200 );
    		//t.testUniqueIPVisitsOnDay( "data/weblog-short_log", "Sep 14" );
    		//t.testUniqueIPVisitsOnDay( "data/weblog-short_log", "Sep 30" );
    		//t.testCountUniqueIPsInRange( "data/short-test_log", 200, 299 );
    		//t.testCountUniqueIPsInRange( "data/short-test_log", 300, 399 );
    		
    		// Practice Quiz Questions
    		t.testPrintAllHigherThanNum( "data/weblog1_log", 400 );
    		t.testUniqueIPVisitsOnDay( "data/weblog1_log", "Mar 17" );
    		t.testCountUniqueIPsInRange( "data/weblog1_log", 300, 399 );
    }
}
