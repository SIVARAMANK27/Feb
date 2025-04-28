package tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import base.BaseTest;
import pages.b_loginPage;
import pages.d_projectPhase;
import utils.CSVTestListener;
import utils.ExcelReader;
import utils.ExtentReportManager;
import utils.RetryAnalyzer;

@Listeners(CSVTestListener.class)
public class d_projectPhaseTest extends BaseTest {

	 private static final String EXCEL_PATH = "src\\test\\resources\\TestData.xlsx";
	 private static final String SHEET_NAME = "ProjectPhase";
	 
	@Test(retryAnalyzer = RetryAnalyzer.class, enabled = true)
	public void AddProjectPhaseName() throws InterruptedException {
		logger.info("Starting Login Test...");
		ExtentReportManager.createTest("TS_PH_01");
		String testCaseName = "ValidProjectPhaseName";
		  
		Map<String, String> testData = ExcelReader.getTestCaseData(EXCEL_PATH, SHEET_NAME, testCaseName);
		String username = testData.get("Username");
		String password = testData.get("Password");
		String addPhase = testData.get("AddPhaseName");
		  
		driver.manage().window().maximize();
		b_loginPage loginPage = new b_loginPage(driver);
		loginPage.validCrdential(username, password);
		d_projectPhase projectPhase = new d_projectPhase(driver);
		Thread.sleep(5000);

		// Get data from Excel
		projectPhase.addProjectPhaseName(addPhase);
		String expectedResult=driver.getCurrentUrl();
		Assert.assertEquals("http://106.51.127.87:3030/dot/phase.html", expectedResult, "The current url is mismatched");
		ExtentReportManager.getTest().pass("Successfully!! added project phase name --> "+ addPhase);
		logger.info("added defect category page");
	}

	@Test(retryAnalyzer = RetryAnalyzer.class, dependsOnMethods="AddProjectPhaseName", enabled = true)
	public void searchwithEditTableRecord() throws InterruptedException {
	
			logger.info("Starting Login Test...");
			ExtentReportManager.createTest("TS_PH_02");
			
			String testCaseName = "EditTableRecord";
			  
			Map<String, String> testData = ExcelReader.getTestCaseData(EXCEL_PATH, SHEET_NAME, testCaseName);
			String username = testData.get("Username");
			String password = testData.get("Password");
			String search = testData.get("Search");
			String update = testData.get("UpdateData");
			
			b_loginPage loginPage = new b_loginPage(driver);
			loginPage.validCrdential(username, password);
			d_projectPhase projectPhase = new d_projectPhase(driver);
			projectPhase.searchEditRecordFromTable(search, update);
			
		
			ExtentReportManager.getTest().pass("Successfully! edited the project phase name --> "+ search + " --> updated to --> "+ update);
			logger.info("search and edit the record from table");
	}

	@Test(retryAnalyzer = RetryAnalyzer.class, dependsOnMethods="searchwithEditTableRecord", enabled = true)
	public void searchWithDeleteTableRecord() throws InterruptedException {
		
			logger.info("Starting Login Test...");
			ExtentReportManager.createTest("TS_PH_03");
			
			String testCaseName = "DeleteTableRecord";
			Map<String, String> testData = ExcelReader.getTestCaseData(EXCEL_PATH, SHEET_NAME, testCaseName);
			
			String username = testData.get("Username");
			String password = testData.get("Password");
			String search = testData.get("Search");
			
			b_loginPage loginPage = new b_loginPage(driver);
			loginPage.validCrdential(username, password);

			d_projectPhase projectPhase = new d_projectPhase(driver);
			projectPhase.searchWithTableDeleteRecord(search);

			ExtentReportManager.getTest().pass("Successfully! deleted the project phase name --> "+ search);
			logger.info("search and deleted the record from table");
	}

