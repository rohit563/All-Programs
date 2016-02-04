package dataBase;
import java.util.HashMap;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;

public class ResponseLogicNode {
	private HashMap<String, String> responses;

	ResponseLogicNode(final Element responseNode) {
		responses = new HashMap<String, String>();

		Element responseElement = (Element) responseNode;
		final NodeList allResponseConditions = responseElement.getElementsByTagName("respcondition");

		for (int i = 0; i < allResponseConditions.getLength(); i++) {
			// For each response_Label, put string into the hashmap, keyed off
			// of identifier
			final Element currentResponseCondition = (Element) allResponseConditions.item(i);
			final Element currentConditionvar = (Element) currentResponseCondition.getElementsByTagName("conditionvar").item(0); //Only 1 allowed
			final NodeList varequalList = currentConditionvar.getElementsByTagName("varequal");
			if(varequalList.getLength() != 1) // Only valid when exactly 1 elment exists.
			{
				continue;
			}
			final Element varEqual = (Element) varequalList.item(0); // Only 1 allowed
			final String responseKey = varEqual.getTextContent();

			final Element setVar = (Element) currentResponseCondition.getElementsByTagName("setvar").item(0); //Only 1 allowed
			final String responseText = setVar.getTextContent();

			this.responses.put(responseKey, responseText);
		}
	}

	public HashMap<String, String> getResponses() {
		return responses;
	}

}
