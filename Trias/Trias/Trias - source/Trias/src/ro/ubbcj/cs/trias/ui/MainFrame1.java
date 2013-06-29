package ro.ubbcj.cs.trias.ui;

import java.awt.EventQueue;
import java.awt.Label;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JComboBox;
import java.awt.BorderLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.Box;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.JTabbedPane;
import java.awt.CardLayout;
import java.io.File;
import java.io.IOException;

import javax.swing.JCheckBox;

import ro.ubbcj.cs.trias.controller.MainTriasController;
import ro.ubbcluj2013.cs.ElbaCSXImporter;
import ro.ubbcluj2013.cs.ui.ConditionSelectionFrame;

public class MainFrame1 extends JFrame{

	private MainTriasController controller;
	
	private ConditionSelectionFrame conditionSelectionFrame;
	private ElbaCSXImporter elbaCSXImporter;
	
	private JFrame frmTriasConcept;
	private JTextField txt_minSup1;
	private JComboBox comboBox_sourceType;
	private JTextField txt_minSup2;
	private JTextField txt_minSup3;
	private JTextField txt_threshold1;
	private JTextField txt_threshold2;
	private JTextField txt_threshold3;
	private JLabel lbl_multiPurpose;
	private JTextField textField_separator;
	private JLabel lbl_fileChooser;
	private JTextField textField_inputFile;
	private JButton btn_selectFile;
	private JTabbedPane tabbedPane;
	private JPanel panel_3;
	private JPanel panel_4;
	private JLabel lblNewLabel_2;
	private JTextField txt_outputFolder;
	private JPanel panel_columns;
	private JLabel lbl_TableName;
	private JComboBox comboBox_table;
	private JComboBox comboBox_objects;
	private JComboBox comboBox_attributes;
	private JComboBox comboBox_conditions;
	private JButton btn_selectRelations;
	private JButton btn_startComputing;
	private JTextField textField;
	private JLabel lblNewLabel_7;
	private JComboBox comboBox_algorithm;
	private JTextField textField_tableName;
	private JTextField textField_columnName;

	private JLabel lblTableName;

	private JLabel lblObjectColumnName;

	/**
	 * Create the application.
	 */
	public MainFrame1(MainTriasController controller) {
		this.controller = controller;
		initialize();
		this.frmTriasConcept.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTriasConcept = new JFrame();
		frmTriasConcept.setTitle("Trias Concept ");
		frmTriasConcept.setBounds(100, 100, 670, 436);
		frmTriasConcept.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTriasConcept.getContentPane().setLayout(null);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 639, 376);
		frmTriasConcept.getContentPane().add(tabbedPane);
		
		panel_3 = new JPanel();
		tabbedPane.addTab("Data Source selection", null, panel_3, null);
		panel_3.setLayout(null);
		
		lbl_fileChooser = new JLabel("New label");
		lbl_fileChooser.setBounds(10, 158, 308, 14);
		panel_3.add(lbl_fileChooser);
		
		lbl_fileChooser.setVisible(false);
		
		JLabel lblNewLabel = new JLabel("Select data source type");
		lblNewLabel.setBounds(10, 27, 162, 14);
		panel_3.add(lblNewLabel);
		
		btn_selectFile = new JButton("Select file");
		btn_selectFile.setBounds(277, 183, 91, 23);
		panel_3.add(btn_selectFile);
		
		textField_inputFile = new JTextField();
		textField_inputFile.setBounds(10, 183, 257, 22);
		panel_3.add(textField_inputFile);
		textField_inputFile.setColumns(10);
		
		textField_separator = new JTextField();
		textField_separator.setBounds(10, 132, 86, 20);
		panel_3.add(textField_separator);
		textField_separator.setColumns(10);
		textField_separator.setText(",,,");
		
		lbl_multiPurpose = new JLabel("");
		lbl_multiPurpose.setBounds(10, 110, 308, 14);
		panel_3.add(lbl_multiPurpose);
		lbl_multiPurpose.setVisible(false);
		
		// Create an itemListener - whenever the element is changed, the text in the label changes.
		comboBox_sourceType = new JComboBox();
		comboBox_sourceType.setBounds(10, 49, 308, 22);
		panel_3.add(comboBox_sourceType);
		comboBox_sourceType.addItem("Text file");
		comboBox_sourceType.addItem("SQL database");
		comboBox_sourceType.addItem("Excel file");
		comboBox_sourceType.addItem("SQL databse and .csx file");
		comboBox_sourceType.addItem("Manual triadic context editor");
		
