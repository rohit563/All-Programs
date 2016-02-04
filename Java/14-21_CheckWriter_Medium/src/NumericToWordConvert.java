import java.text.DecimalFormat;
import java.util.HashMap;
/**
 * This is a class that lets you convert the numbers into words
 * @author rohit
 */
public class NumericToWordConvert {
	/**
	 * This holds the worded number as a string
	 */
	private String numWordString;
	/**
	 * This method returns the words of the numbers
	 * @return numWordString
	 * 			returns the worded numbers 
	 */
	public String getNumWordString() {
		return numWordString;
	}
	/**
	 * This method sets the numbers 
	 * @param numWordString
	 * 			sets the worded numbers
	 */
	public void setNumWordString(String numWordString) {
		this.numWordString = numWordString;
	}

	public NumericToWordConvert() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * This method converts the numbers to words
	 * @param input
	 * 			takes user inputed number and converts to words
	 */
	public void numToString(String input) {
		if (Double.parseDouble(input) < 1000.00) {
			Double userInput = Double.parseDouble(input);
			String numWord = "";
			String numWordHundreds;
			String numWordTens;
			String numWordOnes;
			String tempConvert;
			int cent = 0;
			int count = 0;
			Double value;
			Double value1;
			Double cents;
			HashMap<Double, String> hmap = new HashMap<Double, String>();
			hmap.put(1.0, "ONE");
			hmap.put(2.0, "TWO");
			hmap.put(3.0, "THREE");
			hmap.put(4.0, "FOUR");
			hmap.put(5.0, "FIVE");
			hmap.put(6.0, "SIX");
			hmap.put(7.0, "SEVEN");
			hmap.put(8.0, "EIGHT");
			hmap.put(9.0, "NINE");
			hmap.put(10.0, "TEN");
			hmap.put(11.0, "ELEVEN");
			hmap.put(12.0, "TWELVE");
			hmap.put(13.0, "THIRTEEN");
			hmap.put(14.0, "FOURTEEN");
			hmap.put(15.0, "FIFTEEN");
			hmap.put(16.0, "SIXTEEN");
			hmap.put(17.0, "SEVENTEEN");
			hmap.put(18.0, "EIGHTEEN");
			hmap.put(19.0, "NINETEEN");
			hmap.put(20.0, "TWENTY");
			hmap.put(30.0, "THIRTY");
			hmap.put(40.0, "FOURTY");
			hmap.put(50.0, "FIFTY");
			hmap.put(60.0, "SIXTY");
			hmap.put(70.0, "SEVENTY");
			hmap.put(80.0, "EIGHTY");
			hmap.put(90.0, "NINETY");

			while (userInput != 0) {

				if (userInput >= 100) {
					value = (double) Character.getNumericValue(input.charAt(0));
					numWordHundreds = hmap.get(value);
					value = value * 100;
					userInput = userInput - value;
					numWord = numWordHundreds + " hundred";

				} else if (userInput >= 10) {
					value = (double) Character.getNumericValue(input.charAt(1));
					value1 = (double) Character.getNumericValue(input.charAt(2));

					if (value1 == 0) {
						value = value * 10;
						value = value + value1;
						numWordTens = hmap.get(value);
						userInput = userInput - value;
						numWord = numWord + " " + numWordTens;
						cent = 1;
					} else {
						count = 1;
						value = value * 10;
						if (value == 1) {
							value = value + value1;
						}
						numWordTens = hmap.get(value);
						userInput = userInput - value;
						numWord = numWord + " " + numWordTens;

					}

				} else if (count == 1 || userInput >= 1) {
					value1 = (double) Character.getNumericValue(input.charAt(2));
					value = value1;
					numWordOnes = hmap.get(value);
					userInput = userInput - value;
					numWord = numWord + " " + numWordOnes;
					cent = 1;

				}
				if (cent == 1 || userInput < 1) {
					value = (double) Character.getNumericValue(input.charAt(4));
					value1 = (double) Character.getNumericValue(input.charAt(5));
					value = value * 10.0;
					cents = (value + value1) / 100.00;
					DecimalFormat df = new DecimalFormat("#.00");
					tempConvert = df.format(userInput);
					userInput = Double.parseDouble(tempConvert);
					userInput = userInput - cents;
					if (Character.getNumericValue(input.charAt(0)) == 0
							&& Character.getNumericValue(input.charAt(1)) == 0
							&& Character.getNumericValue(input.charAt(2)) == 0) {
						numWord = numWord + cents + "/100";
					} else {
						numWord = numWord + " and " + cents + "/100";
					}

				}
			}
			setNumWordString(numWord);

		} else {
			setNumWordString("Please Enter A Valid Number");
		}

	}
}
