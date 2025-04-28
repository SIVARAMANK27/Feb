package tests;

import java.util.Map;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.b_loginPage;
import pages.h_userPage;
import utils.CSVTestListener;
import utils.ExcelReader;
import utils.ExtentReportManager;
import utils.RetryAnalyzer;

@Listeners(CSVTestListener.class)
public class h_usersPageTest extends BaseTest {
	
	private static final String EXCEL_PATH = "src\\test\\resources\\TestData.xlsx";
	 private static final String SHEET_NAME = "Users";

	@Test(retryAnalyzer = RetryAnalyzer.class, enabled = true)
	public void navigateAddUsersPage() throws InterruptedException {

		logger.info("Starting Login Test...");
		ExtentReportManager.createTest("TS_U_01");
		
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
		ExtentReportManager.createTest("TS_U_02");
		
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
	

	@Test(retryAnalyzer = RetryAnalyzer.class, enabled = true)
	public void emailWithoutDomainExtension() throws InterruptedException {
		logger.info("Starting Login Test...");
		ExtentReportManager.createTest("TS_DC_02");
		
		String testCaseName = "emailWithoutDomain";
		
		Map<String, String> testData = ExcelReader.getTestCaseData(EXCEL_PATH, SHEET_NAME, testCaseName);
		String username = testData.get("Username");
		String password = testData.get("Password");
		String email = testData.get("Email");
		
		b_loginPage loginPage = new b_loginPage(driver);
		loginPage.validCrdential(username, password);
		
		h_userPage userPage = new h_userPage(driver);
		userPage.emailWithoutDomainExtension(email);
		
		ExtentReportManager.getTest().pass("successfully");
		logger.info("Excecution is completed");
	}
	
	@Test(retryAnalyzer = RetryAnalyzer.class, enabled = true)
	public void testEmailWithoutAtSymbol() throws InterruptedException {
		logger.info("Starting Login Test...");
		ExtentReportManager.createTest("TS_DC_02");
		
		String testCaseName = "testEmailWithoutAtSymbol";
		
		Map<String, String> testData = ExcelReader.getTestCaseData(EXCEL_PATH, SHEET_NAME, testCaseName);
		String username = testData.get("Username");
		String password = testData.get("Password");
		String email = testData.get("Email");
		
		b_loginPage loginPage = new b_loginPage(driver);
		loginPage.validCrdential(username, password);
		
		h_userPage userPage = new h_userPage(driver);
		userPage.testEmailWithoutAtSymbol(email);
		
		ExtentReportManager.getTest().pass("successfully");
		logger.info("Excecution is completed");
	}
	
	@Test(retryAnalyzer = RetryAnalyzer.class, enabled = true)
	public void testEmailWithOnlyAtSymbol() throws InterruptedException {
		logger.info("Starting Login Test...");
		ExtentReportManager.createTest("TS_DC_02");
		
		String testCaseName = "testEmailWithOnlyAtSymbol";
		
		Map<String, String> testData = ExcelReader.getTestCaseData(EXCEL_PATH, SHEET_NAME, testCaseName);
		String username = testData.get("Username");
		String password = testData.get("Password");
		String email = testData.get("Email");
		
		b_loginPage loginPage = new b_loginPage(driver);
		loginPage.validCrdential(username, password);
		
		h_userPage userPage = new h_userPage(driver);
		userPage.testEmailWithOnlyAtSymbol(email);
		
		ExtentReportManager.getTest().pass("successfully");
		logger.info("Excecution is completed");
	}
	
	@Test(retryAnalyzer = RetryAnalyzer.class, enabled = true)
	public void testEmptyEmailField() throws InterruptedException {
		logger.info("Starting Login Test...");
		ExtentReportManager.createTest("TS_DC_02");
		
		String testCaseName = "testEmptyEmailField";
		
		Map<String, String> testData = ExcelReader.getTestCaseData(EXCEL_PATH, SHEET_NAME, testCaseName);
		String username = testData.get("Username");
		String password = testData.get("Password");
		String email = testData.get("Email");
		
		b_loginPage loginPage = new b_loginPage(driver);
		loginPage.validCrdential(username, password);
		
		h_userPage userPage = new h_userPage(driver);
		userPage.testEmptyEmailField(email);
		
		ExtentReportManager.getTest().pass("successfully");
		logger.info("Excecution is completed");
	}
	
	@Test(retryAnalyzer = RetryAnalyzer.class, enabled = true)
	public void testEmailWithSpaces() throws InterruptedException {
		logger.info("Starting Login Test...");
		ExtentReportManager.createTest("TS_DC_02");
		
		String testCaseName = "testEmailWithSpaces";
		
		Map<String, String> testData = ExcelReader.getTestCaseData(EXCEL_PATH, SHEET_NAME, testCaseName);
		String username = testData.get("Username");
		String password = testData.get("Password");
		String email = testData.get("Email");
		
		b_loginPage loginPage = new b_loginPage(driver);
		loginPage.validCrdential(username, password);
		
		h_userPage userPage = new h_userPage(driver);
		userPage.testEmailWithSpaces(email);
		
		ExtentReportManager.getTest().pass("successfully");
		logger.info("Excecution is completed");
	}
}
