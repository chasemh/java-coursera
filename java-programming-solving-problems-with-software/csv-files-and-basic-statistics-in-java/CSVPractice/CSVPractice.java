import edu.duke.*;
import org.apache.commons.csv.*;
/**
 * Exercise solutions from the Parsing Export Data
 * https://www.coursera.org/learn/java-programming/supplement/Qu17T/programming-exercise-parsing-export-data
 *
 * @author Chase Hennion
 * @version 2017-10-16
 */
 public class CSVPractice {

 	public String countryInfo( CSVParser parser, String country ) {
         // Returns string containing
         // Country Name: exports : Export Dollar Value
         // If country isn't found, return "NOT FOUND"
         for( CSVRecord record : parser ) {
           String countryName = record.get( "Country" );
           if( countryName.equalsIgnoreCase( country) ) {
             // Country Found, build out the result string
             return countryName + ": " + record.get( "Exports" ) + ": " + record.get( "Value (dollars)" );
           }
         }

         return "NOT FOUND";
     }

     public void listExportersTwoProducts( CSVParser parser, String exportItem1, String exportItem2 ) {
       // Print out the names of all countries that export both exportItem1 and exportItem2
       for( CSVRecord record : parser ) {
         String exports = record.get( "Exports" );
         if( exports.contains( exportItem1 ) && exports.contains( exportItem2 ) ) {
           System.out.println( record.get( "Country" ) );
         }
       }
     }

     public int numberOfExporters( CSVParser parser, String exportItem ) {
       // Count the number of countries that export the given exportItem
       int count = 0;
       for( CSVRecord record : parser ) {
         String exports = record.get( "Exports" );
         if( exports.contains( exportItem ) ) {
           count++;
         }
       }
       return count;
     }

     public void bigExporters( CSVParser parser, String amount ) {
       // Print out country name and Value whose value string is longer than amount
       for( CSVRecord record : parser ) {
         String currentValue = record.get( "Value (dollars)" );
         if( currentValue.length() > amount.length() ) {
           System.out.println( record.get( "Country" ) + " " + currentValue );
         }
       }

     }

     public void tester() {
         FileResource fr = new FileResource();
         CSVParser parser = fr.getCSVParser();

         // Test countryInfo
         System.out.println( "Testing countryInfo with country = Germany" );
         System.out.println( countryInfo( parser, "Germany") );

         // Test listExportersTwoProducts
         parser = fr.getCSVParser();
         System.out.println( "Testing listExportersTwoProducts with exportItem1 = gold and exportItem2 = diamonds" );
         listExportersTwoProducts( parser, "gold", "diamonds" );

         // Test numberOfExporters
         parser = fr.getCSVParser();
         System.out.println( "Testing numberOfExporters with exportItem = gold" );
         numberOfExporters( parser, "gold" );

         // Test bigExporters
         parser = fr.getCSVParser();
         System.out.println( "Testing bigExporters with amount = $999,999,999" );
         bigExporters( parser, "$999,999,999" );
     }



 	public static void main(String[] args) {
 		CSVPractice practice = new CSVPractice();
 		practice.tester();

 	}

 }
