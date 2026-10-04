package com.enterprise.framework.tests;

import com.enterprise.framework.base.BaseTest;
import com.enterprise.framework.config.ConfigReader;
import com.enterprise.framework.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Enterprise Test Suite validating Authentication flows in CRM Application.
 */
public class LoginTest extends BaseTest {

    @Test(priority = 1, groups = {"smoke", "regression"}, description = "Verify CRM Login Page Elements Render Correctly")
    public void testLoginPageRendering() {
        LoginPage loginPage = new LoginPage();
        Assert.assertTrue(loginPage.isLoginButtonDisplayed(), "Login button should be visible on initial load");
        Assert.assertTrue(loginPage.getPageTitle().toLowerCase().contains("login") 
                || loginPage.getPageTitle().toLowerCase().contains("salesforce"), 
                "Page title should indicate login portal");
    }

    @Test(priority = 2, groups = {"regression"}, description = "Verify CRM Login with Invalid Credentials Displays Error")
    public void testLoginWithInvalidCredentials() {
        LoginPage loginPage = new LoginPage();
        loginPage.enterUsername("invalid_user_qa@enterprise.test")
                 .enterPassword("WrongPassword123!")
                 .clickLoginExpectingFailure();

        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Error banner should be displayed for invalid credentials");
        String errorMsg = loginPage.getErrorMessage();
        Assert.assertTrue(errorMsg.toLowerCase().contains("check your username and password") 
                || errorMsg.toLowerCase().contains("error"), 
                "Error message should alert user of invalid credentials");
    }

    @Test(priority = 3, groups = {"regression"}, description = "Verify CRM Login with Empty Credentials")
    public void testLoginWithEmptyCredentials() {
        LoginPage loginPage = new LoginPage();
        loginPage.enterUsername("")
                 .enterPassword("")
                 .clickLoginExpectingFailure();

        Assert.assertTrue(loginPage.isLoginButtonDisplayed(), "User should remain on login page");
    }
}
