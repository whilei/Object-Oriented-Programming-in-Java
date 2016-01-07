package module2;

public class SimpleLocation {

	// init declarations
	public double latitude;
	public double longitude;
	
	// da CONSTRUCTOR!!
		// note: name is same as class
	public SimpleLocation(double lat, double lon)
	{
		this.latitude = lat; // "this" refers to class
		this.longitude = lon;
	}
	
	public double getDist(double x1, double y1, double x2, double y2)
	{
		// find hypotenuse => pythagoras
		double tooBig = (x1-x2)*(x1-x2) + (y1-y2)*(y1-y2);
		double justRightDegrees = Math.sqrt(tooBig);
		double justRightMiles = justRightDegrees * 69; // close enough
		return justRightMiles; 
	}
	
	public double distance(SimpleLocation somewhereElse)
	{ 
		
		return getDist(this.latitude, this.longitude, somewhereElse.latitude, somewhereElse.longitude);	
		
	}
}
