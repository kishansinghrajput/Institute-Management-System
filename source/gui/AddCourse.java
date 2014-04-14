package ims.gui;

import ims.main.Course;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

/*
 * Author: Pratyush Sharma
 * Date: 10 March 2014 Monday
 * This is the code for AddCourse window for the application
 */

public class AddCourse extends JFrame implements ActionListener, KeyListener
{
	private static final long serialVersionUID = 1L;
	private JLabel label_1, label_2;
	private JTextField textfield_1, textfield_2;
	private JButton addbutton, cancelbutton;
	private JPanel panel;
	
	public AddCourse()
	{
		
		initializeComponent();
		makeGUI();
		
	}
	
	private void initializeComponent()
	{
		
		//Initialize labels
		label_1 = new JLabel("Course Name");
		label_2 = new JLabel("Course Fee");
		
		//Initialize text fields
		textfield_1 = new JTextField(15);
		textfield_2 = new JTextField(15);
		
		//Initialize buttons
		addbutton = new JButton("Add");
		cancelbutton = new JButton("Cancel");
		
		//Initialize panel
		panel = new JPanel();
		
		//Add Action Listener to button
		addbutton.addActionListener(this);
		cancelbutton.addActionListener(this);
		
		//Add Key Listener to button
		addbutton.addKeyListener(this);
		cancelbutton.addKeyListener(this);
		
	}
	
	private void makeGUI()
	{
		
		//Set frame's properties
		this.setTitle("Add Course");
		this.setSize(400, 100);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		//GridLayout
		GridLayout layout = new GridLayout(3, 2, 3, 3);
		
		//Set panel layout and add components
		panel.setLayout(layout);
		panel.add(label_1);
		panel.add(textfield_1);
		panel.add(label_2);
		panel.add(textfield_2);
		panel.add(addbutton);
		panel.add(cancelbutton);
		panel.setBackground(Color.white);
		
		//Add panel to frame
		this.add(panel);
		this.validate();
		
		//Show frame
		this.setVisible(true);
		
	}
	
	public void actionPerformed(ActionEvent e)
	{
		
		if(e.getSource() == addbutton)
		{
			
			if(validateInformation())
			{
				
				Course course = new Course(this);
				
				if(course.addCourse())
				{
					
					JOptionPane.showMessageDialog(this, "Course added", "Add Course", JOptionPane.INFORMATION_MESSAGE);
					this.dispose();
					
				}
				else
				{
					
					JOptionPane.showMessageDialog(this, "Course not added", "Add Course", JOptionPane.ERROR_MESSAGE);
					
				}
				
			}
			else
			{
				
				JOptionPane.showMessageDialog(this, "Check your inputs", "Add Course", JOptionPane.ERROR_MESSAGE);
				
			}
			
		}
		else
		{
			
			this.dispose();
			
		}
		
	}
	
	public void keyPressed(KeyEvent e)
	{
		
		if(e.getSource() == addbutton)
		{
			
			if(e.getKeyCode() == KeyEvent.VK_ENTER)
			{
				
				if(validateInformation())
				{
					
					Course course = new Course(this);
					
					if(course.addCourse())
					{
						
						JOptionPane.showMessageDialog(this, "Course added", "Add Course", JOptionPane.INFORMATION_MESSAGE);
						this.dispose();
						
					}
					else
					{
						
						JOptionPane.showMessageDialog(this, "Course not added", "Add Course", JOptionPane.ERROR_MESSAGE);
						
					}
					
				}
				else
				{
					
					JOptionPane.showMessageDialog(this, "Check your inputs", "Add Course", JOptionPane.ERROR_MESSAGE);
					
				}
				
			}
			
		}
		else
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
	
	//Validation method
	private boolean validateInformation()
	{
		
		if(textfield_1.getText() == "")
		{
			
			return false;
			
		}
		
		try
		{
			
			Float.parseFloat(textfield_2.getText());
			return true;
			
		}
		catch(Exception e)
		{
			
			return false;
			
		}
		
	}
	
	//Get course name
	public String getCourseName()
	{
		
		return textfield_1.getText().trim();
		
	}
	
	//Get course fees
	public float getCourseFees()
	{
		
		return Float.parseFloat(textfield_2.getText());
		
	}
}
