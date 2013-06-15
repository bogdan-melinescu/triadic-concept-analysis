package ro.ubbcluj2013.cs;

import java.util.ArrayList;

public class CSXDiagram {	
	private String diagramName;
	private ArrayList<CSXObject> objectList;
	
	public CSXDiagram() {
		this.diagramName = "";
		this.objectList = new ArrayList<CSXObject>();
	}
	
	public CSXDiagram(String _diagramName, ArrayList<CSXObject> _objectList) {
		this.diagramName = _diagramName;
		this.objectList = _objectList;
	}
	
	public CSXDiagram(CSXDiagram _newCSXDiag) {
		this.diagramName = _newCSXDiag.diagramName;
		this.objectList = _newCSXDiag.objectList;
	}

	public String getDiagramName() {
		return diagramName;
	}

	public void setDiagramName(String diagramName) {
		this.diagramName = diagramName;
	}

	public ArrayList<CSXObject> getObjectList() {
		return objectList;
	}

	public void setObjectList(ArrayList<CSXObject> objectList) {
		this.objectList = objectList;
	}
	
	public void addObject(CSXObject objectToAdd) {
		this.objectList.add(objectToAdd);		
	}
}
