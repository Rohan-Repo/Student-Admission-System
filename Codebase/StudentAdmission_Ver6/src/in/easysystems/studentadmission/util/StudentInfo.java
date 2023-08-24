package in.easysystems.studentadmission.util;



public class StudentInfo 
{
	
	public int studRegNum;
	public String studName, studContactNumber, studEMailID, studAddr1, studAddr2, studAddr3;
	public StudentQualifications studentQualification;
	public String scholarshipEligibility;
	public int studPercentConcession, academicYear_from, academicYear_to, courseNum;
	
	public StudentInfo(int studRegNum, String studName,
			String studContactNumber, String studEMailID, String studAddr1,
			String studAddr2, String studAddr3,
			String scholarshipEligibility, int studPercentConcession,
			int academicYear_from, int academicYear_to, int courseNum, StudentQualifications studentQualification) 
	{

		this.studRegNum = studRegNum;
		this.studName = studName;
		this.studContactNumber = studContactNumber;
		this.studEMailID = studEMailID;
		this.studAddr1 = studAddr1;
		this.studAddr2 = studAddr2;
		this.studAddr3 = studAddr3;
		this.studentQualification = studentQualification;
		this.scholarshipEligibility = scholarshipEligibility;
		this.studPercentConcession = studPercentConcession;
		this.academicYear_from = academicYear_from;
		this.academicYear_to = academicYear_to;
		this.courseNum = courseNum;
	}

	
 
}
