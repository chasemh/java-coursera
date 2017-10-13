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
    displayDimensions();
  }
  else {
    // Wait a second before trying to get the dimensions because the image
    // isn't ready yet
    setTimeout( displayDimensions, 1000 );
  }

}

function doGrayscale( canvasId ) {

  if( !isImageReady( img ) || !isImageReady( modifiedImg ) ) {
    alert( "Images are not yet ready! Did you upload an image first?" );
  }

  var canvas = document.getElementById( canvasId );

  for( var pixel of img.values() ) {
    var pixelToModify = modifiedImg.getPixel( pixel.getX(), pixel.getY() );
    averageColor = getAverageColor( pixel );
    pixelToModify.setRed( averageColor );
    pixelToModify.setGreen( averageColor );
    pixelToModify.setBlue( averageColor );
  }

  clearCanvas( canvasId );
  modifiedImg.drawTo( canvas );
}

function doRedscale( canvasId ) {

  if( !isImageReady( img ) || !isImageReady( modifiedImg ) ){
    alert( "Images are not yet ready! Did you upload an image first?" );
  }

  var canvas = document.getElementById( canvasId );

  for( var pixel of img.values() ) {
    var pixelToModify = modifiedImg.getPixel( pixel.getX(), pixel.getY() );
    var averageColor = getAverageColor( pixel );

    if( averageColor < 128 ) {
      pixelToModify.setRed( averageColor * 2 );
      pixelToModify.setGreen( 0 );
      pixelToModify.setBlue( 0 );
    }
    else {
      pixelToModify.setRed( 255 );
    }
  }

  clearCanvas( canvasId );
  modifiedImg.drawTo( canvas );
}

function doNegative( canvasId ) {

  if( !isImageReady( img ) || !isImageReady( modifiedImg ) ){
    alert( "Images are not yet ready! Did you upload an image first?" );
  }

  var canvas = document.getElementById( canvasId );

  for( var pixel of img.values() ) {
    var pixelToModify = modifiedImg.getPixel( pixel.getX(), pixel.getY() );
    pixelToModify.setRed( 255 - pixel.getRed() );
    pixelToModify.setGreen( 255 - pixel.getGreen() );
    pixelToModify.setBlue( 255 - pixel.getBlue() );
  }

  clearCanvas( canvasId );
  modifiedImg.drawTo( canvas );

}

function doMagentascale( canvasId ) {

  if( !isImageReady( img ) || !isImageReady( modifiedImg ) ){
    alert( "Images are not yet ready! Did you upload an image first?" );
  }

  // Magenta = (255, 0, 255)
  var canvas = document.getElementById( canvasId );

  for( var pixel of img.values() ) {
    var pixelToModify = modifiedImg.getPixel( pixel.getX(), pixel.getY() );
    var averageColor = getAverageColor( pixel );
    pixelToModify.setRed( 255 );
    pixelToModify.setGreen( averageColor );
    pixelToModify.setBlue( 255 );
  }

  clearCanvas( canvasId );
  modifiedImg.drawTo( canvas );

}

