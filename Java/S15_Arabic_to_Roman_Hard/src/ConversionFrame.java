//import TextFieldFrame.TextFieldHandler;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.*;

/**
 * This class has one public method and 2 private methods
 * 
 * @author rbanda
 *
 */
public class ConversionFrame extends JFrame{


    /**
     * Creates the JTextArea for the Roman Numerals
     */
    private final JTextArea textAreaR;

    /**
     * Creates the JTextArea for the Arabic Numerals
     */
    private final JTextArea textAreaA;

    /**
     * String that holds arabic numbers
     */
    private String arabic;

    /**
     * String that holds roman numerals
     */
    private String roman;

    /**
     * Creates JLabel for the Roman Numerals
     */
    private final JLabel label1;
    /**
     * Creates JLabel for the Arabic Numbers
     */
    private final JLabel label2;
    

   
	/**
	 * This is the constructor that generates the GUI
	 */
	public ConversionFrame()
	{
	    super("Roman to Arabic Converter"); setBounds(100,100,600,200);
	    setLayout(new FlowLayout());
	    textAreaR = new JTextArea(2,50);
	    textAreaA = new JTextArea(2, 50);
	    label1 = new JLabel("Roman Numerals");
	    label2 = new JLabel("Arabic Numbers");
	    add(label1);
	    add(textAreaR);
	    add(label2);
	    add(textAreaA);
	    textAreaR.addKeyListener
	    (
	            new KeyListener()
	            {

	                
	                public void keyTyped(KeyEvent e){
	                	
	                }

					@Override
					public void keyPressed(KeyEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void keyReleased(KeyEvent e) {
						// TODO Auto-generated method stub
	    		    	roman = textAreaR.getText();
	    		    	try{
	    		    		arabic = romanToArabic(roman);
	    		    		
	    		    	}catch (NullPointerException e1) {
	    		    		arabic = "Enter A Valid Roman Numeral";
	    		    	}
	    		    	if(textAreaR.getText().isEmpty())
	    		    	{
	    		    		arabic = "";
	    		    	}
	    		    	textAreaA.setText(arabic);
	    		    	
                        }
				
	            	}
	        );
		    textAreaA.addKeyListener
		    (
		            new KeyListener()
		            {
	
		                public void keyTyped(KeyEvent e){
		                	
		                }
	
						@Override
						public void keyPressed(KeyEvent e) {
							// TODO Auto-generated method stub
							
						}
	
						@Override
						public void keyReleased(KeyEvent e) {
							// TODO Auto-generated method stub
		    		    	arabic = textAreaA.getText();
		    		    	try{
		    		    		roman = arabicToRoman(arabic);
		    		    	}catch (NumberFormatException e1) {
		    		    		roman = "Enter A Valid Arabic Number";
		    		    	}
		    		    	if(textAreaA.getText().isEmpty())
		    		    	{
		    		    		roman = "";
		    		    	}
		    		    	textAreaR.setText(roman);
		    		    	
	                        }
					
		            	}
		        );
	    	
	}
	/**
	 * Converts Roman to Arabic
	 * @param romanInput
	 * 			Takes in the the Roman numerals the user inputs
	 * @return
	 * 			Returns an Arabic number from the conversion
	 */
	private String romanToArabic(String romanInput){
		String stringArabicOut;
		String romInput = romanInput.toUpperCase();
		int arabicOutput = 0;
		int holder = 0;
		int previousHolder = 0;
		String romanCheck; 
		
		Hashtable<Character, Integer> hash = new Hashtable<Character, Integer>();
	    hash.put('I',1);
	    hash.put('V',5);
	    hash.put('X',10);
	    hash.put('L',50);
	    hash.put('C',100);
	    hash.put('D',500);
	    hash.put('M',1000);
	
	    for(int i = romInput.length()-1; i>=0 ; i--)
	    {
	    	holder = hash.get(romInput.charAt(i));
	    	
	    	if(holder < previousHolder)
	    	{
	    		arabicOutput = arabicOutput - holder;
	    	}
	    	else
	    	{
                arabicOutput= arabicOutput + holder;
	    	}
	    	previousHolder = holder;
	    }
	    if(arabicOutput < 1 || arabicOutput > 3999)
	    {
	    	stringArabicOut = "Please Enter a valid number between 1-3999";
	    }
	    else
	    {
	    	stringArabicOut = Integer.toString(arabicOutput);
	    	romanCheck = arabicToRoman(stringArabicOut);
	    	if(romInput.equals(romanCheck))
	    	{
	    		return stringArabicOut;
	    	}
	    	else
	    	{
	    		stringArabicOut = "Enter A Valid Roman Numeral";
	    	}
	    }
		return stringArabicOut;
	}
	/**
	 * Converts Arabic to Roman
	 * @param arabicInput
	 * 			Takes in the the Arabic number the user inputs
	 * @return
	 * 			Returns an Roman numeral from the conversion
	 */
	private String arabicToRoman(String arabicInput){
		String stringRomanOut = "";
		int arabInput = Integer.parseInt(arabicInput);
		if(arabInput > 0 && arabInput < 4000)
		{
			LinkedHashMap<Integer, String> hash = new LinkedHashMap<Integer, String>();
	        hash.put(1, "I");
	        hash.put(4, "IV");
	        hash.put(5, "V");
	        hash.put(9, "IX");
	        hash.put(10, "X");
	        hash.put(40, "XL");
	        hash.put(50, "L");
	        hash.put(90, "XC");
	        hash.put(100, "C");
	        hash.put(400, "CD");
	        hash.put(500, "D");
	        hash.put(900, "CM");
	        hash.put(1000, "M");
		    while (arabInput > 0) 
		    {
		    	int highHold = 0;
			    for(Map.Entry<Integer, String> convert : hash.entrySet())
			    {
	                if (convert.getKey() <= arabInput) {
	                    highHold = convert.getKey();
	                }
			    }
			    stringRomanOut += hash.get(highHold);
	            arabInput -= highHold; 
		    }
		}
		else
		{
			stringRomanOut = "Please Enter a valid number between 1-3999";
		}
		return stringRomanOut;
	}


}
