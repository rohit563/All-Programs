import javax.swing.JFrame;
/**
 * This class tests Planet and PlanetPanel
 * @author rohit
 *
 */
public class PlanetTest {

	/**
	 * This method is automatically called when PlanetTest is run
	 * @param args
	 * 			Command line arguments.
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PlanetPanel panel = new PlanetPanel();
		JFrame planetTest = new JFrame();
		planetTest.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		planetTest.add(panel);
		planetTest.setSize(800, 800);
		planetTest.setResizable(false);
		planetTest.setVisible(true);

	}

}
