package com.enterprise.framework.utils;

import com.enterprise.framework.constants.FrameworkConstants;
import com.enterprise.framework.driver.DriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Screenshot utility supporting both Base64 and physical file persistence.
 */
public final class ScreenshotUtils {

    private ScreenshotUtils() {
        // Prevent instantiation
    }

    /**
     * Captures a Base64 string representation of the current browser viewport.
     * Ideal for embedding screenshots directly into HTML reports without relative path issues.
     *
     * @return Base64 image string
     */
    public static String getBase64Image() {
        if (DriverManager.getDriver() != null) {
            return ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BASE64);
        }
        return "";
    }

    /**
     * Captures and writes a screenshot to the reports/screenshots directory.
     *
     * @param testName Name of the failed test
     * @return Absolute file path to saved screenshot
     */
    public static String captureScreenshotFile(String testName) {
        if (DriverManager.getDriver() == null) {
            return "";
        }

        File srcFile = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.FILE);
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String destPath = FrameworkConstants.SCREENSHOT_DIR + testName + "_" + timestamp + ".png";
        File destFile = new File(destPath);

        try {
            FileUtils.copyFile(srcFile, destFile);
            return destPath;
        } catch (IOException e) {
            Log.error("Failed to save screenshot file to " + destPath, e);
            return "";
        }
    }
}
