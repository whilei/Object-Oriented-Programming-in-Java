package module2;

public class LocationTester {
	
	public static void main(String[] args)
	{
		SimpleLocation ucsd = 
				new SimpleLocation(32.9, -115.3);
		SimpleLocation boston = 
				new SimpleLocation(42, -72); 
		SimpleLocation theFarm = 
				new SimpleLocation(); // construction overlord!
		
		System.out.println(ucsd.distance(boston));
		System.out.println(ucsd.distance(49, -82));
		System.out.println(theFarm.distance(0, 0));
		
//		System.out.println(ucsd.latitude); error!
		System.out.println(ucsd.getLatitude());
	}
	
}
