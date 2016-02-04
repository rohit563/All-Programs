import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
/**
 * This class has eight public methods 
 * @author rbanda
 *
 */
public class MyColorChooser extends JFrame {

	/**
	 * This creates the slider for the color Red
	 */
	private final JSlider slider;
	/**
	 * This creates the slider for the color Green
	 */
	private final JSlider slider2;
	/**
	 * This creates the slider for the color Blue
	 */
	private final JSlider slider3;
	/**
	 * This creates the textField for the color Red
	 */
	private final JTextField textField;
	/**
	 * This creates the textField for the color Green
	 */	
	private final JTextField textField2;
	/**
	 * This creates the textField for the color Blue
	 */
	private final JTextField textField3;
	/**
	 * This creates the Jlabel for the color Red
	 */
	private final JLabel label1;
	/**
	 * This creates the Jlabel for the color Green
	 */	
	private final JLabel label2;
	/**
	 * This creates the Jlabel for the color Blue
	 */
	private final JLabel label3;
	/**
	 * This creates the panel that hold all the sliders and labels and textFields
	 */
	private final JPanel panel1;
	/**
	 * This variable holds the color red 
	 */
	private int colorRed = 0;
	/**
	 * This variable holds the color blue 
	 */
	private int colorBlue = 0;
	/**
	 * This variable holds the color green 
	 */
	private int colorGreen = 0;
	/**
	 * This creates a new variable of type paint 
	 */
	private Paint paint;
	/**
	 * This generates the GUI for the color chooser
	 */	
	public MyColorChooser() {
		// TODO Auto-generated constructor stub
		
		super("Color Chooser"); setBounds(100,100,600,120);
		slider = new JSlider(0,255,0);
		slider2 = new JSlider(0,255,0);
		slider3 = new JSlider(0,255,0);
		panel1 = new JPanel();
		textField = new JTextField();
		textField2 = new JTextField();
		textField3 = new JTextField();
		label1 = new JLabel("Red");
		label2 = new JLabel("Green");
		label3 = new JLabel("Blue");
		panel1.setLayout(new GridLayout(0, 3));
		panel1.add(label1);
		panel1.add(label2);
		panel1.add(label3);
		panel1.add(textField);
		panel1.add(textField2);
		panel1.add(textField3);
		textField.setText(Integer.toString(slider.getValue()));
		textField2.setText(Integer.toString(slider.getValue()));
		textField3.setText(Integer.toString(slider.getValue()));
		slider.addChangeListener(new ChangeListener()
		{
			/**
			 * This is a listener for the JTextField
			 * @param e
			 * 			Change event generic variable 
			 */
			@Override
			public void stateChanged(ChangeEvent e) {
			// TODO Auto-generated method stub
			colorRed = slider.getValue();
			paint.setRed(colorRed);
			textField.setText(Integer.toString(colorRed));
			}
		});
		slider2.addChangeListener(new ChangeListener()
		{	/**
			 * This is a listener for the JTextField
			 * @param e
			 * 			Change event generic variable 
			 */
			@Override
			public void stateChanged(ChangeEvent e) {
			// TODO Auto-generated method stub
			colorGreen = slider2.getValue();
			paint.setGreen(colorGreen);
			textField2.setText(Integer.toString(colorGreen));			
			}
		});
		slider3.addChangeListener(new ChangeListener()
		{
			/**
			 * This is a listener for the JTextField
			 * @param e
			 * 			Change event generic variable 
			 */
			@Override
			
			public void stateChanged(ChangeEvent e) {
			// TODO Auto-generated method stub
			colorBlue = slider3.getValue();
			paint.setBlue(colorBlue);
			textField3.setText(Integer.toString(colorBlue));	

			}
			
		});
		textField.addActionListener(new ActionListener()
		{
			/**
			 * This is a actionPerformed listener for the JTextField
			 * @param e
			 * 			Actionevent generic variable 
			 */
			public void actionPerformed(ActionEvent e)
			{
				try{
					colorRed = Integer.parseInt(textField.getText());
				}catch(NumberFormatException e1)
				{
					JOptionPane.showMessageDialog(null, "ERROR: Enter Valid Number");
					textField.setText(Integer.toString(slider.getValue()));
				}
				if(colorRed < slider.getMinimum() || colorRed > slider.getMaximum())
				{
					JOptionPane.showMessageDialog(null, "ERROR: Enter Valid Number");
					textField.setText(Integer.toString(slider.getValue()));
				}
				else
				{
					slider.setValue(colorRed);
				}
			}
		});
		textField2.addActionListener(new ActionListener()
		{
			/**
			 * This is a actionPerformed listener for the JTextField
			 * @param e
			 * 			Actionevent generic variable 
			 */
			public void actionPerformed(ActionEvent e)
			{
				try{
					colorGreen = Integer.parseInt(textField2.getText());
				}catch(NumberFormatException e1)
				{
					JOptionPane.showMessageDialog(null, "ERROR: Enter Valid Number");
					textField2.setText(Integer.toString(slider2.getValue()));
				}
				if(colorGreen < slider2.getMinimum() || colorGreen > slider2.getMaximum())
				{
					JOptionPane.showMessageDialog(null, "ERROR: Enter Valid Number");
					textField2.setText(Integer.toString(slider2.getValue()));
				}
				else
				{
					slider2.setValue(colorGreen);
				}
			}
		});
		textField3.addActionListener(new ActionListener()
		{				/**
			 * This is a actionPerformed listener for the JTextField
			 * @param e
			 * 			Actionevent generic variable 
			 */
			public void actionPerformed(ActionEvent e)
			{
				try{
					colorBlue = Integer.parseInt(textField3.getText());
				}catch(NumberFormatException e1)
				{
					JOptionPane.showMessageDialog(null, "ERROR: Enter Valid Number");
					textField3.setText(Integer.toString(slider3.getValue()));
				}
				if(colorBlue < slider3.getMinimum() || colorBlue > slider3.getMaximum())
				{
					JOptionPane.showMessageDialog(null, "ERROR: Enter Valid Number");
					textField3.setText(Integer.toString(slider3.getValue()));
				}
				else
				{
					slider3.setValue(colorBlue);
				}
			}
		});
		paint = new Paint(Color.black);
		panel1.add(slider);
		panel1.add(slider2);
		panel1.add(slider3);
		add(panel1, BorderLayout.NORTH);
		add(paint, BorderLayout.CENTER);
		
		
		
	}

}
