package ro.ubbcluj2013.cs;

/**
 * The object that holds the necessary values from
 * extracted form the CSX file
 */
public class CSXObject {
	//The title of the <diagram> tag
	private String diagramName;
	
	//The content of the <object> tag
	private String queryCondition;
	
	//The content of the <attribute> tag
	private String description;
	
	public CSXObject() {
		this.diagramName = "";
		this.queryCondition = "";
		this.description = "";
	}
	
	/**
	 * The constructor for the class CSXObject
	 * 
	 * @param _diagramName name of the diagram
	 * @param _queryCondition the condition defined
	 * @param _description the description of the condition
	 */
	public CSXObject( String _diagramName, String _queryCondition, 
				String _description) {
		this.diagramName = _diagramName;
		this.queryCondition = _queryCondition;
		this.description = _description;
	}
	
	//Getters and setters
	public String getDiagramName() {
		return diagramName;
	}

	public void setDiagramName(String diagramName) {
		this.diagramName = diagramName;
	}

	public String getQueryCondition() {
		return queryCondition;
	}

	public void setQueryCondition(String queryCondition) {
		this.queryCondition = queryCondition;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}	
}
