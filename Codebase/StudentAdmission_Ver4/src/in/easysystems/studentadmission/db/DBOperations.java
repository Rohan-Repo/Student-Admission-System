package in.easysystems.studentadmission.db;

import in.easysystems.studentadmission.util.CourseInfo;
import in.easysystems.studentadmission.util.StudentAdmission;
import in.easysystems.studentadmission.util.StudentInfo;
import in.easysystems.studentadmission.util.StudentList;
import in.easysystems.studentadmission.util.StudentQualifications;



import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;



public class DBOperations extends BaseConnection
{
	static PreparedStatement preparedStatement;
	static BaseConnection baseConnection ;
	static ResultSet resultSet;

	static
	{
		baseConnection = new BaseConnection();
		baseConnection.setup();
	}

	/*
	public static void addStudentInfo( int studRegNum, String studName, String studContact, String studEMail ) 
	{
		try
		{
			String query = "insert into Student_Master values(?,?,?,?)";
			preparedStatement = baseConnection.dbConnection.prepareStatement( query );

			preparedStatement.setInt( 1, studRegNum );
			preparedStatement.setString( 2, studName );
			preparedStatement.setString( 3, studContact );
			preparedStatement.setString( 4, studEMail );

			int row = preparedStatement.executeUpdate();
			String msg = "Info. added for " + studName + " No. of Rows Affected : " + row;

			JOptionPane.showMessageDialog( null ,  msg );
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
	}
	 */
	public static boolean addStudentInfo( StudentInfo studentInfo )
	{
		boolean retVal = true;

		try
		{


			String query = "insert into Student_Master(studName,studContact,studEMail,scholar_eligb,percent_concess,addr1, " + 
					"addr2,addr3,academicYear_from,academicYear_to,courseNum) values(?,?,?,?,?,?,?,?,?,?,?)";

			preparedStatement = baseConnection.dbConnection.prepareStatement( query );

			preparedStatement.setString( 1, studentInfo.studName );
			preparedStatement.setString( 2, studentInfo.studContactNumber );
			preparedStatement.setString( 3, studentInfo.studEMailID );
			preparedStatement.setString( 4, studentInfo.scholarshipEligibility );
			preparedStatement.setInt( 5, studentInfo.studPercentConcession); 
			preparedStatement.setString( 6, studentInfo.studAddr1 );
			preparedStatement.setString( 7, studentInfo.studAddr2 );
			preparedStatement.setString( 8, studentInfo.studAddr3 );
			preparedStatement.setInt( 9, studentInfo.academicYear_from );
			preparedStatement.setInt( 10, studentInfo.academicYear_to );
			preparedStatement.setInt( 11, studentInfo.courseNum );

			String query2 = "insert into Stud_Qualifications(studRegNum,courseNum,studTenthPercentage, "
					+ "studTwelfthPercentage, studGraduationPercentage, studPostGraduationPercentage) values(?,?,?,?,?,?)";
			PreparedStatement preparedStatement2 = baseConnection.dbConnection.prepareStatement( query2 );

			preparedStatement2.setInt( 1, studentInfo.studRegNum );
			preparedStatement2.setInt( 2, studentInfo.courseNum );
			preparedStatement2.setFloat( 3, studentInfo.studentQualification.studTenthPercentage );
			preparedStatement2.setFloat( 4, studentInfo.studentQualification.studTwelfthPercentage );
			preparedStatement2.setFloat( 5, studentInfo.studentQualification.studGraduationPercentage );
			preparedStatement2.setFloat( 6, studentInfo.studentQualification.studPostGraduationPercentage );

			/* 				String query3 = "insert into balance_fees( studRegNum, courseNum, totalBalance ) "
					+ " values( ?,?,(select courseFees from course_master where courseNum = ?))";

			PreparedStatement ps = baseConnection.dbConnection.prepareStatement( query3 );

			ps.setInt( 1, studentInfo.studRegNum );
			ps.setInt( 2, courseInfo.courseNum );
			ps.setInt( 3, courseInfo.courseNum );


			 */

			int row = preparedStatement.executeUpdate();
			int row2 = preparedStatement2.executeUpdate();
			//int row3 = ps.executeUpdate();

			String msg = "";

			if( row == row2 )
				msg = "Info. added for " + studentInfo.studName + " No. of Rows Affected : " + row;
			else
			{
				msg = "Discrepency";
				retVal = false;
			}

			JOptionPane.showMessageDialog( null ,  msg );

		}
		catch( Exception e )
		{
			e.printStackTrace();
		}

		return retVal; 

	}


