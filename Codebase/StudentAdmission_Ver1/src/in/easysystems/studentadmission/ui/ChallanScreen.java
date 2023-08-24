package in.easysystems.studentadmission.ui;

import in.easysystems.studentadmission.db.DBOperations;

import in.easysystems.studentadmission.util.CourseInfo;
import in.easysystems.studentadmission.util.StudentAdmission;
import in.easysystems.studentadmission.util.StudentList;

 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
import java.text.ParseException;

import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

public class ChallanScreen 
{
	static int objCnt;
	
	JTextField txtAdmissionYear, txtBankName, txtBranchName, txtDrawerName;
	JLabel lblHeading, lblCollegeName, lblStudName, lblStudCourseName, lblAdmissionYear, lblStudRegNum; 
	JLabel lblFeesInNum, lblFeesInWords, lblParticulars, lblBankName, lblBranchName, lblCopyName, lblCourseNames;
	JLabel lblDrawerName, lblAccountHolder, lblOfficerIncharge, lblCheckedBy, lblDepositorSign, lblStudRegNumVal;
	JLabel lblStudNameVal, lblChallanNumber, lblChallanNumberVal, lblCourseFees, lblCourseFeesVal, lblCurrentInstallment;
	JFormattedTextField txtCurrentInstallment;
	JComboBox<String> particulars;
	
	JButton btnSubmit, btnReset;
	JFrame challanScreen;
	
