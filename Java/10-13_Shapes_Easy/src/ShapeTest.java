/**
 * Main driver class tests all of the classes for each of the shapes
 * @author rbanda
 *
 */
public class ShapeTest {
	/**
	 * This creates an array of shapes
	 */
	private Shape array[];

	/**
	 * This method creates an array of shapes and prints of there volume or area depending on the type of shape
	 */
	public ShapeTest() {
		// TODO Auto-generated constructor stub
		array = new Shape[6];
	
		
		Circle circle = new Circle("Circle", 10);
		array[0] = circle;
		
		Square square = new Square("Square", 4);
		array[1] = square;

		
		Triangle triangle = new Triangle("Triangle", 5, 10);
		array[2] = triangle;
	
		
		Sphere sphere = new Sphere("Sphere", 25);
		array[3] = sphere;
	
		
		Cube cube = new Cube("Cube", 15);
		array[4] = cube;

		
		Tetrahedron tetrahedron = new Tetrahedron("tetrahedron", 6);
		array[5] = tetrahedron;

	}
	/**
	 * This method goes through the array and prints off the area and volume 
	 * depending on the type of shape 
	 *
	 */
	public void printInfo()
	{
		for(int i = 0; i < array.length; i++)
		{
			System.out.print(array[i].getShapeName() + ": ");
			array[i].print();
			if(array[i] instanceof TwoDShape){
				TwoDShape newShape = (TwoDShape) array[i];
				System.out.println("Area: " + newShape.getArea());
			}
			else if(array[i] instanceof ThreeDShape)
			{
				ThreeDShape newShape = (ThreeDShape) array[i];
				System.out.println("Area: " + newShape.getArea());
				System.out.println("Volume: " + newShape.getVolume());
			}
		}
	
	}
	/**
	 * This method will automatically be called when ShapeTest is run
	 * @param args
	 * 			Command line arguments.
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ShapeTest driver = new ShapeTest();
		driver.printInfo();

	}

}
