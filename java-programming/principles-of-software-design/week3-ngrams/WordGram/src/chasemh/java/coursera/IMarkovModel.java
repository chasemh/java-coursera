package chasemh.java.coursera;

/**
 * Exercise solutions to Assignment: WordGram Class
 * https://www.coursera.org/learn/java-programming-design-principles/supplement/cajch/programming-exercise-wordgram-class 
 *
 * Modified By Chase Hennion
 * @version 2017-11-06
 */
public interface IMarkovModel {
    public void setTraining(String text);
    
    public void setRandom(int seed);
    
    public String getRandomText(int numChars);

}
