package module3;

// import UnfoldingMap stuff as suggested by Eclipse
import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.GeoJSONReader;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.utils.MapUtils;

import processing.core.*; // import everything from Processing Core library (includes PApplet)

// we'll need: 
	// LifeExpectancyWorldBank.csv
	// countries.geo.json
// **both are already in UCSDUnfoldingMaps/data/ 

public class LifeExpectancy extends PApplet {
	
	UnfoldingMap map;
	
	//	Ordered list of things of type Feature and Marker
	List<Marker> countries; 
//	List<Feature> countries = new ArrayList(); // another Abstract Data Type
	List<Marker> countryMarkers;
	
	Map<String, Float> lifeExpByCountry; // <stuff> means Generics
	// Map is Abstract Data Type (more to follow)
		// key => value :: 
		// countryID => lifeExp
	
	
	private Map<String, Float> loadLifeExpectancyFromCSV(String fileName) {
		
		Map<String, Float> lifeExpMap = new HashMap<String, Float>(); // Reference type :: CONSTRUCTOR
		
		String[] rows = loadStrings(fileName);
		
		for (String row : rows) {
			String[] columns = row.split(",");
			if ( ... ) { // if it looks legit
				float value = Float.parseFloat(columns[5]); // where lifeExp value is
															// cast string to float
				lifeExpMap.put(columns[4], value); // where countryId is
			}
		}
		
		return lifeExpMap;
	}
	
	private void shadeCountries() {
		for (Marker marker : countryMarkers) {
			String countryId = marker.getId();
			
			if (lifeExpMap.containsKey(countryId)) {
				float lifeExp = lifeExpMap.get(countryId);
				// map from 40-90 to 10-255 :: lifeExp to rgb compatible
				int colorLevel = (int) map(lifeExp, 40, 90, 10, 255); // map lets us take a number from a range and map it into another range
				marker.setColor(color(255-colorLevel, 100, colorLevel));
			}
			else {
				marker.setColor(color(150, 150, 150)); // gray
			}
		}
	}
	
	public void setup() {
		size(800, 600, OPENGL);
		map = new UnfoldingMap(this, 50, 50, 700, 500, new Google.GoogleMapProvider());
		MapUtils.createDefaultEventDispatcher(this, map);
		
		lifeExpByCountry = loadLifeExpectancyFromCSV("data/LifeExpectancyWorldBank.csv"); // helper method
		
		countries = GeoJSONReader.loadData(arg0,  "data/countries.geo.json");
		countryMarkers = MapUtils.createSimpleMarkers(countries);
		
		map.addMarkers(countryMarkers);
		shadeCountries(); // helper
	}
	
	public void draw() {
		
	}
	
}
