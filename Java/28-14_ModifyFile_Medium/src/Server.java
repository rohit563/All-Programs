import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
/**
 * This class was from figure 28_03_06t
 * 
 * @author Deitel and Associates, Inc., rohit only added two methods getFile and writeFile 
 */
public class Server extends JFrame {

	private ServerSocket server;
	private Socket connection;
	private ObjectOutputStream output; // output stream to client
	private ObjectInputStream input; // input stream from client
	private JTextArea displayArea; // display information to user
	private String fileInfo = "";
	private int fileExists = 0;
	private String nameFile = "";
	private int writeFile = 0;
	 /** 
	  * constructor of server that generates the GUI
	  *
	 */
	public Server() {
		// TODO Auto-generated constructor stub
		super("Server");
		displayArea = new JTextArea();
		add(new JScrollPane(displayArea), BorderLayout.CENTER);
		setSize(400, 200);
		setVisible(true);
	}
	 /** 
	  * sets up the server and listens on the port listed below
	  *
	 */
	public void runServer() {
		try // set up server to receive connections; process connections
		{
			server = new ServerSocket(12345, 100); // create ServerSocket
			while (true) {
				try {
					waitForConnection(); // wait for a connection
					getStreams(); // get input & output streams
					processConnection(); // process connection
				} catch (EOFException eofException) {
					System.out.println("\nServer terminated connection");
				} finally {
					closeConnection(); // close connection
				}
			}
		} catch (IOException ioException) {
			ioException.printStackTrace();

		}
	}
	 /** 
	  * @throws IOException
	  * Waits for connection and then accepts it
	  *
	 */
	private void waitForConnection() throws IOException {
		displayMessage("Waiting for connection\n");
		connection = server.accept(); // allow server to accept connection
		displayMessage("Connection " + " received from: " + connection.getInetAddress().getHostName());
	}
	 /** 
	  * @throws IOException
	  * opens input and output streams and displays message to the JTextArea
	  *
	 */
	private void getStreams() throws IOException {
		output = new ObjectOutputStream(connection.getOutputStream());
		output.flush();
		input = new ObjectInputStream(connection.getInputStream());
		displayMessage("\nGot I/O streams\n");
	}
	 /** 
	  * @throws IOException
	  * processes connection and checks to see if a valid file name is entered
	  *
	 */
	private void processConnection() throws IOException {
		String message = "Connection successful";
		sendData(message);
		sendData("Please enter a valid filename");
		do {
			try {
				message = (String) input.readObject();
				System.out.println(message);
				if(writeFile == 1)
				{
					writeFile(message);
					writeFile = 0;
				}
				else
				{
					getFile(message);
				}
				displayMessage("\n" + message);
			} catch (ClassNotFoundException classNotFoundException) {
				displayMessage("\nUnknown object type received");
			}

		} while (!message.equals("CLIENT>>> TERMINATE"));
	}
	 /** 
	  * closes connection
	  *
	 */
	private void closeConnection() {
		displayMessage("\nTerminating connection\n");

		try {
			output.close();
			input.close();
			connection.close();
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}

	 /** 
	  * @param message
	  * 		sends message to client
	  *
	 */
	private void sendData(String message) {
		try {
			if (fileExists == 1) {
				output.writeObject("Data Of File:" + message);
				output.flush();
				fileExists = 0;
			} else {
				output.writeObject("SERVER>>> " + message);
				output.flush();
				displayMessage("\nSERVER>>> " + message);
			}
		} catch (IOException ioException) {
			displayArea.append("\nError writing object");
		}
	}
	 /** 
	  * @param message
	  * 		Writes text to file
	  *
	 */
	private void writeFile(String message) {
		message = message.substring(23);
		try{
			FileWriter writer = new FileWriter(nameFile);
			writer.write(message);
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sendData("Please enter a valid filename");
		
		
	}
	 /** 
	  * @param fileName
	  * 		sends data of file to client
	  *
	 */
	private void getFile(String fileName) {
		String[] nameOfFile = fileName.split("\\s+");
		// System.out.println(nameOfFile[1]);
		try {
			FileReader reader = new FileReader(nameOfFile[1]);
			BufferedReader bufferedReader = new BufferedReader(reader);
			String line;
			try {
				nameFile = nameOfFile[1];
				while ((line = bufferedReader.readLine()) != null) {
					String tmp = line + "\n";
					fileInfo = fileInfo + tmp;
					fileExists = 1;
					writeFile = 1;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				fileExists = 0;
			}
		} catch (FileNotFoundException e) {
			if(writeFile == 0)
			{
				sendData("ERROR: The file was not found!");
			}
			sendData("Please enter a valid filename");
			fileExists = 0;
			writeFile = 0;
		}
		sendData(fileInfo);
		//sendData("\nSERVER>>> Please Enter a File:");
		fileInfo = "";

	}

	// manipulates displayArea in the event-dispatch thread
	private void displayMessage(final String messageToDisplay) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() // updates displayArea
			{
				displayArea.append(messageToDisplay); // append message
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
