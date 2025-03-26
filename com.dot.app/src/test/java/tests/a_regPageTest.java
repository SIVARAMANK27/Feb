package tests;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.b_loginPage;
import pages.a_registerPage;
import utils.ExtentReportManager;
import utils.RetryAnalyzer;

public class a_regPageTest extends BaseTest {
	
	@Test(retryAnalyzer = RetryAnalyzer.class, enabled = false)
	public void verify_RegistrationPage_hyperLink() {
		logger.info("Starting Login Test...");
		ExtentReportManager.createTest("TS_Reg_01");

		a_registerPage reg = new a_registerPage(driver);
		reg.createanaccount();
		String ExpectedResult = driver.getTitle();
		Assert.assertEquals(ExpectedResult, "D.O.T", "Title mismatch");
		logger.info("verified the assertion");
		ExtentReportManager.getTest().pass("create account hyperlink validation");
		logger.info("Login test passed.");
	}

	@Test(retryAnalyzer = RetryAnalyzer.class, enabled = false)
	public void verify_maximumTeamNameValidation(String value) {
		logger.info("Starting Login Test...");
		ExtentReportManager.createTest("TS_Reg_02");
		
		a_registerPage reg = new a_registerPage(driver);
		reg.createanaccount();
		reg.verfifyteamTextBox();
		logger.info("verified maximumUsername");
		ExtentReportManager.getTest().pass("Maximum char text box validation");
		logger.info("Login test passed.");

	}

	@Test(retryAnalyzer = RetryAnalyzer.class, enabled = false)
	public void verify_manimumTeamName() {
		logger.info("Starting Login Test...");
		ExtentReportManager.createTest("TS_Reg_03");
		a_registerPage reg = new a_registerPage(driver);
		reg.createanaccount();
		reg.verfifyteamTextBox();
		logger.info("verified minimumUsername");
		ExtentReportManager.getTest().pass("Manimum char text box validation");
		logger.info("Login test passed.");
	}

	@Test(retryAnalyzer = RetryAnalyzer.class, enabled = false)
	public void verify_emptyTeamNameFieldValidation() throws InterruptedException {
		logger.info("Starting Login Test...");
		ExtentReportManager.createTest("TS_Reg_04");
		a_registerPage reg = new a_registerPage(driver);
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
		a_registerPage reg = new a_registerPage(driver);
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
		a_registerPage reg = new a_registerPage(driver);
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
		a_registerPage reg = new a_registerPage(driver);
		reg.createanaccount();
		reg.emptyValidationUsername("Testing Team", "sivaramank@gmail.com");

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		boolean teamName = driver.findElement(By.xpath("//div[text()=\"Please enter your name\"]")).isDisplayed();
		if (teamName) {
			String expectedResult = driver.findElement(By.xpath("//div[text()=\"Please enter your name\"]")).getText();
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
		a_registerPage reg = new a_registerPage(driver);
		reg.createanaccount();
		reg.validEmailAddress("Testing", "Raman", "sivaramank@gmail.com");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		boolean teamName = driver.findElement(By.xpath("//div[text()=\"Please enter a password\"]")).isDisplayed();
		if (teamName) {
			String expectedResult = driver.findElement(By.xpath("//div[text()=\"Please enter a password\"]")).getText();
			Assert.assertEquals(expectedResult, "Please enter a password", "alert mismatch");
		}
		logger.info("verified blank text box validation");
		ExtentReportManager.getTest().pass("Blank text box validation");
		logger.info("Login test passed.");
	}

	@Test(retryAnalyzer = RetryAnalyzer.class, enabled = false)
	public void verify_emptyValidationEmailAddress() {
		logger.info("Starting test case ...");
		ExtentReportManager.createTest("TS_Reg_09");
		a_registerPage reg = new a_registerPage(driver);
		reg.createanaccount();
		reg.emptyValidationOfEmail("Testing", "Raman");
		logger.info("verified blank text box validation");
		ExtentReportManager.getTest().pass("Blank text box validation");
		logger.info("Login test passed.");
	}

	@Test(retryAnalyzer = RetryAnalyzer.class, enabled = false)
	public void PasswordValidationTest() {
		logger.info("Starting test case ...");
		ExtentReportManager.createTest("TS_Reg_10");
		a_registerPage reg = new a_registerPage(driver);
		reg.createanaccount();
		reg.testPasswordValidation("Testing", "Raman", "sivaramank@gmail.com", "Test@1234", "Test@1234", true);
		logger.info("verified password validation");
		ExtentReportManager.getTest().pass("password validation");
		logger.info("Login test passed.");
	}

	@Test(retryAnalyzer = RetryAnalyzer.class, enabled = false)
	public void GetListOfWebElement() {
		logger.info("Starting test case ...");
		ExtentReportManager.createTest("TS_Reg_11");
		b_loginPage login = new b_loginPage(driver);
		login.validCrdential("sivaramank@rajasri.net", "18Rajasri@");
		driver.findElement(By.xpath("//span[text()=\"Projects\"]")).click();
		List<WebElement> elements = driver.findElements(By.xpath("//section[@class=\"section\"]"));
		for (WebElement element : elements) {
			System.out.println(element.getText());
		}
		logger.info("Get list of element");
		ExtentReportManager.getTest().pass("Try to getting list of webElement");
		logger.info("Get list of webElement fetched successfully");
	}
}
