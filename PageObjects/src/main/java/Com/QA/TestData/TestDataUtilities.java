package Com.QA.TestData;

	import Com.QA.Base.*;
	import java.io.FileInputStream;
	import java.io.IOException;
	import org.apache.poi.ss.usermodel.Sheet;
	import org.apache.poi.xssf.usermodel.XSSFRow;
	import org.apache.poi.xssf.usermodel.XSSFSheet;
	import org.apache.poi.xssf.usermodel.XSSFWorkbook;
	
	public class TestDataUtilities extends TestBase {
	 
		public TestDataUtilities() throws IOException {
			super();
			// TODO Auto-generated constructor stub
		}


		public static String value;
		public static int rowValue;

	  public static int TestScenarioRow(String Scenario, String Instance) throws IOException {
		  
		  FileInputStream fs= new FileInputStream("C:\\Users\\Kamesh\\Desktop\\fb.xlsx");
		  XSSFWorkbook kameshWorkbook = new XSSFWorkbook(fs);
		  XSSFSheet kameshWorkSheet = kameshWorkbook.getSheet("Sheet1");
		  int rowCount = (kameshWorkSheet.getLastRowNum()+1)-kameshWorkSheet.getFirstRowNum();
		  System.out.println(rowCount);
		  for (int i = 0; i < rowCount+1; i++) {
			  XSSFRow row = kameshWorkSheet.getRow(i);
		  for (int j = 0; j < row.getLastCellNum(); j++) {
			  String scenarioValue = row.getCell(j).getStringCellValue();
			  if(scenarioValue.indexOf(Scenario) != -1)
		      {  System.out.print(scenarioValue+" matches "+Scenario);
		         String instanceValue = row.getCell(j+1).getStringCellValue();
				  if(instanceValue.indexOf(Instance) != -1){
					  System.out.println("Scenario("+Scenario+")Instance("+Instance+") are found in the datasheet");
					  rowValue = row.getRowNum();
					  return rowValue;
				  }
				  else
					  System.out.println("Instance is not found in this cell of the row");
				      break;
				  }
		       
		       else
		       {System.out.println("Scenario is not with the row"+row.getRowNum());}
		  }
	  }
		return (Integer) null;}
		
		
	  public static String TestData(String value) throws IOException {
		  System.out.println(value);
		  FileInputStream fs= new FileInputStream("C:\\Users\\Kamesh\\Desktop\\fb.xlsx");
		  XSSFWorkbook kameshWorkbook = new XSSFWorkbook(fs);
		  XSSFSheet kameshWorkSheet = kameshWorkbook.getSheet("Sheet1");
		  int rowCount = (kameshWorkSheet.getLastRowNum()+1)-kameshWorkSheet.getFirstRowNum();
		  XSSFRow row = kameshWorkSheet.getRow(TestScenarioRow(Pro.getProperty("Scenario"),Pro.getProperty("Instance")));
		  XSSFRow topRow = kameshWorkSheet.getRow(0);
		  for (int j = 0; j < topRow.getLastCellNum(); j++) {
			  String cellValue = topRow.getCell(j).getStringCellValue();
			 if(cellValue.indexOf(value) != -1)
		      {  System.out.print(cellValue+" matches "+value);
				 return row.getCell(j).getStringCellValue();
			  }
		       /*else{
		    	   System.out.print("ColumnName "+value+" does not exist");
		    	   }*/
			 }
		  return null;}
	  
	}
