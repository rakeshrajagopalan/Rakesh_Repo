package testpack;

import java.io.File;

import jxl.Cell;
import jxl.CellFeatures;
import jxl.Range;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.CellFormat;
import jxl.write.NumberFormats;
import jxl.write.WritableCell;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.biff.CellValue;

public class TestClass {

	public TestClass() {
		try {
			readFromXLFile();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/*
	 * Add this method in your code and call it first. Then, a new workbook will
	 * be created and the format of the required columns in the workbook will be
	 * changed to TEXT. Now give this file as input to the program.
	 */

	private void readFromXLFile() throws Exception {
		// Existing file
		String existingFileName = new String("F:\\Srini_Project\\Test.xls");
		Workbook workBook = Workbook.getWorkbook(new File(existingFileName));
		// New File
		WritableWorkbook wwb = Workbook.createWorkbook(new File(
				"F:\\Srini_Project\\Test1.xls"), workBook);
		WritableSheet sheet = wwb.getSheet(0);
		// I know that the column in 1 is Group Acct, as it is not going to
		// change. Add this code for other required columns as well.
		Cell[] cells = sheet.getColumn(1);
		try {
			// Iterating through all the cells in that column
			for (int i = 0; i < cells.length; i++) {
				WritableCell temp = null;
				temp = (WritableCell) cells[i];
				// Changing the format of all the cells
				WritableCellFormat cellFormat = new WritableCellFormat(
						NumberFormats.TEXT);
				temp.setCellFormat(cellFormat);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		wwb.write();
		wwb.close();
	}

	public static void main(String[] args) {
		new TestClass();
	}

}
