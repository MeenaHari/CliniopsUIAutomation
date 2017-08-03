package com.testing.Cliniops;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class Cliniops_ReusableMethodsTest {   


	static String htmlname = null;
	static BufferedWriter bw = null;
	static String exeStatus = "True";
	static int report;
	static int rowCount = 1;
	private static String browserName = null;
	private static int reportFlag = 0;


	/**
	 * Used to click on a button
	 * @param obj: WebElement button to be clicked
	 * @param objName: Name of button
	 * @param stepName: Name of step
	 * @throws IOException
	 */
	/*
	public static void buttonClick(WebElement obj,String objName, String stepName) throws IOException{
		if(obj.isDisplayed()){
			obj.click();
			updateReport("Pass",stepName ,objName+" is clicked");
		}
		else{
			updateReport("Fail",stepName ,objName+" is not displayed");
		}	

	}*/

	/**
	 * Check if the object is enabled or not
	 * @param obj
	 * @param objname
	 * @throws IOException
	 */
	public static void checkDisabled(WebElement obj,String objname) throws IOException{
		if(obj.getAttribute("disabled").trim().contains("true")){
			updateReport("Pass","Checkdisabled",objname+" is disabled");
		}
		else{
			updateReport("Fail","Checkdisabled",objname+" is not disabled");
		}
	}

	/**
	 * To click on specified object
	 * @param obj: WebElement to be clicked
	 * @param objName: Name of WebElement to be clicked
	 * @throws IOException
	 */
	public static void clickElement(WebElement obj, String objName, String stepName) throws IOException{
		if(obj.isDisplayed()){
			obj.click();
			updateReport("Pass", stepName , objName + " is clicked");
			//System.out.println("Pass: "+ objName + " is clicked.");
		}
		else{
			updateReport("Fail", stepName , objName + " is not displayed");
		}
	}


	/**
	 * Closes the buffered writer and resets all the variables
	 * @throws IOException
	 */
	public static void closeReport() throws IOException{
		rowCount = 1;
		browserName = null;
		reportFlag = 0;
		htmlname = null;
		bw.close();
	}

	/**
	 * To select the drop down
	 * @param dd: WebelEment drop down
	 * @param index: Index of element to be selected
	 * @throws IOException
	 */
	public static void dropDown(WebElement dd, int index) throws IOException{
		Select select = new Select(dd);
		if(dd.isDisplayed()){
			select.selectByIndex(index);
			updateReport("Pass", "DropDown", "selected dd object by using index");
		}
		else{
			updateReport("Fail", "DropDown", "Not selected dd object by using index");
		}

	}
	/**
	 * To select the drop down by value
	 * @param dd: WebelEment drop down by value
	 * @param index: Index of element to be selected
	 * @throws IOException
	 */
	public static void dropDownByValue(WebElement dd1, String value) throws Exception{
		Select select = new Select(dd1);
		if(dd1.isDisplayed()){
		select.selectByValue(value);
		updateReport("Pass", "DropDown", "selected dd object by using value");
		}
	   else{
		   updateReport("Fail", "DropDown", "Not selected dd object by using value");
		   }
		}


	/**
	 * Used to enter text into text box field
	 * @param obj: WebElement object in which text needs to be entered
	 * @param textVal: String value to be entered
	 * @param objName: Name of object
	 * @param stepName: Name of step
	 * @throws IOException
	 */
	public static void enterText(WebElement obj, String textVal, String objName, String stepName) throws IOException{
		if(obj.isDisplayed()){
			obj.sendKeys(textVal);
			updateReport("Pass", stepName ,textVal+" is entered in "+objName);

		}
		else{
			updateReport("Fail", stepName ,objName+" field is not displayed,please check application");
		}
	}	

	/**
	 * Used to display message on matching actual text with expected text
	 * @param obj
	 * @param Expectedtext
	 * @param objname
	 * @throws IOException
	 */
	public static void errorMessage(WebElement obj,String expectedText,String objname) throws IOException{
		if(obj.isDisplayed())
		{
			String actualText=obj.getText().trim();
			if(expectedText.trim().contains(actualText)){
				updateReport("Pass","ErrorMessage","Actual message matching with expected message:"+actualText);
			}
			else{
				updateReport("Fail","ErrorMessage","Actual message not matching with expected message:"+actualText);
			}
		}
		else{
			updateReport("Fail","ErrorMessage",objname+" is not displayed,please check your application");
		}
	}

	/**
	 * Read checkBox text
	 * @param obj
	 * @param Expectedtext
	 * @param objname
	 * @throws IOException
	 */
	public static void readingCheckbox(WebElement obj,String expectedText,String objname) throws IOException{
		if(obj.isDisplayed()){
			String actualText=obj.getAttribute("checked").trim();
			if(expectedText.equals(actualText)){
				updateReport("Pass","readingCheckbox",objname+" is checked");
			}
			else{
				updateReport("Fail","readingCheckbox",objname+" is not checked");
			}
		}
		else{
			updateReport("Fail","readingCheckbox",objname+" is not displayed,please check your application");
		}
	}

	/**
	 * Read text box value
	 * @param obj
	 * @param objname
	 * @throws IOException
	 */
	public static void readingText(WebElement obj,String objname) throws IOException{
		if(obj.isDisplayed()){
			String Actualtext=obj.getText().trim();
			if(Actualtext.isEmpty()){
				updateReport("Fail","ReadingText",objname+" has No data");
			}
			else{
				updateReport("Pass","ReadingText",objname+" contains "+Actualtext);
			}
		}
	}

	/**
	 * Read data from excel sheet
	 * @param filePath
	 * @param sheetName
	 * @return
	 * @throws IOException
	 */
	public static String[][] readSheet(String filePath, String sheetName) throws IOException{

		/*Step 1: Get the XL Path*/
		File xlFile = new File(filePath);

		/*Step2: Access the Xl File*/
		FileInputStream xlDoc = new FileInputStream(xlFile);

		/*Step3: Access the work book */
		HSSFWorkbook wb = new HSSFWorkbook(xlDoc);

		/*Step4: Access the Sheet */
		HSSFSheet sheet = wb.getSheet(sheetName);

		int iRowCount =  sheet.getLastRowNum()+1;
		int iColCount = sheet.getRow(0).getLastCellNum();//Row count starts from '0' in excel

		System.out.println("Total row = " + iRowCount + " total col = " + iColCount);

		String[][] xlData = new String[iRowCount][iColCount];

		for(int i =0; i<iRowCount;i++){
			for(int j = 0; j <iColCount;j++){
				//sheet.getRow(i).getCell(j).getNumericCellValue()
				Cell cell = sheet.getRow(i).getCell(j);
				switch(cell.getCellType()){
					case Cell.CELL_TYPE_STRING:
						xlData[i][j] = sheet.getRow(i).getCell(j).getStringCellValue().trim();
						break;
					case Cell.CELL_TYPE_NUMERIC:
						xlData[i][j] = new Double(sheet.getRow(i).getCell(j).getNumericCellValue()).toString() ;
						break;
				}
				
			}
		}

		//Close workbook
		wb.close(); 
		return xlData;
	}	

	/**
	 * Start HTML report for the test script
	 * @param scriptName
	 * @param reportsPath
	 * @param browser
	 * @throws IOException
	 */
	public static void startReport(String scriptName, String reportsPath, String browser) throws IOException{

		browserName = browser;
		if(reportFlag == 0){
			reportFlag = 1;
			String strResultPath = null;
			String testScriptName =scriptName;

			Date cur_dt = new Date(); 
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
			String strTimeStamp = dateFormat.format(cur_dt);

			if (reportsPath == "") { 
				reportsPath = "C:\\";
			}

			if (!reportsPath.endsWith("\\")) { 
				reportsPath = reportsPath + "\\";
			}

			strResultPath = reportsPath + "Log" + "/" +testScriptName +"/"; 
			File f = new File(strResultPath);
			f.mkdirs();
			htmlname = strResultPath  + testScriptName + "_" + strTimeStamp 
					+ ".html";

			bw = new BufferedWriter(new FileWriter(htmlname));

			bw.write("<HTML><BODY><TABLE BORDER=0 CELLPADDING=3 CELLSPACING=1 WIDTH=100%>");
			bw.write("<TABLE BORDER=0 BGCOLOR=BLACK CELLPADDING=3 CELLSPACING=1 WIDTH=100%>");
			bw.write("<TR><TD BGCOLOR=#66699 WIDTH=30%><FONT FACE=VERDANA COLOR=WHITE SIZE=2><B>Browser Name</B></FONT></TD><TD COLSPAN=6 BGCOLOR=#66699><FONT FACE=VERDANA COLOR=WHITE SIZE=2><B>"
					+ browserName + "</B></FONT></TD></TR>");
			bw.write("<HTML><BODY><TABLE BORDER=1 CELLPADDING=3 CELLSPACING=1 WIDTH=100%>");
			bw.write("<TR COLS=7><TD BGCOLOR=#BDBDBD WIDTH=5%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>SL No</B></FONT></TD>"
					+ "<TD BGCOLOR=#BDBDBD WIDTH=10%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>Step Name</B></FONT></TD>"
					+ "<TD BGCOLOR=#BDBDBD WIDTH=10%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>Execution Time</B></FONT></TD> "
					+ "<TD BGCOLOR=#BDBDBD WIDTH=10%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>Status</B></FONT></TD>"
					+ "<TD BGCOLOR=#BDBDBD WIDTH=40%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>Detail Report</B></FONT></TD></TR>");
		}
		else{
			bw.write("<TABLE BORDER=0 BGCOLOR=BLACK CELLPADDING=3 CELLSPACING=1 WIDTH=100%>");
			bw.write("<TR><TD BGCOLOR=#66699 WIDTH=30%><FONT FACE=VERDANA COLOR=WHITE SIZE=2><B>Browser Name</B></FONT></TD><TD COLSPAN=6 BGCOLOR=#66699><FONT FACE=VERDANA COLOR=WHITE SIZE=2><B>"
					+ browserName + "</B></FONT></TD></TR>");
			bw.write("<HTML><BODY><TABLE BORDER=1 CELLPADDING=3 CELLSPACING=1 WIDTH=100%>");
		}
	}
	
	/**
	 * Update HTML report for each step
	 * @param Res_type
	 * @param Action
	 * @param result
	 * @throws IOException
	 */
	public static void updateReport(String resType,String action, String result) throws IOException {
		String str_time;
		Date exec_time = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		str_time = dateFormat.format(exec_time);
		if (resType.startsWith("Pass")) {
			bw.write("<TR COLS=7><TD BGCOLOR=#EEEEEE WIDTH=5%><FONT FACE=VERDANA SIZE=2>"
					+ (rowCount++)
					+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><FONT FACE=VERDANA SIZE=2>"
					+ action
					+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><FONT FACE=VERDANA SIZE=2>"
					+ str_time
					+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><FONT FACE=VERDANA SIZE=2 COLOR = GREEN>"
					+ "Passed"
					+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=40%><FONT FACE=VERDANA SIZE=2 COLOR = GREEN>"
					+ result + "</FONT></TD></TR>");

		} else if (resType.startsWith("Fail")) {
			exeStatus = "Failed";
			report = 1;
			bw.write("<TR COLS=7><TD BGCOLOR=#EEEEEE WIDTH=5%><FONT FACE=VERDANA SIZE=2>"
					+ (rowCount++)
					+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><FONT FACE=VERDANA SIZE=2>"
					+ action
					+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><FONT FACE=VERDANA SIZE=2>"
					+ str_time
					+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><FONT FACE=VERDANA SIZE=2 COLOR = RED>"
					+ "<a href= "
					+ htmlname
					+ "  style=\"color: #FF0000\"> Failed </a>"

					+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=40%><FONT FACE=VERDANA SIZE=2 COLOR = RED>"
					+ result + "</FONT></TD></TR>");

		} 
	}

	/**
	 * Validate if message is as expected
	 * @param obj
	 * @param expectedText
	 * @param objName
	 * @throws IOException
	 */
	/*public static void validateMsg(WebElement obj, String expectedText, String objName) throws IOException{
		if(obj.isDisplayed()){
			String actualText = obj.getText().trim();
			if(expectedText.equals(actualText)){
				updateReport("Pass", "validate message", "Actual message is matching with expected message");
			}else{
				updateReport("Fail", "validate message", "Actual message is matching with expected message");
			}
		}else{
			updateReport("Fail",objName," is not displayed, please check your application");
		}
	}
	*/

	/**
	 * To validate if the text on element matches expectedTest
	 * @param obj: WebElement whose text needs to be verified
	 * @param expectedText: Expected value of text
	 * @param objName: Name of object
	 * @param stepName: StepName
	 * @throws IOException
	 */
	public static void validateText(WebElement obj,String expectedText,String objName,
			String stepName) throws IOException{
		if(obj.isDisplayed()){
			String actualText=obj.getText().trim();
			if(expectedText.trim().contains(actualText)){
				updateReport("Pass",stepName,"Actual text matching with expected text");
			}
			else{
				updateReport("Fail",stepName,"Actual text not matching with expected text");
			}
		}
		else{
			updateReport("Fail",stepName,objName+" is not displayed,please check your application");
		}
	}

	/**
	 * Validate message displayed on the web page
	 * @param obj
	 * @param expectedText
	 * @param objName
	 * @param attributeName
	 * @param stepname
	 * @throws IOException
	 */
	public static void validateTextAttribute(WebElement obj, String expectedText, String objName,
			String attributeName,String stepName) throws IOException{
		if(obj.isDisplayed()){
			String actualText = obj.getAttribute(attributeName);
			if(expectedText.equals(actualText)){
				updateReport("Pass",stepName,"Actual text matching with expected text");
			}else{
				updateReport("Fail",stepName,"Actual text not matching with expected text");
			}
		}
		else{
			updateReport("Fail",stepName,objName +"is not displayed, please check your application");
		}
	}
}
