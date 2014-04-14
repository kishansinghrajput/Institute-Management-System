package ims.gui;

import ims.main.Database;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

/*
 * Author: Pratyush Sharma
 * Date: 6 March 2014 Thursday
 * This is the code for settings window for the application
 */

public class Settings extends JFrame implements ActionListener, KeyListener
{
	private static final long serialVersionUID = 1L;
	private JTabbedPane tabbedpane;
	private JPanel panel[];
	private SpinnerModel spinnermodel;
	private JSpinner spinner;
	private JTextField textfield[];
	private JPasswordField passwordfield;
	private JButton button[];
	private JLabel label[];
	
	public Settings()
	{
		initializeComponent();
		makeGUI();
	}
	
	private void initializeComponent()
	{
		//Initialize Tabbed Pane
		tabbedpane = new JTabbedPane();
		
		//Initialize Panels
		panel = new JPanel[3];
		panel[0] = new JPanel(); //For General tab
		panel[1] = new JPanel(); //For Database tab
		panel[2] = new JPanel(); //For Authentication tab
		
		//Add tabs to Tabbed pane and insert panels into them
		tabbedpane.addTab("General", null, panel[0], "General Settings"); //General tab
		tabbedpane.addTab("Database", null, panel[1], "Database Settings"); //Database tab
		tabbedpane.addTab("Authentication", null, panel[2], "Authentication Settings"); //Authentication tab
		
		//Initialize spinner model
		spinnermodel = new SpinnerNumberModel(100, 0, 30000, 5); //Spinner Number Model
		
		//Initialize spinner
		spinner = new JSpinner(spinnermodel);
		
		//Initialize text field
		textfield = new JTextField[2];
		textfield[0] = new JTextField(); //For database name
		textfield[1] = new JTextField(); //For user name
		
		//Initialize password field
		passwordfield = new JPasswordField(); //For database password
		
		//Initialize buttons
		button = new JButton[5];
		button[0] = new JButton("Save"); //In Panel 0, Installment
		button[1] = new JButton("Cancel"); //In Panel 0, Installment
		button[2] = new JButton("Save"); //In Panel 1, Database
		button[3] = new JButton("Cancel"); //In Panel 1, Database
		button[4] = new JButton("Change Password"); //In Panel 2, Authentication
		
		//Initialize labels
		label = new JLabel[4];
		label[0] = new JLabel("Charges per Installment"); //Panel 0
		label[1] = new JLabel("Database name"); //Panel 1
		label[2] = new JLabel("Username"); //Panel 1
		label[3] = new JLabel("Password"); //Panel 1
		
		//Add event listeners to buttons
		button[0].addActionListener(this);
		button[0].addKeyListener(this);
		button[1].addActionListener(this);
		button[1].addKeyListener(this);
		button[2].addActionListener(this);
		button[2].addKeyListener(this);
		button[3].addActionListener(this);
		button[3].addKeyListener(this);
		button[4].addActionListener(this);
		button[4].addKeyListener(this);
	}
	
