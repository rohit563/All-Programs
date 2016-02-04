//import java.io.BufferedOutputStream;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.PrintStream;
package dataBase;
public class TrivaMainServer {

	public static void main(String[] args) {
		/*  Full Question Bank available from http://web-cat.org/questionbank/ */
//		try {
//			System.setOut(new PrintStream(new BufferedOutputStream(new FileOutputStream("output.txt"))));
//			GenericQuestionDOMBranch testQuestions = new GenericQuestionDOMBranch("src/QuestionBank-qti/QuestionBank-qti.xml");
//			for(final SingleQuestion currentQuestion : testQuestions.getListOfQuestions())
//			{
//				currentQuestion.printWithAnswers();
//			}
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
		GenericQuestionDOMBranch testQuestions = new GenericQuestionDOMBranch("src/dataBase/QuestionBank-qti/QuestionBank-qti.xml");
		final SingleQuestion currentQuestion = testQuestions.getListOfQuestions().get(0);
		currentQuestion.printWithAnswers();
		String[] printString=currentQuestion.getQuestion();
		for (int i=0;i<printString.length;i++)
		{
			System.out.println(printString[i]);
		}
//		for(final SingleQuestion currentQuestion : testQuestions.getListOfQuestions())
//		{
//			currentQuestion.printWithAnswers();
//		}
	}
}
