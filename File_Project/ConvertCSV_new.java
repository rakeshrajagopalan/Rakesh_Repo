import java.io.*;
import java.util.Locale;

import jxl.*;
import jxl.biff.XFRecord;
import jxl.format.Format;
import jxl.write.Number;
import jxl.write.NumberFormats;
import jxl.write.WritableCell;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

class ConvertCSV_new {

	public static void makeDirectory(String path) {
		File dir;
		dir = new File(path);

		if (!dir.exists()) {
			dir.mkdirs();
		}
	}

	public static String replace(String source, String pattern, String replace) {
		if (source != null) {
			final int len = pattern.length();
			StringBuffer sb = new StringBuffer();
			int found = -1;
			int start = 0;

			while ((found = source.indexOf(pattern, start)) != -1) {
				sb.append(source.substring(start, found));
				sb.append(replace);
				start = found + len;
			}

			sb.append(source.substring(start));

			return sb.toString();
		} else
			return "";
	}

	/*
	 * Add this method in your code and call it first. Then, a new workbook will
	 * be created and the format of the required columns in the workbook will be
	 * changed to TEXT. Now give this file as input to the program.
	 */

	private static void readFromXLFile(String[] args) throws Exception {
		// Existing file
		String fileName = args[0];
		String existingFileName = new String(fileName);
		Workbook workBook = Workbook.getWorkbook(new File(existingFileName));
		// New File
		WritableWorkbook wwb = Workbook.createWorkbook(new File(fileName),
				workBook);
		for (int i = 0; i < wwb.getNumberOfSheets(); i++) {
			WritableSheet sheet = wwb.getSheet(i);
			// I know that the column in 1 is Group Acct, as it is not going to
			// change. Add this code for other required columns as well.
			Cell[] cells = sheet.getColumn(1);
			try {
				// Iterating through all the cells in that column
				for (int j = 0; j < cells.length; j++) {
					WritableCell temp = null;
					temp = (WritableCell) cells[j];
					// Changing the format of all the cells
					WritableCellFormat cellFormat = new WritableCellFormat(
							NumberFormats.TEXT);
					temp.setCellFormat(cellFormat);
				}
				cells = sheet.getColumn(5);
				for (int j = 0; j < cells.length; j++) {
					WritableCell temp = null;
					temp = (WritableCell) cells[j];
					// Changing the format of all the cells
					WritableCellFormat cellFormat = new WritableCellFormat(
							NumberFormats.TEXT);
					temp.setCellFormat(cellFormat);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			wwb.write();
		}
		wwb.close();
	}

	public static void main(String[] args) throws Exception {
		readFromXLFile(args);
		File csv_File = null;
		int check = 1;
		String path = null;
		boolean isEmptyBook = false;
		String str = null;
		boolean exists_file;

		try {
			// Package p = ConvertCSV.class.getClass().;
			// System.out.println(p.getName());
			// File to store data in form of CSV

			switch (args.length)

			{
			case 3:
				check = 2;
				path = args[1];

				makeDirectory(path);
				// exists_file = (new File(args[0] + ".xls")).exists();
				exists_file = (new File(args[0])).exists();
				if (!exists_file) {
					System.out
							.println("File not found, Please enter a valid file");
					return;
				}
				break;

			case 2:
				path = args[1];

				// makeDirectory(path);
				makeDirectory(path);
				// exists_file = (new File(args[0] + ".xls")).exists();
				exists_file = (new File(args[0])).exists();
				if (!exists_file) {
					System.out
							.println("File not found, Please enter a valid file");
					return;
				}
				break;
			case 1:
				// exists_file = (new File(args[0] + ".xls")).exists();
				exists_file = (new File(args[0])).exists();
				if (!exists_file) {
					System.out
							.println("File not found, Please enter a valid file");
					return;
				}
				break;
			case 0:
				System.out.println("Please enter the file name");
				break;
			default:
				System.out.println("Incorrect number of parameters.");

			}

			String encoding = "UTF8";

			// Excel document to be imported
			// String filename = args[0] + ".xls";
			String filename = args[0];
			WorkbookSettings WB_Settings = new WorkbookSettings();

			WB_Settings.setLocale(new Locale("en", "EN"));
			Workbook wrkbk = Workbook.getWorkbook(new File(filename),
					WB_Settings);

			if (check == 2) {

				int sheetno = Integer.parseInt(args[2]);
				sheetno = sheetno - 1;
				if (sheetno < 0) {
					System.out
							.println("Please enter valid Sheet Number  greater than 0");
					return;
				}

				if (wrkbk.getNumberOfSheets() < sheetno + 1) {
					System.out.println("Sheet Number does not exist");
					return;
				}

				Sheet sht = wrkbk.getSheet(sheetno);
				Cell[] row = null;

				for (int i = 0; i < sht.getRows(); i++) {
					row = sht.getRow(i);
					if (null == row) {
						isEmptyBook = false;
					} else {
						isEmptyBook = true;
					}
				}
				if (!isEmptyBook) {
					System.out.println("Work book " + sht.getName()
							+ " is empty.");
					return;
				}

				csv_File = new File(path, args[0] + "_" + "Sheet" + sheetno
						+ ".csv");
				OutputStream os = (OutputStream) new FileOutputStream(csv_File);
				OutputStreamWriter osw = new OutputStreamWriter(os, encoding);
				BufferedWriter bw = new BufferedWriter(osw);
				/* To avoid sheet name printed at the first row */
				// bw.write(sht.getName());
				// bw.newLine();
				// Gets the cells from sheet
				for (int i = 0; i < sht.getRows(); i++) {
					row = sht.getRow(i);

					if (row.length > 0) {
						str = replace(row[0].getContents(), ",", "|");
						bw.write(str);

						for (int j = 1; j < row.length; j++) {
							bw.write(',');
							str = replace(row[j].getContents(), ",", "|");
							bw.write(str);
						}
					}
					bw.newLine();
				}
				bw.flush();
				bw.close();
			}

			if (check != 2) {
				// Gets the sheets from workbook
				for (int sheet = 0; sheet < wrkbk.getNumberOfSheets(); sheet++) {
					File Csv_File = null;
					Sheet sht = wrkbk.getSheet(sheet);
					Cell[] row = null;
					for (int i = 0; i < sht.getRows(); i++) {
						row = sht.getRow(i);
						if (null == row) {
							isEmptyBook = false;
						} else {
							isEmptyBook = true;
						}
					}
					if (!isEmptyBook) {
						System.out.println("Workbook " + sht.getName()
								+ " is empty.");
						return;
					}
					int Sheetno = sheet + 1;

					Csv_File = new File(path, args[0] + "_" + "Sheet" + Sheetno
							+ ".csv");
					OutputStream os1 = (OutputStream) new FileOutputStream(
							Csv_File);
					OutputStreamWriter osw1 = new OutputStreamWriter(os1,
							encoding);
					BufferedWriter bw1 = new BufferedWriter(osw1);
					/* To avoid sheet name printed at the first row */
					// bw1.write(sht.getName());
					// bw1.newLine();
					// Gets the cells from sheet
					for (int i = 0; i < sht.getRows(); i++) {
						row = sht.getRow(i);

						if (row.length > 0) {
							str = replace(row[0].getContents(), ",", "|");
							bw1.write(str);
							for (int j = 1; j < row.length; j++) {
								bw1.write(',');
								str = replace(row[j].getContents(), ",", "|");
								bw1.write(str);
							}
						}
						bw1.newLine();
					}
					bw1.flush();
					bw1.close();
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Too Many Parameter, please Enter 3 Parameters");
		}

		catch (RuntimeException e) {
			System.out
					.println("Runtime Error...Please Run the Program With Suitable Arguments ");
		}

		catch (jxl.read.biff.PasswordException e) {
			System.out
					.println("The WorkBook is Password Protected..Please Remove The Password from Workbook ");
		}

		catch (jxl.read.biff.BiffException e) {
			System.out
					.println("UnKnown File Format, Please Enter Valid Excel File name..");
		} catch (UnsupportedEncodingException e) {
			System.err.println("Please Enter Valid Excel File Name");
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Error in Opening File ...");
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}
}
