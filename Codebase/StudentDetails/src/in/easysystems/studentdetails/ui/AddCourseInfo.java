package in.easysystems.studentdetails.ui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class AddCourseInfo extends JFrame 
{
	JLabel lblCourseName;
	JTextField txtCourseName;
	JButton btnSave, btnClear;
	
	public AddCourseInfo() 
	{
		// TODO Auto-generated constructor stub
		
		setName( "Add Course Details" );
		setTitle( "Add Course Information" );
		setSize( 500,  500 );
		setLayout(null);
		
		lblCourseName = new JLabel( "Course Name : " );
		lblCourseName.setBounds( 100, 100, 100, 30 );
		
		txtCourseName = new JTextField( "200" );
		txtCourseName.setBounds( 300, 100, 250, 30 );
	
		
		add( lblCourseName );
		add( txtCourseName );
		
		
		try {
		//	UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
	//	   	UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
			//UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
			// UIManager.setLookAndFeel("javax.swing.plaf.synth.SynthLookAndFeel");
			
		//UIManager.setLookAndFeel( "javax.swing.plaf.multi.MultiLookAndFeel" );
			
			UIManager.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
			
			SwingUtilities.updateComponentTreeUI( this );
			 
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			System.out.println("ERR");
		}
		
		setDefaultCloseOperation( EXIT_ON_CLOSE );
		setVisible( true );
		
	}
}
