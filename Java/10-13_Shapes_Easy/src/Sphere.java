	/**
	 * This is a class that lets you create the shape Sphere
	 * extends the ThreeDShape class
	 * @author rohit
	 */
public class Sphere extends ThreeDShape {
	/**
	 * This variable holds the length of the radius
	 */
	private int radius;
	/**
	 * This method creates the shape
	 * @param shapeName
	 * 				This takes in the shapes name
	 * @param radius
	 * 				This takes in the radius
	 */
	public Sphere(String shapeName, int radius) {
		// TODO Auto-generated constructor stub
		super(shapeName);
		this.radius = radius;
		
	}
	/**
	 * This method calculates the area of the sphere 
	 */
	public int getArea()
	{
		double area = Math.PI * 4 * Math.pow(this.radius, 2);
		return (int) area;
	}
	/**
	 * This method calculates the volume of the sphere
	 */
	public int getVolume()
	{
		double volume = (1.33 * Math.PI * Math.pow(this.radius, 3));
		return (int) volume;
	}
	/**
	 * This method prints the shape name along with the radius of the sphere o the console 
	 */
	public void print()
	{
		System.out.println("(" + super.getShapeName() + ") " + "radius: " + this.radius);
	}
}
