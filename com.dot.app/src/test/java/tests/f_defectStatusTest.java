package tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.b_loginPage;
import pages.f_defectStatus;
import utils.ExcelReader;
import utils.ExtentReportManager;
import utils.RetryAnalyzer;

public class f_defectStatusTest extends BaseTest{

	 private static final String EXCEL_PATH = "src\\test\\resources\\TestData.xlsx";
	 private static final String SHEET_NAME = "Defect_Status";
	 
	@Test (retryAnalyzer = RetryAnalyzer.class, enabled=true)
	public void addDefectStatusName() {
		logger.info("Starting Login Test...");
		ExtentReportManager.createTest("TS_DS_01");
		
		String testCaseName = "addDefectStatus";
		
		Map<String, String> testData = ExcelReader.getTestCaseData(EXCEL_PATH, SHEET_NAME, testCaseName);
		String username = testData.get("Username");
		String password = testData.get("Password");
		String StatusName = testData.get("StatusName");
		
		b_loginPage page=new b_loginPage(driver);
		page.validCrdential(username, password);
		logger.info("Entered user name and password.");
		driver.manage().window().maximize();
		f_defectStatus projectPhase=new f_defectStatus(driver);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		projectPhase.addDefectStatusName(StatusName);
		ExtentReportManager.getTest().pass("Successfully! defect status added --> " + StatusName);
		logger.info("added defect category page");
	}

	@Test(retryAnalyzer = RetryAnalyzer.class, dependsOnMethods="addDefectStatusName", enabled=true)
	public void searchwithEditTableRecord() throws InterruptedException {
		
		logger.info("Starting Login Test...");
		ExtentReportManager.createTest("TS_DS_02");
		
		String testCaseName = "editTableRecord";
			
			Map<String, String> testData = ExcelReader.getTestCaseData(EXCEL_PATH, SHEET_NAME, testCaseName);
			String username = testData.get("Username");
			String password = testData.get("Password");
			String search = testData.get("Search");
			String update = testData.get("Update");
			
		b_loginPage page=new b_loginPage(driver);
		page.validCrdential(username, password);
		logger.info("Entered user name and password.");
		
		f_defectStatus defectStatus=new f_defectStatus(driver);
		defectStatus.searchWithEditRecordFromTable(search, update);
		
		ExtentReportManager.getTest().pass("Successfully!! defect status is Edited --> "+ search + " --> Updated to -->"+ update );
		logger.info("search and edit the record from table");
	}

	@Test(retryAnalyzer = RetryAnalyzer.class, dependsOnMethods="searchwithEditTableRecord", enabled=true)
	public void searchWithDeleteTableRecord() throws InterruptedException {

			logger.info("Starting Login Test...");
			ExtentReportManager.createTest("TS_DS_03");
			
			String testCaseName = "deleteTableRecord";
			
			Map<String, String> testData = ExcelReader.getTestCaseData(EXCEL_PATH, SHEET_NAME, testCaseName);
			String username = testData.get("Username");
			String password = testData.get("Password");
			String search = testData.get("Search");
			
			
			b_loginPage page=new b_loginPage(driver);
			page.validCrdential(username, password);
			logger.info("Entered user name and password.");
			
			f_defectStatus projectPhase=new f_defectStatus(driver);
			projectPhase.searchWithDeleteRecordFromTable(search);
		
		ExtentReportManager.getTest().pass("Successfully!! defect status is deleted --> " + search);
		logger.info("search and deleted the record from table");
	}
	
