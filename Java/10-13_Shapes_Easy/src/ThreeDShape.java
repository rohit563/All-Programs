	/**
	 * This is abstract class has methods that help create 3D shapes
	 * extends shape
	 * @author rohit
	 */
public abstract class ThreeDShape extends Shape{
	/**
	 * This method holds the shape name
	 * @param shapeName
	 * 			holds shape name
	 */
	public ThreeDShape(String shapeName) {
		// TODO Auto-generated constructor stub
		super(shapeName);
	
		
	}
	/**
	 * This this abstract method makes the getArea method 
	 * @return
	 * 	returns area
	 */
	public abstract int getArea();
	/**
	 * This this abstract method makes the getVolume method 
	 * @return
	 * 		returns volume
	 */
	public abstract int getVolume();

}