	public static StudentInfo getStudentInfo( ) 
	{ 
		return null;
	}

	public static ArrayList<StudentInfo> getStudentList() 
	{
		ArrayList<StudentInfo> arrStudentInfo = new ArrayList<StudentInfo>();

		String query1 = "select * from Student_Master where studRegNum != 1";
		String query2 = "select * from Stud_Qualifications where studRegNum != 1";

		try
		{
			int studRegNum=0;
			String studName = null, studContactNumber = null, studEMailID = null;
			float studTenthPercentage=0, studTwelfthPercentage=0, studGraduationPercentage=0, studPostGraduationPercentage=0;

			String studAddr1 = null, studAddr2 = null, studAddr3 = null;
			String scholarshipEligibility = null;
			int studPercentConcession = 0, academicYear_from = 0, academicYear_to = 0, courseNum = 0;

			preparedStatement = null;
			resultSet = null;

			PreparedStatement statement2 = baseConnection.dbConnection.prepareStatement( query2 );


			ResultSet resultSet2 = statement2.executeQuery();


			int i=0;

			ArrayList<StudentQualifications> qualfArr = new ArrayList<StudentQualifications>();

			while ( resultSet2.next() ) 
			{
				studTenthPercentage = resultSet2.getFloat( 4 );
				studTwelfthPercentage = resultSet2.getFloat( 5 );
				studGraduationPercentage = resultSet2.getFloat( 6 );
				studPostGraduationPercentage = resultSet2.getFloat( 7 );

				StudentQualifications qualifications = new StudentQualifications(studTenthPercentage, studTwelfthPercentage, studGraduationPercentage, studPostGraduationPercentage);
				qualfArr.add( qualifications );
			}

			resultSet2.close();
			statement2.close();

			preparedStatement = baseConnection.dbConnection.prepareStatement( query1 );
			resultSet = preparedStatement.executeQuery();

			i=0;

			while ( resultSet.next() ) 
			{
				studRegNum = resultSet.getInt( 1 );
				studName = resultSet.getString( 2 );
				studContactNumber = resultSet.getString( 3 );
				studEMailID = resultSet.getString( 4 );
				scholarshipEligibility = resultSet.getString(5);
				studPercentConcession = resultSet.getInt(6);
				studAddr1 = resultSet.getString(7);
				studAddr2 = resultSet.getString(8);
				studAddr3 = resultSet.getString(9);
				academicYear_from = resultSet.getInt(10);
				academicYear_to = resultSet.getInt(11);
				courseNum = resultSet.getInt(12);

				StudentQualifications qualifications = qualfArr.get( i++ );
				StudentInfo student = new StudentInfo( studRegNum, studName, studContactNumber, studEMailID, studAddr1,studAddr2,studAddr3,scholarshipEligibility, studPercentConcession, academicYear_from, academicYear_to, courseNum, qualifications);


				arrStudentInfo.add( student );

			}


		}
		catch( Exception e )
		{
			e.printStackTrace();
		}


		return arrStudentInfo;
	}


