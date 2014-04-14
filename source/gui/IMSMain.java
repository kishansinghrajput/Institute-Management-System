package ims.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/*
 * Author: Pratyush Sharma
 * Date: 7 March 2014 Friday
 * This is the code for main window of the application
 */

public class IMSMain extends JFrame implements ActionListener, KeyListener
{
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JButton button[];
	private JMenuBar menubar;
	private JMenuItem settingsMenuItem, backMenuItem;
	private JMenu menu;
	
	public IMSMain()
	{
		initializeComponent();
		makeGUI();
	}
	
	private void initializeComponent()
	{
		//Initialize panels
		panel = new JPanel();
		
		//Initialize buttons
		button = new JButton[4];
		button[0] = new JButton("Add Student");
		button[1] = new JButton("Search & Edit Student");
		button[2] = new JButton("Add Course");
		button[3] = new JButton("Search & Edit Course");
		
		//Add Action Listeners to buttons
		button[0].addActionListener(this);
		button[1].addActionListener(this);
		button[2].addActionListener(this);
		button[3].addActionListener(this);
		
		//Add Key Listeners to buttons
		button[0].addKeyListener(this);
		button[1].addKeyListener(this);
		button[2].addKeyListener(this);
		button[3].addKeyListener(this);
		
		//Initialize menu bar
		menubar = new JMenuBar();
		
		//Initialize menu
		menu = new JMenu("Control");
		
		//Initialize settings menu item
		settingsMenuItem = new JMenuItem("Settings");
		settingsMenuItem.addActionListener(this);
		settingsMenuItem.addKeyListener(this);
		
		//Initialize backup menu item
		backMenuItem = new JMenuItem("Database Backup");
		backMenuItem.addActionListener(this);
		backMenuItem.addKeyListener(this);
	}
	
	private void makeGUI()
	{
		//Set Frame's property
		this.setTitle("Institute Management System");
		this.setSize(500, 200);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //EXIT_ON_CLOSE, so as to close all windows when main is closed
		
		//Design panel
		panel.setLayout(new GridLayout(2, 2, 2, 2));
		panel.setBackground(Color.WHITE);
		panel.add(button[0]);
		panel.add(button[2]);
		panel.add(button[1]);
		panel.add(button[3]);
		
		//Add menu to menubar
		menubar.add(menu);
		
		//Add menu items to menu
		menu.add(settingsMenuItem);
		menu.add(backMenuItem);
		
		//Set menu bar to frame
		this.setJMenuBar(menubar);
		
		//Add panel to frame
		this.add(panel);
		
		//Set tool tip text to buttons
		button[0].setToolTipText("Add a Student");
		button[1].setToolTipText("Search and Edit a Student");
		button[2].setToolTipText("Add a Course");
		button[3].setToolTipText("Search and Edit a Course");
		
		//Show frame
		this.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == settingsMenuItem)
		{
			new Settings();
		}
		else if(e.getSource() == backMenuItem)
		{
			new DatabaseBackup();
		}
		else if(e.getSource() == button[0])
		{
			new AddStudent();
		}
		else if(e.getSource() == button[1])
		{
			new SearchAndEditStudent();
		}
		else if(e.getSource() == button[2])
		{
			new AddCourse();
		}
		else
		{
			new SearchAndEditCourse();
		}
	}
	
	
	public void keyPressed(KeyEvent e)
	{
		if(e.getSource() == settingsMenuItem && e.getKeyCode() == KeyEvent.VK_ENTER)
		{
			new Settings();
		}
		else if(e.getSource() == backMenuItem && e.getKeyCode() == KeyEvent.VK_ENTER)
		{
			new DatabaseBackup();	
		}
		else if(e.getSource() == button[0])
		{
			if(e.getKeyCode() == KeyEvent.VK_ENTER)
			{
				new AddStudent();
			}
		}
		else if(e.getSource() == button[1])
		{
			if(e.getKeyCode() == KeyEvent.VK_ENTER)
			{
				new SearchAndEditStudent();
			}	
		}
		else if(e.getSource() == button[2])
		{
			if(e.getKeyCode() == KeyEvent.VK_ENTER)
			{
				new AddCourse();	
			}
		}
		else
		{
			if(e.getKeyCode() == KeyEvent.VK_ENTER)
			{
				new SearchAndEditCourse();
			}
		}
	}
	
	
	public void keyTyped(KeyEvent e)
	{
		
	}
	
	public void keyReleased(KeyEvent e)
	{
		
	}
}
