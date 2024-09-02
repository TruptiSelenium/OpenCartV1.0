package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {
	
	public  FileInputStream fi;
	public  FileOutputStream fo;
	public  XSSFWorkbook wb;
	public  XSSFSheet ws;
	public  XSSFRow row;
	public  XSSFCell cell;
	String path;
	
	public ExcelUtility(String path) {
		this.path= path;
	}
	public int getRowCount(String xlsheet) throws IOException {
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(xlsheet);
		int RowCount = ws.getLastRowNum();
		wb.close();
		fi.close();
		return RowCount;
	}
	

	public  int getCellCount(String xlsheet, int rownum) throws IOException {
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(xlsheet);
		row = ws.getRow(rownum);
		int cellCount = row.getLastCellNum();
		wb.close();
		fi.close();
		return cellCount;
		
	}
	
	public  String getCellData(String xlsheet,int rownum, int colnum) throws IOException {
	fi = new FileInputStream(path);
	wb = new XSSFWorkbook(fi);
	ws = wb.getSheet(xlsheet);
	row = ws.getRow(rownum);
	cell = row.getCell(colnum);
	String data = cell.toString();
	wb.close();
	fi.close();
	return data;
	
}
	
	
	public  void setCellData(String xlsheet, int rownum,int cellnum,String data) throws IOException { 
		
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(xlsheet);
		row = ws.getRow(rownum);
		cell = row.createCell(cellnum);
		cell.setCellValue(data);
		fo = new FileOutputStream(path);
		wb.write(fo);
		wb.close();
		fo.close();
		fi.close();
		
	}

}