	@Test(retryAnalyzer = RetryAnalyzer.class, enabled = false)
	public void editProjectPhaseName() {

		try {
			logger.info("Starting Login Test...");
			ExtentReportManager.createTest("TS_LO_01");
			d_projectPhase projectPhase = new d_projectPhase(driver);
			projectPhase.editProjectPhaseName();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Successfully updated the edit field");
	}

	@Test(retryAnalyzer = RetryAnalyzer.class, enabled = false)
	public void deleteRecordFromTable() {
		try {
			logger.info("Starting Login Test...");
			ExtentReportManager.createTest("TS_LO_01");
			d_projectPhase projectPhase = new d_projectPhase(driver);
			projectPhase.deleteRecordFromTable();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Successfully deleted the record from table");
	}

	@Test(retryAnalyzer = RetryAnalyzer.class, enabled = true)
	public void verifySpecialCharacterNotAllowed() throws InterruptedException {
		logger.info("Starting Login Test...");
		ExtentReportManager.createTest("TS_DC_01");
		b_loginPage page = new b_loginPage(driver);
		String testCaseName = "verifySpecialCharacter";
		  
		Map<String, String> testData = ExcelReader.getTestCaseData(EXCEL_PATH, SHEET_NAME, testCaseName);
		String username = testData.get("Username");
		String password = testData.get("Password");
		String phaseName = testData.get("AddPhaseName");
		page.validCrdential(username, password);
		logger.info("Entered user name and password.");
		d_projectPhase projectPhase = new d_projectPhase(driver);
		projectPhase.specialCharacterNotAllowed(phaseName);
		ExtentReportManager.getTest().pass("Valid credential test passed");
		logger.info("Login test passed.");
	}
	
	@Test(retryAnalyzer = RetryAnalyzer.class, enabled = true)
	public void verifyNumericValueNotAllowed() throws InterruptedException {
		logger.info("Starting Login Test...");
		ExtentReportManager.createTest("TS_DC_01");
		b_loginPage page = new b_loginPage(driver);
		String testCaseName = "verifyNumericValue";
		
		Map<String, String> testData = ExcelReader.getTestCaseData(EXCEL_PATH, SHEET_NAME, testCaseName);
		String username = testData.get("Username");
		String password = testData.get("Password");
		String phaseName = testData.get("AddPhaseName");
		page.validCrdential(username, password);
		logger.info("Entered user name and password.");
		d_projectPhase projectPhase = new d_projectPhase(driver);
		projectPhase.numericValueNotAllowed(phaseName);
		ExtentReportManager.getTest().pass("Valid credential test passed");
		logger.info("Login test passed.");
	}

	@Test(retryAnalyzer = RetryAnalyzer.class, enabled = true)
	public void alphabitWithSpecialCharacterNotAllowed() throws InterruptedException {
		logger.info("Starting Login Test...");
		ExtentReportManager.createTest("TS_DC_01");
		b_loginPage page = new b_loginPage(driver);
		String testCaseName = "alphabitWithSpecialChar";
		  
		Map<String, String> testData = ExcelReader.getTestCaseData(EXCEL_PATH, SHEET_NAME, testCaseName);
		String username = testData.get("Username");
		String password = testData.get("Password");
		String phaseName = testData.get("AddPhaseName");
		page.validCrdential(username, password);
		
		logger.info("Entered user name and password.");
		d_projectPhase projectPhase = new d_projectPhase(driver);
		projectPhase.specialCharacterNotAllowed(phaseName);
		
		ExtentReportManager.getTest().pass("Valid credential test passed");
		logger.info("Login test passed.");
	}
	
	@Test(retryAnalyzer = RetryAnalyzer.class, enabled = true)
	public void projectPhaseBlankvalidation() throws InterruptedException {
		
		logger.info("Starting Login Test...");
		ExtentReportManager.createTest("TS_LO_01");
		
		b_loginPage page = new b_loginPage(driver);
		String testCaseName = "projectPhaseBlankvalidation";
		  
		Map<String, String> testData = ExcelReader.getTestCaseData(EXCEL_PATH, SHEET_NAME, testCaseName);
		String username = testData.get("Username");
		String password = testData.get("Password");
		String phaseName = testData.get("AddPhaseName");
		page.validCrdential(username, password);
		
		d_projectPhase projectPhase = new d_projectPhase(driver);
		projectPhase.blankValidation(phaseName);
		
		ExtentReportManager.getTest().pass("Valid credential test passed");
		logger.info("Login test passed.");
	}
	
	@Test(retryAnalyzer = RetryAnalyzer.class, enabled = true)
	public void exceedingLimitVerification() throws InterruptedException {
		
		logger.info("Starting Login Test...");
		ExtentReportManager.createTest("TS_LO_01");
		
		b_loginPage page = new b_loginPage(driver);
		String testCaseName = "exceedingLimitVerification";
		  
		Map<String, String> testData = ExcelReader.getTestCaseData(EXCEL_PATH, SHEET_NAME, testCaseName);
		String username = testData.get("Username");
		String password = testData.get("Password");
		String phaseName = testData.get("AddPhaseName");
		page.validCrdential(username, password);
		
		d_projectPhase projectPhase = new d_projectPhase(driver);
		projectPhase.ExceedingLimitVerification(phaseName);
		
		ExtentReportManager.getTest().pass("Valid credential test passed");
		logger.info("Login test passed.");
	}
	
	@Test(retryAnalyzer = RetryAnalyzer.class, enabled = true)
	public void leadingSpaceVerification() throws InterruptedException {
		
		logger.info("Starting Login Test...");
		ExtentReportManager.createTest("TS_LO_01");
		
		b_loginPage page = new b_loginPage(driver);
		String testCaseName = "leadingSpaceVerification";
		  
		Map<String, String> testData = ExcelReader.getTestCaseData(EXCEL_PATH, SHEET_NAME, testCaseName);
		String username = testData.get("Username");
		String password = testData.get("Password");
		String phaseName = testData.get("AddPhaseName");
		page.validCrdential(username, password);
		
		d_projectPhase projectPhase = new d_projectPhase(driver);
		projectPhase.leadingSpaceVerification(phaseName);
		
		ExtentReportManager.getTest().pass("Valid credential test passed");
		logger.info("Login test passed.");
	}
}
