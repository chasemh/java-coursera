# Web Server Logs - From Logs to Visits

## Reading Log Files

### Intro
* Web Server logs typically contain
  * Requestor IP Address
  * Date of request
  * Page Requested
  * Server response to request
* Going to read, parse and analyze log files in this week

### Reading Log Files
* Hyphens in Apache log files designate missing or otherwise unreliable information about a request
* Apache log file entries have a standard, documented format that can be split and parsed

### LogEntry Class with toString
* When Java has to convert an object into a string, like when printing an object, the `.toString()`
  method for the object will be called, if it has one
* If a `.toString()` method is not provided, the parent `.toString()` class (usually from the `Object` class )
  will be called instead and the location in memory for the object will be printed

## Finding Unique IP Addresses

### Intro
* Going to be finding the unique IP addresses that appear in an Apache log file

### Developing an Algorithm
* Build a unique ArrayList that contains the unique IP addresses

### Equality
* Using the same algorithm with an ArrayList of LogEntries won't work the same way
  * The statement `if( !uniqEntries.contains( someLogEntry ) )` will fail
* How does `contains()` know if two objects are the same or different?
  * `.contains()` is checking if two objects are equal
* Java has two different notions of equality, `==` and `.equals()`
  * Are the two objects the same? `==`
  * Do the two objects contain the same logical content, do they have the same meaning? `.equals()`
```java
String a = "Hello";
String b = a;
String c = "Hello";

a == b;      // True. They point to the same object in memory.
a == c;      // False. They don't point to the same object
a.equals(c); // True. The two objects contain the same logical content
```
* To perform comparisons with a class you have created, you need to add a `.equals()` method that
  tells Java what it means for two instances of that class to be the same.
* If you don't provide one, the default `.equals()` method from the parent class (usually from `Object`)
  will be called.

## Counting Website Visits

### Intro
* How many visits per IP?

### Developing an Algorithm
* Use a HashMap to map IPs to occurrences of the IP
