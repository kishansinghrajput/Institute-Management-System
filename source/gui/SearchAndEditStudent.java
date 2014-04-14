package ims.gui;

import javax.swing.*;
import javax.swing.table.TableColumn;
import ims.main.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

public class SearchAndEditStudent extends JFrame implements ItemListener, ActionListener, KeyListener, ListSelectionListener
{
	private static final long serialVersionUID = 1L;
	private JPanel panel_1, panel_2, panel_3, panel_4, panel_5, panel_6, panel_7;
	@SuppressWarnings("rawtypes")
	private JComboBox searchcombobox;
	private JTextField searchtextfield, addfeestextfield;
	private JButton searchbutton, addfeesbutton, addcoursebutton;
	private JCheckBox allfieldcheckbox;
	private JLabel totalstudentlabel, feespayedlabel, feesduelabel, totalfeeslabel;
	private JComboBox<String> coursecombobox;
	private JSpinner spinner;
	private SpinnerModel spinnermodel;
	
	private MyTableModel mytablemodel, feestablemodel;
	private JTable table, feestable;
	private JScrollPane scrollpane, feesscrollpane;
	
	
	public SearchAndEditStudent()
	{
		initializeComponent();
		makeGUI();
	}
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initializeComponent()
	{
		//Initialize panels
		panel_1 = new JPanel();
		panel_2 = new JPanel();
		panel_3 = new JPanel();
		panel_4 = new JPanel();
		panel_5 = new JPanel();
		panel_6 = new JPanel();
		panel_7 = new JPanel();
		
		
		//Initialize spinner
		spinnermodel = new SpinnerNumberModel(0, 0, 10, 1);
		spinner = new JSpinner(spinnermodel);
		
		
		//Initialize combo box
		searchcombobox = new JComboBox();
		searchcombobox.addItem("All");
		searchcombobox.addItem("By Name");
		searchcombobox.addItem("By Guardian Name");
		searchcombobox.addItem("By Course");
		searchcombobox.addItem("Due Fees");
		searchcombobox.setSelectedIndex(0);
		
		coursecombobox = new JComboBox();
		coursecombobox.addItem("Select Course");
		Database db = new Database();
		String courses[] = db.getCourses();
		
		if(courses != null)
		{
			for(String x: courses)
			{
				coursecombobox.addItem(x);
			}
		}
		
		
		//Initialize text field
		searchtextfield = new JTextField(10);
		addfeestextfield = new JTextField(8);
		
		
		//Initialize button
		searchbutton = new JButton("Search");
		addfeesbutton = new JButton("Add fees");
		addcoursebutton = new JButton("Add Course");
		
		
		//Initialize check box
		allfieldcheckbox = new JCheckBox("Show all fields");
		allfieldcheckbox.setSelected(true);
		
		
		//Initialize label
		totalstudentlabel = new JLabel("Total Student");
		feespayedlabel = new JLabel("Fees Payed");
		feesduelabel = new JLabel("Fees due");
		totalfeeslabel = new JLabel("Total fees");
		
		
		//Add Item listener to check box
		allfieldcheckbox.addItemListener(this);
		
		
		//Add Action Listener to button
		searchbutton.addActionListener(this);
		addfeesbutton.addActionListener(this);
		addcoursebutton.addActionListener(this);
		
		
		//Add Key Listener to button
		searchbutton.addKeyListener(this);
		addfeesbutton.addKeyListener(this);
		addcoursebutton.addKeyListener(this);
	}
	
	
	private void makeGUI()
	{
		//Set Frames properties
		this.setTitle("Search And Edit Student");
		this.setSize(700, 500);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		
		//Add components to panel_1
		FlowLayout layout = new FlowLayout();
		layout.setAlignment(FlowLayout.LEFT);
		panel_1.setLayout(layout);
		panel_1.add(searchcombobox);
		panel_1.add(searchtextfield);
		panel_1.add(searchbutton);
		panel_1.add(allfieldcheckbox);
		panel_1.setBackground(Color.WHITE);
		
		//Add panel_1 to frame
		this.add(panel_1, BorderLayout.PAGE_START);
		
		
		//Add components to panel_2
		panel_2.add(totalstudentlabel);
		panel_2.setBackground(Color.WHITE);
		
		//Add panel_2 to frame
		this.add(panel_2, BorderLayout.PAGE_END);
		
		
		//Add components to panel_3
		panel_3.setLayout(new GridLayout(1,0));
		
		
		//Design panel_5
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		c.weightx = 1;
		c.weighty = 1;
		c.fill = GridBagConstraints.BOTH;
		
		panel_5.setLayout(gbl);
		
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 4;
		c.gridwidth = GridBagConstraints.REMAINDER;
		panel_5.add(panel_3, c);
		
		c.gridx = 0;
		c.gridy = 5;
		c.gridheight = 2;
		c.gridwidth = GridBagConstraints.REMAINDER;
		panel_5.add(panel_4, c);
		
		
		//Add components to panel_4
		panel_4.setLayout(new GridLayout(1, 2));
		panel_4.add(panel_6); //Lower Left
		panel_4.add(panel_7); //Lower Right
		
		
		//Design panel 6
		SpringLayout sl = new SpringLayout();
		panel_6.setLayout(sl);
		
		sl.putConstraint(SpringLayout.WEST, feespayedlabel, 2, SpringLayout.WEST, panel_6);
		sl.putConstraint(SpringLayout.NORTH, feespayedlabel, 2, SpringLayout.NORTH, panel_6);
		
		sl.putConstraint(SpringLayout.WEST, addfeestextfield, 2, SpringLayout.EAST, feespayedlabel);
		sl.putConstraint(SpringLayout.NORTH, addfeestextfield, 2, SpringLayout.NORTH, panel_6);
		
		sl.putConstraint(SpringLayout.WEST, addfeesbutton, 2, SpringLayout.EAST, addfeestextfield);
		sl.putConstraint(SpringLayout.NORTH, addfeesbutton, 2, SpringLayout.NORTH, panel_6);
		
		sl.putConstraint(SpringLayout.WEST, feesduelabel, 2, SpringLayout.WEST, panel_6);
		sl.putConstraint(SpringLayout.NORTH, feesduelabel, 2, SpringLayout.SOUTH, addfeesbutton);
		
		sl.putConstraint(SpringLayout.WEST, totalfeeslabel, 2, SpringLayout.WEST, panel_6);
		sl.putConstraint(SpringLayout.NORTH, totalfeeslabel, 2, SpringLayout.SOUTH, feesduelabel);
		
		sl.putConstraint(SpringLayout.WEST, coursecombobox, 2, SpringLayout.WEST, panel_6);
		sl.putConstraint(SpringLayout.NORTH, coursecombobox, 3, SpringLayout.SOUTH, totalfeeslabel);
		
		sl.putConstraint(SpringLayout.WEST, spinner, 2, SpringLayout.EAST, coursecombobox);
		sl.putConstraint(SpringLayout.NORTH, spinner, 3, SpringLayout.SOUTH, totalfeeslabel);
		
		sl.putConstraint(SpringLayout.WEST, addcoursebutton, 2, SpringLayout.WEST, panel_6);
		sl.putConstraint(SpringLayout.NORTH, addcoursebutton, 3, SpringLayout.SOUTH, coursecombobox);
		
		panel_6.add(feespayedlabel);
		panel_6.add(addfeestextfield);
		panel_6.add(addfeesbutton);
		panel_6.add(feesduelabel);
		panel_6.add(totalfeeslabel);
		panel_6.add(coursecombobox);
		panel_6.add(spinner);
		panel_6.add(addcoursebutton);
		
		
		//Set panel 7 layout
		panel_7.setLayout(new GridLayout(1,0));
		panel_7.setBackground(Color.WHITE);
		
		//Add panel_5 to frame
		this.add(panel_5);
		
		
		update();
		
		//Show frame
		this.validate();
		this.setVisible(true);
	}
	
	
	//Implementation of stateChanged
	public void itemStateChanged(ItemEvent e)
	{
		if(e.getSource() == allfieldcheckbox)
		{
			update();
		}
	}
	
	
	//Implementation of action performed
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == searchbutton)
		{
			update();
		}
		else if(e.getSource() == addfeesbutton)
		{
			addFees();
		}
		else if(e.getSource() == addcoursebutton)
		{
			addCourse();
		}
	}
	
	
	//Implementation of keyPressed
	public void keyPressed(KeyEvent e)
	{
		if(e.getSource() == searchbutton && e.getKeyCode() == KeyEvent.VK_ENTER)
		{
			update();
		}
		else if(e.getSource() == addfeesbutton && e.getKeyCode() == KeyEvent.VK_ENTER)
		{
			addFees();
		}
		else if(e.getSource() == addcoursebutton && e.getKeyCode() == KeyEvent.VK_ENTER)
		{
			addCourse();
		}
	}
	
	
	//Implementation of keyReleased
	public void keyReleased(KeyEvent e)
	{
		//Nothing to do
	}
	
	
	//Implementation of keyTyped
	public void keyTyped(KeyEvent e)
	{
		//Nothing to do
	}
	
	
	//Implementation of valueChanged
	public void valueChanged(ListSelectionEvent e)
	{
		if(e.getSource() == table.getSelectionModel())
		{
			ListSelectionModel ls = table.getSelectionModel();
			
			int index = ls.getMinSelectionIndex();
			long id = (long) table.getValueAt(index, 0);
			
			updateFeesData(id);
		}
		else
		{
			ListSelectionModel ls = feestable.getSelectionModel();
			
			int index = ls.getMinSelectionIndex();
			
			float feespayed = (float) feestable.getValueAt(index, 1);
			float totalfees = (float) feestable.getValueAt(index, 2);
			
			feespayedlabel.setText("Fees Payed: " + feespayed);
			totalfeeslabel.setText("Total Fees: " + totalfees);
			
			if(totalfees - feespayed > 0)
			{
				feesduelabel.setText("Fees Due: " + (totalfees - feespayed));
			}
			
			panel_6.revalidate();
		}
	}
	
	
	//Method to update Table and related components
	private void update()
	{
		Database db = new Database();
		String column[] = {"ID", "NAME", "GENDER", "GUARDIAN_ROLE", "GUARDIAN_NAME", "PRESENT_ADDRESS", "PRESENT_CITY", "PRESENT_PHONE"};
		
		try
		{
			//Update table
			if(searchcombobox.getSelectedIndex() == 0 && allfieldcheckbox.isSelected())
			{
				mytablemodel = new MyTableModel(db.getAllStudent(), db.getColumnNameFromStudent());
			}
			else if(searchcombobox.getSelectedIndex() == 0 && !allfieldcheckbox.isSelected())
			{
				mytablemodel = new MyTableModel(db.getSomeFieldsFromStudent(), column);
			}
			else if(searchcombobox.getSelectedIndex() == 1 && allfieldcheckbox.isSelected())
			{
				String query = "SELECT * FROM Student WHERE NAME = \'" + searchtextfield.getText().trim() + "\'";
				mytablemodel = new MyTableModel(db.getData(query), db.getColumnNameFromStudent());
			}
			else if(searchcombobox.getSelectedIndex() == 1 && !allfieldcheckbox.isSelected())
			{
				String query = "SELECT ID, NAME, GENDER, GUARDIAN_ROLE, GUARDIAN_NAME, PRESENT_ADDRESS, PRESENT_CITY, PRESENT_PHONE FROM Student WHERE NAME = \'" + searchtextfield.getText().trim() + "\'";
				mytablemodel = new MyTableModel(db.getData(query), column);
			}
			else if(searchcombobox.getSelectedIndex() == 2 && allfieldcheckbox.isSelected())
			{
				String query = "SELECT * FROM Student WHERE GUARDIAN_NAME = \'" + searchtextfield.getText().trim() + "\'";
				mytablemodel = new MyTableModel(db.getData(query), db.getColumnNameFromStudent());
			}
			else if(searchcombobox.getSelectedIndex() == 2 && !allfieldcheckbox.isSelected())
			{
				String query = "SELECT ID, NAME, GENDER, GUARDIAN_ROLE, GUARDIAN_NAME, PRESENT_ADDRESS, PRESENT_CITY, PRESENT_PHONE FROM Student WHERE GUARDIAN_NAME = \'" + searchtextfield.getText().trim() + "\'";
				mytablemodel = new MyTableModel(db.getData(query), column);
			}
			else if(searchcombobox.getSelectedIndex() == 3 && allfieldcheckbox.isSelected())
			{
				String query = "SELECT * FROM Student WHERE ID = ANY(SELECT SID FROM Fee WHERE CID = ANY(SELECT ID FROM CourseInfo WHERE NAME = \'" + searchtextfield.getText().trim() + "\'))";
				mytablemodel = new MyTableModel(db.getData(query), db.getColumnNameFromStudent());
			}
			else if(searchcombobox.getSelectedIndex() == 3 && !allfieldcheckbox.isSelected())
			{
				String query = "SELECT ID, NAME, GENDER, GUARDIAN_ROLE, GUARDIAN_NAME, PRESENT_ADDRESS, PRESENT_CITY, PRESENT_PHONE FROM Student WHERE ID = ANY(SELECT SID FROM Fee WHERE CID = ANY(SELECT ID FROM CourseInfo WHERE NAME = \'" + searchtextfield.getText().trim() + "\'))";
				mytablemodel = new MyTableModel(db.getData(query), column);
			}
			else if(searchcombobox.getSelectedIndex() == 4 && allfieldcheckbox.isSelected())
			{
				String query = "SELECT * FROM Student WHERE ID = ANY(SELECT SID FROM Fee WHERE TOTAL_FEES - FEES_PAYED >= 0)";
				mytablemodel = new MyTableModel(db.getData(query), db.getColumnNameFromStudent());
			}
			else
			{
				String query = "SELECT ID, NAME, GENDER, GUARDIAN_ROLE, GUARDIAN_NAME, PRESENT_ADDRESS, PRESENT_CITY, PRESENT_PHONE FROM Student WHERE ID = ANY(SELECT SID FROM Fee WHERE TOTAL_FEES - FEES_PAYED >= 0)";
				mytablemodel = new MyTableModel(db.getData(query), column);
			}
			
			
			table = new JTable(mytablemodel);
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			//Add list selection listener to table
			table.getSelectionModel().addListSelectionListener(this);
			
			TableColumn col = null;
			for(int i = 3; i < table.getColumnCount(); i++)
			{
				col = table.getColumnModel().getColumn(i);
				col.setPreferredWidth(200);
			}
			
			scrollpane = new JScrollPane(table);
			
			panel_3.removeAll();
			panel_3.add(scrollpane);
			
			//Update total student label
			int total = db.getTotalStudent();
			totalstudentlabel.setText("Total Student = " + total);
			
			
			//Clear search combo box
			searchtextfield.setText("");
			
			this.revalidate();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	
	//Fees update method
	private void updateFeesData(long id)
	{
		String columns[] = {"Course", "Fees Payed", "Total fees", "Installments"};
		try
		{
			Database db = new Database();
			
			panel_7.removeAll();
			
			feestablemodel = new MyTableModel(db.getFeeData(id), columns);
			
			feestable = new JTable(feestablemodel);
			feestable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			feestable.getSelectionModel().addListSelectionListener(this);
			
			feesscrollpane = new JScrollPane(feestable);
			panel_7.add(feesscrollpane);
			
			//change fees payed label
			feespayedlabel.setText("Fees Payed");
			feesduelabel.setText("Fees Due");
			totalfeeslabel.setText("Total Fees");
		
			panel_7.revalidate();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(this, e.getMessage(), null, JOptionPane.ERROR_MESSAGE);
		}
	}
	
	
	//Add fee method
	private void addFees()
	{
		try
		{
			float feesadded = Float.parseFloat(addfeestextfield.getText());
			
			int id = table.getSelectionModel().getMinSelectionIndex();
			long studentid = (long)table.getValueAt(id, 0);
			
			int index = feestable.getSelectionModel().getMinSelectionIndex();
			String coursename = (String)feestable.getValueAt(index, 0);
			
			Database db = new Database();
			db.addFees(feesadded, studentid, coursename);
			
			updateFeesData(studentid);
			
			addfeestextfield.setText("");
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(this, e.getMessage(), null, JOptionPane.ERROR_MESSAGE);
		}
	}
	
	
	//Add course method
	public void addCourse()
	{
		try
		{
			Database db = new Database();
			
			String coursename = (String)coursecombobox.getSelectedItem();
			if(coursecombobox.getSelectedIndex() == 0)
			{
				throw new Exception("No course selected");
			}
			
			float fees = db.getCoursefees(coursename);
			float totalfees = fees + (ims.main.Settings.getInstallment() * (int)spinner.getValue());
			
			long id = (long) table.getValueAt((int)table.getSelectionModel().getMinSelectionIndex(), 0);
			
			db.addCourseToCurrentStudent(id, totalfees, (int)spinner.getValue(), coursename);
			
			updateFeesData(id);
			courseReset();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(this, e.getMessage(), null, JOptionPane.ERROR_MESSAGE);
		}
	}
	
	
	//Reset combobox and spinner
	private void courseReset()
	{
		coursecombobox.setSelectedIndex(0);
		spinner.setValue(0);
	}
}
