package ro.ubbcj.cs.trias.ui;

import java.awt.BorderLayout;
import javax.swing.JLabel;

import javax.swing.WindowConstants;

/**
 * This frame does not have any functionality at the moment.
 * 
 * This code was edited or generated using CloudGarden's Jigloo SWT/Swing GUI
 * Builder, which is free for non-commercial use. If Jigloo is being used
 * commercially (ie, by a corporation, company or business for any purpose
 * whatever) then you should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details. Use of Jigloo implies
 * acceptance of these licensing terms. A COMMERCIAL LICENSE HAS NOT BEEN
 * PURCHASED FOR THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED LEGALLY FOR
 * ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
public class TriasFrame extends javax.swing.JFrame {

	private DataSource dataSource;
	private JLabel nyiLabel;

	public TriasFrame(DataSource dataSource) {
		super("Trias Concept Miner");
		this.dataSource = dataSource;
		initGUI();
	}

	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setLayout(null);
			{
				nyiLabel = new JLabel();
				getContentPane().add(nyiLabel, "Center");
				nyiLabel.setText("NOY YET IMPLEMENTED");
				nyiLabel.setBounds(24, 101, 368, 53);
				nyiLabel.setFont(new java.awt.Font("Tahoma", 0, 28));
			}
			pack();
			setSize(400, 300);
		} catch (Exception e) {
			// add your error handling code here
			e.printStackTrace();
		}
	}

}
