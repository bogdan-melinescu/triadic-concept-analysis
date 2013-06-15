package ro.ubbcluj2013.cs;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.ArrayList;

/**
 * The class that handles the parsing and collecting of data
 * from the CSX file. The parsing is made with a DOM parser
 */
public class CSXHandler_DOM {
	//Path to the CSX file
	private String csxFilePath;
	
	//Members used by the parser
	private File csxFile;  
	private DocumentBuilderFactory dbFactory;
	private DocumentBuilder dBuilder;
	private static Document doc;
	
	//The resulting objects
	private ArrayList<CSXDiagram> diagramObjectList;
	
	public CSXHandler_DOM( String _csxFilePath ) {
		this.csxFilePath = _csxFilePath;
		
		try {			
			this.csxFile = new File(this.csxFilePath);
			
			this.dbFactory = DocumentBuilderFactory.newInstance();
			this.dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(this.csxFile);
			
			doc.getDocumentElement().normalize();
			
			this.diagramObjectList = new ArrayList<CSXDiagram>();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * The function that loads the CSX object from the csx
	 * file that Elba generates. It makes the CSX object list
	 * and the Diagram name list
	 * 
	 * @return the list of all the CSX objects that interest 
	 * further
	 */
	public ArrayList<CSXDiagram> loadFromCSX() {
		
		NodeList diagramNodeList = doc.getElementsByTagName("diagram");
	 		
		/*
		 * the CSX file is an XML, and the only element thatwe need are
		 * the <object> <attribute> and <diagram> tags
		 */		
		for (int diagListIter = 0; diagListIter < diagramNodeList.getLength(); 
				diagListIter++) {
			
			//For every diagram node
			CSXDiagram diagramObject = new CSXDiagram();
			
			Element diagNode = (Element) diagramNodeList.item(diagListIter);
			
			String diagName = diagNode.getAttribute("title");
				
			diagramObject.setDiagramName(diagName);
						
			NodeList nodeList = diagNode.getElementsByTagName("node");
			
			int nodeListIter = 0;
			while( nodeListIter < nodeList.getLength() ) {
				Element nodeElement = (Element) nodeList.item(nodeListIter);
				Element conceptNode = (Element) nodeElement.getElementsByTagName("concept").item(0);
				
				if( conceptNode != null ) {
					NodeList objectList = conceptNode.getElementsByTagName("object");
					NodeList attributeList = conceptNode.getElementsByTagName("attribute");
					
					for( int iter=0; iter < objectList.getLength(); iter++ ) {
						CSXObject temporaryObject;
						
						String objectName = "###";
						String attributeName = "###";
						
						if( objectList.item(iter) != null ) {
							objectName = objectList.item(iter).getTextContent();
						}
						if( attributeList.item(iter) != null ) {
							attributeName = attributeList.item(iter).getTextContent();
						}
						temporaryObject = new CSXObject(diagName, objectName, attributeName);
						
						diagramObject.addObject(temporaryObject);
					}
				}
				nodeListIter++;	
			}
			this.diagramObjectList.add(diagramObject);
		}
		//return csxObjectList;
		return this.diagramObjectList;
	}
}
