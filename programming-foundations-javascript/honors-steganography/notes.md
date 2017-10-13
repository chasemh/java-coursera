# Hiding Data in Images with Steganography

## Steganography Part 1
* Hiding of data within images or other digital artifact
* Key is to hide the data in such a way that it is difficult to see in the image
* Idea predates computers
* Going to be hiding one image in another
* Hide data in the least significant digits of RGB values of pixels
  * Least Significant digits would be the ones and tens columns in an RGB value
  * Least Significant digits because changing them does not drastically alter the presented color
* Decimal = Base 10
* Binary = Base 2
* Decimal Example (Suppose RGB values went from 0000 to 9999)
  * Encoding:
    * Take two most significant digits of the pixel you want to hide data in. Those become the most significant digits of the resultant pixel.
    * Take the two most significant digits of the pixel you are hiding. Those become the least significant digits of the resultant pixel.
  * Decoding:  
    * Take the two least significant digits of the pixel to decode. Those become the most significant digits of the extracted pixel.
    * Cannot extract the least significant digits of the extracted pixel in the pixel to decode. Set the least significant digits to 00

## Steganography Part 2
* Decimal = Base 10
  * 1s place, 10s place, 100s place and so on
* Decimal Example
  * 8237 = (7 * 1) + (3 * 10) + (2 * 100) + (8 * 1000)   <-- Powers of 10
* Binary = Base 2
  * 1s place, 2s place, 4s place, 8s place and so on
* Computer work with transistors, electronic components that deal with essentially two voltage levels (on, off ) so base two system naturally fits computers.
 * Binary Example:
   * 10111 = (1 * 1) + (1 * 2)  + (1 * 4) + (0 * 8) + (1 * 16) = 1 + 2 + 4 + 0 + 16 = 23 in decimal  <-- Powers of 2
* binary digits = bits
* Binary Range for a 8 bit number
  * 00000000 = 0 in decimal
  * 11111111 = 255 in decimal
* Steganography with Binary Example - Hide 10110010 in 01110101
  * Most significant digits would be the first four bits
  * Take the significant digits from the first number. Those become the least significant digits of the encoded result.
  * Take the significant digits of the second number. Those become the most significant digits of the encoded result.
  * So the result is 01111011
* How to get the most/least significant digits of a decimal number?
  * Dividing a number by 10 moves the decimal place to the left one space
    * i.e. 8274 / 10 = 827.4
  * So if you wanted the most significant digits of 8274, you would need to divide it by ten twice and then discard the floating point portion of the number
    * i.e Math.floor( 8274 / (10 * 10 ) ) = 82

* Encoding mathematically with decimal example - Hide 8274 in 3568
  * Get most significant digits of 8274
    * Math.floor( 8274 / (10 * 10) ) = 82
  * Get most significant digits of 3668
    * Math.floor( 3568 / (10 * 10) ) = 35
  * Build the encoded string
    * Want the most significant digits of 3568 to be the most significant digits in the result and the most significant digits of 8274 to be the least significant digits in the result.
    * ( 35 * 100 ) + 82 = 3582 = Encoded Number
* Decoding mathematically in decimal example - Retrieve 8200 from 3582
  * Need to obtain the least significant digits (82) from 3582
  * Can do this by using the modulo or % operator in Javascript
  * The modulo operator gives you the remainder left over after performing division
    * 3582 / 100 = 35 remainder 82 so 3582 % 100 = 82
  * We can't retrieve the least significant digits of the original encoded value so we set them to 00
  * So the result is 8200
    * ( 3582 % 100 ) * 100 = 8200 = Decoded Value

* Encoding mathematically in binary example - Hide 10110010 in 01110101
  * Want the four most significant digits of each number. That means we need to divide by two four times to get the first four digits of each number.
    * Math.floor( 10110010 / ( 2 * 2 * 2 * 2 ) ) = 1011
    * Math.floor( 01110101 / ( 2 * 2 * 2 * 2 ) ) = 0111
  * Want the most significant digits of 01110101 to be the most significant digits in the result and the most significant digits of 10110010 to be the least significant in the result.
    * ( 0111 * ( 2 * 2 * 2 * 2) ) + 1011 = 01111011
* Decoding mathematically in binary example - Retrieve 10110000 from 01111011
  * Need to obtain the least significant digits (1011) from 01111011
  * Use the modulo operator again but instead of using 100, use 16 ( 2^4 )
    * 01111011 % 16 = 1011
  * Can't retrieve the least significant digits of the original encoded value so we set them to 0000
    * 1011 * 16 = 10110000
  * ( 01111011 % 16 ) * 16 = 10110000 = Decoded Value

## Steganography Part 3
