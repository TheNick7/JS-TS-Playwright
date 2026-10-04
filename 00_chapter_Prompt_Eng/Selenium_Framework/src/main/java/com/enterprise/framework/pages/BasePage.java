package com.enterprise.framework.pages;

import com.aventstack.extentreports.Status;
import com.enterprise.framework.driver.DriverManager;
import com.enterprise.framework.reports.ExtentManager;
import com.enterprise.framework.utils.Log;
import com.enterprise.framework.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * BasePage encapsulates all low-level WebDriver interactions.
 * Every Page Object inherits from BasePage, ensuring standardized logging,
 * automatic synchronization, and error handling.
 */
public abstract class BasePage {

    protected WebDriver getDriver() {
        return DriverManager.getDriver();
    }

    /**
     * Navigates to a specified URL.
     */
    public void navigateTo(String url) {
        getDriver().get(url);
        Log.info("Navigated to URL: " + url);
        if (ExtentManager.getTest() != null) {
            ExtentManager.getTest().log(Status.INFO, "Navigated to URL: <b>" + url + "</b>");
        }
    }

    /**
     * Clicks on an element after ensuring it is clickable.
     */
    protected void click(By locator, String elementName) {
        WebElement element = WaitUtils.waitForClickable(locator);
        highlightElement(element);
        element.click();
        Log.info("Clicked on element: " + elementName);
        if (ExtentManager.getTest() != null) {
            ExtentManager.getTest().log(Status.PASS, "Clicked on: <b>" + elementName + "</b>");
        }
    }

    /**
     * Enters text into an input field after ensuring visibility.
     */
    protected void sendKeys(By locator, String value, String elementName) {
        WebElement element = WaitUtils.waitForVisibility(locator);
        highlightElement(element);
        element.clear();
        element.sendKeys(value);
        Log.info("Entered '" + value + "' into: " + elementName);
        if (ExtentManager.getTest() != null) {
            ExtentManager.getTest().log(Status.PASS, "Entered text into: <b>" + elementName + "</b>");
        }
    }

    /**
     * Enters masked text (for sensitive inputs like passwords).
     */
    protected void sendKeysMasked(By locator, String value, String elementName) {
        WebElement element = WaitUtils.waitForVisibility(locator);
        highlightElement(element);
        element.clear();
        element.sendKeys(value);
        Log.info("Entered secret value into: " + elementName);
        if (ExtentManager.getTest() != null) {
            ExtentManager.getTest().log(Status.PASS, "Entered value into password field: <b>" + elementName + "</b>");
        }
    }

    /**
     * Retrieves visible text from an element.
     */
    protected String getText(By locator, String elementName) {
        WebElement element = WaitUtils.waitForVisibility(locator);
        highlightElement(element);
        String text = element.getText().trim();
        Log.info("Retrieved text '" + text + "' from: " + elementName);
        return text;
    }

    /**
     * Checks if element is displayed.
     */
    protected boolean isDisplayed(By locator, String elementName) {
        try {
            WebElement element = WaitUtils.waitForVisibility(locator, 5);
            boolean displayed = element.isDisplayed();
            Log.info("Element " + elementName + " displayed status: " + displayed);
            return displayed;
        } catch (Exception e) {
            Log.warn("Element " + elementName + " is not displayed: " + e.getMessage());
            return false;
        }
    }

    /**
     * Performs a JavaScript click when standard WebDriver clicks are intercepted.
     */
    protected void jsClick(By locator, String elementName) {
        WebElement element = WaitUtils.waitForPresence(locator);
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].click();", element);
        Log.info("JavaScript clicked on element: " + elementName);
        if (ExtentManager.getTest() != null) {
            ExtentManager.getTest().log(Status.PASS, "JS clicked on: <b>" + elementName + "</b>");
        }
    }

    /**
     * Scrolls the element into the current viewport.
     */
    protected void scrollToElement(By locator) {
        WebElement element = WaitUtils.waitForPresence(locator);
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
    }

    /**
     * Highlights an element briefly with a border for visual traceability in demos/debug sessions.
     */
    protected void highlightElement(WebElement element) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) getDriver();
            js.executeScript("arguments[0].style.border='2px solid #00c853'", element);
        } catch (Exception ignored) {
            // Ignore highlighting errors
        }
    }

    public String getPageTitle() {
        String title = getDriver().getTitle();
        Log.info("Current Page Title: " + title);
        return title;
    }

    public String getCurrentUrl() {
        String url = getDriver().getCurrentUrl();
        Log.info("Current URL: " + url);
        return url;
    }

    protected List<WebElement> findElements(By locator) {
        return getDriver().findElements(locator);
    }
}
