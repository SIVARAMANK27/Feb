package tests;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.c_homePage;
import pages.b_loginPage;
import utils.CSVTestListener;
import utils.ExcelReader;
import utils.ExtentReportManager;
import utils.RetryAnalyzer;

@Listeners(CSVTestListener.class)
public class b_loginPageTest extends BaseTest {

	private Logger logger = LogManager.getLogger(b_loginPageTest.class); // Initialize Log4j2 logger
	
	 private static final String EXCEL_PATH = "src\\test\\resources\\TestData.xlsx";
	 private static final String SHEET_NAME = "LoginData";

	@Test(retryAnalyzer = RetryAnalyzer.class, enabled = true)
	public void validCredential() {

		logger.info("Starting Login Test...");
		ExtentReportManager.createTest("TS_LO_01");
		
        String testCaseName = "ValidLoginTest"; // The specific test case you want to run
		Map<String, String> testData = ExcelReader.getTestCaseData(EXCEL_PATH, SHEET_NAME, testCaseName);
		String username = testData.get("Username");
		String password = testData.get("Password");
		
		b_loginPage loginPage = new b_loginPage(driver);
		loginPage.validCrdential(username, password);
		logger.info("Entered user name and password.");

		c_homePage homePage = new c_homePage(driver);
		homePage.getWelcomeMessage();
		String getTitle = homePage.getTitleMessage();
		System.out.println("The page title is :" + getTitle);
		logger.info("Retrieved welcome message");
		Assert.assertEquals(getTitle, "Projects", "Title mismatch");

		ExtentReportManager.getTest().pass("Valid credential test passed");
		logger.info("Login test passed.");
	}

	@Test(retryAnalyzer = RetryAnalyzer.class, dependsOnMethods="validCredential", enabled = true)
	public void verify_InvalidCredential() {
		logger.info("Starting Login Test...");
		ExtentReportManager.createTest("TS_LO_02");

		String testCaseName = "InValidLoginTest"; // The specific test case you want to run
		Map<String, String> testData = ExcelReader.getTestCaseData(EXCEL_PATH, SHEET_NAME, testCaseName);
		String username = testData.get("Username");
		String password = testData.get("Password");
		
		b_loginPage loginPage = new b_loginPage(driver);
		loginPage.invalidCredential(username, password);
		logger.info("Entered user name and password.");

		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement t1 = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//div[text()=\"Please enter a registered email!\"]")));
		String text = t1.getText();
		System.out.println("The Expected result is :" + text);
		Assert.assertEquals(text, "Please enter a registered email!", "Title are mismatch");

		ExtentReportManager.getTest().pass("Invalid username vs password");
		logger.info("Login test passed.");
	}