	private void makeGUI()
	{
		//Set frame's properties
		this.setTitle("Settings");
		this.setSize(400, 180);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		//Design panel[0]
		panel[0].setLayout(new GridLayout(2, 2, 2, 2));
		panel[0].add(label[0]);
		panel[0].add(spinner);
		spinner.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 30));
		panel[0].add(button[0]);
		panel[0].add(button[1]);
		panel[0].setBackground(Color.WHITE);
		
		//Design panel[1]
		panel[1].setLayout(new GridLayout(4, 2));
		panel[1].setBackground(Color.WHITE);
		panel[1].add(label[1]);
		panel[1].add(textfield[0]);
		panel[1].add(label[2]);
		panel[1].add(textfield[1]);
		panel[1].add(label[3]);
		panel[1].add(passwordfield);
		panel[1].add(button[2]);
		panel[1].add(button[3]);
		
		//Design panel[2]
		panel[2].setLayout(new FlowLayout());
		panel[2].setBackground(Color.WHITE);
		panel[2].add(button[4]);
		
		//Add Tabbed Pane to frame
		this.setLayout(new GridLayout(1, 1));
		this.getContentPane().add(tabbedpane);
		
		//Set tool tip text to buttons
		button[0].setToolTipText("Click to Save");
		button[1].setToolTipText("Click to Cancel");
		button[2].setToolTipText("Click to Save");
		button[3].setToolTipText("Click to Cancel");
		button[4].setToolTipText("Click to open Change Password window");
		
		//Set tool tip text to text components
		textfield[0].setToolTipText("Enter database name in which tabels will be created");
		textfield[1].setToolTipText("Enter Username for the database server");
		passwordfield.setToolTipText("Enter Password for the database server");
		
		//Set value of spinner
		spinner.setValue(ims.main.Settings.getInstallment()); //Get spinner value from Installment file
		
		//Set text on database name textfield
		if(ims.main.Settings.isDatabaseInformationAvailable()) //If Database file exists
		{
			
			textfield[0].setText("Already Have");
			textfield[0].setEnabled(false);
		
		}
		
		//Validate frame
		this.validate();
		
		//Show frame
		this.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == button[0]) //Save button in Installment tab
		{
			
			int value = (int)spinner.getValue();
			
			//Save the value to file
			if(ims.main.Settings.saveInstallment(value))
			{
				
				JOptionPane.showMessageDialog(this, "Insallment charges saved", "", JOptionPane.INFORMATION_MESSAGE);
			
			}
			else
			{
				
				JOptionPane.showMessageDialog(this, "Insallment charges not saved", "", JOptionPane.ERROR_MESSAGE);
			
			}
			
		}
		else if(e.getSource() == button[2]) //Save button in Database tab
		{
			
			if(ims.main.Settings.saveDatabaseInformation(textfield[0].getText(), textfield[1].getText(), passwordfield.getPassword()))
			{
				if(textfield[0].isEnabled())
				{
					
					Database db = new Database();
					db.createDatabase();
					
				}
				JOptionPane.showMessageDialog(this, "Database Information Saved", "", JOptionPane.INFORMATION_MESSAGE);
			
			}
			else
			{
				
				JOptionPane.showMessageDialog(this, "Database Information not Saved", "", JOptionPane.INFORMATION_MESSAGE);
			
			}
			
			this.dispose(); //Close window after operation
			
		}
		else if(e.getSource() == button[4]) //Change password button
		{
			
			this.setVisible(false);
			new PasswordChange(this);
		
		}
		else //For cancel buttons
		{
			
			this.dispose();
		
		}
	}
	
	public void keyPressed(KeyEvent e)
	{
		if(e.getSource() == button[0] && e.getKeyCode() == KeyEvent.VK_ENTER) //Save button in Installment tab
		{
			
			int value = (int)spinner.getValue();
			
			//Save the value to file
			if(ims.main.Settings.saveInstallment(value))
			{
				
				JOptionPane.showMessageDialog(this, "Insallment charges saved", "", JOptionPane.INFORMATION_MESSAGE);
			
			}
			else
			{
				
				JOptionPane.showMessageDialog(this, "Insallment charges not saved", "", JOptionPane.ERROR_MESSAGE);
			
			}
			
		}
		else if(e.getSource() == button[2] && e.getKeyCode() == KeyEvent.VK_ENTER) //Save button in Database tab
		{
			
			if(ims.main.Settings.saveDatabaseInformation(textfield[0].getText(), textfield[1].getText(), passwordfield.getPassword()))
			{
				if(textfield[0].isEnabled())
				{
					
					Database db = new Database();
					db.createDatabase();
					
				}
				JOptionPane.showMessageDialog(this, "Database Information Saved", "", JOptionPane.INFORMATION_MESSAGE);
			
			}
			else
			{
				
				JOptionPane.showMessageDialog(this, "Database Information not Saved", "", JOptionPane.INFORMATION_MESSAGE);
			
			}
			
			this.dispose(); //Close Window after operation
		
		}
		else if(e.getSource() == button[4]) //Change password button
		{
			
			if(e.getKeyCode() == KeyEvent.VK_ENTER)
			{
				
				this.setVisible(false);
				new PasswordChange(this);
				
			}
		
		}
		else //Other cancel buttons
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
