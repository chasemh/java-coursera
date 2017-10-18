
/**
 * Java code for the part 1 exercise 
 * Finding a Gene - Using the Simplified Algorithm
 * https://www.coursera.org/learn/java-programming/supplement/T8W0j/programming-exercise-finding-a-gene-and-web-links
 * 
 * @author Chase Hennion 
 * @version 2017-10-14
 */
public class Part1 { 
    public static final String STARTCODON = "ATG";
    public static final String ENDCODON = "TAA";
    
    
    
    public String findSimpleGene( String dna ) {
        String result = "";
        
        int startIndex = dna.indexOf( STARTCODON );
        if( startIndex == -1 ) {
            // Could not find an instance of the start codon in the DNA string
            return result;
        }
        
        int endIndex = dna.indexOf( ENDCODON, startIndex + STARTCODON.length() );
        if( endIndex == -1 ) {
            // Could not find an instance of the end codon in the DNA string after the start codon
            return result;
        }
        
        result = dna.substring( startIndex, endIndex + ENDCODON.length() );
        
        // Verify that we have a valid gene sequence
        // A valid gene sequence should contain a substring between the start and end codon
        // that has a length that is a multiple of three
        // That is, it contains only complete codons and not partial ones
        if( result.substring( STARTCODON.length() - 1, result.length() - ENDCODON.length() - 1 ).length() % 3 == 0 ) {
            return result;
        }
        
        return "";
    }
    
    public void testSimpleGene() {
        String good1 = "ATGAAATTTCCCTAA";
        String good2 = "ACAATGATATATACATAACGG";
        String noATG = "TAAACAGCTTAA";
        String noTAA = "TACCATGGCCGT";
        String badCodons = "ATGCAGGAGATAA";
        
        System.out.println( "Testing (should pass)" + good1 );
        System.out.println( "Result: " + findSimpleGene( good1 ) );
        
        System.out.println( "Testing (should pass)" + good2 );
        System.out.println( "Result: " + findSimpleGene( good2 ) );
        
        System.out.println( "Testing (should fail)" + noATG );
        System.out.println( "Result: " + findSimpleGene( noATG ) );
        
        System.out.println( "Testing (should fail)" + noTAA );
        System.out.println( "Result: " + findSimpleGene( noTAA ) );
        
        System.out.println( "Testing (should fail)" + badCodons );
        System.out.println( "Result: " + findSimpleGene( badCodons ) );
    }

}
