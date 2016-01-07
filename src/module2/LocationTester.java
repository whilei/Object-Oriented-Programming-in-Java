package module2;

public class LocationTester {
	
	public static void main(String[] args)
	{
		SimpleLocation ucsd = 
				new SimpleLocation(32.9, -115.3);
		SimpleLocation boston = 
				new SimpleLocation(42, -72);
		
		System.out.println(ucsd.distance(boston));
	}
	
}
