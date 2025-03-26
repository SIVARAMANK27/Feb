package tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.b_loginPage;
import pages.e_defectCategory;
import utils.ExcelReader;
import utils.ExtentReportManager;
import utils.RetryAnalyzer;

public class e_defectCategoryTest extends BaseTest{
	
	 private static final String EXCEL_PATH = "src\\test\\resources\\TestData.xlsx";
	 private static final String SHEET_NAME = "DefectCategory";
	
	@Test (retryAnalyzer = RetryAnalyzer.class, enabled=true)
	public void addCategoryName()  {
		logger.info("Starting Login Test...");
		ExtentReportManager.createTest("TS_DC_01");
		String testCaseName = "addCategoryName";
		
		Map<String, String> testData = ExcelReader.getTestCaseData(EXCEL_PATH, SHEET_NAME, testCaseName);
		String username = testData.get("Username");
		String password = testData.get("Password");
		
		b_loginPage page=new b_loginPage(driver);
		page.validCrdential(username, password);
		logger.info("Entered user name and password.");
		driver.manage().window().maximize();
		e_defectCategory defectCategory=new e_defectCategory(driver);
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String CategoryName = testData.get("CategoryName");
		defectCategory.addDefectCategotyName(CategoryName);
		
		ExtentReportManager.getTest().pass("Category name added successfully!! --> "+ CategoryName);
		logger.info("Successfully added defect category page");
	}
	
	@Test(retryAnalyzer = RetryAnalyzer.class, dependsOnMethods="addCategoryName", enabled=true)
	public void searchwithEditTableRecord() throws InterruptedException {

			logger.info("Starting Login Test...");
			ExtentReportManager.createTest("TS_DC_02");
			
			String testCaseName = "EditTableRecord";
			
			Map<String, String> testData = ExcelReader.getTestCaseData(EXCEL_PATH, SHEET_NAME, testCaseName);
			String username = testData.get("Username");
			String password = testData.get("Password");
			String search = testData.get("Search");
			String update = testData.get("UpdateData");
			
			b_loginPage page=new b_loginPage(driver);
			page.validCrdential(username, password);
			logger.info("Entered user name and password.");
			
			e_defectCategory defectCategory=new e_defectCategory(driver);
			defectCategory.searchEditRecordFromTable(search, update);
		
		
		ExtentReportManager.getTest().pass("Category name edit/updated successfully!! --> "+ " edited data --> " + search + " --> " + update);
		logger.info("search and edit the record from table");
	}
	
	@Test(retryAnalyzer = RetryAnalyzer.class, dependsOnMethods="searchwithEditTableRecord", enabled=true)
	public void searchWithDeleteTableRecord() throws InterruptedException {
		
			logger.info("Starting Login Test...");
			ExtentReportManager.createTest("TS_DC_03");
			String testCaseName = "deleteTableRecord";
			Map<String, String> testData = ExcelReader.getTestCaseData(EXCEL_PATH, SHEET_NAME, testCaseName);
			String username = testData.get("Username");
			String password = testData.get("Password");
			String search = testData.get("Search");
			
			b_loginPage page=new b_loginPage(driver);
			page.validCrdential(username,password);
			logger.info("Entered user name and password.");
			
			e_defectCategory defectCategory=new e_defectCategory(driver);
			defectCategory.searchWithTableDeleteRecord(search);
		
		ExtentReportManager.getTest().pass("defect category name deleted successfully!! --> " + search);
		logger.info("search and deleted the record from table");
	}
	

	@Test (retryAnalyzer = RetryAnalyzer.class, enabled=false)
	public void categoryBlankvalidation() {
	try {
		logger.info("Starting Login Test...");
		ExtentReportManager.createTest("TS_DC_02");
		
		b_loginPage page=new b_loginPage(driver);
		page.validCrdential("sivaramank@rajasri.net", "18Rajasri@");
		logger.info("Entered user name and password.");
		
		e_defectCategory defectCategory=new e_defectCategory(driver);
		String expectedResult=defectCategory.blankValidation_popup();
		driver.getWindowHandle();
		Assert.assertEquals(expectedResult, "Please enter a name for category!","Content mismatch");
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	ExtentReportManager.getTest().pass("test passed");
	logger.info("search and deleted the record from table");
	}
	
	@Test(retryAnalyzer = RetryAnalyzer.class, enabled=false)
	public void editCategoryName() {
	
		try {
			logger.info("Starting Login Test...");
			ExtentReportManager.createTest("TS_DC_02");
			
			b_loginPage page=new b_loginPage(driver);
			page.validCrdential("sivaramank@rajasri.net", "18Rajasri@");
			logger.info("Entered user name and password.");
			
			e_defectCategory defectCategory=new e_defectCategory(driver);
			defectCategory.editCategoryName();
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
			ExtentReportManager.createTest("TS_DC_02");
			
			b_loginPage page=new b_loginPage(driver);
			page.validCrdential("sivaramank@rajasri.net", "18Rajasri@");
			logger.info("Entered user name and password.");
			
			e_defectCategory defectCategory=new e_defectCategory(driver);
			defectCategory.deleteRecordFromTable();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ExtentReportManager.getTest().pass("test passed");
		logger.info("deleted the record from table");
	}

}