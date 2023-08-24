package in.easysystems.studentadmission.ui;

import java.awt.BorderLayout;

import in.easysystems.studentadmission.db.DBOperations;

import javax.swing.JDialog;
import javax.swing.JFrame;
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

public class PayableFeesReport extends JFrame
{
	JTable tblStudFees;
	JPanel pnlTable;
	JScrollPane scrollPaneTable;
	
	public PayableFeesReport( int studRegNum, int courseNum )
	{
		setTitle( "Payable Fees Report" );
		setSize( 700, 100 );
		setLayout( null );
		
		DefaultTableModel tableModel = DBOperations.retrievePayableFeesReport( studRegNum, courseNum );
		tblStudFees = new JTable( tableModel );

		// Center Aligned Text & Border
		DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
		cellRenderer.setHorizontalAlignment( SwingConstants.CENTER );
		cellRenderer.setBorder( UIManager.getBorder("TableHeader.cellBorder") );

		for( int i=0; i<tblStudFees.getColumnModel().getColumnCount(); i++)
		{
			TableColumn col = tblStudFees.getColumnModel().getColumn(i);
			col.setCellRenderer(cellRenderer);
		}

		JTableHeader tableHeader = tblStudFees.getTableHeader();
		tableHeader.setDefaultRenderer(cellRenderer);
		Border headerBorder = UIManager.getBorder("TableHeader.cellBorder");
		tableHeader.setBorder(headerBorder);

		pnlTable = new JPanel();
		scrollPaneTable = new JScrollPane( tblStudFees );
		scrollPaneTable.setVisible( true );
		scrollPaneTable.setHorizontalScrollBarPolicy( JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED );
		scrollPaneTable.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED );
		pnlTable.add(scrollPaneTable, BorderLayout.CENTER);
		pnlTable.setBounds( 10, 10, 700, 200 );

		add( pnlTable );

		//setModal(true);
		setVisible(true);
		setDefaultCloseOperation( DISPOSE_ON_CLOSE );
		
	}
}
