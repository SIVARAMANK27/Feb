package tests;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.HomePage;
import pages.LoginPage;
import pages.RegisterPage;
import utils.ExtentReportManager;
import utils.RetryAnalyzer;

public class TestCase extends BaseTest {

	private Logger logger = LogManager.getLogger(TestCase.class); // Initialize Log4j2 logger

	
	 	 
	@Test(retryAnalyzer = RetryAnalyzer.class, enabled = false)
	public void verify_valid_credential(String username, String password) {
		logger.info("Starting Login Test...");
		ExtentReportManager.createTest("TS_LO_02");

		LoginPage loginPage = new LoginPage(driver);
		loginPage.validCrdential(username, password);
		logger.info("Entered user name and password.");

		HomePage homePage = new HomePage(driver);
		homePage.getWelcomeMessage();
		String getTitle = homePage.getTitleMessage();
		System.out.println("The page title is :" + getTitle);
		logger.info("Retrieved welcome message");
		Assert.assertEquals(getTitle, "Projects", "Title mismatch");

		ExtentReportManager.getTest().pass("Valid credential test passed");
		logger.info("Login test passed.");
	}

	@Test(retryAnalyzer = RetryAnalyzer.class, enabled = false)
	public void verify_InvalidCredential() {
		logger.info("Starting Login Test...");
		ExtentReportManager.createTest("TS_LO_03");

		LoginPage loginPage = new LoginPage(driver);
		loginPage.invalidCredential("sivaramankrajasri.net", "18Rajasri");
		logger.info("Entered user name and password.");

		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement t1 = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//div[text()=\"Please enter a registered email!\"]")));
		String text = t1.getText();
		System.out.println("The Expected result is :" + text);
		Assert.assertEquals(text, "Please enter a registered email!", "Title are mismatch");

		ExtentReportManager.getTest().pass("Invalid Username vs password test case passed");
		logger.info("Login test passed.");
	}

