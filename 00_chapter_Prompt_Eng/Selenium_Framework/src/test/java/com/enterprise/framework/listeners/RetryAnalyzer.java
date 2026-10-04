package com.enterprise.framework.listeners;

import com.enterprise.framework.config.ConfigReader;
import com.enterprise.framework.constants.FrameworkConstants;
import com.enterprise.framework.utils.Log;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 * Automatically retries failed tests to counteract intermittent network/environment blips.
 */
public class RetryAnalyzer implements IRetryAnalyzer {

    private int retryCount = 0;

    @Override
    public boolean retry(ITestResult result) {
        boolean retryEnabled = ConfigReader.getBoolean("retry.failed.tests", true);
        int maxRetry = ConfigReader.getInt("max.retry.count", FrameworkConstants.MAX_RETRY_COUNT);

        if (retryEnabled && retryCount < maxRetry) {
            retryCount++;
            Log.warn("Retrying test [" + result.getName() + "] with status [" + result.getStatus() 
                    + "] for the " + retryCount + " time(s).");
            return true;
        }
        return false;
    }
}
