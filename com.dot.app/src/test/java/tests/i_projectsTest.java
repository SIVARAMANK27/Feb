package tests;

import java.util.Map;

import org.testng.annotations.Test;

import base.BaseTest;
import pages.b_loginPage;
import pages.d_projectPhase;
import pages.i_projectsPage;
import utils.ExcelReader;
import utils.ExtentReportManager;
import utils.RetryAnalyzer;

public class i_projectsTest extends BaseTest{
	
	private static final String EXCEL_PATH = "src\\test\\resources\\TestData.xlsx";
	 private static final String SHEET_NAME = "Projects";
	 private static final String SHEET_NAME1 = "ProjectPhase";
	 
	 @Test(retryAnalyzer = RetryAnalyzer.class, enabled = true)
		public void AddProjectPhaseName() throws InterruptedException {
			logger.info("Starting Login Test...");
			ExtentReportManager.createTest("TS_PH_01");
			String testCaseName = "ValidProjectPhaseName";
			  
			Map<String, String> testData = ExcelReader.getTestCaseData(EXCEL_PATH, SHEET_NAME1, testCaseName);
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
			ExtentReportManager.getTest().pass("Successfully!! added project phase name --> "+ addPhase);
			logger.info("added defect category page");
		}
	 
	@Test(retryAnalyzer = RetryAnalyzer.class, dependsOnMethods="AddProjectPhaseName", enabled=true)
	public void addProjectDetails() throws InterruptedException {
	logger.info("Starting Login Test...");
	ExtentReportManager.createTest("TS_DC_02");
		
	String testCaseName = "addProjectDetails";
	
	Map<String, String> testData = ExcelReader.getTestCaseData(EXCEL_PATH, SHEET_NAME, testCaseName);
	String username = testData.get("Username");
	String password = testData.get("Password");
	String ProjectName = testData.get("ProjectName");
	String Description = testData.get("Description");
	String StartDate = testData.get("StartDate");
	String EndDate = testData.get("EndDate");
	
	b_loginPage loginPage=new b_loginPage(driver);
	loginPage.validCrdential(username, password);
	
	i_projectsPage projects=new i_projectsPage(driver);
		projects.addProjectDetails(ProjectName, Description, StartDate, EndDate);
	
	ExtentReportManager.getTest().pass("added a project details --> "+ ProjectName);
	logger.info("Login test passed.");
	}
	
	@Test(retryAnalyzer = RetryAnalyzer.class, dependsOnMethods="addProjectDetails", enabled=true)
	public void editProjectDetails() {
		logger.info("Starting Login Test...");
		ExtentReportManager.createTest("TS_DC_03");
		
		String testCaseName = "editProjectDetails";
		
		Map<String, String> testData = ExcelReader.getTestCaseData(EXCEL_PATH, SHEET_NAME, testCaseName);
		String username = testData.get("Username");
		String password = testData.get("Password");
		String search = testData.get("Search");
		String update = testData.get("UpdateText");
		String updateDate = testData.get("UpdateEndDate");
		
		i_projectsPage projects=new i_projectsPage(driver);
		b_loginPage loginPage=new b_loginPage(driver);
		loginPage.validCrdential(username, password);
		try {
			projects.editProjectDetails(search, update, updateDate);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ExtentReportManager.getTest().pass("Successfully!! updatation happend project name and end date --> "+ update + " --> "+ updateDate);
		logger.info("edit/updated a project details");
	}
	
	@Test(retryAnalyzer = RetryAnalyzer.class, dependsOnMethods="editProjectDetails", enabled=true)
	public void deleteProjectRecords() throws InterruptedException {
		logger.info("Starting Login Test...");
		ExtentReportManager.createTest("TS_DC_04");
		
		String testCaseName = "deleteProjectDetails";
		
		Map<String, String> testData = ExcelReader.getTestCaseData(EXCEL_PATH, SHEET_NAME, testCaseName);
		String username = testData.get("Username");
		String password = testData.get("Password");
		String search = testData.get("Search");
		
		i_projectsPage projects=new i_projectsPage(driver);
		b_loginPage loginPage=new b_loginPage(driver);
		loginPage.validCrdential(username, password);
		projects.deleteProjectRecord(search);
		
		ExtentReportManager.getTest().pass("Successfully!! project is deleted --> "+ search);
		logger.info("deleted a project details");
	}
}
