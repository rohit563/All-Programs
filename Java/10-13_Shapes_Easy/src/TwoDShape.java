	/**
	 * This is abstract class has methods that help create 2D shapes
	 * extends shape
	 * @author rohit
	 */
public abstract class TwoDShape extends Shape{
	/**
	 * This method holds the shape name
	 * @param shapeName
	 * 			holds shape name
	 */
	public TwoDShape(String shapeName) {
		// TODO Auto-generated constructor stub
		super(shapeName);
	}
	/**
	 * This this abstract method makes the getArea method 
	 * @return
	 * 		return area
	 */
	public abstract int getArea();

}
