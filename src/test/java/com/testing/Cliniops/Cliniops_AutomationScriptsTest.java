package com.testing.Cliniops;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
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
		 	public void auto_Clini_Login_001() throws Exception{
			 
			 		String expectedTooltipText;
			 		String actualTooltipText;
			 		Actions tooltip;
			 		dr.get("https://bridgetherapeutics.cliniops.com");
			 		tooltip = new Actions(dr);
			 		
			 		WebElement usrname=dr.findElement(By.id("username"));
			 		Thread.sleep(3000);			 		
			 		tooltip.moveToElement(usrname).build().perform();			 		
			 		expectedTooltipText="Enter Username";
			 		validateText_Attribute(usrname, expectedTooltipText, "username tooltip", "title","UsernameTooltip");
			 		
			 		WebElement password=dr.findElement(By.id("password"));
			 		Thread.sleep(3000);
			 		tooltip.moveToElement(password).build().perform();
			 		Thread.sleep(3000);			 		
			 		expectedTooltipText="Enter Password";
			 		validateText_Attribute(password, expectedTooltipText, "password tooltip", "title","PasswordTooltip");
			 
			 		WebElement authenticate=dr.findElement(By.id("Authenticate"));
			 		Thread.sleep(3000);
			 		tooltip.moveToElement(authenticate).build().perform();
			 		Thread.sleep(3000);			 		
			 		expectedTooltipText="Authenticate";
			 		validateText_Attribute(authenticate, expectedTooltipText, "Authenticate tooltip", "title","AuthenticateTooltip");

			 		
			 		//usrname.sendKeys("Abhishek");
					//password.sendKeys("Welcome123#");
					//authenticate.click();
					
					Thread.sleep(2000);		
					WebElement selectStudy=dr.findElement(By.id("investigator_study"));
					Thread.sleep(2000);		
					tooltip.moveToElement(selectStudy).build().perform();
					Thread.sleep(2000);
					//actualTooltipText=selectStudy.getAttribute("title");
					expectedTooltipText="Select Study";
					validateText_Attribute(selectStudy, expectedTooltipText, "select Study tooltip", "title","SelectStudyTooltip");
					//TooltipValidation(selectStudy, expectedTooltipText, actualTooltipText);
			 		
			 		WebElement selectLang=dr.findElement(By.id("lang_type"));
			 		Thread.sleep(3000);
			 		tooltip.moveToElement(authenticate).build().perform();
			 		Thread.sleep(3000); 				 		
			 		expectedTooltipText="Select Language";
			 		validateText_Attribute(selectLang, expectedTooltipText, "select Lang tooltip", "title","SelectLanguageTooltip");
			 
			 		WebElement loginBtn=dr.findElement(By.xpath(".//*[@id='login']/div[7]/input"));
			 		Thread.sleep(5000);
			 		tooltip.moveToElement(loginBtn).build().perform();
			 		Thread.sleep(3000);			 		
			 		expectedTooltipText="Login";
			 		validateText_Attribute(loginBtn, expectedTooltipText, "login tooltip", "title","LoginTooltip");
			 		

		 	}
		 @Test

			public void auto_Clini_Login_002() throws IOException, InterruptedException{
			 dr.get("https://bridgetherapeutics.cliniops.com/login");

		 		WebElement username=dr.findElement(By.id("username"));
		 		enterText(username, "Abhishek", "Username");
		 		
		 		WebElement password=dr.findElement(By.id("password"));
		 		enterText(password, "Welcome123#", "Password");
		 		
		 		WebElement authenticate=dr.findElement(By.id("Authenticate"));
		 		clickObj(authenticate, "Authenticate Button");
		 		
		 		//it will verify the dropdown's are enabled
		 		WebElement selectStudy= dr.findElement(By.id("investigator_study"));
		 		boolean selStudy=selectStudy.isEnabled();
		 		Assert.assertEquals(selStudy,true,"Study is enabled");
		 		
		 		WebElement selectLang= dr.findElement(By.id("lang_type"));
		 		boolean lang=selectLang.isEnabled();
		 		Assert.assertEquals(lang, true,"Lang is enabled");	 
		 		
		 		WebElement login=dr.findElement(By.xpath("//*[@title='Login']"));
		 		boolean loginBtn=login.isEnabled();
		 		Assert.assertEquals(loginBtn, true,"Login is enabled");
		 		
		 		Thread.sleep(2000);
		 		selectStudy.click();
		 		Actions ac= new Actions(dr);
		 		WebElement study= dr.findElement(By.xpath("//*[contains(text(),'Cisplatin/Etoposide/Rad')]"));
		 		ac.moveToElement(selectStudy).build().perform();
		 		String expectedTextForStudy="Cisplatin/Etoposide/Rad................-Small Cell Lung Cancer";
				validateText(study, expectedTextForStudy, "Study Details","Study name");
				dropDownByValue(selectStudy,"5");
				Thread.sleep(2000);
				
				selectLang.click();
				WebElement lang1= dr.findElement(By.xpath("//*[contains(text(),'English')]"));
				ac.moveToElement(lang1).build().perform();
				String expectedLanguage="English";
		 		validateText(lang1, expectedLanguage, "Language", "Language option");
		 		Thread.sleep(3000);
		 	
				
				dropDownByValue(selectLang, "1");
				Thread.sleep(2000);
				
				clickObj(login, "Login");
				
				String ActualURL= dr.getCurrentUrl();
				String ExpectedURL="https://bridgetherapeutics.cliniops.com/investigator";
				
				Assert.assertEquals(ActualURL, ExpectedURL);
				WebElement homePage=dr.findElement(By.className("current"));
				validateText(homePage, "Home", "HomePage","HomePage Display");
							

			}	
		 
		 @Test
			public void auto_Clini_Login_004() throws IOException, InterruptedException{
				dr.get("https://bridgetherapeutics.cliniops.com/login");
				WebElement forgotPwd=dr.findElement(By.linkText("Forgot password..? Click here..."));
				ButtonClick(forgotPwd, "forgot Password");
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



		 public void auto_Clini_Login_005() throws Exception{

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

	


