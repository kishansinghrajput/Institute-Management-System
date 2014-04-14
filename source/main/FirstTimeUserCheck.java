package ims.main;

import java.io.File;

/*
 * Author: Pratyush Sharma
 * Date: 11 March 2014
 * This is the code for checking whether the software is executed for first time on users computer.
 */

public class FirstTimeUserCheck
{
	String path = null;
	
	public FirstTimeUserCheck(String path)
	{
		this.path = path;
	}
	
	public boolean isFirstTimeUser()
	{
		File file = new File(path + "/Settings");
		
		if(file.exists())
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	//Register user, return false if cannot else return true
	public boolean registerUser(String username, char[] password, char[] confirmpassword)
	{
		UserUtility uu = new UserUtility(username, password, confirmpassword, path);
		
		if(uu.registerUser())
			return true;
		else
			return false;
	}
}
