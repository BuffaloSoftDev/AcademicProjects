package proj3Rossney; 

/*
 * George Rossney
 * no partner 
 * CSC 242 
 * Project 3: Bayesian Networks 
 * Date submitted: 4/15/16
 */

import java.io.*;
import java.util.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.*;

/**
 * DOM Parser (DocumentBuilder)-based parser for
 * <a href="http://www.cs.cmu.edu/~fgcozman/Research/InterchangeFormat/">XMLBIF</a>
 * files.
 * <p>
 * Note that XMLBIF explicitly states that <q>There is no mandatory
 * order of variable and probability blocks.</q> This means that we
 * have to read the DOM, then create nodes for all the variables using
 * the {@code variable} elements, then hook them up and add the CPTs
 * using the {@code definition} blocks. A good reason to use a DOM
 * parser rather than a SAX parser.
 * <p>
 * Also XMLBIF appears to use uppercase tag names, perhaps thinking they
 * really ought to be case-insensitive.
 * <p>
 * I have implemented minimal sanity checking and error handling.
 * You could do better. Caveat codor.
 */
public class XMLBIFParser {

	public BN readNetworkFromFile(String filename) throws IOException, ParserConfigurationException, SAXException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(new File(filename));
		return processDocument(doc);
	}

	@SuppressWarnings("unused")
	protected BN processDocument(Document doc) {
		Element networkElt = doc.getDocumentElement();
		final BN network = new BN();
		// First do the variables
		doForEachElement(doc, "VARIABLE", new ElementTaker() {
			public void element(Element e) {
				processVariableElement(e, network);
			}
		});
		// Then do the defintions (a.k.a, links and CPTs)
		doForEachElement(doc, "DEFINITION", new ElementTaker() {
			public void element(Element e) {
				processDefinitionElement(e, network);
			}
		});
		/*for(BNode b : network.getNodes())
			b.print();*/
		return network;
	}

	protected void doForEachElement(Document doc, String tagname, ElementTaker taker) {
		NodeList nodes = doc.getElementsByTagName(tagname);
		if (nodes != null && nodes.getLength() > 0) {
			for (int i=0; i < nodes.getLength(); i++) {
				Node node = (Node) nodes.item(i);
				taker.element((Element)node);
			}
		}
	}

	protected void processVariableElement(Element e, BN network) {
		Element nameElt = getChildWithTagName(e, "NAME");
		String name = getChildText((Node) nameElt);
		//trace("creating variable: " + name);
		
		final ArrayList<String> domain = new ArrayList<String>();
		doForEachChild(e, "OUTCOME", new ElementTaker() {
			public void element(Node e) {
				String value = getChildText(e);
				//trace("adding value: " + value);
				domain.add(value);
				//System.out.println(domain.size());
			}

			@Override
			public void element(Element e) {
				
			}

	
		});
		
		RandomVariable var = new RandomVariable(name,domain);
		network.addBNode(new BNnode(var));
	}

	//values of domain
	protected void processDefinitionElement(Element e, final BN network) {
		Element forElt = getChildWithTagName(e, "FOR");
		String forName = getChildText((Node) forElt);
		//trace("creating links to variable: " + forName);
		BNnode forVar = network.getNodeByName(forName);
		
		//lists parents
		final ArrayList<BNnode> givens = new ArrayList<BNnode>();
		//System.out.println("givens size= "+givens.size());
		//add all parents to list of parents
		doForEachChild(e, "GIVEN", new ElementTaker() {
			public void element(Element e) {
				String value = getChildText((Node) e);
				//trace("adding parent: " + value);
				givens.add(network.getNodeByName(value));
				//System.out.println(givens.size());
			}
		});
		givens.add(forVar);
		if(givens.size() > 0)
			forVar.addParents(givens);
		forVar.buildCPT();
		
		//get the list of probabilities
		Element tableElt = getChildWithTagName(e, "TABLE");
		String tableStr = getChildText((Node) tableElt);
		forVar.addProbs(tableStr);
		//network.connect(forVar, givens, cpt);
	}

	protected Element getChildWithTagName(Element elt, String tagname) {
		NodeList children = elt.getChildNodes();
		if (children != null && children.getLength() > 0) {
			for (int i=0; i < children.getLength(); i++) {
				Node node = (Node) children.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element childElt = (Element)node;
					if (childElt.getTagName().equals(tagname)) {
						return childElt;
					}
				}
			}
		}
		throw new NoSuchElementException(tagname);
	}

	protected void doForEachChild(Element elt, String tagname, ElementTaker taker) {
		NodeList children = elt.getChildNodes();
		if (children != null && children.getLength() > 0) {
			for (int i=0; i < children.getLength(); i++) {
				Node node = (Node) children.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element childElt = (Element)node;
					if (childElt.getTagName().equals(tagname)) {
						taker.element(childElt);
					}
				}
			}
		}
	}

	/**
	 * Returns the concatenated child text of the specified node.
	 * This method only looks at the immediate children of type
	 * Node.TEXT_NODE or the children of any child node that is of
	 * type Node.CDATA_SECTION_NODE for the concatenation.
	 */
	public String getChildText(Node node) {
		if (node == null) {
			return null;
		}
		StringBuilder buf = new StringBuilder();
		Node child = (Node) ((org.w3c.dom.Node) node).getFirstChild();
		while (child != null) {
			short type = child.getNodeType();
			if (type == Node.TEXT_NODE) {
				buf.append(((org.w3c.dom.Node) child).getNodeValue());
			}
			else if (type == Node.TEXT_NODE) {
				buf.append(getChildText(child));
			}
			child = (Node) ((org.w3c.dom.Node) child).getNextSibling();
		}
		return buf.toString();
	}

	protected void trace(String msg) {
		System.err.println(msg);
	}

	public static void main(String[] argv) throws IOException, ParserConfigurationException, SAXException {
		XMLBIFParser parser = new XMLBIFParser();
		BN network = parser.readNetworkFromFile(argv[0]);
		System.out.print(network); 
	}

}

interface ElementTaker {
	public void element(Element e);
}