	public static ArrayList<StudentList> getCourseWiseStudentList( int courseNum ) 
	{


		ArrayList<StudentList> studentList = new ArrayList<StudentList>();

		preparedStatement = null;
		resultSet = null;

		String query = "select studRegNum, getStudName(studRegNum), getCourseName(courseNum), " +
				"totalBalance, getStudentEligibility(studRegNum) from balance_fees " +
				"where courseNum = ? AND balance_fees.studRegNum != 1 AND totalBalance > 0" ;

		try
		{
			preparedStatement = baseConnection.dbConnection.prepareStatement( query );
			preparedStatement.setInt( 1, courseNum );

			resultSet = preparedStatement.executeQuery();

			while ( resultSet.next() ) 
			{
				int studRegNum = resultSet.getInt( 1 );

				String studName = resultSet.getString( 2 );

				String cName = resultSet.getString( 3 );

				float balanceFees = resultSet.getFloat( 4 );

				String eligbStr = resultSet.getString( 5 );

				String[] split = eligbStr.split(" ");

				char eligb = split[0].charAt(0);
				int per = Integer.parseInt( split[1] );

				StudentList list = new StudentList(	studRegNum, studName, cName, balanceFees, eligb, per );

				studentList.add( list );

			}


		}
		catch( Exception e )
		{
			e.printStackTrace();
		}

		return studentList;
	}

	public static ArrayList<CourseInfo> getCourseList() 
	{
		String query = "select * from Course_Master";

		ArrayList<CourseInfo> arrCourseInfo = new ArrayList<CourseInfo>();

		try
		{

			preparedStatement = null;
			resultSet = null;

			preparedStatement = baseConnection.dbConnection.prepareStatement( query );

			resultSet = preparedStatement.executeQuery();

			while ( resultSet.next() ) 
			{
				int courseNum = resultSet.getInt( 1  ); 
				String courseName = resultSet.getString( 2 );
				float courseFees = resultSet.getFloat( 3 );
				int courseDuration = resultSet.getInt( 4 );

				CourseInfo course = new CourseInfo( courseNum, courseDuration, courseName, courseFees );
				arrCourseInfo.add( course );
			}

		}
		catch( Exception e )
		{
			e.printStackTrace();
		}

		return arrCourseInfo;

	}


	public static int  getNextStudentID() 
	{
		String query = "select max(studRegNum) from Student_Master";
		int studentID = 0;

		try
		{
			preparedStatement = null;
			resultSet = null;

			preparedStatement = baseConnection.dbConnection.prepareStatement( query );
			resultSet = preparedStatement.executeQuery();


			if( resultSet.next() )
			{ 
				studentID = resultSet.getInt( 1 );
				studentID++;	
			}
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}

		return studentID;
	}

	public static int  getNextCourseID() 
	{
		String query = "select max(courseNum) from Course_Master";
		int courseID = 0;

		try
		{
			preparedStatement = null;
			resultSet = null;

			preparedStatement = baseConnection.dbConnection.prepareStatement( query );
			resultSet = preparedStatement.executeQuery();


			if( resultSet.next() )
			{ 
				courseID  = resultSet.getInt( 1 );
				courseID++; 
			}
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}

		return courseID;
	}

	public static int  getNextStudentQualificationID() 
	{
		String query = "select max(qualificationNum) from Stud_Qualifications";
		int studentQualificationID = 0;

		try
		{
			preparedStatement = null;
			resultSet = null;

			preparedStatement = baseConnection.dbConnection.prepareStatement( query );
			resultSet = preparedStatement.executeQuery();


			if( resultSet.next() )
			{ 
				studentQualificationID = resultSet.getInt(  1 );
				studentQualificationID++;	
			}
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}

		return studentQualificationID;
	}

	public static int  getNextChallanNumber() 
	{
		String query = "select max(challanNum) from stud_admission";
		int challanNum = 0;

		try
		{
			preparedStatement = null;
			resultSet = null;

			preparedStatement = baseConnection.dbConnection.prepareStatement( query );
			resultSet = preparedStatement.executeQuery();


			if( resultSet.next() )
			{ 
				challanNum = resultSet.getInt(  1 );
				challanNum++;	
			}
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}

		return challanNum;

	}