function doRainbow( canvasId ) {

  if( !isImageReady( img ) || !isImageReady( modifiedImg ) ){
    alert( "Images are not yet ready! Did you upload an image first?" );
  }

  var canvas = document.getElementById( canvasId );
  var height = img.getHeight();

  for( var pixel of img.values() ) {
    var x = pixel.getX();
    var y = pixel.getY();
    var pixelToModify = modifiedImg.getPixel( x, y );
    var averageColor = getAverageColor( pixel );
    if( y < ( height / 7 ) ) {
      // Red Scale
      if( averageColor < 128 ) {
        pixelToModify.setRed( 2 * averageColor );
        pixelToModify.setGreen( 0 );
        pixelToModify.setBlue( 0 );
      }
      else {
        pixelToModify.setRed( 255 );
        pixelToModify.setGreen( ( 2 * averageColor ) - 255 );
        pixelToModify.setBlue( ( 2 * averageColor ) - 255 );
      }
    }
    else if( y >= ( height / 7 ) && y < ( (2 * height) / 7 ) ) {
      // Orange Scale
      if( averageColor < 128 ) {
        pixelToModify.setRed( 2 * averageColor );
        pixelToModify.setGreen( 0.8 * averageColor );
        pixelToModify.setBlue( 0 );
      }
      else {
        pixelToModify.setRed( 255 );
        pixelToModify.setGreen( ( 1.2 * averageColor ) - 51 );
        pixelToModify.setBlue( (2 * averageColor ) - 255 );
      }
    }
    else if( y >= ( (2 * height) / 7 ) && y < ( (3 * height) / 7 ) ) {
      // Yellow Scale
      if( averageColor < 128 ) {
        pixelToModify.setRed( 2 * averageColor );
        pixelToModify.setGreen( 2 * averageColor );
        pixelToModify.setBlue( 0 );
      }
      else {
        pixelToModify.setRed( 255 );
        pixelToModify.setGreen( 255 );
        pixelToModify.setBlue( ( 2 * averageColor ) - 255 );
      }
    }
    else if( y >= ( (3 * height) / 7 ) && y < ( (4 * height) / 7 ) ) {
      // Green Scale
      if( averageColor < 128 ) {
        pixelToModify.setRed( 0 );
        pixelToModify.setGreen( 2 * averageColor );
        pixelToModify.setBlue( 0 );
      }
      else {
        pixelToModify.setRed( ( 2 * averageColor ) - 255 );
        pixelToModify.setGreen( 255 );
        pixelToModify.setBlue( ( 2 * averageColor ) - 255 );
      }
    }
    else if( y >= ( (4 * height) / 7 ) && y < ( (5 * height) / 7 ) ) {
      // Blue Scale
      if( averageColor < 128 ) {
        pixelToModify.setRed( 0 );
        pixelToModify.setGreen( 0 );
        pixelToModify.setBlue( 2 * averageColor);
      }
      else {
        pixelToModify.setRed( ( 2 * averageColor ) - 255 );
        pixelToModify.setGreen( ( 2 * averageColor ) - 255 );
        pixelToModify.setBlue( 255 );
      }
    }
    else if( y >= ( (5 * height) / 7 ) && y < ( (6 * height) / 7 ) ) {
      // Indigo Scale
      if( averageColor < 128 ) {
        pixelToModify.setRed( 0.8 * averageColor );
        pixelToModify.setGreen( 0 );
        pixelToModify.setBlue( 1.6 * averageColor );
      }
      else {
        pixelToModify.setRed( ( 1.2 * averageColor ) - 51 );
        pixelToModify.setGreen( ( 2 * averageColor ) - 255 );
        pixelToModify.setBlue( 255 );
      }
    }
    else {
      // Violet Scale
      if( averageColor < 128 ) {
        pixelToModify.setRed( 1.6 * averageColor );
        pixelToModify.setGreen( 0 );
        pixelToModify.setBlue( 1.6 * averageColor );
      }
      else {
        pixelToModify.setRed( ( 0.4 * averageColor ) + 153 );
        pixelToModify.setGreen( ( 2 * averageColor ) - 255 );
        pixelToModify.setBlue( ( 0.4 * averageColor ) + 153 );
      }
    }

  }

  clearCanvas( canvasId );
  modifiedImg.drawTo( canvas );

}

function doBlur( canvasId, xRangeRadius, yRangeRadius ) {

  if( !isImageReady( img ) || !isImageReady( modifiedImg ) ) {
    alert( "Images are not yet ready! Did you upload an image first?" );
  }

  var canvas = document.getElementById( canvasId );

  for( var pixel of img.values() ) {
    var pixelToModify = modifiedImg.getPixel( pixel.getX(), pixel.getY() );
    // Generate a random number between 0 and 1
    // If number < 0.5, copy random nearby pixel into current slot
    // Get a random nearby pixel by choosing a range for x and y and generating
    // a random number in that range.
    // Random Pixel X = currentX + randomXOffset
    // Random Pixel Y = currentY + randomYOffset
    // Otherwise, copy the corresponding pixel in to the current slot
    var blurChance = Math.random();

    if( blurChance < 0.5 ) {
      // Blur the pixel
      var randomPixel = getRandomPixel( img, pixel.getX(), pixel.getY(), xRangeRadius, yRangeRadius );
      pixelToModify.setRed( randomPixel.getRed() );
      pixelToModify.setGreen( randomPixel.getGreen() );
      pixelToModify.setBlue( randomPixel.getBlue() );

    }
    else {
      // Leave the pixel unblurred in the modified image
      pixelToModify.setRed( pixel.getRed() );
      pixelToModify.setGreen( pixel.getGreen() );
      pixelToModify.setBlue( pixel.getBlue() );
    }
  }

  clearCanvas( canvasId );
  modifiedImg.drawTo( canvas );

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

function clearCanvas( canvasId ) {
  var canvas = document.getElementById( canvasId );
  var context = canvas.getContext( "2d" );
  context.clearRect( 0, 0, canvas.width, canvas.height );
}

function getAverageColor( pixel ) {
  return ( pixel.getRed() + pixel.getGreen() + pixel.getBlue() ) / 3;
}

function getRandomPixel( image, currentX, currentY, xRange, yRange ) {
  var randomX = currentX + Math.floor( ( Math.random() * xRange ) + 1 );
  var randomY = currentY + Math.floor( ( Math.random() * yRange ) + 1 );
  var width = image.getWidth();
  var height = image.getHeight();

  if( randomX < 0 ) {
    randomX = 0;
  }
  else if( randomX >= width ) {
    randomX = width - 1;
  }

  if( randomY < 0  ) {
    randomY = 0;
  }
  else if( randomY >= height ) {
    randomY = height - 1;
  }

  return image.getPixel( randomX, randomY );
}
