package ro.ubbcluj2013.cs;

import java.util.ArrayList;

/**
 * The class that wraps the combination of two diagrams and handles the
 * combination of two or more query items
 */
public class CSXMetaDiagram {
	private ArrayList<CSXDiagram> listOfDiagrams;
	private ArrayList<String> listOfQueries;

	public CSXMetaDiagram() {
		this.listOfDiagrams = new ArrayList<CSXDiagram>();
		this.listOfQueries = new ArrayList<String>();
	}

	/**
	 * Adds a diagram to the list and combines all the querys
	 * 
	 * @param _diagram - the diagram to be added
	 */
	public void addDiagram(CSXDiagram _diagram) {
		if (this.listOfDiagrams.isEmpty()) {
			listOfDiagrams.add(_diagram);
			for (int i = 0; i < _diagram.getObjectList().size(); i++) {
				listOfQueries.add(_diagram.getObjectList().get(i)
						.getQueryCondition());
			}
		} else {
			listOfDiagrams.add(_diagram);
			ArrayList<CSXObject> objectList = _diagram.getObjectList();
			ArrayList<String> combinationList = new ArrayList<String>();

			for (int i = 0; i < listOfQueries.size(); i++) {
				String currentQuerry = listOfQueries.get(i);
				for (int j = 0; j < _diagram.getObjectList().size(); j++) {
					String combination = currentQuerry + " AND "
							+ objectList.get(j).getQueryCondition();

					combinationList.add(combination);
				}
			}

			for (CSXObject obj : objectList) {
				listOfQueries.add(obj.getQueryCondition());
			}

			for (String comboQuerry : combinationList) {
				listOfQueries.add(comboQuerry);
			}
		}
	}

	public ArrayList<CSXDiagram> getListOfDiagrams() {
		return listOfDiagrams;
	}

	public void setListOfDiagrams(ArrayList<CSXDiagram> _listOfDiagrams) {
		for (CSXDiagram diagram : _listOfDiagrams) {
			addDiagram(diagram);
		}
	}

	public ArrayList<String> getListOfQueries() {
		return listOfQueries;
	}

	public void setListOfQueries(ArrayList<String> listOfQueries) {
		this.listOfQueries = listOfQueries;
	}
}
