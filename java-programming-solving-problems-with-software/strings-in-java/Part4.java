import edu.duke.*;
/**
 * Java code for the part 4 exercise 
 * Part 4: Finding Web Links
 * https://www.coursera.org/learn/java-programming/supplement/T8W0j/programming-exercise-finding-a-gene-and-web-links
 * 
 * @author Chase Hennion 
 * @version 2017-10-14
 */
public class Part4 {
    
    public static final String URL = "http://www.dukelearntoprogram.com/course2/data/manylinks.html";
    
    public void printYoutubeLinks() {
        // Print out the youtube links found on the page determined by url
        URLResource page = new URLResource( URL );
        
        for( String word : page.words() ) {
            // If the line contains a youtube link, print it
            String sanitized = word.toLowerCase();
            // Find youtube.com
            int index = sanitized.indexOf( "youtube.com" );
            if( index != -1 ) {
                // Found an occurrence
                // Find the quotes around the youtube.com link to capture url
                // Look for the opening quote, should be the last double quote character before
                // you hit the start index of youtube.com
                int openingIndex = sanitized.lastIndexOf( "\"", index );
                
                // Look for the closing quote, should be the first double quote following the 
                // start index of youtube.com
                int closingIndex = sanitized.indexOf( "\"", index );
                
                // Make sure not to include the quotes around the link
                String link = word.substring( openingIndex + 1, closingIndex );
                System.out.println( link );
            }
            
        }
        
        
    }
    
}
