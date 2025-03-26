package tests;

import java.util.Map;

import org.testng.annotations.Test;

import base.BaseTest;
import pages.b_loginPage;
import pages.h_userPage;
import utils.ExcelReader;
import utils.ExtentReportManager;
import utils.RetryAnalyzer;

public class h_usersPageTest extends BaseTest {
	
	private static final String EXCEL_PATH = "src\\test\\resources\\TestData.xlsx";
	 private static final String SHEET_NAME = "Users";

	@Test(retryAnalyzer = RetryAnalyzer.class, enabled = true)
	public void navigateAddUsersPage() throws InterruptedException {

		logger.info("Starting Login Test...");
		ExtentReportManager.createTest("TS_DC_01");
		
		String testCaseName = "navigateAddUsers";
		
		Map<String, String> testData = ExcelReader.getTestCaseData(EXCEL_PATH, SHEET_NAME, testCaseName);
		String username = testData.get("Username");
		String password = testData.get("Password");
		String email = testData.get("Email");
		
		b_loginPage loginPage = new b_loginPage(driver);
		h_userPage userPage = new h_userPage(driver);
		loginPage.validCrdential(username, password);
		try {
			userPage.navigateToAddUsers(email);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Thread.sleep(5000);
		ExtentReportManager.getTest().fail("Successfully!! naviagetd to the user page and not (read) added name");
		logger.info("test failed.");
	}

	@Test(retryAnalyzer = RetryAnalyzer.class, enabled = true)
	public void getTableRecord() throws InterruptedException {
		logger.info("Starting Login Test...");
		ExtentReportManager.createTest("TS_DC_02");
		
		String testCaseName = "fetchTableRecord";
		
		Map<String, String> testData = ExcelReader.getTestCaseData(EXCEL_PATH, SHEET_NAME, testCaseName);
		String username = testData.get("Username");
		String password = testData.get("Password");
		
		b_loginPage loginPage = new b_loginPage(driver);
		h_userPage userPage = new h_userPage(driver);
		loginPage.validCrdential(username, password);

		userPage.printAllTableRecords();
		Thread.sleep(5000);
		ExtentReportManager.getTest().pass("Successfully fetched all table record listed in console");
		logger.info("Excecution is completed");
	}
}
