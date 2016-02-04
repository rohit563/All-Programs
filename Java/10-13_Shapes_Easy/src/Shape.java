	/**
	 * This is abstract class has methods that help create shapes
	 * @author rohit
	 */
public abstract class Shape {
	/**
	 * This variable holds the shape name
	 */
	private String shapeName;
	/**
	 * This method holds the shape name
	 * @param shapeName
	 * 			holds shape name
	 */
	public Shape(String shapeName) {
		// TODO Auto-generated constructor stub	
		this.shapeName = shapeName;
	}
	/**
	 * This method gets the shape name
	 * @return shapeName
	 * 			returns shape name
	 */
	public String getShapeName() {
		return shapeName;
	}
	/**
	 * This method sets the shape name
	 * @param shapeName
	 * 			holds shape name
	 */
	public void setShapeName(String shapeName) {
		this.shapeName = shapeName;
	}
	/**
	 * This is abstract method print
	 * allows the printing of each shape
	 */
	public abstract void print();

}
