import org.apache.commons.csv.*;
import edu.duke.*;
import java.io.*;

public class BabyNames {
	
	// Example Data Line Format
	// Sophia,F,10
	// Index 0 : Baby Name 
	// Index 1: Gender of Name (Always F or M)
	// Index 2: Number of babies given that name
	
	public void printTotalBirths( CSVParser parser ) {
		// Print out:
			// The total number of births
			// The total number of girls names
			// The total number of boys names
			// The total number of name in the file
		// Iterate through each record in the parser
		
		int numGirlNames = 0;
		int numBoyNames = 0;
		int totalBirths = 0;
		int totalNames = 0;
		
		for( CSVRecord rec : parser ) {
			if( rec.get( 1 ).equalsIgnoreCase( "F" ) ) {
				// Found a female name
				numGirlNames++;
			}
			else {
				// Found a male name 
				numBoyNames++;
			}
			totalBirths += Integer.parseInt( rec.get( 2 ) );
		}
		
		totalNames = numGirlNames + numBoyNames;
		
		// Print out desired information
		System.out.println( "Total Number of Births: " + totalBirths );
		System.out.println( "Total Number of Girl Names: " + numGirlNames );
		System.out.println( "Total Number of Boy Names: " + numBoyNames );
		System.out.println( "Total Number of Names in File: " + totalNames );
		
		
	}
	
	public int getRank( int year, String name, String gender ) {
		// Get the rank of the name in a given file for the given gender
		// If the name isn't in the file with correct gender, return -1
		
		// Get the file name path based upon the year
		//  Open a file
		// Get a CSVParser obj from it 
		// Initialize the recordRank to 1;
		// For each record in the parser
		  // If the record's name matches the given name and the record's name matches the given gender
		    // break
		  // Otherwise,
		    // If the record's gender matches the given gender
		      // Increment the record count
		// Return the record count as it is the rank
		
		//String fileName = "data/testing/yob" + year + "short.csv";
		String fileName = "data/us_babynames/us_babynames_by_year/yob" + year + ".csv";
		FileResource fr = new FileResource( fileName );
		CSVParser parser = fr.getCSVParser( false );
		int recRank = 1;
		
		for( CSVRecord rec : parser ) {
			if( rec.get( 0 ).equalsIgnoreCase( name ) && rec.get( 1 ).equalsIgnoreCase( gender ) ) {
				// Found the right name with the right gender
				return recRank;
			}
			else if( rec.get( 1 ).equalsIgnoreCase( gender ) ) {
				// Wrong name but the right gender so increment the recRank and keep looking
				recRank++;
			}
		}
		
		// Couldn't find the name with the desired gender in the file
		return -1;
	}
	
	public CSVRecord getRecordAtRank( int year, int rank, String gender ) {
		
		//String fileName = "data/testing/yob" + year + "short.csv";
		String fileName = "data/us_babynames/us_babynames_by_year/yob" + year + ".csv";
		FileResource fr = new FileResource( fileName );
		CSVParser parser = fr.getCSVParser( false );
		
		int currentRank = 1;
		
		for( CSVRecord rec : parser ) {
			if( currentRank == rank && rec.get( 1 ).equalsIgnoreCase( gender ) ) {
				// Found the right record, right gender with the right ranking
				return rec;
			}
			else if( rec.get( 1 ).equalsIgnoreCase( gender ) ) {
				// Wrong name but the right gender so increment the currentRank and keep looking
				currentRank++;
			}
		}
		
		// Couldn't find the name with the desired gender in the file
		return null;
	}
		
	public void whatIsNameInYear( String name, int year, int newYear, String gender ) {
		// Determines what name would have been if born in newYear
		
		// Determine the rank of the name in year
		int currentRank = getRank( year, name, gender );
		
		if( currentRank != -1 ) {
			CSVRecord newYearRec = getRecordAtRank( newYear, currentRank, gender );
			if( newYearRec != null ) {
				// Found the corresponding record at the same rank.
				// Print out the necessary information
				System.out.println( name + " born in " + year + " would be " + newYearRec.get(0) + " if they were born in " + newYear );
			}
			else {
				System.out.println( "Cannot find a corresponding " + gender + " record of rank " + currentRank + " for year " + newYear + "!" );
			}
		}
		else {
			System.out.println( "Cannot find name " + name + " in year " + year + "!" );
		}
		
	}
	
	public int  getYearFromFileName( File f ) {
		// Get the year integer from the given fileName
		String fullName = f.getName();
		// Replace all non-numeric digit characters with the empty string
		// The remaining values should be the year 
		String yearStr = fullName.replaceAll( "[^0-9]+", "" );
		
		int year = -1;
		
		try {
			
			year = Integer.parseInt( yearStr );
			
		}
		catch( NumberFormatException e ) {
			// Can't cast the year to an int
			System.out.println( "ERROR: Cannot parse int from " + yearStr );
		}
		
		return year;
	}
	
