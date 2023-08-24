package in.easysystems.studentadmission.ui;

import in.easysystems.studentadmission.db.DBOperations;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ReportScreen extends JDialog 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JButton btnDateReport, btnBalanceReport, btnStudentWiseReport, btnCourseWiseReport, btnBack, btnAdvanceReport;
	JLabel lblStudentReport;
	DefaultTableModel tableModel;

	public ReportScreen() 
	{
		// TODO Auto-generated constructor stub

		setTitle( "Report Frame" );
		setSize( 500, 500 );
		setLayout( null );


		/*ImageIcon backIcon = new ImageIcon("/Copy of StudentAdmission/src/Images/backIcon_blue.gif");
		btnBack = new JButton(backIcon) ;*/
		btnBack = new JButton( new ImageIcon("D:\\IT\\backIcon_blue.gif") );
		btnBack.setBounds( 10, 10, 20, 20 );

		btnDateReport = new JButton( "Date Report" );
		btnDateReport.setBounds( 100, 50, 150, 30 );

		btnAdvanceReport = new JButton("Advance Report");
		btnAdvanceReport.setBounds( 100, 100, 150, 30 );

		btnBalanceReport = new JButton( "Balance Report" );
		btnBalanceReport.setBounds( 250, 100, 150, 30 );

		lblStudentReport = new JLabel( "Student Report :" );
		lblStudentReport.setBounds( 10, 200, 150, 30 );

		btnStudentWiseReport = new JButton("Student-Wise");
		btnStudentWiseReport.setBounds( 100, 200, 150, 30 );

		btnCourseWiseReport = new JButton("Course-Wise");
		btnCourseWiseReport.setBounds( 250, 200, 150, 30 );

		btnDateReport.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				new DateReportScreen();

			}
		});

		btnStudentWiseReport.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				tableModel = null;

				tableModel = DBOperations.retrieveStudentReport("student");

				if( tableModel != null )
					new StudentReport( "Student Wise List", false, null, tableModel , null);
				else
					JOptionPane.showMessageDialog( ReportScreen.this, "No Values Returned", "Error Message", JOptionPane.ERROR_MESSAGE );


			}
		});

		btnCourseWiseReport.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				tableModel = null;

				tableModel = DBOperations.retrieveStudentReport("course");

				if( tableModel != null )
					new StudentReport( "Course Wise List", false, null, tableModel , null);
				else
					JOptionPane.showMessageDialog( ReportScreen.this, "No Values Returned", "Error Message", JOptionPane.ERROR_MESSAGE );



			}
		});

		btnBalanceReport.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				try
				{
					tableModel = null;
					tableModel = DBOperations.retrieveStudentReport("balance");

					if( tableModel != null )
						new StudentReport( "Student Fees Balance Report", false, null, tableModel , null);
					else
						JOptionPane.showMessageDialog( ReportScreen.this, "No Dues Remaining From Any Student", "Error Message", JOptionPane.ERROR_MESSAGE );
				}
				catch( Exception e)
				{

				}

			}
		});

		btnAdvanceReport.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				try
				{
					tableModel = null;

					tableModel = DBOperations.retrieveStudentReport("advance");

					if( tableModel != null )
						new StudentReport( "Student Fees Advance Report", false, null, tableModel , null);
					else
						JOptionPane.showMessageDialog( ReportScreen.this, "No Advance Received From Any Student", "Error Message", JOptionPane.ERROR_MESSAGE );
				}
				catch( Exception e )
				{

				}

			}
		});


		btnBack.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				ReportScreen.this.dispose();
			}
		});

		this.setModal(true);

		//this.setModalityType( ModalityType.APPLICATION_MODAL );

		add( btnBack );
		add( btnDateReport );
		add( btnBalanceReport );
		add( btnAdvanceReport );
		add( lblStudentReport ); 
		add( btnStudentWiseReport );
		add( btnCourseWiseReport );

		setVisible(true);
		setDefaultCloseOperation( DISPOSE_ON_CLOSE );

	}
}
