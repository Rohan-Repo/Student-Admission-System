package in.easysystems.studentadmission.ui;

import in.easysystems.studentadmission.db.DBOperations;
import in.easysystems.studentadmission.util.StudentInfo;

import java.awt.Color;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.RepaintManager;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class MainScreen implements ActionListener {

	JFrame mainScreen;
	JButton btnChallan, btnReport, btnStudInfo;


	public MainScreen() 
	{
		// TODO Auto-generated constructor stub

		mainScreen = new JFrame( "Main Screen" );
		mainScreen.setSize( 500, 500 );

		btnChallan = new JButton( "Challan Entry" );
		btnChallan.setBounds(100, 100, 200, 40);

		btnReport = new JButton( "Report" );
		btnReport.setBounds(100, 200, 200, 40);

		btnStudInfo = new JButton( "Test" );
		btnStudInfo.setBounds( 100, 300, 200, 40);

		mainScreen.add( btnChallan );
		mainScreen.add( btnReport );
		mainScreen.add( btnStudInfo );

		mainScreen.setLayout( null );
		mainScreen.setVisible( true );
		mainScreen.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		mainScreen.setResizable(true);
		btnChallan.addActionListener( this );
		btnReport.addActionListener( this );
		btnStudInfo.addActionListener( this );
		
		setTheme();

		
	}

	private void setTheme() 
	{
		// TODO Auto-generated method stub

		//UIManager.setLookAndFeel( "javax.swing.plaf.nimbus.NimbusLookAndFeel" );
		// UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
		try 
		{
			//UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
			//UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
			UIManager.setLookAndFeel( "javax.swing.plaf.nimbus.NimbusLookAndFeel" );
		} 
		catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException 
				| NullPointerException e) 
		{
			// TODO Auto-generated catch block

		}
		// UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		// UIManager.setLookAndFeel("javax.swing.plaf.synth.SynthLookAndFeel");
		//	 UIManager.setLookAndFeel( "com.seaglasslookandfeel.SeaGlassLookAndFeel" );
		//UIManager.setLookAndFeel( "javax.swing.plaf.multi.MultiLookAndFeel" );

		SwingUtilities.updateComponentTreeUI(mainScreen);

	}



	@Override
	public void actionPerformed(ActionEvent e) 
	{
		// TODO Auto-generated method stub

		if( e.getSource() == btnChallan )
		{
			setTheme();

			Object[] options = { "New Student", "Existing Student" };
			int sel = JOptionPane.showOptionDialog( mainScreen, "Student Status", "Student Status",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1] );

			if( sel == JOptionPane.YES_OPTION )
			{
				int studID = DBOperations.getNextStudentID();
				new AddStudentInfoScreen( studID );
			}
			else
				if( sel == JOptionPane.NO_OPTION )
					new StudentListScreen();


		}

		if( e.getSource() == btnReport )
		{
			setTheme();
			new ReportScreen();
		}

		if( e.getSource() == btnStudInfo )
		{
			 sortStudentData( 'c' );
		}


	}

	private void sortStudentData(char sortBy ) 
	{
		// TODO Auto-generated method stub
		ArrayList<StudentInfo> studList = DBOperations.getStudentList();
		
		if( sortBy == 'c' )
		{
			
			// CourseWise

			
			for (int k=0; k<10; k++ ) 
			{
				System.out.println( "Name :" + studList.get(k).studName  + "    Course :" + studList.get(k).courseNum );
			}
			
			int i,j;
			StudentInfo stud;
			
			for( i=0; i<studList.size(); i++ )
			{
				for( j=0; j<studList.size(); j++ )
				{
					if( studList.get(i).courseNum > studList.get(j).courseNum )
					{
						stud = studList.get(i);
						studList.add(i, studList.get(j));
						studList.add(j, stud);
					}
				}
			}
			
			StringBuffer  buffer = new  StringBuffer();
			
			for (StudentInfo studentInfo : studList) 
			{
				buffer.append( "Name :" + studentInfo.studName  + "    Course :" + studentInfo.courseNum );
			}
			
			JOptionPane.showMessageDialog( mainScreen, buffer.toString() );
		}
		
	}

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub

		new MainScreen();

	}

}
