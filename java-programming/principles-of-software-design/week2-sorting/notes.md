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
