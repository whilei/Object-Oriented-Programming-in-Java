package module3;

import processing.core.*; // import everything from Processing Core library
import java.util.Calendar; // http://stackoverflow.com/questions/907170/java-getminutes-and-gethours

public class MyPApplet extends PApplet {
	
	private String URL = "http://thecatapi.com/api/images/get?format=src&type=jpg"; // random cat
	private PImage backgroundImg;
	
	// run ONCE
	public void setup() {
		size(200, 200);
		backgroundImg = loadImage(URL, "jpg"); // loads image into memory
	}
	
	// run OVER AND OVER AND OVER
	public void draw() {
		backgroundImg.resize(0, height); // 0 says 'I don't want to specify width; match original and calculate proportion.'
										 // height is instance variable in PApplet class; references canvas height. DYNAMIC! 

		// **there is a typo in the video; backgroundImg is a PImage, which should the be the first
		// argument of the image() method, followed by some floats. 
		// http://processing.github.io/processing-javadocs/core/
		image(backgroundImg, 0, 0); // coords for top-left corner of image
		
		Calendar now = Calendar.getInstance();
		int hour = now.get(Calendar.HOUR_OF_DAY);
		int useableHour = 12 % hour; // make it so its brightest at noon and dark at midnights
									 // @23 => 11, @3 => 9, @12 => 0
		int blueish = useableHour * 20; // approximate between 0-240 which is close enough to 255
										// at hour = 12 :: blueish = 0; hour = 2 or 22 :: blueish = ~200
		// check
//			System.out.println("useableHour: " + useableHour); // a million bajillion times
		
		fill(255, 209, blueish); // ellipse will be filled with this color
		
		ellipse(width/4, height/5, width/5, height/5); // xcoord, ycoord, width, height
													   // ellipse depends on canvas dimensions
		// #FFD100; rgb 0-255
		
	}
}
