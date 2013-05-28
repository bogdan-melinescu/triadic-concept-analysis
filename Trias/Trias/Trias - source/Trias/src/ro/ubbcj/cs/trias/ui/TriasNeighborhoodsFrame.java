package ro.ubbcj.cs.trias.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.TitledBorder;

import ro.ubbcj.cs.trias.controller.TriasNeighborhoodController;

/**
 * This frame is used to select the parameters for computing neighborhoods of triadic concepts.
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
public class TriasNeighborhoodsFrame extends javax.swing.JFrame {

	private DataSource dataSource;
	private JComboBox column3ComboBox;
	private JComboBox column2ComboBox;
	private JComboBox column1ComboBox;
	private JLabel column3Label;
	private JLabel column2Label;
	private JLabel column1Label;
	private JComboBox tableComboBox;
	private JLabel tableNameLabel;
	private JButton selectOutputFileButton;
	private JButton selectInputFileButton;
	private JTextField separatorTextBox;
	private JLabel separatorLabel;
	private JComboBox fileTypeComboBox;
	private TriasNeighborhoodController controller;

	private JTextField thresholdTextBox3;
	private JTextField thresholdTextBox2;
	private JTextField thresholdTextBox1;
	private JPanel thresholdPanel;
	private JTextField minSupportTextBox3;
	private JTextField minSupportTextBox2;
	private JTextField minSupportTextBox1;
	private JPanel minSupportPanel;
	private JTextField inputFileTextBox;
	private JLabel inputFile;
	private JCheckBox launchGraphCheckbox;
	private JTextField graphFileTextbox;
	private JLabel graphFileLabel;
	private JButton goButton;
	private JPanel outputPanel;
	private JPanel computationPanel;
	private JPanel fileInputPanel;
	private JPanel databaseInputPanel;

	public TriasNeighborhoodsFrame(DataSource dataSource,
			TriasNeighborhoodController controller) {
		super("Trias Concept Neighborhood Miner");
		this.dataSource = dataSource;
		this.controller = controller;
		initGUI();
	}

	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setLayout(null);
			{
				fileInputPanel = new JPanel();
				getContentPane().add(fileInputPanel);
				fileInputPanel.setBounds(5, 13, 642, 111);
				fileInputPanel.setBorder(BorderFactory.createTitledBorder(null,
						"Input", TitledBorder.LEADING,
						TitledBorder.DEFAULT_POSITION));
				fileInputPanel.setLayout(null);
				{
					inputFile = new JLabel();
					fileInputPanel.add(inputFile);
					inputFile.setText("Input file:");
					inputFile.setBounds(17, 19, 66, 14);
				}
				{
					inputFileTextBox = new JTextField();
					fileInputPanel.add(inputFileTextBox);
					inputFileTextBox.setBounds(95, 16, 142, 21);
				}
				{
					separatorLabel = new JLabel();
					fileInputPanel.add(separatorLabel);
					separatorLabel.setText("Separator:");
					separatorLabel.setBounds(17, 46, 66, 14);
				}
				{
					separatorTextBox = new JTextField();
					fileInputPanel.add(separatorTextBox);
					separatorTextBox.setText(",,,");
					separatorTextBox.setBounds(95, 43, 21, 21);
				}
				{
					selectInputFileButton = new JButton();
					fileInputPanel.add(selectInputFileButton);
					selectInputFileButton.setText("Select");
					selectInputFileButton.setBounds(243, 16, 103, 21);
					selectInputFileButton
							.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent arg0) {
									selectInputFile();
								}
							});
				}
			}
			databaseInputPanel = new JPanel();
			getContentPane().add(databaseInputPanel);
			databaseInputPanel.setBounds(5, 12, 642, 111);
			databaseInputPanel.setBorder(BorderFactory.createTitledBorder(null,
					"Input", TitledBorder.LEADING,
					TitledBorder.DEFAULT_POSITION));
			databaseInputPanel.setLayout(null);
			{
				tableNameLabel = new JLabel();
				databaseInputPanel.add(tableNameLabel);
				tableNameLabel.setText("Table:");
				tableNameLabel.setBounds(17, 19, 65, 14);
			}
			{
				ComboBoxModel tableComboBoxModel = new DefaultComboBoxModel(
						new String[0]);
				tableComboBox = new JComboBox();
				databaseInputPanel.add(tableComboBox);
				tableComboBox.setModel(tableComboBoxModel);
				tableComboBox.setBounds(94, 16, 189, 21);
				tableComboBox.addItemListener(new ItemListener() {

					@Override
					public void itemStateChanged(ItemEvent arg0) {
						updateColumnComboBoxes();
					}
				});
			}
			{
				column1Label = new JLabel();
				databaseInputPanel.add(column1Label);
				column1Label.setText("Column 1:");
				column1Label.setBounds(315, 19, 91, 14);
			}
			{
				column2Label = new JLabel();
				databaseInputPanel.add(column2Label);
				column2Label.setText("Column 2:");
				column2Label.setBounds(315, 49, 91, 14);
			}
			{
				column3Label = new JLabel();
				databaseInputPanel.add(column3Label);
				column3Label.setText("Column 3:");
				column3Label.setBounds(315, 80, 91, 14);
			}
			{
				ComboBoxModel column1ComboBoxModel = new DefaultComboBoxModel(
						new String[0]);
				column1ComboBox = new JComboBox();
				databaseInputPanel.add(column1ComboBox);
				column1ComboBox.setModel(column1ComboBoxModel);
				column1ComboBox.setBounds(424, 16, 189, 21);
			}
			{
				ComboBoxModel column2ComboBoxModel = new DefaultComboBoxModel(
						new String[0]);
				column2ComboBox = new JComboBox();
				databaseInputPanel.add(column2ComboBox);
				column2ComboBox.setModel(column2ComboBoxModel);
				column2ComboBox.setBounds(424, 46, 189, 21);
			}
			{
				ComboBoxModel column3ComboBoxModel = new DefaultComboBoxModel(
						new String[0]);
				column3ComboBox = new JComboBox();
				databaseInputPanel.add(column3ComboBox);
				column3ComboBox.setModel(column3ComboBoxModel);
				column3ComboBox.setBounds(424, 77, 189, 21);
			}

			// show only one input panel
			if (dataSource == DataSource.FILE) {
				fileInputPanel.setVisible(true);
				databaseInputPanel.setVisible(false);
			} else {
				fileInputPanel.setVisible(false);
				databaseInputPanel.setVisible(true);
			}
			{
				computationPanel = new JPanel();
				getContentPane().add(computationPanel);
				computationPanel.setBounds(5, 129, 642, 98);
				computationPanel.setBorder(BorderFactory.createTitledBorder(
						null, "Parameters", TitledBorder.LEADING,
						TitledBorder.DEFAULT_POSITION));
				computationPanel.setLayout(null);
				{
					minSupportPanel = new JPanel();
					computationPanel.add(minSupportPanel);
					minSupportPanel.setBounds(12, 19, 109, 51);
					minSupportPanel.setLayout(null);
					minSupportPanel.setBorder(BorderFactory
							.createTitledBorder("MinSupport"));
					{
						minSupportTextBox1 = new JTextField();
						minSupportPanel.add(minSupportTextBox1);
						minSupportTextBox1.setText("0");
						minSupportTextBox1.setBounds(10, 19, 24, 21);
					}
					{
						minSupportTextBox2 = new JTextField();
						minSupportPanel.add(minSupportTextBox2);
						minSupportTextBox2.setText("0");
						minSupportTextBox2.setBounds(42, 19, 24, 21);
					}
					{
						minSupportTextBox3 = new JTextField();
						minSupportPanel.add(minSupportTextBox3);
						minSupportTextBox3.setText("0");
						minSupportTextBox3.setBounds(75, 19, 24, 21);
					}
				}
				{
					thresholdPanel = new JPanel();
					computationPanel.add(thresholdPanel);
					thresholdPanel.setBounds(133, 19, 109, 51);
					thresholdPanel.setBorder(BorderFactory.createTitledBorder(
							null, "Threshold", TitledBorder.LEADING,
							TitledBorder.DEFAULT_POSITION));
					thresholdPanel.setLayout(null);
					{
						thresholdTextBox1 = new JTextField();
						thresholdPanel.add(thresholdTextBox1);
						thresholdTextBox1.setText("1");
						thresholdTextBox1.setBounds(11, 19, 24, 21);
					}
					{
						thresholdTextBox2 = new JTextField();
						thresholdPanel.add(thresholdTextBox2);
						thresholdTextBox2.setText("1");
						thresholdTextBox2.setBounds(38, 19, 24, 21);
					}
					{
						thresholdTextBox3 = new JTextField();
						thresholdPanel.add(thresholdTextBox3);
						thresholdTextBox3.setText("1");
						thresholdTextBox3.setBounds(68, 19, 24, 21);
					}
				}
			}
			{
				outputPanel = new JPanel();
				getContentPane().add(outputPanel);
				outputPanel.setBounds(5, 233, 642, 56);
				outputPanel.setBorder(BorderFactory.createTitledBorder(null,
						"Output", TitledBorder.LEADING,
						TitledBorder.DEFAULT_POSITION));
				outputPanel.setLayout(null);
				{
					graphFileLabel = new JLabel();
					outputPanel.add(graphFileLabel);
					graphFileLabel.setText("Graph file");
					graphFileLabel.setBounds(10, 19, 76, 14);
				}
				{
					graphFileTextbox = new JTextField();
					outputPanel.add(graphFileTextbox);
					graphFileTextbox.setBounds(98, 16, 144, 21);
				}
				{
					launchGraphCheckbox = new JCheckBox();
					outputPanel.add(launchGraphCheckbox);
					launchGraphCheckbox.setText("Open file when completed");
					launchGraphCheckbox.setBounds(387, 17, 208, 18);
					launchGraphCheckbox.setSelected(true);
				}
				{
					selectOutputFileButton = new JButton();
					outputPanel.add(selectOutputFileButton);
					selectOutputFileButton.setText("Select");
					selectOutputFileButton.setBounds(254, 16, 103, 21);
					selectOutputFileButton
							.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent e) {
									selectOutputFile();
								}
							});
				}
			}
			{
				goButton = new JButton();
				getContentPane().add(goButton);
				goButton.setText("Go");
				goButton.setBounds(545, 302, 103, 21);
				goButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						go();
					}
				});
			}
			initializeWindowAccordingToDatasource();
			pack();
			this.setSize(667, 368);
		} catch (Exception e) {
			// add your error handling code here
			e.printStackTrace();
		}
	}

	private void initializeWindowAccordingToDatasource() {
		if (dataSource == DataSource.FILE) {
			this.databaseInputPanel.setVisible(false);
			this.databaseInputPanel.setEnabled(false);
		} else {
			this.fileInputPanel.setVisible(false);
			this.fileInputPanel.setEnabled(false);
			this.tableComboBox.setModel(new DefaultComboBoxModel(
					this.controller.getTableNames().toArray(new String[0])));
			updateColumnComboBoxes();
		}
	}

	private void updateColumnComboBoxes() {
		String[] columns = this.controller.getColumnNames(
				this.tableComboBox.getSelectedItem().toString()).toArray(
				new String[0]);
		this.column1ComboBox.setModel(new DefaultComboBoxModel(columns));
		this.column2ComboBox.setModel(new DefaultComboBoxModel(columns));
		this.column3ComboBox.setModel(new DefaultComboBoxModel(columns));
	}

	private void selectInputFile() {
		JFileChooser fileChooser = new JFileChooser();
		int returnVal = fileChooser.showOpenDialog(this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			inputFileTextBox.setText(fileChooser.getSelectedFile().getPath());
		}
	}

	private void selectOutputFile() {
		JFileChooser fileChooser = new JFileChooser();
		int returnVal = fileChooser.showSaveDialog(this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			graphFileTextbox.setText(fileChooser.getSelectedFile().getPath());
		}
	}

	private void go() {
		boolean result = true;
		switch (dataSource) {
		case FILE:
			result = readDataFromFile();
			break;
		case DATABASE:
			result = readDataFromDatabase();
			break;
		}
		
		if (result) {
			computeNeighborhoods();
		}
	}
	
	private boolean readDataFromDatabase() {
		try {
			String tableName = tableComboBox.getSelectedItem().toString();
			String columnName1 = column1ComboBox.getSelectedItem().toString();
			String columnName2 = column2ComboBox.getSelectedItem().toString();
			String columnName3 = column3ComboBox.getSelectedItem().toString();
			controller.readDataFromDatabase(tableName, columnName1, columnName2, columnName3);
			return true;
		} catch (Exception ex) {
			putError(ex.toString());
			return false;
		}
	}
	
	private boolean readDataFromFile() {
		try {
			controller.readDataFromFile(new File(inputFileTextBox.getText()),
					separatorTextBox.getText());
			return true;
		} catch (IOException ex) {
			putError("Error reading file: " + ex);
			return false;
		}
	}

	private void computeNeighborhoods() {
		try {

			int minSupport[] = new int[3];
			int thresholds[] = new int[3];

			minSupport[0] = parseInteger(minSupportTextBox1.getText(), 0);
			minSupport[1] = parseInteger(minSupportTextBox2.getText(), 0);
			minSupport[2] = parseInteger(minSupportTextBox3.getText(), 0);

			thresholds[0] = parseInteger(thresholdTextBox1.getText(), 1);
			thresholds[1] = parseInteger(thresholdTextBox2.getText(), 1);
			thresholds[2] = parseInteger(thresholdTextBox3.getText(), 1);

			File outputFile = new File(graphFileTextbox.getText());

			controller.computeNeighborhoods(outputFile, minSupport, thresholds,
					launchGraphCheckbox.isSelected());
			JOptionPane.showMessageDialog(this,
					"Neighbourhood generation completed successfully",
					"Success", JOptionPane.INFORMATION_MESSAGE);
		} catch (IOException ex) {
			putError("Error writing output: " + ex);
		} catch (NumberFormatException ex) {
			// do nothing;
		}
	}

	private int parseInteger(String text, int minValue) {
		try {
			return Integer.parseInt(text);
		} catch (NumberFormatException ex) {
			putError("Invalid number: " + text + ". Must be integer >= "
					+ minValue);
			throw ex;
		}
	}

	/**
	 * Displays an error dialog with the given error message.
	 * @param text - the error message
	 */
	public void putError(String text) {
		JOptionPane.showMessageDialog(this, text, "Error",
				JOptionPane.ERROR_MESSAGE);
	}

}
