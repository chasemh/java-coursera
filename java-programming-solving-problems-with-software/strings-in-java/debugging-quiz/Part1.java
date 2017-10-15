
/**
 * Code for the first quiz in the Debugging Section
 * https://www.coursera.org/learn/java-programming/quiz/zYkbA/debugging-part-1
 * 
 * @author Chase Hennion 
 * @version 2017-10-15
 */
public class Part1 {
    
   public void findAbc(String input) {
        int index = input.indexOf("abc");
        //System.out.println( "Input is " + input );
        //System.out.println( "Input has length " + input.length() );
        while (true) {
            if (index == -1) {
                break;
            }
            // Debugging 
            int substringStartIndex = index + 1;
            int substringEndIndex = index + 4;
            //System.out.println( "Index + 1 = " + substringStartIndex );
            //System.out.println( "Index + 4 = " + substringEndIndex );
            
            // Both of these should work
            /*
            if ( index >= input.length() - 3 ) {
              break;
            }
            */
            if( index > input.length() -4 ) {
                break;
            }
            
            String found = input.substring(substringStartIndex, substringEndIndex);
            System.out.println(found);
            index = input.indexOf("abc", index+4);
        }
   }
   
   public void test() {
    
     //findAbc("abcd");
     
     findAbc("abcdabc");
     
    }

}
