package dataBase;
import java.util.HashMap;

import org.w3c.dom.NodeList;
import org.w3c.dom.Element;

public class PresentationNode {
	private String primaryQuestion;
	private HashMap<String, String> responses;

	PresentationNode(final Element presentationNode) {
		responses = new HashMap<String, String>();

		final NodeList material = presentationNode.getElementsByTagName("material");
		final NodeList mattext = ((Element) material.item(0)).getElementsByTagName( "mattext" );
		final Element priaryQuestionElement = (Element) mattext.item(0);
		this.primaryQuestion = priaryQuestionElement.getTextContent();

		final NodeList allResponseLabels = presentationNode.getElementsByTagName("response_label");
		for (int i = 0; i < allResponseLabels.getLength(); i++) {
			// For each response_Label, put string into the hashmap, keyed off
			// of identifier
			final Element currentResponseLabel = (Element) allResponseLabels.item(i);
			final String responseKey = currentResponseLabel.getAttribute("ident");
			final NodeList mattextList = currentResponseLabel.getElementsByTagName("mattext");
			String responseText = new String("");
			for (int j = 0; j < mattextList.getLength(); j++) {
				responseText += mattextList.item(j).getTextContent();
			}
			if(responseText.equals("\""))//Skip if response is single quote
			{
				continue;
			}
			this.responses.put(responseKey, responseText);
		}
	}

	public String getPrimaryQuestion() {
		return primaryQuestion;
	}

	public HashMap<String, String> getResponses() {
		return responses;
	}

}
