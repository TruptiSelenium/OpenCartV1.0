package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	//Dataprovider 1 
	
	//get data from excel and store it in the 2 D array 
	@DataProvider(name="Logindata")
	public String[][]  getData() throws IOException{
		String  path = ".//testData/OpenCart_LoginData.xlsx"; //path of the excel file where login data is present  
		ExcelUtility xlutil = new ExcelUtility(path);
		int rowcount = xlutil.getRowCount("Sheet1");
		int cellcount = xlutil.getCellCount("Sheet1", 1);
		String logindata[][] = new String [rowcount][cellcount];
		
		for(int i=1;i<=rowcount;i++) {
			for(int j=0;j<cellcount;j++) {
				logindata[i-1][j] = xlutil.getCellData("Sheet1", i, j); 
			}
		}
		return logindata;
	}
	
	
	//Dataprovider 2 
	
	//Dataprovider 3 
}
