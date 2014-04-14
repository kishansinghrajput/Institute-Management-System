package ims.main;

import java.sql.*;
import java.io.*;

/*
 * Author: Pratyush	Sharma
 * Date: 12th March 2014 Wednesday
 * This is the code for all kind of Database operations.
 */

public class Database
{
	
	private String DB_URL;
	private String databasename;
	private String username;
	private String password;
	private String JDBC_DRIVER;
	
	public Database()
	{
		if(checkFile())
		{
			initializeComponent();
		}
	}
	
	//Initialize database url, user name and password
	private void initializeComponent()
	{
		try
		{
			File file = new File(System.getProperty("user.dir") + "/Settings/Database");
			
			BufferedReader br = new BufferedReader(new FileReader(file));
			
			JDBC_DRIVER = "com.mysql.jdbc.Driver";
			Class.forName(JDBC_DRIVER);
			databasename = br.readLine(); //Get database name
			DB_URL = "jdbc:mysql://localhost/"; //mysql database on local host
			username = br.readLine(); //Get user name
			password = br.readLine(); //Get password
			
			br.close();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	
	//Check if Database file exists
	private boolean checkFile()
	{	
		try
		{
			File file = new File(System.getProperty("user.dir") + "/Settings/Database");
			
			if(file.exists())
				return true;
			else
				return false;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	
	//Method to create database
	public void createDatabase()
	{
		try
		{
			Connection con = DriverManager.getConnection(DB_URL, username, password); //Get Connection
			
			String databasequery = "CREATE Database " + databasename; //Create query
			String usequery = "USE " + databasename; //Use query
			
			String coursetablequery = "CREATE TABLE CourseInfo(ID int UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY, "
					+ "NAME varchar(40), FEES float)"; //Course table query
			
			String studenttablequery = "CREATE TABLE Student"
					+ "("
					+ "ID int UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,"
					+ "NAME varchar(50),"
					+ "GENDER varchar(7),"
					+ "DATE_OF_BIRTH date,"
					+ "GUARDIAN_ROLE varchar(8),"
					+ "GUARDIAN_NAME varchar(50),"
					+ "GUARDIAN_OCCUPATION varchar(50),"
					+ "PRESENT_ADDRESS varchar(180),"
					+ "PRESENT_CITY varchar(40),"
					+ "PRESENT_STATE varchar(40),"
					+ "PRESENT_COUNTRY varchar(40),"
					+ "PRESENT_PHONE varchar(15),"
					+ "PERMANENT_ADDRESS varchar(180),"
					+ "PERMANENT_CITY varchar(40),"
					+ "PERMANENT_STATE varchar(40),"
					+ "PERMANENT_COUNTRY varchar(40),"
					+ "PERMANENT_PHONE varchar(15),"
					+ "CATEGORY varchar(8),"
					+ "QUALIFICATION varchar(50),"
					+ "DATE_OF_JOINING date"
					+ ")"; //Student table query
			
			String feetablequery = "CREATE TABLE Fee"
					+ "("
					+ "SID int NOT NULL,"
					+ "CID int NOT NULL,"
					+ "FEES_PAYED float,"
					+ "NUMBER_OF_INSTALLMENTS int,"
					+ "TOTAL_FEES float"
					+ ")";
			
			Statement stmt = con.createStatement(); //Create statement
			
			stmt.execute(databasequery); //Execute query
			stmt.execute(usequery);
			stmt.execute(coursetablequery);
			stmt.execute(studenttablequery);
			stmt.execute(feetablequery);
			
			stmt.close(); //Close statement
			con.close(); //Close connection
		}
		catch(SQLException e)
		{
			System.out.println(e);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	
	//Insert student
	public boolean insertStudent(String query)
	{
		try
		{
			//Create connection
			Connection con = DriverManager.getConnection(DB_URL + databasename, username, password);
			
			//Create statement
			Statement stmt = con.createStatement();
			
			stmt.executeUpdate(query);
			
			stmt.close(); //Close statement
			con.close(); //Close connection
			
			return true;
		}
		catch(SQLException e)
		{
			System.out.println();
			return false;
		}
		catch(Exception e)
		{
			System.out.println(e);
			return false;
		}
	}
	
	//Add student id to appropriate course
	public boolean addToFee(String query)
	{
		try
		{
			//Create connection
			Connection con = DriverManager.getConnection(DB_URL + databasename, username, password);
			
			//Create statement
			Statement stmt = con.createStatement();
			
			stmt.executeUpdate(query);
			
			stmt.close(); //Close statement
			con.close(); //Close connection
			
			return true;
		}
		catch(SQLException e)
		{
			System.out.println(e);
			return false;
		}
		catch(Exception e)
		{
			System.out.println(e);
			return false;
		}
	}
	
	//Method to check if course is already available
	public boolean checkCourse(String coursename)
	{
		String query = "SELECT ID FROM CourseInfo WHERE NAME = \'" + coursename + "\'";
		boolean isAvailable = false;
		
		try
		{
			//Create connection
			Connection con = DriverManager.getConnection(DB_URL + databasename, username, password);
			
			//Create statement
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery(query);
			
			if(rs.next())
			{
				isAvailable = true;
			}
			else
			{
				isAvailable = false;
			}
			
			stmt.close(); //Close statement
			con.close(); //Close connection
			rs.close();
			
			return isAvailable;
		}
		catch(SQLException e)
		{
			System.out.println(e);
			return false;
		}
		catch(Exception e)
		{
			System.out.println(e);
			return false;
		}
	}
	
	
	//Method for getting course names
	public String[] getCourses()
	{
		String countquery = "SELECT COUNT(ID) AS COUNT FROM CourseInfo";
		String coursequery = "SELECT NAME FROM CourseInfo";
		String courses[];
		
		try
		{
			//Create connection
			Connection con = DriverManager.getConnection(DB_URL + databasename, username, password);
			
			//Create statement
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery(countquery); //Execute query for getting course count
			
			if(!rs.next())
			{
				return null;
			}
			courses = new String[rs.getInt("COUNT")];
			rs.close();
			
			int i = 0;
			
			rs = stmt.executeQuery(coursequery); //Execute query for getting course names
			
			while(rs.next())
			{
				courses[i++] = rs.getString("NAME");
			}
			
			stmt.close(); //Close statement
			con.close(); //Close connection
			rs.close(); //Close result set
			
			return courses;
		}
		catch(SQLException e)
		{
			System.out.println(e);
			return null;
		}
		catch(Exception e)
		{
			System.out.println(e);
			return null;
		}
	}
	
	
	//Method to add course to CourseInfo table
	public boolean addCourse(String query)
	{
		try
		{
			//Create connection
			Connection con = DriverManager.getConnection(DB_URL + databasename, username, password);
			
			//Create statement
			Statement stmt = con.createStatement();
			
			stmt.executeUpdate(query);
			
			stmt.close(); //Close statement
			con.close(); //Close connection
			
			return true;
		}
		catch(SQLException e)
		{
			System.out.println(e);
			return false;
		}
		catch(Exception e)
		{
			System.out.println(e);
			return false;
		}
		
	}
	
	
	//Get course fee
	public float getCoursefees(String coursename)
	{
		String query = "SELECT FEES FROM CourseInfo WHERE NAME = \'" + coursename + "\'";
		float fees = 0.0f;
		
		try
		{
			Connection con = DriverManager.getConnection(DB_URL + databasename, username, password);
			
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery(query);
			
			if(rs.next())
			{
				fees = rs.getFloat("FEES");
			}
			
			con.close();
			stmt.close();
			rs.close();
			
			return fees;
		}
		catch(Exception e)
		{
			System.out.println(e);
			return 0.0f;
		}
	}
	
	
	public Object[][] getCourseData()
	{
		String query = "SELECT NAME, FEES FROM CourseInfo ORDER BY NAME";
		Object data[][];
		
		try
		{
			Connection con = DriverManager.getConnection(DB_URL + databasename, username, password);
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery(query);
			
			int col = rs.getMetaData().getColumnCount();
			int row = 0;
			
			while(rs.next())
			{
				row++;
			}
			
			data = new Object[row][col];
			rs.beforeFirst();
			for(int i = 0; i < row && rs.next(); i++)
			{
				data[i][0] = rs.getObject("NAME");
				data[i][1] = rs.getObject("FEES");
			}
			
			stmt.close();
			con.close();
			rs.close();
			
			return data;
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	
	//Update course name
	public void updateCourseName(String oldcoursename, String newcoursename) throws SQLException
	{
		String query = "UPDATE CourseInfo SET NAME = \'" + newcoursename + "\' WHERE NAME = \'" + oldcoursename + "\'";
		
		Connection con = DriverManager.getConnection(DB_URL + databasename, username, password);
		Statement stmt = con.createStatement();
		stmt.executeUpdate(query);
		
		stmt.close();
		con.close();
	}
	
	
	//Update course fees
	public void updateCourseFees(String coursename, float newfees) throws SQLException
	{
		String query = "UPDATE CourseInfo SET FEES = " + newfees + " WHERE NAME = \'" + coursename + "\'";
		
		Connection con = DriverManager.getConnection(DB_URL + databasename, username, password);
		Statement stmt = con.createStatement();
		stmt.executeUpdate(query);
		
		stmt.close();
		con.close();
	}
	
	
	//Delete course
	public void deleteCourse(String coursename) throws SQLException
	{
		String query = "DELETE FROM CourseInfo WHERE NAME = \'" + coursename + "\'";
		
		Connection con = DriverManager.getConnection(DB_URL + databasename, username, password);
		Statement stmt = con.createStatement();
		stmt.executeUpdate(query);
		
		stmt.close();
		con.close();
	}
	
	
	//Select all student from table
	public Object[][] getAllStudent() throws SQLException
	{
		String query = "SELECT * FROM Student";
		
		Connection con = DriverManager.getConnection(DB_URL + databasename, username, password);
		Statement stmt = con.createStatement();
		
		ResultSet rs = stmt.executeQuery(query);
		
		int col = rs.getMetaData().getColumnCount();
		int row = 0;
		
		while(rs.next())
		{
			row++;
		}
		
		Object[][] data = new Object[row][col];
		
		rs.beforeFirst();
		
		for(int i = 0; i < row && rs.next(); i++)
		{
			for(int j = 1; j <= col; j++)
			{
				data[i][j - 1] = rs.getObject(j);
			}
		}
		
		rs.close();
		stmt.close();
		con.close();
		
		return data;
	}
	
	
	//Get columns
	public String[] getColumnNameFromStudent() throws SQLException
	{
		String query = "DESCRIBE Student";
		
		Connection con = DriverManager.getConnection(DB_URL + databasename, username, password);
		Statement stmt = con.createStatement();
		
		ResultSet rs = stmt.executeQuery(query);
		
		int row = 0;
		
		while(rs.next())
		{
			row++;
		}
		
		String names[] = new String[row];
		
		rs.beforeFirst();
		
		for(int i = 0; i < row && rs.next(); i++)
		{
			names[i] = rs.getString(1);
		}
		
		rs.close();
		stmt.close();
		con.close();
		
		return names;
	}
	
	
	//Get some fields from students
	public Object[][] getSomeFieldsFromStudent() throws SQLException
	{
		String query = "SELECT ID, NAME, GENDER, GUARDIAN_ROLE, GUARDIAN_NAME, PRESENT_ADDRESS, PRESENT_CITY, PRESENT_PHONE FROM Student";
		
		Connection con = DriverManager.getConnection(DB_URL + databasename, username, password);
		Statement stmt = con.createStatement();
		
		ResultSet rs = stmt.executeQuery(query);
		
		int col = rs.getMetaData().getColumnCount();
		int row = 0;
	
		while(rs.next())
		{
			row++;
		}
		
		Object[][] data = new Object[row][col];
		
		rs.beforeFirst();
		
		for(int i = 0; i < row && rs.next(); i++)
		{
			for(int j = 1; j <= col; j++)
			{
				data[i][j - 1] = rs.getObject(j);
			}
		}
		
		rs.close();
		stmt.close();
		con.close();
		
		return data;
	}
	
	
	//Get total students in table
	public int getTotalStudent() throws SQLException
	{
		String query = "SELECT COUNT(ID) AS TOTAL FROM Student";
		
		Connection con = DriverManager.getConnection(DB_URL + databasename, username, password);
		Statement stmt = con.createStatement();
		
		ResultSet rs = stmt.executeQuery(query);
		
		rs.next();
		
		int total = rs.getInt("TOTAL");
		
		rs.close();
		stmt.close();
		con.close();
		
		return total;
	}
	
	
	public Object[][] getData(String query) throws SQLException
	{
		Connection con = DriverManager.getConnection(DB_URL + databasename, username, password);
		Statement stmt = con.createStatement();
		
		ResultSet rs = stmt.executeQuery(query);
		
		int col = rs.getMetaData().getColumnCount();
		int row = 0;
		
		while(rs.next())
		{
			row++;
		}
		
		Object[][] data = new Object[row][col];
		
		rs.beforeFirst();
		
		for(int i = 0; i < row && rs.next(); i++)
		{
			for(int j = 1; j <= col; j++)
			{
				data[i][j - 1] = rs.getObject(j);
			}
		}
		
		rs.close();
		stmt.close();
		con.close();
		
		return data;
	}
	
	
	//Fees payed method
	public float getFeesPayed(int id) throws SQLException
	{
		String query = "SELECT FEES_PAYED FROM Fee WHERE SID = " + id;
		
		Connection con = DriverManager.getConnection(DB_URL + databasename, username, password);
		Statement stmt = con.createStatement();
		
		ResultSet rs = stmt.executeQuery(query);
		
		rs.next();
		
		float fees = rs.getFloat("FEES_PAYED");
		
		rs.close();
		stmt.close();
		con.close();
		
		return fees;
	}
	
	
	//get data from fees table
	public Object[][] getFeeData(long id) throws SQLException
	{
		String query = "SELECT CourseInfo.NAME, FEES_PAYED, TOTAL_FEES, NUMBER_OF_INSTALLMENTS FROM Fee LEFT JOIN CourseInfo ON Fee.CID = CourseInfo.ID WHERE SID = " + id;
		
		Connection con = DriverManager.getConnection(DB_URL + databasename, username, password);
		Statement stmt = con.createStatement();
		
		ResultSet rs = stmt.executeQuery(query);
		
		int col = rs.getMetaData().getColumnCount();
		int row = 0;
		
		while(rs.next())
		{
			row++;
		}
		
		Object[][] data = new Object[row][col];
		
		rs.beforeFirst();
		
		for(int i = 0; i < row && rs.next(); i++)
		{
			for(int j = 1; j <= col; j++)
			{
				data[i][j - 1] = rs.getObject(j);
			}
		}
		
		rs.close();
		stmt.close();
		con.close();
		
		return data;
	}
	
	
	//Add fee method
	public void addFees(float fees, long sid, String course) throws SQLException
	{
		String query = "UPDATE Fee SET FEES_PAYED = FEES_PAYED + " + fees + " WHERE SID = " + sid + " AND CID = (SELECT ID FROM CourseInfo WHERE NAME = \'" + course + "\')";
		
		Connection con = DriverManager.getConnection(DB_URL + databasename, username, password);
		Statement stmt = con.createStatement();
		stmt.executeUpdate(query);
		
		con.close();
		stmt.close();
	}
	
	
	//Add course to current student
	public void addCourseToCurrentStudent(long sid, float totalfees, int numberofinstallments, String course) throws SQLException
	{
		String query = "INSERT INTO Fee(SID, CID, FEES_PAYED, TOTAL_FEES, NUMBER_OF_INSTALLMENTS) VALUE("
				+ sid + ",(SELECT ID FROM CourseInfo WHERE NAME = \'" + course + "\'),"
						+ 0 + ","
								+ totalfees + ","
										+ numberofinstallments + ")";
		
		Connection con = DriverManager.getConnection(DB_URL + databasename, username, password);
		Statement stmt = con.createStatement();
		
		stmt.executeUpdate(query);
		
		con.close();
		stmt.close();
	}
}
