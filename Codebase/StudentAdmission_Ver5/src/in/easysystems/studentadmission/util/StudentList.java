package in.easysystems.studentadmission.util;

public class StudentList 
{
	public int studRegNum;
	public String studName, courseName;
	public float balanceFees;
	public char concessionEligible;
	public int concessionPercentage;
	
	public StudentList(int studRegNum, String studName, String courseName,
			float balanceFees, char concessionEligible, int concessionPercentage) {

		this.studRegNum = studRegNum;
		this.studName = studName;
		this.courseName = courseName;
		this.balanceFees = balanceFees;
		this.concessionEligible = concessionEligible;
		this.concessionPercentage = concessionPercentage;
	}
	
	 
}
