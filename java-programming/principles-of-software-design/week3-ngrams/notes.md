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