	@Test(retryAnalyzer = RetryAnalyzer.class, enabled = false)
	public void verify_Empty_Credential() {
		logger.info("Starting Login Test...");
		ExtentReportManager.createTest("Ts_Lo_04");

		LoginPage loginPage = new LoginPage(driver);
		loginPage.balnkCredential();
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

	@Test(retryAnalyzer = RetryAnalyzer.class, enabled = false)
	public void verify_InValid_Username_valid_Password() {
		logger.info("Starting Login Test...");
		ExtentReportManager.createTest("TS_LO_05");

		LoginPage loginPage = new LoginPage(driver);
		loginPage.invalidCredential("sivaramankrajasri.net", "18Rajasri@");
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

	@Test(retryAnalyzer = RetryAnalyzer.class, enabled = false)
	public void verify_Valid_Username_Invalid_Password() {
		logger.info("Starting Login Test...");
		ExtentReportManager.createTest("TS_LO_06");

		LoginPage loginPage = new LoginPage(driver);
		loginPage.invalidCredential("sivaramank@rajasri.net", "18Rajasri");
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

	@Test(retryAnalyzer = RetryAnalyzer.class, enabled = false)
	public void verify_Without_DomainValidation() {
		logger.info("Starting Login Test...");
		ExtentReportManager.createTest("TS_LO_07");

		LoginPage loginPage = new LoginPage(driver);
		loginPage.invalidCredential("sivaramankrajasri.net", "18Rajasri");
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

	@Test(retryAnalyzer = RetryAnalyzer.class, enabled = false)
	public void verify_minPassword_validation() {
		logger.info("Starting Login Test...");
		ExtentReportManager.createTest("TS_LO_08");

		LoginPage loginPage = new LoginPage(driver);
		loginPage.invalidCredential("sivaramank@rajasri.net", "1");
		logger.info("Entered user name and password.");

		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement t1 = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//div[text()=\"Please enter a correct password!\"]")));
		String text = t1.getText();
		System.out.println("The Expected result is :" + text);
		Assert.assertEquals(text, "please enter at leat 8 char", "Title are mismatch");

		ExtentReportManager.getTest().pass("Verify min char are verified");
		logger.info("Login test passed.");
	}

	@Test(retryAnalyzer = RetryAnalyzer.class, enabled = false)
	public void verify_ExceedLimit_validation() {
		logger.info("Starting Login Test...");
		ExtentReportManager.createTest("TS_LO_09");

		LoginPage loginPage = new LoginPage(driver);
		loginPage.invalidCredential("sivaramank@rajasri.net", "18Rajasri@123456555554444444");
		logger.info("Entered user name and password.");

		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement t1 = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//div[text()=\"Please enter a correct password!\"]")));
		String text = t1.getText();
		System.out.println("The Expected result is :" + text);
		Assert.assertEquals(text, "please enter maximum 16 char are allowed", "Title are mismatch");

		ExtentReportManager.getTest().pass("Exceeeding limit validation");
		logger.info("Login test passed.");
	}

	@Test(retryAnalyzer = RetryAnalyzer.class, enabled = false)
	public void verify_Password_IsMasked() {
		logger.info("Starting Login Test...");
		ExtentReportManager.createTest("TS_LO_10"); 
		LoginPage loginPage = new LoginPage(driver);
		loginPage.invalidCredential("sivaramank@rajasri.net", "18Rajasri@");
		logger.info("Entered user name and password.");

		WebElement passwordField = driver.findElement(By.id("password"));
		String fieldType = passwordField.getAttribute("type");
		System.out.println("Password field type: " + fieldType);

		Assert.assertEquals(fieldType, "password", "Password field is not masked!");
		ExtentReportManager.getTest().pass("Masked validation");
		logger.info("Login test passed.");
	}

	@Test(retryAnalyzer = RetryAnalyzer.class, enabled = false)
	public void Verify_password_is_unmasked() {
		logger.info("Starting Login Test...");
		ExtentReportManager.createTest("TS_LO_11");
		LoginPage loginPage = new LoginPage(driver);
		loginPage.invalidCredential("sivaramank@rajasri.net", "18Rajasri@");
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

	/**************** Registration page *****************/

	@Test(retryAnalyzer = RetryAnalyzer.class, enabled = false)
	public void verify_RegistrationPage_hyperLink() {
		logger.info("Starting Login Test...");
		ExtentReportManager.createTest("TS_Reg_01");
		RegisterPage reg = new RegisterPage(driver);
		reg.createanaccount();
		String ExpectedResult = driver.getTitle();
		Assert.assertEquals(ExpectedResult, "D.O.T", "Title mismatch");
		logger.info("verified the assertion");
		ExtentReportManager.getTest().pass("create account hyperlink validation");
		logger.info("Login test passed.");
	}

	@Test(retryAnalyzer = RetryAnalyzer.class, enabled = false)
	public void verify_maximumTeamName() {
		logger.info("Starting Login Test...");
		ExtentReportManager.createTest("TS_Reg_02");
		RegisterPage reg = new RegisterPage(driver);
		reg.createanaccount();
		reg.verfifyteamTextBox(
				"ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVW");
		logger.info("verified maximumUsername");
		ExtentReportManager.getTest().pass("Maximum char text box validation");
		logger.info("Login test passed.");
	}

	@Test(retryAnalyzer = RetryAnalyzer.class, enabled = false)
	public void verify_manimumTeamName() {
		logger.info("Starting Login Test...");
		ExtentReportManager.createTest("TS_Reg_03");
		RegisterPage reg = new RegisterPage(driver);
		reg.createanaccount();
		reg.verfifyteamTextBox("A");
		logger.info("verified minimumUsername");
		ExtentReportManager.getTest().pass("Manimum char text box validation");
		logger.info("Login test passed.");
	}

	@Test(retryAnalyzer = RetryAnalyzer.class, enabled = false)
	public void verify_emptyTeamNameFieldValidation() throws InterruptedException {
		logger.info("Starting Login Test...");
		ExtentReportManager.createTest("TS_Reg_04");
		RegisterPage reg = new RegisterPage(driver);
		reg.createanaccount();
		driver.findElement(By.id("userEmail")).sendKeys("sivaramank@gmail.com");

		WebElement targetElement = driver.findElement(By.xpath("//button[text()=\"Create Account\"]"));
		Actions actions = new Actions(driver);
		actions.moveToElement(targetElement).click().perform();

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		boolean teamName = driver.findElement(By.xpath("//div[text()=\"Please enter a team name\"]")).isDisplayed();
		if (teamName) {
			String expectedResult = driver.findElement(By.xpath("//div[text()=\"Please enter a team name\"]"))
					.getText();
			Assert.assertEquals(expectedResult, "Please enter a team name", "alert mismatch");
		}
		logger.info("verified blank text box validation");
		ExtentReportManager.getTest().pass("Blank text box validation");
		logger.info("Login test passed.");
	}

	@Test(retryAnalyzer = RetryAnalyzer.class, enabled = false)
	public void verify_ValidTeamName() {
		logger.info("Starting Login Test...");
		ExtentReportManager.createTest("TS_Reg_05");
		RegisterPage reg = new RegisterPage(driver);
		reg.createanaccount();
		driver.findElement(By.id("userName")).sendKeys("Testing");
		WebElement targetElement = driver.findElement(By.xpath("//button[text()=\"Create Account\"]"));
		Actions actions = new Actions(driver);
		actions.moveToElement(targetElement).click().perform();
		boolean yourEmail = driver.findElement(By.id("userEmail")).isDisplayed();
		Assert.assertEquals(yourEmail, true);
		logger.info("verified valid username");
		ExtentReportManager.getTest().pass("Verified valid username");
		logger.info("Login test passed.");
	}

	@Test(retryAnalyzer = RetryAnalyzer.class, enabled = false)
	public void verify_validUserName() {
		logger.info("Starting Login Test...");
		ExtentReportManager.createTest("TS_Reg_06");
		RegisterPage reg = new RegisterPage(driver);
		reg.createanaccount();
		driver.findElement(By.id("userName")).sendKeys("Testing");
		reg.usernameValidation("Raman");
		boolean yourEmail = driver.findElement(By.id("userEmail")).isDisplayed();
		Assert.assertEquals(yourEmail, true);
		logger.info("verified valid username");
		ExtentReportManager.getTest().pass("Verified valid username");
		logger.info("Login test passed.");
	}

	@Test(retryAnalyzer = RetryAnalyzer.class, enabled = false)
	public void emptyValidation_username() {
		logger.info("Starting test case ...");
		ExtentReportManager.createTest("TS_Reg_07");
		RegisterPage reg = new RegisterPage(driver);
		reg.createanaccount();
		reg.emptyValidationUsername("Testing Team","sivaramank@gmail.com");

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		boolean teamName = driver.findElement(By.xpath("//div[text()=\"Please enter your name\"]")).isDisplayed();
		if (teamName) {
			String expectedResult = driver.findElement(By.xpath("//div[text()=\"Please enter your name\"]"))
					.getText();
			Assert.assertEquals(expectedResult, "Please enter your name", "alert mismatch");
		}
		logger.info("verified blank text box validation");
		ExtentReportManager.getTest().pass("Blank text box validation");
		logger.info("Login test passed.");
	}
	
	@Test(retryAnalyzer = RetryAnalyzer.class, enabled = false)
	public void verify_ValidEmailAddress() {
		logger.info("Starting test case ...");
		ExtentReportManager.createTest("TS_Reg_08");
		RegisterPage reg = new RegisterPage(driver);
		reg.createanaccount();
		reg.validEmailAddress("Testing","Raman","sivaramank@gmail.com");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		boolean teamName = driver.findElement(By.xpath("//div[text()=\"Please enter a password\"]")).isDisplayed();
		if (teamName) {
			String expectedResult = driver.findElement(By.xpath("//div[text()=\"Please enter a password\"]"))
					.getText();
			Assert.assertEquals(expectedResult, "Please enter a password", "alert mismatch");
		}
		logger.info("verified blank text box validation");
		ExtentReportManager.getTest().pass("Blank text box validation");
		logger.info("Login test passed.");
	}
	
	@Test(retryAnalyzer = RetryAnalyzer.class, enabled = false)
	public void verify_emptyValidationEmailAddress() {
		logger.info("Starting test case ...");
		ExtentReportManager.createTest("TS_Reg_08");
		RegisterPage reg = new RegisterPage(driver);
		reg.createanaccount();
		reg.emptyValidationOfEmail("Testing","Raman");
		logger.info("verified blank text box validation");
		ExtentReportManager.getTest().pass("Blank text box validation");
		logger.info("Login test passed.");
	}
	
	@Test(retryAnalyzer = RetryAnalyzer.class, enabled = false)
	public void PasswordValidationTest() {
		logger.info("Starting test case ...");
		ExtentReportManager.createTest("TS_Reg_08");
		RegisterPage reg = new RegisterPage(driver);
		reg.createanaccount();
		reg.testPasswordValidation("Testing","Raman","sivaramank@gmail.com","Test@1234", "Test@1234", true);
		logger.info("verified password validation");
		ExtentReportManager.getTest().pass("password validation");
		logger.info("Login test passed.");
	}
	
	@Test(retryAnalyzer = RetryAnalyzer.class, enabled = true)
	public void GetListOfWebElement() {
		logger.info("Starting test case ...");
		ExtentReportManager.createTest("TS_Reg_08");
		LoginPage login = new LoginPage(driver);
		login.validCrdential("sivaramank@rajasri.net", "18Rajasri@");
		driver.findElement(By.xpath("//span[text()=\"Projects\"]")).click();
		List<WebElement> elements = driver.findElements(By.xpath("//section[@class=\"section\"]"));
		for (WebElement element : elements) {
            System.out.println(element.getText());
        }
		logger.info("Get list of element");
		ExtentReportManager.getTest().pass("Try to getting list of webElement");
		logger.info("Get list of webElement fitched successfully");
	}
}
