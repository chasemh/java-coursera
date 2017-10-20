# GladLibs

## Telling a Random Story

### Intro
* Telling random stories based upon a template with word type substitutions

### High Level Design Concepts
* Need to interate through a story template
* When you encounter a word in <category> brackets, substitute in a random word in the category
* StorageResource allows for extensible collections of strings but only allows access through an iterable
  * Time consuming to search though
* Arrays allow random access but are of fixed size
* An ArrayList would be ideal here
  * Java implementation of an extensible array

### ArrayList
* Simple to add unique words to a StorageResource. Difficult to find a random word though.
* StorageResouce uses an ArrayList internally but doesn't expose random access
* ArrayList is a generic type
  * You have to pass in the type of the object contained within the ArrayList when initialized
  *  `ArrayList<String> = new ArrayList<String>();`
  * `ArrayList<Integer> = new ArrayList<Integer>();`
* ArrayLists cannot accept primitive types for the generic
* Java can typically convert primitives like `int` and `double` into instances of their wrapper classes, `Integer` and `Double` respectively. But there may be times where this behaves incorrectly.
* Common methods
  * `get( index )`: Get element at index
  * `set( index, element )`: Set element at index
  * `add( element )`: Add an element to the end of the ArrayList
  * `size()`: Gets the number of elements in the ArrayList. Don't confuse with `capacity()`, which gives the number of elements that the ArrayList can currently hold and not the number of elements currently in the list.
* Can iterate through an ArrayList using a standard for loop with an index counter or though a for each loop with an iterable.
  * Use an iterable when you don't care about the index value
    * Trying to call `.add()` or `.remove()` during an iterable loop will cause the compiler to throw an error.
  * Use a for loop when you do need the index

### ArrayList for Unique Words
* Use two ArrayLists
  * First one holds the unique words
  * Second one holds the occurrences of the words
  * So the number of occurrences of the kth word would be in the kth index of the second ArrayList

### ArrayList Advantages and Issues
* Arrays
  * Simpler to instantiate and access elements
  * Arrays can be instantiated with primitive types
  * Arrays cannot grow in size
* Counting unique words is more difficult with an Array
  * Requires more space in memory as you have to initialized the array with the maximum possible number of unique words
  * Checking for a word in an array is more difficult as the array will likely contain null values that must be skipped or other wise handled.

## Using and Improving GladLibs

### Intro
* Going to be modifying an existing program
* One ArrayList for each substitution item

### Brittle Code
* Brittle: Code that is very difficult to extend or modify due to it's structure.
* Open/Closed Principle: Code should be open for extension but closed for modification.

### HashMaps
* Using HashMaps is more efficient than using two parallel ArrayLists
* HashMaps associate keys with values
  * Like Python `dict` or Ruby `Hash`
* A key is an element in the domain
* A value is what they key maps to in the range
* In HashMaps, look up the key to get the associated value
* A HashMap in Java also uses generics like ArrayLists
  * Both key and value types are generic
* Iterate over a map by using the `.keySet()` method
  * Method returns an iterable that gives each key in the map
* Maps are very efficient
  * Lookup time for keys in a map is independent of the number of keys
  * This is not true for Arrays or ArrayLists
* Keys must be unique
* Best to make Keys some Immutable type

### HashMaps for Flexible Design
*
