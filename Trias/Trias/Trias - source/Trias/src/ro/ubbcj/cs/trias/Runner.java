package ro.ubbcj.cs.trias;

import ro.ubbcj.cs.trias.controller.MainTriasController;
import ro.ubbcj.cs.trias.ui.MainFrame1;
import static javax.swing.WindowConstants.*;


public class Runner {

	public static void main(String args[]) {
		
		MainFrame1 frame = new MainFrame1(new MainTriasController());
	}
}
