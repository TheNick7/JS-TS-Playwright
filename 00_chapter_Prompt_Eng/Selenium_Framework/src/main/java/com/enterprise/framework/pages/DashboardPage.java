package com.enterprise.framework.pages;

import org.openqa.selenium.By;

/**
 * Page Object representing the Enterprise CRM Dashboard / Home landing page.
 */
public class DashboardPage extends BasePage {

    // Locators
    private final By dashboardHeader = By.cssSelector(".slds-global-header, .navbar-brand, h1");
    private final By leadsTab = By.xpath("//a[contains(@href, 'Lead') or contains(text(), 'Leads')]");
    private final By accountsTab = By.xpath("//a[contains(@href, 'Account') or contains(text(), 'Accounts')]");
    private final By contactsTab = By.xpath("//a[contains(@href, 'Contact') or contains(text(), 'Contacts')]");
    private final By userProfileMenu = By.cssSelector("[data-testid='user-profile'], .user-profile-button, #userNavButton");
    private final By logoutLink = By.xpath("//a[contains(text(), 'Log Out') or contains(@href, 'logout')]");
    private final By searchGlobal = By.cssSelector("input[type='search'], input.search-input");

    /**
     * Navigates to Leads Management page.
     */
    public LeadsPage navigateToLeads() {
        click(leadsTab, "CRM Leads Tab");
        return new LeadsPage();
    }

    /**
     * Checks if dashboard header is displayed.
     */
    public boolean isDashboardHeaderDisplayed() {
        return isDisplayed(dashboardHeader, "Dashboard Header");
    }

    /**
     * Performs global search.
     */
    public DashboardPage searchGlobal(String keyword) {
        sendKeys(searchGlobal, keyword, "Global Search Input");
        return this;
    }

    /**
     * Logs out of CRM application and returns to LoginPage.
     */
    public LoginPage logout() {
        click(userProfileMenu, "User Profile Menu");
        click(logoutLink, "Logout Link");
        return new LoginPage();
    }
}
