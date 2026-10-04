package com.enterprise.framework.pages;

import org.openqa.selenium.By;

/**
 * Page Object representing CRM Leads Management (Creation, Searching, Verification).
 */
public class LeadsPage extends BasePage {

    // Action Locators
    private final By newLeadButton = By.xpath("//button[contains(text(), 'New') or contains(@title, 'New Lead')] | //a[@title='New']");
    private final By saveButton = By.xpath("//button[@name='SaveEdit' or contains(text(), 'Save')]");
    private final By cancelButton = By.xpath("//button[@name='CancelEdit' or contains(text(), 'Cancel')]");

    // Modal Form Locators
    private final By firstNameField = By.xpath("//input[@placeholder='First Name' or @name='firstName']");
    private final By lastNameField = By.xpath("//input[@placeholder='Last Name' or @name='lastName']");
    private final By companyField = By.xpath("//input[@name='Company' or @placeholder='Company']");
    private final By emailField = By.xpath("//input[@type='email' or @name='Email']");
    private final By phoneField = By.xpath("//input[@type='tel' or @name='Phone']");
    private final By titleField = By.xpath("//input[@name='Title' or @placeholder='Title']");

    // Feedback & Table Locators
    private final By toastSuccessMessage = By.cssSelector(".toastMessage, .slds-notify_toast, .alert-success");
    private final By searchLeadsInput = By.xpath("//input[@placeholder='Search this list...' or @type='search']");

    /**
     * Opens the New Lead modal.
     */
    public LeadsPage clickNewLead() {
        click(newLeadButton, "New Lead Button");
        return this;
    }

    public LeadsPage enterFirstName(String firstName) {
        sendKeys(firstNameField, firstName, "Lead First Name Field");
        return this;
    }

    public LeadsPage enterLastName(String lastName) {
        sendKeys(lastNameField, lastName, "Lead Last Name Field");
        return this;
    }

    public LeadsPage enterCompany(String company) {
        sendKeys(companyField, company, "Lead Company Field");
        return this;
    }

    public LeadsPage enterEmail(String email) {
        sendKeys(emailField, email, "Lead Email Field");
        return this;
    }

    public LeadsPage enterPhone(String phone) {
        sendKeys(phoneField, phone, "Lead Phone Field");
        return this;
    }

    public LeadsPage enterTitle(String title) {
        sendKeys(titleField, title, "Lead Job Title Field");
        return this;
    }

    public LeadsPage clickSave() {
        click(saveButton, "Save Lead Button");
        return this;
    }

    /**
     * Complete creation flow for a new lead using fluent chaining.
     */
    public LeadsPage createLead(String firstName, String lastName, String company, String email, String phone) {
        return clickNewLead()
                .enterFirstName(firstName)
                .enterLastName(lastName)
                .enterCompany(company)
                .enterEmail(email)
                .enterPhone(phone)
                .clickSave();
    }

    /**
     * Validates if success toast is displayed.
     */
    public boolean isSuccessToastDisplayed() {
        return isDisplayed(toastSuccessMessage, "Lead Creation Success Toast");
    }

    /**
     * Checks if a lead with specified full name is visible in the leads table/list.
     */
    public boolean isLeadPresentInTable(String leadName) {
        By leadRow = By.xpath("//table//tr[contains(., '" + leadName + "')] | //a[text()='" + leadName + "']");
        return isDisplayed(leadRow, "Lead Record: " + leadName);
    }
}
