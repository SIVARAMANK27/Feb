package listeners;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

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

        // Capture and attach screenshot
        captureAndAttachScreenshot(result, Status.FAIL);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        // Log the success in ExtentReports
        ExtentReportManager.getTest().log(Status.PASS, "Test Passed");

        // Capture and attach screenshot
        captureAndAttachScreenshot(result, Status.PASS);
    }

    private void captureAndAttachScreenshot(ITestResult result, Status status) {
        WebDriver driver = (WebDriver) result.getTestContext().getAttribute("driver");
        if (driver != null) {
            String screenshotPath = captureScreenshot(driver, result.getName());
            ExtentReportManager.getTest().log(status, "Screenshot captured: " + result.getName());
            ExtentReportManager.getTest().addScreenCaptureFromPath(screenshotPath);
        }
    }

    private String captureScreenshot(WebDriver driver, String testName) {
        String screenshotDir = System.getProperty("user.dir") + "/screenshots/";
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String screenshotPath = screenshotDir + testName + "_" + timestamp + ".png";

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
}
