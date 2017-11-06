package chasemh.java.coursera;

import java.util.*;

/**
 * Exercise solutions to Assignment: Interfaces and Abstract Classes
 * https://www.coursera.org/learn/java-programming-design-principles/supplement/n1DIm/programming-exercise-interface-and-abstract-class 
 *
 * Modified By Chase Hennion
 * @version 2017-11-05
 */
public abstract class AbstractMarkovModel implements IMarkovModel {
	
    protected String myText;
    protected Random myRandom;
    
    public AbstractMarkovModel() {
        myRandom = new Random();
    }
    
    public void setTraining(String s) {
        myText = s.trim();
    }
    
    protected ArrayList<String> getFollows( String key ) {
		// Find all characters in myText that follow the given key
		ArrayList<String> followsSet = new ArrayList<String>();
		
		// Find the key repeatedly in myText
		for( int keyIndex = this.myText.indexOf( key ); keyIndex != -1; keyIndex = this.myText.indexOf( key, keyIndex + key.length() ) ) {
			// Key found, grab the next character after the key and put it in followsSet
			if( keyIndex == this.myText.length() - key.length() ) {
				// At the end of the string, break
				break;
			}
			
			followsSet.add( this.myText.substring( keyIndex + key.length(), keyIndex + key.length() + 1 ) );
		}
		
		return followsSet;
	}
 
    abstract public String getRandomText(int numChars);

}