	public static String returnDate( LocalDate localDate ) 
	{
		Date date = Date.valueOf( localDate );

		return "Date : " + date.toString();

	}

	public static  int getRowCount( String tableName ) 
	{
		String query = "select count(*) from " + tableName;

		int rowCnt = 0;

		try
		{
			preparedStatement = null;
			resultSet = null;

			preparedStatement = baseConnection.dbConnection.prepareStatement( query );
			resultSet = preparedStatement.executeQuery();


			if( resultSet.next() )
				rowCnt = resultSet.getInt(  1 );	

		}
		catch( Exception e )
		{
			e.printStackTrace();
		}

		return rowCnt;

	}

	public static boolean depositAdvanceFees(StudentAdmission admission, float advanceAmt ) 
	{
		String query3 = "insert into advance_fees( studRegNum, courseNum, totalAdvance, academicYear_from, " +
				" academicYear_to, courseFees ) values( ?,?,?,?,?,?)";

		boolean success = true ;

		preparedStatement = null;

		try
		{
			preparedStatement = baseConnection.dbConnection.prepareStatement(query3);

			preparedStatement.setInt( 1, admission.studRegNum );
			preparedStatement.setInt( 2, admission.courseNum );
			preparedStatement.setFloat( 3, advanceAmt );
			preparedStatement.setInt( 4, admission.academicYearFrom );
			preparedStatement.setInt( 5, admission.academicYearTo );
			preparedStatement.setFloat( 6, admission.courseFees );

			int v = preparedStatement.executeUpdate();

			if( v == 1)
				success = true;
			else
				success = false;

		}
		catch( Exception e)
		{
			e.printStackTrace();
		}

		return success;

	}


	public static boolean depositFees( StudentAdmission admission ) 
	{
		boolean depositSuccessful = false;

		String query1 = "insert into Stud_Admission(studRegNum,courseNum,admissionDate,currentInstallment, academicYear_from, academicYear_to) values(?,?,?,?,?,?)";
		String query2 = "update Balance_Fees SET totalBalance = ? where studRegNum = ?";

		try
		{
			preparedStatement = null;

			PreparedStatement statement = baseConnection.dbConnection.prepareStatement( query1 );
			statement.setInt( 1, admission.studRegNum );
			statement.setInt( 2, admission.courseNum );
			Date instDate = Date.valueOf( admission.installmentDate );
			statement.setDate( 3, instDate );
			statement.setFloat( 4, admission.currentInstallment );
			statement.setInt( 5, admission.academicYearFrom );
			statement.setInt( 6, admission.academicYearTo );

			preparedStatement = baseConnection.dbConnection.prepareStatement( query2 );
			preparedStatement.setFloat( 1, admission.balance );
			preparedStatement.setInt( 2, admission.studRegNum );

			int r1 = statement.executeUpdate();
			int r2 = preparedStatement.executeUpdate();

			if( r1 == r2 )
				depositSuccessful = true;

		}
		catch( Exception e )
		{
			e.printStackTrace();
		}

		return depositSuccessful;

	}


