package com.mm.utils;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil
{
    public FileInputStream fis = null;
    public XSSFWorkbook workbook = null;
    public XSSFSheet sheet = null;
    public XSSFRow row = null;
    public XSSFCell cell = null;
 
    
    //Method is used to set the Excel File Path
    public ExcelUtil() throws Exception
    {
        fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\resources\\Form_Data.xlsx");
        workbook = new XSSFWorkbook(fis);
        fis.close();
    }
            
 
    /**
     * This method is used to get the sheetName,rowNumber and ColumnName of Excel-Sheet.
     * @param sheetName
     * @param colName
     * @param rowNum
     * @return 
     */
    
    public String getCellData(String sheetName, String colName, int rowNum)
    {
        try
        {
            int col_Num = -1;
            sheet = workbook.getSheet(sheetName);
            row = sheet.getRow(0);
            for(int i = 0; i < row.getLastCellNum(); i++)
            {
                if(row.getCell(i).getStringCellValue().trim().equals(colName.trim()))
                    col_Num = i;
            }
 
            row = sheet.getRow(rowNum - 1);
            cell = row.getCell(col_Num);
 
            if(cell.getCellTypeEnum() == CellType.STRING)
                return cell.getStringCellValue();
            else if(cell.getCellTypeEnum() == CellType.NUMERIC)
            {
            	cell.setCellType(CellType.STRING);
            	return cell.getStringCellValue();
               
            }else if(cell.getCellTypeEnum() == CellType.BLANK)
                return "";
            else
                return String.valueOf(cell.getBooleanCellValue());
        }
        
        
        
        catch(Exception e)
        {
            e.printStackTrace();
            return "row "+rowNum+" or column "+colName +" does not exist  in Excel";
        }
    }
    
    //This method is used to take the row count
    public int getRowCount(int sheetIndex)
	{
		
		int row = workbook.getSheetAt(sheetIndex).getLastRowNum();	
		
		return row;
		
		
	}

}
