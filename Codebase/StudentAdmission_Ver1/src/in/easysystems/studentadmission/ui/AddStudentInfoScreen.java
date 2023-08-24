package in.easysystems.studentadmission.ui;

import in.easysystems.studentadmission.db.DBOperations;
import in.easysystems.studentadmission.util.CourseInfo;
import in.easysystems.studentadmission.util.StudentInfo;
import in.easysystems.studentadmission.util.StudentQualifications;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;

public class AddStudentInfoScreen 
{
	JFrame frameStudInfo;
	JLabel lblStudRegNum, lblStudRegNumVal,lblStudName, lblStudContactNum, lblStudEMail;
	JLabel lblStudCourse, lblTenthPer,lblTwelfthPer, lblGradPer, lblPostGradPer;
	JTextField txtStudName, txtStudEMail;
	JCheckBox chkGradPer, chkPostGradPer;
	JComboBox<String> comboStudCourse;
	JButton btnSubmit, btnReset;
	MaskFormatter numberFormatter,percentageFormatter;
	NumberFormatter numberFormatter2;
	JFormattedTextField txtStudContactNum, txtTenthPer,txtTwelfthPer, txtGradPer, txtPostGradPer;
	ArrayList<CourseInfo> courseList;

	public AddStudentInfoScreen( int studentRegID ) 
	{
		frameStudInfo = new JFrame( "Student Info." );
		frameStudInfo.setSize( 1000, 700 );

		lblStudRegNum = new JLabel( "Registration Number" );
		lblStudRegNum.setBounds( 10, 10, 200, 30);

		lblStudRegNumVal = new JLabel();
		lblStudRegNumVal.setBounds( 150, 10, 100, 30);
		lblStudRegNumVal.setText( studentRegID + "" );

		try 
		{
			lblStudName= new JLabel( "Name" );
			lblStudName.setBounds( 10, 70, 100, 30);

			txtStudName= new JTextField( 300 );
			txtStudName.setBounds( 150, 70, 300, 30);


			lblStudCourse = new JLabel( "Student Course" );
			lblStudCourse.setBounds( 10, 150, 150, 30 );

			courseList = DBOperations.getCourseList();
			
			String[] cList = new String[ courseList.size() ];

			int i=0;

			for ( CourseInfo c : courseList ) 
			{
				cList[i] = new String( c.courseName );
				i++;				
			}
			
			comboStudCourse = new JComboBox<String>( cList );
			comboStudCourse.setBounds( 150, 150, 100, 30 );		

			lblStudContactNum= new JLabel( "Contact Number" );
			lblStudContactNum.setBounds( 10, 200, 200, 30);

			numberFormatter = new MaskFormatter( "##########" );
			numberFormatter.setValidCharacters("0123456789");
			//			numberFormatter2 = new NumberFormatter( locale );

			txtStudContactNum= new JFormattedTextField( numberFormatter );
			txtStudContactNum.setBounds( 150, 200, 200, 30);

			lblStudEMail = new JLabel( "Email ID" );
			lblStudEMail.setBounds( 10, 270, 100, 30);

			txtStudEMail = new JTextField( 300 );
			txtStudEMail.setBounds( 150, 270, 300, 30);

			// Percentage Formatter
			percentageFormatter = new MaskFormatter( "##.##" );

			lblTenthPer = new JLabel( "Tenth Percentage*" );
			lblTenthPer.setBounds( 70, 350, 200, 30 );

			txtTenthPer = new JFormattedTextField( percentageFormatter );
			txtTenthPer.setBounds( 250, 350, 100, 30);
			txtTenthPer.setToolTipText( "Insert Your Tenth Percentage in xx.xx format \n "
					+ "  For Eg. 78% then Input 78.00" );

			lblTwelfthPer = new JLabel( "Twelfth Percentage*" );
			lblTwelfthPer.setBounds( 70, 400, 200, 30 );

			txtTwelfthPer = new JFormattedTextField( percentageFormatter );
			txtTwelfthPer.setBounds( 250, 400, 100, 30);
			txtTwelfthPer.setToolTipText( "Insert Your Twelfth Percentage in xx.xx format \n "
					+ "  For Eg. 78% then Input 78.00" );


			chkGradPer = new JCheckBox( "UG" );
			chkGradPer.setBounds( 10, 450, 50, 30);

			lblGradPer = new JLabel( "Graduation Percentage" );
			lblGradPer.setBounds( 70, 450, 200, 30 );

			txtGradPer = new JFormattedTextField( percentageFormatter );
			txtGradPer.setBounds( 250, 450, 100, 30);
			txtGradPer.setToolTipText( "Insert Your Graduation Percentage in xx.xx format \n "
					+ "  For Eg. 78% then Input 78.00" );

			txtGradPer.setEnabled(false);
		
			chkPostGradPer = new JCheckBox( "PG" );
			chkPostGradPer.setBounds( 10, 500, 50, 30);

			lblPostGradPer = new JLabel( " PostGraduation Percentage" );
			lblPostGradPer.setBounds( 70, 500, 200, 30 );

			txtPostGradPer = new JFormattedTextField( percentageFormatter );
			txtPostGradPer.setBounds( 250, 500, 100, 30);
			txtPostGradPer.setToolTipText( "Insert Your Post-Grad Percentage in xx.xx format \n "
					+ "  For Eg. 78% then Input 78.00" );

			txtPostGradPer.setEnabled(false);


			btnSubmit = new JButton( "Sumbit" );
			btnSubmit.setBounds( 100, 580, 100, 30);

			btnReset = new JButton( "Reset" );
			btnReset.setBounds( 300, 580, 100, 30);

			frameStudInfo.add( lblStudRegNum );
			frameStudInfo.add( lblStudRegNumVal );
			frameStudInfo.add( lblStudName );
			frameStudInfo.add( txtStudName );
			frameStudInfo.add( lblStudCourse );
			frameStudInfo.add( comboStudCourse );
			frameStudInfo.add( lblStudContactNum );
			frameStudInfo.add( txtStudContactNum );
			frameStudInfo.add( lblStudEMail );
			frameStudInfo.add( txtStudEMail );
			frameStudInfo.add( lblTenthPer );
			frameStudInfo.add( txtTenthPer );

			frameStudInfo.add( lblTwelfthPer );
			frameStudInfo.add( txtTwelfthPer );

			frameStudInfo.add( chkGradPer );
			frameStudInfo.add( lblGradPer );
			frameStudInfo.add( txtGradPer );
			frameStudInfo.add( chkPostGradPer );
			frameStudInfo.add( lblPostGradPer );
			frameStudInfo.add( txtPostGradPer );



			frameStudInfo.add( btnSubmit );
			frameStudInfo.add( btnReset );

			 
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
				String email = txtStudEMail.getText();
				float tenthPer = Float.parseFloat( txtTenthPer.getText() );
				float twelfthPer = Float.parseFloat( txtTwelfthPer.getText() );
				
			
				float gradPer = 0, postGradPer = 0;
				
				if( chkGradPer.isSelected() )
					gradPer = Float.parseFloat( txtGradPer.getText() );
				if( chkPostGradPer.isSelected() )
					postGradPer = Float.parseFloat( txtPostGradPer.getText() );
				
				StudentQualifications qualifications = new StudentQualifications( tenthPer, twelfthPer, gradPer, postGradPer );
				
				StudentInfo studentInfo = new StudentInfo( id, name, contactNum, email, qualifications );
				
				
				int selCourseNum = comboStudCourse.getSelectedIndex();
				CourseInfo courseInfo = courseList.get( selCourseNum );
				
				boolean val = DBOperations.addStudentInfo( studentInfo, courseInfo );
				
				if( val )
				{
					// Successfull Insertion
					frameStudInfo.dispose();
				}
				else
				{
					JOptionPane.showMessageDialog( frameStudInfo, "Kindly Re-Enter the Details", "ErrorMessage",  JOptionPane.ERROR_MESSAGE );
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


		frameStudInfo.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		frameStudInfo.setLayout(null);
		frameStudInfo.setVisible(true);


	}


}
