package com.qkart.listeners;

import com.qkart.annotation.FrameworkAnnotation;
import com.qkart.enums.LogStatus;
import com.qkart.report.FWLogger;
import com.qkart.report.extentreport.ExtentManager;
import com.qkart.report.extentreport.ExtentReport;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

public final class TestListeners implements ITestListener, ISuiteListener {

    @Override
    public void onStart(ISuite suite) {
        ExtentReport.initReport();
    }

    @Override
    public void onFinish(ISuite suite) {
        ExtentReport.flushReport();
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentManager.setTest(ExtentReport.createTest(result.getMethod().getMethodName()));
        ExtentReport.addAuthor(result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(FrameworkAnnotation.class).author());
        ExtentReport.addCategories(result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(FrameworkAnnotation.class).category());
        ExtentReport.addBrowser();
        FWLogger.log(LogStatus.LOG4JSTARTTEST, result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        FWLogger.log(LogStatus.EXRENTPASSLOG4JINFO, "the test is passed");
        FWLogger.log(LogStatus.LOG4JENDTEST, result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        FWLogger.log(LogStatus.EXTENTTHOWABLE, result.getThrowable());
        FWLogger.log(LogStatus.EXRENTFAILLOG4JERROR, "the test is failed");
        FWLogger.log(LogStatus.LOG4JENDTEST, result.getMethod().getMethodName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        FWLogger.log(LogStatus.EXTENTSKIP, "the test is failed");
        FWLogger.log(LogStatus.LOG4JTESTINFO, "the test is failed");
        FWLogger.log(LogStatus.LOG4JENDTEST, result.getMethod().getMethodName());
    }

}