	public int yearOfHighestRank( String name, String gender ) {
		// Select a range of files to process and return the integer which is the year with the highest rank of name with the given gender.
		
		// Create a directory resource to get a collection of files
		DirectoryResource dr = new DirectoryResource();
		
		int highestRankYear = -1;
		// Set the highestRank to the max integer value to start with
		int highestRank = Integer.MAX_VALUE;
		
		// For each selected file
			// Get the year for the file
			// Get the rank for the given name and gender in the year
			// If the rank is lower than the current highest rank  ( Lower Number Value = Higher Rank )
				// Set the highest rank to this rank
				// Set the highest rank year to this year
		
		for( File f : dr.selectedFiles() ) {
			// Get the year from the file name
			int year = getYearFromFileName( f );
			//System.out.println( "Looking at Year " + year );
			int currentRank = getRank( year, name, gender );
			// Need to check if currentRank is -1, which means the name wasn't found
			//System.out.println( "Rank in " + year + " for " + name + " is " + currentRank );
			if( currentRank < highestRank && currentRank != -1 ) {
				highestRank = currentRank;
				highestRankYear = year;
			}
		}
		  
		return highestRankYear;
	}
	
	public double getAverageRank( String name, String gender ) {
		// Get the average rank for the given name and gender over a set of files
		
		// Average starts at 0.0
		// For each selected file
			// Get the year for the file
			// Get the rank for the given name and gender in the year
			// Average the rank over the number of files given
		
		// Create a directory resource to get a collection of files
		
		int totalRank = 0;
		int fileCount = 0;
		
		DirectoryResource dr = new DirectoryResource();
		for( File f : dr.selectedFiles() ) {
			// Get the year from the file name
			int year = getYearFromFileName( f );
			int rank = getRank( year, name, gender );
			if( rank != -1 ) {
				totalRank += rank;
			}
			fileCount++;
		}
		
		if( totalRank > 0 ) {
			return (double)totalRank / (double)fileCount;
		}
		
		return -1.0;
	}
	
	public int getTotalBirthsRankedHigher( int year, String name, String gender ) {
		//Gets the total number of births of the names with the same gender and year that are ranked higher.
		
		// Get the rank of the given name and gender in the given year
		int nameRank = getRank( year, name, gender );
		//System.out.println( "Rank of " + name + " in " + year + " is " + nameRank );
		int totalBirths = 0;
		
		// Get the record at each rank higher than the rank of the given name ( loop from 1 up to rank ) 
		for( int i = 1; i < nameRank; ++i ) {
			//System.out.println( "Name: " + getRecordAtRank( year, i, gender).get( 0 ) );
			//System.out.println( "Number of births to add: "  + getRecordAtRank( year, i, gender).get( 2 ) );
			totalBirths += Integer.parseInt( getRecordAtRank( year, i, gender).get( 2 ) );
		}
		
		return totalBirths;
	}
	
	/*
	 * ============
	 * Test Methods
	 * ============
	 * 
	 */
	
	public void testPrintTotalBirths() {
		FileResource fr = new FileResource();
		CSVParser parser = fr.getCSVParser( false );
		printTotalBirths( parser );
	}
	
	public void testGetRank() {
		System.out.println( "Rank of Ethan in 2012: " + getRank( 2012, "Ethan", "M" ) );
		System.out.println( "Rank of Isabella in 2012: " + getRank( 2012, "Isabella", "F" ) );
		System.out.println( "Rank of Female Ethan in 2012: " + getRank( 2012, "Ethan", "F" ) );
		System.out.println( "Rank of Male Ava in 2012: " + getRank( 2012, "Ava", "M" ) );
	}
	
	public void testWhatIsNameInYear() {
		whatIsNameInYear( "Isabella", 2012, 2014, "F" );
	}
	
	public void testYearOfHighestRank() {
		System.out.println( "Highest Rank of male Mason name from years 2012-2014 was " + yearOfHighestRank( "Mason", "M" ) );
	}
	
	public void testGetAverageRank() {
		System.out.println( "Average Rank of male Mason name from years 2012-2014 was " + getAverageRank( "Mason", "M" ) );
		System.out.println( "Average Rank of male Jacob name form years 2012-2014 was " + getAverageRank( "Jacob", "M" ) );
		System.out.println( "Average Rank of male Ava name from years 2012-2014 was " + getAverageRank( "Ava", "M" ) );
	}
	
	public void testGetTotalBirthsRankedHigher() {
		System.out.println( "Total number of births with name ranked higher than male Ethan for 2012 is " + getTotalBirthsRankedHigher( 2012, "Ethan", "M" ) );
	}
	
