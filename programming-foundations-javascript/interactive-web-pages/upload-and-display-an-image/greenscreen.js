// Global Variable
var fgImg = null;
var bgImg = null;
var outputImg = null;

function upload( canvasId, fileInputId ) {
  // Get the file input
  // Make it into a SimpleImage
  // Get the canvas
  // Draw the image onto the canvas

  var fileInput = document.getElementById( fileInputId );
  var canvas = document.getElementById( canvasId );

  if( fileInputId == "foregroundInput" ) {
    fgImg = new SimpleImage( fileInput );
    fgImg.drawTo( canvas );
  }
  else if ( fileInputId == "backgroundInput" ) {
    bgImg = new SimpleImage( fileInput );
    bgImg.drawTo( canvas );
  }
  else {
    alert( "Bad fileInputID!" );
  }

}

function doGreenscreen( canvasId ) {
  // Check if either image is null or incomplete
  // If so, send an alert or something

  if( !isGoodImage( fgImg ) || !isGoodImage( bgImg ) ) {
    alert( "Images are not ready! Please upload a background and foreground image and wait until they are fully uploaded." );
  }

  canvas = document.getElementById( canvasId );
  outputImg = new SimpleImage( fgImg.getWidth(), fgImg.getHeight() );

  for( var pixel of fgImg.values() ) {
    // If pixel in foreground is green, place the corresponding background pixel
    // in the output
    // Otherwise, place the corresponding pixel in the foreground in the output
    var x = pixel.getX();
    var y = pixel.getY();

    if( isGreenPixel( pixel ) ) {
      outputImg.setPixel( x, y, bgImg.getPixel( x, y) );
    }
    else {
      outputImg.setPixel( x, y, pixel );
    }
  }

  clearCanvas( "background" );
  outputImg.drawTo( canvas );

}

function clearImages( canvas1Id, canvas2Id ) {
  clearCanvas( canvas1Id );
  clearCanvas( canvas2Id );
  fgImg = null;
  bgImg = null;
  outputImg = null;
}

function clearCanvas( canvasId ) {
  var canvas = document.getElementById( canvasId );
  var context = canvas.getContext( "2d" );

  context.clearRect( 0, 0, canvas.width, canvas.height );
}

function isGreenPixel( pixel ) {
  if( pixel.getGreen() > ( pixel.getRed() + pixel.getBlue() ) ) {
    return true;
  }
  return false;
}

function isGoodImage( img ) {
  if( img == null || !img.complete() ) {
    return false;
  }
  return true;
}
