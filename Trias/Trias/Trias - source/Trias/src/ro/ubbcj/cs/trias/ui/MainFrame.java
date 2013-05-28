package ro.ubbcj.cs.trias.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

import ro.ubbcj.cs.trias.controller.TriasNeighborhoodController;

/**
 * This is the frame which appears when the program is launched. It allows the
 * user to choose the type of task and the type of data source.
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
public class MainFrame extends javax.swing.JFrame {
	private static final String DATA_FROM_FILE = "File";
	private static final String COMPUTE_NEIGHBORHOODS = "Concept Neighborhoods";
	private static final String COMPUTE_CONCEPTS = "Concepts";
	private static final String DATA_FROM_DATABASE = "Database";

	private JLabel runLabel;
	private JLabel dataFromLabel;
	private JComboBox dataFromComboBox;
	private JButton okButton;
	private JComboBox computeComboBox;

	public MainFrame() {
		super("Trias");
		initGUI();
	}

	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setLayout(null);
			{
				runLabel = new JLabel();
				getContentPane().add(runLabel);
				runLabel.setText("Compute");
				runLabel.setBounds(41, 29, 63, 14);
			}
			{
				ComboBoxModel computeComboBoxModel = new DefaultComboBoxModel(
						new String[] { COMPUTE_NEIGHBORHOODS, COMPUTE_CONCEPTS });
				computeComboBox = new JComboBox();
				getContentPane().add(computeComboBox);
				computeComboBox.setModel(computeComboBoxModel);
				computeComboBox.setBounds(122, 26, 197, 21);
			}
			{
				dataFromLabel = new JLabel();
				getContentPane().add(dataFromLabel);
				dataFromLabel.setText("Data from");
				dataFromLabel.setBounds(41, 63, 63, 14);
			}
			{
				ComboBoxModel dataFromComboBoxModel = new DefaultComboBoxModel(
						new String[] { DATA_FROM_FILE, DATA_FROM_DATABASE });
				dataFromComboBox = new JComboBox();
				getContentPane().add(dataFromComboBox);
				dataFromComboBox.setModel(dataFromComboBoxModel);
				dataFromComboBox.setBounds(122, 60, 197, 21);
			}
			{
				okButton = new JButton();
				getContentPane().add(okButton);
				okButton.setText("Go");
				okButton.setBounds(256, 95, 63, 21);
				okButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						go();
					}
				});
			}
			pack();
			this.setSize(400, 161);
		} catch (Exception e) {
			// add your error handling code here
			e.printStackTrace();
		}
	}

	private void go() {
		DataSource dataSource = null;
		if (dataFromComboBox.getSelectedItem().equals(DATA_FROM_DATABASE)) {
			dataSource = DataSource.DATABASE;
		} else if (dataFromComboBox.getSelectedItem().equals(DATA_FROM_FILE)) {
			dataSource = DataSource.FILE;
		}
		if (computeComboBox.getSelectedItem().equals(COMPUTE_CONCEPTS)) {
			runTrias(dataSource);
		} else if (computeComboBox.getSelectedItem().equals(
				COMPUTE_NEIGHBORHOODS)) {
			runTriasNeighborhoods(dataSource);
		}
	}

	private void runTriasNeighborhoods(DataSource dataSource) {

		try {
			TriasNeighborhoodController controller = new TriasNeighborhoodController(
					dataSource);
			this.setVisible(false);
			this.dispose();
			controller.showFrame();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Error: " + ex, "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void runTrias(DataSource dataSource) {

		try {
			TriasFrame frame = new TriasFrame(dataSource);
			frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			this.setVisible(false);
			this.dispose();
			frame.setVisible(true);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Error: " + ex, "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

}
