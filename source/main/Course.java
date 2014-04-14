package ims.main;

import ims.gui.*;

/*
 * Author: Pratyush Sharma
 * Date: 13th March 2014 Thursday
 * This is the code for all operations related to course.
 */

public class Course
{
	
	private String coursename;
	private float coursefees;
	
	public Course(AddCourse obj)
	{
		
		coursename = obj.getCourseName();
		coursefees = obj.getCourseFees();
		
	}
	
	//Method to add course table
	public boolean addCourse()
	{
		
		Database db = new Database();
		
		String addquery = "INSERT INTO CourseInfo("
				+ "ID, "
				+ "NAME, "
				+ "FEES"
				+ ")"
				+ " VALUE("
				+ "NULL, \'"
				+ coursename + "\',"
				+ coursefees + ")";
		
		if(!db.checkCourse(coursename) && db.addCourse(addquery))
		{
			
			return true;
			
		}
		else
		{
			
			return false;
			
		}
		
	}
	
}
