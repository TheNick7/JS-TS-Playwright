package com.enterprise.framework.listeners;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.enterprise.framework.reports.ExtentManager;
import com.enterprise.framework.utils.Log;
import com.enterprise.framework.utils.ScreenshotUtils;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * Enterprise TestNG Test Listener.
 * Connects test execution lifecycle events directly to ExtentReports 5 and Log4j2.
 */
public class TestListener implements ITestListener {

    @Override
    public void onStart(ITestContext context) {
        Log.info("Starting Test Suite: " + context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        Log.info("Completed Test Suite: " + context.getName());
    }

    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        String description = result.getMethod().getDescription();
        if (description == null || description.isEmpty()) {
            description = testName;
        }

        ExtentManager.createTest(testName, description);

        // Tag test groups/categories if present
        String[] groups = result.getMethod().getGroups();
        if (groups != null && groups.length > 0) {
            for (String group : groups) {
                ExtentManager.getTest().assignCategory(group);
            }
        }

        Log.info("Started Test: [" + testName + "] - " + description);
        ExtentManager.getTest().log(Status.INFO, "Execution started for: " + testName);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        Log.info("Test PASSED: [" + testName + "]");
        if (ExtentManager.getTest() != null) {
            ExtentManager.getTest().log(Status.PASS, "Test passed successfully.");
            ExtentManager.unloadTest();
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        Throwable throwable = result.getThrowable();
        Log.error("Test FAILED: [" + testName + "]", throwable);

        if (ExtentManager.getTest() != null) {
            // Capture base64 screenshot for instant report rendering
            String base64Image = ScreenshotUtils.getBase64Image();
            if (base64Image != null && !base64Image.isEmpty()) {
                ExtentManager.getTest().fail("<b>Failure Screenshot:</b>",
                        MediaEntityBuilder.createScreenCaptureFromBase64String(base64Image).build());
            }

            if (throwable != null) {
                ExtentManager.getTest().log(Status.FAIL, throwable);
            }
            ExtentManager.unloadTest();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        Log.warn("Test SKIPPED: [" + testName + "]");
        if (ExtentManager.getTest() != null) {
            ExtentManager.getTest().log(Status.SKIP, "Test skipped: " + result.getThrowable());
            ExtentManager.unloadTest();
        }
    }
}
