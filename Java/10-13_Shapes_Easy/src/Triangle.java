	/**
	 * This is a class that lets you create the shape triangle
	 * extends the TwoDShape class
	 * @author rohit
	 */
public class Triangle extends TwoDShape{
	/**
	 * This variable holds the triangle base
	 */
	private int base;
	/**
	 * This variable holds the triangle height
	 */
	private int height;
	/**
	 * This method creates the the shape
	 * @param shapeName
	 * 				Takes in shape name
	 * @param base
	 * 				Takes in the the base
	 * @param height
	 * 				Takes in the height
	 */
	public Triangle(String shapeName, int base, int height) {
		// TODO Auto-generated constructor stub
		super(shapeName);
		this.base=base;
		this.height=height;
	}
	/**
	 * This method prints the shape name along with the base and height of the triangle to the console 
	 */
	public void print()
	{
		System.out.println("(" + super.getShapeName() + ") " + "base:" + this.base + " height:" + this.height );
		
	}
	/**
	 * This method calculates the area of the circle 
	 */
	public int getArea()
	{
		double area = (.5) * this.base * this.height;
		return (int) area;
	}
	
	
}
