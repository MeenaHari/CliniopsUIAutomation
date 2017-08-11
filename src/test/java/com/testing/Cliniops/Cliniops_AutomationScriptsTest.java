package com.testing.Cliniops;

import java.io.IOException;
import java.util.ArrayList;
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
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Cliniops_AutomationScriptsTest extends Cliniops_ReusableMethodsTest{
    WebDriver dr;  
    
	
    @BeforeMethod    
	@Parameters({"browser"})
	public void selectBrowser(String browser)throws IOException{
	    if(browser.equalsIgnoreCase("firefox")){
			dr=new FirefoxDriver();	
			dr.manage().window().maximize();
			
		}
		else if(browser.equalsIgnoreCase("chrome")){
			dr=new ChromeDriver();
		}
		else if(browser.equalsIgnoreCase("IE")){
			dr=new InternetExplorerDriver();
		}
		dr.manage().window().maximize();
	}
	
    @Test
    public void auto_Clini_Login_001() throws Exception{
    	dr.get("https://bridgetherapeutics.cliniops.com");
    	Actions tooltip = new Actions(dr);

    	WebElement usrname=dr.findElement(By.id("username"));
    	Thread.sleep(3000);			 		
    	tooltip.moveToElement(usrname).build().perform();	

    	validateTextAttribute(usrname, "Enter Username", "Username tooltip", "title","Username Tooltip",dr);
    	Thread.sleep(3000);			 		

    	WebElement password=dr.findElement(By.id("password"));
    	Thread.sleep(3000);
    	tooltip.moveToElement(password).build().perform();
    	Thread.sleep(3000);			 		
    	validateTextAttribute(password, "Enter Password", "password tooltip", "title","Password Tooltip",dr);

    	WebElement authenticate=dr.findElement(By.id("Authenticate"));
    	Thread.sleep(3000);
    	tooltip.moveToElement(authenticate).build().perform();
    	Thread.sleep(3000);			 		
    	validateTextAttribute(authenticate, "Authenticate", "Authenticate tooltip", "title","Authenticate Tooltip",dr);

    	Thread.sleep(2000);		
    	WebElement selectStudy=dr.findElement(By.id("investigator_study"));
    	Thread.sleep(2000);		
    	tooltip.moveToElement(selectStudy).build().perform();
    	Thread.sleep(2000);
    	validateTextAttribute(selectStudy, "Select Study", "select Study tooltip", "title","SelectStudy Tooltip",dr);

    	WebElement selectLang=dr.findElement(By.id("lang_type"));
    	Thread.sleep(3000);
    	tooltip = new Actions(dr);
    	tooltip.moveToElement(authenticate).build().perform();
    	Thread.sleep(3000); 				 		
    	validateTextAttribute(selectLang, "Select Language", "select Lang tooltip", "title","SelectLanguage Tooltip",dr);

    	WebElement loginBtn=dr.findElement(By.xpath(".//*[@id='login']/div[7]/input"));
    	Thread.sleep(5000);
    	tooltip = new Actions(dr);
    	tooltip.moveToElement(loginBtn).build().perform();
    	Thread.sleep(3000);			 		
    	validateTextAttribute(loginBtn, "Login", "login tooltip ", "title","Login Tooltip",dr);


    }

    
    @Test
    public void auto_Clini_Login_002() throws IOException, InterruptedException{
    	dr.get("https://bridgetherapeutics.cliniops.com/login");
    	WebElement username=dr.findElement(By.id("username"));
    	enterText(username, "Abhishek", "Username", "Enter username", dr);
    	WebElement password=dr.findElement(By.id("password"));
    	enterText(password, "Welcome123#", "Password", "Enter password", dr);
    	WebElement authenticate=dr.findElement(By.id("Authenticate"));
    	clickElement(authenticate, "Authenticate Button", "Click on authenticate", dr);
    	WebElement selectStudy= dr.findElement(By.id("investigator_study"));
    	checkEnabled(selectStudy,"selectStudy",dr);
    	WebElement selectLang= dr.findElement(By.id("lang_type"));
    	checkEnabled(selectLang,"selectLang",dr);
    	WebElement login=dr.findElement(By.xpath("//*[@title='Login']"));
    	checkEnabled(login,"login",dr);  
    	Thread.sleep(2000);
    	clickElement(selectStudy, "selectStudy","selectStudy",dr);
    	WebElement selectStudyOption=dr.findElement(By.xpath("//*[text()='Cisplatin/Etoposide/Rad................-Small Cell Lung Cancer']"));
    	checkObjectDisplay(selectStudyOption,"selectStudyOption","selectStudyOption",dr);
    	//String expectedTextForStudy="Cisplatin/Etoposide/Rad................-Small Cell Lung Cancer123";
    	//validateText(selectStudyOption, expectedTextForStudy, "Study option", "Study option", dr);
    	clickElement(selectStudyOption, "selectStudyOption","selectStudyOption",dr);
    	Thread.sleep(3000);

    	clickElement(selectLang, "selectLang","selectLang",dr);
    	WebElement selectLanguageOption=dr.findElement(By.xpath(".//*[@id='lang_type']/option[2]"));
    	checkObjectDisplay(selectLanguageOption,"selectLanguageOption","selectLanguageOption",dr);
    	//String expectedLanguage="English";
    	//validateText(selectLanguageOption, expectedLanguage, "Language option", "Language option", dr);
    	clickElement(selectLanguageOption, "selectLanguageOption","selectLanguageOption",dr);

    	Thread.sleep(2000);
    	clickElement(login, "login","login",dr);
    	String ActualURL= dr.getCurrentUrl();
    	String ExpectedURL="https://bridgetherapeutics.cliniops.com/investigator";
    	validateURL(ExpectedURL,ActualURL,"homePageURL",dr);
    	if(ExpectedURL.equals(ActualURL)){
    		updateReport("Pass", "HomeURL", "Actual text is matching with expected text",dr);
    	}
    	else{
    		updateReport("Fail", "HomeURL", "Actual text is not matching with expected text",dr);
    	}
    	WebElement homePage=dr.findElement(By.className("current"));
    	String expectedHome="Home";
    	validateText(homePage,expectedHome, "HomePage", "HomePage Highlighted", dr);
    	  
    }

    @Test
    public void auto_Clini_Login_003() throws IOException, InterruptedException{
    	//validating Authentication error message
    	String expected = "Authenitcation failed !";
    	dr.get("https://bridgetherapeutics.cliniops.com");
    	WebElement userNameObj = dr.findElement(By.xpath(".//*[@id='username']"));
    	enterText(userNameObj, "Abhi", "userName object","Enter Username",dr);

    	WebElement passwordObj = dr.findElement(By.id("password"));
    	enterText(passwordObj, "xxx", "password object","Enter Password",dr);

    	WebElement authenticateObj = dr.findElement(By.xpath(".//*[@id='Authenticate']"));
    	clickElement(authenticateObj, "Authentication button object","Click Authenticate",dr);

    	WebElement errMessageObj = dr.findElement(By.xpath(".//*[@id='showCustomErrMsg']"));

    	WebDriverWait wait = new WebDriverWait(dr, 30);
    	WebElement ele = wait.until(ExpectedConditions.visibilityOf(errMessageObj));
    	String actual = ele.getText();
    	System.out.println(actual);

    	if (actual.equals(expected))
    	{
    		System.out.println("authentication error message is passed");
    	}
    	else 
    	{
    		System.out.println("Nothing to check... ");
    	}

    	//Testing select study/ select language and login buttons are disabled
    	WebElement selStudyObj = dr.findElement(By.xpath(".//*[@id='investigator_study']"));
    	if (selStudyObj.isEnabled()){
    		System.out.println("selectstudy is enabled...");
    	}
    	else 
    	{
    		System.out.println("select study is disabled...");
    	}

    	WebElement selLangObj = dr.findElement(By.xpath(".//*[@id='lang_type']"));
    	if (selStudyObj.isEnabled()){
    		System.out.println("select language type is enabled...");
    	}
    	else 
    	{
    		System.out.println("select language type is disabled...");
    	}

    	WebElement loginObj = dr.findElement(By.xpath(".//*[@id='login']/div[7]/input"));
    	if (loginObj.isEnabled()){
    		System.out.println("login button is enabled ...");
    	}
    	else 
    	{
    		System.out.println("login button is disabled...");
    	}

    	//Testing for the error messages if we enter incorrect password and username
    	WebElement userNameObj1 = dr.findElement(By.xpath(".//*[@id='username']"));
    	userNameObj1.clear();
    	enterText(userNameObj1, "", "username object","Enter Username",dr);
    	WebElement passwordObj1 = dr.findElement(By.id("password"));
    	passwordObj1.clear();

    	WebElement authenticateObj1 = dr.findElement(By.id("Authenticate"));
    	clickElement(authenticateObj1, "Authentication button object","Click Authenticate",dr);

    	String expUserNameErrMsg = "Please enter the username";
    	String expPassWordErrMsg = "Please enter the password";

    	WebElement unErrObj = dr.findElement(By.xpath(".//*[@id='login']/div[1]/label"));
    	String unActual = unErrObj.getText();
    	if(expUserNameErrMsg.equals(unActual))
    	{
    		System.out.println("UserName error message is present...");
    	}
    	else 
    	{
    		System.out.println("UserName error message is not present...");
    	}

    	WebElement pwErrObj = dr.findElement(By.xpath(".//*[@id='login']/div[2]/label"));
    	String pwActual = pwErrObj.getText();

    	if(expPassWordErrMsg.equals(pwActual))
    	{
    		System.out.println("Password error message is present...");
    	}
    	else 
    	{
    		System.out.println("Password error message is not present...");
    	}

    }

    @Test
    public void auto_Clini_Login_004() throws IOException{
    	String errorMsg;
    	String expectedErrorMsg;
    	dr.get("https://bridgetherapeutics.cliniops.com/login");

    	WebElement forgotPwd=dr.findElement(By.linkText("Forgot password..? Click here..."));
    	clickElement(forgotPwd, "Click Forgot Password", "Forgot Password",dr);

    	WebElement email=dr.findElement(By.id("forgotemail"));
    	enterText(email, "abc@gmail.com", "Email id", "Enter Email",dr);

    	WebElement requestNewPwd=dr.findElement(By.id("req_new_pass"));
    	clickElement(requestNewPwd, "Click Request Password", "Request new password",dr);

    	WebElement emailIdError = (new WebDriverWait(dr, 5))
    			.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='content-body']/div[1]/span")));
    	//errorMsg=emailIdError.getText();
    	expectedErrorMsg="Email-id or username does not exist in database.";
    	validateText(emailIdError, expectedErrorMsg, "Email error","Email error",dr);

    	WebElement email2=dr.findElement(By.id("forgotemail"));
    	enterText(email2, "abhishekmj11@gmail.com", "Email id", "Enter Email",dr);

    	WebElement requestNewPwd2=dr.findElement(By.id("req_new_pass"));
    	clickElement(requestNewPwd2, "Click Request Password", "Request new password",dr);

    	WebElement emailIdError2=dr.findElement(By.xpath(".//*[@id='content-body']/div[1]/span"));
    	errorMsg=emailIdError2.getText();
    	expectedErrorMsg="Email has been sent to your email address. Please check to create your new password.";
    	validateText(emailIdError2, expectedErrorMsg, errorMsg,"Email error",dr);
    }
    @Test
    public void auto_Clini_Login_005() throws Exception{
    	dr.get("https://bridgetherapeutics.cliniops.com");
    	WebElement rightFooter=dr.findElement(By.id("footer-right"));
    	validateText(rightFooter, "Version : 2.0.27", "Right Footer:Version 2.0.27 ","Right Footer",dr);
    	WebElement logo=dr.findElement(By.xpath(".//*[@id='logo']/h1/a/img"));
    	if(logo.isDisplayed()){
    		updateReport("Pass", "Presence of Logo", "Logo appears",dr);
    	}
    	else{
    		updateReport("Fail", "Presence of Logo", "Logo not displayed",dr);
    	}
    }


    @Test
    public void auto_Clini_Home_001() throws InterruptedException, IOException{
    	login(dr);
    	Thread.sleep(2000);
    	WebElement homeStudyLogo=dr.findElement(By.xpath(".//*[@id='logo']/h1/a/img"));
    	checkObjectDisplay(homeStudyLogo,"home Study  Logo","HomePage Study Logo",dr);
    	Thread.sleep(2000);
    	WebElement WelcomeUsername=dr.findElement(By.xpath(".//*[@id='header-right']/span[1]"));
    	checkObjectDisplay(WelcomeUsername,"WelcomeUsername","Welcome User_Full_Name",dr);
    	Thread.sleep(2000);
    	WebElement homePageStudyPortal=dr.findElement(By.xpath(".//*[@id='header-right']/span[2]/b"));
    	checkObjectDisplay(homePageStudyPortal,"homePageStudyPortal","homePageStudyPortal",dr);
    	Thread.sleep(2000);
    	WebElement logout=dr.findElement(By.xpath("//input[@value='Logout']"));		
    	checkEnabled(logout,"logout",dr);
    	Thread.sleep(2000);
    	if(logout.isEnabled()){
    		checkObjectDisplay(logout,"logout","logout",dr);
    	}
    	Thread.sleep(2000);
    	WebElement homePageStudyName=dr.findElement(By.xpath(".//*[@id='header-right']/div/span"));
    	checkObjectDisplay(homePageStudyName,"homePageStudyName","homePageStudyName",dr);
    	Thread.sleep(2000);
    	WebElement enrollmentCount=dr.findElement(By.xpath(".//*[@id='content-body']/div/div/div[2]/div[1]/span[2]"));
    	String expectedEnrollmentCount="300";
    	validateText(enrollmentCount, expectedEnrollmentCount, "Enrollment count","Enrollment",dr);
    	Thread.sleep(2000);
    	WebElement groupsCount=dr.findElement(By.xpath(".//*[@id='content-body']/div/div/div[2]/div[2]/span[2]"));
    	String expectedGroupsCount="2";
    	validateText(groupsCount, expectedGroupsCount, "Groups count","Groups",dr);
    	Thread.sleep(2000);
    	WebElement sitesCount=dr.findElement(By.xpath(".//*[@id='content-body']/div/div/div[2]/div[3]/span[2]"));
    	String expectedSitesCount="2";
    	validateText(sitesCount, expectedSitesCount, "Sites count","Sites",dr);
    	Thread.sleep(2000);
    	WebElement visitsCount=dr.findElement(By.xpath(".//*[@id='content-body']/div/div/div[2]/div[4]/span[2]"));
    	String expectedVisitsCount="5";
    	validateText(visitsCount, expectedVisitsCount, "Visits count","Visits",dr);
    }
	@Test
	public void auto_Clini_Home_002() throws InterruptedException, IOException {
		
		login(dr);
		
			Thread.sleep(2000);

			//Create action object
			Actions action = new Actions(dr);

			//Locate the 4 graphs
			List<WebElement> graphs = dr.findElements(By.cssSelector("div.graph-container>div[class*='graph']"));
			ArrayList<String> contId = new ArrayList<String>();

			//Locate the 4 containers to get their ids
			List<WebElement> containers = dr.findElements(By.cssSelector("div[id*='container']"));
			for(int i=0; i< containers.size(); i++){
				WebElement container = containers.get(i);
				String id = container.getAttribute("id");
				contId.add(id);
			}

			//Graph titles
			String[] expectedGraphText = {
				"Subjects Enrollment",
				"Visits",
				"Site Enrollment",
				"Group Enrollment"
			};
			//Expected result for chart menu options
			String[] expMenuOption = {
				"Print chart",
				"Download PNG image",
				"Download JPEG image",
				"Download PDF document",
				"Download SVG vector image"
			};

			String locator = "div[id=";
			//Navigate through the list elements 
			for(int i=0; i< graphs.size(); i++){
				WebElement chartObj = graphs.get(i);
				action.moveToElement(chartObj).build().perform();

				//Get Chart title and validate if it is as per expected??
				String title = chartObj.getText().split("\n")[0];
				checkContentsMatch(title, expectedGraphText[i], "Graph","Verify Graph Title",dr);
				System.out.println("Graph title = " + title);
				
				//Get the context menu rectangle "≡" and click on the button so pop up options are enabled
				String menuLocator = locator + contId.get(i) + "]>div>svg>g.highcharts-button>rect";
				WebElement chartMenu = dr.findElement(By.cssSelector(menuLocator));
				Thread.sleep(1000);
				action.moveToElement(chartMenu).build().perform();
				Thread.sleep(2000);
				
				//chartMenu.click();
				String ExpectedBrowser="org.openqa.selenium.firefox.FirefoxDriver";
				String ActualBrowser=dr.getClass().getName();
				System.out.println(ActualBrowser);
				if(ActualBrowser.equals(ExpectedBrowser)){chartMenu.click();}
				else{action.click().build().perform();}
				Thread.sleep(1000);
					
				String popUpLocator = locator + contId.get(i) + "]>div>div.highcharts-contextmenu>div>div";

				List<WebElement> popUpOptions = dr.findElements(By.cssSelector(popUpLocator));

				System.out.println("No. of Pop Up Window Options = " + popUpOptions.size()) ;
				//Find the pop up window and navigate to validate the 5 options
				for(int j=0; j< popUpOptions.size(); j++){
					WebElement opt = popUpOptions.get(j);
					Thread.sleep(1000);
					action.moveToElement(opt);
					String optText = opt.getText();
					System.out.println("Option text == " + optText);
					String stepName = "Verify Download Link";
					checkContentsMatch(optText, expMenuOption[j], "Chart Context Menu",stepName,dr);
				}
			}
		}
