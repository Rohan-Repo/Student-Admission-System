package in.easysystems.studentadmission.ui;

import in.easysystems.studentadmission.oper.PrintingData;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

public class StudentReport extends JDialog
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JLabel lblCollegeName, lblReportName, lblDateRange, lblReportDateAndTime, lblTotalSum;
	JTable tblStudent;
	JPanel pnlTable;
	JScrollPane scrollPaneTable;
	JButton btnBack, btnPrint;
	String tableContents;
	
	public StudentReport( String reportName, boolean isDateReport, String rangeString, DefaultTableModel tableModelStudent, String sumStr ) 
	{

		setTitle( "Student Report" );
		setSize( 900, 750 );
		setLayout( null );
		this.setModal(true);

		btnBack = new JButton( new ImageIcon("D:\\IT\\backIcon_blue.gif") );
		btnBack.setBounds( 10, 10, 20, 20 );

		String clgName = "Sarhad College Of Arts, Commerce & Science";

		lblCollegeName = new JLabel( clgName );
		lblCollegeName.setBounds( 200, 10, 500, 30 );
		Font clgNm = new Font("Arial", Font.BOLD, 20 );
		lblCollegeName.setFont(clgNm);

		lblReportName = new JLabel( reportName );
		lblReportName.setBounds( 300, 50, 400, 30 );
		Font reportNm = new Font("Arial", Font.BOLD, 16 );
		lblReportName.setFont(reportNm);

		Font normalText = new Font("Arial", Font.BOLD, 12 );

		if( isDateReport )
		{
			lblDateRange = new JLabel( "Date Range : " + rangeString );
			lblDateRange.setBounds( 300, 100, 400, 30 );
			lblDateRange.setFont(normalText);

			String total = "Total Amount Received = " + sumStr; 
			lblTotalSum = new JLabel( total );
			lblTotalSum.setBounds( 600, 650, 300, 30 );
			lblTotalSum.setFont(reportNm);

			add( lblDateRange );
			add( lblTotalSum );
		}

		LocalDateTime localDateTime = LocalDateTime.now();
		LocalDate date = localDateTime.toLocalDate();
		LocalTime time = localDateTime.toLocalTime();
		String dateString = date.getDayOfMonth() + "/" + date.getMonthValue() + "/" + date.getYear();
		// To Remove the Nano Seconds
		String timeString = time.toString().substring( 0, 8 );
		String dateAndTimeString = "Report Date And Time : " + dateString + "  " + timeString;

		lblReportDateAndTime = new JLabel( dateAndTimeString );
		lblReportDateAndTime.setBounds( 10, 150, 400, 30 );
		lblReportDateAndTime.setFont(normalText);

		btnPrint = new JButton( new ImageIcon("D:\\IT\\print_icon.gif") );
		btnPrint.setBounds( 800, 150, 50, 50 );
		
		pnlTable = new JPanel( new BorderLayout() );
		pnlTable.setBounds( 10, 200, 850, 450 );


		tblStudent = new JTable();
		tblStudent.setModel( tableModelStudent );


		// Center Aligned Text & Border
		DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
		cellRenderer.setHorizontalAlignment( SwingConstants.LEFT );
		cellRenderer.setBorder( UIManager.getBorder("TableHeader.cellBorder") );

		for( int i=0; i<tblStudent.getColumnModel().getColumnCount(); i++)
		{
			TableColumn col = tblStudent.getColumnModel().getColumn(i);
			col.setCellRenderer(cellRenderer);
		}

		JTableHeader tableHeader = tblStudent.getTableHeader();
		tableHeader.setDefaultRenderer(cellRenderer);
		Border headerBorder = UIManager.getBorder("TableHeader.cellBorder");
		tableHeader.setBorder(headerBorder);

		
		scrollPaneTable = new JScrollPane( tblStudent );
		scrollPaneTable.setVisible( true );
		scrollPaneTable.setHorizontalScrollBarPolicy( JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED );
		scrollPaneTable.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED );
		pnlTable.add(scrollPaneTable, BorderLayout.CENTER);

		
		btnBack.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				StudentReport.this.dispose();
				
			}
			
		});

		btnPrint.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				/*
				lblCollegeName.setFont(null);
				lblReportName.setFont(null);
				lblReportDateAndTime.setFont(null);
				
				String clgNm = lblCollegeName.getText();
				String rprtNm = lblReportName.getText();
				String dtTym = lblReportDateAndTime.getText();
				
				String headerData = clgNm + "\n" +  rprtNm + "\n" + dtTym + "\n";
				*/
				
				MessageFormat tableDataHeader = new MessageFormat( reportName );
				MessageFormat tableDataFooter = null;
				
				if( isDateReport )
					tableDataFooter = new MessageFormat(  lblTotalSum.getText() );
				else
					tableDataFooter = new MessageFormat(  "Page Number" );
				
				
				try 
				{
					tblStudent.print( JTable.PrintMode.FIT_WIDTH, tableDataHeader, tableDataFooter );
				} 
				catch (PrinterException e) 
				{
					JOptionPane.showMessageDialog( StudentReport.this, "Unable To Print...", "Print Error", JOptionPane.ERROR_MESSAGE );
				}	

				
			}
		});
		
		add( btnBack );
		add( lblCollegeName );
		add( lblReportName );
		add( lblReportDateAndTime );
		add( pnlTable );
		add( btnPrint );
		setDefaultCloseOperation( DISPOSE_ON_CLOSE );
		setVisible( true );
		
		
	}
	
	/*
	private void showTableContents( DefaultTableModel tableModel ) 
	{
		// TODO Auto-generated method stub
		System.out.println( " \n Table Contents : \n ");
		
		int rowCnt = tableModel.getRowCount(), colCnt = tableModel.getColumnCount(), i,j;
		
		// 1 Row Added bcz Header Values Also need to be Added
		
		String[][] data = new String[ (rowCnt+1) ][colCnt];
		
		JTableHeader tableHeader = tblStudent.getTableHeader();
		TableColumnModel columnModel = tableHeader.getColumnModel();
		
		for ( j=0; j<columnModel.getColumnCount(); j++  ) 
		{
			TableColumn tableColumn = columnModel.getColumn( j );
			data[0][j] = tableColumn.getHeaderValue().toString();
		}
		
		
		for( i=1; i<rowCnt ; i++ )
		{
			for( j=0; j<colCnt ; j++ )
				data[i][j] = tableModel.getValueAt( i, j ).toString();
		}
		
		for( i=0; i<rowCnt ; i++ )
		{
			for( j=0; j<colCnt ; j++ )
				System.out.print( "\t " + data[i][j] );
			System.out.println( );
		}
		
		
	}	*/
	
}