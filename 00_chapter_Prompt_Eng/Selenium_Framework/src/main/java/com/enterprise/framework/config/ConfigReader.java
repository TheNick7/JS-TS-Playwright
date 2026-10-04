package com.enterprise.framework.config;

import com.enterprise.framework.constants.FrameworkConstants;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

/**
 * Enterprise Configuration Manager.
 * Loads environment-specific properties with CLI system property override support.
 */
public final class ConfigReader {

    private static final Properties PROPERTIES = new Properties();

    static {
        loadProperties();
    }

    private ConfigReader() {
        // Prevent instantiation
    }

    private static void loadProperties() {
        // Determine active environment (default to 'qa' if not set via CLI -Denv=qa)
        String env = System.getProperty("env");
        if (env == null || env.trim().isEmpty()) {
            env = "qa";
        }
        env = env.trim().toLowerCase();

        // 1. Load base configuration
        loadFromFile(FrameworkConstants.DEFAULT_CONFIG_FILE);

        // 2. Load environment-specific properties (overriding base)
        String envConfigFile = FrameworkConstants.CONFIG_DIR + env + "-config.properties";
        File file = new File(envConfigFile);
        if (file.exists()) {
            loadFromFile(envConfigFile);
        }
    }

    private static void loadFromFile(String filePath) {
        try (FileInputStream fis = new FileInputStream(filePath)) {
            PROPERTIES.load(fis);
        } catch (IOException e) {
            System.err.println("Warning: Could not read properties file at: " + filePath + ". " + e.getMessage());
        }
    }

    /**
     * Retrieves configuration value with CLI system property fallback/override.
     * Order of precedence: System Property (-Dkey=value) -> Properties File -> Default Value.
     *
     * @param key Config key
     * @return Resolved property value
     */
    public static String get(String key) {
        if (Objects.isNull(key)) {
            return null;
        }

        // CLI system property has highest priority
        String systemProp = System.getProperty(key);
        if (systemProp != null && !systemProp.trim().isEmpty()) {
            return systemProp.trim();
        }

        String propVal = PROPERTIES.getProperty(key.trim());
        return propVal != null ? propVal.trim() : null;
    }

    public static String get(String key, String defaultValue) {
        String value = get(key);
        return (value != null && !value.isEmpty()) ? value : defaultValue;
    }

    public static boolean getBoolean(String key, boolean defaultValue) {
        String value = get(key);
        return value != null ? Boolean.parseBoolean(value) : defaultValue;
    }

    public static int getInt(String key, int defaultValue) {
        String value = get(key);
        if (value != null) {
            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException ignored) {
            }
        }
        return defaultValue;
    }
}
