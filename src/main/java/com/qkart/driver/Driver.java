package com.qkart.driver;

import com.qkart.enums.PropertyEnums;
import com.qkart.factory.DriverFactory;
import com.qkart.utils.PropertyUtils;
import org.openqa.selenium.WebDriver;

public final class Driver {

    private Driver() {
    }

    public static void initDriver() {
        String browserName = PropertyUtils.getProperty(PropertyEnums.BROWSER);
        WebDriver browserDriver = DriverFactory.getBrowserDriver(browserName);
        DriverManager.setDriver(browserDriver);
        DriverManager.getDriver().manage().window().maximize();
        DriverManager.getDriver();
    }

    public static void tearDown() {
        DriverManager.getDriver().quit();
        DriverManager.unloadDriver();
    }

}
