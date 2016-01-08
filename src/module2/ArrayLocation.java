package module2;

public class ArrayLocation {

	// member vars 
	private double coords[];
	
	// constructor
	public ArrayLocation(double[] coords) {
		this.coords = coords;
	}
	
	public static void main(String[] args) {
		
		double[] coords = {5.0, 0.0};
		ArrayLocation accra = new ArrayLocation(coords); // ArrayLocation object build with reference to coords array (which is an OBJECT, not a var)
		
		coords[0] = 32.9; // coords var changed
//		coords[1] = -118.4;
		
		System.out.println(accra.coords[0] + ", " + accra.coords[1]); // 32.9 

	}

}
