package com.qkart.listeners;

import com.qkart.enums.PropertyEnums;
import com.qkart.utils.PropertyUtils;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public final class RetryFailedTest implements IRetryAnalyzer {

    private int count = 0;
    private static final int RETRTCOUNT = 1;

    @Override
    public boolean retry(ITestResult iTestResult) {
        boolean value = false;
        if (PropertyUtils.getProperty(PropertyEnums.RERUNFAILEDTEST).equalsIgnoreCase("yes")) {
            value = count < RETRTCOUNT;
            count++;
        }
        return value;
    }

}
