package tomPack;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class TomXMLUtils {

    public static List<String> getValueList(Element rootElement) {
	List<String> valueList = new ArrayList<String>();
	NodeList manifestNodes = rootElement.getChildNodes();
	for (int i = 0; i < manifestNodes.getLength(); i++) {
	    Node node = manifestNodes.item(i);
	    String nodeName = node.getNodeName().trim();
	    if (nodeName.startsWith("#")) { //$NON-NLS-1$
		continue;
	    }
	    valueList.add(nodeName);
	}
	return valueList;
    }

    public static Document parseXML(File xmlFile) throws ParserConfigurationException, SAXException, IOException {
	DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
	// TODO show progress bar
	// TODO assess use SAX (research)
	System.out.println("parsing...");
	return docBuilder.parse(xmlFile);
    }

}
