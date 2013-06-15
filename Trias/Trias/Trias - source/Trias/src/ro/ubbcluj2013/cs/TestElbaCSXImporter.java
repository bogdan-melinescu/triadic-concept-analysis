package ro.ubbcluj2013.cs;

import java.util.ArrayList;

/**
 * The class that tests the csx importer
 */
public class TestElbaCSXImporter {

	public static void main(String[] args) {
		ElbaCSXImporter elbaImporter;
		
		elbaImporter = new ElbaCSXImporter("D:\\master\\sem1\\Knowledge discovery\\" + 
				"ToscanaJ-1.7\\ToscanaJ-1.7\\examples\\sql\\diabetes\\diabetes.csx");
		
		ArrayList<CSXDiagram> localDiagramObjectList = elbaImporter.getDiagramObjectList();
		
		for( CSXDiagram diag : localDiagramObjectList) {
			System.out.println("=Diagram name: "+ diag.getDiagramName());
			for( CSXObject obj : diag.getObjectList() ) {
				System.out.println("--Object name: "+ obj.getQueryCondition());
			}
		}
		
		elbaImporter.addAttributeMetaDiagram("Cholesterol Level+Triglycerides Level");
		elbaImporter.addConditionMetaDiagram("Dispensation of Insuline+C-Peptide Level+Sex");
		
		for( CSXMetaDiagram metaDiag : elbaImporter.getAttributeMetaDiagramList() ) {
			System.out.println("*** Attribute Diagram Names ***");
			for( CSXDiagram diag : metaDiag.getListOfDiagrams() ) {
				System.out.println(diag.getDiagramName());
			}
			
			System.out.println("*** Attribute Querrys ***");
			for( String queryes : metaDiag.getListOfQueries() ) {
				System.out.println(queryes);
			}
		}
		
		for( CSXMetaDiagram metaDiag : elbaImporter.getConditionMedaDiagramList() ) {
			System.out.println("*** Conditioon Diagram Names ***");
			for( CSXDiagram diag : metaDiag.getListOfDiagrams() ) {
				System.out.println(diag.getDiagramName());
			}
			
			System.out.println("*** Condition Querrys ***");
			for( String queryes : metaDiag.getListOfQueries() ) {
				System.out.println(queryes);
			}
		}
	}
}
