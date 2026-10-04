package com.enterprise.framework.utils;

import net.datafaker.Faker;

import java.util.Locale;

/**
 * Synthetic CRM test data generator powered by DataFaker.
 * Supplies dynamic, realistic data for Leads, Accounts, Contacts, and Opportunities.
 */
public final class FakerUtils {

    private static final Faker FAKER = new Faker(Locale.US);

    private FakerUtils() {
        // Prevent instantiation
    }

    public static String getFirstName() {
        return FAKER.name().firstName();
    }

    public static String getLastName() {
        return FAKER.name().lastName();
    }

    public static String getFullName() {
        return FAKER.name().fullName();
    }

    public static String getCompanyName() {
        return FAKER.company().name();
    }

    public static String getJobTitle() {
        return FAKER.job().title();
    }

    public static String getEmail() {
        return FAKER.internet().emailAddress();
    }

    public static String getPhoneNumber() {
        return FAKER.phoneNumber().phoneNumber();
    }

    public static String getWebsite() {
        return "https://www." + FAKER.internet().domainName();
    }

    public static String getStreetAddress() {
        return FAKER.address().streetAddress();
    }

    public static String getCity() {
        return FAKER.address().city();
    }

    public static String getState() {
        return FAKER.address().state();
    }

    public static String getPostalCode() {
        return FAKER.address().zipCode();
    }

    public static String getCountry() {
        return FAKER.address().country();
    }
}
