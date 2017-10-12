# Event-Driven Programming Notes

## Buttons with Divs  

* Can setup event driven functions for webpages using Javascript and HTML `<input>` elements.
* Example HTML Button `<input>` element
```html
<input type = "button" value = "Click Me" onclick = "jsFunction()" >
```
This will create an HTML button that displays "Click Me". When the button is clicked
the function `jsFunction()` will be executed.

## Changing Pages Interactively  

* JavaScript can be used to access and change HTML elements.
* Can access all elements by types, elements by CSS class and elements by ID
  * IDs are unique so they are good for targeting single elements
* Use `document.getElementById("ID")` to retrieve HTML elements by ID
  * The document object is the entire HTML page
* Update the `className` parameter/field to update an element's CSS class.
* To update the HTML within a div use the `innerHTML` field.

## Using HTML5 Canvas
* `<canvas>` HTML element used to store images. Used with image processing.
* Used in many apps to display graphical content
* Need special methods to add text to a canvas element
* `<canvas>` is a container for graphics. Use scripts to draw the graphics.
* To render things within a canvas, you must update the canvas' context.
  * Get the canvas' two dimensional context with `getContext("2d")`

## Inputs and Events
* Many kinds of `<input>` types
  * Button
  * Text Entry (text)
  * Color Picker (Uses `onchange` event)
  * Range/Slider (range)
  * File Picker (file)
* Many kind of input events as well
  * onclick
  * oninput
  * onchange

# Green Screen Web Page

## Upload and Display an Image

* Start with simpler text entry upload and then move to using a file picker
* Include external JS libraries using the `<script>` HTML element and adding the source of the JS library.

## Convert an Image to Grayscale

* Global Variables: Accessable in all functions
* Global variables declared outside of functions
* Minimize usage of global variables (Can create confusion, security vulnerabilities)

## Moving to CodePen
