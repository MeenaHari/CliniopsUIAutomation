package com.testing.Cliniops;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Cliniops_AutomationScriptsTest extends Cliniops_ReusableMethodsTest{

WebDriver dr;  
    
	
    @BeforeMethod    
	@Parameters({"browser"})
	public void selectBrowser(String browser){
		if(browser.equalsIgnoreCase("firefox")){
			dr=new FirefoxDriver();	
			dr.manage().window().maximize();

		}
		else if(browser.equalsIgnoreCase("chrome")){
		
			dr=new ChromeDriver();
			dr.manage().window().maximize();

		}
		else if(browser.equalsIgnoreCase("IE")){

			dr=new InternetExplorerDriver();
	        dr.manage().window().maximize();
	        
		}
		
	}
	
		 @Test
		 	public void auto_clini_login_001() throws Exception{
			 		Actions tooltip;
			 		dr.get("https://bridgetherapeutics.cliniops.com");
			 		tooltip = new Actions(dr);
			 		
			 		WebElement usrname=dr.findElement(By.id("username"));
			 		Thread.sleep(3000);			 		
			 		tooltip.moveToElement(usrname).build().perform();			 		
			 		validateMsg_Attribute(usrname, "Enter Username", "username tooltip", "title");
			 		
			 		WebElement password=dr.findElement(By.id("password"));
			 		Thread.sleep(3000);
			 		tooltip.moveToElement(password).build().perform();
			 		Thread.sleep(3000);			 		
			 		validateMsg_Attribute(password, "Enter Password", "password tooltip", "title");
			 
			 		WebElement authenticate=dr.findElement(By.id("Authenticate"));
			 		Thread.sleep(3000);
			 		tooltip.moveToElement(authenticate).build().perform();
			 		Thread.sleep(3000);			 		
			 		
			 		validateMsg_Attribute(authenticate, "Authenticate", "Authenticate tooltip", "title");

			 		
			 		Thread.sleep(2000);		
					WebElement selectStudy=dr.findElement(By.id("investigator_study"));
					Thread.sleep(2000);		
					tooltip.moveToElement(selectStudy).build().perform();
					Thread.sleep(2000);
					validateMsg_Attribute(selectStudy, "Select Study", "select Study tooltip", "title");
					//TooltipValidation(selectStudy, expectedTooltipText, actualTooltipText);
			 		
			 		WebElement selectLang=dr.findElement(By.id("lang_type"));
			 		Thread.sleep(3000);
			 		tooltip = new Actions(dr);
			 		tooltip.moveToElement(authenticate).build().perform();
			 		Thread.sleep(3000); 				 		
			 		validateMsg_Attribute(selectLang, "Select Language", "select Lang tooltip", "title");
			 
			 		WebElement loginBtn=dr.findElement(By.xpath(".//*[@id='login']/div[7]/input"));
			 		Thread.sleep(5000);
			 		tooltip = new Actions(dr);
			 		tooltip.moveToElement(loginBtn).build().perform();
			 		Thread.sleep(3000);			 		
			 		validateMsg_Attribute(loginBtn, "Login", "login tooltip ", "title");
			 		

		 	}
		 @Test(enabled=false)
		 public void auto_clini_login_002() throws IOException{
			 
		 }
		 @Test

		 public void auto_clini_login_005() throws Exception{
			 dr.get("https://bridgetherapeutics.cliniops.com");
			 WebElement rightFooter=dr.findElement(By.id("footer-right"));
			 validate(rightFooter, "Version : 2.0.27", "Right Footer:Version 2.0.27 ","Right Footer");
			 WebElement logo=dr.findElement(By.xpath(".//*[@id='logo']/h1/a/img"));
			 if(logo.isDisplayed()){
				 Update_Report("Pass", "Presence of Logo", "Logo appears");
			 }
			 else{
				 Update_Report("Fail", "Presence of Logo", "Logo not displayed");
			 }
		 }


		@AfterMethod
		
			public void closeBrowser(){
			dr.close();
		}


	}

	


