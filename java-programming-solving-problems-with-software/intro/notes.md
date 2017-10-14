# Introduction to the Course

## Functions and Conditionals

* Java doesn't strictly have functions because everything in Java occurs within the context of an object
* When a method is called in Java, a new frame is created to house the variables utilized during the execution of the method
* "call site" : Where the method was called in the code
* Frames are destroyed when method execution completes
* Java uses if/else statements to control execution

## Classes
* Object Oriented Programming groups variables and the code that manipulates those variables into logical units called objects.
* Groups data and code into a single logical unit
* Should always try to make class parameters private to prevent security leaks in code
* Constructors = Code that is used to build an instance of an object. Have the same name as the class but look like a method.
* If no constructor is provided in a class definition, Java provides a default constructor that does nothing to set or manipulate class parameters.
* Methods = Function inside classes. Methods are invoked by objects and implicitly act upon the calling object.
* Static method = Do not act on an instance of a class, just belong to the class in general
* main method = Starting point of code execution

## New
* `new` keyword is used to make a new instance of an object
* Whenever you use new, you create new data in the heap
* Data in the heap is not deleted when a frame is deleted
* `this` - An implicit parameter passed to constructors and class methods
  * Tells these methods / constructors the implicit object to act upon
* `this` is a pointer/reference that points to the object's instance in the heap

## Methods
* Consider the method call `p1.distance( p2 )`
  * `p1` is the implicit object, the `this` parameter when referenced in the method `distance`.

## Types
* A type specifies interpretation and operations available for a given item
* Some types may be converted implicitly by the compiler
  * `int` to `double` for example
* Some types may be explicitly converted by using casting
  * Cast an object by doing `(object_type)variable_name`
  * i.e. `int a = 10;  double b = (double)a;`
* More complex conversions require specialized method calls
* Two main categories of types:
  * Primitives
    * Types:
      * `int`
      * `short`
      * `long`
      * `float`
      * `double`
      * `char`
      * `boolean`
      * `byte`
    * When an instance of primitive is declared, it's value is stored in the stack frame of the method it is called in.
    * Cannot invoke methods on them
      * Can't do `int a = 10; a.someMethod();` for example.
      * Can pass to other methods though
      * Each primitive also has a wrapper class where you could invoke methods
        * `Integer.parseInt( "1" )` for example.
        * Here `Integer` is the wrapper class for `int`.
    * Cannot be null
  * Objects
    * Everything that isn't a primitive
    * When an instance of an object is declared, it's value is stored in the heap.
      * `String someString = "Hello"; `
        * `someString` holds a reference that contains the address in memory where `"Hello"` is stored
      * Can be Null
    * When checking equality of objects with `==`, you are checking to see if the memory references are pointing to the same slot in memory

## For Each Loops
* Iterable = An object that gives you a sequence of values, allowing you to iterate over them in a loop.
* For Each Loop Example
```java
for( String line : f.lines() ) {
  System.out.println( line );
}
```
* The `:` indicates this is a for each loop. Can read the `:` as "in"
  * "For String line in f.lines()"
* `f.lines()` is an Interable, whose values are each line in the file as they appear.
* In the loop, the `line` parameter will point to the current line given by the Iterable
* At the end of a loop cycle, `f.lines()` will give the next line in the file and `line` will be updated to point to the new line


## Solving Programming: A Seven Step Approach
1. Work an example by hand
  * Solve a small instance of the problem on paper
  * If you can't solve small instance on paper then several things could be wrong:
    * The problem may be unclear
    * You may lack the necessary domain knowledge to understand the problem
2. Write down what you did
  * Write down the exact steps you took to solve the small instance on paper
  * Be comprehensive and clear
  * Don't gloss over small details and steps
3. Find patterns
  * Analyze the steps you took. Try to find patterns in the steps.
  * Devise an algorithm that can solve any instance of the problem.
  * Look for repetitive behaviors, conditions for behaviors, non-repetitive behaviors
  * Look for relationships between values and parameters
  * If it is difficult to find patterns, work another example by hand and write down your steps again
4. Check by hand
  * Check your algorithm against a small instance by hand
  * If your algorithm gives you the correct answer, you are ready to start translating the algorithm to code
5. Translate to Code
  * Write out your algorithm in the syntax of your desired languages
6. Run test cases
  * Execute code against text input and verify the output is correct
  * Check edge cases too
7. Debug Failed Test Cases
  * If a test case failed, apply the scientific method to determine the issue(s) in your code
  * Once the issue(s) are determined, return to earlier steps to correct your algorithm and implementation
