package dataBase;
import java.util.ArrayList;
import java.util.Random;

import org.jsoup.Jsoup;

public class DatabaseManager {
	private ArrayList<SingleQuestion> listOfQuestions = new ArrayList<SingleQuestion>();
	private int[] randomArray;
	
	public DatabaseManager(int numberofQuestions)
	{
		Random randomGenorator = new Random();
		GenericQuestionDOMBranch testQuestions = new GenericQuestionDOMBranch("src/dataBase/QuestionBank-qti/QuestionBank-qti.xml");
		this.listOfQuestions=testQuestions.getListOfQuestions();
		this.randomArray=new int[numberofQuestions];
		for(int i=0;i<numberofQuestions;i++)
		{
			randomArray[i]=randomGenorator.nextInt(listOfQuestions.size());
		}
	}
	public String[]getQuestion(int questionNumber)
	{
		final SingleQuestion currentQuestion = listOfQuestions.get(randomArray[questionNumber]);
		String[] question =currentQuestion.getQuestion();
		for (int i=0;i<6;i++)
		{
			question[i]=html2text(question[i]);
		}
		return question;
	}
	public static String html2text(String html) {
	    return Jsoup.parse(html).text();
	}
}