	public ChallanScreen( StudentList studentList, CourseInfo course ) 
	{
		// TODO Auto-generated constructor stub
		
		objCnt++;
		
		challanScreen = new JFrame( "ChallanScreen" );
		challanScreen.setSize(1000, 750);
		
		String bankName = "Cosmos Bank"+"\n"+ "Sarhad Extension Counter"+"\n"+"Katraj, Pune - 46";
		
		lblHeading = new JLabel( bankName );
		lblHeading.setBounds(10, 10, 500, 30);
		
		lblCollegeName = new JLabel( "Sarhad College Of Arts, Commerce and Science" );
		lblCollegeName.setBounds(10, 50, 400, 30);
		
		lblCopyName = new JLabel( "StudentCopy" );
		lblCopyName.setBounds(10, 80, 400, 30);
		
		lblChallanNumber = new JLabel( "Challan Number :" );
		lblChallanNumber.setBounds( 10, 120, 100, 30 );
		
		lblChallanNumberVal = new JLabel();
		lblChallanNumberVal.setBounds( 200, 120, 100, 30 );
		lblChallanNumberVal.setText( DBOperations.getNextChallanNumber() + "" );
		
		lblStudRegNum = new JLabel( "Registration Number" );
		lblStudRegNum.setBounds( 10, 150, 200, 30 );
		
		lblStudRegNumVal = new JLabel( );
		lblStudRegNumVal.setBounds( 200, 150, 100, 30 );
		lblStudRegNumVal.setText( studentList.studRegNum + "" );
		
		lblStudName = new JLabel( "Name :" );
		lblStudName.setBounds(10, 200, 400, 30);
		
		lblStudNameVal = new JLabel( studentList.studName );
		lblStudNameVal.setBounds(200, 200, 400, 30);
		
		lblStudCourseName = new JLabel( "Course Name :" );
		lblStudCourseName.setBounds(10, 250, 100, 30);
		
		lblCourseNames = new JLabel( studentList.courseName );
		lblCourseNames.setBounds(200, 250, 400, 30);
		
		lblCourseFees = new JLabel( "Course Fees & Balance :" );
		lblCourseFees.setBounds( 10, 300, 150, 30);

		lblCourseFeesVal = new JLabel();
		lblCourseFeesVal.setBounds( 200, 300, 200, 30 );
		lblCourseFeesVal.setText( course.courseFees + "" + "     &  " + studentList.balanceFees );
		
		lblCurrentInstallment = new JLabel( "Current Installment:" );
		lblCurrentInstallment.setBounds( 10, 350, 200, 30 );
		
		try 
		{
			MaskFormatter formatter = null;
			
			if( studentList.balanceFees >= 10000 ) // Greater then 10k
				formatter = new MaskFormatter( "#####" );
			else
			if( studentList.balanceFees >=1000 && studentList.balanceFees <= 9999 ) // Between 1K & 9K
				formatter = new MaskFormatter( "####" );

			txtCurrentInstallment = new JFormattedTextField( formatter );
			txtCurrentInstallment.setBounds( 200, 350, 100, 30 );
		} 
		catch (ParseException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		lblParticulars = new JLabel( "Particulars");
		lblParticulars.setBounds( 10, 400, 200, 30);
		String[] par = { "Cash", "Cheque", "DD" };
		
		particulars = new JComboBox<String>(par);
		particulars.setBounds( 200, 400, 100, 30);
		
		lblBankName = new JLabel( "BankName" );
		lblBankName.setBounds( 10, 450, 100, 30);
		
		txtBankName = new JTextField( 250 );
		txtBankName.setBounds( 200, 450, 250, 30);
		
		lblBranchName = new JLabel( "BranchName" );
		lblBranchName.setBounds(10, 500, 150, 30);
		
		txtBranchName = new JTextField( 250 );
		txtBranchName.setBounds( 200, 500, 250, 30);
		
		lblDrawerName = new JLabel( "Drawer Name :");
		lblDrawerName.setBounds( 10, 550, 100, 30);
		
		txtDrawerName = new JTextField( 250 );
		txtDrawerName.setBounds( 200, 550, 250, 30);
		
		lblBankName.setEnabled(false);
		txtBankName.setEnabled(false);
		lblBranchName.setEnabled(false);
		txtBranchName.setEnabled(false);
		lblDrawerName.setEnabled(false);
		txtDrawerName.setEnabled(false);
		
		btnSubmit = new JButton( "Submit" );
		btnSubmit.setBounds( 20, 650, 100, 30 );
		
		btnReset = new JButton( "Reset" );
		btnReset.setBounds( 170, 650, 100, 30);
	
		challanScreen.add( lblHeading );
		challanScreen.add( lblCollegeName );
		challanScreen.add( lblCopyName );
		challanScreen.add( lblChallanNumber);
		challanScreen.add( lblChallanNumberVal );
		challanScreen.add( lblCourseFees);
		challanScreen.add( lblCourseFeesVal );
		challanScreen.add( lblStudRegNum );
		challanScreen.add( lblStudRegNumVal );
		challanScreen.add( lblStudName );
		challanScreen.add( lblStudNameVal );
		challanScreen.add( lblStudCourseName );
		challanScreen.add( lblCourseNames );
		challanScreen.add( lblCurrentInstallment);
		challanScreen.add( txtCurrentInstallment );
		challanScreen.add(lblParticulars);
		challanScreen.add(particulars);
		challanScreen.add(lblBankName);
		challanScreen.add(txtBankName);
		challanScreen.add(lblBranchName);
		challanScreen.add(txtBranchName);
		challanScreen.add(lblDrawerName);
		challanScreen.add(txtDrawerName);
		challanScreen.add(btnSubmit);
		challanScreen.add( btnReset );
		
		
		btnSubmit.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				/*
				int challanNumber = objCnt;
				
				String studName = txtStudentName.getText();
				
				String studCourse = (String) courseNames.getSelectedItem();
				
				String tf = (String) table.getModel().getValueAt(2, 1);
				int totalFees = Integer.parseInt( tf );
				
				
				String currPaid = (String) table.getModel().getValueAt(3, 1);
				int currentPaid  =  Integer.parseInt( currPaid );
				int balance = totalFees - currentPaid;
				
				String modeOfPayment = (String) particulars.getSelectedItem();
				
				String val = "Challan No. : " + challanNumber + " Stud Name :" + studName +
						     "Course : " + studCourse + "\n Total Fees :" + totalFees +
						     "Currently Paid :" + currentPaid + "Balance :" + balance +
						      "Mode Of Payment :" + modeOfPayment ; 
				
			
				JOptionPane.showMessageDialog( null, val);
				
				*/
				
				int challanNumber = Integer.parseInt( lblChallanNumberVal.getText() );
				int studRegNumber = Integer.parseInt( lblStudRegNumVal.getText() );
				int courseNumber = course.courseNum;
				LocalDate installmentDate = LocalDate.now();
				float currentInstallment = Float.parseFloat( txtCurrentInstallment.getText() );
				
				System.out.println("--------------" + txtCurrentInstallment.getText().replaceAll("-", "") );
				
				float balance = studentList.balanceFees - currentInstallment;

				StudentAdmission admission = new StudentAdmission(challanNumber, studRegNumber, courseNumber, installmentDate, currentInstallment, balance );
				boolean success = DBOperations.depositFees( admission );
				
				if( success )
				{
					JOptionPane.showMessageDialog( challanScreen, "Insertion Successful", "Insertion Status", JOptionPane.INFORMATION_MESSAGE );
					challanScreen.dispose();
				}
				else
					JOptionPane.showMessageDialog( challanScreen, "ERROR - Kindly Re-Enter the Details", "Insertion Status", JOptionPane.ERROR_MESSAGE );
				
			}
		});		
		
		btnReset.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				 
			}
		});
		
		
		particulars.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if( particulars.getSelectedIndex() != 0 )
				{
					lblBankName.setEnabled(true);
					txtBankName.setEnabled(true);
					lblBranchName.setEnabled(true);
					txtBranchName.setEnabled(true);
					lblDrawerName.setEnabled(true);
					txtDrawerName.setEnabled(true);
					
				}
				
			}
		});
		
		challanScreen.setLayout( null );
		challanScreen.setVisible( true );
		challanScreen.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		
	}
	
	
	
	
	
}
