package utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;

public class RetryAnalyzer implements IRetryAnalyzer {

	private int retryCount = 0;
	private static final int MAX_RETRY_COUNT = 1;

	@Override
	public boolean retry(ITestResult result) {

		if (retryCount < MAX_RETRY_COUNT) {
			retryCount++;
			ExtentReportManager.getTest().log(Status.WARNING,
					"Retrying test " + result.getName() + " for the " + retryCount + " time.");
			return true; // Retry the test
		}
		return false; // Stop retrying
	}

}
