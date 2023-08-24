package in.easysystems.studentadmission.ui;

import in.easysystems.studentadmission.db.DBOperations;
import in.easysystems.studentadmission.util.CourseInfo;
import in.easysystems.studentadmission.util.StudentInfo;
import in.easysystems.studentadmission.util.StudentQualifications;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.event.ChangeListener;
import javax.swing.text.Caret;
import javax.swing.text.JTextComponent;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;

public class AddStudentInfoScreen extends JDialog
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	JDialog dialogStudInfo;
	JLabel lblStudRegNum, lblStudRegNumVal,lblStudName, lblStudContactNum, lblStudEMail, lblEligibleForConcession, lblFrom, lblTo;
	JLabel lblStudCourse, lblTenthPer,lblTwelfthPer, lblGradPer, lblPostGradPer, lblTitle, lblConcessionPer, lblAcademicYear;
	JLabel lblAddress, lblLineOne, lblLineTwo, lblLineThree;
	JTextField txtStudName, txtStudEMail, txtLineOne, txtLineTwo, txtLineThree;
	JCheckBox chkGradPer, chkPostGradPer;
	JRadioButton radioConcessionYes, radioConcessionNo;
	JComboBox<String> comboStudCourse, comboYearStart, comboYearEnd, comboConcessionPer;
	JButton btnSubmit, btnReset, btnBack;
	MaskFormatter numberFormatter,percentageFormatter;
	NumberFormatter numberFormatter2;
	JFormattedTextField txtStudContactNum, txtTenthPer,txtTwelfthPer, txtGradPer, txtPostGradPer;
	ArrayList<CourseInfo> courseList;
	String[] cList, academicYearStartArr, academicYearEndArr,  percentageConcessionArr;

	public AddStudentInfoScreen( int studentRegID ) 
	{

		setSize( 1000, 720 );
		setModal(true);
		this.setTitle("Student Entry Form");

		btnBack = new JButton( new ImageIcon("D:\\IT\\backIcon_blue.gif") );
		btnBack.setBounds( 10, 10, 20, 20 );

		lblTitle = new JLabel( "Student Entry Form" );
		lblTitle.setFont( new Font("arial", Font.BOLD, 20) );
		lblTitle.setBounds( 300, 10, 300, 30 );

		Font marksFont = new Font("Arial", Font.BOLD, 12 );

		lblStudRegNum = new JLabel( "Registration Number" );
		lblStudRegNum.setBounds( 10, 50, 200, 30);
		lblStudRegNum.setFont(marksFont);

		lblStudRegNumVal = new JLabel();
		lblStudRegNumVal.setBounds( 150, 50, 100, 30);
		lblStudRegNumVal.setText( studentRegID + "" );
		lblStudRegNumVal.setFont(marksFont);

		try 
		{
			lblStudName= new JLabel( "Name *" );
			lblStudName.setBounds( 10, 100, 100, 30);

			txtStudName= new JTextField( 300 );
			txtStudName.setBounds( 150, 100, 300, 30);
			txtStudName.setText("");

			lblStudCourse = new JLabel( "Student Course *" );
			lblStudCourse.setBounds( 10, 150, 150, 30 );

			courseList = DBOperations.getCourseList();

			cList = new String[ courseList.size() ];

			int i=0;

			for ( CourseInfo c : courseList ) 
			{
				cList[i] = new String( c.courseName );
				i++;				
			}

			comboStudCourse = new JComboBox<String>( cList );
			comboStudCourse.setBounds( 150, 150, 100, 30 );		

			lblStudContactNum= new JLabel( "Contact Number *" );
			lblStudContactNum.setBounds( 10, 200, 200, 30);

			numberFormatter = new MaskFormatter( "##########" );
			numberFormatter.setValidCharacters("0123456789");

			txtStudContactNum= new JFormattedTextField( numberFormatter );
			txtStudContactNum.setBounds( 150, 200, 200, 30);
			txtStudContactNum.setText("");
			
			lblStudEMail = new JLabel( "Email ID : *" );
			lblStudEMail.setBounds( 10, 250, 100, 30);

			txtStudEMail = new JTextField( 300 );
			txtStudEMail.setBounds( 150, 250, 300, 30);
			txtStudEMail.setText("");

			lblAddress = new JLabel("Address : *" );
			lblAddress.setBounds( 10, 300, 70, 30 );

			lblLineOne = new JLabel( "Line 1 :" );
			lblLineOne.setBounds( 90, 300, 50, 30 );

			txtLineOne = new JTextField(200);
			txtLineOne.setBounds( 150, 300, 200, 30 );
			txtLineOne.setText("");
			
			lblLineTwo = new JLabel( "Line 2 : " );
			lblLineTwo.setBounds( 370, 300, 50, 30 );

			txtLineTwo = new JTextField(200);
			txtLineTwo.setBounds( 430, 300, 200, 30 );
			txtLineTwo.setText("");
			
			lblLineThree = new JLabel( "Line 3 :" );
			lblLineThree.setBounds( 650, 300, 50, 30 );

			txtLineThree = new JTextField(200);
			txtLineThree.setBounds( 720, 300, 200, 30 );
			txtLineThree.setText("");
			
			academicYearStartArr = new String[6];
			academicYearEndArr = new String[6];

			// For Initializing Academic Year 
			int y = 2014;

			for( i=0; i<6; i++ )
			{
				academicYearStartArr[i] = (y+i) + "";
				academicYearEndArr[i] = (y+i+1) + "";
			}

			lblAcademicYear = new JLabel( " Academic Year : *" );
			lblAcademicYear.setBounds( 10, 370, 100, 30 );

			lblFrom = new JLabel("From : " );
			lblFrom.setBounds( 150, 370, 50, 30 );

			comboYearStart = new JComboBox<String>(academicYearStartArr);
			comboYearStart.setBounds( 210, 370, 70, 30 );

			lblTo = new JLabel("To : ");
			lblTo.setBounds( 320, 370, 70, 30 );

			comboYearEnd = new JComboBox<String>(academicYearEndArr);
			comboYearEnd.setBounds( 360, 370, 70, 30 );

			lblEligibleForConcession = new JLabel("Is Student Eligible For Concession ?" );
			lblEligibleForConcession.setBounds( 10, 420, 200, 30 );

			radioConcessionYes = new JRadioButton("Yes");
			radioConcessionYes.setBounds( 250, 420, 70, 30 );

			radioConcessionNo = new JRadioButton("No");
			radioConcessionNo.setBounds( 350, 420, 70, 30 );
			radioConcessionNo.setSelected(true);


			// Percentage Concession InitialiZation
			percentageConcessionArr = new String[10];

			for( i=0; i<10; i++ )
				percentageConcessionArr[i] = (i+1) + "0 %";


			lblConcessionPer = new JLabel( "Concession %" );
			lblConcessionPer.setBounds( 480, 420, 100, 30 );

			comboConcessionPer = new JComboBox<String>(percentageConcessionArr);
			comboConcessionPer.setBounds( 640, 420, 70, 30 );

			lblConcessionPer.setEnabled(false);
			comboConcessionPer.setEnabled(false);


			// Percentage Formatter
			percentageFormatter = new MaskFormatter( "##.##" );

			lblTenthPer = new JLabel( "Tenth Percentage*" );
			lblTenthPer.setBounds( 70, 480, 200, 30 );
			lblTenthPer.setFont(marksFont);

			txtTenthPer = new JFormattedTextField( percentageFormatter );
			txtTenthPer.setBounds( 250, 480, 100, 30);
			txtTenthPer.setToolTipText( "Insert Your Tenth Percentage in xx.xx format \n "
					+ "  For Eg. 78% then Input 78.00" );

			lblTwelfthPer = new JLabel( "Twelfth Percentage*" );
			lblTwelfthPer.setBounds( 480, 480, 200, 30 );
			lblTwelfthPer.setFont(marksFont);

			txtTwelfthPer = new JFormattedTextField( percentageFormatter );
			txtTwelfthPer.setBounds( 640, 480, 100, 30);
			txtTwelfthPer.setToolTipText( "Insert Your Twelfth Percentage in xx.xx format \n "
					+ "  For Eg. 78% then Input 78.00" );


			chkGradPer = new JCheckBox( "UG" );
			chkGradPer.setBounds( 10, 520, 50, 30);

			lblGradPer = new JLabel( "Graduation Percentage" );
			lblGradPer.setBounds( 70, 520, 200, 30 );
			lblGradPer.setFont(marksFont);

			txtGradPer = new JFormattedTextField( percentageFormatter );
			txtGradPer.setBounds( 250, 520, 100, 30);
			txtGradPer.setToolTipText( "Insert Your Graduation Percentage in xx.xx format \n "
					+ "  For Eg. 78% then Input 78.00" );


			txtGradPer.setEnabled(false);

			chkPostGradPer = new JCheckBox( "PG" );
			chkPostGradPer.setBounds( 400, 520, 50, 30);

			lblPostGradPer = new JLabel( " PostGrad. Percentage" );
			lblPostGradPer.setBounds( 480, 520, 150, 30 );
			lblPostGradPer.setFont(marksFont);

			txtPostGradPer = new JFormattedTextField( percentageFormatter );
			txtPostGradPer.setBounds( 640, 520, 100, 30);
			txtPostGradPer.setToolTipText( "Insert Your Post-Grad Percentage in xx.xx format \n "
					+ "  For Eg. 78% then Input 78.00" );

			txtPostGradPer.setEnabled(false);


			btnSubmit = new JButton( "Sumbit" );
			btnSubmit.setBounds( 100, 620, 100, 30);

			btnReset = new JButton( "Reset" );
			btnReset.setBounds( 300, 620, 100, 30);

			  
			JLabel lblNote = new JLabel("* Marked Fields Are Compulsory...");
			lblNote.setBounds( 630, 650, 300, 30 );
			lblNote.setFont( new Font("Arial", Font.BOLD, 14) );

			add( lblNote );
			this.add(lblTitle);
			this.add( lblStudRegNum );
			this.add( lblStudRegNumVal );
			this.add( lblStudName );
			this.add( txtStudName );
			this.add( lblStudCourse );
			this.add( comboStudCourse );
			this.add( lblStudContactNum );
			this.add( txtStudContactNum );

			this.add( lblEligibleForConcession );
			this.add( radioConcessionNo );
			this.add( radioConcessionYes );
			this.add( lblAcademicYear );
			this.add( lblFrom );
			this.add( comboYearStart );
			this.add( lblTo );
			add(lblConcessionPer);
			add(comboConcessionPer);
			this.add( comboYearEnd );
			this.add( lblStudEMail );
			this.add( txtStudEMail );
			this.add( lblTenthPer );
			this.add( txtTenthPer );
			add( lblAddress );
			add( lblLineOne );
			add( txtLineOne );
			add( lblLineTwo );
			add( txtLineTwo );
			add( lblLineThree );
			add( txtLineThree );

			this.add( lblTwelfthPer );
			this.add( txtTwelfthPer );

			this.add( chkGradPer );
			this.add( lblGradPer );
			this.add( txtGradPer );
			this.add( chkPostGradPer );
			this.add( lblPostGradPer );
			this.add( txtPostGradPer );


			this.add( btnBack );
			this.add( btnSubmit );
			this.add( btnReset );


			txtStudName.setText( "" );
			txtStudContactNum.setText("");
			txtStudEMail.setText("");




		}
		catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		btnSubmit.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{

				
				int id = Integer.parseInt( lblStudRegNumVal.getText() );
				String name = txtStudName.getText();
				String contactNum = txtStudContactNum.getText();

				if( name.equals("") || contactNum.equals("") )
				{
					JOptionPane.showMessageDialog( AddStudentInfoScreen.this, "Enter Name & Contact Number", "ERROR", JOptionPane.ERROR_MESSAGE );
					txtStudName.setText("");
					txtStudContactNum.setText("");
					return;
				}
				
				// Check EMail
				String email = txtStudEMail.getText();
				if( checkEMail(email) )
				{

					float tenthPer = 0,twelfthPer = 0;
					
					System.out.println( "======" + txtTenthPer.getText() + "    " + txtTwelfthPer.getText() );
					
					if( !(txtTenthPer.getText().contentEquals(".") && txtTwelfthPer.getText().contentEquals(".")) )
					{
						tenthPer = Float.parseFloat( txtTenthPer.getText() );
					 	twelfthPer = Float.parseFloat( txtTwelfthPer.getText() );
					}
					
					String addr1 = txtLineOne.getText();
					String addr2 = txtLineTwo.getText();
					String addr3 = txtLineThree.getText();
					int yearStart = Integer.parseInt( comboYearStart.getSelectedItem().toString() );
					int yearEnd = Integer.parseInt( comboYearEnd.getSelectedItem().toString() );
					int concessionPer = 0;
					String concessionEligb = "N";

					if( (addr1.length() > 100) || (addr2.length() > 100) || (addr3.length() > 100) )
					{
						txtLineOne.setText("");
						txtLineThree.setText("");
						txtLineTwo.setText("");

						JOptionPane.showMessageDialog( AddStudentInfoScreen.this, "Address Exceeds 100 Characters, Kindly ReEnter", "Error Message", JOptionPane.ERROR_MESSAGE );
						return;

					}

					if( radioConcessionYes.isSelected() )
					{
						concessionEligb = "Y";
						concessionPer = Integer.parseInt( comboConcessionPer.getSelectedItem().toString().replaceAll(" %", "") );
					}


					float gradPer = 0, postGradPer = 0;

					if( chkGradPer.isSelected() )
						gradPer = Float.parseFloat( txtGradPer.getText() );
					if( chkPostGradPer.isSelected() )
						postGradPer = Float.parseFloat( txtPostGradPer.getText() );

					StudentQualifications qualifications = new StudentQualifications( tenthPer, twelfthPer, gradPer, postGradPer );

					int selCourseNum = comboStudCourse.getSelectedIndex();
					CourseInfo courseInfo = courseList.get( selCourseNum );
					int courseNum = courseInfo.courseNum;

					StudentInfo studentInfo = new StudentInfo( id, name, contactNum, email, addr1, addr2, addr3, concessionEligb, concessionPer, yearStart, yearEnd, courseNum, qualifications );

					boolean val = DBOperations.addStudentInfo( studentInfo );

					if( val )
					{
						// Successfull Insertion
						AddStudentInfoScreen.this.dispose();
					}
					else
					{
						JOptionPane.showMessageDialog( AddStudentInfoScreen.this, "Kindly Re-Enter the Details", "ErrorMessage",  JOptionPane.ERROR_MESSAGE );
						return;
					}
				}
				else
				{
					JOptionPane.showMessageDialog( AddStudentInfoScreen.this, "InCorrect EMail ID Entered", "ERROR", JOptionPane.ERROR_MESSAGE );
					txtStudEMail.setText("");
					return;
				}


			}
		});

		btnReset.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{

				txtStudName.setText( "" );
				txtStudContactNum.setText("");
				txtStudEMail.setText("");
				txtTenthPer.setText("");
			}
		});

		btnBack.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				AddStudentInfoScreen.this.dispose();
			}
		});


		chkGradPer.addItemListener( new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent arg0) {

				txtGradPer.setEnabled(true);

			}
		});

		chkPostGradPer.addItemListener( new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent arg0) {

				txtPostGradPer.setEnabled(true);

			}
		});

		radioConcessionYes.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				lblConcessionPer.setEnabled(true);
				comboConcessionPer.setEnabled(true);
				radioConcessionNo.setSelected(false);

			}
		});

		radioConcessionNo.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				lblConcessionPer.setEnabled(false);
				comboConcessionPer.setEnabled(false);
				radioConcessionYes.setSelected(false);
			}
		});


		this.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		this.setLayout(null);
		this.setVisible(true);

	}


	private boolean checkEMail(String eMail ) 
	{
		// TODO Auto-generated method stub
		int v1 = 0, v2=0;

		if( (v1 = getOccCount('@', eMail)) != 1 )
			return false;
		else
		{
			// Only 1 @ sign

			String[] splitArr = eMail.split("@");

			v1 = getOccCount('.', splitArr[0] );
			v2 = getOccCount('.', splitArr[1] );

			// No Of Dots <= 2 
			if( v1 <= 2 && v2 <= 2 )
			{
				// No . B4 @ & No . at the End & not stating both sides with .
				if( splitArr[0].startsWith(".") || splitArr[0].endsWith(".") || 
					splitArr[1].startsWith(".") || splitArr[1].endsWith(".") 	)
					return false;
				else
					return true;
			}
			else
				return false;
			 
		}

	}


	private int getOccCount(char c, String text ) 
	{
		// TODO Auto-generated method stub
		int cnt = 0;

		for( int i=0; i<text.length(); i++)
		{
			if( text.charAt(i) == c )
				cnt++;
		}

		return cnt;
	}

}