	@Test (retryAnalyzer = RetryAnalyzer.class, enabled=false)
	public void defectStatusBlankvalidation() {
	try {
		logger.info("Starting Login Test...");
		ExtentReportManager.createTest("TS_DC_01");
		
		b_loginPage page=new b_loginPage(driver);
		page.validCrdential("sivaramank@rajasri.net", "18Rajasri@");
		logger.info("Entered user name and password.");
		
		f_defectStatus projectPhase=new f_defectStatus(driver);
		String expectedResult=projectPhase.blankValidation_popup();
		driver.getWindowHandle();
		Assert.assertEquals(expectedResult, "Please enter a name for category!","Content mismatch");
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	ExtentReportManager.getTest().pass(" test passed");
	logger.info("defect Status Blank validation");
	}
	
	@Test(retryAnalyzer = RetryAnalyzer.class, enabled=false)
	public void editDefectStatusNameField() {
	
		try {
			logger.info("Starting Login Test...");
			ExtentReportManager.createTest("TS_DC_01");
			
			b_loginPage page=new b_loginPage(driver);
			page.validCrdential("sivaramank@rajasri.net", "18Rajasri@");
			logger.info("Entered user name and password.");
			
			f_defectStatus projectPhase=new f_defectStatus(driver);
			projectPhase.editDefectStatusName();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ExtentReportManager.getTest().pass(" test passed");
		logger.info("updated the edit field");
	}
	
	@Test (retryAnalyzer = RetryAnalyzer.class, enabled=false)
	public void deleteRecordFromTable() {
		try {
			logger.info("Starting Login Test...");
			ExtentReportManager.createTest("TS_DC_01");
			
			b_loginPage page=new b_loginPage(driver);
			page.validCrdential("sivaramank@rajasri.net", "18Rajasri@");
			logger.info("Entered user name and password.");
			
			f_defectStatus projectPhase=new f_defectStatus(driver);
			projectPhase.deleteRecordFromTable();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ExtentReportManager.getTest().pass(" test passed");
		logger.info("deleted the record from table");
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
		String StatusName = testData.get("StatusName");
		page.validCrdential(username, password);
		logger.info("Entered user name and password.");
		
		f_defectStatus defectStatus = new f_defectStatus(driver);
		defectStatus.specialCharacterNotAllowed(StatusName);
		ExtentReportManager.getTest().pass("Valid credential test passed");
		logger.info("Login test passed.");
	}
	
	@Test(retryAnalyzer = RetryAnalyzer.class, enabled = false)
	public void verifyNumericValueNotAllowed() throws InterruptedException {
		logger.info("Starting Login Test...");
		ExtentReportManager.createTest("TS_DC_01");
		b_loginPage page = new b_loginPage(driver);
		String testCaseName = "verifyNumericValue";
		
		Map<String, String> testData = ExcelReader.getTestCaseData(EXCEL_PATH, SHEET_NAME, testCaseName);
		String username = testData.get("Username");
		String password = testData.get("Password");
		String StatusName = testData.get("StatusName");
		page.validCrdential(username, password);
		logger.info("Entered user name and password.");
		
		f_defectStatus defectStatus = new f_defectStatus(driver);
		defectStatus.numericValueNotAllowed(StatusName);
		ExtentReportManager.getTest().pass("Valid credential test passed");
		logger.info("Login test passed.");
	}

	@Test(retryAnalyzer = RetryAnalyzer.class, enabled = false)
	public void alphabitWithSpecialCharacterNotAllowed() throws InterruptedException {
		logger.info("Starting Login Test...");
		ExtentReportManager.createTest("TS_DC_01");
		b_loginPage page = new b_loginPage(driver);
		String testCaseName = "alphabitWithSpecialChar";
		  
		Map<String, String> testData = ExcelReader.getTestCaseData(EXCEL_PATH, SHEET_NAME, testCaseName);
		String username = testData.get("Username");
		String password = testData.get("Password");
		String StatusName = testData.get("StatusName");
		page.validCrdential(username, password);
		
		logger.info("Entered user name and password.");
		f_defectStatus defectStatus = new f_defectStatus(driver);
		defectStatus.specialCharacterNotAllowed(StatusName);
		
		ExtentReportManager.getTest().pass("Valid credential test passed");
		logger.info("Login test passed.");
	}
	
	@Test(retryAnalyzer = RetryAnalyzer.class, enabled = false)
	public void blankValidation() throws InterruptedException {
		
		logger.info("Starting Login Test...");
		ExtentReportManager.createTest("TS_LO_01");
		
		b_loginPage page = new b_loginPage(driver);
		String testCaseName = "blankValidation";
		  
		Map<String, String> testData = ExcelReader.getTestCaseData(EXCEL_PATH, SHEET_NAME, testCaseName);
		String username = testData.get("Username");
		String password = testData.get("Password");
		String StatusName = testData.get("StatusName");
		page.validCrdential(username, password);
		
		f_defectStatus defectStatus = new f_defectStatus(driver);
		defectStatus.blankValidation(StatusName);
		
		ExtentReportManager.getTest().pass("Valid credential test passed");
		logger.info("Login test passed.");
	}
	
	@Test(retryAnalyzer = RetryAnalyzer.class, enabled = false)
	public void exceedingLimitVerification() throws InterruptedException {
		
		logger.info("Starting Login Test...");
		ExtentReportManager.createTest("TS_LO_01");
		
		b_loginPage page = new b_loginPage(driver);
		String testCaseName = "exceedingLimitVerification";
		  
		Map<String, String> testData = ExcelReader.getTestCaseData(EXCEL_PATH, SHEET_NAME, testCaseName);
		String username = testData.get("Username");
		String password = testData.get("Password");
		String StatusName = testData.get("StatusName");
		page.validCrdential(username, password);
		
		f_defectStatus defectStatus = new f_defectStatus(driver);
		defectStatus.ExceedingLimitVerification(StatusName);
		
		ExtentReportManager.getTest().pass("Valid credential test passed");
		logger.info("Login test passed.");
	}
	
	@Test(retryAnalyzer = RetryAnalyzer.class, enabled = false)
	public void leadingSpaceVerification() throws InterruptedException {
		
		logger.info("Starting Login Test...");
		ExtentReportManager.createTest("TS_LO_01");
		
		b_loginPage page = new b_loginPage(driver);
		String testCaseName = "leadingSpaceVerification";
		  
		Map<String, String> testData = ExcelReader.getTestCaseData(EXCEL_PATH, SHEET_NAME, testCaseName);
		String username = testData.get("Username");
		String password = testData.get("Password");
		String StatusName = testData.get("StatusName");
		page.validCrdential(username, password);
		
		f_defectStatus defectStatus = new f_defectStatus(driver);
		defectStatus.leadingSpaceVerification(StatusName);
		
		ExtentReportManager.getTest().pass("Valid credential test passed");
		logger.info("Login test passed.");
	}
}
