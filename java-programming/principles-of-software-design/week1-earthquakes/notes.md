# Searching EarthQuake Data

## Relationships Between Classes
* POJO - Plain Old Java Object
  * Typically refers to a Java object that is only responsible for holding data
    and doesn't have many methods that operate on the data other than getting and setting it.
  * Essentially the equivalent of a C `Struct`
*  Composition = "Has a" Relationship
  * When a class includes an instance of another class as a instance variable

## Licensing and APIs
* Android Location class is freely available under the Apache 2.0 license
* It can be freely modified and released

# Filtering Data

## Intro
* Avoid duplicating code
  * DRY

## Interfaces to Avoid Duplication
* Use the `Filter` interface
* An interface is a type that promises certain methods will exist and be implemented in classes that implement the interface
* Classes `implement` interfaces
* When a class implements an interface, that class must define the methods from the interface
* A class that implements an interface may be treated as the interface type in method calls, assignment etc.
  * If you implement `filter` for `QuakeEntry` you could say `Filter f = new QuakeEntry();`

## Interfaces in More Depth
* Java always notes the type of an object when it is created with `new` inside the object
* When methods are called on an object, Java find the type and then determines which method to call
* Dynamic Dispatch: Using the object type to determine what method to call
  * The process of selecting which implementation operation to call at run time  

## MatchAll
* Can write a filter that accepts many filter items to filter on
  * Example of composition 
