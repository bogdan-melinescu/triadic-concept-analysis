package ro.ubbcluj2013.cs;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class CSXHandler_SAX {
	
	SAXParserFactory factory;
	SAXParser saxParser;
	
	DefaultHandler handler;
	
	public CSXHandler_SAX( String _csxFilePath ) {
		try {
			this.factory = SAXParserFactory.newInstance();
			this.saxParser = this.factory.newSAXParser();
			
			createDefaultHandler();
			
			this.saxParser.parse(_csxFilePath, handler);
			
		} catch (Exception e) {
		       e.printStackTrace();
		}
	}
	
	public void createDefaultHandler(){
		this.handler = new DefaultHandler() {
			 
			boolean isDiagramName = false;
			boolean isObjectContingent = false;
			boolean isAttributeContingent = false;
			
			//CSXObject csxObject;
		
			
			public void startElement( String uri, String localName,
					String qName, Attributes attributes) throws SAXException {
		 
				//System.out.println("uri: " + uri);
				System.out.println("qname: " + qName);
				System.out.println("attributes: " + attributes.getValue(0));
				
				
				/*
				System.out.println("Start Element :" + qName);
				
				if( qName.equalsIgnoreCase("diagram") ) {
					isDiagramName = true;
				}
		 		*/
				if( qName.equalsIgnoreCase("object") ) {
					isObjectContingent = true;
				}
				
				if( qName.equalsIgnoreCase("attribute") ) {
					isAttributeContingent = true;
				}
						 
			}
		 
			public void endElement( String uri, String localName,
					String qName) throws SAXException {
		 
				System.out.println("End Element :" + qName);
		 
			}
		 
			public void characters( char ch[], int start, 
					int length) throws SAXException {
		 
				if (isDiagramName) {
					String diagramName = new String(ch, start, length);
					System.out.println("Diagram Name : " + diagramName);
					isDiagramName = false;
				}
		 
				if (isObjectContingent) {
					String objectContingent = new String(ch, start, length);
					System.out.println("Object Contingent : " + objectContingent);
					isObjectContingent = false;
				}
		 
				if (isAttributeContingent) {
					String attributeContingent = new String(ch, start, length);
					System.out.println("Attribute Name : " + attributeContingent );
					isAttributeContingent = false;
				}		  
			}
		 
		};
	}	
}
