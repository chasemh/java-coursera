/**
 * Exercise solutions from part 2 of the third strings assignment
 * https://www.coursera.org/learn/java-programming/supplement/ct8gA/programming-exercise-storing-all-genes
 * 
 * @author Chase Hennion 
 * @version 2017-10-14
 */
public class Part2 {
    
    public double cgRatio( String dna ) {
        // Count the number of C's and G's in a dna string 
        // Return the ratio of C's and G's
        // Ratio = (Number of C's + Number of G's) / length of dna
        
        //Sanitize the input
        dna = dna.toLowerCase();
        
        int cgCount = 0;
        char[] characters = dna.toCharArray();
        for( int i = 0; i < characters.length; ++i ) {
            if( characters[ i ] == 'c' || characters[ i ] == 'g' ) {
                cgCount++;
            }
        }
        
        return cgCount / (double)dna.length();
    }
    
    public int countCTG( String dna ) {
        // Count all occurrences of CTG in dna.
        
        // Sanitize Input
        dna = dna.toLowerCase();
        
        int count = 0;
        int currentIndex = dna.indexOf( "ctg" );
        while( currentIndex != -1 ) {
            currentIndex = dna.indexOf( "ctg", currentIndex + 3 );
            count++;
        }
        return count;
    }
    
    //
    // Test Methods
    //
    
    public void testCGRatio() {
        String t1 = "ATGCCATAG";
        
        System.out.println( "Finding CG Ratio for " + t1 + ". Should be .444444 . " );
        System.out.println( "Result: " + cgRatio( t1 ) );
    }
    
    public void testCountCTG() {
        String t1 = "xxxCTGxxx";
        String t2 = "CTGCTGCTG";
        String t3 = "xxxxxxxxx";
        
        System.out.println( "Finding CTG count for " + t1 + ". Should be 1." );
        System.out.println( "Result: " + countCTG( t1 ) );
        
        System.out.println( "Finding CTG count for " + t2 + ". Should be 3." );
        System.out.println( "Result: " + countCTG( t2 ) );
        
        System.out.println( "Finding CTG count for " + t3 + ". Should be 0." );
        System.out.println( "Result: " + countCTG( t3 ) );
    }

}
