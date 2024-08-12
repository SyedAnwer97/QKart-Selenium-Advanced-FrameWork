package com.qkart.report.extentreport;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.qkart.driver.DriverManager;
import com.qkart.enums.Category;
import com.qkart.enums.PropertyEnums;
import com.qkart.utils.PropertyUtils;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.Objects;

public class ExtentReport {

    private static ExtentReports extentReports;

    public static void initReport() {
        if (Objects.isNull(extentReports)) {
            String reportPath = System.getProperty("user.dir") + "/extent-test-output/index.html";
            ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(reportPath);
            extentReports = new ExtentReports();
            extentReports.attachReporter(extentSparkReporter);

            extentSparkReporter.config().setTheme(Theme.STANDARD);
            extentSparkReporter.config().setDocumentTitle("GPN Test Report");
            extentSparkReporter.config().setReportName("GPN Automation Report");
            extentSparkReporter.config().setJs("document.getElementsByClassName('logo')[0].style.display='none';");

            extentReports.setSystemInfo("Executed on OS & Java: ", System.getProperty("os.name") + " Java : " + System.getProperty("java.version"));
            extentReports.setSystemInfo("Executed on Environment: ", PropertyUtils.getProperty(PropertyEnums.URL));
            extentReports.setSystemInfo("Executed by User: ", System.getProperty("user.name"));
        }
    }

    public static ExtentTest createTest(String testNames) {
        return extentReports.createTest(testNames);
    }

    public static void flushReport() {
        if (Objects.nonNull(extentReports)) {
            extentReports.flush();
            ExtentManager.unloadTest();
        }
    }

    public static void addBrowser() {
        Capabilities capabilities = ((RemoteWebDriver) DriverManager.getDriver()).getCapabilities();
        String string = System.getProperty("os.name") + "::" + capabilities.getBrowserName() + " "
                + capabilities.getBrowserVersion().substring(0, capabilities.getBrowserVersion().indexOf("."));
        ExtentManager.getTest().assignDevice(string);
    }

    public static void addAuthor(String[] authors) {
        for (String author : authors) {
            ExtentManager.getTest().assignAuthor(author);
        }
    }

    public static void addCategories(Category[] categories) {
        for (Category category : categories) {
            ExtentManager.getTest().assignCategory(category.name());
        }
    }

}