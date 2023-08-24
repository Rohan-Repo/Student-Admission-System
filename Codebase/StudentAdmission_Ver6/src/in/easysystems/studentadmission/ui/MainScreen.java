package in.easysystems.studentadmission.ui;

import in.easysystems.studentadmission.db.DBOperations;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.RepaintManager;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class MainScreen extends JDialog implements ActionListener {

	JButton btnChallan, btnReport, btnStudInfo, btnLogOut;

	public MainScreen() 
	{
		// TODO Auto-generated constructor stub

		setTitle( "Main Screen v6.0" );
		setSize( 500, 500 );

		btnChallan = new JButton( "Challan Entry" );
		btnChallan.setBounds(100, 100, 200, 40);

		btnReport = new JButton( "Report" );
		btnReport.setBounds(100, 200, 200, 40);

		btnStudInfo = new JButton( "Test" );
		btnStudInfo.setBounds( 100, 300, 200, 40);

		btnLogOut = new  JButton( "LogOut" );
		btnLogOut.setBounds( 400, 10, 80, 30 );
		btnLogOut.setFont( new Font( "Arial", Font.BOLD, 12) );
		btnLogOut.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				MainScreen.this.dispose();
				new LoginScreen();
			}
		});
		
		add( btnChallan );
		add( btnReport );
		add( btnStudInfo );
		add( btnLogOut );
		
		setLayout( null );
		setVisible( true );
		setDefaultCloseOperation( DISPOSE_ON_CLOSE );
		setResizable(true);
		setModal(true);
		
		btnChallan.addActionListener( this );
		btnReport.addActionListener( this );
		btnStudInfo.addActionListener( this );
		
		
	}

	


	@Override
	public void actionPerformed(ActionEvent e) 
	{
		// TODO Auto-generated method stub

		if( e.getSource() == btnChallan )
		{
		
			Object[] options = { "New Student", "Existing Student" };
			int sel = JOptionPane.showOptionDialog( MainScreen.this, "Student Status", "Student Status",
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
			new ReportScreen();
		}

		if( e.getSource() == btnStudInfo )
		{
			 
		}


	}
/*
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub

		new MainScreen();

	}
*/
	
}
