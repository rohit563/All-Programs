import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * @author Rohit
 * This class has two public methods 
 */
public class CalcFrame extends JFrame implements ActionListener {

	/**
	 * This creates the textField to display calculations
	 */
	private final JTextField textField;
	/**
	 * This creates the buttons for the calculator
	 */
	private final JButton[] buttons;
	/**
	 * This creates the panel to add buttons to the GUI
	 */
	private final JPanel panel;
	/**
	 * This creates the panel to add the TextField to
	 */
	private final JPanel panel2;
	/**
	 * This creates the string that holds the numbers for calculations
	 */
	private String num1 = "";
	/**
	 * This creates a string that holds the operators for the math functions
	 */
	private String sign = "";
	/**
	 * This creates the holder of the first number the user enters
	 */
	private double firstNum;
	/**
	 * This is a check that sees if an operator can be clicked or not
	 */
	private int parse;
	/**
	 * This checks to see if the equal button can be clicked or not
	 */
	private int equals;
	/**
	 * This check prevents clicking on the decimal more than once
	 */
	private int decimal = 0;
	/**
	 * This is a placeholder that holds the second number the user enter after they click on an operator
	 */
	private double secondNum;
	/**
	 * This creates a double that holds the total calculated by the user
	 */
	private double total;
	/**
	 * This creates an array list that holds the numbers that are put in from the num1 string
	 */
	private ArrayList<Double> numbas = new ArrayList<>();

	/**
	 * Lays out all the buttons and the textfield as well as the panel for the
	 * calculator
	 */
	public CalcFrame() {
		// TODO Auto-generated constructor stub
		super("Calculator");
		setBounds(100, 100, 300, 400);
		// setLayout(new GridLayout(4,4));
		textField = new JTextField(23);
		textField.setEditable(false);
		panel = new JPanel();
		panel2 = new JPanel();
		buttons = new JButton[16];
		String myList = "789/456*123-0.=+";
		panel.setLayout(new GridLayout(0, 4));
		panel2.add(textField);
		for (int count = 0; count < buttons.length; count++) {

			buttons[count] = new JButton("" + (myList.charAt(count)));
			buttons[count].addActionListener(this);
			panel.add(buttons[count]);

		}
		add(panel2, BorderLayout.NORTH);
		add(panel, BorderLayout.CENTER);

	}

	/**
	 * @param e
	 *            Take in the action key of a button click and computes
	 *            appropriately
	 */
	public void actionPerformed(ActionEvent e) {

		for (int i = 0; i < buttons.length; i++) {
			if (e.getSource() == buttons[i]) {
				if (buttons[i].getText().equals("*")) {

					if (parse == 1) {
						numbas.add(Double.parseDouble(num1));
						sign = sign + buttons[i].getText();
						num1 = "";
						equals = 1;
						decimal = 0;
					}
					parse = 0;

				} else if (buttons[i].getText().equals("/")) {

					if (parse == 1) {
						numbas.add(Double.parseDouble(num1));
						sign = sign + buttons[i].getText();
						num1 = "";
						equals = 1;
						decimal = 0;
					}
					parse = 0;

				} else if (buttons[i].getText().equals("+")) {
					if (parse == 1) {
						numbas.add(Double.parseDouble(num1));
						sign = sign + buttons[i].getText();
						num1 = "";
						equals = 1;
						decimal = 0;
					}
					parse = 0;
				} else if (buttons[i].getText().equals("-")) {
					if (parse == 1) {
						numbas.add(Double.parseDouble(num1));
						sign = sign + buttons[i].getText();
						num1 = "";
						equals = 1;
						decimal = 0;
					}
					parse = 0;

				} else if (buttons[i].getText().equals("=")) {
					if (equals == 1) {
						numbas.add(Double.parseDouble(num1));
						for (int j = 0; j < sign.length(); j++) {
							if ((Character.toString(sign.charAt(j))).equals("*")) {
								if (j == 0) {

									firstNum = numbas.get(0);
									numbas.remove(0);
									secondNum = numbas.get(0);
									numbas.remove(0);

									System.out.println(firstNum);
									total = firstNum * secondNum;
								} else {
									firstNum = numbas.get(0);
									total = total * firstNum;
									numbas.remove(0);
								}
							} else if ((Character.toString(sign.charAt(j))).equals("/")) {
								if (j == 0) {

									firstNum = numbas.get(0);
									numbas.remove(0);
									secondNum = numbas.get(0);
									numbas.remove(0);

									System.out.println(firstNum);
									total = firstNum / secondNum;
								} else {
									firstNum = numbas.get(0);
									total = total / firstNum;
									numbas.remove(0);
								}
							} else if ((Character.toString(sign.charAt(j))).equals("+")) {
								if (j == 0) {

									firstNum = numbas.get(0);
									numbas.remove(0);
									secondNum = numbas.get(0);
									numbas.remove(0);

									System.out.println(firstNum);
									total = firstNum + secondNum;
								} else {
									firstNum = numbas.get(0);
									total = total + firstNum;
									numbas.remove(0);
								}
							} else if ((Character.toString(sign.charAt(j))).equals("-")) {
								if (j == 0) {

									firstNum = numbas.get(0);
									numbas.remove(0);
									secondNum = numbas.get(0);
									numbas.remove(0);

									System.out.println(firstNum);
									total = firstNum - secondNum;
								} else {
									firstNum = numbas.get(0);
									total = total - firstNum;
									numbas.remove(0);
								}
							}

						}
						textField.setText(Double.toString(total));
						sign = "";
						num1 = "";
						parse = 0;
						equals = 0;
						decimal = 0;
					}

				} else {

					if ((decimal == 0) || !(buttons[i].getText().equals("."))) {
						parse = 1;
						num1 = num1 + buttons[i].getText();
						textField.setText(num1);
						if ((buttons[i].getText().equals("."))) {
							decimal = 1;
						}
					}

				}

			}
		}

	}

}
