package ro.ubbcj.cs.trias;

import ro.ubbcj.cs.trias.ui.MainFrame;
import static javax.swing.WindowConstants.*;


public class Runner {

	public static void main(String args[]) {
		
		MainFrame frame = new MainFrame();
		frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		frame.setVisible(true);
	}
}
