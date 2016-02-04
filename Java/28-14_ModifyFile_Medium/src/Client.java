import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
/**
 * This class was from figure 28_03_06t has 8 private methods and 
 * 
 * @author Deitel and Associates, Inc., rohit only added two methods getFile and writeFile 
 */
public class Client extends JFrame {
	private JTextField enterField;
	private JTextArea displayArea;
	private JButton saveButton;
	private ObjectOutputStream output;
	private ObjectInputStream input;
	private String message = "";
	private String fileServer;
	private Socket client;
	private int displayData = 0;
	private String modData = "";
	 /** 
	  * Constructor
	 * @param host
	 *            The ip adress of the host
	 */
	public Client(String host) {
		super("Client");
		fileServer = host;
		enterField = new JTextField();
		enterField.setEditable(false);
		enterField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				sendData(event.getActionCommand());
				enterField.setText("");
			}
		});
		saveButton = new JButton("Save");
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if(displayData == 1)
				{
					message = displayArea.getText();
					sendData(message);
					displayData = 0;
				}
			}
		});
		add(saveButton, BorderLayout.EAST);
		add(enterField, BorderLayout.SOUTH);

		displayArea = new JTextArea();
		add(new JScrollPane(displayArea), BorderLayout.CENTER);

		
		setSize(500, 650);
		setVisible(true);
	}
	 /** 
	  * connects to server and opens input output stream 
	  * as well as processes the conecctions by allowing user 
	  * to enter data and sendData
	 */
	public void runClient() {
		try {
			connectToServer();
			getStreams();
			processConnection();
		} catch (EOFException eofException) {
			displayMessage("\nClient terminated connection");
		} catch (IOException ioException) {
			ioException.printStackTrace();
		} finally {
			closeConnection();
		}
	}
	 /** 
	  * @throws IOException
	  * connects to host using ip adress and port number
	  *
	 */
	private void connectToServer() throws IOException {
		displayMessage("Attempting connection\n");
		client = new Socket(InetAddress.getByName(fileServer), 23500);

		displayMessage("Connected to: " + client.getInetAddress().getHostName());
	}
	 /** 
	  * @throws IOException
	  * opens input and output streams
	  *
	 */
	private void getStreams() throws IOException {
		output = new ObjectOutputStream(client.getOutputStream());
		output.flush();

		input = new ObjectInputStream(client.getInputStream());
		displayMessage("\nGot I/O streams\n");
	}

	 /** 
	  * @throws IOException
	  * processes the connection by allowing user to sendData
	  * to the server/host
	  *
	 */
	private void processConnection() throws IOException {
		setTextFieldEditable(true);

		do {
			try {
				message = (String) input.readObject();
				writeFile(message);
				if(displayData == 0)
				{
					displayMessage("\n" + message);
				}
				
			} catch (ClassNotFoundException classNotFoundException) {
				displayMessage("\nUnknown object type received");
			}

		} while (!message.equals("SERVER>>> TERMINATE"));
	}
	 /** 
	  * closes connection
	  *
	 */
	private void closeConnection() {
		displayMessage("\nClosing connection");
		setTextFieldEditable(false);

		try {
			output.close();
			input.close();
			client.close();
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}
	 /** 
	  * @param message
	  * 		Takes the message the user enters and outputs
	  * to host
	  *
	 */
	private void sendData(String message) {
		try {
			output.writeObject("CLIENT>>> " + message);
			output.flush();
			displayMessage("\nCLIENT>>> " + message);
		} catch (IOException ioException) {
			displayArea.append("\nError writing object");
		}
	}
	 /** 
	  * @param messageToDisplay and displays message to JTextArea
	  *
	 */
	private void displayMessage(final String messageToDisplay) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				displayArea.append(messageToDisplay);
			}
		});
	}
	 /** 
	  * @param message
	  * 		clears JTextArea and shows 
	  * user the contents of the file
	  *
	 */
	private void writeFile(String message) {
		String[] tempHolder = message.split("\\s+");
		String data = message;
		if(tempHolder[0].equals("Data")){
			displayData = 1;
			displayArea.setText(data);
		}
		else{
			displayData = 0;
		}
		
	}
	 /** 
	  * @param editable 
	  * 		checks to see if user can edit textField or not
	  *
	 */
	private void setTextFieldEditable(final boolean editable) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				enterField.setEditable(editable);
			}
		});
	}
}
/**************************************************************************
 * (C) Copyright 1992-2014 by Deitel & Associates, Inc. and               *
 * Pearson Education, Inc. All Rights Reserved.                           *
 *                                                                        *
 * DISCLAIMER: The authors and publisher of this book have used their     *
 * best efforts in preparing the book. These efforts include the          *
 * development, research, and testing of the theories and programs        *
 * to determine their effectiveness. The authors and publisher make       *
 * no warranty of any kind, expressed or implied, with regard to these    *
 * programs or to the documentation contained in these books. The authors *
 * and publisher shall not be liable in any event for incidental or       *
 * consequential damages in connection with, or arising out of, the       *
 * furnishing, performance, or use of these programs.                     *
 *************************************************************************/
