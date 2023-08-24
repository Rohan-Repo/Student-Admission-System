package in.easysystems.studentadmission.util;

public class CourseInfo 
{
	
	public int courseNum, courseDuration;
	public String courseName;
	public float courseFees;
	
	public CourseInfo() 
	{
		this.courseNum = -100;
		this.courseDuration = -100;
		this.courseName = "";
		this.courseFees = -100;
	}



	public CourseInfo(int courseNum, int courseDuration, String courseName, float courseFees) 
	{
	
		this.courseNum = courseNum;
		this.courseDuration = courseDuration;
		this.courseName = courseName;
		this.courseFees = courseFees;
	}
	
}
