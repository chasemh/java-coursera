# Strings in Java

## Finding a gene in DNA
* Genomes are represented by long strings of characters made up of the characters `A`, `T`, `C` and `G`
  * `A` = Adenine
  * `T` = Thymine
  * `C` = Cytosine
  * `G` = Guanine
* These letters represent nucleotides, the basic building blocks of DNA
* Three nucleotides create a codon, which describes an amino acid
* `ATG` = Start Codon
  * Special Codon that indicates the start of a gene
* `TAA` = Stop codon
  * Special codon that indicates the end of a gene
* Human Genome contains 3 billion of these characters, making it difficult to analyze by hand

### Understanding strings
* While computers store all information in numbers, strings are used to store human-readable data

### Developing an Algorithm
* Problem statement: Find a gene in a genome string
* Use knowledge of start and stop codons
* Essentially capturing the substring between the start and stop codons
  * Remember to capture the codon text as well as it is part of the gene too
* Simplified problem here
  * Real genes have multiple of three codons
  * There are more than one stop codons

### Positions on Strings
* Strings are just arrays of characters
* Arrays are zero indexed, that is, the first slot in the array is index 0
* Slots in array == Indices or Indexes
* Builtin substring method will be useful here
* Need double quotes for strings in Java
* In substring method, start index is inclusive, end index is exclusive
  * Note: The length of the resultant substring will be the difference between the start and end index passed to the substring method
* Other useful methods
  * `.length()`: Get the length of a string. Length is max_index + 1
  * `.indexOf("somestring")`: Attempts to find the first occurrence of a given string within a string and returns the index of where the given string starts in the search string. If not found, method returns -1.
    * Can also pass an integer to this method ( i.e. `.indexOf( "somestring", 10 )` )
    * The index specifies where to start looking for `"somestring"` in the search string
  * `.startsWith("somestring")`: Returns `true` if the calling string starts with `somestring`. Otherwise returns `false`.
  * `.endsWith("somestring")`: Like `startsWith("somestring")` but checks to see if the calling string ends with `"somestring"`
* Don't worry about memorizing all the methods, just look them up from the Java API docs

### Translating into Code
* Find the index of the start codon, if it exists
* Find the index of the stop codon, if it exists. Start looking for the stop codon at the index of the start codon
* If either the start or stop index are -1, return an empty string as there is no gene in the given sequence
* Capture the substring between the index of the start codon to the index of the stop condon + the length of the stop codon
  * Add the length of the stop codon to capture the entirety of the stop codon in the gene
* Return the result  

### Java Math
* Previous example was gross oversimplification
* Real genes must be made of three codons
  * So a valid (but still simplified( gene string must start with `ATG` and a multiple of three characters before hitting `TAA`

## Finding all genes in DNA

### Conceptual Understanding
* Indefinite loops (`while` loops)
* while loop prototype
```java
while( condition is true ) {
  // Do stuff
}
```
### Three Stop Codons
* There are actually three stop codons
  * `TAA`
  * `TGA`
  * `TAG`
