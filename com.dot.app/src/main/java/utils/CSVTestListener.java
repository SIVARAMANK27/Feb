package utils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class CSVTestListener implements ITestListener {

    @Override
    public void onStart(ITestContext context) {
        CSVReportUtil.initReport();
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        CSVReportUtil.writeResult(
            result.getTestClass().getName(),
            result.getMethod().getMethodName(),
            "PASS",
            ""
        );
    }

    @Override
    public void onTestFailure(ITestResult result) {
        CSVReportUtil.writeResult(
            result.getTestClass().getName(),
            result.getMethod().getMethodName(),
            "FAIL",
            result.getThrowable().toString()
        );
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        CSVReportUtil.writeResult(
            result.getTestClass().getName(),
            result.getMethod().getMethodName(),
            "SKIPPED",
            result.getThrowable() != null ? result.getThrowable().toString() : ""
        );
    }

    @Override
    public void onFinish(ITestContext context) {
        CSVReportUtil.closeReport();
    }
}
