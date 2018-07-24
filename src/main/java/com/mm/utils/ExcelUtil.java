package com.mm.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {
	String xlFilePath = System.getProperty("user.dir")+"\\src\\main\\resources\\BTS_Form_Data.xlsx";
	
	ExcelApiTest eat = null;
	List<String> listRowData =null;

	//Logic to get the excel sheet data into object.
	public HashMap<String, List<String>> testData(String sheetName) throws Exception {
		
		HashMap<String, List<String>> excelData = new HashMap<String, List<String>>();
		//Read excel sheet and get rows and column count
		eat = new ExcelApiTest(xlFilePath);
		int rows = eat.getRowCount(sheetName);
		int columns = eat.getColumnCount(sheetName);

		//Browse through all columns
		for (int j = 0; j < columns; j++) {
			//Read the ColumnName
			String sColumnName = eat.getCellData(sheetName, j, 0).toLowerCase();
			listRowData = new ArrayList<String>();
			
			//Read All rows
			for (int i = 1; i < rows; i++) {
				listRowData.add(eat.getCellData(sheetName, j, i));
				//ToDo- Check if the field is blank, don't add in list
			}			
			//Populate the HashMap
			
			excelData.put(sColumnName, listRowData);			
		}
		
		return excelData;
	}
	
	public void downloadExcel() throws Exception
	{
		Thread.sleep(6000);
		String[] executionPath = {System.getProperty("user.dir") + "\\src\\main\\java\\autoItScripts\\saveExcel.exe"};
		Runtime.getRuntime().exec(executionPath);
		Thread.sleep(12000);
	}
	
	
	public void writeData(String testCaseId, String columnName, String cellValue, int rowNum,String saveDataFilePath) throws Exception {
		String excelFilePath = saveDataFilePath;
		FileInputStream inputStream;

		inputStream = new FileInputStream(new File(excelFilePath));
		try {

			XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
			XSSFSheet dataSheet = (XSSFSheet) workbook.getSheet(testCaseId);

			Row headerRow = dataSheet.getRow(0);

			for (int cellNumber = headerRow.getFirstCellNum(); cellNumber <= headerRow.getLastCellNum()
					- 1; cellNumber++) {
				Cell headerCell = headerRow.getCell(cellNumber);
				System.out.println(headerCell.getStringCellValue().toLowerCase());
				if (headerCell.getStringCellValue().toLowerCase().trim().equals(columnName.toLowerCase())) {
					Row dataRow = dataSheet.getRow(rowNum);
					Cell dataCell = dataRow.getCell(cellNumber);
					dataCell.setCellValue(cellValue);
					break;
				}

			} // for loop - cellNumber
			inputStream.close();
			FileOutputStream outputStream = new FileOutputStream(excelFilePath);
			workbook.write(outputStream);
			workbook.close();
			outputStream.flush();
			outputStream.close();

		} catch (NoSuchElementException e) {

			e.printStackTrace();
		}

	}
}

/*for (int i = 1; i < rows; i++) {
for (int j = 0; j < columns; j++) {
	excelData[i - 1][j] = eat.getCellData(sheetName, j, i);
}
}*/