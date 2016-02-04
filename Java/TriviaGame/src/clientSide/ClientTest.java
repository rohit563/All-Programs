package clientSide;

import javax.swing.JFrame;

public class ClientTest {

		public static void main(String[] args) {
			Client application;
			application = new Client("127.0.0.1"); //local connection
			application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			application.runClient();
		}
}
