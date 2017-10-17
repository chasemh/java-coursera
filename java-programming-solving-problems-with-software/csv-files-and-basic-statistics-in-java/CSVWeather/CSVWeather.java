import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
/**
 *  Exercise solutions from the Weather CSV Problem Set
 * https://www.coursera.org/learn/java-programming/supplement/wkC85/programming-exercise-parsing-weather-data
 *
 * @author Chase Hennion
 * @version 2017-10-16
 */
public class CSVWeather {

    public boolean hasLowerValue( CSVRecord rec1, CSVRecord rec2, String csvColumn ) {
        // Return true if rec1 temp is lower or equal to  rec2 temp
        // Return false otherwise

        double rec1Val = 0;
        double rec2Val = 0;

        try {
            rec1Val = Double.parseDouble( rec1.get( csvColumn ) );
        }
        catch( NumberFormatException e) {
            // Can't perform the cast
            // Set rec1 to the largest double possible for comparisons then
            rec1Val = Double.MAX_VALUE;
        }

        try {
            rec2Val = Double.parseDouble( rec2.get( csvColumn ) );
        }
        catch( NumberFormatException e) {
            // Can't perform the cast
            // Set rec2 to the largest double possible for comparisons then
            rec2Val = Double.MAX_VALUE;
        }

        if( rec1Val == -9999 ) {
            return false;
        }
        else if( rec2Val == -9999 ) {
            return true;
        }

        if( rec1Val < rec2Val ) {
            return true;
        }

        return false;
    }

    public CSVRecord getLowerValueRecord( CSVRecord rec1, CSVRecord rec2, String csvColumn ) {

        if( hasLowerValue( rec1, rec2, csvColumn ) ) {
            return rec1;
        }

        return rec2;

    }

    public CSVRecord getLowestValueRecord( CSVParser parser, String csvColumn ) {
        CSVRecord lowestRecord = null;

        for( CSVRecord currentRecord : parser ) {
            if( lowestRecord == null ) {
                lowestRecord = currentRecord;
            }
            else {
                lowestRecord = getLowerValueRecord( currentRecord, lowestRecord, csvColumn );
            }
        }

        return lowestRecord;
    }

    public String fileWithLowestValue( String csvColumn ) {
        DirectoryResource dr = new DirectoryResource();

        CSVRecord lowestRecord = null;
        String lowestFileName = null;

        // Iterate through all of the selected files
        for( File f : dr.selectedFiles() ) {
            // Create a file resource out of the file
            FileResource fr = new FileResource( f );
            CSVRecord currentLowest = getLowestValueRecord( fr.getCSVParser(), csvColumn );
            if( lowestRecord == null ) {
                lowestRecord = currentLowest;
            }
            else {
                if( hasLowerValue( currentLowest, lowestRecord, csvColumn ) ) {
                    lowestRecord = currentLowest;
                    lowestFileName = f.getName();
                }
            }


        }

        return lowestFileName;
    }

    public CSVRecord lowestValueRecord( String csvColumn ) {
        DirectoryResource dr = new DirectoryResource();

        CSVRecord lowestRecord = null;

        // Iterate through all of the selected files
        for( File f : dr.selectedFiles() ) {
            // Create a file resource out of the file
            FileResource fr = new FileResource( f );
            CSVRecord currentLowest = getLowestValueRecord( fr.getCSVParser(), csvColumn );
            if( lowestRecord == null ) {
                lowestRecord = currentLowest;
            }
            else {
                if( hasLowerValue( currentLowest, lowestRecord, csvColumn ) ) {
                    lowestRecord = currentLowest;
                }
            }


        }

        return lowestRecord;
    }

    public double getAverageValue( CSVParser parser, String csvColumn ) {
        // Get the average value of a column in a CSV file
        double total = 0.0;
        int count = 0;

        for( CSVRecord currentRecord : parser ){
            // Get the current value, casted to a double
            // Add the value to total
            // Increment count
            total += Double.parseDouble( currentRecord.get( csvColumn ) );
            count++;
        }

        // Return the average by taking total / count
        return total / (double)count;
    }

    public CSVRecord coldestHourInFile( CSVParser parser ) {

        return getLowestValueRecord( parser, "TemperatureF" );
    }

