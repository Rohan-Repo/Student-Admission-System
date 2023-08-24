package in.easysystems.studentadmission.ui;

import in.easysystems.studentadmission.db.DBOperations;
import in.easysystems.studentadmission.util.CourseInfo;
import in.easysystems.studentadmission.util.StudentList;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

public class StudentListScreen extends JFrame 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JPanel pnlHeader, pnlFooter;
	JScrollPane scrollPane;
	JTable tblStudent;
	JButton btnSearch, btnChallan;
	JLabel lblcourseName;
	JComboBox<String> comboCourseList; 
	ArrayList<CourseInfo> courseList;
	ArrayList<StudentList> studentList;
	DefaultTableModel tableModel;
	JScrollPane jScrollPane;
	JPanel pnlTable;
	

	public StudentListScreen() 
	{
		setSize( 700, 600 );
		setTitle( "List Of Students" );
		setLayout( null );

		pnlHeader = new JPanel();
		pnlHeader.setLayout( null );
		pnlHeader.setComponentOrientation( ComponentOrientation.LEFT_TO_RIGHT );
		pnlHeader.setBounds( 10, 10, 700, 100 );

		lblcourseName = new JLabel( "Select Course Name :" );
		lblcourseName.setBounds( 20, 20, 200, 30);

		courseList = DBOperations.getCourseList();

		String[] cList = new String[ courseList.size() ];

		int i=0;

		for ( CourseInfo c : courseList ) 
		{
			cList[i] = new String( c.courseName );
			i++;				
		}

		comboCourseList = new JComboBox<String>( cList );
		comboCourseList.setBounds( 200, 20, 100, 30 );		

		tblStudent = new JTable( );
		 
		btnSearch = new JButton( "Get Student List" );
		btnSearch.setBounds( 350, 20, 150, 30 );
		 
		pnlHeader.add( lblcourseName );
		pnlHeader.add( comboCourseList );
		pnlHeader.add( btnSearch );

		pnlHeader.setVisible( true );

		btnChallan = new JButton( "Challan Screen" );
		btnChallan.setBounds( 450, 500, 150, 30 );

		btnChallan.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
			
				// TODO Auto-generated method stub
				CourseInfo course = courseList.get( comboCourseList.getSelectedIndex() );
				//				System.out.println( tableModel.getValueAt( tblStudent.getSelectedRow(), tblStudent.getSelectedColumn() ).toString() );
				@SuppressWarnings("unchecked")
				Vector<StudentList> vec = tableModel.getDataVector();
				System.out.println( "Vec" + vec.toString() );
				System.out.println( "CName : " + course.courseName );

				ArrayList<Object> vals = new ArrayList<Object>( tableModel.getColumnCount() );

				for( int i=0; i<tableModel.getColumnCount();i++)
				{
					vals.add( tableModel.getValueAt( tblStudent.getSelectedRow(), i) );
					System.out.println( i + " : " + vals.get(i) );
				}

				int regNum = Integer.valueOf( vals.get(0).toString() );
				String studName = vals.get(1).toString();
				String cName = vals.get(2).toString();
				float balance = Float.valueOf( vals.get(3).toString().replaceAll(",", "") );

				StudentList list = new StudentList(regNum, studName, cName, balance );
				CourseInfo courseInfo = courseList.get( comboCourseList.getSelectedIndex() );

				StudentListScreen.this.dispose();
				new ChallanScreen( list, courseInfo );

			}
		});


		btnSearch.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String selCourseName = comboCourseList.getSelectedItem().toString();
				studentList = DBOperations.getCourseWiseStudentList( selCourseName );
				System.out.println( "SEL COURSE" + selCourseName );
				
				tableModel = null;
				tblStudent = null;

				tableModel = DBOperations.populateTable( selCourseName );

				tblStudent = new JTable();

				tblStudent.setModel( tableModel );
				
				// Center Aligned Text 
				DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
				cellRenderer.setHorizontalAlignment( SwingConstants.CENTER );
			
				for( int i=0; i<tblStudent.getColumnModel().getColumnCount(); i++)
				{
					TableColumn col = tblStudent.getColumnModel().getColumn(i);
					col.setCellRenderer(cellRenderer);
				}

				JTableHeader tableHeader = tblStudent.getTableHeader();
				tableHeader.setDefaultRenderer(cellRenderer);
				Border headerBorder = UIManager.getBorder("TableHeader.cellBorder");
				tableHeader.setBorder(headerBorder);


				System.out.println( tableModel.getColumnCount() + "  " + tableModel.getRowCount() + tableModel.getColumnName(1)  );

				tblStudent.updateUI();

				//' tblStudent.setVisible(true); 

				pnlTable = new JPanel( new BorderLayout() );

				JScrollPane jScrollPane = null;

				jScrollPane = new JScrollPane( tblStudent );
				jScrollPane.setVisible( true );
				jScrollPane.setHorizontalScrollBarPolicy( JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED );
				jScrollPane.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED );
				pnlTable.add(jScrollPane, BorderLayout.CENTER);

				pnlTable.setBounds( 100, 200, 450, 250);
				pnlTable.setVisible(true);

				pnlTable.repaint();

				StudentListScreen.this.add( pnlTable );

				repaint();

				revalidate();
		 

			}
		});


		comboCourseList.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				try
				{
					StudentListScreen.this.remove(pnlTable);
				}
				catch( NullPointerException e )
				{
					
				}
				
/*				if( tableModel.getRowCount() > 1 )
				{
					int rowCnt = tableModel.getRowCount();
					for( int i=0; i<rowCnt; i++ )
						tableModel.removeRow(i);

					tableModel = null;
					tblStudent = null;
				}
*/
				
			}
		}); 


		add( pnlHeader );
		add( btnChallan );

		setVisible( true );
		setDefaultCloseOperation( DISPOSE_ON_CLOSE );



	}
}
