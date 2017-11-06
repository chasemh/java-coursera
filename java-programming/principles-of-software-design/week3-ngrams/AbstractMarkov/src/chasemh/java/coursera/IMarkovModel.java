package chasemh.java.coursera;

/**
 * Exercise solutions to Assignment: Interfaces and Abstract Classes
 * https://www.coursera.org/learn/java-programming-design-principles/supplement/n1DIm/programming-exercise-interface-and-abstract-class 
 *
 * Modified By Chase Hennion
 * @version 2017-11-05
 */
public interface IMarkovModel {
	public void setRandom( int seed );
    public void setTraining(String text);
    public String getRandomText(int numChars);
    
}
