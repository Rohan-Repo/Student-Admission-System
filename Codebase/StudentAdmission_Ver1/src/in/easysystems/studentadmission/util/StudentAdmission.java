package in.easysystems.studentadmission.util;

import java.time.LocalDate;

public class StudentAdmission 
{

	public int challanNum, studRegNum, courseNum;
	public LocalDate installmentDate;
	public float currentInstallment,balance;
	
	public StudentAdmission( int challanNum, int studRegNum, int courseNum, 
							 LocalDate installmentDate, float currentInstallment, float balance ) 
	{
	
		this.challanNum = challanNum;
		this.studRegNum = studRegNum;
		this.courseNum = courseNum;
		this.installmentDate = installmentDate;
		this.currentInstallment = currentInstallment;
		this.balance = balance;
		
	}
	
}
