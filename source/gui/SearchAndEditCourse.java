package ims.gui;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;


import ims.main.*;

public class SearchAndEditCourse extends JFrame implements ListSelectionListener, ActionListener, KeyListener, DocumentListener
{
	private static final long serialVersionUID = 1L;
	private JPanel panel, panel_1, panel_2, panel_3;
	private JLabel coursenamelabel, feeslabel;
	private JButton updatebutton, deletebutton, cancelbutton;
	private JTextField coursetextfield, feestextfield;
	private JTable table;
	private MyTableModel mytablemodel;
	
	private boolean iscoursenameupdated;
	private boolean iscoursefeesupdated;
	
	private String coursename;
	private float coursefees;

	
	public SearchAndEditCourse()
	{
		initializeComponent();
		makeGUI();
	}
	
	
	//Method to initialize components
	private void initializeComponent()
	{
		//Initialize panels
		panel = new JPanel();
		panel_1 = new JPanel();
		panel_2 = new JPanel();
		panel_3 = new JPanel();
		
		
		//Initialize labels
		coursenamelabel = new JLabel("Course Name:");
		feeslabel = new JLabel("Course Fees:");
		
		
		//Initialize buttons
		updatebutton = new JButton("Update");
		updatebutton.setEnabled(false);
		deletebutton = new JButton("Delete");
		cancelbutton = new JButton("Cancel");
		
		
		//Initialize text fields
		coursetextfield = new JTextField(10);
		feestextfield = new JTextField(10);
		
		
		//Add document listener to text field
		coursetextfield.getDocument().addDocumentListener(this);
		feestextfield.getDocument().addDocumentListener(this);
		
		
		//Add Action Listener to buttons
		updatebutton.addActionListener(this);
		deletebutton.addActionListener(this);
		cancelbutton.addActionListener(this);
		
		//Add Key Listener to button
		updatebutton.addKeyListener(this);
		deletebutton.addKeyListener(this);
		cancelbutton.addKeyListener(this);
	}
	
	
	//Method to make and show GUI
	private void makeGUI()
	{
		//Set frames properties
		this.setTitle("Search and Edit Course");
		this.setLocationRelativeTo(null);
		this.setSize(550, 300);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(true);
		
		
		//Grid bag constraints and panel layout
		GridBagConstraints c = new GridBagConstraints();
		panel.setLayout(new GridBagLayout());
		panel.setBackground(Color.white);
		
		c.weightx = 1;
		c.weighty = 1;
		
		
		//Set panels positions in grid
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		c.gridheight = GridBagConstraints.REMAINDER;
		c.fill = GridBagConstraints.BOTH;
		panel.add(panel_1, c);
		panel_1.setBackground(Color.WHITE);
		
		c.gridx = 2;
		c.gridy = 0;
		c.gridwidth = 2;
		c.gridheight = 3;
		c.fill = GridBagConstraints.BOTH;
		panel.add(panel_2, c);
		panel_2.setBackground(Color.WHITE);
		
		c.gridx = 2;
		c.gridy = 3;
		c.gridwidth = 2;
		c.gridheight = 1;
		c.fill = GridBagConstraints.BOTH;
		panel.add(panel_3, c);
		panel_3.setBackground(Color.WHITE);
		
		
		//Add scroll pane in panel_1
		panel_1.setLayout(new GridLayout(1, 0));
		
		
		//Spring Layout
		SpringLayout layout = new SpringLayout();
		
		
		//Add buttons to panel_3
		panel_3.setLayout(layout);
		
		layout.putConstraint(SpringLayout.WEST, updatebutton, 1, SpringLayout.WEST, panel_3);
		layout.putConstraint(SpringLayout.NORTH, updatebutton, 5, SpringLayout.NORTH, panel_3);
		
		layout.putConstraint(SpringLayout.WEST, deletebutton, 1, SpringLayout.EAST, updatebutton);
		layout.putConstraint(SpringLayout.NORTH, deletebutton, 5, SpringLayout.NORTH, panel_3);
		
		layout.putConstraint(SpringLayout.WEST, cancelbutton, 1, SpringLayout.EAST, deletebutton);
		layout.putConstraint(SpringLayout.NORTH, cancelbutton, 5, SpringLayout.NORTH, panel_3);
		
		panel_3.add(updatebutton);
		panel_3.add(deletebutton);
		panel_3.add(cancelbutton);
		
		
		//Add components to panel_2
		panel_2.setLayout(layout);
		
		layout.putConstraint(SpringLayout.WEST, coursenamelabel, 5, SpringLayout.WEST, panel_2);
		layout.putConstraint(SpringLayout.NORTH, coursenamelabel, 5, SpringLayout.NORTH, panel_2);
		
		layout.putConstraint(SpringLayout.WEST, coursetextfield, 5, SpringLayout.EAST, coursenamelabel);
		layout.putConstraint(SpringLayout.NORTH, coursetextfield, 5, SpringLayout.NORTH, panel_2);
		
		layout.putConstraint(SpringLayout.WEST, feeslabel, 5, SpringLayout.WEST,  panel_2);
		layout.putConstraint(SpringLayout.NORTH, feeslabel, 5,SpringLayout.SOUTH, coursetextfield);
		
		layout.putConstraint(SpringLayout.WEST, feestextfield, 5, SpringLayout.EAST, feeslabel);
		layout.putConstraint(SpringLayout.NORTH, feestextfield, 5, SpringLayout.SOUTH, coursetextfield);
		
		panel_2.add(coursenamelabel);
		panel_2.add(feeslabel);
		panel_2.add(coursetextfield);
		panel_2.add(feestextfield);
		
		
		//Add panel to content pane
		this.getContentPane().setLayout(new GridLayout(1, 0));
		this.getContentPane().add(panel);
		
		//Update panel_1
		updatePanel();
		
		
		//Show Frame
		this.setVisible(true);
	}
	
	
	//Implementation of value changed method
	public void valueChanged(ListSelectionEvent e)
	{
		ListSelectionModel lsm = (ListSelectionModel)e.getSource();
		
		int index = lsm.getMinSelectionIndex();
		
		coursename = table.getValueAt(index, 0).toString();
		coursefees = (float)table.getValueAt(index, 1);
		
		coursetextfield.setText(coursename);
		feestextfield.setText(Float.toString(coursefees));
		
		updatebutton.setEnabled(false);
	}
	
	
	//Implementation of action performed method
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == updatebutton) //Update button
		{
			try
			{
				if(iscoursefeesupdated && iscoursenameupdated)
				{
					Database db = new Database();
					
					db.updateCourseFees(coursename, Float.parseFloat(feestextfield.getText()));
					db.updateCourseName(coursename, coursetextfield.getText().trim());
					
					iscoursenameupdated = false;
					iscoursefeesupdated = false;
				}
				else if(iscoursefeesupdated)
				{
					Database db = new Database();
					
					db.updateCourseFees(coursename, Float.parseFloat(feestextfield.getText()));
					
					iscoursefeesupdated = false;
				}
				else if(iscoursenameupdated)
				{
					Database db = new Database();
					
					db.updateCourseName(coursename, coursetextfield.getText().trim());
					
					iscoursenameupdated = false;
				}
				
				updatePanel();
				JOptionPane.showMessageDialog(this, "Update Successful", "Updation", JOptionPane.INFORMATION_MESSAGE);
			}
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(this, ex.getMessage(), "Table not updated", JOptionPane.ERROR_MESSAGE);
			}
		}
		else if(e.getSource() == deletebutton) //Delete button
		{
			try
			{
				Database db = new Database();
				
				db.deleteCourse(coursename);
			}
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(this, ex.getMessage(), "Error in deleting", JOptionPane.ERROR_MESSAGE);
			}
			
			updatePanel();
		}
		else //Cancel button
		{
			this.dispose();
		}
	}
	
	
	//Implementation of key pressed method
	public void keyPressed(KeyEvent e)
	{
		if(e.getSource() == updatebutton && e.getKeyCode() == KeyEvent.VK_ENTER) //Update button
		{
			try
			{
				if(iscoursefeesupdated && iscoursenameupdated)
				{
					Database db = new Database();
					
					db.updateCourseFees(coursename, Float.parseFloat(feestextfield.getText()));
					db.updateCourseName(coursename, coursetextfield.getText().trim());
					
					iscoursenameupdated = false;
					iscoursefeesupdated = false;
				}
				else if(iscoursefeesupdated)
				{
					Database db = new Database();
					
					db.updateCourseFees(coursename, Float.parseFloat(feestextfield.getText()));
					
					iscoursefeesupdated = false;
				}
				else if(iscoursenameupdated)
				{
					Database db = new Database();
					
					db.updateCourseName(coursename, coursetextfield.getText().trim());
					
					iscoursenameupdated = false;
				}
				
				updatePanel();
				JOptionPane.showMessageDialog(this, "Update Successful", "Updation", JOptionPane.INFORMATION_MESSAGE);
			}
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(this, ex.getMessage(), "Table not updated", JOptionPane.ERROR_MESSAGE);
			}
		}
		else if(e.getSource() == deletebutton && e.getKeyCode() == KeyEvent.VK_ENTER) //Delete button
		{
			try
			{
				Database db = new Database();
				
				db.deleteCourse(coursename);
			}
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(this, ex.getMessage(), "Error in deleting", JOptionPane.ERROR_MESSAGE);
			}
			
			updatePanel();
		}
		else //Cancel button
		{
			this.dispose();
		}
	}
	
	
	public void keyReleased(KeyEvent e)
	{
		//Nothing to do
	}
	
	
	public void keyTyped(KeyEvent e)
	{
		//Nothing to do
	}
	
	
	//Implementation of insertUpdate
	public void insertUpdate(DocumentEvent e)
	{
		if(e.getDocument() == coursetextfield.getDocument())
		{
			iscoursenameupdated = true;
		}
		else
		{
			iscoursefeesupdated = true;
		}
		
		updatebutton.setEnabled(true);
	}
	
	
	//Implementation of removeUpdate
	public void removeUpdate(DocumentEvent e)
	{
		if(e.getDocument() == coursetextfield.getDocument())
		{
			iscoursenameupdated = true;
		}
		else
		{
			iscoursefeesupdated = true;
		}
		
		updatebutton.setEnabled(true);
	}
	
	
	//Implementation of changedUpdate
	public void changedUpdate(DocumentEvent e)
	{
		
	}
	
	
	//Update panel_1
	private void updatePanel()
	{	
		String[] colname = {"Course Name", "Course Fees"};
		
		Database db = new Database();
		mytablemodel = new MyTableModel(db.getCourseData(), colname);
		table = new JTable(mytablemodel);
		table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getSelectionModel().addListSelectionListener(this);
		JScrollPane sc = new JScrollPane(table);
		
		panel_1.removeAll();
		panel_1.add(sc);
		this.revalidate();
		
		coursetextfield.setText("");
		feestextfield.setText("");
		updatebutton.setEnabled(false);
	}
}
