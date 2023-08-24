package in.easysystems.studentadmission.util;

public class StudentInfo 
{
	
	public int studRegNum;
	public String studName, studContactNumber, studEMailID;
	public StudentQualifications studentQualification;
	
	public StudentInfo(int studRegNum, String studName,
			String studContactNumber, String studEMailID,
			StudentQualifications studentQualification )
	{
		this.studRegNum = studRegNum;
		this.studName = studName;
		this.studContactNumber = studContactNumber;
		this.studEMailID = studEMailID;
		this.studentQualification = studentQualification;
	}

}
