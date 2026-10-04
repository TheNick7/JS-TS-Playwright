package com.enterprise.framework.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.enterprise.framework.config.ConfigReader;
import com.enterprise.framework.constants.FrameworkConstants;

import java.io.File;
import java.util.Objects;

/**
 * Thread-safe ExtentReports 5 Manager.
 * Orchestrates HTML report creation, thread-bound test nodes, and system metadata.
 */
public final class ExtentManager {

    private static ExtentReports extentReports;
    private static final ThreadLocal<ExtentTest> EXTENT_TEST = new ThreadLocal<>();

    private ExtentManager() {
        // Prevent instantiation
    }

    /**
     * Initializes the ExtentReports instance if not already initialized.
     */
    public static synchronized void initReports() {
        if (Objects.isNull(extentReports)) {
            extentReports = new ExtentReports();

            // Ensure reports directory exists
            File reportDir = new File(FrameworkConstants.EXTENT_REPORT_DIR);
            if (!reportDir.exists()) {
                reportDir.mkdirs();
            }

            String reportPath = FrameworkConstants.getExtentReportFilePath();
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);

            sparkReporter.config().setTheme(Theme.STANDARD);
            sparkReporter.config().setDocumentTitle("Enterprise CRM Automation Execution Report");
            sparkReporter.config().setReportName("Selenium 4 + TestNG Regression Results");
            sparkReporter.config().setTimeStampFormat("MMM dd, yyyy HH:mm:ss");
            sparkReporter.config().setEncoding("UTF-8");

            extentReports.attachReporter(sparkReporter);
            extentReports.setSystemInfo("Organization", "Enterprise QA Labs");
            extentReports.setSystemInfo("Environment", ConfigReader.get("env", "QA").toUpperCase());
            extentReports.setSystemInfo("Browser", ConfigReader.get("browser", "Chrome"));
            extentReports.setSystemInfo("OS", System.getProperty("os.name"));
            extentReports.setSystemInfo("Java Version", System.getProperty("java.version"));
            extentReports.setSystemInfo("User", System.getProperty("user.name"));
        }
    }

    /**
     * Flushes the ExtentReports instance to disk.
     */
    public static synchronized void flushReports() {
        if (Objects.nonNull(extentReports)) {
            extentReports.flush();
        }
    }

    /**
     * Creates a new ExtentTest node and attaches it to the current thread.
     *
     * @param testCaseName Name of test case
     * @param description  Brief test description
     */
    public static void createTest(String testCaseName, String description) {
        ExtentTest test = extentReports.createTest(testCaseName, description);
        EXTENT_TEST.set(test);
    }

    public static ExtentTest getTest() {
        return EXTENT_TEST.get();
    }

    public static void unloadTest() {
        EXTENT_TEST.remove();
    }
}
