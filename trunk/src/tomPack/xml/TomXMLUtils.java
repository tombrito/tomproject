package tomPack.xml;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.sun.org.apache.xerces.internal.dom.DOMImplementationImpl;

/**
 * Utilities for XML files.
 * <p>
 * Use {@link #parseXML(File)} to read a xml file.
 */
public class TomXMLUtils {

    /**
     * Return the list of child node's names.
     */
    public static List<String> getValueList(Element rootElement) {
	List<String> valueList = new ArrayList<String>();
	NodeList nodes = rootElement.getChildNodes();

	for (int i = 0; i < nodes.getLength(); i++) {
	    Node node = nodes.item(i);
	    String nodeName = node.getNodeName().trim();

	    // remove xml comments
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
	if (xmlFile.length() == 0) {
	    return null; // empty file
	}
	// TODO Issue #74 - show progress bar or use SAX (research)
	System.out.println("parsing..."); //$NON-NLS-1$
	return docBuilder.parse(xmlFile);
    }

    /** Writes a DOM document to a stream. */
    public static void outputDOM(Document doc, OutputStream stream) throws TransformerFactoryConfigurationError,
	    TransformerException {

	// Prepare the DOM document for writing
	Source source = new DOMSource(doc);
	// Prepare the output stream
	Result result = new StreamResult(stream);

	// Write the DOM document to the file
	Transformer xformer = TransformerFactory.newInstance().newTransformer();
	xformer.setOutputProperty(OutputKeys.INDENT, "yes"); //$NON-NLS-1$
	xformer.transform(source, result);

    }

    /**
     * Convenience method for
     * {@link DOMImplementation#createDocument(String, String, org.w3c.dom.DocumentType)}
     * using args (null, rootElementName, null).
     */
    public static Document createDocument(String rootElementName) {
	DOMImplementation domImpl = DOMImplementationImpl.getDOMImplementation();
	Document doc = domImpl.createDocument(null, rootElementName, null);
	return doc;
    }

}