@Test   

    public void auto_Clini_Home_003() throws Exception{
    	login(dr);
    	Thread.sleep(2000);
    	//Configure Tab
    	WebElement configure= dr.findElement(By.xpath("//*[contains(text(),'Configure')]"));
    	clickElement(configure, "Configure", "Configure Tab details", dr);
    	String expectedConfigUrl="https://bridgetherapeutics.cliniops.com/investigator/configurestudy/general";
    	String actualConfigUrl=dr.getCurrentUrl();
    	validateURL(expectedConfigUrl,actualConfigUrl,"Configure URL Check",dr);
    	Actions action=new Actions(dr);
    	action.moveToElement(dr.findElement(By.xpath("//*[contains(text(),'Configure')]"))).build().perform();
    	Thread.sleep(3000);
        String expectedTextColor11="rgba(255, 255, 255, 1)";
    	String actualTextColor11 = dr.findElement(By.xpath("//*[contains(text(),'Configure')]")).getCssValue("color");
    	checkHighlightText(expectedTextColor11,actualTextColor11,"Configure tab highlight",dr);
        WebElement studyDetails=dr.findElement(By.xpath(".//*[@id='content-body']/div[1]/div/div[2]/div[1]/ul/li[1]/a"));
        action.moveToElement(studyDetails).build().perform();
    	Thread.sleep(3000);
        String expectedTextColor12="rgba(255, 255, 255, 1)";
    	String actualTextColor12 = studyDetails.getCssValue("color");
    	checkHighlightText(expectedTextColor12,actualTextColor12,"Study Details highlight",dr);
    	WebElement studyDetailsPage= dr.findElement(By.xpath("//div[@id='content-body']/div/div/h3"));
    	String actualText=studyDetailsPage.getText().substring(0, 13);
    	String expectedText="Study Details";
    	checkContentsMatch(actualText,expectedText,"Study Details","Configure Study Details",dr); 
    	Thread.sleep(3000);
    	//Manage Tab
    	WebElement manage= dr.findElement(By.xpath("//a[contains(text(),'Manage')]"));
    	clickElement(manage, "Manage Tab", "Manage Tab details", dr);
    	String expectedManageUrl="https://bridgetherapeutics.cliniops.com/investigator/managestudy/roles";
    	String actualManageUrl=dr.getCurrentUrl();
    	validateURL(expectedManageUrl,actualManageUrl,"Manage URL Check",dr);
    	action.moveToElement(dr.findElement(By.xpath("//a[contains(text(),'Manage')]"))).build().perform();
    	Thread.sleep(3000);
        String expectedTextColor21="rgba(255, 255, 255, 1)";
    	String actualTextColor21 = dr.findElement(By.xpath("//a[contains(text(),'Manage')]")).getCssValue("color");
    	checkHighlightText(expectedTextColor21,actualTextColor21,"Manage tab highlight",dr);
        WebElement roles=dr.findElement(By.xpath(".//*[@id='content-body']/div/div/div[2]/div[1]/ul/li[1]/a"));
        action.moveToElement(roles).build().perform();
    	Thread.sleep(3000);
        String expectedTextColor22="rgba(255, 255, 255, 1)";
    	String actualTextColor22 = roles.getCssValue("color");
    	checkHighlightText(expectedTextColor22,actualTextColor22,"Roles highlight",dr);
    	WebElement studyUserRoles= dr.findElement(By.xpath("//div[@id='content-body']/div/div/h3"));
    	String actualText2=studyUserRoles.getText().substring(0, 16);
    	String expectedText2="Study User Roles";
    	checkContentsMatch(actualText2,expectedText2,"Study User Roles","Study User Roles",dr); 
    	Thread.sleep(3000);
    	
       	//Analyze Tab
    	WebElement analyze= dr.findElement(By.xpath("//*[contains(text(),'Analyze')]"));
    	clickElement(analyze, "Analyze Tab", "Analyze Tab details", dr);
    	String expectedAnalyzeUrl="https://bridgetherapeutics.cliniops.com/investigator/analyzestudy";
    	String actualAnalyzeUrl=dr.getCurrentUrl();
    	validateURL(expectedAnalyzeUrl,actualAnalyzeUrl,"Analyze URL Check",dr);
    	action.moveToElement(dr.findElement(By.xpath("//*[contains(text(),'Analyze')]"))).build().perform();
    	Thread.sleep(3000);
        String expectedTextColor31="rgba(255, 255, 255, 1)";
    	String actualTextColor31 = dr.findElement(By.xpath("//*[contains(text(),'Analyze')]")).getCssValue("color");
    	checkHighlightText(expectedTextColor31,actualTextColor31,"Manage tab highlight",dr);
    	WebElement export=dr.findElement(By.xpath(".//*[@id='content-body']/div/div[1]/div[2]/ul/li[1]/a"));
        action.moveToElement(export).build().perform();
    	Thread.sleep(3000);
        String expectedTextColor32="rgba(255, 255, 255, 1)";
    	String actualTextColor32 = export.getCssValue("color");
    	checkHighlightText(expectedTextColor32,actualTextColor32,"Export highlight",dr);    	
    	WebElement studyAnalysis = dr.findElement(By.xpath("//div[@id='content-body']/div/div/h3"));
    	String actualText3=studyAnalysis.getText().substring(0, 14);
    	String expectedText3="Study Analysis";
    	checkContentsMatch(actualText3,expectedText3,"Study Analysis","Study Analysis",dr); 
    	Thread.sleep(3000);
    	//Subjects Tab
    	WebElement subjects= dr.findElement(By.xpath("//*[contains(text(),'Subjects')]"));
    	clickElement(subjects, "Subjects Tab", "Subjects Tab details", dr);
    	String expectedSubjectsUrl="https://bridgetherapeutics.cliniops.com/investigator/viewsubjects";
    	String actualSubjectsUrl=dr.getCurrentUrl();
    	validateURL(expectedSubjectsUrl,actualSubjectsUrl,"Subjects URL Check",dr);
    	action.moveToElement(dr.findElement(By.xpath("//*[contains(text(),'Subjects')]"))).build().perform();
    	Thread.sleep(3000);
        String expectedTextColor41="rgba(255, 255, 255, 1)";
    	String actualTextColor41 = dr.findElement(By.xpath(".//*[@id='nav']/ul/li[5]/a")).getCssValue("color");
    	checkHighlightText(expectedTextColor41,actualTextColor41,"Subjects tab highlight",dr);      	  	
    	WebElement subjectSummary = dr.findElement(By.xpath(".//*[@id='content-body']/div/div[2]/div[1]/h3"));
    	String actualText41=subjectSummary.getText();
    	String expectedText41="Subject Summary";
    	checkContentsMatch(actualText41,expectedText41,"Subject Summary","Subject Summary",dr); 
    	Thread.sleep(3000);
    	WebElement subjectData = dr.findElement(By.xpath(".//*[@id='content-body']/div/div[2]/div[2]/h3"));
    	String actualText42=subjectData.getText();
    	String expectedText42="Subject Data";
    	checkContentsMatch(actualText42,expectedText42,"Subject Data","Subject Data",dr); 
    	//Audit
       	WebElement audit= dr.findElement(By.xpath("//*[contains(text(),'Audit')]"));
    	clickElement(audit, "Audit Tab", "Audit Tab details", dr);
    	String expectedAuditUrl="https://bridgetherapeutics.cliniops.com/investigator/audittrial";
    	String actualAuditUrl=dr.getCurrentUrl();
    	validateURL(expectedAuditUrl,actualAuditUrl,"Audit URL Check",dr);
    	action.moveToElement(dr.findElement(By.xpath("//*[contains(text(),'Audit')]"))).build().perform();
    	Thread.sleep(3000);
        String expectedTextColor51="rgba(255, 255, 255, 1)";
    	String actualTextColor51 = dr.findElement(By.xpath("//*[contains(text(),'Audit')]")).getCssValue("color");
    	checkHighlightText(expectedTextColor51,actualTextColor51,"Audit tab highlight",dr);     
    	WebElement auditTrail = dr.findElement(By.xpath(".//*[text()='Audit Trail']"));
    	String actualText5=auditTrail.getText();
    	String expectedText5="Audit Trail";
    	checkContentsMatch(actualText5,expectedText5,"Audit Trail","Audit Trail",dr); 
      	//Profile Tab
    	WebElement profile= dr.findElement(By.xpath("//*[contains(text(),'Profile')]"));
    	clickElement(profile, "Profile Tab", "Profile Tab details", dr);    	
    	String expectedProfileUrl="https://bridgetherapeutics.cliniops.com/investigator/profile";
    	String actualProfileUrl=dr.getCurrentUrl();
    	validateURL(expectedProfileUrl,actualProfileUrl,"Profile URL Check",dr);
    	action.moveToElement(dr.findElement(By.xpath("//*[contains(text(),'Profile')]"))).build().perform();
    	Thread.sleep(3000);
        String expectedTextColor61="rgba(255, 255, 255, 1)";
    	String actualTextColor61 = dr.findElement(By.xpath(".//*[text()='Profile']")).getCssValue("color");
    	checkHighlightText(expectedTextColor61,actualTextColor61,"Profile tab highlight",dr);  
    	WebElement profileInformation = dr.findElement(By.xpath(".//*[@id='content-body']/div[2]/div/h3"));
    	String actualText6=profileInformation.getText();
    	String expectedText6="Profile Information";
    	checkContentsMatch(actualText6,expectedText6,"Profile Information","Profile Information",dr); 
    }
