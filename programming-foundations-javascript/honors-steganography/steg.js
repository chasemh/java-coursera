// Crops an image to a given width and height
function crop( image, width, height ) {
  // Create an new image of the desired width and height
  var output = new SimpleImage( width, height );
  // Copy the pixels that fit within with the cropped image from the original image
  for( var pixel of output.values() ) {
    var pixelToCopy = image.getPixel( pixel.getX(), pixel.getY() );
    pixel.setRed( pixelToCopy.getRed() );
    pixel.setGreen( pixelToCopy.getGreen() );
    pixel.setBlue( pixelToCopy.getBlue() );
  }
  // Return the cropped image
  return output;
}

function clearLeastSignificant( colorValue ) {
  // Clear out the least significant bits of a given color value
  return Math.floor( colorValue / 16 ) * 16;
}

function clearLeastSignificantImageBits( image ) {
  // Remove the least significant digits from each pixel in an image
  for( var pixel of image.values() ) {
    pixel.setRed( clearLeastSignificant( pixel.getRed() ) );
    pixel.setGreen( clearLeastSignificant( pixel.getGreen() ) );
    pixel.setBlue( clearLeastSignificant( pixel.getBlue() ) );
  }
  // Return the resultant image
  return image;
}

function shiftToLeastSignificant( image ) {
  // Shift all of the color values to the least significant position
  for( var pixel of image.values() ) {
    pixel.setRed( pixel.getRed() / 16 );
    pixel.setGreen( pixel.getGreen() / 16 );
    pixel.setBlue( pixel.getBlue() / 16 );
  }

  return image;
}

function combine( displayImage, hiddenImage ) {
  // Hide the given image in the display image
  var output = new SimpleImage( displayImage.getWidth(), displayImage.getHeight() );

  for( var pixel of output.values() ) {
    // Get the current pixel coordinates
    // Get the RGB values of both the display and hidden images in the same location
    // Add the RGB values together for the pixels in both images
    // Set the resultant RGB values from the addition to the RGB values for the pixel in the output image
    var x = pixel.getX();
    var y = pixel.getY();
    var displayPixel = displayImage.getPixel( x, y );
    var hiddenPixel = hiddenImage.getPixel( x, y );
    pixel.setRed( displayPixel.getRed() + hiddenPixel.getRed() );
    pixel.setGreen( displayPixel.getGreen() + hiddenPixel.getGreen() );
    pixel.setBlue( displayPixel.getBlue() + hiddenPixel.getBlue() );
  }

  return output;

}

function encodeImage( displayImage, hiddenImage ) {

  // Crop the the hidden image to match the display image
  hiddenImage = crop( hiddenImage, displayImage.getWidth(), displayImage.getHeight() )
  var start = clearLeastSignificantImageBits( displayImage );
  var hidden = shiftToLeastSignificant( hiddenImage );
  return combine( start, hidden );

}

function decodeImage( img ) {
  // Retrieve the hidden image within this image
  // Loop through all pixels
  var secretImage = new SimpleImage( img.getWidth(), img.getHeight() );
  for( var pixel of img.values() ) {
    var pixelToModify = secretImage.getPixel( pixel.getX(), pixel.getY() );
    //Get the least significant bits of each RGB value in the input image
    // ( value % 16 ) * 16 = secretImageValue
    pixelToModify.setRed( ( pixel.getRed() % 16 ) * 16 );
    pixelToModify.setGreen( ( pixel.getGreen() % 16 ) * 16 );
    pixelToModify.setBlue( ( pixel.getBlue() % 16 ) * 16 );
  }

  return secretImage;
}

// Program Start

var toDisplay = new SimpleImage( "usain.jpg" );
var toHide = new SimpleImage( "skyline.jpg" );
var encoded = encodeImage( toDisplay, toHide );
var decoded = decodeImage( encoded );

print( toDisplay );
print( toHide );
print( encoded );
print( decoded );

print( Math.floor( 195 / 16 ) * 16 )
