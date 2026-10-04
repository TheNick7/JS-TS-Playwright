package com.enterprise.framework.pages;

import org.openqa.selenium.By;

/**
 * Page Object representing the Enterprise CRM Authentication / Login page.
 */
public class LoginPage extends BasePage {

    // Locators
    private final By usernameField = By.id("username");
    private final By passwordField = By.id("password");
    private final By loginButton = By.id("Login");
    private final By rememberMeCheckbox = By.id("rememberUn");
    private final By errorMessageBanner = By.id("error");
    private final By forgotPasswordLink = By.id("forgot_password_link");

    /**
     * Enters username.
     */
    public LoginPage enterUsername(String username) {
        sendKeys(usernameField, username, "Username Input Field");
        return this;
    }

    /**
     * Enters password.
     */
    public LoginPage enterPassword(String password) {
        sendKeysMasked(passwordField, password, "Password Input Field");
        return this;
    }

    /**
     * Checks Remember Me option.
     */
    public LoginPage checkRememberMe() {
        click(rememberMeCheckbox, "Remember Me Checkbox");
        return this;
    }

    /**
     * Clicks the login button expecting success and transition to DashboardPage.
     */
    public DashboardPage clickLoginSuccess() {
        click(loginButton, "Login Button");
        return new DashboardPage();
    }

    /**
     * Clicks login button expecting failure/validation error.
     */
    public LoginPage clickLoginExpectingFailure() {
        click(loginButton, "Login Button");
        return this;
    }

    /**
     * Convenience method to perform complete login flow.
     */
    public DashboardPage loginAs(String username, String password) {
        return enterUsername(username)
                .enterPassword(password)
                .clickLoginSuccess();
    }

    /**
     * Retrieves visible error message on login failure.
     */
    public String getErrorMessage() {
        return getText(errorMessageBanner, "Login Error Banner");
    }

    public boolean isLoginButtonDisplayed() {
        return isDisplayed(loginButton, "Login Button");
    }

    public boolean isErrorMessageDisplayed() {
        return isDisplayed(errorMessageBanner, "Error Message Banner");
    }
}
