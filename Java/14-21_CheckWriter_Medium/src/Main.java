import java.text.DecimalFormat;
import java.util.Scanner;
/**
 * Main driver class tests the class numericToWordConvert
 * @author rbanda
 *
 */
public class Main {
	/**
	 * This method will automatically be called when main is run
	 * @param args
	 * 			Command line arguments.
	 */

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int validInput = 0;
		while (validInput == 0) {
			System.out.println("Please enter a check amount that is less than $1000");
			Scanner scan = new Scanner(System.in);
			Double userNum = scan.nextDouble();
			DecimalFormat money = new DecimalFormat("#000.00");
			String formatted = money.format(userNum);
			NumericToWordConvert e = new NumericToWordConvert();
			e.numToString(formatted);
			System.out.println(e.getNumWordString());
			if (userNum < 1000) {
				validInput = 1;
			}
		}

	}

}
