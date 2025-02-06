package tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.HomePage;
import pages.LoginPage;
import utils.ExtentReportManager;
import utils.RetryAnalyzer;

public class TestCase extends BaseTest {

	private Logger logger = LogManager.getLogger(TestCase.class); // Initialize Log4j2 logger
	
	@Test(retryAnalyzer = RetryAnalyzer.class, enabled=true) 
	public void verify_valid_credential() {
		logger.info("Starting Login Test...");
		ExtentReportManager.createTest("TS_LO_01");

		LoginPage loginPage = new LoginPage(driver);
		loginPage.login("sivaramank@rajasri.net", "18Rajasri@");
		logger.info("Entered user name and password.");

		HomePage homePage = new HomePage(driver);
		homePage.getWelcomeMessage();
		String getTitle = homePage.getTitleMessage();
		System.out.println("The page title is :"+ getTitle);
		logger.info("Retrieved welcome message");
		Assert.assertEquals(getTitle, "Projects","Title mismatch");

		ExtentReportManager.getTest().pass("Valid credential test passed");
		logger.info("Login test passed.");
	}
	
	@Test(retryAnalyzer = RetryAnalyzer.class, enabled=true)
	public void verify_Valid_Username_Invalid_Password() {
		logger.info("Starting Login Test...");
		ExtentReportManager.createTest("TS_LO_02");
		
		LoginPage loginPage = new LoginPage(driver);
		loginPage.invalidCredential("sivaramank@rajasri.net", "18Rajasri");
		logger.info("Entered user name and password.");
		
		WebDriverWait wait=new WebDriverWait(driver, 10);
		WebElement t1=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()=\"Please enter a correct password!\"]")));
		String text=t1.getText();
		System.out.println("The Expected result is :"+ text);
		Assert.assertEquals(text, "Please enter a correct password!", "Title are mismatch");
		
		ExtentReportManager.getTest().pass("valid username and invalid password test case passed");
		logger.info("Login test passed.");
	}
	
	@Test(retryAnalyzer = RetryAnalyzer.class, enabled=true)
	public void verify_InValid_Username_valid_Password() {
		logger.info("Starting Login Test...");
		ExtentReportManager.createTest("TS_LO_03");
		
		LoginPage loginPage = new LoginPage(driver);
		loginPage.invalidCredential("sivaramankrajasri.net", "18Rajasri@");
		logger.info("Entered user name and password.");
		
		WebDriverWait wait=new WebDriverWait(driver, 10);
		WebElement t1=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()=\"Please enter a registered email!\"]")));
		String text=t1.getText();
		System.out.println("The Expected result is :"+ text);
		Assert.assertEquals(text, "Please enter a registered email!", "Title are mismatch");
		
		ExtentReportManager.getTest().pass("Invalid username and valid password test case passed");
		logger.info("Login test passed.");
	}
	
	@Test(retryAnalyzer = RetryAnalyzer.class, enabled=true)
	public void verify_InvalidCredential() {
		logger.info("Starting Login Test...");
		ExtentReportManager.createTest("TS_LO_04");
		
		LoginPage loginPage = new LoginPage(driver);
		loginPage.invalidCredential("sivaramankrajasri.net", "18Rajasri");
		logger.info("Entered user name and password.");
		
		WebDriverWait wait=new WebDriverWait(driver, 10);
		WebElement t1=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()=\"Please enter a registered email!\"]")));
		String text=t1.getText();
		System.out.println("The Expected result is :"+ text);
		Assert.assertEquals(text, "Please enter a registered email!", "Title are mismatch");
		
		ExtentReportManager.getTest().pass("Invalid Username vs password test case passed");
		logger.info("Login test passed.");
	}
	
	@Test(retryAnalyzer = RetryAnalyzer.class, enabled=true)
	public void verify_Empty_Credential() {
		logger.info("Starting Login Test...");
		ExtentReportManager.createTest("TS_LO_05");
		
		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginbutton();
		logger.info("Login button is clicked successfully");
		WebDriverWait wait=new WebDriverWait(driver, 10);
		WebElement t1=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()=\"Please enter the email\"]")));
		String text=t1.getText();
		System.out.println("The Expected result is :"+ text);
		Assert.assertEquals(text, "Please enter the email", "Title are mismatch");
		
		ExtentReportManager.getTest().pass("Nothing login credential test case passed");
		logger.info("Login test passed.");
	}
}

