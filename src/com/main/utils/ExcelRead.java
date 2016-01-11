package com.main.utils;


import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import org.apache.log4j.Logger;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class ExcelRead {
	
    Object[][] data = null;
    ArrayList<Object> topRowList = new ArrayList<>();
    private Logger logger;

    public ExcelRead() {
        logger = Logger.getLogger(ExcelRead.class);
    }
    
	    
    public Object[][] getSimpleExcelData(String filename, String sheetname) throws Exception {
 
	    FileInputStream fis = null;
	    try {
	    
	        // Create a FileInputStream that will be use to read the excel file.
	    	String fName = new File(String.format("%s/resources", System.getProperty("user.dir"))).getAbsolutePath()+"/"+filename;
	        fis = new FileInputStream(fName);
	     
	        // Create an excel workbook from the file system.
	        XSSFWorkbook workbook = new XSSFWorkbook(fis);
	        
	        // Get the first sheet on the workbook.
	        XSSFSheet sheet = workbook.getSheet(sheetname);
	        
	        int numOfRows = sheet.getLastRowNum() + 1;
	        int numOfColumns = countNonEmptyColumns(sheet);
	        data = new Object[numOfRows-1][numOfColumns];
	        
	        for(int rowNum = 1; rowNum < numOfRows; rowNum++)
	        {
	            XSSFRow row = sheet.getRow(rowNum);
	        
	            if (isEmpty(row)) {
	                break;
	            } else {
	                for (int colNum = 0; colNum < numOfColumns; colNum++) {
	                    Cell cell = row.getCell(colNum);
	                    if (cell != null 
	                    		&& (cell.getCellType() != Cell.CELL_TYPE_BLANK)) {
	                        data[rowNum - 1][colNum] = getCellValue(workbook, cell);
	                    }
	                }
	            }
	        }
	    }
	    catch(Exception e){
	    	logger.error(e);;
	    }
	    return data;
    }
    
    
    private Object getNumericCellValue(final Cell cell) {
        Object cellValue;
        if (DateUtil.isCellDateFormatted(cell)) {
            cellValue = new Date(cell.getDateCellValue().getTime());
        } else {
            cellValue = cell.getNumericCellValue();
        }
        return cellValue;
    }
    
    
    private Object getCellValue(XSSFWorkbook workbook, Cell cell) {
        Object cellValue = null;
        if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
           if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                cellValue = getNumericCellValue(cell);
            } else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
                cellValue = cell.getBooleanCellValue();
            }
            else {
                cellValue = cell.getStringCellValue();
            }
        }
        return cellValue;
    }
    
    
    
    private boolean isEmpty(XSSFRow row) {
        Cell firstCell = row.getCell(0);
        boolean rowIsEmpty = (firstCell == null)
                || (firstCell.getCellType() == Cell.CELL_TYPE_BLANK);
        return rowIsEmpty;
    }
    
    private int countNonEmptyColumns(XSSFSheet sheet) {
        XSSFRow firstRow = sheet.getRow(0);
        return firstEmptyCellPosition(firstRow);
    }
    

    private int firstEmptyCellPosition(XSSFRow cells) {
        int columnCount = 0;
        Iterator<Cell> iter = cells.cellIterator();

        while (iter.hasNext()) {
            XSSFCell cell = (XSSFCell) iter.next();
            if (cell.getCellType() == Cell.CELL_TYPE_BLANK) {
                break;
            }
            columnCount++;
        }
        return columnCount;
    }
	
}


