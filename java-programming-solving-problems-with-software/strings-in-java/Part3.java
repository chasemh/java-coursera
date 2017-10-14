
/**
 * Java code for the part 3 exercise 
 * Part 3: Problem Solving with Strings
 * https://www.coursera.org/learn/java-programming/supplement/T8W0j/programming-exercise-finding-a-gene-and-web-links
 * 
 * @author Chase Hennion 
 * @version 2017-10-14
 */
public class Part3 {
    
    public boolean twoOccurrences( String stringa, String stringb ) {
        // Return true if stringa occurs two or more times in stringb
        int occurrences = 0;
        int currentIndex = 0; 
        while( currentIndex != -1 && currentIndex < stringb.length() ) {
            // Look for the substring stringb in stringa starting at currentIndex
            currentIndex = stringb.indexOf( stringa, currentIndex );
            if( currentIndex != -1 ) {
                occurrences++;
                // Set currentIndex to the index in the string right after the occurrence of stringb
                currentIndex += stringa.length();
            }
        }
        
        if( occurrences >= 2 ) {
            return true;
        }
        return false;
    }
    
    public String lastPart( String stringa, String stringb ) {
        // Find first occurrence of stringa in stringb
        // The returns the part of the string that follows the first occurrence of stringb
        // Omit stringa from the remainder
        // If an occurrence is not found, return stringb
        
        int index = stringb.indexOf( stringa );
        if( index == -1 ) {
            return stringb;
        }
        
        // Recall that the last index passed to the substring is exclusive
        return stringb.substring( index + stringa.length(), stringb.length() );
        
    }
    
    public void testing() {
        // Test the twoOccurences Method
        String search1 = "bananas";
        String find1 = "an";
        String search2 = "hello";
        String find2 = "l";
        String search3 = "goodbye";
        String find3 = "ood";
        
        System.out.println( "Looking for " + find1 + " in " + search1 + ". Should pass." );
        System.out.println( "Result: " + twoOccurrences( find1, search1 ) );
        
        System.out.println( "Looking for " + find2 + " in " + search2 + ". Should pass." );
        System.out.println( "Result: " + twoOccurrences( find2, search2 ) );
        
        System.out.println( "Looking for " + find3 + " in " + search3 + ". Should fail." );
        System.out.println( "Result: " + twoOccurrences( find3, search3 ) );
        
        // Test the lastPart Method 
        String a1 = "an";
        String b1 = "bananas";
        String a2 = "ood";
        String b2 = "goodbye";
        String a3 = "bad";
        String b3 = "sandwich";
        
        System.out.println( "Looking for " + a1 + " in " + b1 + ". Should pass." );
        System.out.println( "Result: " + lastPart( a1, b1 ) );
        
        System.out.println( "Looking for " + a2 + " in " + b2 + ". Should pass." );
        System.out.println( "Result: " + lastPart( a2, b2 ) );
        
        System.out.println( "Looking for " + a3 + " in " + b3 + ". Should fail." );
        System.out.println( "Result: " + lastPart( a3, b3 ) );
    }
    
}
