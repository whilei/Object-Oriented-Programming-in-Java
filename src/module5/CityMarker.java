package module5;

import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;
import processing.core.PConstants;
import processing.core.PGraphics;

/** Implements a visual marker for cities on an earthquake map
 * 
 * @author UC San Diego Intermediate Software Development MOOC team
 * @author Your name here
 *
 */
// TODO: Change SimplePointMarker to CommonMarker as the very first thing you do 
// in module 5 (i.e. CityMarker extends CommonMarker).  It will cause an error.
// That's what's expected.
public class CityMarker extends CommonMarker {
	
	public static int TRI_SIZE = 5;  // The size of the triangle marker
	
	public CityMarker(Location location) {
		super(location);
	}
	
	
	public CityMarker(Feature city) {
		super(((PointFeature)city).getLocation(), city.getProperties());
		// Cities have properties: "name" (city name), "country" (country name)
		// and "population" (population, in millions)
	}

	
	/**
	 * Implementation of method to draw marker on the map.
	 */
	public void draw(PGraphics pg, float x, float y) {
		// Save previous drawing style
		pg.pushStyle();
		
		// IMPLEMENT: drawing triangle for each city
		pg.fill(150, 30, 30);
		pg.triangle(x, y-TRI_SIZE, x-TRI_SIZE, y+TRI_SIZE, x+TRI_SIZE, y+TRI_SIZE);
		
		// Restore previous drawing style
		pg.popStyle();
	}
	
	/** Show the title of the city if this marker is selected */
	public void showTitle(PGraphics pg, float x, float y)
	{
		
		// TODO: Implement this method
//		pg.text(getCity()+","+getCountry()+","+getPopulation(), x, y);
//		pg.rect(x, x, 100, 100);
		String shouldSay = getCity() + ", " + getCountry() + " | pop: " + getPopulation();
		System.out.println(x + ", " + y);
		System.out.println(shouldSay);
		
		pg.popStyle();
		pg.pushStyle();
//		pg.pushStyle();
		
//		pg.fill(255,255,255); // white
		
		// Note: This element neither has attached source nor attached Javadoc and hence no Javadoc could be found.
//		pg.rectMode(PConstants.CORNER); // I don't know what the hell this does. 
		
//		pg.rect(25, 25, 50, 200);
//		pg.color(0, 0, 0);
		
//		pg.text(shouldSay, 10, 10);
//		pg.fill(100,100,100);
//		pg.rect(x, y, 50, 50);
		
//		pg.popStyle();
	}
	
	
	
	/* Local getters for some city properties.  
	 */
	public String getCity()
	{
		return getStringProperty("name");
	}
	
	public String getCountry()
	{
		return getStringProperty("country");
	}
	
	public float getPopulation()
	{
		return Float.parseFloat(getStringProperty("population"));
	}


	@Override
	public void drawMarker(PGraphics pg, float x, float y) {
		// TODO Auto-generated method stub
		
	}
}
