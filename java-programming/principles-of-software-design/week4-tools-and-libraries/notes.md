# Java: Tools and Libraries for Everyone

## Using Java Beyond BlueJ

### Main Method
* Java programs begin execution in the main method
* Main method always has the same signature, though it may be in any class
  * `public static void main( String[] args )`
* The parameters for main represent the command line arguments passed to the program

### Static
* `static` means that a method or variable belongs to a class and not to individual instances of a class
* Static methods can access static field and call static methods
* Static methods cannot access instance variables or call non-static methods

## Mainstream Java

### Understanding Exceptions
* An exception is thrown when a program encounters an error or issue that prevents the program from continuing
* Unhandled exceptions crash the program and print the stack trace that gives details about where the
  exception occurred
* Exceptions can be handled using try, catch and finally blocks
* Exceptions may be propagated when it cannot be sufficiently handled
  * Requires updating the method signature to add a clause that the method could throw an exception

### Handling Exceptions
* Use try, catch and finally to handle exceptions
```java
try {
  // Code that could throw an exception
}
catch( SomeExceptionClass e ) {
  // Code to handle the exception if is thrown
}
finally {
  // Code that will execute in either case
}
```
* Java 7+ has some additional exception handling features that may be used

### Declaring Exceptions
* Checked Exceptions: Must specify that the method could throw the exception
  * `public void myMethod() throws SomeExceptionClass { ... }`
* Checked exceptions are those that are checked by the compiler at compile time
* Exceptions under `Error` and `RuntimeException` parent classes in Java are unchecked
* All other exceptions are checked
* To make your own checked/unchecked exceptions, ensure that you exception class inherits from
  a checked/unchecked parent exception class

### Throwing Exceptions
* Use the keyword `throw` to throw an exception
  * Typically something like `throw new SomeExceptionClass();`
* Can throw anything that extends the Java class throwable
* More commonly, instances of classes that extend Exception are thrown
* Java has many built-in Exception types
* Can create your own exceptions by extending the appropriate exception class

### Reading Files with java.nio
* Use Path objects to build and manipulate file paths
* Use BufferedReader class to read in a file
* Use `.readLine()` from BufferedReader to continually read lines from a file until there are no more lines and `null` is returned
* `Files` has a static method `newBufferedReader( somePathObject )` that can create a BufferedReader object given a Path object
```java
public void readAndPrint() throws IOException {
  Path p = Paths.get( "my_file.txt" );
  BufferedReader reader = Files.newBufferedReader( p );
  while( true ) {
    String line = reader.readLine();
    if( line == null ) {
      break;
    }
    System.out.println( line );
  }
}
```
* java.nio - New Java I/O package, newer than java.io package
* Most methods throw some kind of exception
* Typically use static methods to create and get resources
* Can read URLs with the java.net package
* Similar to file reading
```java
URL source = new URL( "http://mySite.com/my_file.txt" );
BufferedReader reader = new BufferedReader( new InputStreamReader( source.openStream() ) );
while( true ) {
  String line = reader.readLine();
  if( line == null ) {
    break;
  }
  System.out.println( line );
}
```
* If a method can throw multiple checked exceptions, multiple exceptions need to be added to the throws clause in the method header
* If multiple exceptions can be thrown and those exceptions share a common base class, saying that the method can throw an exception
  of the common base class is sufficient.
