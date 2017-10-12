function clicker() {
  alert( "Button Clicked! Triggered clicker JS function!" );
}

function confirmClicker() {
  var choice = confirm("Button Clicked! Select a choice!" );
  var message;
  if( choice == true ) {
    message = "You pressed OK!";
  }
  else {
    message = "Are you sure you want to cancel?";
  }
  alert( message );
}

function changeColor() {
  // Get div1 and div2
  var div1 = document.getElementById( "div1" );
  var div2 = document.getElementById( "div2" );

  // Set the CSS class of div1 to fusciaBack
  // Set the CSS class of div2 to greenBack
  div1.className = "blueBack";
  div2.className = "greenBack";
}

function changeText() {
  // Get div1 and div2
  var div1 = document.getElementById( "div1" );
  var div2 = document.getElementById( "div2" );

  // Update the innerHTML field of both divs
  div1.innerHTML = "<h1>Updated the First Div</h1>";
  div2.innerHTML = "<h1>Updated the Second Div</h1>";
}

function changeButton() {
  // Get the button to change
  var button = document.getElementById( "buttonToChange" );

  // Update the button's value field
  button.value = "I was changed!";
}

function changeStyle() {
  // Get the first div
  var div1 = document.getElementById( "div1" );

  // Update the style.color property
  div1.style.color = "black";
}
