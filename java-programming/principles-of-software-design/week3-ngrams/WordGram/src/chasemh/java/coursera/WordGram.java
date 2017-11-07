package chasemh.java.coursera;

/**
 * Exercise solutions to Assignment: WordGram Class
 * https://www.coursera.org/learn/java-programming-design-principles/supplement/cajch/programming-exercise-wordgram-class 
 *
 * Modified By Chase Hennion
 * @version 2017-11-06
 */
public class WordGram {
    private String[] myWords;
    private int myHash;

    public WordGram(String[] source, int start, int size) {
        myWords = new String[size];
        System.arraycopy( source, start, myWords, 0, size );
    }

    public String wordAt(int index) {
        if (index < 0 || index >= myWords.length) {
            throw new IndexOutOfBoundsException("bad index in wordAt "+index);
        }
        return myWords[index];
    }

    public int length(){
        return this.myWords.length;
    }

    public String toString(){
        String ret = "";
        
        for( int i = 0; i < this.length(); ++i ) {
        	  ret = ret + this.myWords[ i ] + " ";
        }

        return ret.trim();
    }

    public boolean equals(Object o) {
        WordGram other = (WordGram) o;
        
        if( this.length() != other.length() ) {
        		return false;
        }
        
        for( int i = 0; i < this.length(); ++i ) {
        		if( !this.wordAt( i ).equals( other.wordAt( i ) ) ) {
        			return false;
        		}
        }
        
        return true;

    }

    public WordGram shiftAdd(String word) {	
        
        // shift all words one towards 0 and add word at the end. 
        // you lose the first word
        
        String[] newWords = new String[ this.length() ];
        System.arraycopy( myWords, 0, newWords, 0, this.length() );
        
        for( int i = 1; i < newWords.length; ++i ) {
        		newWords[ i - 1 ] = newWords[ i ];
        }
        newWords[ newWords.length - 1 ] = word;
        
        WordGram out = new WordGram( newWords, 0, newWords.length );
        return out;
    }
    
    public int hashCode() {
    		return this.toString().hashCode();
    }

}