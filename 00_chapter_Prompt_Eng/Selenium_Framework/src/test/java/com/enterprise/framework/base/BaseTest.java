package com.enterprise.framework.base;

import com.enterprise.framework.config.ConfigReader;
import com.enterprise.framework.driver.DriverFactory;
import com.enterprise.framework.driver.DriverManager;
import com.enterprise.framework.reports.ExtentManager;
import com.enterprise.framework.utils.Log;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

/**
 * BaseTest serves as the foundational test fixture for all test classes.
 * Handles driver lifecycle, suite-level reporting initialization, and environment setup.
 */
public abstract class BaseTest {

    @BeforeSuite(alwaysRun = true)
    public void setUpSuite() {
        Log.info("Initializing Test Suite & ExtentReports...");
        ExtentManager.initReports();
    }

    @BeforeMethod(alwaysRun = true)
    @Parameters({"browser", "headless"})
    public void setUpMethod(@Optional("") String xmlBrowser, @Optional("") String xmlHeadless) {
        // Resolve browser priority: XML Parameter -> CLI Property (-Dbrowser) -> Config Property -> Default (chrome)
        String browser = xmlBrowser;
        if (browser == null || browser.isEmpty()) {
            browser = ConfigReader.get("browser", "chrome");
        }

        boolean isHeadless;
        if (xmlHeadless != null && !xmlHeadless.isEmpty()) {
            isHeadless = Boolean.parseBoolean(xmlHeadless);
        } else {
            isHeadless = ConfigReader.getBoolean("headless", false);
        }

        Log.info("Starting browser session: [Browser: " + browser + ", Headless: " + isHeadless + "]");
        WebDriver driver = DriverFactory.createDriver(browser, isHeadless);
        DriverManager.setDriver(driver);

        String appUrl = ConfigReader.get("app.url", "https://login.salesforce.com");
        Log.info("Navigating to target application: " + appUrl);
        DriverManager.getDriver().get(appUrl);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDownMethod() {
        Log.info("Tearing down browser session for current thread.");
        DriverManager.quitDriver();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDownSuite() {
        Log.info("Flushing ExtentReports to disk...");
        ExtentManager.flushReports();
    }
}
