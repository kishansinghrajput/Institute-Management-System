package ims.gui;

import javax.swing.*;

import ims.main.*;

import java.awt.*;
import java.awt.event.*;
/*
 * Author: Pratyush Sharma
 * Date: 4 March 2014 Tuesday
 * This is the code for password change window for the application
 */
public class PasswordChange extends JFrame implements ActionListener, KeyListener
{
	private static final long serialVersionUID = 1L;
	private JLabel[] label;
	private JPasswordField[] passwordfield;
	private JPanel panel;
	private JButton[] button;
	private Settings settings;
	
	public PasswordChange(Settings obj)
	{
		
		initializeComponent();
		makeGUI();
		settings = obj;
		
	}
	
	//Initializes the components
	private void initializeComponent()
	{
		//Initialize labels
		label = new JLabel[3];
		label[0] = new JLabel("Enter Old Password:");
		label[1] = new JLabel("Enter New Password:");
		label[2] = new JLabel("Confirm New Password:");
		
		//Initializes password fields
		passwordfield = new JPasswordField[3];
		passwordfield[0] = new JPasswordField();
		passwordfield[1] = new JPasswordField();
		passwordfield[2] = new JPasswordField();
		
		//Set tool tip on password fields
		passwordfield[0].setToolTipText("Enter Old Password");
		passwordfield[1].setToolTipText("Enter New Password");
		passwordfield[2].setToolTipText("Re-Enter New Password");
		
		
		//Initialize Buttons
		button = new JButton[2];
		button[0] = new JButton("Change Password");
		button[1] = new JButton("Cancel");
		
		//Set tool tip on buttons
		button[0].setToolTipText("Click to Change the password");
		button[1].setToolTipText("Click to Cancel");
		
		//Add listeners to button
		button[0].addActionListener(this);
		button[1].addActionListener(this);
		button[0].addKeyListener(this);
		button[1].addKeyListener(this);
		
		//Initialize panel
		panel = new JPanel();
	}
	
	private void makeGUI()
	{	
		//Set Frames property
		this.setTitle("Change Password");
		this.setLocationRelativeTo(null);
		this.setSize(400, 200);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		
		//Add components to panel
		panel.setLayout(new GridLayout(4, 2, 2, 2));
		panel.add(label[0]);
		panel.add(passwordfield[0]);
		panel.add(label[1]);
		panel.add(passwordfield[1]);
		panel.add(label[2]);
		panel.add(passwordfield[2]);
		panel.add(button[0]);
		panel.add(button[1]);
		
		//Set panels properties
		panel.setBackground(Color.WHITE);
		
		//Add panel to frame
		this.getContentPane().add(panel);
		panel.validate();
		this.validate();
		
		//Make frame visible
		this.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == button[0])
		{
			
			UserUtility uu = new UserUtility(passwordfield[0].getPassword(), passwordfield[1].getPassword(), passwordfield[2].getPassword());
			
			//Change password
			if(uu.changePassword())
			{
				JOptionPane.showMessageDialog(this, "Password changed", "Password Change Utility", JOptionPane.INFORMATION_MESSAGE);
				this.dispose();
				settings.setVisible(true);
			}
			else
			{
				
				JOptionPane.showMessageDialog(this, "Something is wrong", "Password Change Utility", JOptionPane.ERROR_MESSAGE);
				for(int i = 0; i < 3; i++)
				{
					
					passwordfield[i].setText("");
					
				}
				
			}
			
		}
		else
		{
			
			this.dispose();
			settings.setVisible(true);
			
		}
	}
	
	public void keyTyped(KeyEvent e)
	{
		//Nothing to do
	}
	
	public void keyPressed(KeyEvent e)
	{
		if(e.getSource() == button[0] && e.getKeyCode() == KeyEvent.VK_ENTER)
		{
			
			UserUtility uu = new UserUtility(passwordfield[0].getPassword(), passwordfield[1].getPassword(), passwordfield[2].getPassword());
			
			//Change password
			if(uu.changePassword())
			{
				
				JOptionPane.showMessageDialog(this, "Password changed", "Password Change Utility", JOptionPane.INFORMATION_MESSAGE);
				this.dispose();
				settings.setVisible(true);
				
			}
			else
			{
				
				JOptionPane.showMessageDialog(this, "Something is wrong", "Password Change Utility", JOptionPane.ERROR_MESSAGE);
				for(int i = 0; i < 3; i++)
				{
					
					passwordfield[i].setText("");
					
				}
				
			}
			
		}
		else
		{
			
			if(e.getKeyCode() == KeyEvent.VK_ENTER)
			{
				
				this.dispose();
				settings.setVisible(true);
				
			}
			
		}
		
	}
	
	public void keyReleased(KeyEvent e)
	{
		//Nothing to do
	}
}
