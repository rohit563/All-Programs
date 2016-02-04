	/**
	 * This is a class that lets you create the shape square
	 * extends the TwoDShape class
	 * @author rohit
	 */
public class Square extends TwoDShape {
	/**
	 * This variable holds the length of the side of the square
	 */
	private int side;
	/**
	 * This method creates the shape
	 * @param shapeName
	 * 				This takes in the shapes name
	 * @param side
	 * 				This takes in the side
	 */
	public Square(String shapeName, int side) {
		// TODO Auto-generated constructor stub
		super(shapeName);
		this.side = side;
	}
	/**
	 * This method prints the shape name along with the side of the square to the console 
	 */
	public void print()
	{
		System.out.println("(" + super.getShapeName() + ") " + "side:" + this.side);
		
	}
	/**
	 * This method calculates the area of the sphere 
	 */
	public int getArea()
	{
		double area = Math.pow(this.side, 2);
		return (int) area;
	}

}
