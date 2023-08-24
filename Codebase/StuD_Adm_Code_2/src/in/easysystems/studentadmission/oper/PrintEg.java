package in.easysystems.studentadmission.oper;

import java.awt.print.PageFormat;
import java.awt.print.PrinterJob;

public class PrintEg 
{
	public static void main(String[] args) {

		try
		{
			PrinterJob prnJob = PrinterJob.getPrinterJob();;
			PageFormat pf;

			pf = prnJob.pageDialog(prnJob.defaultPage());
			
			prnJob.print();
			
		}
		catch( Exception e)
		{
			e.printStackTrace();
		}
	}



}
