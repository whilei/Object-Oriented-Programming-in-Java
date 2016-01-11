package module3;

//Java utilities libraries
import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Comparator;
import java.util.List;

//Processing library
import processing.core.PApplet;

//Unfolding libraries
import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.providers.MBTilesMapProvider;
import de.fhpotsdam.unfolding.utils.MapUtils;

//Parsing library
import parsing.ParseFeed;

/** EarthquakeCityMap
 * An application with an interactive map displaying earthquake data.
 * Author: UC San Diego Intermediate Software Development MOOC team
 * @author isaac
 * Date: 20160111
 * */
public class EarthquakeCityMap extends PApplet {

	// -------- IGNORE -------- //
	// You can ignore this.  It's to keep eclipse from generating a warning.
	private static final long serialVersionUID = 1L;
	// IF YOU ARE WORKING OFFLINE, change the value of this variable to true
	private static final boolean offline = false;
	/** This is where to find the local tiles, for working without an Internet connection */
	public static String mbTilesString = "blankLight-1-3.mbtiles";
	
	
	// -------- THRESHOLDS -------- //
	// Less than this threshold is a light earthquake
	public static final float THRESHOLD_MODERATE = 5;
	// Less than this threshold is a minor earthquake
	public static final float THRESHOLD_LIGHT = 4;

	
	// The map
	private UnfoldingMap map;
	
	//feed with magnitude 2.5+ Earthquakes
	private String earthquakesURL = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/2.5_week.atom";

	
	public void setup() {
		size(950, 700, OPENGL);

		if (offline) {
		    map = new UnfoldingMap(this, 200, 50, 700, 500, new MBTilesMapProvider(mbTilesString));
		    earthquakesURL = "2.5_week.atom"; 	// Same feed, saved Aug 7, 2015, for working offline
		}
		else {
			map = new UnfoldingMap(this, 50, 85, 700, 500, new Google.GoogleMapProvider());
			// IF YOU WANT TO TEST WITH A LOCAL FILE, uncomment the next line
			//earthquakesURL = "2.5_week.atom";
		}
		
	    map.zoomToLevel(2);
	    MapUtils.createDefaultEventDispatcher(this, map);	
			
	    // The List you will populate with new SimplePointMarkers
	    List<Marker> markers = new ArrayList<Marker>();

	    //Use provided parser to collect properties for each earthquake
	    //PointFeatures have a getLocation method
	    List<PointFeature> earthquakes = ParseFeed.parseEarthquake(this, earthquakesURL);
	    
	    // These print statements show you (1) all of the relevant properties 
	    // in the features, and (2) how to get one property and use it
	    if (earthquakes.size() > 0) {
	    	PointFeature f = earthquakes.get(0);
	    	System.out.println(f.getProperties());
	    	Object magObj = f.getProperty("magnitude");
	    	float mag = Float.parseFloat(magObj.toString());
	    	
	    	// PointFeatures also have a getLocation method
	    	Object loc = f.getLocation();
	    	System.out.println("loc: " + loc);
	    }
	    
	    // Here is an example of how to use Processing's color method to generate 
	    // an int that represents the color yellow.  
	    int yellow = color(255, 255, 0);
	    
	    //TODO: Add code here as appropriate
	    // Add marker for each earthquake in earthquakes. 
	    // http://www.mkyong.com/java/how-to-loop-arraylist-in-java/
	    for ( int i = 0; i < earthquakes.size(); i++ ) {
//	    	System.out.println(earthquakes.get(i));
	    	createMarker(earthquakes.get(i));
	    }
	}
		
	// A suggested helper method that takes in an earthquake feature and 
	// returns a SimplePointMarker for that earthquake
	// TODO: Implement this method and call it from setUp, if it helps
	private SimplePointMarker createMarker(PointFeature feature)
	{
		// finish implementing and use this method, if it helps.
//		return new SimplePointMarker(feature.getLocation());
		
		// http://unfoldingmaps.org/javadoc/de/fhpotsdam/unfolding/marker/SimplePointMarker.html
		SimplePointMarker spoint = new SimplePointMarker(feature.getLocation());  
		
		Object magnitudeObj = feature.getProperty("magnitude");
		float magnitude = Float.parseFloat(magnitudeObj.toString());
		// see what our range is like
//			System.out.println(magnitude); // 2.5 - 6, call it 8
		// remember we have predefined THRESHHOLDs above, and that feed is only for 2.5+
		
		// Modify marker to show earthquake magnitude.

		// radius
		float magRad = magnitude * 2; // set radius to depend on magnitude
		spoint.setRadius(magRad); 

		// color
			// should be between 10-255, given 2.5-8. i sense a map.
			// I want light red for minimal, medium red for medium, and dark red for big deal quakes
		
		float colorLevel = map(magnitude, (float) 2.5, (float) 8, (float) 10, (float) 255); // map 2.5-8 => 10-255
		
		// http://unfoldingmaps.org/tutorials/markers-simple.html
		spoint.setColor(color(colorLevel, 0, 0, colorLevel)); // (that fourth arg is for the alpha value, which should be between 0 (transparent) and 1.0 (opaque) or, as in this case :: 0-255 
		spoint.setStrokeColor(color(255, 0, 0, 0)); // transparent stroke (= outline)
//		spoint.setStrokeWeight(4);
		
		// Now, if you wanted to actually follow the rules and fulfill assignment requirement #4, where "as a minimum you should have the size and color change for at least 3 different levels of earthquake", then instead of the directly above, you'd want to set threshhold-based if: 
		// light
		if (magnitude <= THRESHOLD_LIGHT) {
			// spoint.setColor(color(0, colorLevel, colorLevel, colorLevel));
		}
		// moderate
		if (magnitude >= THRESHOLD_LIGHT && magnitude < THRESHOLD_MODERATE) {
			// spoint.setColor(color(colorLevel, 0, 0, colorLevel));
		} 
		// heavy
		if (magnitude >= THRESHOLD_MODERATE) {
			spoint.setStrokeColor(color(255, 0, 0, colorLevel));
			spoint.setStrokeWeight(Math.round(magnitude)); // round float to int
		}
		
		
		map.addMarker(spoint); // add each marker to map (only once) 
		return spoint;
	}
	
	public void draw() {
	    background(10);
	    map.draw();
	    addKey();
	}


	// helper method to draw key in GUI
	// TODO: Implement this method to draw the key
	private void addKey() 
	{	
		// Remember you can use Processing's graphics methods here
//		rect(50, 50, 100, 200, 7); // 7 is border radius i assume
		text("Color and opacity correspond to charted magnitude of earthquake.", 55, 55);
		text("Earthquakes catalogued as greater than moderate (> " + THRESHOLD_MODERATE + ") are outlined in a border also corresponding to their relative magnitude.", 55, 70);
	
	}
}
