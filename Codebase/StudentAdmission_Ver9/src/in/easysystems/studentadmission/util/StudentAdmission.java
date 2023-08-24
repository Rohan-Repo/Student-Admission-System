package in.easysystems.studentadmission.util;

import java.time.LocalDate;

public class StudentAdmission 
{

	public int challanNum, studRegNum, courseNum, academicYearFrom, academicYearTo;
	public LocalDate installmentDate;
	public float currentInstallment,balance, courseFees;
	
	public StudentAdmission(int challanNum, int studRegNum, int courseNum,
			int academicYearFrom, int academicYearTo,
			LocalDate installmentDate, float currentInstallment, float balance,
			float courseFees) {

		this.challanNum = challanNum;
		this.studRegNum = studRegNum;
		this.courseNum = courseNum;
		this.academicYearFrom = academicYearFrom;
		this.academicYearTo = academicYearTo;
		this.installmentDate = installmentDate;
		this.currentInstallment = currentInstallment;
		this.balance = balance;
		this.courseFees = courseFees;
	}
	
	 
	
}