		JButton btn_DoneDS = new JButton("Done");
		btn_DoneDS.setBounds(10, 76, 91, 23);
		panel_3.add(btn_DoneDS);
		
		JPanel panel = new JPanel();
		panel.setBounds(333, 11, 291, 92);
		panel_3.add(panel);
		panel.setBorder(null);
		panel.setToolTipText("");
		panel.setLayout(null);
		
		final JLabel labelDataSource = new JLabel("");
		labelDataSource.setVerticalAlignment(SwingConstants.TOP);
		labelDataSource.setBounds(10, 11, 271, 70);
		panel.add(labelDataSource);
		labelDataSource.setText("<html>A text file will contain on each line a triple,<br>(object, attribute, condition) " +
				"separated by<br>some custom separator.</html>");
		
		panel_columns = new JPanel();
		panel_columns.setBounds(391, 110, 233, 125);
		panel_columns.setBorder(new TitledBorder(null, "Select Columns:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.add(panel_columns);
		panel_columns.setLayout(null);
		
		JLabel lblNewLabel_4 = new JLabel("Objects:");
		lblNewLabel_4.setBounds(10, 24, 57, 14);
		panel_columns.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Attributes:");
		lblNewLabel_5.setBounds(10, 57, 70, 14);
		panel_columns.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Conditions:");
		lblNewLabel_6.setBounds(10, 86, 70, 14);
		panel_columns.add(lblNewLabel_6);
		
		comboBox_objects = new JComboBox();
		comboBox_objects.setBounds(80, 20, 143, 22);
		panel_columns.add(comboBox_objects);
		
		comboBox_attributes = new JComboBox();
		comboBox_attributes.setBounds(80, 53, 143, 22);
		panel_columns.add(comboBox_attributes);
		
		comboBox_conditions = new JComboBox();
		comboBox_conditions.setBounds(80, 82, 143, 22);
		panel_columns.add(comboBox_conditions);
		
		comboBox_table = new JComboBox();
		comboBox_table.setBounds(10, 244, 162, 22);
		panel_3.add(comboBox_table);
		comboBox_table.setVisible(false);
		
		lbl_TableName = new JLabel("Select the table name");
		lbl_TableName.setBounds(10, 221, 162, 14);
		panel_3.add(lbl_TableName);
		lbl_TableName.setVisible(false);
		
		btn_selectRelations = new JButton("Select tricontext relations");
		btn_selectRelations.setBounds(277, 244, 184, 23);
		btn_selectRelations.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SelectTricontextRelations();
			}
		});
		panel_3.add(btn_selectRelations);
		
		textField_tableName = new JTextField();
		textField_tableName.setBounds(176, 284, 155, 20);
		panel_3.add(textField_tableName);
		textField_tableName.setColumns(10);
		
		lblTableName = new JLabel("Table name :");
		lblTableName.setBounds(10, 287, 162, 14);
		panel_3.add(lblTableName);
		
		textField_columnName = new JTextField();
		textField_columnName.setBounds(176, 315, 155, 20);
		panel_3.add(textField_columnName);
		textField_columnName.setColumns(10);
		
		lblObjectColumnName = new JLabel("Object column name :");
		lblObjectColumnName.setBounds(10, 318, 156, 14);
		panel_3.add(lblObjectColumnName);
		btn_selectRelations.setVisible(false);
		
		panel_4 = new JPanel();
		tabbedPane.addTab("Configuration", null, panel_4, null);
		panel_4.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(333, 11, 232, 74);
		panel_4.add(panel_2);
		panel_2.setLayout(null);
		panel_2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Threshold", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		txt_threshold1 = new JTextField();
		txt_threshold1.setColumns(10);
		txt_threshold1.setBounds(10, 23, 40, 20);
		panel_2.add(txt_threshold1);
		txt_threshold1.setText("1");
		
		txt_threshold2 = new JTextField();
		txt_threshold2.setColumns(10);
		txt_threshold2.setBounds(59, 23, 40, 20);
		panel_2.add(txt_threshold2);
		txt_threshold2.setText("1");
		
		txt_threshold3 = new JTextField();
		txt_threshold3.setColumns(10);
		txt_threshold3.setBounds(109, 23, 40, 20);
		panel_2.add(txt_threshold3);
		txt_threshold3.setText("1");
		
		JLabel lblSelectTheThreshold = new JLabel("Select the Threshold values");
		lblSelectTheThreshold.setBounds(10, 54, 163, 14);
		panel_2.add(lblSelectTheThreshold);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(29, 11, 232, 74);
		panel_4.add(panel_1);
		panel_1.setBorder(new TitledBorder(null, "MinSupport", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setLayout(null);
		
		txt_minSup1 = new JTextField();
		txt_minSup1.setBounds(10, 23, 40, 20);
		panel_1.add(txt_minSup1);
		txt_minSup1.setColumns(10);
		txt_minSup1.setText("0");
		
		txt_minSup2 = new JTextField();
		txt_minSup2.setColumns(10);
		txt_minSup2.setBounds(59, 23, 40, 20);
		panel_1.add(txt_minSup2);
		txt_minSup2.setText("0");
		
		txt_minSup3 = new JTextField();
		txt_minSup3.setColumns(10);
		txt_minSup3.setBounds(109, 23, 40, 20);
		panel_1.add(txt_minSup3);
		txt_minSup3.setText("0");
		
		JLabel lblNewLabel_1 = new JLabel("Select the MinSupport values");
		lblNewLabel_1.setBounds(10, 54, 271, 14);
		panel_1.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Please choose:");
		lblNewLabel_2.setBounds(29, 109, 249, 14);
		panel_4.add(lblNewLabel_2);
		
		comboBox_algorithm = new JComboBox();
		comboBox_algorithm.setBounds(29, 134, 302, 22);
		panel_4.add(comboBox_algorithm);
		comboBox_algorithm.addItem("Compute concepts using Trias");
		comboBox_algorithm.addItem("Compute concept neighborhoods");
		
		JPanel panel_5 = new JPanel();
		tabbedPane.addTab("Output information", null, panel_5, null);
		panel_5.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("Select the output representation:");
		lblNewLabel_3.setBounds(28, 23, 270, 14);
		panel_5.add(lblNewLabel_3);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBounds(28, 48, 302, 116);
		panel_5.add(panel_6);
		panel_6.setLayout(null);
		
		JCheckBox chck_textFile = new JCheckBox("Save a list of concepts inside a text file");
		chck_textFile.setBounds(6, 7, 258, 23);
		panel_6.add(chck_textFile);
		
		JCheckBox chck_XML = new JCheckBox("Save the concepts inside an XML file");
		chck_XML.setBounds(6, 33, 258, 23);
		panel_6.add(chck_XML);
		
		JCheckBox chckbxNewCheckBox_2 = new JCheckBox("Save a graph file (use ZGRViewer/GraphViz to open)");
		chckbxNewCheckBox_2.setBounds(6, 59, 275, 23);
		panel_6.add(chckbxNewCheckBox_2);
		
		JCheckBox chckbxNewCheckBox_3 = new JCheckBox("Show a concept tree");
		chckbxNewCheckBox_3.setBounds(6, 85, 155, 23);
		panel_6.add(chckbxNewCheckBox_3);
		
		txt_outputFolder = new JTextField();
		txt_outputFolder.setBounds(28, 200, 302, 20);
		panel_5.add(txt_outputFolder);
		txt_outputFolder.setColumns(10);
		
		JLabel lblSelectAFolder = new JLabel("Select a folder to save file(s):");
		lblSelectAFolder.setBounds(28, 175, 184, 14);
		panel_5.add(lblSelectAFolder);
		
		JButton btn_outputFolder = new JButton("Select");
		btn_outputFolder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SelectOutputFolder();
			}
		});
		btn_outputFolder.setBounds(340, 199, 91, 23);
		panel_5.add(btn_outputFolder);
		
		btn_startComputing = new JButton("Start Computing!");
		btn_startComputing.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				StartComputing();
			}
		});
		btn_startComputing.setBounds(459, 193, 133, 35);
		panel_5.add(btn_startComputing);
		
		textField = new JTextField();
		textField.setBounds(28, 249, 223, 20);
		panel_5.add(textField);
		textField.setColumns(10);
		
		lblNewLabel_7 = new JLabel("Enter a filename:");
		lblNewLabel_7.setBounds(28, 231, 198, 14);
		panel_5.add(lblNewLabel_7);
		btn_DoneDS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				HandleDataSourceSelection(comboBox_sourceType.getSelectedIndex());
			}
		});
		
		// The label text will be changed, showing explanations to the user
		comboBox_sourceType.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent event) {
					switch (comboBox_sourceType.getSelectedIndex())
					{
					// text file
					case 0:
						labelDataSource.setText("<html>Select a text file contains on each line a triple," +
								"(object, attribute, condition) separated by some custom separator.</html>");
						SetInvisibleItems();
						
						break;
					// sql database
					case 1:
						// COMMENT: we simply choose the columns from the database
						labelDataSource.setText("<html>Select a sql database file and choose which columns contain " +
								"objects, attributes and conditions. This option doesn't allow adding extra conditions.</html>");
						SetInvisibleItems();
						break;
					// excel file
					case 2:
						// COMMENT: this behaves the same as above
						labelDataSource.setText("<html><br>Select an Excel file's columns that contain objects, " +
								"attributes and conditions.</html>");
						SetInvisibleItems();
						break;
					// CSX and sql database
					case 3:
						labelDataSource.setText("<html>Select an .csx file previously generated using Elba, " +
								"that contains relations between tricontext elements. Select the objects, attributes " +
								"and conditions and apply them on an sql database.</html>");
						SetInvisibleItems();
						break;
					// manual tricontext editor
					case 4:
						labelDataSource.setText("<html>Introduce objects, attributes and conditions and " +
								"manually select the relations among these elements or open a manually created " +
								"tricontext file for editing.</html>");
						SetInvisibleItems();
						break;
					}
			}
		});
		
		textField_separator.setVisible(false);
		textField_inputFile.setVisible(false);
		
		// ActionListener for pressing the Select input file button
		btn_selectFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SelectInputFile();
			}
		});
		btn_selectFile.setVisible(false);
		
		SetInvisibleItems();
	}
	
	/**
	 * This function handles the selection of a certain datasources and calls appropriate functions
	 * @param selectedIndex
	 */
	private void HandleDataSourceSelection(int selectedIndex) {
		switch (selectedIndex)
		{
		case 0:
			DataSourceHandling_TextFile();
			break;
		case 1:
			DataSourceHandling_SQLDatabase();
			break;
		case 2:
			DataSourceHandling_ExcelFile();
			break;
		case 3:
			DataSourceHandling_CSX_SQLDatabase();
			break;
		case 4:
			DataSourceHandling_Manual();
			break;
		}
	}

	/**
	 * Call manual editing frame
	 */
	private void DataSourceHandling_Manual() {
		// COMMENT: Ela, aici intializezi si chemi fereastra ta
	}

	/**
	 * Select an *.csx file and set needed UI items to visible
	 */
	private void DataSourceHandling_CSX_SQLDatabase() {
		lbl_fileChooser.setText("Choose an CSX file:");
		lbl_fileChooser.setVisible(true);
		btn_selectFile.setVisible(true);
		textField_inputFile.setVisible(true);
		btn_selectRelations.setVisible(true);
		textField_columnName.setVisible(true);
		textField_tableName.setVisible(true);
		lblTableName.setVisible(true);
		lblObjectColumnName.setVisible(true);
		
		// COMMENT: Trebuie verificat daca aici trebuie precizat ca citesc dintr-o baza de date.
		// Asta depinde de functiile lui George cred :-)
		this.controller.SetDataSourceAndReadProperties(DataSource.DATABASE);
		
		
	}

	/**
	 * Select an Excel file - set to visible the needed UI items
	 */
	private void DataSourceHandling_ExcelFile() {
		// COMMENT: George, aici nu imi mai amintesc daca selectai si care coloane
		// contin elementele tricontextului sau daca luai de pe primele 3 coloane. 
		// In cazul in care nu le selectezi manual, sa nu setezi vizibilitatea lui panel_columns pe true.
		lbl_fileChooser.setText("Choose an Excel file:");
		lbl_fileChooser.setVisible(true);
		btn_selectFile.setVisible(true);
		textField_inputFile.setVisible(true);
		panel_columns.setVisible(true);
		lbl_TableName.setVisible(true);
		comboBox_table.setVisible(true);
		// COMMENT: daca deschid un Excel am nevoie sa citesc din database.properties?
	}

	/**
	 * Select the sql database - set to visible the needed UI items
	 */
	private void DataSourceHandling_SQLDatabase() {
		// COMMENT: tocmai am realizat ca se citeste din database.properties unde se afla baza de date.
		// Daca asa e, atunci lbl_fileChooser si btn_selectFile ar trebui setate invizibile
		lbl_fileChooser.setText("Choose a sql database file:");
		lbl_fileChooser.setVisible(true);
		btn_selectFile.setVisible(true);
		textField_inputFile.setVisible(true);
		panel_columns.setVisible(true);
		lbl_TableName.setVisible(true);
		comboBox_table.setVisible(true);
		this.controller.SetDataSourceAndReadProperties(DataSource.DATABASE);
	}

	/**
	 * Select the tricontext separator and show needed UI items (specific buttons, labels, etc)
	 */
	private void DataSourceHandling_TextFile() {
		// we create a label that 
		lbl_multiPurpose.setText("Select a string separator used inside the text file:");
		lbl_multiPurpose.setVisible(true);
		textField_separator.setVisible(true);
		lbl_fileChooser.setText("Choose a text file containing a tricontext:");
		lbl_fileChooser.setVisible(true);
		btn_selectFile.setVisible(true);
		textField_inputFile.setVisible(true);
		this.controller.SetDataSourceAndReadProperties(DataSource.FILE);
	}
	
	/**
	 * Select an input file. This can be text, sql, excel.
	 */
	private void SelectInputFile() {
		JFileChooser fileChooser = new JFileChooser();
		int returnVal = fileChooser.showOpenDialog(this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			textField_inputFile.setText(fileChooser.getSelectedFile().getPath());
		}
		
		// if we have chosen the select sql/excel option then we must populate the 
		// table/sheet name
		// if we have some datasource type that contains a database or an Excel file, 
		// we have to populate the columns and the table/sheet comboboxes
		// COMMENT: daca pentru Excel luam doar primele 3 coloane, atunci conditia de mai jos se va modifica
		// si selectedItemIndex va trebui sa fie ori 1 ori 3 :)
		int selectedItemIndex = comboBox_sourceType.getSelectedIndex();
		if ((selectedItemIndex != 0) && (selectedItemIndex != 4))
		{
			comboBox_table.setModel(new DefaultComboBoxModel(
					this.controller.getTableNames().toArray(new String[0])));
			UpdateColumnComboBoxes();
		}
	}
	
	/**
	 * Updates the comboboxes with the names of the columns in the database/excel file
	 */
	private void UpdateColumnComboBoxes() {
		String[] columns = this.controller.getColumnNames(
				comboBox_table.getSelectedItem().toString()).toArray(
				new String[0]);
		comboBox_objects.setModel(new DefaultComboBoxModel(columns));
		comboBox_attributes.setModel(new DefaultComboBoxModel(columns));
		comboBox_conditions.setModel(new DefaultComboBoxModel(columns));
	}
	
	/**
	 * Select the output folder in which the selected types of files will be saved.
	 */
	private void SelectOutputFolder()
	{
		JFileChooser folderChooser = new JFileChooser();
		folderChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int returnVal = folderChooser.showOpenDialog(this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			txt_outputFolder.setText(folderChooser.getSelectedFile().getPath());
		}
	}
	
	public void SelectTricontextRelations()
	{
		String filePath = textField_inputFile.getText();
		elbaCSXImporter = new ElbaCSXImporter(filePath);		
	}
	
	/**
	 * This method sets various labels, buttons, text boxes invisible whenever
	 * this is needed.
	 */
	private void SetInvisibleItems()
	{
		lbl_fileChooser.setVisible(false);
		textField_separator.setVisible(false);
		textField_inputFile.setVisible(false);
		btn_selectFile.setVisible(false);
		lbl_multiPurpose.setVisible(false);
		panel_columns.setVisible(false);
		lbl_TableName.setVisible(false);
		comboBox_table.setVisible(false);
		btn_selectRelations.setVisible(false);
		textField_columnName.setVisible(false);
		textField_tableName.setVisible(false);
		lblTableName.setVisible(false);
		lblObjectColumnName.setVisible(false);
		
	}
	
	/**
	 * This method computes the chosen concepts/concept neighborhoods.
	 * The output will also be computed here.
	 */
	private void StartComputing()
	{
		// Check if we have valid data
		if (!CheckForValidData())
		{
			return;
		}
		// Read data
		boolean result = true;
		switch (this.controller.GetDataSource()) {
		case FILE:
			result = readDataFromFile();
			break;
		case DATABASE:
			result = readDataFromDatabase();
			break;
		case EXCEL:
			// COMMENT: cred ca trebuie chemata o functie de-a lui George aici
			break;
		}
		
		// Compute concepts
		if (result) {
			if (comboBox_algorithm.getSelectedIndex() == 0)
			{
				computeConcepts();	
			}
			else 
			{
				computeNeighborhoods();
			}
		}
		
		// Save results
		
		// COMMENT: not implemented yet
		// we should keep the results inside the controller and create some new functions
		// in the controller or in some output related class that create the files. 
	}
	
	/*
	 * Compute the concept neighborhoods
	 */
	private void computeNeighborhoods() {
		try {

			int minSupport[] = new int[3];
			int thresholds[] = new int[3];

			minSupport[0] = parseInteger(txt_minSup1.getText(), 0);
			minSupport[1] = parseInteger(txt_minSup2.getText(), 0);
			minSupport[2] = parseInteger(txt_minSup3.getText(), 0);

			thresholds[0] = parseInteger(txt_threshold1.getText(), 1);
			thresholds[1] = parseInteger(txt_threshold2.getText(), 1);
			thresholds[2] = parseInteger(txt_threshold3.getText(), 1);

			controller.computeNeighborhoods(minSupport, thresholds,
					false);
			JOptionPane.showMessageDialog(this,
					"Neighbourhood generation completed successfully",
					"Success", JOptionPane.INFORMATION_MESSAGE);
		} catch (IOException ex) {
			putError("Error writing output: " + ex);
		} catch (NumberFormatException ex) {
			// do nothing;
		}
	}
	
	/*
	 * We compute all the concepts
	 */
	private void computeConcepts() {
		try {

			int minSupport[] = new int[3];
			int thresholds[] = new int[3];

			minSupport[0] = parseInteger(txt_minSup1.getText(), 0);
			minSupport[1] = parseInteger(txt_minSup2.getText(), 0);
			minSupport[2] = parseInteger(txt_minSup3.getText(), 0);

			thresholds[0] = parseInteger(txt_threshold1.getText(), 1);
			thresholds[1] = parseInteger(txt_threshold2.getText(), 1);
			thresholds[2] = parseInteger(txt_threshold3.getText(), 1);


			controller.computeAllConcepts(minSupport, thresholds);
			JOptionPane.showMessageDialog(this,
					"Neighbourhood generation completed successfully",
					"Success", JOptionPane.INFORMATION_MESSAGE);
		} catch (NumberFormatException ex) {
			// do nothing;
		}
	}
	
	/**
	 * Parse an int
	 * @param text - string to be parsed
	 * @param minValue
	 * @return
	 */
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
	 * Reads the information from a file
	 * @return boolean - operation succedeed or not
	 */
	private boolean readDataFromFile() {
		try {
			controller.readDataFromFile(new File(textField_inputFile.getText()),
					textField_separator.getText());
			return true;
		} catch (IOException ex) {
			putError("Error reading file: " + ex);
			return false;
		}
	}
	
	/**
	 * Reads the information from a database
	 * @return boolean - operation succedeed or not
	 */
	private boolean readDataFromDatabase() {
		try {
			String tableName = comboBox_table.getSelectedItem().toString();
			String columnName1 = comboBox_objects.getSelectedItem().toString();
			String columnName2 = comboBox_attributes.getSelectedItem().toString();
			String columnName3 = comboBox_conditions.getSelectedItem().toString();
			controller.readDataFromDatabase(tableName, columnName1, columnName2, columnName3);
			return true;
		} catch (Exception ex) {
			putError(ex.toString());
			return false;
		}
	}
	
	/**
	 * 
	 * @return a boolean  - whether the data is valid or not
	 */
	// COMMENT: not implemented yet
	private boolean CheckForValidData()
	{
		boolean valid = true;
		
		// check for having paths inside text boxes (if needed)
		// check for minSupport and threshold being in bounds
		
		return valid;
	}
	
	/**
	 * Show some message dialog
	 * @param text - the message we want to show up
	 */
	public void putError(String text) {
		JOptionPane.showMessageDialog(this, text, "Error",
				JOptionPane.ERROR_MESSAGE);
	}
	
	/**
	 * Get the table name from the text input
	 */
	public String getTableName() {
		return textField_tableName.getText();
	}
	
	/**
	 * Get the column name from the text input
	 */
	public String getColumnName() {
		return textField_columnName.getText();
	}
}
