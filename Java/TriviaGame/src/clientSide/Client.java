package clientSide;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.Timer;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class Client extends JFrame {
	private ObjectOutputStream output; // output to server
	private ObjectInputStream input; // input from server
	private String gameServer; // host server
	private Socket client; // socket communicating with server
	private String[] questionAndChoices; //holds questions and answers from server
	private JRadioButton a1; // radio button for answers
	private JRadioButton a2;
	private JRadioButton a3;
	private JRadioButton a4;
	private JButton lockAnswer; // button used to lock answers
	private JTextArea question; // hold the question
	private JLabel timerNum; // starts the timer
	private JLabel questionNum; // holds question number
	private int counter = 15; // 15 second timer
	private int delay = 1000;
	private JLabel playerNum; // holds player number
	private int sendID = 0; // used to check to see if player is assigned a player number
	private boolean gameOver = false; // loop until game over
	private final int numOfQuestions = 10; // sets number of question
	private int questionNumber = 0; // number of questions counter
	private Timer timer; //timer 
	private ArrayList<JRadioButton> buttonList = new ArrayList<JRadioButton>(); // creates radion button list array

	public Client(String host) {
		super("Client");
		gameServer = host; // set server for client to connect to
		JPanel quesPanel = new JPanel(); // question panel
		JPanel ansPanel = new JPanel(); // answer panel
		JPanel timerPanel = new JPanel(); // timerPanel panel
		question = new JTextArea("Question?"); // skeleton of question
		playerNum = new JLabel("Null", SwingConstants.CENTER); // skeleton of player number
		questionNum = new JLabel("Null", SwingConstants.CENTER); // skeleton of question num 
		// Creates radio buttons for answers
		a1 = new JRadioButton("Answer 1");
		a2 = new JRadioButton("Answer 2");
		a3 = new JRadioButton("Answer 3");
		a4 = new JRadioButton("Answer 4");
		// adds each radio button to the ArrayList
		buttonList.add(a1);
		buttonList.add(a2);
		buttonList.add(a3);
		buttonList.add(a4);
		//creates lay out for each panel
		GridLayout ansLayout = new GridLayout(2, 2); 
		BorderLayout quesLayout = new BorderLayout();
		BorderLayout timerLayout = new BorderLayout();
		ButtonGroup answers = new ButtonGroup(); // creates group for answers
		lockAnswer = new JButton("Lock Answer"); // creates button
		lockAnswer.addActionListener(new ActionListener() { // adds the action listener to the button
			public void actionPerformed(ActionEvent event) {
				for (int i = 0; i < buttonList.size(); i++) { // loops through the button list and checks to see if button is pressed
					buttonList.get(i).setEnabled(false);  //sets disables radio button
					if (buttonList.get(i).isSelected()) { 
						lockAnswer.setEnabled(false); // disables button

					}
				}
			}
		});
		timerNum = new JLabel("Timer Num", SwingConstants.CENTER);
		ActionListener action = new ActionListener() { // action listener for timer
			@Override
			public void actionPerformed(ActionEvent event) {
				if (counter == 0) { // when timer hits 0
					timer.stop(); // stops timer
					timerNum.setText("The time is up!");
						for (int i = 0; i < buttonList.size(); i++) { // loops through and sets radio buttons to false
							buttonList.get(i).setEnabled(false);
						}
						for (int i = 0; i < buttonList.size(); i++) { // loops through to find which radio button is selected
							if(buttonList.get(i).isSelected())
							{
								sendData(questionAndChoices[i+1]); // if button is select, it sends the string to the server
								
							}
						}
					

				} else {
					timerNum.setText(counter + " sec");
					counter--; // counts down timer
				}
			}
		};
		timer = new Timer(delay, action); // creates timer with delay and action listener
		timer.setInitialDelay(0); // sets the initial delay to 0
		// adds layout to each panel
		ansPanel.setLayout(ansLayout); 
		quesPanel.setLayout(quesLayout);
		timerPanel.setLayout(timerLayout);

		// adds each radio button to panel
		answers.add(a1);
		answers.add(a2);
		answers.add(a3);
		answers.add(a4);

		question.setLineWrap(true);
		question.setEditable(false);
		quesPanel.add(question, BorderLayout.CENTER); // adds question the panel and centers it
		
		// adds answers to the panel
		ansPanel.add(a1);
		ansPanel.add(a2);
		ansPanel.add(a3);
		ansPanel.add(a4);

		
		timerPanel.setLayout(new GridLayout(3, 1)); // sets panel layout
		
		// adds player number, question number, and timer to panel
		timerPanel.add(playerNum);
		timerPanel.add(questionNum);
		timerPanel.add(timerNum);
		
		// adds panels to the frame along with setting each of the panels layouts
		add(timerPanel, BorderLayout.EAST);
		add(quesPanel, BorderLayout.NORTH);
		add(ansPanel, BorderLayout.CENTER);
		add(lockAnswer, BorderLayout.SOUTH);

		setSize(900, 600); 
		setVisible(true); 
		setResizable(false);
	}
	 /** 
	  * connects to server and opens input output stream 
	  * as well as processes the connections by allowing user 
	  * to enter data and sendData
	 */
	public void runClient() {
		try {
			connectToServer();
			getStreams();
			processConnection();
		} catch (EOFException eofException) {
			System.out.println("connection ended");
		} catch (IOException ioException) {
			ioException.printStackTrace();
		} finally {
			closeConnection();
		}
	}

	/**
	 * Connects to the server
	 * @throws IOException
	 */
	private void connectToServer() throws IOException {
		System.out.println("Attempting to connect");

		client = new Socket(InetAddress.getByName(gameServer), 23555);

		System.out.println("Connected to: " + client.getInetAddress().getHostName());
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
		System.out.println("I/O setup successful");
	}
	/**
	 * This method keeps calling addQuesAndChoices method until the game ends
	 * @throws IOException
	 */
	private void processConnection() throws IOException {
		// process info from server
		do {
			try {
				if(sendID == 0) // checks to see if player number was set or not
				{
					playerNum.setText("Player Number: " + (String) input.readObject()); // sets player number
					sendID = 1; // sets check to true
				}
				else {
					if(questionNumber == numOfQuestions) { // check to see if game is over
						JOptionPane.showMessageDialog(null,  (String) input.readObject()); // displays the each clients score 
						gameOver = true; // sets the check to game over to true
					}
					else{
						questionAndChoices = (String[]) input.readObject(); // gets string array of question and answers from server
						counter = 15; // resets the timer to 15 seconds 
						addQuesAndChoices(questionAndChoices);	// calls method to set question and answers
					}
										
				}
				
			} catch (ClassNotFoundException classNotFoundException) {
				System.out.println("Unknown data type received");
			}
		} while (!gameOver);

	}

	 /** 
	  * closes connection
	  *
	 */
	private void closeConnection() {
		System.out.println("closing connection");

		try {
			output.close();
			input.close();
			client.close();
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}
	/**
	 * This method takes in String of questions from server and sets the text of the question and answers
	 * @param questionAndChoices
	 * 				String of questions from server
	 */
	private void addQuesAndChoices(String[] questionAndChoices) {
		
			for (int i = 0; i < buttonList.size(); i++) { // loops through and enables all of the radio buttons
				buttonList.get(i).setEnabled(true);
			}
			lockAnswer.setEnabled(true); // enables button
			
			// sets question and answers
			question.setText(questionAndChoices[0]);
			a1.setText("<html>" + questionAndChoices[1] + "</html>");
			a2.setText("<html>" + questionAndChoices[2] + "</html>");
			a3.setText("<html>" + questionAndChoices[3] + "</html>");
			a4.setText("<html>" + questionAndChoices[4] + "</html>");
			a1.setSelected(true); // default selection of an answers
			timer.start(); // starts timer
			questionNumber++; //  increments question number 
			questionNum.setText("Question Number: " + Integer.toString(questionNumber)); // counter for question number
		

	}

	/**
	 * This method sends the answer data to the server
	 * @param answerData
	 * 			holds answer string
	 */
	private void sendData(String answerData) {
		try {
			output.writeObject(answerData);
			output.flush();
			System.out.println("data sent");
		} catch (IOException ioException) {
			System.out.println("Error writing object");
		}
	}
}
