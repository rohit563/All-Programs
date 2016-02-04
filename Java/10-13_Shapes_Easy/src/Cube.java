	/**
	 * This is a class that lets you create the shape Cube
	 * extends the ThreeDShape class
	 * @author rohit
	 */
public class Cube extends ThreeDShape {
	/**
	 * This variable holds the length of the side of the cube
	 */
	private int side;
	/**
	 * This method creates the the shape
	 * @param shapeName
	 * 				Takes in shape name
	 * @param side
	 * 				Takes in the the length of the side of the cube
	 */
	public Cube(String shapeName, int side) {
		// TODO Auto-generated constructor stub
		super(shapeName);
		this.side = side;
	}
	/**
	 * This method calculates the area of the circle 
	 */
	public int getArea()
	{
		double area = Math.pow(this.side, 2);
		return (int) area;
	}
	/**
	 * This method calculates the volume of the cube
	 */
	public int getVolume()
	{
		double volume = Math.pow(this.side, 3);
		return (int) volume;
	}
	/**
	 * This method prints the shape name along with the side of the cube to the console 
	 */
	public void print() {
		System.out.println("(" + super.getShapeName() + ") " + "side: " + this.side);
		
	}

}
