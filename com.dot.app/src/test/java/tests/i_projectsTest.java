package tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.b_loginPage;
import pages.d_projectPhase;
import pages.i_projectsPage;
import utils.CSVTestListener;
import utils.ExcelReader;
import utils.ExtentReportManager;
import utils.RetryAnalyzer;

@Listeners(CSVTestListener.class)
public class i_projectsTest extends BaseTest{
	
	private static final String EXCEL_PATH = "src\\test\\resources\\TestData.xlsx";
	 private static final String SHEET_NAME = "Projects";
	 private static final String SHEET_NAME1 = "ProjectPhase";
	 
	 @Test(retryAnalyzer = RetryAnalyzer.class, enabled = true)
		public void AddProjectPhaseName() throws InterruptedException {
			logger.info("Starting Login Test...");
			ExtentReportManager.createTest("TS_P_01");
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
			
			String expectedResult=driver.getCurrentUrl();
			Assert.assertEquals("http://106.51.127.87:3030/dot/phase.html", expectedResult, "The current url is mismatched");
			
			ExtentReportManager.getTest().pass("Successfully!! added project phase name --> "+ addPhase);
			logger.info("added defect category page");
		}
	 
	@Test(retryAnalyzer = RetryAnalyzer.class, dependsOnMethods="AddProjectPhaseName", enabled=true)
	public void addProjectDetails() throws InterruptedException {
	logger.info("Starting Login Test...");
	ExtentReportManager.createTest("TS_P_02");
		
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
		
		String expectedResult=driver.getCurrentUrl();
		Assert.assertEquals("http://106.51.127.87:3030/dot/projects.html", expectedResult, "The current url is mismatched");
	
	ExtentReportManager.getTest().pass("added a project details --> "+ ProjectName);
	logger.info("Login test passed.");
	}
	
