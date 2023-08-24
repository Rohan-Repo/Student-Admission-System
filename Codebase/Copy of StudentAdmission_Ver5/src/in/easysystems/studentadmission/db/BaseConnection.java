package in.easysystems.studentadmission.db;

import java.sql.Connection;
import java.sql.DriverManager;


public class BaseConnection 
{

	protected Connection dbConnection;
	
	protected void setup() 
	{
		try
		{
			String driverName = "com.mysql.jdbc.Driver";
		
			Class.forName( driverName );
		
			String firstAtt = "jdbc:mysql://localhost/studadmin";
		
			dbConnection = DriverManager.getConnection( firstAtt, "rd", "sarhad123" );
		}
		catch( Exception e )
		{
			e.printStackTrace();
			
		}

	}
	
	protected void cleanUp() 
	{

		try
		{
			dbConnection.close();
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}

	}
	
	
	
}
