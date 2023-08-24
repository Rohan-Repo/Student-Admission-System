package in.easysystems.studentadmission.ui;

import in.easysystems.studentadmission.db.DBOperations;





import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

public class DateReportScreen extends JDialog 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JLabel lblDailyDeposit, lblDateWise, lblStartDate, lblEndDate;
	JFormattedTextField txtDailyDeposit, txtDateRangeStart, txtDateRangeEnd;
	JButton btnRetrieveReport, btnBack;
	JCheckBox chkToday, chkSingleDate, chkDateRange;


	public DateReportScreen() 
	{
		// TODO Auto-generated constructor stub
		setTitle( "Reports Screen" );
		setSize( 700, 600 );
		setLayout(null);
		this.setModal(true);
		
		btnBack = new JButton( new ImageIcon("D:\\IT\\backIcon_blue.gif") );
		btnBack.setBounds( 10, 10, 20, 20 );
		
		lblDateWise = new  JLabel( "Date-Wise Report" );
		lblDateWise.setBounds( 210, 10, 200, 30 );

		chkSingleDate = new JCheckBox( "Single Date :" );
		chkSingleDate.setBounds( 10, 50, 100, 30 );

		chkToday = new JCheckBox( "For Today?" );
		chkToday.setBounds( 10, 100, 100, 30 );
		chkToday.setSelected(true);

		lblDailyDeposit = new JLabel( "Enter Date (DD-MM-YYYY) " );
		lblDailyDeposit.setBounds( 10, 150, 150, 30 );

		MaskFormatter formatter = new MaskFormatter();
		try {
			formatter.setMask( "##/##/####" );
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		txtDailyDeposit = new JFormattedTextField( formatter );
		txtDailyDeposit.setBounds( 180, 150, 80, 30);

		chkDateRange = new JCheckBox( "Date Range :" );
		chkDateRange.setBounds( 10, 200, 100, 30 );

		lblStartDate = new JLabel( "Start Date (DD/MM/YYYY) :" );
		lblStartDate.setBounds( 10, 250, 150, 30 );

		txtDateRangeStart = new JFormattedTextField( formatter );
		txtDateRangeStart.setBounds( 180, 250, 80, 30 );

		lblEndDate = new JLabel( "End Date (DD/MM/YYYY) :" );
		lblEndDate.setBounds( 310, 250, 150, 30 );

		txtDateRangeEnd = new JFormattedTextField( formatter );
		txtDateRangeEnd.setBounds( 480, 250, 80, 30 );


		txtDateRangeStart.setEnabled(false); 
		txtDateRangeEnd.setEnabled(false);
		txtDailyDeposit.setEnabled(false);
		chkToday.setEnabled(false);
		lblDailyDeposit.setEnabled(false);
		 
		lblStartDate.setEnabled(false);
		lblEndDate.setEnabled(false);

		btnRetrieveReport = new JButton( "Retrieve Report" );
		btnRetrieveReport.setBounds( 30, 350, 150, 30);

		add( btnBack );
		add( lblDateWise );
		add( chkSingleDate );
		add( chkToday );
		add( lblDailyDeposit );
		add( txtDailyDeposit );
		add( chkDateRange );
		add( lblStartDate );
		add( txtDateRangeStart );
		add( lblEndDate );
		add( txtDateRangeEnd );
		add( btnRetrieveReport );


		chkToday.addItemListener( new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent arg0) 
			{
				lblDailyDeposit.setEnabled(true);
				txtDailyDeposit.setEnabled(true);

			}
		});

		chkSingleDate.addItemListener( new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent arg0) {
				// TODO Auto-generated method stub

				chkToday.setEnabled(true);
			}
		});

		chkDateRange.addItemListener( new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent arg0) {
				// TODO Auto-generated method stub

				lblStartDate.setEnabled(true);
				txtDateRangeStart.setEnabled(true);
				lblEndDate.setEnabled(true);
				txtDateRangeEnd.setEnabled(true);

			}
		});


		btnRetrieveReport.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				
				DefaultTableModel model = null;
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				LocalDate localDate = null;

				if( chkSingleDate.isSelected() )
				{
					if( ! chkToday.isSelected() )
						localDate = LocalDate.parse(txtDailyDeposit.getText(), formatter ); 
					else
						localDate = LocalDate.now();

					model = DBOperations.retreiveDayWiseReport(localDate);
					String sumStr = DBOperations.getFeesSumSingle( localDate );
					String dateString = localDate.format(formatter);
					String val = "From : " + dateString + " to " + dateString ;
					DateReportScreen.this.dispose();
					/*new StudentReport( "Student Fees Collection Report", true, val + txtDailyDeposit.getText(), 
							model, sum );*/
					new StudentReport( "Student Fees Collection Report", true, val , 
							model, sumStr );
					
					
				}
				else
				if( chkDateRange.isSelected() )
				{
					LocalDate dateStart, dateEnd;
					
					dateStart = LocalDate.parse( txtDateRangeStart.getText(), formatter );
					dateEnd = LocalDate.parse( txtDateRangeEnd.getText(), formatter );

					model = DBOperations.retreiveDateRangeReport(dateStart, dateEnd);
					//model = DBOperations_1.retreiveDateRangeReport(dateStart, dateEnd);
					String sumStr = DBOperations.getFeesSumRange(dateStart, dateEnd);
					String dateStr = "From " + txtDateRangeStart.getText() + " to " + txtDateRangeEnd.getText();
					DateReportScreen.this.dispose();
					new StudentReport( "Student Fees Collection Report", true,  dateStr, model, sumStr );

				}
			}
		});
		
		btnBack.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				DateReportScreen.this.dispose();
			}
		});

		setVisible( true );
		setDefaultCloseOperation( DISPOSE_ON_CLOSE );

	}
}
