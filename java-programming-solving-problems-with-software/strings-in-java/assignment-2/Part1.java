
/**
 * Exercise solutions from part of the second strings assignment
 * Part 1: Finding many Genes
 * https://www.coursera.org/learn/java-programming/supplement/FzhKr/programming-exercise-finding-many-genes
 * 
 * @author Chase Hennion 
 * @version 2017-10-14
 */
public class Part1 {
    
    public int findStopCodon( String dna, int startIndex, String stopCodon ) {
        
        // Search through dna for the desired stopCodon
        // If the difference between the index of the stop codon and the start index + 3 is a multiple of 9
        // return the stop codon index
        // Otherwise look for the next index
        // If a properly formed gene string cannot be found, return the length of the dna string
        
        int stopCodonIndex = dna.indexOf( stopCodon, startIndex + 3 );
        while( stopCodonIndex != -1 ) {
            
            if( ( stopCodonIndex - startIndex ) % 3 == 0 ) {
                return stopCodonIndex;
            }
            else {
                stopCodonIndex = dna.indexOf( stopCodon, stopCodonIndex + 1 );
            }
                
        }
        
        return dna.length();
    }
    
    public String findGene( String dna ) {
        
        int startIndex = dna.indexOf("ATG");
        if( startIndex == -1 ) {
            return "";
        }
        
        int taaIndex = findStopCodon( dna, startIndex, "TAA" );
        int tgaIndex = findStopCodon( dna, startIndex, "TGA" );
        int tagIndex = findStopCodon( dna, startIndex, "TAG" );
        
        int minIndex = Math.min( tagIndex, Math.min( taaIndex, tgaIndex ) );
        if( minIndex != dna.length() ) {
            return dna.substring( startIndex, minIndex + 3 );
        }
        
        return "";
    }
    
    public void printAllGenes( String dna ) {
        // Repeatedly call findGene to find all of the genes
        // Find the index of ATG 
        // Pass the substring from ATG to the end of dna to findGene
        // Find the next index of ATG and repeat
        
        int startCodonIndex = dna.indexOf( "ATG" );
        
        while( startCodonIndex != -1 ) {
            System.out.println( findGene( dna.substring( startCodonIndex ) ) );
            startCodonIndex = dna.indexOf( "ATG", startCodonIndex + 1 );
        }
       
    }
    
    public void testFindStopCodon() {
        // Test the findStopCodon method
        
        String t1 = "ATGxxxxxTAAxTAG";
        
        System.out.println( "Looking for ATG-TAA Gene in " + t1 + ". Should return 15" );
        System.out.println( "Result: " + findStopCodon( t1, 0, "TAA" ) );
        
        System.out.println( "Looking for ATG-TGA Gene in " + t1 + ". Should return 15" );
        System.out.println( "Result: " + findStopCodon( t1, 0, "TGA" ) );
        
        System.out.println( "Looking for ATG-TAG Gene in " + t1 + ". Should return 12" );
        System.out.println( "Result: " + findStopCodon( t1, 0, "TAG" ) );
    }
    
    public void testFindGene() {
        
        String t1 = "xxxATGxxxTAATAGTGA";
        String t2 = "ATGxxTAAxTAGTGA";
        String t3 = "xATGxxxxTAGxxxTGAxxTAA";
        String t4 = "ATGxxxxxxx";
        String t5 = "xxxxxxxATGxxxxTAAxxTGAxxTAG";
        
        System.out.println( "Looking for Gene in " + t1 + ". Should return ATGxxxTAA" );
        System.out.println( "Result: " + findGene( t1 ) );
        
        System.out.println( "Looking for Gene in " + t2 + ". Should return ATGxxTAAxTAG" );
        System.out.println( "Result: " + findGene( t2 ) );
        
        System.out.println( "Looking for Gene in " + t3 + ". Should return ATGxxxxTAGxxxTGAxxTAA" );
        System.out.println( "Result: " + findGene( t3 ) );
        
        System.out.println( "Looking for Gene in " + t4 + ". Should return \"\"" );
        System.out.println( "Result: " + findGene( t4 ) );
        
        System.out.println( "Looking for Gene in " + t5 + ". Should return ATGxxxxTAAxxTGA" );
        System.out.println( "Result: " + findGene( t5 ) );
    }
    
    public void testPrintAllGenes() {
        String t1 = "ATGxxx";
        String t2 = "ATGxxxTAAATGxxxTGAxxxATGxxxTAG";
        
        System.out.println( "All genes in " + t1 + ". Should be none.");
        printAllGenes( t1 );
        
        System.out.println( "All genes in " + t2 + ". Should be ATGxxxTAA, ATGxxxTGA, ATGxxxTAG.");
        printAllGenes( t2 );
        
        
    }

}
