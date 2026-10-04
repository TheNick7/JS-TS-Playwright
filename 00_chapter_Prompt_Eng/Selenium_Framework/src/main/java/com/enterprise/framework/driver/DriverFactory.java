package com.enterprise.framework.driver;

import com.enterprise.framework.constants.FrameworkConstants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;

import java.time.Duration;

/**
 * Factory class responsible for initializing WebDriver instances based on browser type and configuration.
 */
public final class DriverFactory {

    private DriverFactory() {
        // Prevent instantiation
    }

    /**
     * Creates and configures a WebDriver instance.
     *
     * @param browserName Name of the browser (chrome, firefox, edge, safari)
     * @param isHeadless  Whether to run in headless mode
     * @return Fully configured WebDriver instance
     */
    public static WebDriver createDriver(String browserName, boolean isHeadless) {
        WebDriver driver;
        BrowserType browserType;

        try {
            browserType = BrowserType.valueOf(browserName.trim().toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            browserType = BrowserType.CHROME;
        }

        switch (browserType) {
            case FIREFOX:
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                if (isHeadless) {
                    firefoxOptions.addArguments("-headless");
                }
                firefoxOptions.addArguments("--width=1920");
                firefoxOptions.addArguments("--height=1080");
                driver = new FirefoxDriver(firefoxOptions);
                break;

            case EDGE:
                EdgeOptions edgeOptions = new EdgeOptions();
                if (isHeadless) {
                    edgeOptions.addArguments("--headless=new");
                }
                edgeOptions.addArguments("--start-maximized");
                edgeOptions.addArguments("--disable-notifications");
                driver = new EdgeDriver(edgeOptions);
                break;

            case SAFARI:
                driver = new SafariDriver();
                break;

            case CHROME:
            default:
                ChromeOptions chromeOptions = new ChromeOptions();
                if (isHeadless) {
                    chromeOptions.addArguments("--headless=new");
                }
                chromeOptions.addArguments("--start-maximized");
                chromeOptions.addArguments("--disable-notifications");
                chromeOptions.addArguments("--disable-popup-blocking");
                chromeOptions.addArguments("--disable-dev-shm-usage");
                chromeOptions.addArguments("--no-sandbox");
                chromeOptions.addArguments("--remote-allow-origins=*");
                chromeOptions.addArguments("--window-size=1920,1080");
                driver = new ChromeDriver(chromeOptions);
                break;
        }

        // Configure timeouts and window
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(FrameworkConstants.PAGE_LOAD_TIMEOUT));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(FrameworkConstants.PAGE_LOAD_TIMEOUT));
        driver.manage().window().maximize();

        return driver;
    }
}
