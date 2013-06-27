package ro.ubbcluj2013.cs.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListModel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListDataListener;

import org.omg.CORBA.INITIALIZE;

import ro.ubbcluj2013.cs.CSXMetaDiagram;
import ro.ubbcluj2013.cs.ElbaCSXImporter;

import java.awt.Color;
import java.util.ArrayList;


/**
 * Class for designing the interface for condition/attribute selection process.
 * @author Andra
 *
 */
public class ConditionSelectionFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JButton selectConditionButton;
	private JList<String> conditionList;
	private JList<String> attributeList;
	private JList<String> diagramList;
	private DefaultListModel<String> diagramListModel;
	private DefaultListModel<String> conditionListModel;
	private DefaultListModel<String> attributeListModel;
	private JButton button;
	private JLabel lblConditions;
	private ElbaCSXImporter elbaCSXImporter;
	
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ConditionSelectionFrame frame = new ConditionSelectionFrame();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public ConditionSelectionFrame() {
		initializeGUI();
		ArrayList<String> list = new ArrayList<String>();
		list.add("Item one");
		list.add("Item two");
		list.add("Item three");
		list.add("Item four");
		initializeConditionList(list);
	}
	
	public ConditionSelectionFrame(ElbaCSXImporter controller) {
		initializeGUI();
		this.elbaCSXImporter = controller;
		ArrayList<String> list = new ArrayList<String>();
		list = this.elbaCSXImporter.getDiagramNameList();
		initializeConditionList(list);
	}

	private void initializeGUI() {
		setTitle("Selection Frame");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 563, 327);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		contentPane.setLayout(null);
		attributeList = new JList();
		attributeList.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		getContentPane().add(attributeList);
		attributeList.setBounds(219, 33, 127, 181);
		contentPane.add(attributeList, BorderLayout.CENTER);
		
		JLabel lblSelectCondition = new JLabel("Attributes:");
		lblSelectCondition.setBounds(219, 11, 104, 14);
		contentPane.add(lblSelectCondition);
		
		conditionList = new JList();
		conditionList.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		conditionList.setBounds(423, 33, 114, 181);
		contentPane.add(conditionList);
		
		selectConditionButton = new JButton(">>>");
		selectConditionButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				moveItemToSelected();
			}
		});
		selectConditionButton.setBounds(356, 59, 57, 23);
		contentPane.add(selectConditionButton);
		
		JButton btnDone = new JButton("Done");
		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendInfo();
			}
		});
		btnDone.setBounds(448, 246, 89, 23);
		contentPane.add(btnDone);
		
		button = new JButton("<<<");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				moveItemBack();
			}
		});
		button.setBounds(356, 93, 57, 23);
		contentPane.add(button);
		
		lblConditions = new JLabel("Conditions:");
		lblConditions.setBounds(423, 11, 104, 14);
		contentPane.add(lblConditions);
		
		diagramList = new JList();
		diagramList.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		diagramList.setBounds(10, 34, 147, 181);
		contentPane.add(diagramList);
		
		JLabel lblDiagrams = new JLabel("Diagrams:");
		lblDiagrams.setBounds(10, 11, 104, 14);
		contentPane.add(lblDiagrams);
		
		JButton btnAddAttribute = new JButton("Add as attribute");
		btnAddAttribute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addAttribute();
			}
		});
		btnAddAttribute.setBounds(10, 226, 147, 23);
		contentPane.add(btnAddAttribute);
		
		JButton btnNewButton = new JButton("Add as condition");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addCondtion();
			}
		});
		btnNewButton.setBounds(10, 257, 147, 23);
		contentPane.add(btnNewButton);
		
		JButton btnRemoveSelectedAttribute = new JButton("Remove selected attribute");
		btnRemoveSelectedAttribute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int pos = attributeList.getSelectedIndex();
				if (pos>=0) {
					attributeListModel.remove(pos);
					attributeList.setModel(attributeListModel);
				}
			}
		});
		btnRemoveSelectedAttribute.setBounds(219, 226, 194, 23);
		contentPane.add(btnRemoveSelectedAttribute);
		
		JButton btnRemoveSelectedCondition = new JButton("Remove selected condition");
		btnRemoveSelectedCondition.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int pos = conditionList.getSelectedIndex();
				if (pos>=0) {
					conditionListModel.remove(pos);
					conditionList.setModel(conditionListModel);
				}
			}
		});
		btnRemoveSelectedCondition.setBounds(219, 257, 194, 23);
		contentPane.add(btnRemoveSelectedCondition);
	}
	

	
	protected void sendInfo() {
		//getTriadicData(attrlist, condlist);
		//attributes
		for (int i=0; i<attributeListModel.size(); i++) {
			String atr = attributeListModel.get(i);
			//CSXMetaDiagram cmd = ElbaCSXImporter.createMetaDiagram(atr);
			elbaCSXImporter.addAttributeMetaDiagram(atr);
		}
		
		//conditions
		for (int i=0; i<conditionListModel.size(); i++) {
			String atr = conditionListModel.get(i);
			//CSXMetaDiagram cmd = elbaCSXImporter.createMetaDiagram(atr);
			elbaCSXImporter.addConditionMetaDiagram(atr); 
		}
		//send attributeList + condList
		//elbaCSXImporter.sendLists();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initializeConditionList(ArrayList<String> list) {	
		//DefaultListModel listModel = new DefaultListModel();
		diagramListModel = new DefaultListModel();
		for (String s: list){
			diagramListModel.addElement(s);
		}
		diagramList.setModel(diagramListModel);
		
		conditionListModel = new DefaultListModel();
		conditionList.setModel(conditionListModel);
		attributeListModel = new DefaultListModel<String>();
		attributeList.setModel(attributeListModel);
	}
	
	@SuppressWarnings("unchecked")
	private void moveItemToSelected () {
		int pos = attributeList.getSelectedIndex();
		if (pos >= 0) {
			String elem = "";
			elem = attributeListModel.get(pos);
			conditionListModel.addElement(elem);
			attributeListModel.removeElementAt(pos);
			attributeList.setModel(attributeListModel);
			conditionList.setModel(conditionListModel);
		}
	}
	

	@SuppressWarnings("unchecked")
	protected void moveItemBack() {
		int pos = conditionList.getSelectedIndex();
		if (pos >= 0) {
			String elem = "";
			elem = conditionListModel.get(pos);
			attributeListModel.addElement(elem);
			conditionListModel.removeElementAt(pos);
			conditionList.setModel(conditionListModel);
			attributeList.setModel(attributeListModel);
		}
	}
	
	protected String combineItems() {
		int [] positions = diagramList.getSelectedIndices();
		String elem = "";
		for (int i: positions) {
			elem = elem + "+" + diagramListModel.get(i);
		}
		return elem;
	}
	
	protected void addCondtion() {
		String elem = combineItems();
		conditionListModel.addElement(elem);
		conditionList.setModel(conditionListModel);
	}
	
	protected void addAttribute() {
		String elem = combineItems();
		
		attributeListModel.addElement(elem);
		attributeList.setModel(attributeListModel);
	}
	
	protected void removeCondtion() {
		int pos = conditionList.getSelectedIndex();
		conditionListModel.remove(pos);
		conditionList.setModel(conditionListModel);
	}
	
	protected void removeAttribute() {
		int pos = attributeList.getSelectedIndex();
		attributeListModel.remove(pos);
		attributeList.setModel(attributeListModel);
	}
}
