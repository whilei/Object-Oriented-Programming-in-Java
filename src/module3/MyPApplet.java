package module3;

import processing.core.*; // import everything from Processing Core library

public class MyPApplet extends PApplet {
	
	private String URL = "http://thecatapi.com/api/images/get?format=src&type=jpg"; // random cat
	private PImage backgroundImg;
	
	public void setup() {
		size(200, 200);
		backgroundImg = loadImage(URL, "jpg"); // loads image into memory
	}
	
	public void draw() {
		backgroundImg.resize(0, height); // 0 says 'I don't want to specify width; match original and calculate proportion.'
										 // height is instance variable in PApplet class; reference canvas height. DYNAMIC! 

		// **there is a typo in the video; backgroundImg is a PImage, which should the be the first
		// argument of the image() method, followed by some floats. 
		// http://processing.github.io/processing-javadocs/core/
		image(backgroundImg, 0, 0); // coords for top-left corner of image
	}
}
