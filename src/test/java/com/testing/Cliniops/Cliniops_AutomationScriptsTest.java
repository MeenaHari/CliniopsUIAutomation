package com.testing.Cliniops;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
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

private static WebDriver dr;  
public static WebDriver getInstance(){
	return dr;}
    
	
    @BeforeMethod    
	@Parameters({"browser"})
	public void selectBrowser(String browser){
		if(browser.equalsIgnoreCase("firefox")){
			dr=Cliniops_AutomationScriptsTest.getInstance();
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
			 
			 		String expectedTooltipText;
			 		String actualTooltipText;
			 		Actions tooltip;
			 		dr.get("https://bridgetherapeutics.cliniops.com");
			 		tooltip = new Actions(dr);
			 		
			 		WebElement usrname=dr.findElement(By.id("username"));
			 		Thread.sleep(3000);			 		
			 		tooltip.moveToElement(usrname).build().perform();			 		
			 		expectedTooltipText="Enter Username";
			 		validateText_Attribute(usrname, expectedTooltipText, "username tooltip", "title","Tooltip");
			 		
			 		WebElement password=dr.findElement(By.id("password"));
			 		Thread.sleep(3000);
			 		tooltip.moveToElement(password).build().perform();
			 		Thread.sleep(3000);			 		
			 		expectedTooltipText="Enter Password";
			 		validateText_Attribute(password, expectedTooltipText, "password tooltip", "title","Tooltip");
			 
			 		WebElement authenticate=dr.findElement(By.id("Authenticate"));
			 		Thread.sleep(3000);
			 		tooltip.moveToElement(authenticate).build().perform();
			 		Thread.sleep(3000);			 		
			 		expectedTooltipText="Authenticate";
			 		validateText_Attribute(authenticate, expectedTooltipText, "Authenticate tooltip", "title","Tooltip");

			 		
			 		//usrname.sendKeys("Abhishek");
					//password.sendKeys("Welcome123#");
					authenticate.click();
					
					Thread.sleep(2000);		
					WebElement selectStudy=dr.findElement(By.id("investigator_study"));
					Thread.sleep(2000);		
					tooltip.moveToElement(selectStudy).build().perform();
					Thread.sleep(2000);
					//actualTooltipText=selectStudy.getAttribute("title");
					expectedTooltipText="Select Study";
					validateText_Attribute(selectStudy, expectedTooltipText, "select Study tooltip", "title","Tooltip");
					//TooltipValidation(selectStudy, expectedTooltipText, actualTooltipText);
			 		
			 		WebElement selectLang=dr.findElement(By.id("lang_type"));
			 		Thread.sleep(3000);
			 		tooltip = new Actions(dr);
			 		tooltip.moveToElement(authenticate).build().perform();
			 		Thread.sleep(3000); 				 		
			 		expectedTooltipText="Select Language";
			 		validateText_Attribute(selectLang, expectedTooltipText, "select Lang tooltip", "title","Tooltip");
			 
			 		WebElement loginBtn=dr.findElement(By.xpath(".//*[@id='login']/div[7]/input"));
			 		Thread.sleep(5000);
			 		tooltip = new Actions(dr);
			 		tooltip.moveToElement(loginBtn).build().perform();
			 		Thread.sleep(3000);			 		
			 		expectedTooltipText="Login";
			 		validateText_Attribute(loginBtn, expectedTooltipText, "login tooltip", "title","Tooltip");
			 		

		 	}
					
		 
		 @Test
			public void auto_clini_login_004() throws IOException, InterruptedException{
				dr.get("https://bridgetherapeutics.cliniops.com/login");
				WebElement forgotPwd=dr.findElement(By.linkText("Forgot password..? Click here..."));
				 forgotPwd.click();
				 Thread.sleep(3000);
				 WebElement email=dr.findElement(By.id("forgotemail"));
				 enterText(email, "abc@gmail.com", "Email id");
				 Thread.sleep(3000);
				 WebElement requestNewPwd=dr.findElement(By.id("req_new_pass"));
				 ButtonClick(requestNewPwd, "Request new password");
				 Thread.sleep(3000);
				 WebElement emailIdError=dr.findElement(By.xpath("//*[text()='Email-id does not exist in database.']"));
				 String errorMsg=emailIdError.getText();
				 String actualErrorMsg="Email-id does not exist in database.";
				 validateText(emailIdError, actualErrorMsg, errorMsg,"Email error");
				 Thread.sleep(3000);
				 WebElement email2=dr.findElement(By.id("forgotemail"));
				 enterText(email2, "abhishekmj11@gmail.com", "Email id");
				 Thread.sleep(3000);
				 WebElement requestNewPwd2=dr.findElement(By.id("req_new_pass"));
				 ButtonClick(requestNewPwd2, "Request new password");
				 Thread.sleep(3000);
				 WebElement emailIdError2=dr.findElement(By.xpath(".//*[@id='content-body']/div[1]/span"));
				 String errorMsg2=emailIdError2.getText();
				 String actualErrorMsg2="Email has been sent to your email address. Please check to create your new password.";
				 validateText(emailIdError2, actualErrorMsg2, errorMsg2,"Email error");
			}
		 @Test



		 public void auto_clini_login_005() throws Exception{

			 dr.get("https://bridgetherapeutics.cliniops.com");

			 WebElement rightFooter=dr.findElement(By.id("footer-right"));

			 validateText(rightFooter, "Version : 2.0.27", "Right Footer:Version 2.0.27 ","Right Footer");

			 WebElement logo=dr.findElement(By.xpath(".//*[@id='logo']/h1/a/img"));

			 if(logo.isDisplayed()){

				 Update_Report("Pass", "Presence of Logo", "Logo appears");

			 }

			 else{

				 Update_Report("Pass", "Presence of Logo", "Logo not displayed");

			 }

		 }


		
				
			
		 	
		 
		@AfterMethod
		
			public void closeBrowser(){
			dr.close();
		}


	}

	


