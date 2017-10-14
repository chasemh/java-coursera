
/**
 * Java code for the part 2 exercise 
 * Finding a Gene - Using the Simplified Algorithm Reorganized
 * https://www.coursera.org/learn/java-programming/supplement/T8W0j/programming-exercise-finding-a-gene-and-web-links
 * 
 * @author Chase Hennion 
 * @version 2017-10-14
 */
public class Part2 {
    
    public String findSimpleGene( String dna, String startCodon, String endCodon ) {
        String result = "";
        
        // Sanitize the input
        if( isUpperCaseString( dna ) ) {
            // Make the search codons uppercase
            startCodon = startCodon.toUpperCase();
            endCodon = endCodon.toUpperCase();
        }
        else if( isLowerCaseString( dna ) ) {
            // Make the search codons lowercase
            startCodon = startCodon.toLowerCase();
            endCodon = endCodon.toLowerCase();
        }
        else {
            // Input is mixture of upper and lowercase
            // Sanitize everything by making it all uppercase
            startCodon = startCodon.toUpperCase();
            endCodon = endCodon.toUpperCase();
            dna = dna.toUpperCase();
        }
        
        int startIndex = dna.indexOf( startCodon );
        if( startIndex == -1 ) {
            // Could not find an instance of the start codon in the DNA string
            return result;
        }
        
        int endIndex = dna.indexOf( endCodon, startIndex + startCodon.length() );
        if( endIndex == -1 ) {
            // Could not find an instance of the end codon in the DNA string after the start codon
            return result;
        }
        
        result = dna.substring( startIndex, endIndex + endCodon.length() );
        
        // Verify that we have a valid gene sequence
        // A valid gene sequence should contain a substring between the start and end codon
        // that has a length that is a multiple of three
        // That is, it contains only complete codons and not partial ones
        if( result.substring( startCodon.length() - 1, result.length() - endCodon.length() - 1 ).length() % 3 == 0 ) {
            return result;
        }
        
        return "";
    }
    
    public boolean isUpperCaseString( String s ) {
        // A string must be all uppercase if the orignal string matches the uppercased string
        return s.equals( s.toUpperCase() );
    }
    
    public boolean isLowerCaseString( String s ) {
        // A string must be all lowercase if the orignal string matches the lowercased string 
        return s.equals( s.toLowerCase() );
    }
    
    public void testSimpleGene() {
        String startCodon = "ATG";
        String endCodon = "TAA";
        
        String good1 = "ATGAAATTTCCCTAA";
        String good2 = "ACAATGATATATACATAACGG";
        String noATG = "TAAACAGCTTAA";
        String noTAA = "TACCATGGCCGT";
        String badCodons = "ATGCAGGAGATAA";
        String allLowerCase = "atgaaatttccctaa";
        String mixCase = "AtGaAAtTTCcctAa";
        
        System.out.println( "Testing (should pass)" + good1 );
        System.out.println( "Result: " + findSimpleGene( good1, startCodon, endCodon ) );
        
        System.out.println( "Testing (should pass)" + good2 );
        System.out.println( "Result: " + findSimpleGene( good2, startCodon, endCodon ) );
        
        System.out.println( "Testing (should fail)" + noATG );
        System.out.println( "Result: " + findSimpleGene( noATG, startCodon, endCodon ) );
        
        System.out.println( "Testing (should fail)" + noTAA );
        System.out.println( "Result: " + findSimpleGene( noTAA, startCodon, endCodon ) );
        
        System.out.println( "Testing (should fail)" + badCodons );
        System.out.println( "Result: " + findSimpleGene( badCodons, startCodon, endCodon ) );
        
        System.out.println( "Testing (should pass)" + allLowerCase );
        System.out.println( "Result: " + findSimpleGene( allLowerCase, startCodon, endCodon ) );
        
        System.out.println( "Testing (should pass)" + mixCase );
        System.out.println( "Result: " + findSimpleGene( mixCase, startCodon, endCodon ) );
    }

}
