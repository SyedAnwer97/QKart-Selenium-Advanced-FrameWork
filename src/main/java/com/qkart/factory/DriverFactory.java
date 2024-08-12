package com.qkart.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public final class DriverFactory {

    private DriverFactory() {
    }

    private static Map<String, Supplier<WebDriver>> map = new HashMap<>();

    private static Supplier<WebDriver> chrome = () -> {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    };

    private static Supplier<WebDriver> firefox = () -> {
        WebDriverManager.chromedriver().setup();
        return new FirefoxDriver();
    };

    static {
        map.put("chrome", chrome);
        map.put("firefox", firefox);
    }

    public static WebDriver getBrowserDriver(String browserDriver) {
        return map.get(browserDriver).get();
    }
}
