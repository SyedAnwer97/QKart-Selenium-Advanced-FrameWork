package com.qkart.factory;

import com.qkart.driver.DriverManager;
import com.qkart.enums.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.function.Consumer;

public final class WaitFactory {

    private WaitFactory() {
    }

    public static WebElement waitStratgy(Waits waits, By by) {
        WebElement element = null;
        WebDriverWait driverWait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(10));
        switch (waits) {
            case CLICKABLE:
                element = driverWait.until(ExpectedConditions.elementToBeClickable(by));
            case PRESENCE:
                element = driverWait.until(ExpectedConditions.presenceOfElementLocated(by));
            case VISIBLE:
                element = driverWait.until(ExpectedConditions.visibilityOfElementLocated(by));
            case NONE:
                element = DriverManager.getDriver().findElement(by);
        }
        return element;
    }

    public static void waitStrategy(Consumer<WebDriverWait> wait) {
        wait.accept(new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(10)));
    }

}
