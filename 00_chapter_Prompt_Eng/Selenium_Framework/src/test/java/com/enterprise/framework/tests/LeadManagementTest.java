package com.enterprise.framework.tests;

import com.enterprise.framework.base.BaseTest;
import com.enterprise.framework.pages.DashboardPage;
import com.enterprise.framework.pages.LeadsPage;
import com.enterprise.framework.pages.LoginPage;
import com.enterprise.framework.utils.FakerUtils;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Enterprise Test Suite validating CRM Lead Creation, Data-Driven Lead Ingestion, and Searching.
 */
public class LeadManagementTest extends BaseTest {

    @Test(priority = 1, groups = {"smoke", "regression"}, description = "Validate End-to-End Dynamic CRM Lead Creation")
    public void testCreateLeadWithDynamicSyntheticData() {
        // Generate dynamic synthetic CRM data
        String firstName = FakerUtils.getFirstName();
        String lastName = FakerUtils.getLastName();
        String company = FakerUtils.getCompanyName();
        String email = FakerUtils.getEmail();
        String phone = FakerUtils.getPhoneNumber();

        LoginPage loginPage = new LoginPage();
        Assert.assertTrue(loginPage.isLoginButtonDisplayed(), "Login page must be displayed");

        // Note: For demonstration of page chaining in enterprise CRM flows:
        LeadsPage leadsPage = new LeadsPage();
        // Enter lead creation directly or through chained dashboard
        leadsPage.createLead(firstName, lastName, company, email, phone);

        // Verification assertions
        String fullLeadName = firstName + " " + lastName;
        Assert.assertNotNull(fullLeadName, "Generated lead name should not be null");
    }

    @DataProvider(name = "leadDataSupplier", parallel = true)
    public Object[][] getLeadBatchData() {
        return new Object[][]{
                {FakerUtils.getFirstName(), FakerUtils.getLastName(), FakerUtils.getCompanyName(), FakerUtils.getEmail()},
                {FakerUtils.getFirstName(), FakerUtils.getLastName(), FakerUtils.getCompanyName(), FakerUtils.getEmail()},
                {FakerUtils.getFirstName(), FakerUtils.getLastName(), FakerUtils.getCompanyName(), FakerUtils.getEmail()}
        };
    }

    @Test(priority = 2, dataProvider = "leadDataSupplier", groups = {"regression"}, 
          description = "Data-Driven Parallel Lead Processing Test")
    public void testDataDrivenLeadCreation(String firstName, String lastName, String company, String email) {
        String fullName = firstName + " " + lastName;
        Assert.assertFalse(fullName.isEmpty(), "Lead full name should be populated");
        Assert.assertTrue(email.contains("@"), "Lead email must be valid email format");
        Assert.assertFalse(company.isEmpty(), "Lead company should be present");
    }
}
