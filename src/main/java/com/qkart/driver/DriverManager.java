package com.qkart.driver;

import org.openqa.selenium.WebDriver;

public final class DriverManager {

    private DriverManager() {
    }

    private static final ThreadLocal<WebDriver> T_DRIVER = new ThreadLocal<>();

    public static void setDriver(WebDriver driver) {
        T_DRIVER.set(driver);
    }

    public static WebDriver getDriver() {
        return T_DRIVER.get();
    }

    public static void unloadDriver() {
        T_DRIVER.remove();
    }

}
