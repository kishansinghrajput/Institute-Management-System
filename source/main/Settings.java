package ims.main;

import java.io.*;

/*
 *Author: Pratyush Sharma
 *Date: 11 March 2014 Monday
 *This is the code for saving settings to the file. 
 */

public class Settings
{
	//Method for saving Installment information in file
	public static boolean saveInstallment(int value)
	{
		try
		{
			File file = new File(System.getProperty("user.dir") + "/Settings/Installment");
			
			PrintWriter pw = new PrintWriter(new FileWriter(file)); //Create a file named Installment
			
			pw.println(value); //Write the value to file
			
			pw.close(); //Close stream
			
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
	
	//Method for getting installment information from file
	public static int getInstallment()
	{
		try
		{
			File file = new File(System.getProperty("user.dir") + "/Settings/Installment");
			
			if(!file.exists()) //If file does not exist
			{
				return 100;
			}
			
			BufferedReader br = new BufferedReader(new FileReader(file)); //Open file for reading
			
			int value = Integer.parseInt(br.readLine()); //Parse String to double
			
			br.close(); //Close stream
			
			return value; //return value
		}
		catch(IOException e)
		{
			return 100;
		}
		catch(Exception e)
		{
			return 100;
		}
	}
	
	//Method for saving Database information in file
	public static boolean saveDatabaseInformation(String databasename,String username, char[] password)
	{
		try
		{
			File file = new File(System.getProperty("user.dir") + "/Settings/Database");
			
			if(isDatabaseInformationAvailable())
			{
				BufferedReader br = new BufferedReader(new FileReader(file));
				databasename = br.readLine();
				br.close();
			}
			
			PrintWriter pw = new PrintWriter(new FileWriter(file)); //Create or overwrite file
			
			pw.println(databasename); //Write database name
			pw.println(username); //Write user name
			pw.println(password); //Write password
			
			pw.close(); //Close stream
			
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
	
	public static boolean isDatabaseInformationAvailable()
	{
		File file = new File(System.getProperty("user.dir") + "/Settings/Database");
		
		if(file.exists())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
