package com.testing.Cliniops;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Cliniops_ReusableMethodsTest {   
  
	static WebDriver driver ;  
	static String htmlname;
	static Date cur_dt = null;
	static BufferedWriter bw = null;
	static BufferedWriter bw1 = null;
	static String exeStatus = "True";
	static int report;
	static int j = 1;


	/* 
	 * Name of the Method: enterText
	 * Brief description : Enter text into text box field
	 * Arguments: obj --> Webelement Object, textval --> Text to be entered, objName --> name of hte object
	 * Created by: Automation team
	 * Creation date : July 17 2017
	 * last modified:  July 17 2017
	 * */
	public static void enterText(WebElement obj, String textVal, String objName) throws IOException{
		if(obj.isDisplayed()){
			obj.sendKeys(textVal);
			Update_Report("Pass","entertext",textVal+" is entered in "+objName);

		}else{
			Update_Report("Fail","entertext",objName+" field is not displayed,please check application");

		}

	}
	/* 
	 * Name of the Method: dropDown
	 * Brief description : Selecting the DropDown
	 * Arguments: obj --> Webelement Object, textval --> Text to be entered 
	 * Created by: Automation team
	 * Creation date : July 17 2017
	 * last modified:  July 17 2017
	 * */
	public static void dropDown(WebElement dd, int index) throws IOException{

		Select select = new Select(dd);
		if(dd.isDisplayed()){
			select.selectByIndex(index);
			Update_Report("Pass", "DropDown", "selected dd object by using index");
		}
		else{
			Update_Report("Fail", "DropDown", "Not selected dd object by using index");
		}

	}

	/* 
	 * Name of the Method: clickObj
	 * Brief description : click object
	 * Arguments: obj --> Webelement Object,  objName --> name of the object
	 * Created by: Automation team
	 * Creation date : July 17 2017
	 * last modified: July 17 2017
	 * 
	 * */	
	public static void clickObj(WebElement obj, String objName) throws IOException{
		if(obj.isDisplayed()){
			obj.click();
			Update_Report("Pass", "click Action", "clicked on selected object");
			System.out.println("Pass: "+ objName + " is clicked.");
		}else{
			Update_Report("Fail", "click Action", "Not clicked on selected object");
		}
	}

	//Name of the method:ButtonClick
	//Brief description:used to click the button
	//arguments:obj->WebElement,objname->name of the object
	//created by:Automation team
	//creation date:12/15/2016
	//modified date:12/15/2016
	/*public static void ButtonClick(WebElement obj,String objname) throws IOException{
		if(obj.isDisplayed())
		{
			obj.click();
			Update_Report("Pass","ButtonClick",objname+" is clicked");
		}
		else{
			Update_Report("Fail","ButtonClick",objname+" is not displayed");
		}	
		
	}*/
	

	/* 
	 * Name of the Method: validate
	 * Brief description : validate text displayed on the web page
	 * Arguments: obj --> Webelement Object,  expectedText --> expected text to be displayed, objName --> name of hte object
	 * Created by: Automation team
	 * Creation date : July 17 2017
	 * last modified: July 17 2017
	 * 
	 * */	

	public static void validate(WebElement obj, String expectedText, String objName,String stepName) throws IOException{
		if(obj.isDisplayed()){
			String actualText = obj.getText().trim();
			if(expectedText.equals(actualText)){
				Update_Report("Pass", stepName, "Actual text is matching with expected text");
			}else{
				Update_Report("Fail", stepName, "Actual text is not matching with expected text");
			}
		}else{
			Update_Report("Fail",stepName,objName+ " is not displayed, please check your application");
		}
	}
	
	
	/* 
	 * Name of the Method: validateMsg_Attribute
	 * Brief description : validate message displayed on the web page
	 * Arguments: obj --> Webelement Object,  expectedText --> expected text to be displayed, objName --> name of the object
	 * Created by: Automation team
	 * Creation date : July 20 2017
	 * last modified: July 20 2017
	 * 
	 */	

	public static void validateMsg_Attribute(WebElement obj, String expectedText, String objName,String attributeName) throws IOException{
		//if(obj.isDisplayed()){
		if(obj.isEnabled()){	
			String actualText = obj.getAttribute(attributeName);
			if(expectedText.equals(actualText)){
				Update_Report("Pass","validateMsg_Attribute","Actual message matching with expected message:"+ actualText);
			}else{
				Update_Report("Fail","validateMsg_Attribute","Actual message not matching with expected message:"+actualText);
			}
		}else{
			Update_Report("Fail","validateMsg_Attribute",objName +"is not displayed, please check your application");
		}
	}
	//Name of the method:checkDisabled 
		//Brief description:check disabled object
		//arguments:obj->WebElement,objname->name of the object
		//created by:Automation team
		//creation date:07/24/2017
		//modified date:07/24/2017
	
	public static void checkDisabled(WebElement obj,String objname) throws IOException{
		if(obj.getAttribute("disabled").trim().contains("true")){Update_Report("Pass","Checkdisabled",objname+" is disabled");}
		else{Update_Report("Fail","Checkdisabled",objname+" is not disabled");}
	}

	//Name of the method:Readingtext
	//Brief description:Reading text box value
	//arguments:obj->WebElement,objname->name of the object
	//created by:Automation team
	//creation date:07/24/2017
	//modified date:07/24/2017
	public static void ReadingText(WebElement obj,String objname) throws IOException{
		if(obj.isDisplayed())
		{
			String Actualtext=obj.getText().trim();
			if(Actualtext.isEmpty())
			{
				Update_Report("Fail","ReadingText",objname+" has No data");
			}
			else{
				Update_Report("Pass","ReadingText",objname+" contains "+Actualtext);
			}
		}
	}	

	//Name of the method:readingCheckbox
	//Brief description:Reading check box value
	//arguments:obj->WebElement,objname->name of the object
	//created by:Automation team
	//creation date:07/24/2017
	//modified date:07/24/2017
	public static void readingCheckbox(WebElement obj,String Expectedtext,String objname) throws IOException{
		if(obj.isDisplayed())
		{
			String Actualtext=obj.getAttribute("checked").trim();
			if(Expectedtext.equals(Actualtext)){
				Update_Report("Pass","readingCheckbox",objname+" is checked");
			}
			else{
				Update_Report("Fail","readingCheckbox",objname+" is not checked");
			}
		}
		else{
			Update_Report("Fail","readingCheckbox",objname+" is not displayed,please check your application");
		}
	}
/*
	 * Name of the Method: readSheet
	 * Brief description : read excel sheet data
	 * Arguments: datatable --> report path,sheetname:sheet name to access in the excel file
	 * Created by: Automation team
	 * Creation date : July 17 2017
	 * last modified:  July 17 2017
*/

	public static String[][] readSheet(String dataTable, String sheetName) throws IOException{


		/*Step 1: Get the XL Path*/
		File xlFile = new File(dataTable);

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
				xlData[i][j] = sheet.getRow(i).getCell(j).getStringCellValue().trim();
			}

		}

		return xlData;
	}
	/*
	 * Name of the Method: startReport
	 * Brief description : Creates HTML report template
	 * Arguments: scriptname:test script name to run,ReportsPath:HTML report path to create,browserName:browser the script is running
	 * Created by: Automation team
	 * Creation date : July 17 2017
	 * last modified:  July 17 2017
*/
	public static void startReport(String scriptName, String ReportsPath,String browserName) throws IOException{

		String strResultPath = null;
		String testScriptName =scriptName;

		cur_dt = new Date(); 
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		String strTimeStamp = dateFormat.format(cur_dt);

		if (ReportsPath == "") { 

			ReportsPath = "C:\\";
		}

		if (ReportsPath.endsWith("\\")) { 
			ReportsPath = ReportsPath + "\\";
		}

		strResultPath = ReportsPath + "Log" + "/" +testScriptName +"/"; 
		File f = new File(strResultPath);
		f.mkdirs();
		htmlname = strResultPath  + testScriptName + "_" + strTimeStamp 
				+ ".html";

		bw = new BufferedWriter(new FileWriter(htmlname));

		bw.write("<HTML><BODY><TABLE BORDER=0 CELLPADDING=3 CELLSPACING=1 WIDTH=100%>");
		bw.write("<TABLE BORDER=0 BGCOLOR=BLACK CELLPADDING=3 CELLSPACING=1 WIDTH=100%>");
		bw.write("<TR><TD BGCOLOR=#66699 WIDTH=27%><FONT FACE=VERDANA COLOR=WHITE SIZE=2><B>Browser Name</B></FONT></TD><TD COLSPAN=6 BGCOLOR=#66699><FONT FACE=VERDANA COLOR=WHITE SIZE=2><B>"
				+ browserName + "</B></FONT></TD></TR>");
		bw.write("<HTML><BODY><TABLE BORDER=1 CELLPADDING=3 CELLSPACING=1 WIDTH=100%>");
		bw.write("<TR COLS=7><TD BGCOLOR=#BDBDBD WIDTH=3%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>SL No</B></FONT></TD>"
				+ "<TD BGCOLOR=#BDBDBD WIDTH=10%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>Step Name</B></FONT></TD>"
				+ "<TD BGCOLOR=#BDBDBD WIDTH=10%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>Execution Time</B></FONT></TD> "
				+ "<TD BGCOLOR=#BDBDBD WIDTH=10%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>Status</B></FONT></TD>"
				+ "<TD BGCOLOR=#BDBDBD WIDTH=47%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>Detail Report</B></FONT></TD></TR>");


	}
	/*
	 * Name of the Method: Update_Report
	 * Brief description : Updates HTML report with test results
	 * Arguments: Res_type:holds the response of test script,Action:Action performed,result:contains test results
	 * Created by: Automation team
	 * Creation date : July 17 2017
	 * last modified:  July 17 2017
*/

	public static void Update_Report(String Res_type,String Action, String result) throws IOException {
		String str_time;
		Date exec_time = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		str_time = dateFormat.format(exec_time);
		if (Res_type.startsWith("Pass")) {
			bw.write("<TR COLS=7><TD BGCOLOR=#EEEEEE WIDTH=3%><FONT FACE=VERDANA SIZE=2>"
					+ (j++)
					+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><FONT FACE=VERDANA SIZE=2>"
					+Action
					+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><FONT FACE=VERDANA SIZE=2>"
					+ str_time
					+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><FONT FACE=VERDANA SIZE=2 COLOR = GREEN>"
					+ "Passed"
					+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=30%><FONT FACE=VERDANA SIZE=2 COLOR = GREEN>"
					+ result + "</FONT></TD></TR>");

		} else if (Res_type.startsWith("Fail")) {
			exeStatus = "Failed";
			report = 1;
			bw.write("<TR COLS=7><TD BGCOLOR=#EEEEEE WIDTH=3%><FONT FACE=VERDANA SIZE=2>"
					+ (j++)
					+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><FONT FACE=VERDANA SIZE=2>"
					+Action
					+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><FONT FACE=VERDANA SIZE=2>"
					+ str_time
					+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><FONT FACE=VERDANA SIZE=2 COLOR = RED>"
					+ "<a href= "
					+ htmlname
					+ "  style=\"color: #FF0000\"> Failed </a>"

					+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=30%><FONT FACE=VERDANA SIZE=2 COLOR = RED>"
					+ result + "</FONT></TD></TR>");


		} 
	}
}
