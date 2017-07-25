package com.testing.Cliniops;

import java.io.IOException;

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
	
		 @Test(enabled=false)
		 	public void auto_clini_login_001() throws Exception{
			 
			 		String expectedTooltipText;
			 		String actualTooltipText;
			 		Actions tooltip;
			 		dr.get("https://bridgetherapeutics.cliniops.com");
			 		
			 		WebElement usrname=dr.findElement(By.id("username"));
			 		Thread.sleep(3000);
			 		tooltip = new Actions(dr);
			 		tooltip.moveToElement(usrname).build().perform();
			 		
			 		expectedTooltipText="Enter Username";
			 		validateMsg_Attribute(usrname, expectedTooltipText, "username tooltip", "title");
			 		
			 		WebElement password=dr.findElement(By.id("password"));
			 		Thread.sleep(3000);
			 		tooltip = new Actions(dr);
			 		tooltip.moveToElement(password).build().perform();
			 		Thread.sleep(3000);
			 		
			 		expectedTooltipText="Enter Password";
			 		validateMsg_Attribute(password, expectedTooltipText, "password tooltip", "title");
			 
			 		WebElement authenticate=dr.findElement(By.id("Authenticate"));
			 		Thread.sleep(3000);
			 		tooltip = new Actions(dr);
			 		tooltip.moveToElement(authenticate).build().perform();
			 		Thread.sleep(3000);
			 		
			 		expectedTooltipText="Authenticate";
			 		validateMsg_Attribute(authenticate, expectedTooltipText, "Authenticate tooltip", "title");

			 		
			 		usrname.sendKeys("Abhishek");
			 		password.sendKeys("Welcome123#");
			 		ButtonClick(authenticate, "AuthenticateButton");
			 		
			 		WebElement selectLang=dr.findElement(By.id("lang_type"));
			 		Thread.sleep(3000);
			 		tooltip = new Actions(dr);
			 		tooltip.moveToElement(authenticate).build().perform();
			 		Thread.sleep(3000);
			 		
			 		expectedTooltipText="Select Language";
			 		validateMsg_Attribute(selectLang, expectedTooltipText, "select lang tooltip", "title");
			 
			 		WebElement loginBtn=dr.findElement(By.xpath(".//*[@id='login']/div[7]/input"));
			 		Thread.sleep(5000);
			 		tooltip = new Actions(dr);
			 		tooltip.moveToElement(loginBtn).build().perform();
			 		Thread.sleep(3000);
			 		
			 		expectedTooltipText="Login";
			 		validateMsg_Attribute(loginBtn, expectedTooltipText, "login tooltip", "title");
			 		

		 	}
		 @Test
		 public void auto_clini_login_002() throws IOException{
			 
			 String expected = "Abhishek";
			 dr.get("https://bridgetherapeutics.cliniops.com");
			 WebElement userNameObj = dr.findElement(By.xpath(".//*[@id='username']"));
			 enterText(userNameObj, "Abhishek", "userName object");
			 String actual = userNameObj.getAttribute("value");
			 validateMsg_Attribute(userNameObj, expected, "usernameObject", "value");
			
		 }
		 
		@AfterMethod
		
			public void closeBrowser(){
			dr.close();
		}


	}

	