	@Test(retryAnalyzer = RetryAnalyzer.class, dependsOnMethods="addProjectDetails", enabled=true)
	public void editProjectDetails() {
		logger.info("Starting Login Test...");
		ExtentReportManager.createTest("TS_P_03");
		
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
		ExtentReportManager.createTest("TS_P_04");
		
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
	
	@Test(retryAnalyzer = RetryAnalyzer.class, enabled=true)
	public void emptyValidationForProjectName() throws InterruptedException {
		
		logger.info("Starting Login Test...");
		ExtentReportManager.createTest("TS_DC_04");
		
		String testCaseName = "emptyValidationForProjectName";
		
		Map<String, String> testData = ExcelReader.getTestCaseData(EXCEL_PATH, SHEET_NAME, testCaseName);
		String username = testData.get("Username");
		String password = testData.get("Password");
		String ProjectName = testData.get("ProjectName");
		
		i_projectsPage projects=new i_projectsPage(driver);
		b_loginPage loginPage=new b_loginPage(driver);
		loginPage.validCrdential(username, password);
		projects.emptyValidationForProjectName(ProjectName);
		
		ExtentReportManager.getTest().pass("Successfully!! project is empty validation --> "+ ProjectName);
		logger.info("deleted a project details");
	}
	
	
	@Test(retryAnalyzer = RetryAnalyzer.class, enabled=true)
	public void spcialCharacterNotAllowed() throws InterruptedException {
		
		logger.info("Starting Login Test...");
		ExtentReportManager.createTest("TS_DC_04");
		
		String testCaseName = "spcialCharacterNotAllowed";
		
		Map<String, String> testData = ExcelReader.getTestCaseData(EXCEL_PATH, SHEET_NAME, testCaseName);
		String username = testData.get("Username");
		String password = testData.get("Password");
		String ProjectName = testData.get("ProjectName");
		
		i_projectsPage projects=new i_projectsPage(driver);
		b_loginPage loginPage=new b_loginPage(driver);
		loginPage.validCrdential(username, password);
		projects.spcialCharacterNotAllowed(ProjectName);
		
		ExtentReportManager.getTest().pass("Successfully!! project is empty validation --> "+ ProjectName);
		logger.info("deleted a project details");
	}
	
	@Test(retryAnalyzer = RetryAnalyzer.class, enabled=true)
	public void exceedingLimitVerification() throws InterruptedException {
		
		logger.info("Starting Login Test...");
		ExtentReportManager.createTest("TS_DC_04");
		
		String testCaseName = "exceedingLimitVerification";
		
		Map<String, String> testData = ExcelReader.getTestCaseData(EXCEL_PATH, SHEET_NAME, testCaseName);
		String username = testData.get("Username");
		String password = testData.get("Password");
		String ProjectName = testData.get("ProjectName");
		
		i_projectsPage projects=new i_projectsPage(driver);
		b_loginPage loginPage=new b_loginPage(driver);
		loginPage.validCrdential(username, password);
		projects.exceedingLimitVerification(ProjectName);
		
		ExtentReportManager.getTest().pass("Successfully!! project is empty validation --> "+ ProjectName);
		logger.info("deleted a project details");
	}
	
	@Test(retryAnalyzer = RetryAnalyzer.class, enabled=true)
	public void leadingSpaceVerificcation() throws InterruptedException {
		
		logger.info("Starting Login Test...");
		ExtentReportManager.createTest("TS_DC_04");
		
		String testCaseName = "leadingSpaceVerificcation";
		
		Map<String, String> testData = ExcelReader.getTestCaseData(EXCEL_PATH, SHEET_NAME, testCaseName);
		String username = testData.get("Username");
		String password = testData.get("Password");
		String ProjectName = testData.get("ProjectName");
		
		i_projectsPage projects=new i_projectsPage(driver);
		b_loginPage loginPage=new b_loginPage(driver);
		loginPage.validCrdential(username, password);
		projects.leadingSpaceVerificcation(ProjectName);
		
		ExtentReportManager.getTest().pass("Successfully!! project is empty validation --> "+ ProjectName);
		logger.info("deleted a project details");
	}
	
	@Test(retryAnalyzer = RetryAnalyzer.class, enabled=true)
	public void leadingSpaceVerificcation1() throws InterruptedException {
		
		logger.info("Starting Login Test...");
		ExtentReportManager.createTest("TS_DC_04");
		
		String testCaseName = "leadingSpaceVerificcation1";
		
		Map<String, String> testData = ExcelReader.getTestCaseData(EXCEL_PATH, SHEET_NAME, testCaseName);
		String username = testData.get("Username");
		String password = testData.get("Password");
		String ProjectName = testData.get("ProjectName");
		String Description = testData.get("Description");
		
		i_projectsPage projects=new i_projectsPage(driver);
		b_loginPage loginPage=new b_loginPage(driver);
		loginPage.validCrdential(username, password);
		projects.leadingSpaceVerificcation1(ProjectName, Description);
		Thread.sleep(3000);
		ExtentReportManager.getTest().pass("Successfully!! project is empty validation --> "+ ProjectName +" and --> " +Description);
		logger.info("deleted a project details");
	}
	
	@Test(retryAnalyzer = RetryAnalyzer.class, enabled=true)
	public void exceedingLimitVerification1() throws InterruptedException {
		
		logger.info("Starting Login Test...");
		ExtentReportManager.createTest("TS_DC_04");
		
		String testCaseName = "exceedingLimitVerification1";
		
		Map<String, String> testData = ExcelReader.getTestCaseData(EXCEL_PATH, SHEET_NAME, testCaseName);
		String username = testData.get("Username");
		String password = testData.get("Password");
		String ProjectName = testData.get("ProjectName");
		String Description = testData.get("Description");
		
		i_projectsPage projects=new i_projectsPage(driver);
		b_loginPage loginPage=new b_loginPage(driver);
		loginPage.validCrdential(username, password);
		projects.exceedingLimitVerification1(ProjectName, Description);
		Thread.sleep(3000);
		ExtentReportManager.getTest().pass("Successfully!! project is empty validation --> "+ ProjectName +" and --> " +Description);
		logger.info("deleted a project details");
	}
	
	@Test(retryAnalyzer = RetryAnalyzer.class, enabled=true)
	public void emptyValidationForProjectName1() throws InterruptedException {
		
		logger.info("Starting Login Test...");
		ExtentReportManager.createTest("TS_DC_04");
		
		String testCaseName = "emptyValidationForProjectName1";
		
		Map<String, String> testData = ExcelReader.getTestCaseData(EXCEL_PATH, SHEET_NAME, testCaseName);
		String username = testData.get("Username");
		String password = testData.get("Password");
		String ProjectName = testData.get("ProjectName");
		String Description = testData.get("Description");
		
		i_projectsPage projects=new i_projectsPage(driver);
		b_loginPage loginPage=new b_loginPage(driver);
		loginPage.validCrdential(username, password);
		projects.emptyValidationForProjectName1(ProjectName, Description);
		Thread.sleep(3000);
		ExtentReportManager.getTest().pass("Successfully!! project is empty validation --> "+ ProjectName +" and --> " +Description);
		logger.info("deleted a project details");
	}
	
	@Test(retryAnalyzer = RetryAnalyzer.class, enabled=true)
	public void verifyValidTextAreAvailable() throws InterruptedException {
	
		logger.info("Starting Login Test...");
		ExtentReportManager.createTest("TS_DC_04");
		
		String testCaseName = "verifyValidTextAreAvailable";
		
		Map<String, String> testData = ExcelReader.getTestCaseData(EXCEL_PATH, SHEET_NAME, testCaseName);
		String username = testData.get("Username");
		String password = testData.get("Password");
		String ProjectName = testData.get("ProjectName");
		String Description = testData.get("Description");
		String DropdownPhase = testData.get("DropdownPhase");
		
		i_projectsPage projects=new i_projectsPage(driver);
		b_loginPage loginPage=new b_loginPage(driver);
		loginPage.validCrdential(username, password);
		projects.verifyValidTextAreAvailable(ProjectName, Description, DropdownPhase);
		Thread.sleep(3000);
		ExtentReportManager.getTest().pass("Successfully!! project is empty validation --> "+ ProjectName +" and --> " +Description);
		logger.info("deleted a project details");
	}
	
	@Test(retryAnalyzer = RetryAnalyzer.class, enabled=true)
	public void dontSelectProjectPhase() throws InterruptedException {
		
		logger.info("Starting Login Test...");
		ExtentReportManager.createTest("TS_DC_04");
		
		String testCaseName = "dontSelectProjectPhase";
		
		Map<String, String> testData = ExcelReader.getTestCaseData(EXCEL_PATH, SHEET_NAME, testCaseName);
		String username = testData.get("Username");
		String password = testData.get("Password");
		String ProjectName = testData.get("ProjectName");
		String Description = testData.get("Description");
		
		i_projectsPage projects=new i_projectsPage(driver);
		b_loginPage loginPage=new b_loginPage(driver);
		loginPage.validCrdential(username, password);
		projects.dontSelectProjectPhase(ProjectName, Description);
		Thread.sleep(3000);
		ExtentReportManager.getTest().pass("Successfully!! project is empty validation --> "+ ProjectName +" and --> " +Description);
		logger.info("deleted a project details");
	}
	
	
	@Test(retryAnalyzer = RetryAnalyzer.class, enabled=true)
	public void verifyValidTextStatus() throws InterruptedException {
		
		logger.info("Starting Login Test...");
		ExtentReportManager.createTest("TS_DC_04");
		
		String testCaseName = "verifyValidTextStatus";
		
		Map<String, String> testData = ExcelReader.getTestCaseData(EXCEL_PATH, SHEET_NAME, testCaseName);
		String username = testData.get("Username");
		String password = testData.get("Password");
		String ProjectName = testData.get("ProjectName");
		String Description = testData.get("Description");
		String DropdownPhase = testData.get("DropdownPhase");
		String DropDownStatus = testData.get("DropDownStatus");
		
		i_projectsPage projects=new i_projectsPage(driver);
		b_loginPage loginPage=new b_loginPage(driver);
		loginPage.validCrdential(username, password);
		projects.verifyValidTextStatus(ProjectName, Description, DropdownPhase, DropDownStatus);
		Thread.sleep(3000);
		ExtentReportManager.getTest().pass("Successfully!! project validation --> "+ ProjectName +" and --> " +Description);
		logger.info("deleted a project details");
	}
	
	@Test(retryAnalyzer = RetryAnalyzer.class, enabled=true)
	public void dontSelectStatus() throws InterruptedException {
		
		logger.info("Starting Login Test...");
		ExtentReportManager.createTest("TS_DC_04");
		
		String testCaseName = "dontSelectStatus";
		
		Map<String, String> testData = ExcelReader.getTestCaseData(EXCEL_PATH, SHEET_NAME, testCaseName);
		String username = testData.get("Username");
		String password = testData.get("Password");
		String ProjectName = testData.get("ProjectName");
		String Description = testData.get("Description");
		String DropdownPhase = testData.get("DropdownPhase");
		
		i_projectsPage projects=new i_projectsPage(driver);
		b_loginPage loginPage=new b_loginPage(driver);
		loginPage.validCrdential(username, password);
		projects.dontSelectStatus(ProjectName, Description, DropdownPhase);
		Thread.sleep(3000);
		ExtentReportManager.getTest().pass("Successfully!! project validation --> "+ ProjectName +" and --> " +Description);
		logger.info("deleted a project details");
	}
	
	@Test(retryAnalyzer = RetryAnalyzer.class, enabled=true)
	public void DateValidation() throws InterruptedException {
		logger.info("Starting Login Test...");
		ExtentReportManager.createTest("TS_DC_04");
		
		String testCaseName = "dateValidation";
		
		Map<String, String> testData = ExcelReader.getTestCaseData(EXCEL_PATH, SHEET_NAME, testCaseName);
		String username = testData.get("Username");
		String password = testData.get("Password");
		String projectName = testData.get("ProjectName");
		String description = testData.get("Description");
		String startDate = testData.get("StartDate");
		String endDate = testData.get("EndDate");
		
		i_projectsPage projects=new i_projectsPage(driver);
		b_loginPage loginPage=new b_loginPage(driver);
		loginPage.validCrdential(username, password);
		projects.DateValidation(projectName, description, startDate, endDate);
		Thread.sleep(3000);
		ExtentReportManager.getTest().pass("Successfully!! project validation --> "+ projectName +" and --> " +description);
		logger.info("deleted a project details");
	}
}