	@Test(retryAnalyzer = RetryAnalyzer.class, enabled = true, dependsOnMethods="verify_InvalidCredential")
	public void verify_Empty_Credential() {
		logger.info("Starting Login Test...");
		ExtentReportManager.createTest("Ts_LO_03");

		String testCaseName = "Empty_Credential"; // The specific test case you want to run
		Map<String, String> testData = ExcelReader.getTestCaseData(EXCEL_PATH, SHEET_NAME, testCaseName);
		String username = testData.get("Username");
		String password = testData.get("Password");
		
		b_loginPage loginPage = new b_loginPage(driver);
		loginPage.balnkCredential(username, password);
		logger.info("Login button is clicked successfully");
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement t1 = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()=\"Please enter the email\"]")));
		String text = t1.getText();
		System.out.println("The Expected result is :" + text);
		Assert.assertEquals(text, "Please enter the email", "Title are mismatch");

		ExtentReportManager.getTest().pass("Nothing login credential test case passed");
		logger.info("Login test passed.");
	}

	@Test(retryAnalyzer = RetryAnalyzer.class, enabled = true, dependsOnMethods="verify_Empty_Credential")
	public void verify_InValid_Username_valid_Password() {
		logger.info("Starting Login Test...");
		ExtentReportManager.createTest("TS_LO_05");

		String testCaseName = "InValid_Username_valid_Password"; // The specific test case you want to run
		Map<String, String> testData = ExcelReader.getTestCaseData(EXCEL_PATH, SHEET_NAME, testCaseName);
		String username = testData.get("Username");
		String password = testData.get("Password");
	
		b_loginPage loginPage = new b_loginPage(driver);
		loginPage.invalidCredential(username, password);
		logger.info("Entered user name and password.");

		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement t1 = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//div[text()=\"Please enter a registered email!\"]")));
		String text = t1.getText();
		System.out.println("The Expected result is :" + text);
		Assert.assertEquals(text, "Please enter a registered email!", "Title are mismatch");

		ExtentReportManager.getTest().pass("Invalid username and valid password test case passed");
		logger.info("Login test passed.");
	}

	@Test(retryAnalyzer = RetryAnalyzer.class, enabled = true, dependsOnMethods="verify_InValid_Username_valid_Password")
	public void verify_Valid_Username_Invalid_Password() {
		logger.info("Starting Login Test...");
		ExtentReportManager.createTest("TS_LO_06");

		String testCaseName = "Valid_Username_Invalid_Password"; // The specific test case you want to run
		Map<String, String> testData = ExcelReader.getTestCaseData(EXCEL_PATH, SHEET_NAME, testCaseName);
		String username = testData.get("Username");
		String password = testData.get("Password");
		
		b_loginPage loginPage = new b_loginPage(driver);
		loginPage.invalidCredential(username, password);
		logger.info("Entered user name and password.");

		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement t1 = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//div[text()=\"Please enter a correct password!\"]")));
		String text = t1.getText();
		System.out.println("The Expected result is :" + text);
		Assert.assertEquals(text, "Please enter a correct password!", "Title are mismatch");

		ExtentReportManager.getTest().pass("valid username and invalid password test case passed");
		logger.info("Login test passed.");
	}

	@Test(retryAnalyzer = RetryAnalyzer.class, enabled = true)
	public void verify_Without_DomainValidation() {
		logger.info("Starting Login Test...");
		ExtentReportManager.createTest("TS_LO_07");

		String testCaseName = "Without_DomainValidation"; // The specific test case you want to run
		Map<String, String> testData = ExcelReader.getTestCaseData(EXCEL_PATH, SHEET_NAME, testCaseName);
		String username = testData.get("Username");
		String password = testData.get("Password");
		
		b_loginPage loginPage = new b_loginPage(driver);
		loginPage.invalidCredential(username,password);
		logger.info("Entered user name and password.");

		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement t1 = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//div[text()=\"Please enter a registered email!\"]")));
		String text = t1.getText();
		System.out.println("The Expected result is :" + text);
		Assert.assertEquals(text, "Invalid format error", "Title are mismatch");

		ExtentReportManager.getTest().pass("Invalid format error displayed");
		logger.info("Login test passed.");
	}

	@Test(retryAnalyzer = RetryAnalyzer.class, enabled = true)
	public void verify_minPassword_validation() {
		logger.info("Starting Login Test...");
		ExtentReportManager.createTest("TS_LO_08");

		String testCaseName = "minPassword_validation"; // The specific test case you want to run
		Map<String, String> testData = ExcelReader.getTestCaseData(EXCEL_PATH, SHEET_NAME, testCaseName);
		String username = testData.get("Username");
		String password = testData.get("Password");
		
		b_loginPage loginPage = new b_loginPage(driver);
		loginPage.invalidCredential(username, password);
		logger.info("Entered user name and password.");

		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement t1 = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//div[text()=\"Please enter a correct password!\"]")));
		String text = t1.getText();
		System.out.println("The Expected result is :" + text);
		Assert.assertEquals(text, "Please enter a correct password!", "Title are mismatch");

		ExtentReportManager.getTest().pass("Verify min char are verified");
		logger.info("Login test passed.");
	}

	@Test(retryAnalyzer = RetryAnalyzer.class, enabled = true)
	public void verify_ExceedLimit_validation() {
		logger.info("Starting Login Test...");
		ExtentReportManager.createTest("TS_LO_09");

		String testCaseName = "ExceedLimit_validation"; // The specific test case you want to run
		Map<String, String> testData = ExcelReader.getTestCaseData(EXCEL_PATH, SHEET_NAME, testCaseName);
		String username = testData.get("Username");
		String password = testData.get("Password");
		
		b_loginPage loginPage = new b_loginPage(driver);
		loginPage.invalidCredential(username, password);
		logger.info("Entered user name and password.");

		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement t1 = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//div[text()=\"Please enter a correct password!\"]")));
		String text = t1.getText();
		System.out.println("The Expected result is :" + text);
		Assert.assertEquals(text, "Please enter a correct password!", "Title are mismatch");

		ExtentReportManager.getTest().pass("Exceeeding limit validation");
		logger.info("Login test passed.");
	}

	@Test(retryAnalyzer = RetryAnalyzer.class, enabled = true)
	public void verify_Password_IsMasked() {
		logger.info("Starting Login Test...");
		ExtentReportManager.createTest("TS_LO_10");

		String testCaseName = "Password_IsMasked"; // The specific test case you want to run
		Map<String, String> testData = ExcelReader.getTestCaseData(EXCEL_PATH, SHEET_NAME, testCaseName);
		String username = testData.get("Username");
		String password = testData.get("Password");
		
		b_loginPage loginPage = new b_loginPage(driver);
		loginPage.invalidCredential(username, password);
		logger.info("Entered user name and password.");

		WebElement passwordField = driver.findElement(By.id("password"));
		String fieldType = passwordField.getAttribute("type");
		System.out.println("Password field type: " + fieldType);

		Assert.assertEquals(fieldType, "password", "Password field is not masked!");
		ExtentReportManager.getTest().pass("Masked validation");
		logger.info("Login test passed.");
	}

	@Test(retryAnalyzer = RetryAnalyzer.class, enabled = true)
	public void Verify_password_is_unmasked() {
		logger.info("Starting Login Test...");
		ExtentReportManager.createTest("TS_LO_11");

		String testCaseName = "password_is_unmasked"; // The specific test case you want to run
		Map<String, String> testData = ExcelReader.getTestCaseData(EXCEL_PATH, SHEET_NAME, testCaseName);
		String username = testData.get("Username");
		String password = testData.get("Password");
		
		b_loginPage loginPage = new b_loginPage(driver);
		loginPage.invalidCredential(username, password);
		logger.info("Entered user name and password.");

		// Verify password is initially masked
		WebElement passwordField = driver.findElement(By.id("password"));
		String fieldType = passwordField.getAttribute("type");
		System.out.println("Password field type: " + fieldType);

		// Locate and click the eye/toggle icon to un-mask password
		driver.findElement(By.xpath("//input[@id=\"custom-switch\"]")).click();

		// Verify password is unmasked
		String unmaskedType = passwordField.getAttribute("type");
		Assert.assertEquals(unmaskedType, "text", "Password should be unmasked after clicking the toggle button!");

		ExtentReportManager.getTest().pass("Unmasked validation");
		logger.info("Login test passed.");
	}

	/****************************************************************************************/
}