    public String fileWithColdestTemperature() {

        return fileWithLowestValue( "TemperatureF" );

    }

    public CSVRecord lowestHumidityInFile( CSVParser parser ) {

        return getLowestValueRecord( parser, "Humidity" );
    }

    public CSVRecord lowestHumidityInManyFiles() {

        return lowestValueRecord("Humidity");
    }

    public double averageTemperatureInFile( CSVParser parser ) {

        return getAverageValue( parser, "TemperatureF" );
    }

    public double averageTemperatureWithHighHumidityInFile( CSVParser parser, int value ) {
        // Find the avg temp only for days when humidity > value

        double total = 0.0;
        int count = 0;

        for( CSVRecord record : parser ) {
            String humidityStr = record.get( "Humidity" );
            if( !humidityStr.equalsIgnoreCase("N/A") ) {
                // Safe to cast
                int humidity = Integer.parseInt( humidityStr );
                if( humidity > value ) {
                    total += Double.parseDouble( record.get( "TemperatureF" ) );
                    count++;
                }
            }
        }

        return total / (double)count;
    }

    //
    // Test Methods
    //

    public void testColdestHourInFile() {
        /*
        System.out.println( "Testing weather-2015-01-01.csv. Should get 24.1." );
        FileResource fr1 = new FileResource( "nc_weather/2015/weather-2015-01-01.csv" );
        CSVParser parser1 = fr1.getCSVParser();
        CSVRecord c1 = coldestHourInFile( parser1 );
        System.out.println( "Coldest Temp was " + c1.get("TemperatureF") + " at " + c1.get("TimeEST") );

        System.out.println( "Testing weather-2015-01-02.csv. Should get 41.0." );
        FileResource fr2 = new FileResource( "nc_weather/2015/weather-2015-01-02.csv" );
        CSVParser parser2 = fr2.getCSVParser();
        CSVRecord c2 = coldestHourInFile( parser2 );
        System.out.println( "Coldest Temp was " + c2.get("TemperatureF") + " at " + c2.get("TimeEST") );

        System.out.println( "Testing weather-2015-01-03.csv. Should get 46.0." );
        FileResource fr3 = new FileResource( "nc_weather/2015/weather-2015-01-03.csv" );
        CSVParser parser3 = fr3.getCSVParser();
        CSVRecord c3 = coldestHourInFile( parser3 );
        System.out.println( "Coldest Temp was " + c3.get("TemperatureF") + " at " + c3.get("TimeEST") );
        */
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = coldestHourInFile(parser);
        System.out.println( "Coldest Temp was " + csv.get("TemperatureF") + " at " + csv.get("DateUTC") );
    }

    public void testFileWithColdestTemperature( String year ) {
        String coldestFileName = "nc_weather/" + year + "/" + fileWithColdestTemperature();
        FileResource fr = new FileResource( coldestFileName );
        CSVParser parser = fr.getCSVParser();
        CSVRecord coldestRecord = coldestHourInFile( parser );

        System.out.println( "Coldest Day was in file " + coldestFileName );
        System.out.println( "Coldest Temp on that day was " + coldestRecord.get( "TemperatureF" ) );
        System.out.println( "All of the temperatures on the coldest day were:" );

        parser = fr.getCSVParser();
        for( CSVRecord record : parser ) {
            System.out.println( record.get( "DateUTC" ) + " " + record.get( "TemperatureF" ) );
        }
    }

    public void testLowestHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        System.out.println( "Lowest Humidity was " + csv.get("Humidity") + " at " + csv.get("DateUTC") );
    }

    public void testLowestHumidityInManyFiles() {
        CSVRecord csv = lowestHumidityInManyFiles();
        System.out.println( "Lowest Humidity was " + csv.get("Humidity") + " at " + csv.get( "DateUTC" ) );
    }

    public void testAverageTemperatureInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double avg = averageTemperatureInFile( parser );
        System.out.println( "Average Temperature in file is " + avg );
    }

    public void testAverageTemperatureWithHighHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double avg = averageTemperatureWithHighHumidityInFile( parser, 80 );
        System.out.println( "Average Temperature when high humidity is " + avg );
    }

}
