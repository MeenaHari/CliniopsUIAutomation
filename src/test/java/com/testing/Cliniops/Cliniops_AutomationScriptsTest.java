package com.testing.Cliniops;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Cliniops_AutomationScriptsTest extends Cliniops_ReusableMethodsTest{

	private WebDriver dr;  

	//@BeforeMethod    
	//@Parameters({"browser"})
	public void selectBrowser(String browser) throws IOException{
		if(browser.equalsIgnoreCase("firefox")){
			dr=new FirefoxDriver();	
		}
		else if(browser.equalsIgnoreCase("chrome")){
			dr=new ChromeDriver();
		}
		else if(browser.equalsIgnoreCase("IE")){
			dr=new InternetExplorerDriver();
		}
		dr.manage().window().maximize();
	}

	//@Test
	public void auto_Clini_Login_001() throws Exception{
		String expectedTooltipText;
		dr.get("https://bridgetherapeutics.cliniops.com");
		Actions tooltip = new Actions(dr);

		WebElement usrname=dr.findElement(By.id("username"));
		tooltip.moveToElement(usrname).build().perform();			 		
		expectedTooltipText="Enter Username";
		validateTextAttribute(usrname, expectedTooltipText, "Username tooltip", "title","UsernameTooltip");

		WebElement password=dr.findElement(By.id("password"));
		tooltip.moveToElement(password).build().perform();
		expectedTooltipText="Enter Password";
		validateTextAttribute(password, expectedTooltipText, "Password tooltip", "title","PasswordTooltip");

		WebElement authenticate=dr.findElement(By.id("Authenticate"));
		tooltip.moveToElement(authenticate).build().perform();
		expectedTooltipText="Authenticate";
		validateTextAttribute(authenticate, expectedTooltipText, "Authenticate tooltip", "title","AuthenticateTooltip");

		WebElement selectStudy=dr.findElement(By.id("investigator_study"));
		tooltip.moveToElement(selectStudy).build().perform();
		//actualTooltipText=selectStudy.getAttribute("title");
		expectedTooltipText="Select Study";
		validateTextAttribute(selectStudy, expectedTooltipText, "Select Study tooltip", "title","SelectStudyTooltip");
		//TooltipValidation(selectStudy, expectedTooltipText, actualTooltipText);

		WebElement selectLang=dr.findElement(By.id("lang_type"));
		tooltip.moveToElement(authenticate).build().perform();
		expectedTooltipText="Select Language";
		validateTextAttribute(selectLang, expectedTooltipText, "Select Lang tooltip", "title","SelectLanguageTooltip");

		WebElement loginBtn=dr.findElement(By.xpath(".//*[@id='login']/div[7]/input"));
		tooltip.moveToElement(loginBtn).build().perform();
		expectedTooltipText="Login";
		validateTextAttribute(loginBtn, expectedTooltipText, "login tooltip", "title","LoginTooltip");
	}

	 //@Test

		public void auto_Clini_Login_002() throws Exception{
		 dr.get("https://bridgetherapeutics.cliniops.com/login");

	 		WebElement username=dr.findElement(By.id("username"));
	 		enterText(username, "Abhishek", "Username", "Enter username");
	 		
	 		WebElement password=dr.findElement(By.id("password"));
	 		enterText(password, "Welcome123#", "Password", "Enter password");
	 		
	 		WebElement authenticate=dr.findElement(By.id("Authenticate"));
	 		clickElement(authenticate, "Authenticate Button", "Click on authenticate");
	 		
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
				String Actualtext=study.getText().trim();
				if(expectedTextForStudy.equals(Actualtext))
				{
			dropDownByValue(selectStudy,"5");
			Thread.sleep(2000);
				}
			selectLang.click();
			WebElement lang1= dr.findElement(By.xpath("//*[contains(text(),'English')]"));
			ac.moveToElement(lang1).build().perform();
			String expectedLanguage="English";
	 		validateText(lang1, expectedLanguage, "Language", "Language option");
	 		Thread.sleep(3000);
	 	
	 		dropDownByValue(selectStudy,"5");
			dropDownByValue(selectLang, "1");
			Thread.sleep(2000);
			
			clickElement(login, "Login", "Click on Login");
			
			String ActualURL= dr.getCurrentUrl();
			String ExpectedURL="https://bridgetherapeutics.cliniops.com/investigator";
			
			Assert.assertEquals(ActualURL, ExpectedURL);
			WebElement homePage=dr.findElement(By.className("current"));
			validateText(homePage, "Home", "HomePage","HomePage Display");
						
				}
	
	//@Test
	public void auto_Clini_Login_004() throws IOException, InterruptedException{
		
		String errorMsg;
		String expectedErrorMsg;
		dr.get("https://bridgetherapeutics.cliniops.com/login");
		
		WebElement forgotPwd=dr.findElement(By.linkText("Forgot password..? Click here..."));
		clickElement(forgotPwd, "Click Forgot Password", "Forgot Password");
		//Thread.sleep(3000);
		
		WebElement email=dr.findElement(By.id("forgotemail"));
		enterText(email, "abc@gmail.com", "Email id", "Enter Email");
		//Thread.sleep(3000);
		
		WebElement requestNewPwd=dr.findElement(By.id("req_new_pass"));
		clickElement(requestNewPwd, "Click Request Password", "Request new password");
		//Thread.sleep(3000);
		
		//WebElement emailIdError=dr.findElement(By.xpath("//*[text()='Email-id does not exist in database.']"));
		WebElement emailIdError = (new WebDriverWait(dr, 5))
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='Email-id does not exist in database.']")));
		errorMsg=emailIdError.getText();
		expectedErrorMsg="Email-id does not exist in database.";
		validateText(emailIdError, expectedErrorMsg, errorMsg,"Email error");
		//Thread.sleep(3000);
		
		WebElement email2=dr.findElement(By.id("forgotemail"));
		enterText(email2, "abhishekmj11@gmail.com", "Email id", "Enter Email");
		//Thread.sleep(3000);
		
		WebElement requestNewPwd2=dr.findElement(By.id("req_new_pass"));
		clickElement(requestNewPwd2, "Click Request Password", "Request new password");
		//Thread.sleep(3000);
		
		WebElement emailIdError2=dr.findElement(By.xpath(".//*[@id='content-body']/div[1]/span"));
		errorMsg=emailIdError2.getText();
		expectedErrorMsg="Email has been sent to your email address. Please check to create your new password.";
		validateText(emailIdError2, expectedErrorMsg, errorMsg,"Email error");
	}

	//@Test
	public void auto_Clini_Login_005() throws Exception{

		dr.get("https://bridgetherapeutics.cliniops.com");

		WebElement rightFooter=dr.findElement(By.id("footer-right"));

		validateText(rightFooter, "Version : 2.0.27", "Right Footer:Version 2.0.27 ","Right Footer");

		WebElement logo=dr.findElement(By.xpath(".//*[@id='logo']/h1/a/img"));

		if(logo.isDisplayed()){
			updateReport("Pass", "Presence of Logo", "Logo appears");
		}
		else{
			updateReport("Fail", "Presence of Logo", "Logo not displayed");
		}
	}


	//@AfterMethod
	public void closeBrowser(){
		dr.close();
		dr.quit();

	}

}




