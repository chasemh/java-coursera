# Week 1: Cryptography

## Implementing the Caesar Cipher

### Brief History of Cryptography
* Covering Caesar and Vigenere Ciphers in this course

### Introduction: Caesar Cipher Overview
* Encryption: Substitute letter in unencrypted message with letter that is shifted forward some fixed amount, N, in the alphabet
* Decryption: 26 - N
* Java can do integer match on `char`s
* Easier to start by preshifting the alphabet before encryption

### Creating and Manipulating Strings
* String concatenation with `+` operator
  * Java casts non-strings to Strings before concatenating
* Use substring to build the preshifted Caesar Cipher alphabet
```java
String encr = alphabet.substring( key );
encr = encr + alphabet.substring( 0, key );
```
* Strings are immutable. Existing strings cannot be changed after creation.
* String concatenation with large strings can be very time consuming and costly
* `StringBuilder`: Java class for mutable sequences of characters
  * Good for building and manipulating large character sequences
  * More efficient than constantly creating new strings and moving references in memory

### Implementing the Caesar Cipher: Counting Loops
* For Loop Prototype
```java
for( initialization statement; loop guard/test; increment ) {
  // Statements
}

// Example
for( int i = 0; i < 10; i++ ){
  System.out.println( "i = " + i );
}
```
* For loops can be rewritten as while loops
  * initialization statement moved before of the loop
  * Loop guard placed as the condition of the while loop
  * Increment is the last step in the while loop body
```java
for( int i = 0; i < 10; i++ ) {
  System.out.println( "i = " + i );
}

// Same As:
int i = 0;
while( i < 10 ) {
  System.out.println( "i = " + i );
  i++;
}

```

### Implementing the Caesar Cipher: Character Class
* `char` is a primitive type
* Specified with single quotes
* Double quotes designate a String
* The `Character` class has several methods for interacting with and manipulating characters

## Breaking the Caesar Cipher

### Introduction
* Encrypt with key, decrypt with alphabet_length - key
  * Think about how the shifting works
  * Shifting by 26 is the same as shifting by 0 in the english alphabet
* Brute force: Try every key to try to decrypt, choose the one that makes sense
* Brute force here is okay because the key space is small
* Eyeball decryption: Look at the brute force output, see one that makes sense in english

### Arrays
* Array: Homogeneous, indexed collection of elements or values
* Need letter counts to break a Caesar Cipher
* `<Array Type>[] arrayName = new <ArrayType>[ <length of array ];`
  * `char[] chars = new char[ 26 ];`
* Get the length of an array with `.length`  <-- Not a method for arrays
  * Array length cannot be changed after initialization

### Random Numbers and Arrays
* Random numbers with the `java.util.Random` class
* Randomness is modeled and not based upon truly random phenomena
  * Called Psudorandomness

### Developing an Algorithm
* Use frequency analysis of letters in english
* Find the most frequent letter and assume it is 'e'
* A better approach would be to rely upon the frequency of all letters
* Count the occurrence of each letter in the message using an array

## Object Oriented Caesar Cipher

### Intro
* Objects encapsulate code and data into one cohesive construct
* String
  * Data = Sequence of characters
  * Code = Methods in the String class
* A class defines a type. The class defines the code and data that make up the type.
* Objects are instances of classes. Made with `new` keyword in Java
* Objects correspond more naturally with how people think about the world
* Objects create a means of tackling very large problems

### Rewriting with Encapsulation
* Encapsulation: Bundling of data with the methods that act upon the data
  * Used to hide the values or state of structured data inside of class, preventing
    unauthorized access

### Fields
* Also called instance variables
* Each instance of an object will have it's own set of instance variables
* Class names should be a noun
* Method names should be verbs, describe actions.
* Fields should be nouns. Describe things that the class has.
* Fields can also be adjectives that describe properties

### Visibility
* Public: May be accessed within or outside the class
* Private: May only be accessed from within the class
* Abstraction: Separate interface from implementation. Setting privacy can enforce abstraction.
  * Interface to the class is public
  * Implementation is private
  * Allows you to update the implementation without breaking other things that use the class.
* Fields should typically be private
* Helper methods should be private. Other methods should be public.
* Classes should typically be public. There are some cases where they should be private.
* Constructors should typically be public. There are some cases where they should be private.
* Trying to access something private outside of a class will yield a Java compiler error.

### Constructors
* Constructors initialize objects
* Always have the same exact name as the class
* Never have a return type
* May have many constructors that accept different sets of parameters
* Not like normal methods. Called implicitly using the new keyword.
* Java compiler provides a default constructor that does nothing if a constructor is not written.
