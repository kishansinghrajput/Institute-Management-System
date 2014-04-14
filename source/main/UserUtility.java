package ims.main;

import java.io.*;
import java.util.Arrays;

/*
 * Author: Pratyush Sharma
 * Date: 11 March 2014 Monday
 * This is the code for all kind of user related tools required for the software.
 */

public class UserUtility
{
	private String username = null;
	private char[] newpassword = null;
	private char[] oldpassword = null;
	private char[] confirmpassword = null;
	private String path = null;
	
	//For first time user
	public UserUtility(String username, char[] newpassword, char[] confirmpassword, String path)
	{
		this.username = username;
		this.newpassword = newpassword;
		this.confirmpassword = confirmpassword;
		this.path = path;
	}
	
	//For authentication
	public UserUtility(String username, char[] newpassword)
	{
		this.username = username;
		this.newpassword = newpassword;
	}
	
	//For password change
	public UserUtility(char[] oldpassword, char[] newpassword, char[] confirmpassword)
	{
		this.oldpassword = oldpassword; //current password
		this.newpassword = newpassword;
		this.confirmpassword = confirmpassword;
	}
	
	//Register user for first time
	public boolean registerUser()
	{
		//Validate user name and password
		if(!validateFirstTime(username, newpassword, confirmpassword))
		{
			return false;
		}
		
		//Create Settings directory
		try
		{
			File file = new File(path + "/Settings");
			if(!file.exists())
			{
				file.mkdir(); //Make folder named "Settings"
			}
		}
		catch(Exception ex)
		{
			System.out.println(path + ex);
			return false;
		}
		
		try
		{
			//Open file for writing
			PrintStream out = new PrintStream(new FileOutputStream(path + "/Settings/User"));
				
			out.println(username); //Write user name
			out.println(newpassword); //Write password
				
			out.close(); //Close the file
			
			return true;
		}
		catch(IOException e)
		{
			return false;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	private boolean validateFirstTime(String username, char[] password, char[] confirmpassword)
	{
		//Check if any thing is null
		if(username != null && password != null && confirmpassword != null)
		{
			//Check for lengths of user name and passwords
			if(username.length() > 0 && password.length > 5 && confirmpassword.length > 5)
			{
				//Compare the passwords
				if(Arrays.equals(password, confirmpassword))
				{
					return true;
				}
				else //password do not match
				{
					return false;
				}
			}
			else //Length restriction
			{
				return false;
			}
		}
		else //Something was null
		{
			return false;
		}
	}
	
	public boolean authenticateUser()
	{
		//Get the path of file
		String path = System.getProperty("user.dir") + "/Settings/User";
		
		try
		{
			//Open file for reading
			BufferedReader br = new BufferedReader(new FileReader(path));
			
			String username = br.readLine(); //Read user name
			String password = br.readLine(); //Read password
			
			br.close(); //Close file
			
			//Match user name and password
			if(username.equals(this.username) && Arrays.equals(password.toCharArray(), this.newpassword))
			{
				return true;
			}
			else //user name and password does not match
			{
				return false;
			}
		}
		catch(FileNotFoundException e)
		{
			return false;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	public boolean changePassword()
	{
		String username;
		String password;
		BufferedReader br = null;

		//Get user name and password from file
		try
		{
			br = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/Settings/User"));
			
			username = br.readLine(); //Read user name
			password = br.readLine(); //Read password
			
			br.close(); //Close file
			
			if(!Arrays.equals(password.toCharArray(), this.oldpassword)) //Match Password
			{
				return false;
			}
		}
		catch(Exception e)
		{
			return false;
		}
		
		//Validate user name and new password
		if(validateFirstTime(username, this.newpassword, this.confirmpassword))
		{
			//Write new password to file with old user name
			try
			{
				File file = new File(System.getProperty("user.dir") + "/Settings/User");
				file.delete();
				
				PrintWriter pw = new PrintWriter(new FileWriter(file)); //Create a new File
				
				pw.println(username); //Write user name
				pw.println(this.newpassword); //Write password
				
				pw.close();
				
				return true;
			}
			catch(Exception e)
			{
				return false;
			}
		}
		else
		{
			return false;
		}
	}
}
