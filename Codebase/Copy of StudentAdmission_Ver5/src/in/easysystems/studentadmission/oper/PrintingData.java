package in.easysystems.studentadmission.oper;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class PrintingData 
{
	public static String concatJTableData( JTable table ) 
	{
		StringBuffer buffer = new StringBuffer();
		
		buffer.append(  " \n Table Contents : \n " );
		
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		
		int rowCnt = tableModel.getRowCount(), colCnt = tableModel.getColumnCount(), i,j;
		
		// 1 Row Added bcz Header Values Also need to be Added
		
		String[][] data = new String[ (rowCnt+1) ][colCnt];
		
		JTableHeader tableHeader = table.getTableHeader();
		
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
				buffer.append( "\t " + data[i][j] );
			buffer.append( "\n" );
		}
		

		return buffer.toString();
	}
	
	public static String concatFeesCollectionReportData	( 	String clgName, String reportName,
															String rangeString,
															String reportDateAndTime,				
															DefaultTableModel tableModel,
															String amountReceived
														) 
	{
		return null;
	}
}
