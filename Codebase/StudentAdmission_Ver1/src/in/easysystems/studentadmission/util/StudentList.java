package in.easysystems.studentadmission.util;

public class StudentList 
{
	public int studRegNum;
	public String studName, courseName;
	public float balanceFees;
	
	public StudentList( int studRegNum, String studName, String courseName, float balanceFees) 
	{
		this.studRegNum = studRegNum;
		this.studName = studName;
		this.courseName = courseName;
		this.balanceFees = balanceFees;
	}
}
