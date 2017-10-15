
/**
 * Exercise solutions from part of the second strings assignment
 * Part 3: How Many Genes?
 * https://www.coursera.org/learn/java-programming/supplement/FzhKr/programming-exercise-finding-many-genes
 * 
 * @author Chase Hennion 
 * @version 2017-10-14
 */
public class Part3 {
    
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
            String gene = findGene( dna.substring( startCodonIndex ) );
            startCodonIndex = dna.indexOf( "ATG", startCodonIndex + 1 );
            if( !gene.equals("") ) {
                System.out.println( gene );
            }
        }
       
    }
    
    public int countGenes( String dna ) {
        
        int occurrences = 0;
        int startCodonIndex = dna.indexOf( "ATG" );
        
        while( startCodonIndex != -1 ) {
            String gene = findGene( dna.substring( startCodonIndex ) );
            startCodonIndex = dna.indexOf( "ATG", startCodonIndex + 1 );
            if( !gene.equals("") ) {
                occurrences++;
            }
        }
        return occurrences;
    }
    
    public void testCountGenes() {
        
        String t1 = "ATGxxx";
        String t2 = "ATGxxxTAAATGxxxTGAxxxATGxxxTAG";
        
        System.out.println( "Number of genes in " + t1 + ". Should be 0.");
        System.out.println( "Result: " + countGenes( t1 ) );
        
        System.out.println( "Number of genes in " + t2 + ". Should be 3.");
        System.out.println( "Result: " + countGenes( t2 ) );
    }

}
