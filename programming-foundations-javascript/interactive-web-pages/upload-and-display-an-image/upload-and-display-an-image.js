// Global Variable
var img;
var greyscaleImg;

function upload( canvasId ) {
  // Get the file input
  // Make it into a SimpleImage
  // Get the canvas
  // Draw the image onto the canvas
  var fileInput = document.getElementById( "finput" );
  img = new SimpleImage( fileInput );
  greyScaleImg = new SimpleImage( fileInput );
  var canvas = document.getElementById( canvasId );
  img.drawTo( canvas );
}

function makeGray( canvasId ) {
  // Get the canvas
  var canvas = document.getElementById( canvasId );

  // Grayscale Algorithm
  // Gray == RBG values the same
  // Average all of the RGB values, set all RBG values the same
  for( var pixel of greyScaleImg.values() ) {
    setGray( pixel );
  }

  greyScaleImg.drawTo( canvas );

}

function setGray( pixel ) {
  var avg = ( pixel.getRed() + pixel.getGreen() + pixel.getBlue() ) / 3;
  pixel.setRed( avg );
  pixel.setGreen( avg );
  pixel.setBlue( avg );
  return pixel;
}
