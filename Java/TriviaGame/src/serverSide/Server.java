package serverSide;

import java.io.EOFException;
import java.io.IOException;
import dataBase.*;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
	private ServerSocket server;
	private int connectionCounter = 0;
	private SockServer[] sockServer;
	private int currentQuestionNumber = 0;
	private StringBuilder highScores = new StringBuilder();
	private int numberOfAnswers = 0;
	private boolean gameOver = false;
	private DatabaseManager dataBase;
	private ExecutorService executor;
	private String[][] questionList = {
			{ "Which Led Zeppelin album featured the song Stairway to Heaven?", "Houses of the Holy", "Led Zeppelin II",
					"Led Zeppelin IV", "Physical Graffiti" },
			{ "The top selling beer in the state of Iowa is: ", "Miller Light", "Natural Light", "Coors Light",
					"Bud Light" },
			{ "The movie Creed features an up and coming boxer who is trained by: ", "Rocky Balboa", "Apollo Creed",
					"Mike Tyson", "Manny Pacquiao" },
			{ "Which color is the most favorited color in the world?", "Red", "Green", "Blue", "Pink" },
			{ "The only mammal with the capability to fly is the ", "flying lemur", "flying squirrel", "gliding possum",
					"bat" },
			{ "In which year was Facebook invented?", "2004", "2007", "2001", "2010" },
			{ "The country with the lowest recorded crime rate is", "Russia", "Singapore", "Japan", "Switzerland" },
			{ "The highest grossing film of all time is", "Jurassic World", "Avatar", "Titanic", "The Avengers" },
			{ "The leading cause of death in the world is", "AIDS", "Heart disease", "Lung Cancer", "Stroke" },
			{ "The latest season of South Park focuses on ", "ISIS", "College Tuition", "Justin Beiber",
					"Political Correctness" } };
	private String[] answers = { "Led Zeppelin IV", "Bud Light", "Rocky Balboa", "Blue", "bat", "2004", "Switzerland",
			"Avatar", "Heart disease", "Political Correctness" };
	private ArrayList<Integer> scoreList = new ArrayList<Integer>();
	private ArrayList<SockServer> connectionList = new ArrayList<SockServer>();

	public Server() {
		sockServer = new SockServer[10]; // support 10 clients
		executor = Executors.newFixedThreadPool(10);
	    dataBase = new DatabaseManager(10);
		// TODO add question list and question push functionality
	}

	public synchronized void checkAnswersReceived() {
		 if (numberOfAnswers == connectionCounter) {
			numberOfAnswers = 0;
			currentQuestionNumber++;
			if (currentQuestionNumber == 10) {
				System.out.println("reached checked answers received");
				for (int i = 0; i < scoreList.size(); i++) {
					highScores.append("Player " + i + ":" + scoreList.get(i) + " ");
				}
				gameOver = true;
			}
			System.out.println(currentQuestionNumber);
			for (int i = 0; i < connectionList.size(); i++) {
				connectionList.get(i).setAskQuestion(true);
			}

		}

	}

	public void runServer() {
		try {
			server = new ServerSocket(23555, 12);// made maximum queue 12
													// arbitrarily, 2 waiting

			while (true) {
				try {
					sockServer[connectionCounter] = new SockServer(connectionCounter);
					sockServer[connectionCounter].waitForConnection();
					scoreList.add(0);
					connectionList.add(sockServer[connectionCounter]);
					executor.execute(sockServer[connectionCounter]);
				} catch (EOFException eofException) {
					System.out.println("Server terminated");
				} finally {
					++connectionCounter;
				}
			}
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}

	private class SockServer implements Runnable {
		private ObjectOutputStream output;
		private boolean askQuestion = true;
		private ObjectInputStream input;
		private Socket connection;
		private int connectionID;

		public SockServer(int iD) {
			connectionID = iD;
		}

		@Override
		public void run() {
			try {
				try {
					getStreams();
					processConnection();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} finally {
				System.out.println("reached finally");
				sendScores(highScores.toString());
				closeConnection();
				--connectionCounter;
			}
		}

		private void waitForConnection() throws IOException {
			System.out.println("Waiting for connection");
			connection = server.accept();
			System.out.println(
					"Connection " + connectionCounter + " received by " + connection.getInetAddress().getHostName());
		}

		private void getStreams() throws IOException {
			output = new ObjectOutputStream(connection.getOutputStream());
			output.flush();

			input = new ObjectInputStream(connection.getInputStream());
			System.out.println("I/O established");
		}

		public void setAskQuestion(boolean value) {
			askQuestion = value;
		}

		private void processConnection() throws IOException {
			do {
				try {
					
						if (askQuestion) {
							if (currentQuestionNumber == 0) { // send the ID first
															// if the first
															// question has yet
															// to be sent
							sendID(String.valueOf(connectionID));
							}
						
								sendData(dataBase.getQuestion(currentQuestionNumber));
								checkAnswer((String) input.readObject());
								numberOfAnswers++;
								askQuestion = false;
						}
			
					checkAnswersReceived();
				
				} catch (ClassNotFoundException classNotFoundException) {
					System.out.println("Unknown object type");
				}
			

			} while (gameOver == false);
		}

		// add score for correct answer
		private void checkAnswer(String answer) {
			System.out.println(answer);
			if (answer.equals(dataBase.getQuestion(currentQuestionNumber)[5])) {
				scoreList.set(connectionID, scoreList.get(connectionID) + 1);
			}
		}

		private void sendID(String id) {
			try {
				output.writeObject(id);
				output.flush();
			} catch (IOException ioException) {
				System.out.println("Error writing");
			}
		}

		private void sendData(String[] questionsAndAnswers) {
			try {
				output.writeObject(questionsAndAnswers);
				output.flush();
			} catch (IOException ioException) {
				System.out.println("Error writing");
			}
		}

		private void sendScores(String scores) {
			try {
				output.writeObject(scores);
				output.flush();
			} catch (IOException ioException) {
				System.out.println("Error writing");
			}
		}

		private void closeConnection() {
			connectionCounter--;
			System.out.println("Terminating connection " + connectionID);
			System.out.println("Active Clients : " + connectionCounter);

			try {
				output.close();
				input.close();
				connection.close();
			} catch (IOException ioException) {
				ioException.printStackTrace();
			}
		}
	}

}
