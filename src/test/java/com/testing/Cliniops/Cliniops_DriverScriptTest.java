package com.testing.Cliniops;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.tools.ant.taskdefs.UpToDate;
import org.testng.annotations.Test;

public class Cliniops_DriverScriptTest {  
  
	
	@Test
	public static void cliniops_driver() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException, ClassNotFoundException, InstantiationException{

		Cliniops_AutomationScriptsTest CAS = new Cliniops_AutomationScriptsTest();


		//String testDataPath = "C:/Users/Sreeram/git/Testing_Cliniops/cliniops_AutomationScripts_Execution.xls";
		String testDataPath = "C:/Users/Zunaira's/git_Cliniops/CliniopsUIAutomation/cliniops_AutomationScripts_Execution.xls";
		
		//String ReportsPath="C:/Users/Sreeram/git/Testing_Cliniops/test-output/Suite/CliniOps_Report";
		String ReportsPath="C:/Users/Zunaira's/git_Cliniops/CliniopsUIAutomation/test-output/Suite/CliniOps_Report";
		

		String[][] recData = Cliniops_ReusableMethodsTest.readSheet(testDataPath, "Sheet1");

		String tc = null;
		//looping through the rows
		for(int i=0; i<recData.length; i++){

			if (recData[i][1].equalsIgnoreCase("y")){

				tc = recData[i][2];
				System.out.println("Testcase to run: "+recData[i][2]);

				//we are getting handle to the method for invoking...
				Method testcase = Cliniops_AutomationScriptsTest.class.getMethod(tc);

				//invoke---executes the method
				if(recData[i][3].equalsIgnoreCase("Y"))
				{
					Cliniops_ReusableMethodsTest.startReport(tc, ReportsPath,"FIREFOX");
					CAS.selectBrowser("firefox");
					testcase.invoke(CAS);
					CAS.closeBrowser();
					//closing bufferwriter
					Cliniops_ReusableMethodsTest.bw.close();
				}

				if(recData[i][4].equalsIgnoreCase("Y"))
				{
					Cliniops_ReusableMethodsTest.startReport(tc, ReportsPath,"CHROME");
					CAS.selectBrowser("chrome");
					testcase.invoke(CAS);
					CAS.closeBrowser();
					Cliniops_ReusableMethodsTest.bw.close();

				}
				if(recData[i][5].equalsIgnoreCase("Y"))
				{
					Cliniops_ReusableMethodsTest.startReport(tc, ReportsPath,"IE");
					CAS.selectBrowser("IE");
					testcase.invoke(CAS);
					CAS.closeBrowser();
					Cliniops_ReusableMethodsTest.bw.close();

				}						
				Cliniops_ReusableMethodsTest.bw.close();				
			}
			else if(recData[i][1].equalsIgnoreCase("n")){
				System.out.println(recData[i][2] + "in line number "+i+ "skipped from execution");
			}
		}	

	}
}