package com.qkart.report.extentreport;

import com.aventstack.extentreports.ExtentTest;

public class ExtentManager {

    private static final ThreadLocal<ExtentTest> T_TEST = new ThreadLocal<>();

    public static void setTest(ExtentTest test) {
        T_TEST.set(test);
    }

    public static ExtentTest getTest() {
        return T_TEST.get();
    }

    public static void unloadTest() {
        T_TEST.remove();
    }

}