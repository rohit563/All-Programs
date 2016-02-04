	/**
	 * This is a class that lets you create the shape tetrahedron
	 * extends the ThreeDShape class
	 * @author rohit
	 */
public class Tetrahedron extends ThreeDShape{
	/**
	 * This variable holds the length of the side
	 */
	private int side;
	/**
	 * This method creates the shape
	 * @param shapeName
	 * 				This takes in the shapes name
	 * @param side
	 * 				This takes in the side
	 */
	public Tetrahedron(String shapeName, int side) {
		// TODO Auto-generated constructor stub
		super(shapeName);
		this.side = side;
	}
	/**
	 * This method calculates the area of the sphere 
	 */
	public int getArea()
	{
		double area = (Math.sqrt(3)/4) * Math.pow(this.side, 2);
		return (int) area;
	}
	/**
	 * This method calculates the volume of the sphere
	 */
	public int getVolume()
	{
		double volume = Math.sqrt(2)* Math.pow(3, .25) * (Math.pow(getArea(), 1.5)/36);
		return (int) volume;
	}
	/**
	 * This method prints the shape name along with the side of the tetrahedron to the console 
	 */
	public void print() {
		System.out.println("(" + super.getShapeName() + ") " + "side: " + this.side);
		
	}

}
