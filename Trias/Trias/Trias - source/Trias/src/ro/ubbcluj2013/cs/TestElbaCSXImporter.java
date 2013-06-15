package ro.ubbcluj2013.cs;

import java.util.ArrayList;

public class TestElbaCSXImporter {

	public static void main(String[] args) {
		ElbaCSXImporter elbaImporter;
		
		elbaImporter = new ElbaCSXImporter("D:\\master\\sem1\\Knowledge discovery\\" + 
				"ToscanaJ-1.7\\ToscanaJ-1.7\\examples\\sql\\diabetes\\diabetes.csx");
		
		ArrayList<CSXObject> localCSXObjectList = elbaImporter.getCsxObjectList();
		
		for( CSXObject obj : localCSXObjectList) {
			System.out.println("#-# diag name: " + obj.getDiagramName());
			System.out.println("#-# obj name: " + obj.getQueryCondition());
			System.out.println("#-# attr name: " + obj.getDescription());
		}

	}

}