	public static DefaultTableModel populateTable( String courseName ) 
	{



		Vector<Object> vectorColumnNames = new Vector<Object>();
		Vector<Object> vectorRowData = new Vector<Object>();
		DefaultTableModel tableModel = null;

		String query = "select student_master.studRegNum, studName, courseName,totalBalance from " +
				"student_master, course_master, balance_fees " + " where " + 
				"student_master.studRegNum = balance_fees.studRegNum AND " +
				" course_master.courseNum = balance_fees.courseNum AND " + 
				"course_master.courseName = ? AND balance_fees.studRegNum != 1 " +
				"AND totalBalance > 0";	
		try
		{

			preparedStatement = null;
			resultSet = null;

			preparedStatement = baseConnection.dbConnection.prepareStatement( query );
			preparedStatement.setString( 1, courseName );
			resultSet = preparedStatement.executeQuery();

			ResultSetMetaData metaData = resultSet.getMetaData();
			int columnCnt = metaData.getColumnCount();


			// To get Column Names
			int i;

			for(  i=1 ; i<=columnCnt ; i++ )
			{
				vectorColumnNames.addElement( metaData.getColumnName(i) );

			}


			// Populate Row Data

			DecimalFormat decimalFormat = new DecimalFormat("##,###.00");

			while ( resultSet.next() ) 
			{
				Vector<Object> row = new Vector<Object>(columnCnt);

				for( i=1; i <= columnCnt ; i++ )
				{
					if( i == 4 )
					{
						// DecimalFormat

						double val = Double.valueOf( resultSet.getObject(i).toString() );
						String decNum = decimalFormat.format( val );
						row.addElement( decNum );

					}
					else
						row.addElement( resultSet.getObject(i) );
				}


				vectorRowData.addElement( row );

			}


			tableModel = new DefaultTableModel( vectorRowData, vectorColumnNames );



		}
		catch( Exception e )
		{
			e.printStackTrace();
		}


		return tableModel;


	}

	public static  DefaultTableModel retreiveDayWiseReport( LocalDate localDate ) 
	{
		Vector<Object> vectorColumnNames = new Vector<Object>();
		Vector<Object> vectorRowData = new Vector<Object>();
		DefaultTableModel tableModel = null;

		String query =  "select challanNum, admissionDate AS challanDate, studRegNum AS RegistrationNumber, " + 
				" getStudName(studRegNum) AS StudentName, getCourseName(courseNum) AS CourseName, " +
				" currentInstallment AS AmountPaid from stud_admission where admissionDate = ? " + 
				" AND challanNum != 1 order by admissionDate,challanNum";


		try
		{

			preparedStatement = null;
			resultSet = null;

			preparedStatement = baseConnection.dbConnection.prepareStatement( query );
			Date date2 = Date.valueOf(localDate);
			preparedStatement.setDate( 1, date2 );
			resultSet = preparedStatement.executeQuery();

			ResultSetMetaData metaData = resultSet.getMetaData();
			int columnCnt = metaData.getColumnCount();


			// To get Column Names
			int i;

			for(  i=1 ; i<=columnCnt ; i++ )
			{
				vectorColumnNames.addElement( metaData.getColumnLabel(i).toString() );

			}


			// Populate Row Data

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			DecimalFormat decimalFormat = new DecimalFormat("##,###.00");

			while ( resultSet.next() ) 
			{
				Vector<Object> row = new Vector<Object>(columnCnt);

				for( i=1; i <= columnCnt ; i++ )
				{
					if( i == 2 )
					{ 		// DD MM YYYY Format Date
						LocalDate localDate2 = LocalDate.parse(resultSet.getObject(i).toString());
						String date = localDate2.format(formatter);

						row.addElement( date );
					}
					else
						if( i == 6 )
						{
							// DecimalFormat

							double val = Double.valueOf( resultSet.getObject(i).toString() );
							String decNum = decimalFormat.format( val );
							row.addElement( decNum );

						}
						else
							row.addElement( resultSet.getObject(i) );
				}

				vectorRowData.addElement( row );

			}


			tableModel = new DefaultTableModel( vectorRowData, vectorColumnNames );



		}
		catch( Exception e )
		{
			e.printStackTrace();
		}

		return tableModel;
	}

