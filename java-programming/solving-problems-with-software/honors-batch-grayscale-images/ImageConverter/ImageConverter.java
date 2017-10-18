package chasemh.java.coursera;

import edu.duke.*;
import java.io.*;
import org.apache.commons.csv.*;

/**
 * Exercise solutions from the honors Batch Grayscale Images Section
 * https://www.coursera.org/learn/java-programming/supplement/X0D39/programming-exercise-batch-grayscale-and-image-inversion
 *
 * @author Chase Hennion
 * @version 2017-10-18
 */
public class ImageConverter {
	
	public ImageResource getGrayScale( ImageResource origImg ) {
		
		// Create a new ImageResource of the same dimensions as origImg
		// Iterate through the pixels in origImg
			// Get the x and y coords of the pixel
			// Get the average RBG value of the pixel in origImg
		    // Set the RGB values in the output image to the average RGB value from origImg
		// Return the output image
		
		ImageResource outImg = new ImageResource( origImg.getWidth(), origImg.getHeight() );
		
		for( Pixel origPixel : origImg.pixels() ) {
			Pixel pixelToModify = outImg.getPixel( origPixel.getX(), origPixel.getY() );
			int avgColorVal = ( origPixel.getRed() + origPixel.getGreen() + origPixel.getBlue() ) / 3;
			pixelToModify.setRed( avgColorVal );
			pixelToModify.setGreen( avgColorVal );
			pixelToModify.setBlue( avgColorVal );
		}
		
		return outImg;
	}
	
	public ImageResource getInverted( ImageResource origImg ) {
		
		ImageResource outImg = new ImageResource( origImg.getWidth(), origImg.getHeight() );
		
		for( Pixel origPixel : origImg.pixels() ) {
			Pixel pixelToModify = outImg.getPixel( origPixel.getX(), origPixel.getY() );
			pixelToModify.setRed( 255 - origPixel.getRed() );
			pixelToModify.setGreen( 255 - origPixel.getGreen() );
			pixelToModify.setBlue( 255 - origPixel.getBlue() );
		}
		
		return outImg;
		
	}
	
	public void batchGrayscale() {
		
		// Let user select multiple files <-- DirectoryResource
		// For each file 
			// Make an image resource out of the file 
			// Set an outImage resource to the output of toGrayScale( originalImage );
			// Write the image to file
		
		DirectoryResource dr = new DirectoryResource();
		
		for( File f : dr.selectedFiles() ) {
			ImageResource origImg = new ImageResource( f );
			ImageResource modImg = getGrayScale( origImg );
			modImg.setFileName( "gray-" + origImg.getFileName() );
			modImg.save();
		}
			
	}
	
	public void batchInvert() {
		
		DirectoryResource dr = new DirectoryResource();
		
		for( File f : dr.selectedFiles() ) {
			ImageResource origImg = new ImageResource( f );
			ImageResource modImg = getInverted( origImg );
			modImg.setFileName( "inverted-" + origImg.getFileName() );
			modImg.save();
		}
		
	}

	public static void main(String[] args) {
		
		ImageConverter ic = new ImageConverter();
		//ic.batchGrayscale();
		ic.batchInvert();

	}

}
