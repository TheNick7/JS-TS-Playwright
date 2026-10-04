package com.enterprise.framework.constants;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Centralized constant values used across the enterprise automation framework.
 */
public final class FrameworkConstants {

    private FrameworkConstants() {
        // Prevent instantiation
    }

    public static final String USER_DIR = System.getProperty("user.dir");

    // Configuration Paths
    public static final String CONFIG_DIR = USER_DIR + File.separator + "src" + File.separator + "main" 
            + File.separator + "resources" + File.separator + "config" + File.separator;
    public static final String DEFAULT_CONFIG_FILE = CONFIG_DIR + "config.properties";

    // Reports & Screenshots
    public static final String EXTENT_REPORT_DIR = USER_DIR + File.separator + "reports" + File.separator;
    public static final String SCREENSHOT_DIR = EXTENT_REPORT_DIR + "screenshots" + File.separator;

    // Timeouts
    public static final int EXPLICIT_WAIT_TIMEOUT = 15;
    public static final int PAGE_LOAD_TIMEOUT = 30;
    public static final int POLLING_INTERVAL_MS = 500;

    // Maximum Retry Count for Flaky Tests
    public static final int MAX_RETRY_COUNT = 1;

    /**
     * Generates a unique, timestamped Extent report file path.
     */
    public static String getExtentReportFilePath() {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss"));
        return EXTENT_REPORT_DIR + "ExecutionReport_" + timestamp + ".html";
    }
}