	public static  DefaultTableModel retreiveDateRangeReport( LocalDate dateStart, LocalDate dateEnd ) 
	{

		System.out.println("---0---------");

		Vector<Object> vectorColumnNames = new Vector<Object>();
		Vector<Object> vectorRowData = new Vector<Object>();
		DefaultTableModel tableModel = null;

		String query =  "select challanNum, admissionDate AS challanDate, studRegNum AS RegistrationNumber, " +
				"getStudName(studRegNum) AS StudentName, getCourseName(courseNum) AS CourseName, " +
				"currentInstallment AS AmountPaid from stud_admission where admissionDate " +
				"BETWEEN ? AND ? AND challanNum != 1 order by admissionDate,challanNum";


		try
		{

			preparedStatement = null;
			resultSet = null;

			preparedStatement = baseConnection.dbConnection.prepareStatement( query );
			Date date2 = Date.valueOf(dateStart);
			preparedStatement.setDate( 1, date2 );
			Date date3 = Date.valueOf(dateEnd);
			preparedStatement.setDate( 2, date3 );
			resultSet = preparedStatement.executeQuery();

			ResultSetMetaData metaData = resultSet.getMetaData();
			int columnCnt = metaData.getColumnCount();
			System.out.println("CNT : " + columnCnt);

			// To get Column Names
			int i;

			for(  i=1 ; i<=columnCnt ; i++ )
			{
				vectorColumnNames.addElement( metaData.getColumnLabel(i).toString() );
				System.out.println("i"+ i+ " : "+ metaData.getColumnLabel(i).toString());
				// System.out.println(metaData.getColumnLabel(1)+ ": " + challanNum);

			}


			// Populate Row Data
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			DecimalFormat decimalFormat = new DecimalFormat("##,###.00");

			while ( resultSet.next() ) 
			{
				Vector<Object> row = new Vector<Object>(columnCnt);

				for( i=1; i <= columnCnt ; i++ )
				{
					if( i == 2 )
					{ 		// DD MM YYYY Format Date
						LocalDate localDate = LocalDate.parse(resultSet.getObject(i).toString());
						String date = localDate.format(formatter);

						row.addElement( date );
					}
					else
						if( i == 6 )
						{
							// DecimalFormat

							double val = Double.valueOf( resultSet.getObject(i).toString() );
							String decNum = decimalFormat.format( val );
							row.addElement( decNum );

						}
						else
							row.addElement( resultSet.getObject(i) );
				}

				vectorRowData.addElement( row );

			}


			tableModel = new DefaultTableModel( vectorRowData, vectorColumnNames );



		}
		catch( Exception e )
		{
			e.printStackTrace();
		}

		return tableModel;

	}


	public static DefaultTableModel retrieveStudentReport( String sortBy ) 
	{
		Vector<Object> vectorColumnNames = new Vector<Object>();
		Vector<Object> vectorRowData = new Vector<Object>();
		DefaultTableModel tableModel = null;

		try
		{


			String query = null;

			if( sortBy.equals("student") )
			{
				query = "select studRegNum AS RegistrationNumber, getStudName(studRegNum) AS StudentName, " + 
						"getCourseName(courseNum) AS CourseName from stud_qualifications " + 
						"where studRegNum != 1 order by studRegNum";
			}
			else
				if( sortBy.equals("course") )
				{
					query = "select studRegNum AS RegistrationNumber, getStudName(studRegNum) AS StudentName, " + 
							"getCourseName(courseNum) AS CourseName from stud_qualifications " + 
							"where studRegNum != 1 order by courseNum";
				}
				else
					if( sortBy.equals("balance") )
					{
						query = "select studRegNum AS RegistrationNumber, getStudName(studRegNum) AS StudentName, " + 
								"getCourseName(courseNum) AS CourseName, totalBalance AS RemainingBalance " +
								"academicYear_from AS AcademicYearFrom, academicYear_to AS AcademicYearTo " +
								"from balance_fees where totalBalance > 0 order by courseNum";
					}
					else
						if( sortBy.equals("advance") )
						{
							query = "select studRegNum AS RegistrationNumber, getStudName(studRegNum) AS StudentName, " + 
									"getCourseName(courseNum) AS CourseName, totalAdvance AS AdvancePaid,  " +
									"academicYear_from AS AcademicYearFrom, academicYear_to AS AcademicYearTo " +
									"from advance_fees where totalAdvance > 0 order by courseNum";
						}


			preparedStatement = null;
			resultSet = null;

			preparedStatement = baseConnection.dbConnection.prepareStatement( query );
			resultSet = preparedStatement.executeQuery();

			if( resultSet != null )
			{
				ResultSetMetaData metaData = resultSet.getMetaData();
				int columnCnt = metaData.getColumnCount();


				// To get Column Names
				int i;

				for(  i=1 ; i<=columnCnt ; i++ )
				{
					vectorColumnNames.addElement( metaData.getColumnLabel(i).toString() );

				}


				// Populate Row Data

				DecimalFormat decimalFormat = new DecimalFormat("##,###.00");

				while ( resultSet.next() ) 
				{
					Vector<Object> row = new Vector<Object>(columnCnt);

					if( sortBy.equals("balance") )
					{
						for( i=1; i <= columnCnt ; i++ )
						{	
							row.addElement( resultSet.getObject(i) );

							if( i == 4 )
							{
								// DecimalFormat

								double val = Double.valueOf( resultSet.getObject(i).toString() );
								String decNum = decimalFormat.format( val );
								row.addElement( decNum );

							}
						}
					}


					for( i=1; i <= columnCnt ; i++ )
					{	
						row.addElement( resultSet.getObject(i) );
					}

					vectorRowData.addElement( row );

				}


				tableModel = new DefaultTableModel( vectorRowData, vectorColumnNames );

			}

		}
		catch( Exception e )
		{

		}

		return tableModel;
	}


