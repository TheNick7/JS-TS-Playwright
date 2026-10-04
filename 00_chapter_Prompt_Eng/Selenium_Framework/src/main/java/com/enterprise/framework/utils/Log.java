package com.enterprise.framework.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Enterprise Logger wrapper around Apache Log4j2.
 * Provides unified static logging methods with class-context resolution.
 */
public final class Log {

    private static final Logger LOGGER = LogManager.getLogger(Log.class);

    private Log() {
        // Prevent instantiation
    }

    public static void info(String message) {
        LOGGER.info(message);
    }

    public static void warn(String message) {
        LOGGER.warn(message);
    }

    public static void error(String message) {
        LOGGER.error(message);
    }

    public static void error(String message, Throwable throwable) {
        LOGGER.error(message, throwable);
    }

    public static void debug(String message) {
        LOGGER.debug(message);
    }
}
