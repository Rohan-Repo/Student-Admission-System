package in.easysystems.studentdetails.baseclasses;

public class Student 
{
	String studentName, courseName;
	float studTenthPercentage, studTwelthPercentage, studGraduationPercentage;
	float studPostGraduationPercentage, studentFine;
	
	public Student( String studentName, String courseName,
			float studTenthPercentage, float studTwelthPercentage,
			float studGraduationPercentage, float studPostGraduationPercentage,
			float studentFine ) 
	{
		this.studentName = studentName;
		this.courseName = courseName;
		this.studTenthPercentage = studTenthPercentage;
		this.studTwelthPercentage = studTwelthPercentage;
		this.studGraduationPercentage = studGraduationPercentage;
		this.studPostGraduationPercentage = studPostGraduationPercentage;
		this.studentFine = studentFine;
	}
	
	
}
