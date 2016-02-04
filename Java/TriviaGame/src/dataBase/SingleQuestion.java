package dataBase;
import java.util.HashMap;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;

public class SingleQuestion {

	private PresentationNode presentation;    /* This should be a presentation object, not just a string ! */
	private ResponseLogicNode resprocessing; /* This should be a processing object */


	SingleQuestion(final Element questionNode) {
		final NodeList presentationSubsection = questionNode.getElementsByTagName("presentation");

		final Element presentationElement = (Element)presentationSubsection.item(0) ;
		this.presentation = new PresentationNode(presentationElement);

		final NodeList resprocessingSubsection = questionNode.getElementsByTagName("resprocessing");
		this.resprocessing = new ResponseLogicNode((Element) resprocessingSubsection.item(0));
	}

	public void printWithAnswers()
	{
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("QUESTION:\n    " + this.presentation.getPrimaryQuestion());
		System.out.println("");
		HashMap<String, String> resp = this.presentation.getResponses();
		HashMap<String, String> answ = this.resprocessing.getResponses();
		for (HashMap.Entry<String, String> entry : resp.entrySet()) {
			System.out.println("    " + entry.getValue() + "   ----> " + answ.get(entry.getKey()));
		}
	}
	
	public String[] getQuestion()
	{
		String[] myString = new String[6];
		String[] holder = new String[10];
		String respString=new String();
		String answString=new String();
		//System.out.println("--------------------------------------------------------------------------");
		myString[0]=(this.presentation.getPrimaryQuestion());
		System.out.println("");
		HashMap<String, String> resp = this.presentation.getResponses();
		HashMap<String, String> answ = this.resprocessing.getResponses();
		for (HashMap.Entry<String, String> entry : resp.entrySet()) {
			respString+=(entry.getValue()+"splithere");
			if (answ.get(entry.getKey()).equals("1"))
			{
				answString=entry.getValue();
			}
		}
		holder=respString.split("splithere");
		boolean hasAnswer=false;
		for (Integer i=0;i<4;i++)
		{
			if(i==3&&!hasAnswer)
			{
				for(i=3;!hasAnswer&&i<holder.length;i++)
				{
					if(holder[i].equals(answString))
					{
						hasAnswer=true;
						myString[4]=holder[i];
						myString[5]=answString;
					}
				}
			}
			else
			{
				if(holder[i].equals(answString))
				{
					hasAnswer=true;
					myString[5]=answString;
				}
				myString[i+1]=holder[i];
			}
		}
		return  myString;
	}
}
