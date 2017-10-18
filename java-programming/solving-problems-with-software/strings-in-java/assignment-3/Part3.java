import edu.duke.*;
/**
 * Exercise solutions from part 3 of the third strings assignment
 * https://www.coursera.org/learn/java-programming/supplement/ct8gA/programming-exercise-storing-all-genes
 * 
 * @author Chase Hennion 
 * @version 2017-10-14
 */
public class Part3 {
    
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
        
        // Sanitize the input
        dna = dna.toUpperCase();
        
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
    
     public StorageResource getAllGenes( String dna ) {
        // Repeatedly call findGene to find all of the genes
        // Find the index of ATG 
        // Pass the substring from ATG to the end of dna to findGene
        // Find the next index of ATG and repeat
        
        // Sanitize the input
        dna = dna.toUpperCase();
        
        int startCodonIndex = dna.indexOf( "ATG" );
        StorageResource genes = new StorageResource();
        
        while( true ) {
            String gene = findGene( dna.substring( startCodonIndex ) );
            if( gene.isEmpty() ) {
                break;
            }
            genes.add( gene );
            
            // Start looking for the next gene at the index in the dna string right after the current gene
            // To do this, find the index of the current gene in the string starting at the current index
            // of the start codon. Then add the length of the gene we found to this index.
            // The resultant value should yield the index right after the current gene ends in the dna string.
            startCodonIndex = dna.indexOf( gene, startCodonIndex ) + gene.length();
        }
        
        return genes;
       
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
        
        // Be careful with integer division 
        // Need to make sure that one of the values is a floating point number before performing division
        // If the double cast is around the entire expression, then the floating point portion of the quotient
        // will already be lost before the cast.
        return (double)cgCount / dna.length();
    }
    
    public void processGenes( StorageResource sr ) {
        // Print all strings in sr that are longer than 9 characters and the number of these strings
        // Print the strings in sr whose CG ratio is higher than 0.35 and the number of these strings
        // Print the longest string in sr
        
        String longest = "";
        StorageResource highCharStrings = new StorageResource();
        StorageResource highCGRatioStrings = new StorageResource();
        
        // Populate the new StorageResources with the appropriate strings
        for( String gene : sr.data() ) {
            int geneLength = gene.length();
            if( geneLength > 60 ) {
                highCharStrings.add( gene );
            }
            if( cgRatio( gene ) > 0.35 ) {
                highCGRatioStrings.add( gene );
            }
            if( geneLength > longest.length() ) {
                longest = gene;
            }
        }
        
        // Print out the desired information
        System.out.println( "More than Sixty Character Strings: \n ===========================" );
        for( String gene : highCharStrings.data() ) {
            System.out.println( gene );
        }
        System.out.println( "Number of more than Sixty Char Strings: " + highCharStrings.size() );
        
        System.out.println( "CG Ratio > 0.35 Strings: \n ===========================" );
        for( String gene : highCGRatioStrings.data() ) {
            System.out.println( gene );
        }
        System.out.println( "Number of CG Ratio > 0.35 Strings: " + highCGRatioStrings.size() );
        
        System.out.println( "Longest String Length: " + longest.length() );
    }
    
    public void testProcessGenes() {
        
        String t1 = "ATGxxx";
        String t2 = "ATGxxxTAAATGxxxTGAxxxATGxxxTAG";
        String t3 = "ATGxxCCCCxxxxxxTAACCCCGGGG";
        String t4 = "ATGxxxTAAxxxATGxxxTAG";
        String t5 = "xxxATGGGGCCCCGGGGGTGACCCC";
        
        System.out.println( "Testing string " + t1 + "." );
        processGenes( getAllGenes( t1 ) );
        
        System.out.println( "Testing string " + t2 + "." );
        processGenes( getAllGenes( t2 ) );
        
        System.out.println( "Testing string " + t3 + "." );
        processGenes( getAllGenes( t3 ) );
        
        System.out.println( "Testing string " + t4 + "." );
        processGenes( getAllGenes( t4 ) );
        
        System.out.println( "Testing string " + t5 + "." );
        processGenes( getAllGenes( t5 ) );
    
    }
    
    public void testRealDNA() {
        testDNAFile("brca1line.fa");
    }
    
    public void testQuizDNA() {
        testDNAFile( "GRch38dnapart.fa" );
    }
    
    public void testDNAFile( String fileName ) {
        FileResource fr = new FileResource( fileName );
        String dna = fr.asString();
        StorageResource genes = getAllGenes( dna );
        System.out.println( "Testing DNA in file " + fileName + "\n==============================" );
        System.out.println( "Total Number of Genes: " + genes.size() );
        processGenes( genes );
        System.out.println( "Count CTG Result: " + countCTG( dna ) );
    }

}
