package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {
	  private static ExtentReports extent;
	    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

	    public static ExtentReports getInstance() {
	        if (extent == null) {
	            ExtentSparkReporter htmlReporter = new ExtentSparkReporter("C:\\Users\\admin\\eclipse-workspace\\com.dot.app\\reports\\extent-report.html");
	            extent = new ExtentReports();
	            extent.attachReporter(htmlReporter);
	        }
	        return extent;
	    }

	    public static ExtentTest createTest(String testName) {
	        ExtentTest test = getInstance().createTest(testName);
	        extentTest.set(test);
	        return test;
	    }

	    public static ExtentTest getTest() {
	        return extentTest.get();
	    }

	    public static void flushReport() {
	        if (extent != null) {
	            extent.flush();
	        }
	    }
}
