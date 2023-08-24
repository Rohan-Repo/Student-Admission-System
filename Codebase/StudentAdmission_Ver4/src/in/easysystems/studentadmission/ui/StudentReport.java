package in.easysystems.studentadmission.ui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
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
	JButton btnBack;
	
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
		
		pnlTable = new JPanel( new BorderLayout() );
		pnlTable.setBounds( 10, 200, 850, 450 );
		
		
		tblStudent = new JTable();
		tblStudent.setModel( tableModelStudent );
		
			
			// Center Aligned Text & Border
			DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
			cellRenderer.setHorizontalAlignment( SwingConstants.CENTER );
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
			
			/*
			TableColumn challaNum = tblStudent.getColumnModel().getColumn(0);
			challaNum.setCellRenderer(cellRenderer);
			
			TableColumn regNum = tblStudent.getColumnModel().getColumn(2);
			regNum.setCellRenderer(cellRenderer);
			
			TableColumn amtPaid = tblStudent.getColumnModel().getColumn(5);
			amtPaid.setCellRenderer(cellRenderer);
			*/
		
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

		add( btnBack );
		add( lblCollegeName );
		add( lblReportName );
		add( lblReportDateAndTime );
		add( pnlTable );
		setDefaultCloseOperation( DISPOSE_ON_CLOSE );
		setVisible( true );
	}
}
