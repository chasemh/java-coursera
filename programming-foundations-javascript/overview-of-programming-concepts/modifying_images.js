// Exercises: https://www.coursera.org/learn/duke-programming-web/supplement/xApiL/programming-exercise-modifying-images

// Create a yellow 200 x 200 square
var width = 200;
var height = 200;

var yellowPixel = new SimplePixel( 255, 255, 0 );
var square = new SimpleImage( width, height );

for( var pixel of square.values() ) {
    pixel.setRed( 255 );
    pixel.setGreen( 255 );
    pixel.setBlue( 0 );
}

print( square );


// Part 1: Image Striping Problem
function stripeImage( imageName ) {
  // Read in the image
  // Make left third red
  // Make middle third green
  // Make right third blue
  var img = new SimpleImage( imageName );
  var imgWidth = img.getWidth();
  var imgHeight = img.getHeight();

  var leftMax = imgWidth / 3;
  var middleMax = ( imgWidth * 2 ) / 3;

  for( var pixel of img.values() ) {
    if( pixel.getX() <= leftMax ) {
      // Set red
      pixel.setRed( 255 );
    }
    else if( pixel.getX() > leftMax && pixel.getX() <= middleMax ) {
      // Set green
      pixel.setGreen( 255 );
    }
    else {
      // Set blue
      pixel.setBlue( 255 );
    }
  }

  return img;
}

print( stripeImage( 'hilton.jpg' ) );


// Part 2
function swapRedGreen( pixel ) {
  //Swap the red and green values of the pixel
  var redVal = pixel.getRed();
  pixel.setRed( pixel.getGreen );
  pixel.setGreen( redVal );
  return pixel;
}

var img = new SimpleImage( 'eastereggs.jpg' );
print( img );
for ( var pixel of img.values() ) {
    pixel = swapRedGreen( pixel );
}
print( img );

// Part 3
function isWhitePixel( pixel ) {
  // If pixel is completely white, return true
  // If not, return false
  if( pixel.getRed() == 255 && pixel.getGreen() == 255 && pixel.getBlue() == 255 ) {
    return true;
  }
  return false;
}

var devil = new SimpleImage( 'duke_blue_devil.png' );
print( devil );
for( var pixel of devil.values() ) {
  if( !isWhitePixel( pixel ) ) {
    // Turn the pixel yellow
    pixel.setRed( 255 );
    pixel.setGreen( 255 );
    pixel.setBlue( 0 );
  }
}
print( devil );
