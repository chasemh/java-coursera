# Batch GrayScale Images

## Batch GrayScale Converting Many Files
* Task: Convert many images to GrayScale
  * User selects a group of Images, from 1 to 1000
  * Program converts each image to grayscale
  * Save converted images to new file
    * Add "gray-" to the beginning of the original file name

## GrayScale Algorithm
* Gray = RBG values all the same
* Making a pixel gray
  * Take the average of the RGB values
  * Set each RGB value for the pixel to the calculated average

## Image Iterable in Bluej:
* Use the Duke `ImageResource` class
  * `ImageResource img = new ImageResource( width, height );`
* Iterate through pixels in ImageResource
  * `for( Pixel pixel : img.pixels() )`
* Get Pixel: `img.getPixel( x, y );`
* Also have get/set red,green,blue methods for pixels
* Draw an image with `img.draw();`

## Batch Processing GrayScale
* Use `DirectoryResource` to get many files
* Iterate through all the files selected in the `DirectoryResource`
* Convert the files to image resources
* Pass the images to makeGray method

## Saving Images with New Names
* `ImageResource` class has methods for getting file name, saving images to file
