import javax.swing.JFrame;
/**
 * Main driver class that test MyColorChooser.java nad Paint.java 
 * @author rbanda
 *
 */
public class ColorTester {

	
	/**
	 * This method will automatically be called when ColorTester is run.
	 * @param args
	 * 			Command line arguments.
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyColorChooser color = new MyColorChooser();
		color.setVisible(true);
		color.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
