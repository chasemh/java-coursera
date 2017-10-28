# Earthquakes: Sorting Algorithms

## Implementing Selection Sort

### Intro
* Sorting can make some problems more efficient and easier to solve

### Developing an Algorithm
* Search for the smallest element in the input array
* Copy the smallest element into the sorted array
* Remove the smallest element
* Repeat until the input array is empty

### In Place
* Starting at index i in the array to sort
* Search for the minimum element starting at i + i up to the end of the array to sort
* Swap the minimum element with the element at i
* Increment i and keep going until you hit the end of the array
```java
for( int i = 0; i < toSort.length - 1 ; ++i ) {
  int currentMin = toSort[ i ];
  int minIndex = i;
  for( int j = i + 1; j < toSort.length; ++j ) {
    //Find the smallest element
    if( toSort[ j ] < currentMin ) {
      currentMin = toSort[ j ];
      minIndex = j;
    }
  }
  //Swap minElement with the element at i
  int temp = toSort[ i ];
  toSort[ i ] = toSort[ minIndex ];
  toSort[ minIndex ] = temp;
}
```

### Efficiency
* Selection sort is conceptually simple, and simple to implement
* Rather inefficient for large sets of data
* Two general categories of sorting algorithms
  1. Simple and slow: Runtime quadratic in data size. Big O: n^2
    * Selection Sort
    * Bubble Sort
    * Insertion Sort
  2. Clever and Efficient: Runtime close to linear (really logarithmic) in data size.
    * Merge Sort
    * Quick Sort
* Java `Collections.sort()` is variation of merge sort called [Timsort](https://en.wikipedia.org/wiki/Timsort)
* Most languages have an efficient sorting algorithm built in to their standard library which works on many different types
* Use interfaces to make use of generic sorting functions
* Important interfaces for sorting:
  * Comparable
  * Comparator

## Sorting at Scale

### Intro
* Can use `System.nanoTime()` for code timings

### Comparable
* Comparable interface promises one method: `compareTo`
* Used to define a natural ordering for the type
* Comparable is a generic type
* String compareTo uses lexicographical ordering
  * Iterate through the string as long as the letters are the same
  * Special characters are handled through numerical comparison of they character
    * ASCII Value etc.
* Comparable return type (suppose `a.compareTo( b )` )
  * `a` is less than `b`: Returns Negative Number
  * `a` is greater than `b`: Returns Positive Number
  * `a` is equal to `b`: Returns 0
* Typically 1, -1, and 0 will be returned from a `compareTo` call, but the explicit value returned is not enforced. Can only rely upon the return value's sign.
* This is typically because  `compareTo` is implemented using subtraction, such is the case with String `compareTo`.
* Can remember the return signs of `compareTo` by thinking of a comparison of two numbers, a and b
  * If a and be are equal, a - b will be 0. 0 is what is returned when a and be are equal.
  * If a is less than b, a - b will be less than 0, and a value that is less than zero is returned by `compareTo` in this situation.
  * If a is greater than b, a - b will be greater than 0, and a value that is greater than zero is returned by `compareTo` in this situation

### Comparator
* Defines ordering for another type
* Comparable is bound to the type in order to determine natural ordering
  * `a.compareTo(b)`: Asking a to compare itself to b
* Comparators are defined outside of the type definition
  * `myComparator.compare( a, b )`
  * The logic for the comparison between a and b is defined within the `myComparator` class.
  * Allows you to implement different comparisons for the same type
* `Collections.sort` accepts a comparator as a parameter. The sorting method will then use that comparator for sorting
  * Recall that when a class implements an interface, the implementing class may be treated as an instance of the instance class.
