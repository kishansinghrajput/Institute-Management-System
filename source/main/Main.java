package ims.main;
import ims.gui.*;

public class Main
{
	public static void main(String args[])
	{
		FirstTimeUserCheck ftuc = new FirstTimeUserCheck(System.getProperty("user.dir"));
		
		if(ftuc.isFirstTimeUser()) //Check if first time user
		{
			new FirstTimeUser();
		}
		else //If not a first time user
		{
			new Authentication();
		}
	}
}
