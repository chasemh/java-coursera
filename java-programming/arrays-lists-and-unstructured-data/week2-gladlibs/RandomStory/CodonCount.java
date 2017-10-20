package chasemh.java.coursera;

import edu.duke.*;
import java.util.*;

/**
 * Exercise solutions to Assignment 1: Codon Count
 * https://www.coursera.org/learn/java-programming-arrays-lists-data/supplement/RnGtN/programming-exercise-improving-gladlibs 
 *
 * @author Chase Hennion
 * @version 2017-10-20
 */

public class CodonCount {
	
	HashMap<String,Integer> codonMap;
	
	// Constructor
	
	public CodonCount() {
		this.codonMap = new HashMap<String,Integer>();
	}
	
	// Private Methods
	private String getMostCommonCodon() {
		// Get the codon that occurs most often in the codonMap
		
		int currentMax = -1;
		String maxCodon = null;
		
		for( String codon : this.codonMap.keySet() ) {
			int codonValue = this.codonMap.get( codon );
			if( codonValue > currentMax ) {
				currentMax = codonValue;
				maxCodon = codon;
			}
		}
		
		return maxCodon;
	}
 
	
	// Public Methods
	
	public void buildCodonMap( int start, String dna ) {
		// Build a new map of codons based upon the dna and the reading frame offset start
	
		if( start < 0 || start > 2 ) {
			System.out.println( "Bad start value. Value must be 0, 1, or 2." );
			return;
		}
		
		// Sanitize DNA String
		dna = dna.toUpperCase().trim();
		
		this.codonMap.clear();
		
		// While characters are left in the string
			// Get the substring from start to start + 3
			// If the codon substring is already in the map
				// Increment the value for the codon
			// Otherwise
				// Add the new codon substring to the map with an initial value of 1
			// Set start = start + 3 and repeat
		
		for( int i = start; i < dna.length() - 3; i += 3 ) {
			String codon = dna.substring( i, i + 3 );
			if( this.codonMap.containsKey( codon ) ) {
				int currentValue = this.codonMap.get( codon );
				this.codonMap.put( codon , currentValue + 1 );
			}
			else {
				this.codonMap.put( codon , 1 );
			}
		}
	}
	
	public void printCodonCounts( int start, int end ) {
		// Print codons whose counts are >= start and <= end
		
		if( start > end ) {
			System.out.println( "Bad Values! Start value cannot be greater than end value." );
			return;
		}
		
		System.out.println( "Codons that occur between " + start + " and " + end + " times:\n==================================" );
		for( String codon : this.codonMap.keySet() ) {
			int value = this.codonMap.get( codon );
			if( value >= start && value <= end ) {
				System.out.println( codon + "  " + value );
			}
		}
	}
	
	public void tester() {
		FileResource fr = new FileResource();
		String dna = fr.asString();
				
		// Test each reading frame: 0, 1, 2 
		
		// Reading Frame 0
		System.out.println( "Testing Reading Frame 0:\n=====================");
		buildCodonMap( 0, dna );
		printCodonCounts(6, 7);
		System.out.println( "There are " + this.codonMap.size() + " unique codons." );
		String mostCommonCodon0 = getMostCommonCodon();
		System.out.println("\nMost Common Codon is " + mostCommonCodon0 + " with " + this.codonMap.get( mostCommonCodon0 ) + " occurrences.\n" );
		
		// Reading Frame 1
		System.out.println( "Testing Reading Frame 1:\n=====================");
		buildCodonMap( 1, dna );
		printCodonCounts(6, 7);
		System.out.println( "There are " + this.codonMap.size() + " unique codons." );
		String mostCommonCodon1 = getMostCommonCodon();
		System.out.println("\nMost Common Codon is " + mostCommonCodon1 + " with " + this.codonMap.get( mostCommonCodon1 ) + " occurrences." );
		
		// Reading Frame 2
		System.out.println( "Testing Reading Frame 2:\n=====================");
		buildCodonMap( 2, dna );
		printCodonCounts(6, 7);
		System.out.println( "There are " + this.codonMap.size() + " unique codons." );
		String mostCommonCodon2 = getMostCommonCodon();
		System.out.println("\nMost Common Codon is " + mostCommonCodon2 + " with " + this.codonMap.get( mostCommonCodon2 ) + " occurrences." );
		
		
	}

	public static void main(String[] args) {
		CodonCount cc = new CodonCount();
		cc.tester();

	}

}
