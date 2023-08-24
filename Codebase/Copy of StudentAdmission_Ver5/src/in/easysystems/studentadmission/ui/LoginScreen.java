package in.easysystems.studentadmission.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class LoginScreen extends JFrame 
{
	JLabel lblUserName, lblPassword;
	JTextField txtUserName;
	JPasswordField txtPassword;
	JButton btnLogin, btnCancel;
	
	public LoginScreen() 
	{
		// TODO Auto-generated constructor stub
		setTitle( "Login Screen v7.0" );
		setSize( 500,500 );
		setLayout(null);
		
		Font font = new Font( "Arial", Font.BOLD, 12 );
		
		lblUserName = new JLabel( "User-Name : " );
		lblUserName.setFont(font);
		lblUserName.setBounds( 100, 100, 100, 30 );
		
		txtUserName = new JTextField( 150 );
		txtUserName.setBounds( 220, 100, 150, 30 );
		
		lblPassword = new JLabel( "Password : " );
		lblPassword.setBounds( 100, 150, 100, 30 );
		lblPassword.setFont(font);
		
		txtPassword = new JPasswordField( 150 );
		txtPassword.setBounds( 220, 150, 150, 30 );
		
		btnLogin = new JButton( "Login" );
		btnLogin.setBounds( 100, 200, 100, 30 );
		
		btnCancel = new JButton( "Cancel" );
		btnCancel.setBounds( 250, 200, 100, 30 );
		
		add( lblUserName ); 
		add( lblPassword );
		add( txtUserName );
		add( txtPassword );
		add( btnLogin );
		add( btnCancel );
		
		btnLogin.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				// TODO Auto-generated method stub
				String userName = txtUserName.getText();
				String password = new String( txtPassword.getPassword() ); 
				// Because JPasswordField's getPassword Field method returns a char[]
				
				if( userName.equals("admin") && password.equals("admin") )
				{
					setTheme();
					JOptionPane.showMessageDialog( LoginScreen.this, "Login Successful...", "Login Confirmation", JOptionPane.INFORMATION_MESSAGE );
					LoginScreen.this.dispose();
					new MainScreen();
				}
				/*
				else
				if( password.length() < 8 )
				{
					setTheme();
					JOptionPane.showMessageDialog( LoginScreen.this, "Password should contain atleast 8 characters", "Login Confirmation", JOptionPane.WARNING_MESSAGE );
					txtUserName.setText( "" );
					txtPassword.setText( "" );
					
				}*/
				else
				{
					setTheme();
					JOptionPane.showMessageDialog( LoginScreen.this, "Invalid UserName or Password Entered!!!", "Login Confirmation", JOptionPane.WARNING_MESSAGE );
					txtUserName.setText( "" );
					txtPassword.setText( "" );					
				}
					
			}
		});
		
		btnCancel.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				LoginScreen.this.dispose();
			}
		});
		
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
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

		SwingUtilities.updateComponentTreeUI( LoginScreen.this );

	}


	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		new LoginScreen();
	}

}
