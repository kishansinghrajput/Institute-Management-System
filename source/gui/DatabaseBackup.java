package ims.gui;

import javax.swing.*;

import ims.main.DataBackup;

import java.awt.*;
import java.awt.event.*;
import java.io.File;

/*
 * Author: Pratyush Sharma
 * Date: 9 March 2014 Sunday
 * This is the code for Data backup window for the application
 */

public class DatabaseBackup extends JFrame implements ActionListener, KeyListener
{
	private static final long serialVersionUID = 1L;
	private JFileChooser filechooser;
	private JTextField textfield;
	private JButton backup, cancel, browse;
	private JPanel panel;
	
	public DatabaseBackup()
	{
		initializeComponent();
		makeGUI();
	}
	
	private void initializeComponent()
	{
		//Initialize file chooser
		filechooser = new JFileChooser();
		filechooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
		//Initialize textfield
		textfield = new JTextField();
		
		//Initialize buttons
		backup = new JButton("Backup");
		cancel = new JButton("Cancel");
		browse = new JButton("Browse");
		
		//Add Action Listeners to buttons
		backup.addActionListener(this);
		cancel.addActionListener(this);
		browse.addActionListener(this);
		
		//Add Key Listener to buttons
		backup.addKeyListener(this);
		cancel.addKeyListener(this);
		browse.addKeyListener(this);
		
		//Initialize panel
		panel = new JPanel();
	}
	
	private void makeGUI()
	{
		//Set Frame's properties
		this.setTitle("Data Backup");
		this.setSize(500, 150);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		//Design panel
		panel.setLayout(new GridLayout(3, 2, 2, 2));
		panel.add(textfield);
		textfield.setEditable(false);
		panel.add(browse);
		panel.add(new Label());
		panel.add(new Label());
		panel.add(backup);
		panel.add(cancel);
		panel.setBackground(Color.WHITE);
		
		//Add tool tip text to buttons
		browse.setToolTipText("Select directory to store backup files");
		backup.setToolTipText("Click to Backup data");
		cancel.setToolTipText("Click to exit");
		textfield.setToolTipText("Path to directory in which data will be backed up");
		
		//Add panel to frame
		this.add(panel);
		
		//Show frame
		this.validate();
		this.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == browse) //Browse button
		{
			if(filechooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
			{
				File file = filechooser.getSelectedFile();
				textfield.setText(file.getPath());
			}
		}
		else if(e.getSource() == backup) //Backup button
		{
			DataBackup db = new DataBackup(textfield.getText()); //Get path to directory
			
			if(db.backupDatabase())
			{
				JOptionPane.showMessageDialog(this, "Database Backed up", "", JOptionPane.INFORMATION_MESSAGE);
				this.dispose();
			}
			else
			{
				JOptionPane.showMessageDialog(this, "Database could not be backed up", "", JOptionPane.ERROR_MESSAGE);
			}
		}
		else
		{
			this.dispose();
		}
	}
	
	public void keyPressed(KeyEvent e)
	{
		if(e.getSource() == browse) //Browse button
		{
			if(e.getKeyCode() == KeyEvent.VK_ENTER)
			{
				if(filechooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
				{
					File file = filechooser.getSelectedFile();
					textfield.setText(file.getPath());
				}
			}
		}
		else if(e.getSource() == backup && e.getKeyCode() == KeyEvent.VK_ENTER) //Backup Button
		{
			DataBackup db = new DataBackup(textfield.getText()); //Get path to directory
			
			if(db.backupDatabase())
			{
				JOptionPane.showMessageDialog(this, "Database Backed up", null, JOptionPane.INFORMATION_MESSAGE);
				this.dispose();
			}
			else
			{
				JOptionPane.showMessageDialog(this, "Database could not backed up", null, JOptionPane.ERROR_MESSAGE);
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
}
