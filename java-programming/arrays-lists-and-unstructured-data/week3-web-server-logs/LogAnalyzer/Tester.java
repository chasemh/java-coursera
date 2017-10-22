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
    
    public void testCountUniqueIP( String fileName ) {
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
    
    public void testCountVisitsPerIP( String fileName ) {
    		LogAnalyzer la = new LogAnalyzer();
    		la.readFile( fileName );
    		HashMap<String,Integer> ipCounts = la.countVisitsPerIP();
    		System.out.println( "Counting Visits per IP for file " + fileName + ":\n=====================================================" );
    		for( String ipAddr : ipCounts.keySet() ) {
    			System.out.println( ipAddr + ": " + ipCounts.get( ipAddr ) );
    		}
    }
    
    public void testMostNumberVisitsByIP( String fileName ) {
    		LogAnalyzer la = new LogAnalyzer();
		la.readFile( fileName );
		HashMap<String,Integer> ipCounts = la.countVisitsPerIP();	
		System.out.println( "Most Number of Visits from a Single IP in " + fileName + " is " + la.mostNumberVisitsByIP( ipCounts ) );
    }
    
    public void testIPsMostVisits( String fileName ) {
    		LogAnalyzer la = new LogAnalyzer();
		la.readFile( fileName );
		HashMap<String,Integer> ipCounts = la.countVisitsPerIP();
		ArrayList<String> maxVisitIPs = la.iPsMostVisits( ipCounts );
		int mostNumberVisits = la.mostNumberVisitsByIP( ipCounts );
		System.out.println( "IPs that visited the site the most number of times (" + mostNumberVisits +" times) in " + fileName + ":\n==================================================================" );
		for( String ipAddr : maxVisitIPs ) {
			System.out.println( ipAddr );
		}
    }
    
    public void testIPsForDays( String fileName ) {
    		LogAnalyzer la = new LogAnalyzer();
		la.readFile( fileName );
		HashMap<String,ArrayList<String>> dayIPMap = la.iPsForDays();
		System.out.println( "IPs that visited site per in " + fileName + ":\n======================================================" );
		for( String day : dayIPMap.keySet() ) {
			System.out.print( day + ": " );
			for( String ipAddr : dayIPMap.get( day ) ) {
				System.out.print( ipAddr + "  " );
			}
			System.out.println();
		}
    }
    
    public  void testDayWithMostIPVisits( String fileName ) {
    		LogAnalyzer la = new LogAnalyzer();
		la.readFile( fileName );
		HashMap<String,ArrayList<String>> dayIPMap = la.iPsForDays();
		String maxDay = la.dayWithMostIPVisits( dayIPMap );
		System.out.println( "The day with the most IP visits in " + fileName + " is " + maxDay + "." );
    }
    
    public void testIPsWithMostVisitsOnDay( String fileName, String day ) {
    		LogAnalyzer la = new LogAnalyzer();
		la.readFile( fileName );
		HashMap<String,ArrayList<String>> dayIPMap = la.iPsForDays();
		ArrayList<String> maxVisitIPs = la.iPsWithMostVisitsOnDay( dayIPMap, day );
		System.out.println( "IPs with the most visits on " + day + " in file " + fileName +":\n===================================================================" );
		for( String ipAddr : maxVisitIPs ) {
			System.out.println( ipAddr );
		}
    }
    
    
    public static void main( String[] args ) {
    		Tester t = new Tester();
    		//t.testLogAnalyzer( "data/short-test_log" );
    		//t.testCountUniqueIP( "data/short-test_log" );
    		//t.testPrintAllHigherThanNum( "data/short-test_log", 200 );
    		//t.testUniqueIPVisitsOnDay( "data/weblog-short_log", "Sep 14" );
    		//t.testUniqueIPVisitsOnDay( "data/weblog-short_log", "Sep 30" );
    		//t.testCountUniqueIPsInRange( "data/short-test_log", 200, 299 );
    		//t.testCountUniqueIPsInRange( "data/short-test_log", 300, 399 );
    		
    		// Practice Quiz 1 Questions
    		//t.testPrintAllHigherThanNum( "data/weblog1_log", 400 );
    		//t.testUniqueIPVisitsOnDay( "data/weblog1_log", "Mar 17" );
    		//t.testCountUniqueIPsInRange( "data/weblog1_log", 300, 399 );
    		
    		//t.testCountVisitsPerIP( "data/short-test_log" );
    		//t.testMostNumberVisitsByIP("data/short-test_log" );
    		//t.testIPsMostVisits( "data/weblog3-short_log" );
    		//t.testIPsForDays( "data/weblog3-short_log" );
    		//t.testDayWithMostIPVisits( "data/weblog3-short_log" );
    		//t.testIPsWithMostVisitsOnDay( "data/weblog3-short_log", "Sep 30"  );
    		
    		// Practice Quiz 2 Questions
    		// 1. Run the method mostNumberVisitsByIP after a HashMap has been created from the method countVisitsPerIP on the file weblog1_log. What number is returned?
    		//t.testMostNumberVisitsByIP( "data/weblog1_log" );
    		// 2. Run the method iPsMostVisits after a HashMap has been created from the method countVisitsPerIP on the file weblog1_log. What single IP address is returned in the ArrayList?
    		//t.testIPsMostVisits( "data/weblog1_log" );
    		// 3. Run the method dayWithMostIPVisits with a HashMap has been created from the method iPsForDays on the file weblog1_log. What day is returned?
    		//t.testDayWithMostIPVisits( "data/weblog1_log" );
    		// 4. Run the method iPsWithMostVisitsOnDay with two parameters—one, a HashMap that has been created from the method iPsForDays on the file weblog1_log and two, the day “Mar 17”.
    		//    One IP Address is returned in the ArrayList—what is it?
    		//t.testIPsWithMostVisitsOnDay ("data/weblog1_log", "Mar 17" );

    		// Section Quiz Questions 
    		// 4. Run the method countUniqueIPs on the file weblog2_log. How many unique IP addresses are in the file?
    		t.testCountUniqueIP( "data/weblog2_log" );
   
    		// 5. Run the method uniqueIPVisitsOnDay(“Sep 27”) on the file weblog2_log. What size is the ArrayList that is returned?
    		t.testUniqueIPVisitsOnDay( "data/weblog2_log", "Sep 27" );
    		
    		// 6. Run the method countUniqueIPsInRange(200,299) on the file weblog2_log. What number is returned?
    		t.testCountUniqueIPsInRange( "data/weblog2_log", 200, 299 );
    		
    		// 7. Run the method mostNumberVisitsByIP after a HashMap has been created from the method countVisitsPerIP on the file weblog2_log. What number is returned?
    		t.testMostNumberVisitsByIP( "data/weblog2_log" );
    		
    		// 8. Run the method iPsMostVisits after a HashMap has been created from the method countVisitsPerIP on the file weblog2_log. What single IP address is returned in the ArrayList?
    		t.testIPsMostVisits( "data/weblog2_log" );
    		
    		// 9. Run the method dayWithMostIPVisits with a HashMap has been created from the method iPsForDays on the file weblog2_log. What day is returned?
    		t.testDayWithMostIPVisits( "data/weblog2_log" );
    		
    		// 10. Run the method iPsWithMostVisitsOnDay with two parameters—one, a HashMap that has been created from the method iPsForDays on the file weblog2_log 
    		//    and two, the day “Sep 30”. Two IP addresses are returned in the ArrayList—which are they?
    		t.testIPsWithMostVisitsOnDay( "data/weblog2_log", "Sep 30" );

















    } 
}
