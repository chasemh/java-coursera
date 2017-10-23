# Breaking the Vigenere Cipher

## Intro
* Key in a Vigenere Cipher is a word (array of ints)
* The word is written down repeatedly to match the length of the message to encrypt
* Each value in the key is a value by which a character should be shifted in the alphabet
* Conceptually like using multiple Caesar Ciphers

## Known Language and Key Length
* First need to slice message into pieces that correspond to a given key

## Unknown Key Length
* If the length is unknown, have to try many key lengths until something comprehensible is returned.
* "Comprehensible" => All the words are in english or whatever encrypted language was used.
* Need to test a decryption, get all of the words and verify that the words are present in a dictionary
* Use a HashSet for efficiency
* Just try key length up to 100 in the mini profject
* Use String `.split("\\W")` to split string on all "non-word" characters

## Unknown Language
* Need a dictionary for each language and the most common character in each language
* Try each language and see which one had the most valid words
