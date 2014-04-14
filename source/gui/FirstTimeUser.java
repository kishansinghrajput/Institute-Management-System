package ims.gui;

import ims.main.FirstTimeUserCheck;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

/*
 * Author: Pratyush Sharma
 * Date: 5 March 2014 Wednesday
 * This is the code for first time user windows for the application
 */

public class FirstTimeUser extends JFrame implements ActionListener, KeyListener
{
	private static final long serialVersionUID = 1L;
	private JPanel panel[];
	private JTextField textfield;
	private JPasswordField passwordfield[];
	private JButton button[];
	private JLabel label[];
	private JTextArea textarea;
	private JScrollPane scrollpane;
	
	public FirstTimeUser()
	{
		initializeComponent();
		makeGUI();
	}
	
	private void initializeComponent()
	{
		//Initialize panels
		panel = new JPanel[2];
		panel[0] = new JPanel();
		panel[1] = new JPanel();
		
		//Initialize text field
		textfield = new JTextField();
		
		//Initialize password field
		passwordfield = new JPasswordField[2];
		passwordfield[0] = new JPasswordField();
		passwordfield[1] = new JPasswordField();
		
		//Initialize buttons
		button = new JButton[2];
		button[0] = new JButton("Set");
		button[1] = new JButton("Cancel");
		
		//Add listeners to buttons
		button[0].addActionListener(this);
		button[0].addKeyListener(this);
		button[1].addActionListener(this);
		button[1].addKeyListener(this);
		
		//Initialize labels
		label = new JLabel[3];
		label[0] = new JLabel("Enter Username:");
		label[1] = new JLabel("Enter Password:");
		label[2] = new JLabel("Re-Enter Password:");
		
		//Initialize text area
		textarea = new JTextArea();
		
		//Initialize scroll pane
		//initialized in makeGUI() method
	}
	
	private void makeGUI()
	{
		//Set frame properties
		this.setTitle("First Time User");
		this.setSize(400, 300);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(new GridLayout(2, 1, 1, 2));
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		//Add Panels to the frame
		this.getContentPane().add(panel[0]);
		this.getContentPane().add(panel[1]);
	
		//Add components to panel[0]
		panel[0].setLayout(new GridLayout(4, 2, 2, 2));
		panel[0].add(label[0]);
		panel[0].add(textfield);
		panel[0].add(label[1]);
		panel[0].add(passwordfield[0]);
		panel[0].add(label[2]);
		panel[0].add(passwordfield[1]);
		panel[0].add(button[0]);
		panel[0].add(button[1]);
		
		//Set text area
		textarea.setText("This is a First time user window."
				+ "\nSet Username and Password which will be used for AUTHENTICATION in this application."
				+ "\nRestart the application after setting the required fields."
				+ "\nPassword must be of atleast 6 characters.");
		textarea.setEditable(false);
		textarea.setLineWrap(true);
		textarea.setWrapStyleWord(true);
		
		//Add text area to scroll pane
		scrollpane = new JScrollPane(textarea);
		
		//Add scroll pane to panel[1]
		panel[1].setLayout(new GridLayout(1, 0));
		panel[1].add(scrollpane);
		
		//Set panel backgrounds
		panel[0].setBackground(Color.WHITE);
		panel[1].setBackground(Color.WHITE);
		
		//Add tool tip text to buttons
		button[0].setToolTipText("Click to Set Username and Password");
		button[1].setToolTipText("Click to cancel and exit");
		
		//Add tool tip text to text components
		textfield.setToolTipText("Enter Username");
		passwordfield[0].setToolTipText("Enter Passowrd");
		passwordfield[1].setToolTipText("Confirm Password");
		
		//Validate panel and frame
		panel[0].validate();
		panel[1].validate();
		this.validate();
		
		//Show the frame
		this.setVisible(true);
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
			if(e.getSource() == button[0])
			{
				FirstTimeUserCheck ftuc = new FirstTimeUserCheck(System.getProperty("user.dir"));
				if(ftuc.registerUser(textfield.getText(), passwordfield[0].getPassword(), passwordfield[1].getPassword()))
				{
					JOptionPane.showMessageDialog(this, "User Successfully Registered", "Registeration Successful", JOptionPane.INFORMATION_MESSAGE);
					this.dispose();
				}
				else
				{
					JOptionPane.showMessageDialog(this, "User Not Registered", "Registeration UnSuccessful", JOptionPane.ERROR_MESSAGE);
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

	@Override
	public void keyReleased(KeyEvent e)
	{
		// Nothing to do.	
	}

	@Override
	public void keyTyped(KeyEvent e)
	{
		//Nothing to do.	
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == button[0])
		{
			FirstTimeUserCheck ftuc = new FirstTimeUserCheck(System.getProperty("user.dir"));
			if(ftuc.registerUser(textfield.getText(), passwordfield[0].getPassword(), passwordfield[1].getPassword()))
			{
				JOptionPane.showMessageDialog(this, "User Successfully Registered", "Registeration Successful", JOptionPane.INFORMATION_MESSAGE);
				this.dispose();
			}
			else
			{
				JOptionPane.showMessageDialog(this, "User Not Registered", "Registeration UnSuccessful", JOptionPane.ERROR_MESSAGE);
			}
		}
		else
		{
			this.dispose();
		}
	}
}
