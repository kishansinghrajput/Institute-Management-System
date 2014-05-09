package ims.gui;

import javax.swing.*;
import ims.main.*;
import java.awt.*;
import java.awt.event.*;

/*
 * Author: Pratyush Sharma
 * Date: 3 March 2014, Monday
 * This is the code for authentication window for the application.
 */
public class Authentication extends JFrame implements ActionListener, KeyListener
{
	private static final long serialVersionUID = 1L;
	private JLabel[] label;
	private JTextField username;
	private JPasswordField password;
	private JButton[] button;
	private JPanel panel;
	
	public Authentication()
	{
		this.initializeComponent();
		this.makeGUI();
	}
	
	//Initializes all the components of the class
	private void initializeComponent()
	{
		//Initialize Labels
		label = new JLabel[2];
		label[0] = new JLabel("Username");
		label[1] = new JLabel("Password");
		
		//Initialize Buttons
		button = new JButton[2];
		button[0] = new JButton("Login");
		button[0].addActionListener(this);//Add Action Listener to Button[0]
		button[0].addKeyListener(this);//Add Key Listener to Button[0]
		button[1] = new JButton("Cancel");
		button[1].addActionListener(this);//Add Action Listener to Button[1]
		button[1].addKeyListener(this);//Add Key Listener to Button[1]
		
		//Initialize TextFields and Password Fields
		username = new JTextField();
		password = new JPasswordField();
		
		//Initialize panel
		panel = new JPanel();
	}
	
	//Make GUI using the components
	private void makeGUI()
	{
		//Set Frame's properties
		this.setTitle("Authentication");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setSize(300, 100);
		this.setLocationRelativeTo(null);
		
		//Grid Layout
		GridLayout layout = new GridLayout(3, 2, 2, 2);
		
		//Add components to panel
		panel.setLayout(layout);
		panel.add(label[0]);
		panel.add(username);
		panel.add(label[1]);
		panel.add(password);
		panel.add(button[0]);
		panel.add(button[1]);
		
		//Set panel background to white
		panel.setBackground(Color.white);
		
		//Set tool tips on Text components
		username.setToolTipText("Enter your Username");
		password.setToolTipText("Enter your Password");
		
		//Set tool tips on buttons
		button[0].setToolTipText("Click to Login");
		button[1].setToolTipText("Click to Cancel");
		
		//Add panel to frame
		this.getContentPane().add(panel);
		
		//Show frame
		this.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == button[0])
		{
			UserUtility uu = new UserUtility(username.getText(), password.getPassword());
			
			if(uu.authenticateUser())
			{
				this.dispose();
				new IMSMain();
			}
			else
			{
				JOptionPane.showMessageDialog(this, "User authentication fails", "Authentication failure", JOptionPane.ERROR_MESSAGE);
				password.setText("");
			}
		}
		else
		{
			this.dispose();
		}
	}
	
	public void keyPressed(KeyEvent e)
	{
		if(e.getSource() == button[0] && e.getKeyCode() == KeyEvent.VK_ENTER)
		{
			UserUtility uu = new UserUtility(username.getText(), password.getPassword());
			
			if(uu.authenticateUser())
			{
				this.dispose();
				new IMSMain();
			}
			else
			{
				JOptionPane.showMessageDialog(this, "User authentication fails", "Authentication failure", JOptionPane.ERROR_MESSAGE);
				password.setText("");
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
