var img = null;
var modifiedImg = null;
var fileInput = null;

function upload( canvasId, fileInputId ) {
  // Get the file input
  // Make it into a SimpleImage
  // Get the canvas
  // Draw the image onto the canvas

  fileInput = document.getElementById( fileInputId );
  var canvas = document.getElementById( canvasId );

  img = new SimpleImage( fileInput );
  modifiedImg = new SimpleImage( fileInput );
  img.drawTo( canvas );

  if( isImageReady( img ) ) {
    displayDimensions()
  }
  else {
    // Wait a second before trying to get the dimensions because the image
    // isn't ready yet
    setTimeout( displayDimensions, 1000 );
  }

}

function doGrayscale( canvasId ) {
  var canvas = document.getElementById( canvasId );

  for( var pixel of modifiedImg.values() ) {
    setGray( pixel );
  }

  clearCanvas( canvasId );
  modifiedImg.drawTo( canvas );
}

function doRedscale( canvasId ) {
  var canvas = document.getElementById( canvasId );

  for( var pixel of modifiedImg.values() ) {
    pixel.setRed( 255 );
  }

  clearCanvas( canvasId );
  modifiedImg.drawTo( canvas );
}

function doRainbow( canvasId ) {

}

function doReset( canvasId ) {
  // Draw the original image back on the canvas.
  // Reset the modified image
  var canvas = document.getElementById( canvasId );
  clearCanvas( canvasId );
  img.drawTo( canvas );
  modifiedImg = new SimpleImage( fileInput );
}

function displayDimensions() {

  var dimensionsDiv = document.getElementById( "imageDimensions" );
  dimensionsDiv.innerHTML = "<h4>" + img.getWidth() + " x " + img.getHeight() + "</h4>";

}

function isImageReady( image ) {
  if( image == null || !image.complete() ) {
    return false;
  }
  return true;
}

function setGray( pixel ) {
  var avg = ( pixel.getRed() + pixel.getGreen() + pixel.getBlue() ) / 3;
  pixel.setRed( avg );
  pixel.setGreen( avg );
  pixel.setBlue( avg );
  return pixel;
}

function clearCanvas( canvasId ) {
  var canvas = document.getElementById( canvasId );
  var context = canvas.getContext( "2d" );
  context.clearRect( 0, 0, canvas.width, canvas.height );
}
