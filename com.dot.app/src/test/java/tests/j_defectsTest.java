package tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.b_loginPage;
import pages.g_defectPriority;
import pages.i_projectsPage;
import pages.j_defectPage;
import utils.ExcelReader;
import utils.ExtentReportManager;
import utils.RetryAnalyzer;

public class j_defectsTest extends BaseTest{
	
	 private static final String EXCEL_PATH = "src\\test\\resources\\TestData.xlsx";
	 private static final String SHEET_NAME = "Defects";
	 private static final String SHEET_NAME1 = "Projects";
	 
	@Test (retryAnalyzer = RetryAnalyzer.class, enabled=true)
	public void addDefectPriorityName() throws InterruptedException  {
			logger.info("Starting Login Test...");
			ExtentReportManager.createTest("TS_DC_01");
			
			String testCaseName = "defectPriorityName";
			
			Map<String, String> testData = ExcelReader.getTestCaseData(EXCEL_PATH, SHEET_NAME, testCaseName);
			Thread.sleep(5000);
			String username = testData.get("Username");
			String password = testData.get("Password");
			String addPriority = testData.get("addPriority");
			
			b_loginPage page=new b_loginPage(driver);
			page.validCrdential(username, password);
			logger.info("Entered user name and password.");
			driver.manage().window().maximize();
			g_defectPriority defectPriority=new g_defectPriority(driver);
			
			defectPriority.addDefectPriorityName(addPriority);
			
			String expectedResult=driver.getCurrentUrl();
			Assert.assertEquals("http://106.51.127.87:3030/dot/priority.html", expectedResult, "The current url is mismatched");
			
			ExtentReportManager.getTest().pass("Successfully!! added a defect priority --> " + addPriority);
			logger.info("added defect category page");
		}
	
	@Test(retryAnalyzer = RetryAnalyzer.class,  dependsOnMethods="addDefectPriorityName", enabled=true)
	public void addProjectDetails() throws InterruptedException {
		logger.info("Starting Login Test...");
		ExtentReportManager.createTest("TS_P_01");
			
		String testCaseName = "addProjectDetails";
		
		Map<String, String> testData = ExcelReader.getTestCaseData(EXCEL_PATH, SHEET_NAME1, testCaseName);
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
	 
	 
	@Test(retryAnalyzer = RetryAnalyzer.class, dependsOnMethods="addProjectDetails", enabled = true)
	public void addNewDefect() throws InterruptedException {
		logger.info("Starting Login Test...");
		ExtentReportManager.createTest("TS_D_02");
		
		String testCaseName = "addNewDefect";
		
		Map<String, String> testData = ExcelReader.getTestCaseData(EXCEL_PATH, SHEET_NAME, testCaseName);
		String username = testData.get("Username");
		String password = testData.get("Password");
		String description = testData.get("Description");
		String stepToReproduce = testData.get("StepToReproduce");
		String category = testData.get("Category");
		String status = testData.get("Status");
		String priority = testData.get("Priority");
		String closeDate = testData.get("Targeted Closure Date");
		String Member = testData.get("Member");
		
		b_loginPage loginPage=new b_loginPage(driver);
		loginPage.validCrdential(username, password);
		j_defectPage defectpage=new j_defectPage(driver);
		defectpage.addDefect(description, stepToReproduce, category, status, priority, Member, closeDate );

		ExtentReportManager.getTest().pass("Successfully!! defect reported to the test management tool");
		logger.info("aaded the new defects");
	}
	
	@Test(retryAnalyzer = RetryAnalyzer.class, dependsOnMethods="addNewDefect", enabled = true)
	public void searchWithEditText() throws InterruptedException {
		logger.info("Starting Login Test...");
		ExtentReportManager.createTest("TS_D_03");
		
		String testCaseName = "searchWithEditText";
		
		Map<String, String> testData = ExcelReader.getTestCaseData(EXCEL_PATH, SHEET_NAME, testCaseName);
		String username = testData.get("Username");
		String password = testData.get("Password");
		String closureDate = testData.get("Targeted Closure Date");
		String defectPriority = testData.get("defectPriority");
		String search = testData.get("Search");
		
		b_loginPage loginPage=new b_loginPage(driver);
		loginPage.validCrdential(username, password);
		j_defectPage defectpage=new j_defectPage(driver);
		defectpage.searchWithEditText(search, closureDate, defectPriority);
		
		ExtentReportManager.getTest().pass("Successfully!! edited the defect priority --> "+ closureDate +" and " + defectPriority);
		logger.info("Defect priority satus updated successfully!");
	}
	
	@Test(retryAnalyzer = RetryAnalyzer.class, dependsOnMethods="searchWithEditText", enabled = true)
	public void searchWithDeleteDefect() throws InterruptedException {
		logger.info("Starting Login Test...");
		ExtentReportManager.createTest("TS_D_04");
		
		String testCaseName = "deleteDefect";
		
		Map<String, String> testData = ExcelReader.getTestCaseData(EXCEL_PATH, SHEET_NAME, testCaseName);
		String username = testData.get("Username");
		String password = testData.get("Password");
		
		b_loginPage loginPage=new b_loginPage(driver);
		loginPage.validCrdential(username, password);
		j_defectPage defectpage=new j_defectPage(driver);
		
		String search = testData.get("Search");
		defectpage.searchWithDelete(search);
		
		ExtentReportManager.getTest().pass("Successfully!! deleted the defect priority");
		logger.info("defect is deleted successfully !!");
	}
}
