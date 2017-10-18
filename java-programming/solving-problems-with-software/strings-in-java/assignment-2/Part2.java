
/**
 * Exercise solutions from part of the second strings assignment
 * Part 2: HowMany - Finding Multiple Occurrences
 * https://www.coursera.org/learn/java-programming/supplement/FzhKr/programming-exercise-finding-many-genes
 * 
 * @author Chase Hennion 
 * @version 2017-10-14
 */
public class Part2 {
    
    public int howMany( String stringa, String stringb ) {
        // Find how many times stringa appears in stringb
        
        int occurrences = 0;
        int occurrenceIndex = stringb.indexOf( stringa );
        while( occurrenceIndex != -1 ) {
            //Find the next occurrence of the string
            occurrenceIndex = stringb.indexOf( stringa, occurrenceIndex + 1 );
            occurrences++;
        }
        
        return occurrences;
    }
    
    public void testHowMany() {
        String a1 = "an";
        String b1 = "bananas";
        
        String a2 = "ip";
        String b2 = "starship";
        
        String a3 = "nope";
        String b3 = "bluebird";
        
        System.out.println( "Looking for the number of times " + a1 + " occurs in " + b1 + ". Should be 2.");
        System.out.println( "Result: " + howMany( a1, b1 ) );
        
        System.out.println( "Looking for the number of times " + a2 + " occurs in " + b2 + ". Should be 1.");
        System.out.println( "Result: " + howMany( a2, b2 ) );
        
        System.out.println( "Looking for the number of times " + a3 + " occurs in " + b3 + ". Should be 0.");
        System.out.println( "Result: " + howMany( a3, b3 ) );
        
    }

}
