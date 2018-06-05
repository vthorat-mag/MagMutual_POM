package com.mm.utils;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil
{
	String xlFilePath = "C://Users//VTHORAT//git//GIT_MM_LatestCode//MagMutual_POM//src//main//resources//Form_Data.xlsx";
    //String sheetName = "Credentials";
    ExcelApiTest eat = null;
	  public Object[][] testData(String sheetName) throws Exception
	    {
	        Object[][] excelData = null;
	        eat = new ExcelApiTest(xlFilePath);
	        int rows = eat.getRowCount(sheetName);
	        int columns = eat.getColumnCount(sheetName);	
	                 
	        excelData = new Object[rows-1][columns];
	         
	        for(int i=1; i<rows; i++)
	        {
	            for(int j=0; j<columns; j++)
	            {
	                excelData[i-1][j] = eat.getCellData(sheetName, j, i);
	            }
	        }
	        return excelData;
	    }
}