package tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.b_loginPage;
import pages.g_defectPriority;
import utils.ExcelReader;
import utils.ExtentReportManager;
import utils.RetryAnalyzer;

public class g_defectPriorityTest extends BaseTest {

	 private static final String EXCEL_PATH = "src\\test\\resources\\TestData.xlsx";
	 private static final String SHEET_NAME = "DefectPriority";
	
	@Test (retryAnalyzer = RetryAnalyzer.class, enabled=true)
	public void addDefectPriorityName()  {
		logger.info("Starting Login Test...");
		ExtentReportManager.createTest("TS_DP_01");
		
		String testCaseName = "defectPriorityName";
		
		Map<String, String> testData = ExcelReader.getTestCaseData(EXCEL_PATH, SHEET_NAME, testCaseName);
		String username = testData.get("Username");
		String password = testData.get("Password");
		String addPriority = testData.get("addPriority");
		
		b_loginPage page=new b_loginPage(driver);
		page.validCrdential(username, password);
		logger.info("Entered user name and password.");
		driver.manage().window().maximize();
		g_defectPriority defectPriority=new g_defectPriority(driver);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		defectPriority.addDefectPriorityName(addPriority);
		ExtentReportManager.getTest().pass("Successfully!! added a defect priority --> " + addPriority);
		logger.info("added defect category page");
	}

	
	@Test(retryAnalyzer = RetryAnalyzer.class, dependsOnMethods="addDefectPriorityName", enabled=true)
	public void searchwithEditTableRecord(){
		
		logger.info("Starting Login Test...");
		ExtentReportManager.createTest("TS_DP_02");
		
		String testCaseName = "editTableRecord";
		
		Map<String, String> testData = ExcelReader.getTestCaseData(EXCEL_PATH, SHEET_NAME, testCaseName);
		String username = testData.get("Username");
		String password = testData.get("Password");
		String search = testData.get("Search");
		String update = testData.get("UpdateData");
		
		b_loginPage page=new b_loginPage(driver);
		page.validCrdential(username, password);
		logger.info("Entered user name and password.");
		
			g_defectPriority defectPriority=new g_defectPriority(driver);
			try {
				defectPriority.searchWithEditRecordFromTable(search, update);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
		ExtentReportManager.getTest().pass("Successfully edited the defect priority --> "+ search + "--> updated to --> "+ update);
		logger.info("search and edit the record from table");
	}
	
	
	@Test(retryAnalyzer = RetryAnalyzer.class, dependsOnMethods="searchwithEditTableRecord", enabled=true)
	public void searchWithDeleteTableRecord() throws InterruptedException {
	
			logger.info("Starting Login Test...");
			ExtentReportManager.createTest("TS_DP_03");
			
			String testCaseName = "deleteTableRecord";
			
			Map<String, String> testData = ExcelReader.getTestCaseData(EXCEL_PATH, SHEET_NAME, testCaseName);
			String username = testData.get("Username");
			String password = testData.get("Password");
			String search = testData.get("Search");
			
			b_loginPage page=new b_loginPage(driver);
			page.validCrdential(username, password);
			logger.info("Entered user name and password.");
			
			g_defectPriority defectPriority=new g_defectPriority(driver);
			defectPriority.searchWithDeleteRecordFromTable(search);
		
		ExtentReportManager.getTest().pass("Successfully!! deleted the defect priority --> " + search);
		logger.info("search and deleted the record from table");
	}
	
	@Test (retryAnalyzer = RetryAnalyzer.class, enabled=false)
	public void projectPhaseBlankvalidation() {
	try {
		logger.info("Starting Login Test...");
		ExtentReportManager.createTest("TS_DC_01");
		
		b_loginPage page=new b_loginPage(driver);
		page.validCrdential("sivaramank@rajasri.net", "18Rajasri@");
		logger.info("Entered user name and password.");
		
		g_defectPriority defectPriority=new g_defectPriority(driver);
		String expectedResult=defectPriority.blankValidation_popup();
		driver.getWindowHandle();
		Assert.assertEquals(expectedResult, "Please enter a name for category!","Content mismatch");
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		ExtentReportManager.getTest().pass("test passed");
		logger.info("blank validation");
	}
	
	@Test(retryAnalyzer = RetryAnalyzer.class, enabled=false)
	public void editProjectPhaseName() {
	
		try {
			logger.info("Starting Login Test...");
			ExtentReportManager.createTest("TS_DC_01");
			
			b_loginPage page=new b_loginPage(driver);
			page.validCrdential("sivaramank@rajasri.net", "18Rajasri@");
			logger.info("Entered user name and password.");
			
			g_defectPriority defectPriority=new g_defectPriority(driver);
			defectPriority.editDefectPriorityName();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ExtentReportManager.getTest().pass("test passed");
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
			
			g_defectPriority defectPriority=new g_defectPriority(driver);
			defectPriority.deleteRecordFromTable();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ExtentReportManager.getTest().pass("test passed");
		logger.info("deleted the record from table");
	}
	
	@Test(retryAnalyzer = RetryAnalyzer.class, enabled = false)
	public void verifySpecialCharacterNotAllowed() throws InterruptedException {
		logger.info("Starting Login Test...");
		ExtentReportManager.createTest("TS_DC_01");
		b_loginPage page = new b_loginPage(driver);
		String testCaseName = "verifySpecialCharacter";
		  
		Map<String, String> testData = ExcelReader.getTestCaseData(EXCEL_PATH, SHEET_NAME, testCaseName);
		String username = testData.get("Username");
		String password = testData.get("Password");
		String addPriority = testData.get("addPriority");
		page.validCrdential(username, password);
		logger.info("Entered user name and password.");
		g_defectPriority defectPriority = new g_defectPriority(driver);
		defectPriority.specialCharacterNotAllowed(addPriority);
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
		String addPriority = testData.get("addPriority");
		page.validCrdential(username, password);
		logger.info("Entered user name and password.");
		g_defectPriority defectPriority = new g_defectPriority(driver);
		defectPriority.numericValueNotAllowed(addPriority);
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
		String addPriority = testData.get("addPriority");
		page.validCrdential(username, password);
		
		logger.info("Entered user name and password.");
		g_defectPriority defectPriority = new g_defectPriority(driver);
		defectPriority.specialCharacterNotAllowed(addPriority);
		
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
		String addPriority = testData.get("addPriority");
		page.validCrdential(username, password);
		
		g_defectPriority defectPriority = new g_defectPriority(driver);
		defectPriority.blankValidation(addPriority);
		
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
		String addPriority = testData.get("addPriority");
		page.validCrdential(username, password);
		
		g_defectPriority defectPriority = new g_defectPriority(driver);
		defectPriority.ExceedingLimitVerification(addPriority);
		
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
		String addPriority = testData.get("addPriority");
		page.validCrdential(username, password);
		
		g_defectPriority defectPriority = new g_defectPriority(driver);
		defectPriority.leadingSpaceVerification(addPriority);
		
		ExtentReportManager.getTest().pass("Valid credential test passed");
		logger.info("Login test passed.");
	}
}
