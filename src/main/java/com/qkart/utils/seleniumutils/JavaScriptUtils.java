package com.qkart.utils.seleniumutils;

import com.google.common.util.concurrent.Uninterruptibles;
import com.qkart.driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.time.Duration;

public final class JavaScriptUtils {

    private JavaScriptUtils() {
    }

    static JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();

    public static void flash(WebElement element) {
        String bgColor = element.getCssValue("backgroundColor");
        for (int i = 0; i < 15; i++) {
            changeColor("#FFFF00", element);
            changeColor(bgColor, element);
        }
    }

    public static void changeColor(String color, WebElement element) {
        js.executeScript("arguments[0].style.backgroundColor='" + color + "'", element);
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(3));
    }

    public static void drawBorder(WebElement element) {
        js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 3px solid red;');", element);
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(3));
        js.executeScript("arguments[0].setAttribute('style', 'background: ; border: 0px solid red;');", element);
    }

    public static String getTitle() {
        return (String) js.executeScript("return document.title;");
    }

    public static String getInnerText(WebElement element) {
        return (String) js.executeScript("return arguments[0].innerText;", element);
    }

    public static void clickElementJs(By by) {
        js.executeScript("arguments[0].click();", DriverManager.getDriver().findElement(by));
    }

    public static void generateAlert(String message) {
        js.executeScript("alert('" + message + "')");
    }

    public static void refreshBrowser() {
        js.executeScript("history.go(0)");
    }

    public static void scrollPageDown() {
        js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
    }

    public static void scrollPageUp() {
        js.executeScript("window.scrollTo(0,-document.body.scrollHeight)");
    }

    public static void scrollToElementUsingJS(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView();", element);
    }

    public static void scrollToPixelPositionUsingJS(int xPixel, int yPixel) {
        js.executeScript("window.scrollBy(" + xPixel + "," + yPixel + ")", "");
    }

    public static void zoomPageByJs() {
        js.executeScript("document.body.style.zoom='50%'");
    }

    public static void sendKeysUsingJS(By by, String value) {
        js.executeScript("arguments[0].value= '" + value + "';", DriverManager.getDriver().findElement(by));
    }

}