package com.qkart.pages;

import com.qkart.driver.DriverManager;
import com.qkart.enums.LogStatus;
import com.qkart.enums.Waits;
import com.qkart.factory.WaitFactory;
import com.qkart.report.FWLogger;
import com.qkart.utils.seleniumutils.JavaScriptUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class PageActions {

    protected static void clickAction(By by, String elementName) {
        try {
            WaitFactory.waitStratgy(Waits.CLICKABLE, by).click();
        } catch (Exception e) {
            JavaScriptUtils.clickElementJs(by);
        }
        FWLogger.log(LogStatus.EXRENTPASSLOG4JINFO, elementName + " : is clicked");
    }

    protected static void sendKeysAction(By by, String key, String elementName) {
        try {
            WaitFactory.waitStratgy(Waits.VISIBLE, by).sendKeys(key);
        } catch (Exception e) {
            JavaScriptUtils.sendKeysUsingJS(by, key);
        }
        FWLogger.log(LogStatus.EXRENTPASSLOG4JINFO, elementName + " : is passed with " + key);
    }

    protected static void elementIsDisplayed(By by, String elementName) {
        WaitFactory.waitStrategy(a -> a.until(ExpectedConditions.visibilityOfElementLocated(by)));
        FWLogger.log(LogStatus.EXRENTPASSLOG4JINFO, elementName + " : is displayed");
    }

    protected static void clearAction(By by) {
        WaitFactory.waitStratgy(Waits.VISIBLE, by).clear();
    }

    protected static void selectAction(By by, Consumer<Select> consumer, String elementName) {
        consumer.accept(new Select(WaitFactory.waitStratgy(Waits.VISIBLE, by)));
        FWLogger.log(LogStatus.EXRENTPASSLOG4JINFO, elementName + " is selected");
    }

    protected static Alert alertAction() {
        Supplier<Alert> alertSupplier = () -> DriverManager.getDriver().switchTo().alert();
        return alertSupplier.get();
    }

}