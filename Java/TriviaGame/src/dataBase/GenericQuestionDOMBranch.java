package dataBase;
import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;

public class GenericQuestionDOMBranch {

	private ArrayList<SingleQuestion> listOfQuestions = new ArrayList<SingleQuestion>();

	public ArrayList<SingleQuestion> getListOfQuestions() {
		return listOfQuestions;
	}

	GenericQuestionDOMBranch(final String XMLFileName)
	{
		try {	
			/*  Question Bank from http://web-cat.org/questionbank/ */
			File inputFile = new File(XMLFileName);
			DocumentBuilderFactory dbFactory 
			= DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();
			//System.out.println("Root element :"  + doc.getDocumentElement().getNodeName());
			final Element section = (Element) doc.getElementsByTagName("section").item(0); // Only 1 section allowed
			final NodeList items = section.getElementsByTagName("item");

			//System.out.println("-All Questions---------------------------");
			for (int questionIndex = 0; questionIndex < items.getLength(); questionIndex++) {
				final Element questionNode = (Element) items.item(questionIndex);
				//System.out.println(questionNode.getNodeName());

				final SingleQuestion currentQuestion = new SingleQuestion( questionNode );
				this.listOfQuestions.add(currentQuestion);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}