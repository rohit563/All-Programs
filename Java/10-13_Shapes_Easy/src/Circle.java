	/**
	 * This is a class that lets you create the shape circle
	 * extends the TwoDShape class
	 * @author rohit
	 */
public class Circle extends TwoDShape{
	/**
	 * This variable holds the circles radius
	 */
	private int radius;
	/**
	 * This method creates the the shape
	 * @param shapeName
	 * 				Takes in shape name
	 * @param radius
	 * 				Takes in the the radius of the circle
	 */
	public Circle(String shapeName, int radius) {
		// TODO Auto-generated constructor stub
		super(shapeName);
		this.radius = radius;
	}
	/**
	 * This method prints the shape name along with the radius of the circle to the console 
	 */
	public void print()
	{
		System.out.println("(" + super.getShapeName() + ") " + "radius: " + this.radius);
	}
	/**
	 * This method calculates the area of the circle 
	 */
	public int getArea()
	{
		double area = (Math.PI * Math.pow(this.radius, 2));
		return (int) area;
	}

}
