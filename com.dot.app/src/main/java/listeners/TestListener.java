package listeners;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;

import utils.ExtentReportManager;

public class TestListener implements ITestListener {

	@Override
	public void onTestFailure(ITestResult result) {
		// Log the failure in ExtentReports
		ExtentReportManager.getTest().log(Status.FAIL, "Test Failed: " + result.getThrowable().getMessage());

		// Capture screenshot on failure
		WebDriver driver = (WebDriver) result.getTestContext().getAttribute("driver");
		if (driver != null) {
			String screenshotPath = captureScreenshot(driver, result.getName());
			// Attach screenshot to ExtentReport
			ExtentReportManager.getTest().addScreenCaptureFromPath(screenshotPath);
		}
	}

	private String captureScreenshot(WebDriver driver, String testName) {
		String screenshotDir = "D:\\Git\\Feb\\com.dot.app\\screenshot\\";
		String screenshotPath = screenshotDir + testName + "_failure.png";

		// Create the screenshots directory if it doesn't exist
		try {
			Files.createDirectories(Paths.get(screenshotDir));
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Take screenshot and save it to the specified path
		File screenshotFile = ((org.openqa.selenium.TakesScreenshot) driver)
				.getScreenshotAs(org.openqa.selenium.OutputType.FILE);
		try {
			Files.copy(screenshotFile.toPath(), Paths.get(screenshotPath));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return screenshotPath;
	}
	
	public void onTestSuccess(ITestResult result) {
		// Log the failure in ExtentReports
		ExtentReportManager.getTest().log(Status.PASS, "Test Passed ");

		// Capture screenshot on failure
		WebDriver driver = (WebDriver) result.getTestContext().getAttribute("driver");
		if (driver != null) {
			String screenshotPath = captureScreenshot(driver, result.getName());
			// Attach screenshot to ExtentReport
			ExtentReportManager.getTest().addScreenCaptureFromPath(screenshotPath);
		}
	}
}
