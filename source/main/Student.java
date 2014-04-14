package ims.main;

import ims.gui.AddStudent;

/*
 * Author: Pratyush Sharma
 * Date: 12 March 2014 Wednesday
 * This is the code for student class which represents a student.
 */

public class Student
{
	
	private String name;
	private String gender;
	private int dayofbirth;
	private int monthofbirth;
	private int yearofbirth;
	private String guardianrole;
	private String guardianname;
	private String guardianoccupation;
	private String presentaddress;
	private String presentcity;
	private String presentstate;
	private String presentcountry;
	private String presentphone;
	private String permanentaddress;
	private String permanentcity;
	private String permanentstate;
	private String permanentcountry;
	private String permanentphone;
	private String category;
	private String qualification;
	private String course;
	private double fees;
	private int numberofinstallments;
	private int dayofjoining;
	private int monthofjoining;
	private int yearofjoining;
	
	private float totalfees;
	
	public Student(AddStudent obj)
	{
	
		this.name = obj.getName(); //Get Name
		this.gender = obj.getGender(); //Get Gender
		this.category = obj.getCategory(); //Get Category
		this.course = obj.getCourse(); //Get Course
		this.dayofbirth = obj.getDayOfBirth(); //Get Day of Birth
		this.monthofbirth = obj.getMonthOfBirth(); //Get Month of Birth
		this.yearofbirth = obj.getYearOfBirth(); //Get year of Birth
		this.presentaddress = obj.getPresentAddress(); //Get Present address
		this.presentcity = obj.getPresentCity(); //Get present city
		this.presentphone = obj.getPresentPhone(); //Get Present phone
		this.presentcountry = obj.getPresentCountry(); //Get Present country
		this.presentstate = obj.getPresentState(); //Get present state
		this.guardianrole = obj.getGuardianRole(); //Get Guardian role
		this.guardianname = obj.getGuardianName(); //Get guardian Name
		this.guardianoccupation = obj.getGuardianOccupation(); //Get Guardian occupation
		this.permanentaddress = obj.getPermanentAddress(); //Get Permanent address
		this.permanentcity = obj.getPermanentCity(); //Get Permanent city
		this.permanentstate = obj.getPermanentState(); //Get Permanent state
		this.permanentcountry = obj.getPermanentCountry(); //Get permanent country
		this.permanentphone = obj.getPermanentPhone(); //Get permanent phone
		this.fees = obj.getFees(); //Get fees
		this.numberofinstallments = obj.getNumberOfInstallments(); //Get number of Installments
		this.dayofjoining = obj.getDayOfJoining(); //Get day of joining
		this.monthofjoining = obj.getMonthOfJoining(); //Get month of joining
		this.yearofjoining = obj.getYearOfJoining(); //Get year of joining
		this.qualification = obj.getQualification(); //Get qualification
		
		this.totalfees = obj.getTotalFees(); //Total fees
		
	}
	
	public boolean addStudentToDatabase()
	{
		
		String studentquery = "INSERT INTO Student"
				+ "("
				+ "ID,"
				+ "NAME,"
				+ "GENDER,"
				+ "DATE_OF_BIRTH,"
				+ "GUARDIAN_ROLE,"
				+ "GUARDIAN_NAME,"
				+ "GUARDIAN_OCCUPATION,"
				+ "PRESENT_ADDRESS,"
				+ "PRESENT_CITY,"
				+ "PRESENT_STATE,"
				+ "PRESENT_COUNTRY,"
				+ "PRESENT_PHONE,"
				+ "PERMANENT_ADDRESS,"
				+ "PERMANENT_CITY,"
				+ "PERMANENT_STATE,"
				+ "PERMANENT_COUNTRY,"
				+ "PERMANENT_PHONE,"
				+ "CATEGORY,"
				+ "QUALIFICATION,"
				+ "DATE_OF_JOINING"
				+ ")"
				+ " VALUE"
				+ "("
				+ "NULL, \'"
				+ name +"\', \'"
				+ gender + "\', \'"
				+ yearofbirth + "-" + monthofbirth + "-" + dayofbirth + "\', \'"
				+ guardianrole + "\', \'"
				+ guardianname + "\', \'"
				+ guardianoccupation + "\', \'"
				+ presentaddress + "\', \'"
				+ presentcity + "\', \'"
				+ presentstate + "\', \'"
				+ presentcountry + "\', \'"
				+ presentphone + "\', \'"
				+ permanentaddress + "\', \'"
				+ permanentcity + "\', \'"
				+ permanentstate + "\', \'"
				+ permanentcountry + "\', \'"
				+ permanentphone + "\', \'"
				+ category + "\', \'"
				+ qualification + "\', \'"
				+ yearofjoining + "-" + monthofjoining + "-" + dayofjoining
				+ "\')";
		
		String feequery = "INSERT INTO Fee"
				+ "("
				+ "SID,"
				+ "CID,"
				+ "FEES_PAYED,"
				+ "NUMBER_OF_INSTALLMENTS,"
				+ "TOTAL_FEES"
				+ ") "
				+ "VALUE"
				+ "("
				+ "(SELECT ID FROM Student WHERE NAME = \'" + name + "\' AND GUARDIAN_NAME = \'" + guardianname + "\'),"
						+ "(SELECT ID FROM CourseInfo WHERE NAME = \'" + course + "\'),"
								+ fees + ","
										+ numberofinstallments + ","
												+ totalfees + ")";
		
		
		Database db = new Database();
		
		if(db.insertStudent(studentquery) && db.addToFee(feequery))
		{
			
			return true;
			
		}
		else
		{
			
			return false;
			
		}
	}
	
}
