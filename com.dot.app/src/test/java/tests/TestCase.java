package tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.HomePage;
import pages.LoginPage;
import utils.ExtentReportManager;
import utils.RetryAnalyzer;

public class TestCase extends BaseTest {

	private Logger logger = LogManager.getLogger(TestCase.class); // Initialize Log4j2 logger
	
	@Test(retryAnalyzer = RetryAnalyzer.class)
	public void testLogin() {
		logger.info("Starting Login Test...");
		ExtentReportManager.createTest("Login Test");

		LoginPage loginPage = new LoginPage(driver);
		loginPage.login("sivaramank@rajasri.net", "18Rajasri@");
		logger.info("Entered username and password.");

		HomePage homePage = new HomePage(driver);
		homePage.getWelcomeMessage();
		String getTitle = homePage.getTitleMessage();
		System.out.println("The page title is :"+ getTitle);
		logger.info("Retrieved welcome message");
		Assert.assertEquals(getTitle, "Project","Title mismatch");

		ExtentReportManager.getTest().pass("Login test passed");
		logger.info("Login test passed.");
	}
}
