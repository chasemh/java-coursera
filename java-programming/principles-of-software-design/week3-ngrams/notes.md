# N-Grams Predictive Tests

## Generating Random Text

### Intro
* Claude Shannon: Principal thinker behind information theory
* Going to be generating text based upon training data
* Order Zero Text: Based solely upon the distribution of characters in text
  * Essentially incomprehensible
* Order One Text: Uses one letter to predict the next
  * Usually pronounceable but not real words
* Order Two Text: Uses two characters to predict the third
  * More recognizable words
* Given a high order text, you may be able to discern the training data
  * Markov recognition
* Spam filters typically rely upon Bayesian probability and Markov processes to predict which messages are spam.

### Order-Zero, Order-One
* All Markov text generation algorithms use a training text to generate text randomly
* Order-Zero Markov Text Generation
  * Randomly choose characters, no prediction of characters
  * Characters chosen to match the frequency of their occurrence in the training text
* Order-One Markov Text Generation
  * Use one character to predict the next
  * Words are more comprehensible
* Need methods for setting training text, generating random string, setting random
* Order-One Markov Text Generation
  * One character predicts the next
  * Need to determine the probability of one character following another?
    * How to do this?
      * Iterate through the training text and find all occurrences of a character
      * When the character is found, add the character following it to a list
      * Randomly choose a character from the built list
        * Takes into account probability because you are more likely to choose a character that appears more often from the lists

### Finding Follow Set
* Key Algorithm: Determining and building the follow set to allow for random text generation
* Iterate through the string:
  * When you encounter the character you are searching for, store the character after it in the follows array
  * If no following character can be obtained, break
  * Stop searching once you hit the end of the string

### Implementing Order-Two
* Like Markov One but looking at two character strings to determine the following characters

### Interfaces and Abstract Classes
* Common practice to have custom Interfaces begin with the letter i
  * For Example `IMarkovModel`
* Recall that when a class implements an interface, instances of that class may be treated as instances of the interface itself
  * Allows great flexibility when writing methods as the method may accept any class that is implementing the interface as a parameter
* Open-Closed Software Design Principle
  * Open for extensions, closed for modification
  * Don't extend the functionality of code by modifying already written and tested code
* Each developed Markov class contains shared state and code
  * Capture commonality in an abstract class and then use inheritance
* Declare an abstract class by using the keyword `abstract` in the class definition
  * For example `public abstract class MyAbstractClass`
* Typically, when you create an abstract class, you will want to change your instance variables and to `protected` rather than `private`
* Protected means that only subclasses may directly access the instance variables. The variables are still private to external access for unrelated classes
* This should also be done for method declarations as well that will need to be called in the subclasses
* Abstract classes have at least one method in it that is marked abstract and not implemented in the abstract class
  * Typically reserved for a method that will require custom implementation in the subclasses
  * Subclasses must implement these methods themselves
* Designate an inheritance relationship with the `extends` keyword

## Word N-Grams

### Intro
* Instead of predicting the next letter, now predicting the next word

### Order-One Concepts
* Good Design: Implementation can change but the interface doesn't
  * Abstraction
* Going to be using the same Markov class interfaces but the classes will be updated to find random words instead of letters

### Order-One Helper Functions
* Arrays do not have a `indexOf` method. This will need to be replaced with a custom helper function.
* `substring` calls can be replaced with Array indexing
* 
