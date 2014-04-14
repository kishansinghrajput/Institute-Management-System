package ims.main;

import java.io.*;

/*
 * Author: Pratyush Sharma
 * Date: 11 March 2014 Monday
 * This is the code for database backup. 
 */

public class DataBackup
{
	String path = null;
	
	public DataBackup(String path)
	{
		this.path = path;
	}
	
	//Method for backing up data
	public boolean backupDatabase()
	{
		String databasename = null;
		String username = null;
		String password = null;
		
		if(path == null || path == "")
		{
			return false;
		}
		
		try
		{
			File file = new File(System.getProperty("user.dir") + "/Settings/Database");
			BufferedReader br = new BufferedReader(new FileReader(file)); //Open file for reading
			
			databasename = br.readLine(); //Read Database name
			username = br.readLine(); //Read user name
			password = br.readLine(); //Read Password
			
			br.close(); //Close stream
			String command = "mysqldump --user=" + username + " --password=" + password + " " + databasename;
			Process process = Runtime.getRuntime().exec(command); //Execute backup Command
	
			br = new BufferedReader(new InputStreamReader(process.getInputStream())); //Read output of process
			FileWriter fw = new FileWriter(path + "/Backup.sql"); //Open file for backing/writing
			
			String s;
			while((s = br.readLine()) != null) //Read and write on line at a time
			{
				fw.write(s + "\n");
			}
			br.close(); //Close stream
			fw.close(); //Close stream
			
			int val = process.waitFor();
			if(val == 0) //Check exit value
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
			return false;
		}
	}
}
