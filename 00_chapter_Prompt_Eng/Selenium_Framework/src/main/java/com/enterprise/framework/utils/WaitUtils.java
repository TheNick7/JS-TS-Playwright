package com.enterprise.framework.utils;

import com.enterprise.framework.constants.FrameworkConstants;
import com.enterprise.framework.driver.DriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Resilient synchronization utility implementing explicit and fluent waits.
 * Replaces unreliable Thread.sleep() calls with deterministic polling.
 */
public final class WaitUtils {

    private WaitUtils() {
        // Prevent instantiation
    }

    private static WebDriverWait getWait() {
        return new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(FrameworkConstants.EXPLICIT_WAIT_TIMEOUT));
    }

    private static WebDriverWait getWait(int timeoutInSeconds) {
        return new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeoutInSeconds));
    }

    public static WebElement waitForVisibility(By locator) {
        return getWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static WebElement waitForVisibility(By locator, int timeoutInSeconds) {
        return getWait(timeoutInSeconds).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static WebElement waitForClickable(By locator) {
        return getWait().until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static WebElement waitForClickable(By locator, int timeoutInSeconds) {
        return getWait(timeoutInSeconds).until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static WebElement waitForPresence(By locator) {
        return getWait().until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public static boolean waitForInvisibility(By locator) {
        return getWait().until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public static boolean waitForTextToBePresent(By locator, String text) {
        return getWait().until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
    }

    public static boolean waitForUrlContains(String fraction) {
        return getWait().until(ExpectedConditions.urlContains(fraction));
    }

    /**
     * Fluent wait with custom polling interval and ignored NoSuchElementException / StaleElementReferenceException.
     */
    public static WebElement fluentWaitForElement(By locator, int timeoutInSeconds, int pollingIntervalMs) {
        FluentWait<WebDriver> wait = new FluentWait<>(DriverManager.getDriver())
                .withTimeout(Duration.ofSeconds(timeoutInSeconds))
                .pollingEvery(Duration.ofMillis(pollingIntervalMs))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);

        return wait.until(driver -> driver.findElement(locator));
    }
}
