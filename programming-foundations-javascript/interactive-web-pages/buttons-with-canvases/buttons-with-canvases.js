function doColor( elementId, colorName ) {
  var element = document.getElementById( elementId );
  element.style.backgroundColor = colorName;
}

function colorPicker( canvasId ) {
  // Get the canvas and context
  var canvas = document.getElementById( canvasId );

  // Get the color picker element
  var colorInput = document.getElementById( "clrPicker" );
  var color = colorInput.value;

  // Set the canvas background color to the color picker color
  canvas.style.backgroundColor = color;
}

function doSquare( canvasId ) {
  //Get the canvas and context
  var canvas = document.getElementById( canvasId );
  var context = canvas.getContext("2d");
  //Get slider
  var slider = document.getElementById( "slider" );
  //Clear old rectangles
  context.clearRect( 0, 0, canvas.width, canvas.height );
  //Draw a yellow square using the size from the slider
  context.fillStyle = "yellow";
  context.fillRect( 10, 10, slider.value, slider.value );
}
