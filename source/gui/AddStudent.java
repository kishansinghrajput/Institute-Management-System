package ims.gui;

import javax.swing.*;
import javax.swing.event.*;
import ims.main.*;
import java.awt.*;
import java.awt.event.*;

/*
 *Author: Pratyush Sharma
 *Date: 9 March 2014 Sunday
 *This is the code for add student window for the application. 
 */

public class AddStudent extends JFrame implements ActionListener, KeyListener, ItemListener, ChangeListener
{
	private static final long serialVersionUID = 1L;
	private JLabel label[];
	private JTextField textfield[];
	private JTextArea textarea[];
	private JScrollPane scrollpane[];
	private JFormattedTextField ftextfield[];
	@SuppressWarnings("rawtypes")
	private JComboBox combobox[];
	private JRadioButton radiobutton[];
	private ButtonGroup buttongroup[];
	private JCheckBox checkbox;
	private JSpinner spinner;
	private SpinnerModel spinnermodel;
	private JPanel panel;
	private JButton addbutton, cancelbutton;
	
	private float fees;
	private float totalfees;
	
	public AddStudent()
	{
		initializeComponent();
		makeGUI();	
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initializeComponent()
	{
		//Initialize labels
		label = new JLabel[21];
		label[0] = new JLabel("Name");
		label[1] = new JLabel("Date of Birth");
		label[2] = new JLabel("Name of");
		label[3] = new JLabel("Occupation");
		label[4] = new JLabel("Present Address");
		label[5] = new JLabel("City");
		label[6] = new JLabel("State");
		label[7] = new JLabel("Country");
		label[8] = new JLabel("Phone");
		label[9] = new JLabel("Permanent Address");
		label[10] = new JLabel("City");
		label[11] = new JLabel("State");
		label[12] = new JLabel("Country");
		label[13] = new JLabel("Phone");
		label[14] = new JLabel("Category");
		label[15] = new JLabel("Educational Qualification");
		label[16] = new JLabel("Course");
		label[17] = new JLabel("Fees Payed");
		label[18] = new JLabel("Date of Joining");
		label[19] = new JLabel();
		label[20] = new JLabel();
		
		//Initialize text fields
		textfield = new JTextField[10];
		for(int i = 0; i < 10; i++)
		{
			textfield[i] = new JTextField(22);	
		}
		
		//Initialize text area and scroll pane
		textarea = new JTextArea[2];
		textarea[0] = new JTextArea(); //For Present address
		textarea[0].setWrapStyleWord(true);
		textarea[0].setLineWrap(true);
		textarea[1] = new JTextArea(); //For Permanent Address
		textarea[1].setWrapStyleWord(true);
		textarea[1].setLineWrap(true);
		scrollpane = new JScrollPane[2];
		scrollpane[0] = new JScrollPane(textarea[0]); //Add text area to scroll pane
		scrollpane[1] = new JScrollPane(textarea[1]); //Add text area to scroll pane
		
		//Initialize formatted text field
		ftextfield = new JFormattedTextField[3];
		for(int i = 0; i < 3; i++)
		{
			ftextfield[i] = new JFormattedTextField();
			ftextfield[i].setColumns(13);	
		}
		
		//Initialize combo boxes
		combobox = new JComboBox[7];
		for(int i = 0; i < 7; i++)
		{
			combobox[i] = new JComboBox();	
		}
		
		String months[] = {"Month", "January", "February", "March", "April", "May",
							"June", "July", "August", "September", "October", "November", "December"};
		for(String x: months)
		{
			combobox[1].addItem(x);
			combobox[5].addItem(x);	
		}
		
		combobox[0].addItem("Date");
		combobox[4].addItem("Date");
		combobox[2].addItem("Year");
		combobox[6].addItem("Year");
		
		for(int i = 1; i <= 31; i++) //Add Dates
		{
			combobox[0].addItem(i);
			combobox[4].addItem(i);	
		}
		
		for(int i = 1930; i <= 2200; i++) //Add Years
		{
			combobox[2].addItem(i);
			combobox[6].addItem(i);	
		}
		
		combobox[3].addItem("Select Course");
		Database db = new Database();
		
		String[] s;
		if((s = db.getCourses()) != null)
		{
			for(String x: s)
			{
				if(x == null)
					break;
			
				combobox[3].addItem(x);
			}
		}
		
		//Initialize radio button and button group
		radiobutton = new JRadioButton[9];
		radiobutton[0] = new JRadioButton("Father");
		radiobutton[1] = new JRadioButton("Husband");
		radiobutton[2] = new JRadioButton("General");
		radiobutton[3] = new JRadioButton("OBC");
		radiobutton[4] = new JRadioButton("SC");
		radiobutton[5] = new JRadioButton("ST");
		radiobutton[6] = new JRadioButton("M");
		radiobutton[7] = new JRadioButton("F");
		radiobutton[8] = new JRadioButton("O");
		buttongroup = new ButtonGroup[3];
		buttongroup[0] = new ButtonGroup();
		buttongroup[1] = new ButtonGroup();
		buttongroup[2] = new ButtonGroup();
		buttongroup[0].add(radiobutton[0]);
		buttongroup[0].add(radiobutton[1]);
		buttongroup[1].add(radiobutton[2]);
		buttongroup[1].add(radiobutton[3]);
		buttongroup[1].add(radiobutton[4]);
		buttongroup[1].add(radiobutton[5]);
		buttongroup[2].add(radiobutton[6]);
		buttongroup[2].add(radiobutton[7]);
		buttongroup[2].add(radiobutton[8]);
		
		//Initialize check box
		checkbox = new JCheckBox("Installment"); //Install Check box
		
		//Initialize spinner model and spinner
		spinnermodel = new SpinnerNumberModel(2, 2, 10, 1);
		spinner = new JSpinner(spinnermodel);
		spinner.setEnabled(false);
		
		//Initialize panel
		panel = new JPanel();
		
		//Initialize button
		addbutton = new JButton("Add");
		cancelbutton = new JButton("Cancel");
		
		//Add Action Listener to buttons and Combo box
		addbutton.addActionListener(this);
		cancelbutton.addActionListener(this);
		combobox[3].addActionListener(this);
		
		//Add Key Listener to buttons
		addbutton.addKeyListener(this);
		cancelbutton.addKeyListener(this);
		
		//Add Item Listener to Check box
		checkbox.addItemListener(this);
		
		//Add change listener to spinner
		spinner.addChangeListener(this);
	}
	
	private void makeGUI()
	{
		int x = 3;
		int y = 5;
		
		//Set Frame's properties
		this.setTitle("Add Student");
		this.setResizable(false);
		this.setSize(500, 550);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		//Spring Layout and container
		SpringLayout layout = new SpringLayout();
		Container pane = this.getContentPane();
		
		//Set panel layout
		panel.setLayout(layout);
		panel.setBackground(Color.WHITE);
		
		//Put constraints on components
		
		// 1 row
		layout.putConstraint(SpringLayout.NORTH, label[0], y, SpringLayout.NORTH, pane);
		layout.putConstraint(SpringLayout.WEST, label[0], x, SpringLayout.WEST, pane);
		
		layout.putConstraint(SpringLayout.WEST, textfield[0], x, SpringLayout.EAST, label[0]);
		layout.putConstraint(SpringLayout.NORTH, textfield[0], y, SpringLayout.NORTH, pane);
		
		layout.putConstraint(SpringLayout.WEST, radiobutton[6], x, SpringLayout.EAST, textfield[0]);
		layout.putConstraint(SpringLayout.NORTH, radiobutton[6], y, SpringLayout.NORTH, pane);
		radiobutton[6].setSelected(true);
		
		layout.putConstraint(SpringLayout.WEST, radiobutton[7], x, SpringLayout.EAST, radiobutton[6]);
		layout.putConstraint(SpringLayout.NORTH, radiobutton[7], y, SpringLayout.NORTH, pane);
		
		layout.putConstraint(SpringLayout.WEST, radiobutton[8], x, SpringLayout.EAST, radiobutton[7]);
		layout.putConstraint(SpringLayout.NORTH, radiobutton[8], y, SpringLayout.NORTH, pane);
		
		// 2 row
		layout.putConstraint(SpringLayout.WEST, label[1], x, SpringLayout.WEST, pane);
		layout.putConstraint(SpringLayout.NORTH, label[1], y, SpringLayout.SOUTH, radiobutton[6]);
		
		layout.putConstraint(SpringLayout.WEST, combobox[0], x, SpringLayout.EAST, label[1]);
		layout.putConstraint(SpringLayout.NORTH, combobox[0], y, SpringLayout.SOUTH, radiobutton[6]);
		
		layout.putConstraint(SpringLayout.WEST, combobox[1], x, SpringLayout.EAST, combobox[0]);
		layout.putConstraint(SpringLayout.NORTH, combobox[1], y, SpringLayout.SOUTH, radiobutton[6]);
		
		layout.putConstraint(SpringLayout.WEST, combobox[2], x, SpringLayout.EAST, combobox[1]);
		layout.putConstraint(SpringLayout.NORTH, combobox[2], y, SpringLayout.SOUTH, radiobutton[6]);
		
		// 3 row
		layout.putConstraint(SpringLayout.WEST, label[2], x, SpringLayout.WEST, pane);
		layout.putConstraint(SpringLayout.NORTH, label[2], y, SpringLayout.SOUTH, combobox[0]);
		
		layout.putConstraint(SpringLayout.WEST, radiobutton[0], x, SpringLayout.EAST, label[2]);
		layout.putConstraint(SpringLayout.NORTH, radiobutton[0], y, SpringLayout.SOUTH, combobox[0]);
		radiobutton[0].setSelected(true);
		
		layout.putConstraint(SpringLayout.WEST, radiobutton[1], x, SpringLayout.EAST, radiobutton[0]);
		layout.putConstraint(SpringLayout.NORTH, radiobutton[1], y, SpringLayout.SOUTH, combobox[0]);
		
		layout.putConstraint(SpringLayout.WEST, textfield[1], x, SpringLayout.EAST, radiobutton[1]);
		layout.putConstraint(SpringLayout.NORTH, textfield[1], y, SpringLayout.SOUTH, combobox[0]);
		
		// 4 row
		layout.putConstraint(SpringLayout.WEST, label[3], x, SpringLayout.WEST, pane);
		layout.putConstraint(SpringLayout.NORTH, label[3], y, SpringLayout.SOUTH, radiobutton[0]);
		
		layout.putConstraint(SpringLayout.WEST, textfield[2], x, SpringLayout.EAST, label[3]);
		layout.putConstraint(SpringLayout.NORTH, textfield[2], y, SpringLayout.SOUTH, radiobutton[0]);
		
		// 5 row
		layout.putConstraint(SpringLayout.WEST, label[4], x, SpringLayout.WEST, pane);
		layout.putConstraint(SpringLayout.NORTH, label[4], y, SpringLayout.SOUTH, textfield[2]);
		
		scrollpane[0].setPreferredSize(new Dimension(300, 50));
		layout.putConstraint(SpringLayout.WEST, scrollpane[0], x, SpringLayout.EAST, label[4]);
		layout.putConstraint(SpringLayout.NORTH, scrollpane[0], y, SpringLayout.SOUTH, textfield[2]);
		
		// 6 row
		layout.putConstraint(SpringLayout.WEST, label[5], x, SpringLayout.WEST, pane);
		layout.putConstraint(SpringLayout.NORTH, label[5], y, SpringLayout.SOUTH, scrollpane[0]);
		
		textfield[3].setColumns(8);
		layout.putConstraint(SpringLayout.WEST, textfield[3], x, SpringLayout.EAST, label[5]);
		layout.putConstraint(SpringLayout.NORTH, textfield[3], y, SpringLayout.SOUTH, scrollpane[0]);
		
		layout.putConstraint(SpringLayout.WEST, label[6], x, SpringLayout.EAST, textfield[3]);
		layout.putConstraint(SpringLayout.NORTH, label[6], y, SpringLayout.SOUTH, scrollpane[0]);
		
		textfield[4].setColumns(8);
		layout.putConstraint(SpringLayout.WEST, textfield[4], x, SpringLayout.EAST, label[6]);
		layout.putConstraint(SpringLayout.NORTH, textfield[4], y, SpringLayout.SOUTH, scrollpane[0]);
		
		layout.putConstraint(SpringLayout.WEST, label[7], x, SpringLayout.EAST, textfield[4]);
		layout.putConstraint(SpringLayout.NORTH, label[7], y, SpringLayout.SOUTH, scrollpane[0]);
		
		textfield[5].setColumns(8);
		layout.putConstraint(SpringLayout.WEST, textfield[5], x, SpringLayout.EAST, label[7]);
		layout.putConstraint(SpringLayout.NORTH, textfield[5], y, SpringLayout.SOUTH, scrollpane[0]);
		
		// 7 row
		layout.putConstraint(SpringLayout.WEST, label[8], x, SpringLayout.EAST, pane);
		layout.putConstraint(SpringLayout.NORTH, label[8], y, SpringLayout.SOUTH, textfield[5]);
		
		layout.putConstraint(SpringLayout.WEST, ftextfield[0], x, SpringLayout.EAST, label[8]);
		layout.putConstraint(SpringLayout.NORTH, ftextfield[0], y, SpringLayout.SOUTH, textfield[5]);
		
		// 8 row
		layout.putConstraint(SpringLayout.WEST, label[9], x, SpringLayout.WEST, pane);
		layout.putConstraint(SpringLayout.NORTH, label[9], y, SpringLayout.SOUTH, ftextfield[0]);
		
		scrollpane[1].setPreferredSize(new Dimension(300, 50));
		layout.putConstraint(SpringLayout.WEST, scrollpane[1], x, SpringLayout.EAST, label[9]);
		layout.putConstraint(SpringLayout.NORTH, scrollpane[1], y, SpringLayout.SOUTH, ftextfield[0]);
		
		// 9 row
		layout.putConstraint(SpringLayout.WEST, label[10], x, SpringLayout.WEST, pane);
		layout.putConstraint(SpringLayout.NORTH, label[10], y, SpringLayout.SOUTH, scrollpane[1]);
		
		textfield[6].setColumns(8);
		layout.putConstraint(SpringLayout.WEST, textfield[6], x, SpringLayout.EAST, label[10]);
		layout.putConstraint(SpringLayout.NORTH, textfield[6], y, SpringLayout.SOUTH, scrollpane[1]);
		
		layout.putConstraint(SpringLayout.WEST, label[11], x, SpringLayout.EAST, textfield[6]);
		layout.putConstraint(SpringLayout.NORTH, label[11], y, SpringLayout.SOUTH, scrollpane[1]);
		
		textfield[7].setColumns(8);
		layout.putConstraint(SpringLayout.WEST, textfield[7], x, SpringLayout.EAST, label[11]);
		layout.putConstraint(SpringLayout.NORTH, textfield[7], y, SpringLayout.SOUTH, scrollpane[1]);
		
		layout.putConstraint(SpringLayout.WEST, label[12], x, SpringLayout.EAST, textfield[7]);
		layout.putConstraint(SpringLayout.NORTH, label[12], y, SpringLayout.SOUTH, scrollpane[1]);
		
		textfield[8].setColumns(8);
		layout.putConstraint(SpringLayout.WEST, textfield[8], x, SpringLayout.EAST, label[12]);
		layout.putConstraint(SpringLayout.NORTH, textfield[8], y, SpringLayout.SOUTH, scrollpane[1]);
		
		// 10 row
		layout.putConstraint(SpringLayout.WEST, label[13], x, SpringLayout.WEST, pane);
		layout.putConstraint(SpringLayout.NORTH, label[13], y, SpringLayout.SOUTH, textfield[8]);
		
		layout.putConstraint(SpringLayout.WEST, ftextfield[1], x, SpringLayout.EAST, label[13]);
		layout.putConstraint(SpringLayout.NORTH, ftextfield[1], y, SpringLayout.SOUTH, textfield[8]);
		
		// 11 row
		layout.putConstraint(SpringLayout.WEST, label[14], x, SpringLayout.WEST, pane);
		layout.putConstraint(SpringLayout.NORTH, label[14], y, SpringLayout.SOUTH, ftextfield[1]);
		
		layout.putConstraint(SpringLayout.WEST, radiobutton[2], x, SpringLayout.EAST, label[14]);
		layout.putConstraint(SpringLayout.NORTH, radiobutton[2], y, SpringLayout.SOUTH, ftextfield[1]);
		radiobutton[2].setSelected(true);
		
		layout.putConstraint(SpringLayout.WEST, radiobutton[3], x, SpringLayout.EAST, radiobutton[2]);
		layout.putConstraint(SpringLayout.NORTH, radiobutton[3], y, SpringLayout.SOUTH, ftextfield[1]);
		
		layout.putConstraint(SpringLayout.WEST, radiobutton[4], x, SpringLayout.EAST, radiobutton[3]);
		layout.putConstraint(SpringLayout.NORTH, radiobutton[4], y, SpringLayout.SOUTH, ftextfield[1]);
		
		layout.putConstraint(SpringLayout.WEST, radiobutton[5], x, SpringLayout.EAST, radiobutton[4]);
		layout.putConstraint(SpringLayout.NORTH, radiobutton[5], y, SpringLayout.SOUTH, ftextfield[1]);
		
		// 12 row
		layout.putConstraint(SpringLayout.WEST, label[15], x, SpringLayout.WEST, pane);
		layout.putConstraint(SpringLayout.NORTH, label[15], y, SpringLayout.SOUTH, radiobutton[4]);
		
		layout.putConstraint(SpringLayout.WEST, textfield[9], x, SpringLayout.EAST, label[15]);
		layout.putConstraint(SpringLayout.NORTH, textfield[9], y, SpringLayout.SOUTH, radiobutton[4]);
		
		// 13 row
		layout.putConstraint(SpringLayout.WEST, label[16], x, SpringLayout.WEST, pane);
		layout.putConstraint(SpringLayout.NORTH, label[16], y, SpringLayout.SOUTH, textfield[9]);
		
		layout.putConstraint(SpringLayout.WEST, combobox[3], x, SpringLayout.EAST, label[16]);
		layout.putConstraint(SpringLayout.NORTH, combobox[3], y, SpringLayout.SOUTH, textfield[9]);
		
		layout.putConstraint(SpringLayout.WEST, label[19], x, SpringLayout.EAST, combobox[3]);
		layout.putConstraint(SpringLayout.NORTH, label[19], y, SpringLayout.SOUTH, textfield[9]);
		
		// 14 row
		layout.putConstraint(SpringLayout.WEST, label[17], x, SpringLayout.WEST, pane);
		layout.putConstraint(SpringLayout.NORTH, label[17], y, SpringLayout.SOUTH, combobox[3]);
		
		ftextfield[2].setColumns(6);
		layout.putConstraint(SpringLayout.WEST, ftextfield[2], x, SpringLayout.EAST, label[17]);
		layout.putConstraint(SpringLayout.NORTH, ftextfield[2], y, SpringLayout.SOUTH, combobox[3]);
		
		layout.putConstraint(SpringLayout.WEST, checkbox, x, SpringLayout.EAST, ftextfield[2]);
		layout.putConstraint(SpringLayout.NORTH, checkbox, y, SpringLayout.SOUTH, combobox[3]);
		
		layout.putConstraint(SpringLayout.WEST, spinner, x, SpringLayout.EAST, checkbox);
		layout.putConstraint(SpringLayout.NORTH, spinner, y, SpringLayout.SOUTH, combobox[3]);
		
		layout.putConstraint(SpringLayout.WEST, label[20], x, SpringLayout.EAST, spinner);
		layout.putConstraint(SpringLayout.NORTH, label[20], y, SpringLayout.SOUTH, combobox[3]);
		
		// 15 row
		layout.putConstraint(SpringLayout.WEST, label[18], x, SpringLayout.WEST, pane);
		layout.putConstraint(SpringLayout.NORTH, label[18], y, SpringLayout.SOUTH, checkbox);
		
		layout.putConstraint(SpringLayout.WEST, combobox[4], x, SpringLayout.EAST, label[18]);
		layout.putConstraint(SpringLayout.NORTH, combobox[4], y, SpringLayout.SOUTH, checkbox);
		
		layout.putConstraint(SpringLayout.WEST, combobox[5], x, SpringLayout.EAST, combobox[4]);
		layout.putConstraint(SpringLayout.NORTH, combobox[5], y, SpringLayout.SOUTH, checkbox);
		
		layout.putConstraint(SpringLayout.WEST, combobox[6], x, SpringLayout.EAST, combobox[5]);
		layout.putConstraint(SpringLayout.NORTH, combobox[6], y, SpringLayout.SOUTH, checkbox);
		
		// 16 row
		layout.putConstraint(SpringLayout.WEST, addbutton, x, SpringLayout.EAST, pane);
		layout.putConstraint(SpringLayout.NORTH, addbutton, y + 10, SpringLayout.SOUTH, combobox[5]);
		
		layout.putConstraint(SpringLayout.WEST, cancelbutton, x, SpringLayout.EAST, addbutton);
		layout.putConstraint(SpringLayout.NORTH, cancelbutton, y + 10, SpringLayout.SOUTH, combobox[5]);
		
		
		
		//Add Labels to panel
		for(int i = 0; i < 21; i++)
		{
			panel.add(label[i]);	
		}
		
		//Add text field to panel
		for(int i = 0; i < 10; i++)
		{
			panel.add(textfield[i]);	
		}
		
		//Add formatted text field to panel
		for(int i = 0; i < 3; i++)
		{
			panel.add(ftextfield[i]);	
		}
		
		//Add radio buttons to panel
		for(int i = 0; i < 9; i++)
		{
			panel.add(radiobutton[i]);	
		}
		
		//Add combo box to panel
		for(int i = 0; i < 7; i++)
		{
			panel.add(combobox[i]);
		}
		
		//Add scroll pane to panel
		panel.add(scrollpane[0]);
		panel.add(scrollpane[1]);
		
		//Add check box to panel
		panel.add(checkbox);
		
		//Add Spinner to panel
		panel.add(spinner);
		
		//Add buttons to panel
		panel.add(addbutton);
		panel.add(cancelbutton);
		
		//Add panel to frame
		this.add(panel);
		
		//Validate panel and frame
		panel.validate();
		this.validate();
		
		//Show frame
		this.setVisible(true);
	}
	
	@Override
	public void stateChanged(ChangeEvent e)
	{
		if(e.getSource() == spinner)
		{
			updateTotalFeesLabel();	
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == addbutton) //Add button
		{
			if(validateInformation())
			{		
				Student student = new Student(this);
				
				if(student.addStudentToDatabase())
				{		
					JOptionPane.showMessageDialog(this, "Student Successfully added", "Add student", JOptionPane.INFORMATION_MESSAGE);
					this.dispose();	
				}
				else
				{
					JOptionPane.showMessageDialog(this, "Error in adding student to database", "Add Student", JOptionPane.ERROR_MESSAGE);	
				}
			}
		}
		else if(e.getSource() == combobox[3]) //Combo box
		{	
			if(combobox[3].getSelectedIndex() == 0)
			{		
				label[19].setText("Select a Course");
				updateTotalFeesLabel();	
			}
			else
			{
				Database db = new Database();
				
				fees = db.getCoursefees((String)combobox[3].getSelectedItem());
				label[19].setText("Base fee: " + fees);
				updateTotalFeesLabel();	
			}
		}
		else //Cancel button
		{
			this.dispose();	
		}
		
	}
	
	public void itemStateChanged(ItemEvent e)
	{
		if(e.getSource() == checkbox)
		{	
			if(checkbox.isSelected())
			{		
				spinner.setEnabled(true);	
			}
			else
			{	
				spinner.setEnabled(false);	
			}
			
			updateTotalFeesLabel();
		}
	}
	
	public void keyPressed(KeyEvent e)
	{
		if(e.getSource() == addbutton) //Add button
		{		
			if(e.getKeyCode() == KeyEvent.VK_ENTER)
			{		
				if(validateInformation())
				{
					Student student = new Student(this);
					
					if(student.addStudentToDatabase())
					{		
						JOptionPane.showMessageDialog(this, "Student Successfully added", "Add student", JOptionPane.INFORMATION_MESSAGE);
						this.dispose();	
					}
					else
					{
						JOptionPane.showMessageDialog(this, "Error in adding student to database", "Add Student", JOptionPane.ERROR_MESSAGE);
					}					
				}
			}
		}
		else //Cancel button
		{
			if(e.getKeyCode() == KeyEvent.VK_ENTER)
			{
				this.dispose();	
			}
		}
	}
	
	public void keyTyped(KeyEvent e)
	{
		//Nothing to do
	}
	
	public void keyReleased(KeyEvent e)
	{
		//Nothing to do
	}
	
	//Validation Function
	private boolean validateInformation()
	{
		//Validation variables
		boolean textfieldValid = true;
		boolean formattedtextfieldValid = true;
		boolean textareaValid = true;
		boolean comboboxValid = true;
		
		//validate text fields
		for(int i = 0; i < 9; i++)
		{
			if(textfield[i].getText() == "") //If empty
			{
				textfieldValid = false;
				break;
			}
		}
		
		//Validate formatted Text field
		for(int i = 0; i < 3; i++)
		{
			if(ftextfield[i].getText() == "")
			{
				formattedtextfieldValid = false;
				break;
			}
		}
		
		try
		{
			Float.parseFloat(ftextfield[2].getText());
		}
		catch(Exception e)
		{
			formattedtextfieldValid = false;
		}
		
		//Validate text area
		for(int i = 0; i < 2; i++)
		{		
			if(textarea[i].getText() == "") //If empty
			{
				textareaValid = false;
				break;		
			}
		}
		
		//Validate combo boxes
		for(int i = 0; i < 7; i++)
		{
			if(combobox[i].getSelectedIndex() == 0) //Check if nothing has been selected
			{		
				comboboxValid = false;
				break;	
			}
		}
		
		//Check for dates in combo box
		if(comboboxValid)
		{
			//Check for Date of Birth
			
			//Check for leap year
			if((int)combobox[2].getSelectedItem() % 4 == 0)
			{		
				//Check for date in February
				if(combobox[0].getSelectedIndex() > 29 && combobox[2].getSelectedIndex() == 2)
				{		
					comboboxValid = false;	
				}
			}
			else
			{
				//Check for date in February
				if(combobox[0].getSelectedIndex() > 28 && combobox[2].getSelectedIndex() == 2)
				{		
					comboboxValid = false;	
				}
			}
			
			//Check for other dates
			if(combobox[0].getSelectedIndex() > 30)
			{
				switch(combobox[1].getSelectedIndex())
				{
				
				case 4: //April
					
					comboboxValid = false;
					break;
				
				case 6: //June
					
					comboboxValid = false;
					break;
					
				case 9: //September
					
					comboboxValid = false;
					break;
					
				case 11: //November
					
					comboboxValid = false;
					break;		
				}
			}
			
			//Check for Date of joining
			
			//Check for leap year
			if((int)combobox[6].getSelectedItem() % 4 == 0)
			{
				//Check for date in February
				if(combobox[4].getSelectedIndex() > 29 && combobox[5].getSelectedIndex() == 2)
				{		
					comboboxValid = false;	
				}
			}
			else
			{
				//Check for date in February
				if(combobox[4].getSelectedIndex() > 28 && combobox[5].getSelectedIndex() == 2)
				{		
					comboboxValid = false;	
				}
			}
			
			//Check for other dates
			if(combobox[4].getSelectedIndex() > 30)
			{
				switch(combobox[5].getSelectedIndex())
				{
				
				case 4: //April
					
					comboboxValid = false;
					break;
				
				case 6: //June
					
					comboboxValid = false;
					break;
					
				case 9: //September
					
					comboboxValid = false;
					break;
					
				case 11: //November
					
					comboboxValid = false;
					break;		
				} //End of switch case
				
			} //End of Check for other dates
			
		} //End of Check for dates in Combo box
		
		
		//Returning procedures
		if(!textfieldValid) //Check for empty fields
		{
			JOptionPane.showMessageDialog(this, "Something has been left Blank", "Error", JOptionPane.ERROR_MESSAGE);
			return false;	
		}
		else if(!textareaValid) //Again check for empty fields
		{
			JOptionPane.showMessageDialog(this, "Something has been left Blank", "Error", JOptionPane.ERROR_MESSAGE);
			return false;	
		}
		else if(!formattedtextfieldValid) //Check for strings in formatted text fields
		{
			JOptionPane.showMessageDialog(this, "Check Phone Entries(Only numbers allowed) and fees", "Error", JOptionPane.ERROR_MESSAGE);
			return false;	
		}
		else if(!comboboxValid) //Check for wrong date selection
		{
			JOptionPane.showMessageDialog(this, "Error in Date of Birth or Date of Joining or Course Selection", "Error", JOptionPane.ERROR_MESSAGE);
			return false;	
		}
		else //Everything is fine
		{
			return true;	
		} //End of Returning procedures
		
	}
	
	//Method for getting name
	public String getName()
	{
		return textfield[0].getText().trim();	
	}
	
	//Method for getting gender
	public String getGender()
	{
		if(radiobutton[6].isSelected())
		{		
			return "Male";	
		}
		else if(radiobutton[7].isSelected())
		{
			return "Female";	
		}
		else
		{
			return "Other";
		}
	}
	
	//Method for getting guardian role
	public String getGuardianRole()
	{
		if(radiobutton[0].isSelected())
		{	
			return "Father";	
		}
		else
		{
			return "Husband";	
		}
	}
	
	//Method for getting day of birth
	public int getDayOfBirth()
	{
		return combobox[0].getSelectedIndex();
	}
	
	//Method for getting month of birth
	public int getMonthOfBirth()
	{	
		return combobox[1].getSelectedIndex();	
	}
	
	//Method for getting year of birth
	public int getYearOfBirth()
	{
		return (int)combobox[2].getSelectedItem();	
	}
	
	//Method for getting guardian name
	public String getGuardianName()
	{
		return textfield[1].getText().trim();	
	}
	
	//Method for getting guardian occupation
	public String getGuardianOccupation()
	{
		return textfield[2].getText().trim();	
	}
	
	//Method for getting present address
	public String getPresentAddress()
	{
		return textarea[0].getText().replace('\n', ' ').replace('\'', ' ').trim();
	}
	
	//Method for getting present city
	public String getPresentCity()
	{
		return textfield[3].getText().trim();	
	}
	
	//Method for getting present state
	public String getPresentState()
	{
		return textfield[4].getText().trim();	
	}
	
	//Method for getting present country
	public String getPresentCountry()
	{	
		return textfield[5].getText().trim();	
	}
	
	//Method for getting present phone
	public String getPresentPhone()
	{
		return ftextfield[0].getText();	
	}
	
	//Method for getting permanent address
	public String getPermanentAddress()
	{
		return textarea[1].getText().replace('\n', ' ').replace('\'', ' ' ).trim();	
	}
	
	//Method for getting permanent city
	public String getPermanentCity()
	{
		return textfield[6].getText().trim();	
	}
	
	//Method for getting permanent state
	public String getPermanentState()
	{
		return textfield[7].getText().trim();	
	}
	
	//Method for getting permanent country
	public String getPermanentCountry()
	{
		return textfield[8].getText().trim();	
	}
	
	//Method for getting permanent phone
	public String getPermanentPhone()
	{
		return ftextfield[1].getText();	
	}
	
	//Method for getting category
	public String getCategory()
	{
		if(radiobutton[2].isSelected())
		{
			return "General";	
		}
		else if(radiobutton[3].isSelected())
		{
			return "OBC";	
		}
		else if(radiobutton[4].isSelected())
		{
			return "SC";	
		}
		else
		{
			return "ST";	
		}
	}
	
	//Method for getting qualification
	public String getQualification()
	{
		return textfield[9].getText().trim();	
	}
	
	//Method for getting Course
	public String getCourse()
	{
		return (String)combobox[3].getSelectedItem();	
	}
	
	//Method for getting fees
	public double getFees()
	{
		return Double.parseDouble(ftextfield[2].getText());	
	}
	
	//Method for getting number of installments
	public int getNumberOfInstallments()
	{
		if(spinner.isEnabled())
		{		
			return (int)spinner.getValue();	
		}
		else
		{
			return 0;	
		}
	}
	
	//Method for getting day of joining
	public int getDayOfJoining()
	{
		return combobox[4].getSelectedIndex();	
	}
	
	//Method for getting month of joining
	public int getMonthOfJoining()
	{
		return combobox[5].getSelectedIndex();	
	}
	
	//Method for getting year of joining
	public int getYearOfJoining()
	{
		return (int)combobox[6].getSelectedItem();	
	}
	
	//Update Total fees label
	private void updateTotalFeesLabel()
	{
		if(spinner.isEnabled())
		{		
			totalfees = fees + (ims.main.Settings.getInstallment() * (int)spinner.getValue());	
		}
		else if(combobox[3].getSelectedIndex() == 0)
		{			
			label[20].setText("");
			return;	
		}
		else
		{
			totalfees = fees;	
		}
		
		label[20].setText("Total fees: " + totalfees);
	}
	
	//Get total fees
	public float getTotalFees()
	{
		return totalfees;
	}
}
