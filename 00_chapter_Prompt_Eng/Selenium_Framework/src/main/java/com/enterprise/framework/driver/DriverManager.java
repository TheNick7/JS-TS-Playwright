package com.enterprise.framework.driver;

import org.openqa.selenium.WebDriver;

/**
 * Thread-safe WebDriver storage manager using ThreadLocal.
 * Ensures isolation of browser sessions across concurrent threads in parallel test execution.
 */
public final class DriverManager {

    private DriverManager() {
        // Prevent instantiation
    }

    private static final ThreadLocal<WebDriver> DRIVER_THREAD_LOCAL = new ThreadLocal<>();

    /**
     * Retrieves the thread-bound WebDriver instance.
     *
     * @return WebDriver instance for current thread
     */
    public static WebDriver getDriver() {
        return DRIVER_THREAD_LOCAL.get();
    }

    /**
     * Binds a WebDriver instance to the current thread.
     *
     * @param driver WebDriver instance
     */
    public static void setDriver(WebDriver driver) {
        DRIVER_THREAD_LOCAL.set(driver);
    }

    /**
     * Quits and removes the thread-bound WebDriver instance to prevent memory leaks.
     */
    public static void quitDriver() {
        WebDriver driver = DRIVER_THREAD_LOCAL.get();
        if (driver != null) {
            try {
                driver.quit();
            } finally {
                DRIVER_THREAD_LOCAL.remove();
            }
        }
    }
}
