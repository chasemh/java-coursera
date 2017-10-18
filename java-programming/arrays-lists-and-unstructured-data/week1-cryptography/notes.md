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
