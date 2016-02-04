
/**
 * This class has two public classes that handle the temperature conversions
 * @author rbanda
 *
 */
public class Calc {
	
	/**
	 * @param input
	 * 			takes in the Celsius input of the user
	 * @return
	 * 			returns the Fahrenheit equivalent 
	 */
	public Integer calcFahrenheit(int input){
		int celsius = input;
		int fahrenheit = 0;
		fahrenheit = (int) Math.round((9.0 / 5.0) * celsius + 32); 
		return fahrenheit;	
	}
	/**
	 * @param input
	 * 			takes in the Fahrenheit input of the user
	 * @return
	 * 			returns the Celsius equivalent 
	 */
	public Integer calcCelsius(int input){
		int fahrenheit = input;
		int celsius = 0;
		celsius = (int) Math.round((5.0/9.0)*(fahrenheit - 32)); 
		return celsius;
	}
}
