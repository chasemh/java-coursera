import edu.duke.*;
/**
 * Exercise solutions from part 1 of the third strings assignment
 * https://www.coursera.org/learn/java-programming/supplement/ct8gA/programming-exercise-storing-all-genes
 * 
 * @author Chase Hennion 
 * @version 2017-10-14
 */
public class Part1 {
    
    public int getPositiveMinimum( int a, int b ) {
        
        // If a is positive and either a is less than b or b is negative, return a
        // Otherwise, if b is positive and either b is less than a or a is negative, return b
        // If both are negative, return -1
        
        // Use <= here to cover the case when a == b
        if( a >= 0 && ( a <= b || b < 0 ) ){
            return a;
        }
        else if( b >=0 && ( b < a || a < 0 ) ) { 
            return b;
        }
        
        // Both numbers must be less than 0
        return -1;
    }
    
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
        
        //return dna.length();
        return -1;
    }
    
    public String findGene( String dna ) {
        
        int startIndex = dna.indexOf("ATG");
        if( startIndex == -1 ) {
            return "";
        }
        
        int taaIndex = findStopCodon( dna, startIndex, "TAA" );
        int tgaIndex = findStopCodon( dna, startIndex, "TGA" );
        int tagIndex = findStopCodon( dna, startIndex, "TAG" );
        
        // Older method when returning dna.length() when no index is found
        /* int minIndex = Math.min( tagIndex, Math.min( taaIndex, tgaIndex ) );
        if( minIndex != dna.length() ) {
            return dna.substring( startIndex, minIndex + 3 );
        }
        */
       
       // New method when returning -1 when no index is found 
       // Find the minimum, positive index out of taaIndex, tgaIndex, tagIndex
       // If the minimum isn't -1, get the substring from the startIndex to the minIndex + 3
       // Otherwise, return the empty string
      
       int minIndex = getPositiveMinimum( getPositiveMinimum( taaIndex, tgaIndex ), tagIndex );
       if( minIndex != -1 ) {
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
    
     public StorageResource getAllGenes( String dna ) {
        // Repeatedly call findGene to find all of the genes
        // Find the index of ATG 
        // Pass the substring from ATG to the end of dna to findGene
        // Find the next index of ATG and repeat
        
        int startCodonIndex = dna.indexOf( "ATG" );
        StorageResource genes = new StorageResource();
        
        while( startCodonIndex != -1 ) {
            String gene = findGene( dna.substring( startCodonIndex ) );
            if( gene.isEmpty() ) {
                break;
            }
            genes.add( gene );
            startCodonIndex = dna.indexOf( "ATG", startCodonIndex + 1 );
        }
        
        return genes;
       
    }
    
    
    //
    // Testing Methods
    //
    
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
    
    public void testGetPositiveMinimum() {
        int a1 = 1;
        int b1 = 2;
        
        int a2 = 0;
        int b2 = -1;
        
        int a3 = -2;
        int b3 = -3;
        
        int a4 = 10;
        int b4 = 10;
        
        System.out.println( "Finding positive minimum of " + a1 + " and " + b1 + ". Should be " + a1 + ".");
        System.out.println( "Result: " + getPositiveMinimum( a1, b1 ) );
        
        System.out.println( "Finding positive minimum of " + a2 + " and " + b2 + ". Should be " + a2 + ".");
        System.out.println( "Result: " + getPositiveMinimum( a2, b2 ) );
        
        System.out.println( "Finding positive minimum of " + a3 + " and " + b3 + ". Should be -1." );
        System.out.println( "Result: " + getPositiveMinimum( a3, b3 ) );
        
        System.out.println( "Finding positive minimum of " + a4 + " and " + b4 + ". Should be " + a4 + "." );
        System.out.println( "Result: " + getPositiveMinimum( a4, b4 ) );
    }
    
    public void testGetAllGenes() {
        String t1 = "ATGxxx";
        String t2 = "ATGxxxTAAATGxxxTGAxxxATGxxxTAG";
        
        System.out.println( "Getting all genes in " + t1 + ". Should be none.");
        StorageResource t1Genes = getAllGenes( t1 );
        int t1NumGenes = 0;
        for( String gene : t1Genes.data() ){
            System.out.println( gene );
            t1NumGenes++;
        }
        System.out.println( t1 + " contains " + t1NumGenes + " genes. Should be 0." ); 
        
        System.out.println( "Getting all genes in " + t2 + ". Should be ATGxxxTAA, ATGxxxTGA, ATGxxxTAG..");
        StorageResource t2Genes = getAllGenes( t2 );
        int t2NumGenes = 0;
        for( String gene : t2Genes.data() ){
            System.out.println( gene );
            t2NumGenes++;
        }
        System.out.println( t1 + " contains " + t2NumGenes + " genes. Should be 3." );
        
        
    }

}