	public void quizTester() {
		// 1. What is the number of girls' names in the file yob1900.csv?
		System.out.println( " 1. What is the number of girls' names in the file yob1900.csv?" );
		FileResource fr1 = new FileResource( "data/us_babynames/us_babynames_by_year/yob1900.csv" );
		CSVParser parser1 = fr1.getCSVParser( false );
		printTotalBirths( parser1 );
		
		System.out.println();
		
		// 2. What is the number of boys' names in the file yob1905.csv?
		System.out.println( "2. What is the number of boys' names in the file yob1905.csv?" );
		FileResource fr2 = new FileResource( "data/us_babynames/us_babynames_by_year/yob1905.csv" );
		CSVParser parser2 = fr2.getCSVParser( false );
		printTotalBirths( parser2 );
		
		System.out.println();
		
		// 3. What is the rank of the girl’s name “Emily” in 1960? 
		System.out.println( "3. What is the rank of the girl’s name “Emily” in 1960? " + getRank( 1960, "Emily", "F" )  );
		
		System.out.println();
		
		// 4. What is the rank of the boy’s name “Frank” in 1971?
		System.out.println( "4. What is the rank of the boy’s name “Frank” in 1971? " + getRank( 1971, "Frank", "M" ) );
		
		System.out.println();
		
		// 5. What is the girl’s name of rank 350 in 1980?
		System.out.println( "5. What is the girl’s name of rank 350 in 1980? " + getRecordAtRank( 1980, 350, "F" ).get( 0 ) );
		
		System.out.println();
		
		// 6. What is the boy’s name of rank 450 in 1982?
		System.out.println( "6. What is the boy’s name of rank 450 in 1982? " + getRecordAtRank( 1982, 450, "M" ).get( 0 ) );
		
		System.out.println();
		
		// 7. Suppose Susan was born in 1972. Based on her name's rank in 1972, 
		//    what would her name be if she were born in 2014 (that is, what name in 2014 had the same rank that "Susan" had in 1972)?
		System.out.println( "7. Suppose Susan was born in 1972. Based on her name's rank in 1972, what would her name be if she were born in 2014 (that is, what name in 2014 had the same rank that \"Susan\" had in 1972)? " );
		whatIsNameInYear( "Susan", 1972, 2014, "F" );
		
		System.out.println();
		
		// 8. Suppose Owen was born in 1974. Based on his name's rank in 1974, 
		//    what would his name be if he were born in 2014 (that is, what name in 2014 had the same rank that "Owen" had in 1974)?
		System.out.println( "8. Suppose Owen was born in 1974. Based on his name's rank in 1974, what would his name be if he were born in 2014 (that is, what name in 2014 had the same rank that \"Owen\" had in 1974)? " );
		whatIsNameInYear( "Owen", 1974, 2014, "M" );
		System.out.println();
		
		// 9. In which year from 1880 to 2014 does the girl’s name "Genevieve" have the highest rank (over all the data files)?
		System.out.println( "9. In which year from 1880 to 2014 does the girl’s name \"Genevieve\" have the highest rank (over all the data files)? "  + yearOfHighestRank( "Genevieve", "F") );
		
		System.out.println();
		
		// 10. In which year from 1880 to 2014 does the boy’s name "Mich" have the highest rank (over all the data files)?
		System.out.println( "10. In which year from 1880 to 2014 does the boy’s name \"Mich\" have the highest rank (over all the data files)? "  + yearOfHighestRank( "Mich", "M") );
		
		System.out.println();
		
		// 11. What is the average rank of the girl’s name "Susan" over all the data files?
		System.out.println( "11. What is the average rank of the girl’s name \"Susan\" over all the data files? " + getAverageRank( "Susan", "F" ) );
		
		System.out.println();
		
		// 12. What is the average rank of the boy's name "Robert" over all the data files?
		System.out.println( "12. What is the average rank of the boy's name \"Robert\" over all the data files? " + getAverageRank( "Robert", "M" ) ); 
		
		System.out.println();
		
		// 13. What is the total number of girls born in 1990 with names ranked higher than the girl's name "Emily" in 1990?
		System.out.println( "13. What is the total number of girls born in 1990 with names ranked higher than the girl's name \"Emily\" in 1990? " + getTotalBirthsRankedHigher( 1990, "Emily", "F" ) );
		
		System.out.println();
		
		// 14. What is the total number of boys born in 1990 with names ranked higher than the boy's name "Drew" in 1990?
		System.out.println( "14. What is the total number of boys born in 1990 with names ranked higher than the boy's name \"Drew\" in 1990? " + getTotalBirthsRankedHigher( 1990, "Drew", "M" ) );
		
		System.out.println();
		
	}
	

	public static void main( String[] args ) {
		// TODO Auto-generated method stub
		BabyNames bn = new BabyNames();
		//bn.testPrintTotalBirths();
		//bn.testGetRank();
		//bn.testWhatIsNameInYear();
		//bn.testYearOfHighestRank();
		//bn.testGetAverageRank();
		//bn.testGetTotalBirthsRankedHigher();
		bn.quizTester();
	}

}
