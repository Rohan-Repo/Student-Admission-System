package in.easysystems.studentdetails.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseConnection 
{
	protected Connection dbConnection;
	
	
	protected void setup() 
	{
		try
		{
			String driverName = "com.mysql.jdbc.Driver";
			
			String firstAttribute = "jdbc:mysql://localhost/dbName";
			dbConnection = DriverManager.getConnection( firstAttribute, "root", "ronny17" );
			
			Class.forName( driverName );

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
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}

}


