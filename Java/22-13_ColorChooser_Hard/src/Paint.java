import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * This class has 7 public methods
 * @author rbanda 
 */
public class Paint extends JPanel{

	private Color color;
	private int red, green, blue;
    public Paint(Color color) {
    	this.color = color;
    	red = color.getRed();
    	green = color.getGreen();
    	blue = color.getBlue();
    }
	/**
	 * This is creates the rectangle and fills it with the color
	 * @param g
	 * 			generates the properties of the shape 
	 */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = getWidth();
        int height = getHeight();
        g.setColor(color);
        g.fillRect(0, 0, width, height);
        
    }
	/**
	 * This allows the user to set the color red
	 * @param red
	 * 			this sets the value of the color red  
	 */
    public void setRed(int red)
    {
    	this.red = red;
    	colorChange();
    }
	/**
	 * This allows the user to set the color green
	 * @param green
	 * 			this sets the value of the color green  
	 */
    public void setGreen(int green)
    {
    	this.green = green;
    	colorChange();
    }
	/**
	 * This allows the user to set the color blue
	 * @param blue
	 * 			this sets the value of the color blue  
	 */
    public void setBlue(int blue)
    {
    	this.blue = blue;
    	colorChange();
    }
	/**
	 * This sets the color 
	 * 
	 * 			  
	 */
    private void colorChange()
    {
    	color = new Color(red,green,blue);
    	repaint();
    }

}
