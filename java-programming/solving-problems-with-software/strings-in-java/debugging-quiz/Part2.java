
/**
 * Code for the second quiz in the Debugging Section
 * https://www.coursera.org/learn/java-programming/quiz/zYkbA/debugging-part-1
 * 
 * @author Chase Hennion 
 * @version 2017-10-15
 */
public class Part2 {
    
    public void findAbc(String input){
       int index = input.indexOf("abc");
       while (true){
           if (index == -1 || index >= input.length() - 3){
               break;
           }
           //System.out.println( "Found abc at index " + index );
           String found = input.substring(index+1, index+4);
           System.out.println(found);
           //index = input.indexOf("abc",index+4);
           index = input.indexOf( "abc", index+3 );
           //System.out.println( "Index after updating " + index );
       }
   }

   public void test(){
       //findAbc("abcd");
       //findAbc("abcdabc");
       findAbc( "abcdkfjsksioehgjfhsdjfhksdfhuwabcabcajfieowj" );
       //findAbc( "abcabcabcabca" );
   }

}