@Test
public void auto_Clini_Confg_001() throws InterruptedException, IOException{
	login(dr);
	Thread.sleep(2000);
	WebElement config=dr.findElement(By.xpath(".//*[text()='Configure']"));
    Actions action=new Actions(dr);
    action.moveToElement(config).build().perform();
    Thread.sleep(3000);
	String expectedTextColor="rgba(255, 255, 255, 1)";
	String ActualTextColor = dr.findElement(By.xpath(".//*[text()='Configure']")).getCssValue("color");
	Thread.sleep(2000);
	checkHighlightText(expectedTextColor,ActualTextColor,"Configure tab highlight",dr);
	clickElement(config, "Configure tab", "Configure tab",dr);
	String ExpectedURL="https://bridgetherapeutics.cliniops.com/investigator/configurestudy/general";
	String ActualURL=dr.getCurrentUrl();
	validateURL(ExpectedURL,ActualURL,"Configure URL Check",dr);
	
}
@Test
public void auto_Clini_Confg_004() throws InterruptedException, IOException

{	login(dr);
	Thread.sleep(3000);
	WebElement configure_tab = dr.findElement(By.xpath(".//*[@id='nav']//li[2]"));
	clickElement(configure_tab, "configureTab", "click configureTab", dr);
	Thread.sleep(4000);
	//validate text present in the target enrollment textbox
	String expected = "300";
	String actual = dr.findElement(By.xpath(".//*[@id='targetenrollment']")).getAttribute("value");
	System.out.println(actual);
	checkContentsMatch(actual, expected, "targetEnrollment", "Target Enrollment Value", dr);
	//verify value(custom) visibility in subjectID dropdown 
	String expSubjectId = "Custom";
	Thread.sleep(3000);
	String actual_subjectID_option=dr.findElement(By.xpath("//option[@value='Custom']")).getText();
	if(actual_subjectID_option.equals(expSubjectId))
	{checkContentsMatch(actual_subjectID_option, expSubjectId,"subjectID","SubjectID dropdown option", dr);
	System.out.println("Custom value is present in subjectID dropdown options");
	}
   	//verify data change options present in textboxes
	String expReasonForDataChange1 = "Invalid entry";
	String expReasonForDataChange2 = "Updated Information";
	List<WebElement> reasonForDataChangeOptions = dr.findElements(By.xpath(".//*[@id='reasonForDataChange']"));
	String actual1 = reasonForDataChangeOptions.get(0).getAttribute("value");
	checkContentsMatch(actual1, expReasonForDataChange1, "reasonForDataChange", "Reason for Data Change:Value1", dr);
	String actual2 = reasonForDataChangeOptions.get(1).getAttribute("value");
	checkContentsMatch(actual2, expReasonForDataChange2, "reasonForDataChange", "Reason for Data Change:Value2", dr);	
	//verify file visibility of  informed consent form
	String expFileName = "ICF - Interview...cians_v8.pdf";
	String actualFileName = dr.findElement(By.xpath(".//*[@id='study_general_settings']/div[2]/div[1]/fieldset[2]/div/span")).getText();
	System.out.println(actualFileName);
	checkContentsMatch(actualFileName, expFileName, "dataChange", "Choose FileName Check", dr);

	

	

}
    @AfterMethod

    public void closeBrowser(){
    	dr.close();
    	dr.quit();
    }


}




