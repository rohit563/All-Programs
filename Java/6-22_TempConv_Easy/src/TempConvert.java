import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * Main driver that tests Calc.java calculations 
 * 
 * @author rbanda
 *
 */
public class TempConvert {


	/**
	 * This method will be automatically called when TempConvert is run.
	 * 
	 * @param args
	 * 			Command line arguments.
	 */
	public static void main(String[] args) {
		// prints of menu for the user and calls Calc.java funtions to do conversions
		int userInput = 0;
		int temp = 0;
		int looper = 0;
		int looper2 = 0;
		Calc e = new Calc();
		while(looper == 0)
		{
			Scanner scan = new Scanner(System.in);
			while(looper2 == 0)
			{	
	   		 	try {
	   		 			System.out.print("Enter 1 for Fahrenheit to Celsius and Enter 2 for Celsius to Fahrenheit: ");
	   		 			userInput = Integer.parseInt(scan.nextLine());
	   		 			looper2 = 1;
	   		 		}catch (NumberFormatException e1) {
	   		 			System.out.println("enter a valid number");
	   		 			
	   		 		}
			}
		
			switch (userInput) {
	        case 1:  System.out.print("Please enter a temperature in Fahrenheit to convert to Celsius: ");
		        		 try {
		        			 	temp = (int) Double.parseDouble(scan.nextLine());
		        			 	System.out.println(temp + " degrees Fahrenheit in Celsius is " + e.calcCelsius(temp) + " degrees");
		        			 	looper = 1;
		        			 	scan.close();
		        		 }catch (NumberFormatException e1) {
		        			 	System.out.println("enter a valid number");
		        		 }

	                 break;
	        case 2:  System.out.print("Please enter a temperature in Celsius to convert to Fahrenheit: ");
	        		 try {
			        		 temp = (int)Double.parseDouble(scan.nextLine());
			        		 System.out.printf(temp + " degrees Celsius in Fahrenheit is " + e.calcFahrenheit(temp) + " degrees");
			        		 looper = 1;
			        		 scan.close();
	        		 }catch (NumberFormatException e1) {
	        			 	 System.out.println("enter a valid number");
	        		 }
	        		 break;
	        default: System.out.println("Please enter a valid option");
	        		 break;
			}
		}
	}

}
