package in.easysystems.studentadmission.ui;

import in.easysystems.studentadmission.db.DBOperations;

import java.awt.event.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class MainScreen implements ActionListener {

	JFrame mainScreen;
	JButton btnChallan, btnReport, btnStudInfo;


	public MainScreen() 
	{
		// TODO Auto-generated constructor stub

		mainScreen = new JFrame( "Main Screen V3.0" );
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
			/*
			UIManager.put("swing.boldMetal", Boolean.FALSE);
	        JFrame f = new JFrame("Print UI Example");
	        f.addWindowListener(new WindowAdapter() {
	           public void windowClosing(WindowEvent e) {System.exit(0);}
	        });
	        JTextArea text = new JTextArea(50, 20);
	        for (int i=1;i<=50;i++) {
	            text.append("Line " + i + "\n");
	        }
	        JScrollPane pane = new JScrollPane(text);
	        pane.setPreferredSize(new Dimension(250,200));
	        f.add("Center", pane);
	        JButton printButton = new JButton("Print This Window");
	        printButton.addActionListener(new PrintUIWindow(f));
	        f.add("South", printButton);
	        f.pack();
	        f.setVisible(true);
	        */
			
		}
	

	}

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub

		new MainScreen();

	}

}
