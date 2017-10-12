var img = null;
var modifiedImg = null;

function upload( canvasId, fileInputId ) {
  // Get the file input
  // Make it into a SimpleImage
  // Get the canvas
  // Draw the image onto the canvas

  var fileInput = document.getElementById( fileInputId );
  var canvas = document.getElementById( canvasId );

  img = new SimpleImage( fileInput );
  modifiedImg = new SimpleImage( fileInput );
  img.drawTo( canvas );

  // Update the div to display the dimensions of the image

  while( !img.complete() ) {}

  var dimensionsDiv = document.getElementById( "imageDimensions" );
  dimensionsDiv.innerHTML = "<h4>" + img.getWidth() + " x " + img.getHeight() + "</h4>";

}

function doGrayscale( canvasId ) {

}

function doRedScale( canvasId ) {

}

function doRainbow( canvasId ) {

}

function doReset( canvasId ) {
  // Draw the original image back on the canvas.
  // Reset the modified image
}
