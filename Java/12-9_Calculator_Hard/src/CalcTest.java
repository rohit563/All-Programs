import javax.swing.JFrame;

/**
 * Main driver class that test CalcFrame.java 
 * 
 * @author Rohit Banda
 */
public class CalcTest {

	/**
	 * This method will automatically be called when CalcTest.java is
	 * run
	 * 
	 * @param args
	 * 			Command line arguments.
	 * 
	 */
	public static void main(String[] args) {
		CalcFrame calc = new CalcFrame();
		calc.setVisible(true);
		calc.setResizable(false);
		calc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