	public static String getFeesSumSingle( LocalDate date ) 
	{
		String query = "select sum(currentInstallment) from stud_admission where admissionDate = ?";

		double sum = 0;
		String val = null;
		DecimalFormat decimalFormat = null;

		try
		{
			preparedStatement = null;
			resultSet = null;


			
			
			preparedStatement = baseConnection.dbConnection.prepareStatement( query );
			Date date2 = Date.valueOf(date);
			preparedStatement.setDate(1, date2);

			resultSet = preparedStatement.executeQuery();


			if( resultSet.next() )
				sum = resultSet.getInt(  1 );	
			
			System.out.println( "Sum =" + sum );
			
			if( sum >= 100000 )
				 decimalFormat = new DecimalFormat("#,##,###.00");
			else
			if( sum <100000 && sum >1000 )
				decimalFormat = new DecimalFormat("##,###.00");
			else
				decimalFormat = new DecimalFormat("#,###.00");
			
			
			val = decimalFormat.format(sum);
			
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}

		return val;

	}

	public static String getFeesSumRange( LocalDate dateStart, LocalDate dateEnd ) 
	{
		String query = "select sum(currentInstallment) from stud_admission where admissionDate BETWEEN ? AND ?";

		double sum = 0;
		
		String val = null;
		DecimalFormat decimalFormat = null;
		
		try
		{
			preparedStatement = null;
			resultSet = null;

			
			
			preparedStatement = baseConnection.dbConnection.prepareStatement( query );

			Date date2 = Date.valueOf(dateStart);
			preparedStatement.setDate(1, date2);

			Date date3 = Date.valueOf(dateEnd);
			preparedStatement.setDate(2, date3);

			resultSet = preparedStatement.executeQuery();


			if( resultSet.next() )
				sum = resultSet.getInt(  1 );	
			
			if( sum >= 100000.0 )
			{
				System.out.println( "l" + sum );
				decimalFormat = new DecimalFormat("##,##,###.00");
			}
			else
			if( sum <100000.0 && sum >1000.0 )
			{
				System.out.println( "t" + sum );
				decimalFormat = new DecimalFormat("##,###.00");
			}
			else
				decimalFormat = new DecimalFormat("#,###.00");
			
			 val = decimalFormat.format(sum);
			

		}
		catch( Exception e )
		{
			e.printStackTrace();
		}

		return val;

	}

}
