package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders 
{
	//DataProvide 1
		@DataProvider(name="LoginData")
		public String [][] getData() throws IOException
		{
			String path=".\\testData\\WebShop_LoginData.xlsx";   //taking xl file from testdata
			ExcelUtils xlutil=new ExcelUtils(path);  //create an object for ExcelUtils from utilities
			
			int totalrows=xlutil.getRowCount("Sheet1");
			int totalcols=xlutil.getCellCount("Sheet1",1);
			
			String logindata [][]=new String [totalrows][totalcols];  //created for two dimension array which can store
			
			for(int i=1;i<=totalrows;i++)  //read the data from xl storing in two dimensional array
			{
				for(int j=0;j<totalcols;j++)  //0 i is rows, j is cols
				{
					logindata [i-1][j]=xlutil.getCellData("Sheet1",i,j);  //1,0
				}
			}
			
			return logindata;  //returning two dimensional array
		}
		
}
				
