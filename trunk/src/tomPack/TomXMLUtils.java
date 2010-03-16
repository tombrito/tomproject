package tomPack;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class TomXMLUtils {
    
    public List<String> getValueList(Element rootElement) {
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

}
